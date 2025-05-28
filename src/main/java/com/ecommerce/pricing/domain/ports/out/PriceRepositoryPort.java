package com.ecommerce.pricing.domain.ports.out;

import com.ecommerce.pricing.domain.model.Price;
import java.time.LocalDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PriceRepositoryPort {

  /**
   * Obetner el precio de un producto por determinada fecha y validar su prioridad.
   *
   * @param productId
   * @param brandId
   * @param date
   * @return Price
   */
  Flux<Price> getPricesByDate(Long productId, Integer brandId, LocalDateTime date);

  /**
   *
   * Obtener el precip por su identificador.
   *
   * @param id
   * @return Price
   */
  Mono<Price> getPriceById(Long id);

  /**
   *
   * Obtener el nuemero de registros.
   *
   * @return Long
   */
  Mono<Long> selectCount();

  /**
   * Obtener los precios de un producto.
   *
   * @param productId
   * @return ListPrices
   */
  Flux<Price> getAllPricesByProductId(Long productId);
}
