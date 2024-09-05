package com.inditex.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inditex.application.port.in.GetPriceForApplicationQuery;
import com.inditex.application.port.out.PriceRepositoryPort;
import com.inditex.domain.exception.PriceNotFoundException;
import com.inditex.domain.model.Price;

@Service
public class PriceService implements GetPriceForApplicationQuery {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price getPriceForApplication(Long productId, Long brandId, LocalDateTime applicationDate) {
        Optional<Price> price = priceRepositoryPort.findApplicablePrice(productId, brandId, applicationDate);
        
        return price.orElseThrow(PriceNotFoundException::new);
    }
}