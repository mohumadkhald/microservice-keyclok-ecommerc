package org.springframework.samples.inventoryservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.inventoryservice.dto.InventoryResponse;
import org.springframework.samples.inventoryservice.repository.InventoryRepostiory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryService {
  private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
  private final InventoryRepostiory inventoryRepostiory;

  @Transactional
  @SneakyThrows
  public List<InventoryResponse> isStock(List<String> skuCode) {
//    log.info("Wait Start Checking if stock code is {}", skuCode);
//    Thread.sleep(1000);
//    log.info("Wait End Checking if stock code is {}", skuCode);
    return inventoryRepostiory.findBySkuCodeIn(skuCode).stream()
      .map(inventory ->
        InventoryResponse.builder()
          .skuCode(inventory.getSkuCode())
          .available(inventory.getQuantity() > 0)
          .build()
    ).toList();
  }
}
