package com.ecommerce.pricing.infrastructure.api;

import com.ecommerce.pricing.domain.model.PriceRequest;
import com.ecommerce.pricing.domain.model.PriceResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceControllerApiTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice1() {
    PriceRequest request = new PriceRequest(
        LocalDateTime.of(2020, 6, 14, 10, 0).atOffset(ZoneOffset.UTC),
        35455,
        1
    );

    webTestClient.post()
        .uri("/prices/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(35.50)
        .jsonPath("$.productId").isEqualTo(35455)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice2() {
    PriceRequest request = new PriceRequest(
        LocalDateTime.of(2020, 6, 14, 16, 0).atOffset(ZoneOffset.UTC),
        35455,
        1
    );

    webTestClient.post()
        .uri("/prices/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
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
    PriceRequest request = new PriceRequest(
        LocalDateTime.of(2020, 6, 14, 21, 0).atOffset(ZoneOffset.UTC),
        35455,
        1
    );

    webTestClient.post()
        .uri("/prices/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
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
    PriceRequest request = new PriceRequest(
        LocalDateTime.of(2020, 6, 15, 10, 0).atOffset(ZoneOffset.UTC),
        35455,
        1
    );

    webTestClient.post()
        .uri("/prices/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(30.50)
        .jsonPath("$.productId").isEqualTo(35455)
        .jsonPath("$.brandId").isEqualTo(1);
  }

  @Test
  @DisplayName("Test 5: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void apiPostFinalPrice5() {
    PriceRequest request = new PriceRequest(
        LocalDateTime.of(2020, 6, 15, 21, 0).atOffset(ZoneOffset.UTC),
        35455,
        1
    );

    webTestClient.post()
        .uri("/prices/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
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
    PriceRequest request = new PriceRequest(
        LocalDateTime.of(2020, 6, 14, 10, 0).atOffset(ZoneOffset.UTC),
        35456,
        1
    );

    webTestClient.post()
        .uri("/prices/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
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
    PriceRequest request = new PriceRequest(
        LocalDateTime.of(2020, 6, 15, 16, 0).atOffset(ZoneOffset.UTC),
        35456,
        1
    );

    webTestClient.post()
        .uri("/prices/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.price").isEqualTo(45.95)
        .jsonPath("$.productId").isEqualTo(35456)
        .jsonPath("$.brandId").isEqualTo(1);
  }


  @Test
  @DisplayName("")
  void apiGetAllPriceForProduct() {

    webTestClient.get()
        .uri("/prices/{productId}/product",35455)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(PriceResponse.class)
        .hasSize(4);
  }

  @Test
  @DisplayName("")
  void apiGetPriceForIdentifier() {

    webTestClient.get()
        .uri("/prices/{id}",1)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(PriceResponse.class)
        .hasSize(1);
  }
}
