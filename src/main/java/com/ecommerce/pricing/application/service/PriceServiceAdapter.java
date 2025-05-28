package com.ecommerce.pricing.application.service;

import com.ecommerce.pricing.domain.model.PriceResponse;
import java.time.LocalDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PriceServiceAdapter {

  Flux<PriceResponse> getAllPriceByProduct(Long productId);

  Mono<PriceResponse> getPriceForIdentifier(Long id);

  Mono<PriceResponse> getPriceProduct(Long productId, Integer brandId, LocalDateTime date);
}
