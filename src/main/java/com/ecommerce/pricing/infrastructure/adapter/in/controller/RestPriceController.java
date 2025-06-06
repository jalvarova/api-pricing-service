package com.ecommerce.pricing.infrastructure.adapter.in.controller;

import com.ecommerce.pricing.application.service.PriceQueryFacade;
import com.ecommerce.pricing.infrastructure.adapter.in.dto.PriceResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Validated
@RestController
@AllArgsConstructor
public class RestPriceController implements PricingControllerApi {

  private PriceQueryFacade adapter;

  @Override
  public Mono<ResponseEntity<PriceResponse>> getPriceById(Integer id,
      ServerWebExchange exchange) {
    return Mono.just(id)
        .flatMap(integer -> adapter.getPriceForIdentifier(Long.valueOf(integer)))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Flux<PriceResponse>>> getAllPricesByProductId(Integer productId,
      ServerWebExchange exchange) {
    return Mono.just(adapter.getAllPriceByProduct(productId))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<PriceResponse>> getApplicablePrice(
      @Valid @RequestParam(value = "brandId") Integer brandId,
      @Valid @RequestParam(value = "productId") Integer productId,
      @Valid @RequestParam(value = "applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime applicationDate,
      @Parameter(hidden = true) final ServerWebExchange exchange) {
    return adapter.getApplicablePrice(productId, brandId, applicationDate.toLocalDateTime())
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
