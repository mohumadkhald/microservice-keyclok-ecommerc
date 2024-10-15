package org.springframework.samples.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String orderNumber;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<OrderLineItems> orderLineItems;

}
