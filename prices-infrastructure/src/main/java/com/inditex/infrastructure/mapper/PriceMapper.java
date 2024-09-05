package com.inditex.infrastructure.mapper;

import com.inditex.domain.model.Price;
import com.inditex.infrastructure.entity.PriceEntity;

public interface PriceMapper {

    PriceEntity toEntity(Price price);

    Price toDomain(PriceEntity priceEntity);
}