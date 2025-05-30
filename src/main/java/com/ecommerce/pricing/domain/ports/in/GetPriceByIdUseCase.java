package com.ecommerce.pricing.domain.ports.in;

import com.ecommerce.pricing.domain.model.Price;
import reactor.core.publisher.Mono;


/**
 * Caso de uso para obtener un precio por su identificador único.
 */
public interface GetPriceByIdUseCase {

  /**
   * Recupera un precio específico utilizando su ID.
   *
   * @param priceId el identificador único del precio
   * @return un Mono que contiene el precio si existe, o vacío si no se encuentra
   */
  Mono<Price> getPriceForId(Long priceId);
}
