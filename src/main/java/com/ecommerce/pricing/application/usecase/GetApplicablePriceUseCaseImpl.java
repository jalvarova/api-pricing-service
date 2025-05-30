package com.ecommerce.pricing.application.usecase;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.in.GetApplicablePriceUseCase;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.config.TimedLog;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetApplicablePriceUseCaseImpl implements GetApplicablePriceUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  @TimedLog
  @Override
  public Mono<Price> getApplicablePrice(Long productId, Integer brandId,
      LocalDateTime applicationDate) {
    return priceRepositoryPort.findApplicablePrices(productId, brandId, applicationDate);
  }
}
