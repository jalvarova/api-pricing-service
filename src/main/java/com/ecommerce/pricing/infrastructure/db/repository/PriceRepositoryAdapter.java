package com.ecommerce.pricing.infrastructure.db.repository;

import com.ecommerce.pricing.domain.mappers.PriceMapper;
import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.db.entity.PriceEntity;
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
  public Flux<Price> getPricesByDate(Long productId, Integer brandId, LocalDateTime date) {
    return Flux.fromIterable(repository.findTopByProductBrandAndDate(productId, brandId, date))
        .map(PriceMapper.toDomain);
  }

  @Override
  public Mono<Price> getPriceById(Long id) {
    return Mono.just(repository.findById(id).orElse(PriceEntity.builder().build()))
        .map(PriceMapper.toDomain);
  }

  @Override
  public Mono<Long> selectCount() {
    return Mono.just(repository.count());
  }

  @Override
  public Flux<Price> getAllPricesByProductId(Long productId) {
    return Flux.fromIterable(repository.findAllByProductId(productId)).map(PriceMapper.toDomain);
  }
}
