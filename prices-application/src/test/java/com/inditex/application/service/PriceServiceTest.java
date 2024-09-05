package com.inditex.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inditex.application.port.out.PriceRepositoryPort;
import com.inditex.domain.exception.PriceNotFoundException;
import com.inditex.domain.model.Price;

class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    private final long productId = 35455L;
    private final long brandId = 1L;
    private final LocalDateTime applicationDate = LocalDateTime.of(2024, 6, 14, 10, 0, 0);
    private Price price3550;
    private Price price2545;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        price3550 = new Price.Builder()
                .id(1L)
                .brandId(brandId)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .productId(productId)
                .priority(0)
                .unitPrice(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();

        price2545 = new Price.Builder()
                .id(2L)
                .brandId(brandId)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
                .priceList(2)
                .productId(productId)
                .priority(1)
                .unitPrice(BigDecimal.valueOf(25.45))
                .currency("EUR")
                .build();
    }


    @Test
    void testGetPriceForApplication_whenPriceExists() {
        when(priceRepositoryPort.findApplicablePrice(productId, brandId, applicationDate))
            .thenReturn(Optional.of(price3550));

        Price result = priceService.getPriceForApplication(productId, brandId, applicationDate);

        assertNotNull(result);
        assertEquals(price3550, result);
        verify(priceRepositoryPort).findApplicablePrice(productId, brandId, applicationDate);
    }

    @Test
    void testGetPriceForApplication_whenPriceDoesNotExist() {
        when(priceRepositoryPort.findApplicablePrice(productId, brandId, applicationDate))
            .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () ->
            priceService.getPriceForApplication(productId, brandId, applicationDate)
        );

        verify(priceRepositoryPort).findApplicablePrice(productId, brandId, applicationDate);
    }
}
