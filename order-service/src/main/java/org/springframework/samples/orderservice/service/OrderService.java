package org.springframework.samples.orderservice.service;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.samples.orderservice.event.OrderPlacedEvent;
import org.springframework.samples.orderservice.dto.InventoryResponse;
import org.springframework.samples.orderservice.dto.OrderRequest;
import org.springframework.samples.orderservice.mapper.OrderMapper;
import org.springframework.samples.orderservice.model.Order;
import org.springframework.samples.orderservice.model.OrderLineItems;
import org.springframework.samples.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;
  private final WebClient.Builder webClientBuilder;
  private final Tracer tracer;
  private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
  @Value("${spring.kafka.topic.order}")
  private String orderTopic;

  public String placeOrder(OrderRequest request) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    List<OrderLineItems> orderLineItems = request.getOrderLineItemsDtoList().stream()
      .map(OrderMapper::toOrderDto)
      .toList();
    order.setOrderLineItems(orderLineItems);

    List<String> skuCodes = order.getOrderLineItems().stream()
      .map(OrderLineItems::getSkuCode)
      .toList();
    Span lookup = tracer.nextSpan().name("lookup in inventory");
    try(Tracer.SpanInScope spanInScope = tracer.withSpan(lookup.start())) {
      InventoryResponse[] inventoryResponses = webClientBuilder
        .build()
        .get()
        .uri("http://inventory-service/api/inventory",
          uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
        .retrieve()
        .bodyToMono(InventoryResponse[].class)
        .block();

      assert inventoryResponses != null;

      boolean allAvailableProducts = Arrays.stream(inventoryResponses)
        .allMatch(InventoryResponse::isAvailable);

      if (Boolean.TRUE.equals(allAvailableProducts)) {
        orderRepository.save(order);

        // Send order ID to Kafka for notification service
        OrderPlacedEvent event = new OrderPlacedEvent(order.getOrderNumber());
        kafkaTemplate.send(orderTopic, event);
        log.info("Order ID {} sent to Kafka", order.getOrderNumber());

        return "Order created";
      } else {
        List<String> unavailableSkuCodes = Arrays.stream(inventoryResponses)
          .filter(inventoryResponse -> !inventoryResponse.isAvailable())
          .map(InventoryResponse::getSkuCode)
          .toList();

        throw new IllegalArgumentException("The following SKU codes are not available: " + unavailableSkuCodes);
      }
    } finally {
      lookup.end();
    }
  }
}
