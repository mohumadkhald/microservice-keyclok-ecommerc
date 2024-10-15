package org.springframework.samples.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.samples.productservice.dto.ProductRequest;
import org.springframework.samples.productservice.dto.ProductResponse;
import org.springframework.samples.productservice.mapper.ProductMapper;
import org.springframework.samples.productservice.model.Product;
import org.springframework.samples.productservice.repository.ProdcutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProdcutRepository prodcutRepository;
  public void addProduct(ProductRequest request) {
    Product product = Product.builder()
      .name(request.getName())
      .price(request.getPrice())
      .description(request.getDescription())
      .build();
    log.info("Adding product {}", product.getId());
    this.prodcutRepository.save(product);
  }

  public List<ProductResponse> getProducts() {
    List<Product> products = this.prodcutRepository.findAll();
    return products.stream().map(ProductMapper::toProductResponse).toList();
  }
}
