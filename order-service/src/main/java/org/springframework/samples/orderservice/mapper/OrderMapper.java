package org.springframework.samples.orderservice.mapper;

import org.springframework.samples.orderservice.dto.OrderLineItemsDto;
import org.springframework.samples.orderservice.model.OrderLineItems;

public interface OrderMapper {
  static OrderLineItems toOrderDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode()); // Corrected line
    return orderLineItems;
  }
}
