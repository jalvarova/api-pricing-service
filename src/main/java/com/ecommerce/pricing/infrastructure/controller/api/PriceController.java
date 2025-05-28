package com.ecommerce.pricing.infrastructure.controller.api;

import com.ecommerce.pricing.application.service.PriceServiceAdapter;
import com.ecommerce.pricing.domain.model.PriceRequest;
import com.ecommerce.pricing.domain.model.PriceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Validated
@RestController
@AllArgsConstructor
public class PriceController implements PricingControllerApi {

  private PriceServiceAdapter adapter;

  @Override
  public Mono<ResponseEntity<PriceResponse>> getPriceForIdentifier(Integer id,
      ServerWebExchange exchange) {
    return Mono.just(id)
        .flatMap(integer -> adapter.getPriceForIdentifier(Long.valueOf(integer)))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<PriceResponse>>> getPriceForProduct(Integer productId,
      ServerWebExchange exchange) {
    return Mono.just(adapter.getAllPriceByProduct(Long.valueOf(productId)))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<PriceResponse>> getFinalPrice(Mono<PriceRequest> priceRequest,
      ServerWebExchange exchange) {
    return priceRequest
        .map(request -> adapter.getPriceProduct(Long.valueOf(request.getProductId()),
            request.getBrandId(), request.getApplicationDate().toLocalDateTime()))
        .flatMap(priceResponseMono -> priceResponseMono)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
