package com.ecommerce.pricing.domain.ports.in;

import com.ecommerce.pricing.domain.model.Price;
import java.time.LocalDateTime;
import reactor.core.publisher.Mono;

public interface GetPriceUseCase {

  /**
   * Obetner el precio de un producto por determinada fecha y validar su prioridad.
   *
   * @param productId
   * @param brandId
   * @param date
   * @return Price
   */
  Mono<Price> getPriceProduct(Long productId, Integer brandId, LocalDateTime date);

}
