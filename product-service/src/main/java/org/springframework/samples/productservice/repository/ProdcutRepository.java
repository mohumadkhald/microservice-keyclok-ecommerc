package org.springframework.samples.productservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.productservice.model.Product;

public interface ProdcutRepository extends MongoRepository<Product, String> {
}
