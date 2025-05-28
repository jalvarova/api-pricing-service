package com.ecommerce.pricing.infrastructure.config;

import com.ecommerce.pricing.infrastructure.db.repository.PriceRepositoryAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VerifyDatabaseComponent {

  @Bean
  public CommandLineRunner verifyDatabase(PriceRepositoryAdapter port) {
    return args -> {
      long count = port.selectCount().block();
      System.out.println(
          ">>> PRICES table has " + count + " rows.");
      port
          .getAllPricesByProductId(35455L)
          .toIterable()
          .forEach(
              p -> System.out.println("Price loaded: " + p.getProductId() + " -> " + p.getPrice()));
    };
  }
}
