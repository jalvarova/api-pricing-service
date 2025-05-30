package com.ecommerce.pricing.infrastructure.adapter.out.repository;

import com.ecommerce.pricing.infrastructure.adapter.out.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
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
             LIMIT 1
          """)
  PriceEntity findApplicablePrice(
      @Param("productId") Long productId,
      @Param("brandId") Integer brandId,
      @Param("applicationDate") LocalDateTime applicationDate);

  @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId ORDER BY p.startDate DESC")
  List<PriceEntity> findPrecesByProductId(@Param("productId") Long productId);

  @Query("SELECT p FROM PriceEntity p WHERE p.id = :id")
  PriceEntity findPriceById(@Param("id") Long id);
}
