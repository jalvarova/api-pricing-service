package com.ecommerce.pricing.infrastructure.adapter.out.mapper;


import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.out.entity.PriceEntity;
import java.util.function.Function;

@FunctionalInterface
public interface PriceEntityMapper {

  void test();

  Function<PriceEntity, Price> toDomain =
      (PriceEntity entity) ->
          Price.builder()
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
