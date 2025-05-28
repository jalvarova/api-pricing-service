package com.ecommerce.pricing.domain.mappers;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.model.PriceResponse;
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
              .priority(entity.getPriority())
              .endDate(entity.getEndDate())
              .curr(entity.getCurr())
              .build();

  Function<Price, PriceResponse> toApi =
      (Price domain) -> {
        PriceResponse response = new PriceResponse();
        response.setPrice(domain.getPrice().floatValue());
        response.setPriceList(domain.getPriceList());
        response.setBrandId(domain.getBrandId());
        response.setProductId(domain.getProductId().intValue());
        response.setCurrency(domain.getCurr());
        return response;
      };
}
