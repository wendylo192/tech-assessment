package com.inditex.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inditex.infrastructure.entity.PriceEntity;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {
}
