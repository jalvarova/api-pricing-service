package com.ecommerce.pricing.domain.ports.in;

import com.ecommerce.pricing.domain.model.Price;
import reactor.core.publisher.Flux;

public interface GetAllPriceForProductUseCase {

  /**
   *Obtener los precios de un producto.
   *
   * @param productId
   * @return ListPrices
   */
  Flux<Price> getAllPriceByProduct(Long productId);

}
