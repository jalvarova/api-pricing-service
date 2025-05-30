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
public class GetPriceByIdUseCaseTest {

  @Mock
  private PriceRepositoryPort priceRepository;

  @InjectMocks
  private GetPriceByIdUseCaseImpl useCase;

  private static final Long ID = 1L;

  private final BuilderObjectMocks mapper = new BuilderObjectMocks();

  @Test
  @DisplayName("Given a price ID, when executing use case, then it should return the expected price")
  void returnsExpectedPriceWhenPriceIdIsValid() {

    Price expected = mapper.buildDomain(mapper.getPriceEntity1());
    when(priceRepository.findPriceById(ID))
        .thenReturn(Mono.just(expected));

    Mono<Price> result = useCase.execute(ID);

    StepVerifier.create(result)
        .expectNext(expected)
        .verifyComplete();

    verify(priceRepository).findPriceById(ID);
  }
}
