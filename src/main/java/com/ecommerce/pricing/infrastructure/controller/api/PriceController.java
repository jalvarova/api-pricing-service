package com.ecommerce.pricing.infrastructure.controller.api;

import com.ecommerce.pricing.application.service.PriceServiceAdapter;
import com.ecommerce.pricing.domain.model.PriceRequest;
import com.ecommerce.pricing.domain.model.PriceResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Validated
@Tag(name = "PriceApi", description = "API de Consulta de Precios")
@RestController
@RequiredArgsConstructor
public class PriceController implements DefaultApi {

  @Autowired
  private PriceServiceAdapter adapter;

  @Override
  public Mono<ResponseEntity<PriceResponse>> pricesIdGet(Integer id,
      ServerWebExchange exchange) {
    return Mono.just(id)
        .flatMap(integer -> adapter.getPriceForIdentifier(Long.valueOf(integer)))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<PriceResponse>>> pricesProductIdProductGet(Integer productId,
      ServerWebExchange exchange) {
    return Mono.just(adapter.getAllPriceByProduct(Long.valueOf(productId)))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<PriceResponse>> pricesSearchPost(Mono<PriceRequest> priceRequest,
      ServerWebExchange exchange) {
    return priceRequest
        .map(request -> adapter.getPriceProduct(Long.valueOf(request.getProductId()),
            request.getBrandId(), request.getApplicationDate().toLocalDateTime()))
        .flatMap(priceResponseMono -> priceResponseMono)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
