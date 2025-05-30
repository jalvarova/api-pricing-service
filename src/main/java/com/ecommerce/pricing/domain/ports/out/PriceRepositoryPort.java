package com.ecommerce.pricing.domain.ports.out;

import com.ecommerce.pricing.domain.model.Price;
import java.time.LocalDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for querying product pricing information.
 */
public interface PriceRepositoryPort {

  /**
   * Retrieve the applicable price for a given product, brand, and date.
   * Applies priority rules to select the most relevant price.
   *
   * @param productId       the ID of the product
   * @param brandId         the ID of the brand
   * @param applicationDate the date for which the price is requested
   * @return a Mono of applicable prices, ordered by priority
   */
  Mono<Price> findApplicablePrices(Integer productId, Integer brandId, LocalDateTime applicationDate);

  /**
   * Retrieve a price by its unique identifier.
   *
   * @param id the ID of the price
   * @return a Mono containing the price if found
   */
  Mono<Price> findPriceById(Long id);

  /**
   * Count the total number of price records.
   *
   * @return a Mono with the total number of records
   */
  Mono<Long> countPrices();

  /**
   * Retrieve all prices associated with a given product ID.
   *
   * @param productId the ID of the product
   * @return a Flux containing all price entries for the specified product
   */
  Flux<Price> findAllPricesByProductId(Integer productId);
}
