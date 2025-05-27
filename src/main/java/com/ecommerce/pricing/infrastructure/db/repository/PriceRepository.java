package com.ecommerce.pricing.infrastructure.db.repository;

import com.ecommerce.pricing.infrastructure.db.entity.PriceEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends CrudRepository<PriceEntity, Long> {

  @Query(
      """
              SELECT p FROM PriceEntity p
              WHERE p.productId = :productId
               AND p.brandId = :brandId
               AND :applicationDate BETWEEN p.startDate AND p.endDate
             ORDER BY p.priority DESC
          """)
  PriceEntity findTopByProductBrandAndDate(
      @Param("productId") Long productId,
      @Param("brandId") Integer brandId,
      @Param("applicationDate") LocalDateTime applicationDate);
}
