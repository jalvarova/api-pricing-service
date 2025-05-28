package com.ecommerce.pricing.infrastructure.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class PriceController implements PricingControllerApi{

}
