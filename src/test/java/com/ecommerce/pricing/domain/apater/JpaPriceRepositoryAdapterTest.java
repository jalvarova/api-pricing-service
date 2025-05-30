package com.ecommerce.pricing.domain.apater;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.out.repository.JpaPriceRepositoryAdapter;
import com.ecommerce.pricing.infrastructure.adapter.out.repository.PriceRepository;
import com.ecommerce.pricing.util.BuilderObjectMocks;
import java.time.LocalDateTime;
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
class JpaPriceRepositoryAdapterTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private JpaPriceRepositoryAdapter adapter;

  private static final Integer PRODUCT_ID = 35455;
  private final BuilderObjectMocks mapper = new BuilderObjectMocks();


  @Test
  @DisplayName("get all the prices of a product")
  void getAllPricesByProductId() {

    when(priceRepository.findPrecesByProductId(PRODUCT_ID))
        .thenReturn(mapper.getListPricesByProduct());

    Flux<Price> result = adapter.findAllPricesByProductId(PRODUCT_ID);

    StepVerifier.create(result)
        .expectNextCount(4).verifyComplete();

    verify(priceRepository).findPrecesByProductId(PRODUCT_ID);
  }

  @Test
  @DisplayName("Get the price of the product by id")
  void getPriceById() {

    when(priceRepository.findPriceById(1L))
        .thenReturn(mapper.getPriceEntity1());

    Mono<Price> result = adapter.findPriceById(1L);

    StepVerifier.create(result)
        .assertNext(price -> {
          assertThat(price).isNotNull();
          assertThat(price.getProductId().equals(PRODUCT_ID));
          assertThat(price.getPriority()).isZero();
          assertThat(price.getPrice()).isEqualByComparingTo("35.50");
          assertThat(price.getCurr()).isEqualTo(BuilderObjectMocks.CURRENCY_CODE);
          assertThat(price.getPriceList()).isEqualTo(1);

        })
        .verifyComplete();

    verify(priceRepository).findPriceById(1L);
  }

  @Test
  @DisplayName("Get the price for a date range by product and brand")
  void getByProductBrandAndDate() {
    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    when(priceRepository.findApplicablePrice(PRODUCT_ID, 1, date))
        .thenReturn(mapper.getPriceEntity1());

    Mono<Price> result = adapter.findApplicablePrices(PRODUCT_ID, 1, date);

    StepVerifier.create(result)
        .assertNext(price -> {
          assertThat(price).isNotNull();
          assertThat(price.getProductId().equals(PRODUCT_ID));
          assertThat(price.getPriority()).isZero();
          assertThat(price.getPrice()).isEqualByComparingTo("35.50");
          assertThat(price.getCurr()).isEqualTo(BuilderObjectMocks.CURRENCY_CODE);
          assertThat(price.getPriceList()).isEqualTo(1);

        })
        .verifyComplete();

    verify(priceRepository).findApplicablePrice(PRODUCT_ID, 1, date);
  }

}
