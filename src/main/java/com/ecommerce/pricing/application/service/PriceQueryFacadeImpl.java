package com.ecommerce.pricing.application.service;

import com.ecommerce.pricing.infrastructure.adapter.in.mapper.PriceMapper;
import com.ecommerce.pricing.domain.ports.in.GetAllPricesByProductIdUseCase;
import com.ecommerce.pricing.domain.ports.in.GetPriceByIdUseCase;
import com.ecommerce.pricing.domain.ports.in.GetApplicablePriceUseCase;
import com.ecommerce.pricing.infrastructure.adapter.in.dto.PriceResponse;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class PriceQueryFacadeImpl implements PriceQueryFacade {

  private final GetAllPricesByProductIdUseCase getAllPricesByProductIdUseCase;
  private final GetPriceByIdUseCase getPriceByIdUseCase;
  private final GetApplicablePriceUseCase getApplicablePriceUseCase;

  @Override
  public Flux<PriceResponse> getAllPriceByProduct(Long productId) {
    return getAllPricesByProductIdUseCase.execute(productId).map(PriceMapper.toApi);
  }

  @Override
  public Mono<PriceResponse> getPriceForIdentifier(Long id) {
    return getPriceByIdUseCase.execute(id).map(PriceMapper.toApi);
  }

  @Override
  public Mono<PriceResponse> getApplicablePrice(Long productId, Integer brandId, LocalDateTime date) {
    return getApplicablePriceUseCase.execute(productId, brandId, date).map(PriceMapper.toApi);
  }
}
