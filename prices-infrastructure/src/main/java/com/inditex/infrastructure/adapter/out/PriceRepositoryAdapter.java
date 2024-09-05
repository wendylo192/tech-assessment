package com.inditex.infrastructure.adapter.out;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.inditex.application.port.out.PriceRepositoryPort;
import com.inditex.domain.constants.PriceConstants;
import com.inditex.domain.model.Price;
import com.inditex.infrastructure.entity.PriceEntity;
import com.inditex.infrastructure.mapper.PriceMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;
	
    private final PriceMapper priceMapper;

    public PriceRepositoryAdapter(PriceMapper priceMapper) {
        this.priceMapper = priceMapper;
    }
    
    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime fechaAplicacion) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PriceEntity> cq = cb.createQuery(PriceEntity.class);
        Root<PriceEntity> price = cq.from(PriceEntity.class);

        Predicate productIdPredicate = cb.equal(price.get(PriceConstants.PRODUCT_ID), productId);
        Predicate brandIdPredicate = cb.equal(price.get(PriceConstants.BRAND_ID), brandId);
        Predicate datePredicate = cb.between(cb.literal(fechaAplicacion), price.get(PriceConstants.START_DATE), price.get(PriceConstants.END_DATE));

        cq.where(cb.and(productIdPredicate, brandIdPredicate, datePredicate));
        cq.orderBy(cb.desc(price.get(PriceConstants.PRIORITY)));

        TypedQuery<PriceEntity> query = entityManager.createQuery(cq);
        query.setMaxResults(1);

        return query.getResultStream()
                .findFirst()
                .map(priceMapper::toDomain);
    }

}