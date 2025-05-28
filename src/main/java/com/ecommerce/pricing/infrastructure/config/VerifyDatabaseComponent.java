package com.ecommerce.pricing.infrastructure.config;

import com.ecommerce.pricing.infrastructure.db.repository.PriceRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class VerifyDatabaseComponent {

  @Bean
  public CommandLineRunner verifyDatabase(PriceRepositoryAdapter port) {
    return args -> {
      long count = port.selectCount().block();
      log.info(">>> PRICES table has {} rows.", count);
      port
          .getAllPricesByProductId(35455L)
          .toIterable()
          .forEach(
              p -> System.out.println("Price loaded: " + p.getProductId() + " -> " + p.getPrice()));
    };
  }
}
