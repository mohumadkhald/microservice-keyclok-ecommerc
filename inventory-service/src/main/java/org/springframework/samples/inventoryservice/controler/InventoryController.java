package org.springframework.samples.inventoryservice.controler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.samples.inventoryservice.dto.InventoryResponse;
import org.springframework.samples.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/inventory")
public class InventoryController {

  private  final InventoryService inventoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryResponse> isStock(@RequestParam List<String> skuCode) {
    log.info("isStock() invoked");
    return inventoryService.isStock(skuCode);
  }
}
