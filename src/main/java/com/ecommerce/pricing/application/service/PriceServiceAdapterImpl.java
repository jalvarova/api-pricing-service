package com.ecommerce.pricing.application.service;

import com.ecommerce.pricing.domain.mappers.PriceMapper;
import com.ecommerce.pricing.domain.model.PriceResponse;
import com.ecommerce.pricing.domain.ports.in.GetAllPriceForProductUseCase;
import com.ecommerce.pricing.domain.ports.in.GetPriceForIdentifierUseCase;
import com.ecommerce.pricing.domain.ports.in.GetPriceUseCase;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class PriceServiceAdapterImpl implements PriceServiceAdapter {

  private final GetAllPriceForProductUseCase getAllPriceForProductUseCase;
  private final GetPriceForIdentifierUseCase getPriceForIdentifierUseCase;
  private final GetPriceUseCase getPriceUseCase;

  @Override
  public Flux<PriceResponse> getAllPriceByProduct(Long productId) {
    return getAllPriceForProductUseCase.getAllPriceByProduct(productId).map(PriceMapper.toApi);
  }

  @Override
  public Mono<PriceResponse> getPriceForIdentifier(Long id) {
    return getPriceForIdentifierUseCase.getPriceForIdentifier(id).map(PriceMapper.toApi);
  }

  @Override
  public Mono<PriceResponse> getPriceProduct(Long productId, Integer brandId, LocalDateTime date) {
    return getPriceUseCase.getPriceProduct(productId, brandId, date).map(PriceMapper.toApi);
  }
}
