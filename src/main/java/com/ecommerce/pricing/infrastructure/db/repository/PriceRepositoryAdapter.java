package com.ecommerce.pricing.infrastructure.db.repository;

import com.ecommerce.pricing.domain.mappers.PriceMapper;
import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.ports.out.PriceRepositoryPort;
import java.time.LocalDateTime;
import java.util.List;
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
    return Flux.fromIterable(List.of(repository.findApplicablePrice(productId, brandId, date)))
        .map(PriceMapper.toDomain);
  }

  @Override
  public Mono<Price> getPriceById(Long id) {
    return Mono.just(repository.findPriceById(id))
        .map(PriceMapper.toDomain);
  }

  @Override
  public Mono<Long> selectCount() {
    return Mono.just(repository.count());
  }

  @Override
  public Flux<Price> getAllPricesByProductId(Long productId) {
    return Flux.fromIterable(repository.findPrecesByProductId(productId)).map(PriceMapper.toDomain);
  }
}
