package com.ecommerce.pricing.infrastructure.adapter.in.mapper;

import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.adapter.in.dto.PriceResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Function;

@FunctionalInterface
public interface PriceMapper {

  void test();

  Function<Price, PriceResponse> toApi =
      (Price domain) -> {
        PriceResponse response = new PriceResponse();
        response.setPrice(domain.getPrice().floatValue());
        response.setPriceList(domain.getPriceList());
        response.setBrandId(domain.getBrandId());
        response.setProductId(domain.getProductId().intValue());
        response.setAppDate(LocalDateTime.now().atOffset(ZoneOffset.UTC));
        response.setCurrency(domain.getCurr());
        return response;
      };
}
