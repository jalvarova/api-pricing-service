package com.ecommerce.pricing.domain.ports.in;

import com.ecommerce.pricing.domain.model.Price;
import java.time.LocalDateTime;
import reactor.core.publisher.Mono;

/**
 * Caso de uso para obtener el precio aplicable de un producto en una fecha específica,
 * considerando la prioridad entre posibles precios coincidentes.
 */
public interface GetApplicablePriceUseCase {

  /**
   * Busca el precio aplicable para un producto, una marca y una fecha determinada.
   * En caso de múltiples precios disponibles para el mismo intervalo, se selecciona el de mayor prioridad.
   *
   * @param productId       identificador del producto
   * @param brandId         identificador de la marca
   * @param applicationDate fecha y hora de aplicación
   * @return Mono que contiene el precio aplicable, o vacío si no se encuentra
   */
  Mono<Price> getApplicablePrice(Long productId, Integer brandId, LocalDateTime applicationDate);

}
