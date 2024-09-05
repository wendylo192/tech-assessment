package com.inditex.infrastructure.mapper;

import com.inditex.domain.model.Price;
import com.inditex.infrastructure.entity.PriceEntity;

public class PriceMapperImpl implements PriceMapper {

	 @Override
	    public PriceEntity toEntity(Price price) {
	        if (price == null) {
	            return null;
	        }

	        return new PriceEntity.Builder()
	                .id(price.getId())
	                .brandId(price.getBrandId())
	                .startDate(price.getStartDate())
	                .endDate(price.getEndDate())
	                .priceList(price.getPriceList())
	                .productId(price.getProductId())
	                .priority(price.getPriority())
	                .price(price.getUnitPrice())
	                .currency(price.getCurrency())
	                .build();
	    }

	    @Override
	    public Price toDomain(PriceEntity priceEntity) {
	        if (priceEntity == null) {
	            return null;
	        }

	        return new Price.Builder()
	                .id(priceEntity.getId())
	                .brandId(priceEntity.getBrandId())
	                .startDate(priceEntity.getStartDate())
	                .endDate(priceEntity.getEndDate())
	                .priceList(priceEntity.getPriceList())
	                .productId(priceEntity.getProductId())
	                .priority(priceEntity.getPriority())
	                .unitPrice(priceEntity.getPrice())
	                .currency(priceEntity.getCurrency())
	                .build();
	    }
}
