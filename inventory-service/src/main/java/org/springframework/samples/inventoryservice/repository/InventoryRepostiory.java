package org.springframework.samples.inventoryservice.repository;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.inventoryservice.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepostiory extends CrudRepository<Inventory, Long> {
  Optional<Inventory> findBySkuCode(String skuCode);

  List <Inventory> findBySkuCodeIn(List<String> skuCode);
}
