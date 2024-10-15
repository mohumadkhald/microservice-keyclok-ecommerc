package org.springframework.samples.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
  private List<OrderLineItemsDto> orderLineItemsDtoList= new ArrayList<>();
}
