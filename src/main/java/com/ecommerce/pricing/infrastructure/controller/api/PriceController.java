package com.ecommerce.pricing.infrastructure.controller.api;

import com.ecommerce.pricing.application.service.PriceServiceAdapter;
import com.ecommerce.pricing.domain.model.PriceResponse;
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
public class PriceController implements PricingControllerApi {

  private PriceServiceAdapter adapter;

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
    return Mono.just(adapter.getAllPriceByProduct(Long.valueOf(productId)))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<PriceResponse>> getApplicablePrice(
      @Valid @RequestParam(value = "brandId") Integer brandId,
      @Valid @RequestParam(value = "productId") Long productId,
      @Valid @RequestParam(value = "applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime applicationDate,
      @Parameter(hidden = true) final ServerWebExchange exchange) {
    return adapter.getApplicablePrice(productId, brandId, applicationDate.toLocalDateTime())
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
