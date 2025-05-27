package com.ecommerce.pricing.infrastructure.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "prices")
@Builder
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "brand_id", nullable = false)
  private Integer brandId;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "price_list", nullable = false)
  private Integer priceList;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(nullable = false)
  private Integer priority;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(length = 3, nullable = false)
  private String curr;
}
