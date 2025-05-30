package com.ecommerce.pricing.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.ecommerce.pricing.infrastructure.adapter.in.dto.PriceResponse;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

  static Stream<Arguments> applicablePriceScenarios() {
    return Stream.of(
        Arguments.of(35455, 1, "2020-06-14T10:00:00Z", 35.50f),
        Arguments.of(35455, 1, "2020-06-14T16:00:00Z", 25.45f),
        Arguments.of(35455, 1, "2020-06-14T21:00:00Z", 35.50f),
        Arguments.of(35455, 1, "2020-06-15T10:00:00Z", 30.50f),
        Arguments.of(35455, 1, "2020-06-16T21:00:00Z", 38.95f),
        Arguments.of(35456, 1, "2020-06-14T10:00:00Z", 47.50f),
        Arguments.of(35456, 1, "2020-06-15T16:00:00Z", 45.95f)
    );
  }

  @ParameterizedTest(name = "GET /prices?productId={0}&brandId={1}&applicationDate={2} => price={3}")
  @MethodSource("applicablePriceScenarios")
  @DisplayName("GET applicable price for various scenarios")
  void getApplicablePriceParameterized(
      Integer productId,
      Integer brandId,
      String applicationDate,
      Float expectedPrice
  ) {
    webTestClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("productId", productId)
            .queryParam("brandId", brandId)
            .queryParam("applicationDate", applicationDate)
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        .expectBody(PriceResponse.class)
        .value(resp -> {
          assertThat(resp.getProductId()).isEqualTo(productId);
          assertThat(resp.getBrandId()).isEqualTo(brandId);
          assertThat(resp.getPrice()).isEqualTo(expectedPrice);
        });
  }


  @Test
  @DisplayName("GET /products/{productId}/prices returns full price list")
  void shouldReturnAllPricesForProduct() {

    webTestClient.get()
        .uri("/products/{productId}/prices",35455)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(PriceResponse.class)
        .hasSize(4);
  }

  @Test
  @DisplayName("GET /prices/{id} returns the correct price entry")
  void shouldReturnPriceById1() {

    webTestClient.get()
        .uri("/prices/{id}",1)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        .expectBody(PriceResponse.class)
        .value(resp -> {
          assertThat(resp.getProductId()).isEqualTo(35455);
          assertThat(resp.getBrandId()).isEqualTo(1);
          assertThat(resp.getPrice()).isEqualTo(35.50f);
        });
  }
}
