package com.ecommerce.pricing.application.usecase;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.in.GetPriceByIdUseCase;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.config.TimedLog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class GetPriceByIdUseCaseImpl implements GetPriceByIdUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  @TimedLog
  @Override
  public Mono<Price> getPriceForIdentifier(Long id) {
    return priceRepositoryPort.findPriceById(id);
  }
}
