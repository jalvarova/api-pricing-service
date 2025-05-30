package com.ecommerce.pricing.domain.ports.in;

import com.ecommerce.pricing.domain.model.Price;
import reactor.core.publisher.Flux;

/**
 * Caso de uso para recuperar todos los precios asociados a un producto específico.
 */

public interface GetAllPricesByProductIdUseCase {

  /**
   * Recupera todos los precios asociados al producto identificado por su ID.
   *
   * @param productId el identificador único del producto
   * @return un flujo reactivo con los precios correspondientes al producto
   */
  Flux<Price> execute(Integer productId);

}
