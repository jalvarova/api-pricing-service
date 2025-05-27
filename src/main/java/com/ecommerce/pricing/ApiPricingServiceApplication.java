package com.ecommerce.pricing;

import com.ecommerce.pricing.infrastructure.db.repository.PriceRepositoryAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiPricingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPricingServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner verifyDatabase(PriceRepositoryAdapter port) {
		return args -> {
			long count = port.selectCount().block();
			System.out.println(
					">>> PRICES table has " + count + " rows.");
			port
					.getAllPrices()
					.toIterable()
					.forEach(
							p -> System.out.println("Price loaded: " + p.getProductId() + " -> " + p.getPrice()));
		};
	}

}
