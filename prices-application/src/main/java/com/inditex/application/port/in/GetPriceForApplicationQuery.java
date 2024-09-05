package com.inditex.application.port.in;

import java.time.LocalDateTime;

import com.inditex.domain.model.Price;

public interface GetPriceForApplicationQuery {
    Price getPriceForApplication(Long productId, Long brandId, LocalDateTime applicationDate);
}