package com.ecommerce.pricing.domain.ports.out;

import com.ecommerce.pricing.domain.model.Price;
import java.time.LocalDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PriceRepositoryPort {

  Flux<Price> getPricesByDate(Long productId, Integer brandId, LocalDateTime date);

  Mono<Price> getPriceById(Long id);

  Mono<Long> selectCount();

  Flux<Price> getAllPricesByProductId(Long productId);

  Mono<Price> save(Price price);

  Mono<Price> update(Price price);

  Mono<Price> deleteById(Long id);
}
