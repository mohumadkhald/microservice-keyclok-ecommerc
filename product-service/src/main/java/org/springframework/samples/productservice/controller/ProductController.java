package org.springframework.samples.productservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.samples.productservice.dto.ProductRequest;
import org.springframework.samples.productservice.dto.ProductResponse;
import org.springframework.samples.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
  private final ProductService productService;
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> products() {
    log.info("Get all products");
    return productService.getProducts();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addProduct(@RequestBody ProductRequest product) {
    productService.addProduct(product);
  }
}
