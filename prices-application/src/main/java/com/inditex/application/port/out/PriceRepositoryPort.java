package com.inditex.application.port.out;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.domain.model.Price;

public interface PriceRepositoryPort {
    Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
