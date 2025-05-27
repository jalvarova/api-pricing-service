package com.ecommerce.pricing.domain.mappers;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.db.entity.PriceEntity;
import java.util.function.Function;

@FunctionalInterface
public interface PriceMapper {

  void test();

  Function<PriceEntity, Price> toDomain =
      (PriceEntity entity) ->
          Price.builder()
              .price(entity.getPrice())
              .priceList(entity.getPriceList())
              .brandId(entity.getBrandId())
              .productId(entity.getProductId())
              .startDate(entity.getStartDate())
              .endDate(entity.getEndDate())
              .build();
}
