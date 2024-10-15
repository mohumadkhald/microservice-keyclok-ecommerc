package org.springframework.samples.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.orderservice.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
