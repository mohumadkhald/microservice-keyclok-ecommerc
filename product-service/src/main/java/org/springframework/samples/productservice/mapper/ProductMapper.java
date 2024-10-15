package org.springframework.samples.productservice.mapper;

import org.springframework.samples.productservice.dto.ProductResponse;
import org.springframework.samples.productservice.model.Product;

public interface ProductMapper {

  public static ProductResponse toProductResponse (final Product product) {
    return ProductResponse.builder()
      .id(product.getId())
      .price(product.getPrice())
      .name(product.getName())
      .description(product.getDescription())
      .build();
  }
}
