package org.springframework.samples.orderservice.controler;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.orderservice.dto.OrderRequest;
import org.springframework.samples.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackOrder")
  @TimeLimiter(name = "inventory")
  @Retry(fallbackMethod = "fallbackOrder", name = "inventory")
  public CompletableFuture<String> placeOrder(@RequestBody OrderRequest order) {
//    orderService.placeOrder(order);
//    return "Order placed successfully";
    return CompletableFuture.supplyAsync(() -> orderService.placeOrder(order));
  }

  public CompletableFuture<String> fallbackOrder(OrderRequest orderRequest, RuntimeException runtimeException) {
    return CompletableFuture.supplyAsync(() -> "Order fallback due to: " + runtimeException.getMessage());
  }

}
