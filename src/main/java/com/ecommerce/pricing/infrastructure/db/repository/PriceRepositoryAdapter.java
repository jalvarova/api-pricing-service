package com.ecommerce.pricing.infrastructure.db.repository;

import com.ecommerce.pricing.domain.mappers.PriceMapper;
import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private PriceRepository repository;

  @Override
  public Mono<Price> findApplicablePrices(Long productId, Integer brandId, LocalDateTime applicationDate) {
    return Mono.just(repository.findApplicablePrice(productId, brandId, applicationDate))
        .map(PriceMapper.toDomain);
  }

  @Override
  public Mono<Price> findPriceById(Long id) {
    return Mono.just(repository.findPriceById(id))
        .map(PriceMapper.toDomain);
  }

  @Override
  public Mono<Long> countPrices() {
    return Mono.just(repository.count());
  }

  @Override
  public Flux<Price> findAllPricesByProductId(Long productId) {
    return Flux.fromIterable(repository.findPrecesByProductId(productId)).map(PriceMapper.toDomain);
  }
}
