package com.ecommerce.pricing.application.usecase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.util.BuilderObjectMocks;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class GetAllPricesByProductIdUseCaseTest {

  @Mock
  private PriceRepositoryPort priceRepository;

  @InjectMocks
  private GetAllPricesByProductIdUseCaseImpl useCase;

  private static final Integer PRODUCT_ID = 35455;
  private static final Integer MISSING_PRODUCT = 99999;

  @ParameterizedTest(name = "When productId={0}, should return {2} prices")
  @MethodSource("productIdScenarios")
  @DisplayName("Find applicable price for various application dates")
  void shouldReturnExpectedPriceCountGivenProductId(Integer productId,
      List<Price> stubbed,
      List<Price> expected) {

    when(priceRepository.findAllPricesByProductId(productId))
        .thenReturn(Flux.fromIterable(stubbed));

    Flux<Price> result = useCase.execute(productId);

    StepVerifier.create(result)
        .expectNextSequence(expected)
        .verifyComplete();

    verify(priceRepository).findAllPricesByProductId(productId);
  }

  static Stream<Arguments> productIdScenarios() {
    BuilderObjectMocks m = new BuilderObjectMocks();

    List<Price> existingPrices = m.getListDomainPricesByProduct();
    List<Price> emptyPrices = Collections.emptyList();

    return Stream.of(
        Arguments.of(PRODUCT_ID, existingPrices, existingPrices),
        Arguments.of(MISSING_PRODUCT, emptyPrices, emptyPrices)
    );
  }

}
