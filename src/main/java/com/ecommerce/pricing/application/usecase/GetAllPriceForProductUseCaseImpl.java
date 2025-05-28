package com.ecommerce.pricing.application.usecase;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.in.GetAllPriceForProductUseCase;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.config.TimedLog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class GetAllPriceForProductUseCaseImpl implements GetAllPriceForProductUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  @TimedLog
  @Override
  public Flux<Price> getAllPriceByProduct(Long productId) {
    return priceRepositoryPort.getAllPricesByProductId(productId);
  }
}
