package com.ecommerce.pricing.application.service;

import com.ecommerce.pricing.infrastructure.adapter.in.dto.PriceResponse;
import java.time.LocalDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PriceQueryFacade {

  Flux<PriceResponse> getAllPriceByProduct(Integer productId);

  Mono<PriceResponse> getPriceForIdentifier(Long id);

  Mono<PriceResponse> getApplicablePrice(Integer productId, Integer brandId, LocalDateTime date);
}
