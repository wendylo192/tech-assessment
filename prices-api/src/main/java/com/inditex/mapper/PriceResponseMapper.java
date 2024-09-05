package com.inditex.mapper;

import org.springframework.stereotype.Component;

import com.inditex.api.dto.PriceResponse;
import com.inditex.domain.model.Price;

@Component
public class PriceResponseMapper {

    public PriceResponse toPriceResponse(Price price) {
        return new PriceResponse(
            price.getProductId(),
            price.getBrandId(),
            price.getPriceList(),	
            price.getStartDate(),
            price.getEndDate(),
            price.getUnitPrice()
        );
    }
}