package com.ecommerce.pricing.application.usecase;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.util.BuilderObjectMocks;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceUseCaseTest {

  @Mock
  private PriceRepositoryPort priceRepository;

  @InjectMocks
  private GetApplicablePriceUseCaseImpl getPriceUseCase;

  private static final Long PRODUCT_ID = 35455L;
  private static final Integer BRAND = 1;
  private final BuilderObjectMocks mapper = new BuilderObjectMocks();

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void getAllPriceTest1() {

    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

    when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND, date))
        .thenReturn(Mono.just(mapper.buildDomain(mapper.getPriceEntity1())));

    Mono<Price> result = getPriceUseCase.getPriceProduct(PRODUCT_ID, BRAND, date);

    StepVerifier.create(result)
        .expectNextCount(1)
        .verifyComplete();

    verify(priceRepository).findApplicablePrices(PRODUCT_ID, BRAND, date);
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void getAllPriceTest2() {

    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);

    when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND, date))
        .thenReturn(Mono.just(mapper.buildDomain(mapper.getPriceEntity1())));

    Mono<Price> result = getPriceUseCase.getPriceProduct(PRODUCT_ID, BRAND, date);

    StepVerifier.create(result)
        .expectNextCount(1)
        .verifyComplete();

    verify(priceRepository).findApplicablePrices(PRODUCT_ID, BRAND, date);
  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void getAllPriceTest3() {

    LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);

    when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND, date))
        .thenReturn(Mono.just(mapper.buildDomain(mapper.getPriceEntity1())));

    Mono<Price> result = getPriceUseCase.getPriceProduct(PRODUCT_ID, BRAND, date);

    StepVerifier.create(result)
        .expectNextCount(1)
        .verifyComplete();

    verify(priceRepository).findApplicablePrices(PRODUCT_ID, BRAND, date);
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
  void getAllPriceTest4() {

    LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);

    when(priceRepository.findApplicablePrices(PRODUCT_ID, 1, date))
        .thenReturn(Mono.just(mapper.buildDomain(mapper.getPriceEntity1())));

    Mono<Price> result = getPriceUseCase.getPriceProduct(PRODUCT_ID, 1, date);

    StepVerifier.create(result)
        .expectNextCount(1)
        .verifyComplete();

    verify(priceRepository).findApplicablePrices(PRODUCT_ID, 1, date);
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
  void getAllPriceTest5() {

    LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

    when(priceRepository.findApplicablePrices(PRODUCT_ID, 1, date))
        .thenReturn(Mono.just(mapper.buildDomain(mapper.getPriceEntity1())));

    Mono<Price> result = getPriceUseCase.getPriceProduct(PRODUCT_ID, 1, date);

    StepVerifier.create(result)
        .expectNextCount(1)
        .verifyComplete();

    verify(priceRepository).findApplicablePrices(PRODUCT_ID, 1, date);
  }
}
