package org.springframework.samples.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.inventoryservice.model.Inventory;
import org.springframework.samples.inventoryservice.repository.InventoryRepostiory;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

  public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
  @Bean
  public CommandLineRunner loadData(InventoryRepostiory inventoryRepostiory) {
    return args -> {
      System.out.println("Welcome to Inventory Service");
      Inventory inventory1 = new Inventory();
      inventory1.setSkuCode("iphone_12");
      inventory1.setQuantity(50);
      Inventory inventory2 = new Inventory();
      inventory2.setSkuCode("iphone_13");
      inventory2.setQuantity(10);
      Inventory inventory3 = new Inventory();
      inventory3.setSkuCode("iphone_14");
      inventory3.setQuantity(0);
      inventoryRepostiory.save(inventory1);
      inventoryRepostiory.save(inventory2);
      inventoryRepostiory.save(inventory3);
      System.out.println("Inventory saved");
    };
  }

}
