package com.ecommerce.pricing.application.usecase;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.util.BuilderObjectMocks;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

  static Stream<Arguments> applicationDates() {
    return Stream.of(
        Arguments.of(LocalDateTime.of(2020, 6, 14, 10, 0, 0), "10:00 on 2020-06-14"),
        Arguments.of(LocalDateTime.of(2020, 6, 14, 16, 0, 0), "16:00 on 2020-06-14"),
        Arguments.of(LocalDateTime.of(2020, 6, 14, 21, 0, 0), "21:00 on 2020-06-14"),
        Arguments.of(LocalDateTime.of(2020, 6, 15, 10, 0, 0), "10:00 on 2020-06-15"),
        Arguments.of(LocalDateTime.of(2020, 6, 16, 21, 0, 0), "21:00 on 2020-06-16")
    );
  }

  @ParameterizedTest(name = "Test applicable price at {1}")
  @MethodSource("applicationDates")
  @DisplayName("Find applicable price for various application dates")
  void execute_shouldReturnPriceForGivenDate(LocalDateTime applicationDate, String label) {

    Price expected = mapper.buildDomain(mapper.getPriceEntity1());
    when(priceRepository.findApplicablePrices(PRODUCT_ID, BRAND, applicationDate))
        .thenReturn(Mono.just(expected));

    Mono<Price> result = getPriceUseCase.execute(PRODUCT_ID, BRAND, applicationDate);

    StepVerifier.create(result)
        .expectNext(expected)
        .verifyComplete();

    verify(priceRepository).findApplicablePrices(PRODUCT_ID, BRAND, applicationDate);
  }
}
