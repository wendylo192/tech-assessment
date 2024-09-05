package com.inditex.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inditex.domain.model.Price;
import com.inditex.infrastructure.entity.PriceEntity;

class PriceMapperImplTest {

	private PriceMapperImpl priceMapper;

	@BeforeEach
	void setUp() {
		priceMapper = new PriceMapperImpl();
	}

	@Test
	void testToEntity() {
		// Given
		Price price = new Price.Builder()
				.id(1L)
				.brandId(1L)
				.startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
				.endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
				.priceList(1)
				.productId(35455L)
				.priority(0)
				.unitPrice(BigDecimal.valueOf(35.50))
				.currency("EUR")
				.build();

		// When
		PriceEntity priceEntity = priceMapper.toEntity(price);

		// Then
		assertEquals(1L, priceEntity.getId());
		assertEquals(1L, priceEntity.getBrandId());
		assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0), priceEntity.getStartDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59), priceEntity.getEndDate());
		assertEquals(1, priceEntity.getPriceList());
		assertEquals(35455L, priceEntity.getProductId());
		assertEquals(0, priceEntity.getPriority());
		assertEquals(BigDecimal.valueOf(35.50), priceEntity.getPrice());
		assertEquals("EUR", priceEntity.getCurrency());
	}

	@Test
	void testToDomain() {
		// Given
		PriceEntity priceEntity = new PriceEntity.Builder()
				.id(1L)
				.brandId(1L)
				.startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
				.endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
				.priceList(1)
				.productId(35455L)
				.priority(0)
				.price(BigDecimal.valueOf(35.50))
				.currency("EUR")
				.build();

		// When
		Price price = priceMapper.toDomain(priceEntity);

		// Then
		assertEquals(1L, price.getId());
		assertEquals(1L, price.getBrandId());
		assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0), price.getStartDate());
		assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59), price.getEndDate());
		assertEquals(1, price.getPriceList());
		assertEquals(35455L, price.getProductId());
		assertEquals(0, price.getPriority());
		assertEquals(BigDecimal.valueOf(35.50), price.getUnitPrice());
		assertEquals("EUR", price.getCurrency());
	}
}