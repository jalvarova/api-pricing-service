package com.ecommerce.pricing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Price {

  private Integer brandId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Integer priceList;

  private Long productId;

  private Integer priority;

  private BigDecimal price;

  private String curr;
}

