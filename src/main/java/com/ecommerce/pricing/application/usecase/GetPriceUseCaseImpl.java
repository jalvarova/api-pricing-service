package com.ecommerce.pricing.application.usecase;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.in.GetPriceUseCase;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.config.TimedLog;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  @TimedLog
  @Override
  public Mono<Price> getPriceProduct(Long productId, Integer brandId,
      LocalDateTime applicationDate) {
    return priceRepositoryPort.getPricesByDate(productId, brandId, applicationDate)
        .sort((p1, p2) -> Integer.compare(p2.getPriority(),
            p1.getPriority()))
        .next();
  }
}
