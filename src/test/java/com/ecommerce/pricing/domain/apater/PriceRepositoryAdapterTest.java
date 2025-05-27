package com.ecommerce.pricing.domain.apater;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.db.repository.PriceRepository;
import com.ecommerce.pricing.infrastructure.db.repository.PriceRepositoryAdapter;
import com.ecommerce.pricing.util.BuilderObjectMocks;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
public class PriceRepositoryAdapterTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceRepositoryAdapter adapter;

  private Long productId;

  @BeforeEach
  void setUp() {
    productId = 35455L;

  }

  @Test
  @DisplayName("get all the prices of a product")
  void getAllPricesByProductId() {

    when(priceRepository.findAllByProductId(productId))
        .thenReturn(BuilderObjectMocks.getListPricesByProduct());

    Flux<Price> result = adapter.getAllPricesByProductId(productId);

    StepVerifier.create(result)
        .expectNextCount(4).verifyComplete();

    verify(priceRepository).findAllByProductId(productId);
  }

  @Test
  @DisplayName("Get the price of the product by id")
  void getPriceById() {

    when(priceRepository.findById(1L))
        .thenReturn(Optional.of(BuilderObjectMocks.priceEntity1));

    Mono<Price> result = adapter.getPriceById(1L);

    StepVerifier.create(result)
        .assertNext(price -> {
          assertThat(price).isNotNull();
          assertThat(price.getProductId().equals(productId));
          assertThat(price.getPriority()).isEqualTo(0);
          assertThat(price.getPrice()).isEqualByComparingTo("35.50");
          assertThat(price.getCurr()).isEqualTo(BuilderObjectMocks.CURRENCY_CODE);
          assertThat(price.getPriceList()).isEqualTo(1L);

        })
        .verifyComplete();

    verify(priceRepository).findById(1L);
  }

  @Test
  @DisplayName("Get the price for a date range by product and brand")
  void getByProductBrandAndDate() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    when(priceRepository.findTopByProductBrandAndDate(productId, 1, date))
        .thenReturn(BuilderObjectMocks.getListPricesRangeDate());

    Flux<Price> result = adapter.getPricesByDate(productId, 1, date);

    StepVerifier.create(result)
        .expectNextCount(2)
        .verifyComplete();

    verify(priceRepository).findTopByProductBrandAndDate(productId, 1, date);
  }

}
