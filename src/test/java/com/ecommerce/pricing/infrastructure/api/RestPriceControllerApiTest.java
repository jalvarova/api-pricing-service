package com.ecommerce.pricing.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.ecommerce.pricing.infrastructure.adapter.in.dto.PriceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RestPriceControllerApiTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice1() {

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("brandId", 1)
            .queryParam("productId", 35455)
            .queryParam("applicationDate", "2020-06-14T10:00:00Z")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        .expectBody()
        .jsonPath("$.price").isEqualTo(35.50)
        .jsonPath("$.productId").isEqualTo(35455)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice2() {

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("brandId", 1)
            .queryParam("productId", 35455)
            .queryParam("applicationDate", "2020-06-14T16:00:00Z")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(25.45)
        .jsonPath("$.productId").isEqualTo(35455)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice3() {

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("brandId", 1)
            .queryParam("productId", 35455)
            .queryParam("applicationDate", "2020-06-14T21:00:00Z")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(35.50)
        .jsonPath("$.productId").isEqualTo(35455)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice4() {

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("brandId", 1)
            .queryParam("productId", 35455)
            .queryParam("applicationDate", "2020-06-15T10:00:00Z")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(30.50)
        .jsonPath("$.productId").isEqualTo(35455)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice5() {

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("brandId", 1)
            .queryParam("productId", 35455)
            .queryParam("applicationDate", "2020-06-16T21:00:00Z")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(38.95)
        .jsonPath("$.productId").isEqualTo(35455)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 6: petición a las 10:00 del día 14 del producto 35456 para la brand 1 (ZARA)")
  void apiPostFinalPrice6() {

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("brandId", 1)
            .queryParam("productId", 35456)
            .queryParam("applicationDate", "2020-06-14T10:00:00Z")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(47.5)
        .jsonPath("$.productId").isEqualTo(35456)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 7: petición a las 16:00 del día 15 del producto 35456 para la brand 1 (ZARA)")
  void apiPostFinalPrice7() {

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("brandId", 1)
            .queryParam("productId", 35456)
            .queryParam("applicationDate", "2020-06-15T16:00:00Z")
            .build()
        )
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(45.95)
        .jsonPath("$.productId").isEqualTo(35456)
        .jsonPath("$.brandId").isEqualTo(1);
  }


  @Test
  @DisplayName("Debe devolver lista de precios para el producto 35455 con tamaño esperado")
  void shouldReturnAllPricesForProduct() {

    webTestClient.get()
        .uri("/products/{productId}/prices",35455)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(PriceResponse.class)
        .hasSize(4);
  }

  @Test
  @DisplayName("Debe devolver precio por ID cuando se consulta ID=1")
  void shouldReturnPriceById1() {

    webTestClient.get()
        .uri("/prices/{id}",1)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        .expectBody()
        .consumeWith(response -> {
          String responseBody = new String(response.getResponseBody());

          assertThat(responseBody).contains("\"price\":35.5");
          assertThat(responseBody).contains("\"productId\":35455");
          assertThat(responseBody).contains("\"brandId\":1");
        });
  }
}
