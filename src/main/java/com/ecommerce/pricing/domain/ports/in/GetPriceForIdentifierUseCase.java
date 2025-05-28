package com.ecommerce.pricing.domain.ports.in;

import com.ecommerce.pricing.domain.model.Price;
import reactor.core.publisher.Mono;

public interface GetPriceForIdentifierUseCase {

  /**
   *
   * Obtener el precip por su identificador.
   *
   * @param id
   * @return Price
   */
  Mono<Price> getPriceForIdentifier(Long id);
}
