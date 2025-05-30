package com.ecommerce.pricing.util;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.out.entity.PriceEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Data;

@Data
public class BuilderObjectMocks {

  private PriceEntity priceEntity1, priceEntity2, priceEntity3, priceEntity4, priceEntity5;

  public final static String CURRENCY_CODE = "EUR";

  public BuilderObjectMocks() {
    priceEntity1 = PriceEntity
        .builder()
        .id(1L)
        .price(BigDecimal.valueOf(35.50))
        .brandId(1)
        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
        .priority(0)
        .productId(35455)
        .priceList(1)
        .curr(CURRENCY_CODE)
        .build();

    priceEntity2 = PriceEntity
        .builder()
        .id(2L)
        .price(BigDecimal.valueOf(35.50))
        .brandId(1)
        .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
        .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
        .priority(0)
        .productId(35455)
        .priceList(1)
        .curr(CURRENCY_CODE)
        .build();

    priceEntity3 = PriceEntity
        .builder()
        .id(3L)
        .price(BigDecimal.valueOf(35.50))
        .brandId(1)
        .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
        .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
        .priority(0)
        .productId(35455)
        .priceList(1)
        .curr(CURRENCY_CODE)
        .build();

    priceEntity4 = PriceEntity
        .builder()
        .id(4L)
        .price(BigDecimal.valueOf(35.50))
        .brandId(1)
        .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
        .priority(0)
        .productId(35455)
        .priceList(1)
        .curr(CURRENCY_CODE)
        .build();

    priceEntity5 = PriceEntity
        .builder()
        .id(4L)
        .price(BigDecimal.valueOf(80.50))
        .brandId(1)
        .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
        .priority(0)
        .productId(35456)
        .priceList(1)
        .curr(CURRENCY_CODE)
        .build();

  }

  public List<PriceEntity> getListPricesByProduct() {
    return Arrays.asList(this.priceEntity1, priceEntity2, priceEntity3, priceEntity4);
  }

  public List<PriceEntity> getAllPricesByProduct2() {
    return Collections.singletonList(priceEntity5);
  }

  public List<PriceEntity> getListPricesRangeDate() {
    return Arrays.asList(priceEntity1, priceEntity2);
  }

  public List<Price> getPricesByDate() {
    return Arrays.asList(buildDomain(priceEntity1), buildDomain(priceEntity2));
  }

  public List<Price> getListDomainPricesByProduct() {
    return Arrays.asList(buildDomain(priceEntity1), buildDomain(priceEntity2),
        buildDomain(priceEntity3), buildDomain(priceEntity4));
  }
  public Price buildDomain(PriceEntity entity) {
    return Price.builder()
        .price(entity.getPrice())
        .priceList(entity.getPriceList())
        .brandId(entity.getBrandId())
        .productId(entity.getProductId())
        .startDate(entity.getStartDate())
        .priority(entity.getPriority())
        .endDate(entity.getEndDate())
        .curr(entity.getCurr())
        .build();
  }
}
