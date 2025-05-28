package com.ecommerce.pricing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "API de Consulta de Precios", version = "1.0.0"),
		servers = {
				@Server(url = "https://api-pricing-service-936595159798.us-central1.run.app/api/v1/pricing", description = "Production"),
				@Server(url = "http://localhost:8080/api/v1/pricing", description = "Local")
		}
)
@SpringBootApplication
public class ApiPricingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPricingServiceApplication.class, args);
	}
}
