package com.ecommerce.pricing.application.service;

import com.ecommerce.pricing.domain.mappers.PriceMapper;
import com.ecommerce.pricing.domain.model.PriceResponse;
import com.ecommerce.pricing.domain.ports.in.GetAllPricesByProductIdUseCase;
import com.ecommerce.pricing.domain.ports.in.GetPriceByIdUseCase;
import com.ecommerce.pricing.domain.ports.in.GetApplicablePriceUseCase;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class PriceServiceAdapterImpl implements PriceServiceAdapter {

  private final GetAllPricesByProductIdUseCase getAllPricesByProductIdUseCase;
  private final GetPriceByIdUseCase getPriceByIdUseCase;
  private final GetApplicablePriceUseCase getApplicablePriceUseCase;

  @Override
  public Flux<PriceResponse> getAllPriceByProduct(Long productId) {
    return getAllPricesByProductIdUseCase.getAllPriceByProduct(productId).map(PriceMapper.toApi);
  }

  @Override
  public Mono<PriceResponse> getPriceForIdentifier(Long id) {
    return getPriceByIdUseCase.getPriceForIdentifier(id).map(PriceMapper.toApi);
  }

  @Override
  public Mono<PriceResponse> getPriceProduct(Long productId, Integer brandId, LocalDateTime date) {
    return getApplicablePriceUseCase.getPriceProduct(productId, brandId, date).map(PriceMapper.toApi);
  }
}
