package com.ecommerce.pricing.infrastructure.adapter.out.repository;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.adapter.out.mapper.PriceEntityMapper;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class JpaPriceRepositoryAdapter implements PriceRepositoryPort {

  private PriceRepository repository;

  @Override
  public Mono<Price> findApplicablePrices(Long productId, Integer brandId, LocalDateTime applicationDate) {
    return Mono.justOrEmpty(repository.findApplicablePrice(productId, brandId, applicationDate))
        .map(PriceEntityMapper.toDomain);

  }

  @Override
  public Mono<Price> findPriceById(Long id) {
    return Mono.justOrEmpty(repository.findPriceById(id))
        .map(PriceEntityMapper.toDomain);
  }

  @Override
  public Mono<Long> countPrices() {
    return Mono.just(repository.count());
  }

  @Override
  public Flux<Price> findAllPricesByProductId(Long productId) {
    return Flux
        .fromIterable(repository.findPrecesByProductId(productId))
        .map(PriceEntityMapper.toDomain);
  }
}
