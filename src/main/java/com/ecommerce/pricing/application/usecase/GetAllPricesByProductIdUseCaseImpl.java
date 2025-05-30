package com.ecommerce.pricing.application.usecase;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.in.GetAllPricesByProductIdUseCase;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.config.TimedLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class GetAllPricesByProductIdUseCaseImpl implements GetAllPricesByProductIdUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  @TimedLog
  @Override
  public Flux<Price> getPricesByProductId(Long productId) {
    return priceRepositoryPort.findAllPricesByProductId(productId);
  }
}
