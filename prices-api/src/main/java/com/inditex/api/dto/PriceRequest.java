package com.inditex.api.dto;

import java.time.LocalDateTime;

public class PriceRequest {
    private Long productId;
    private Long brandId;
    private LocalDateTime applicationDate;

    public PriceRequest() {}

    public PriceRequest(Long productId, Long brandId, LocalDateTime applicationDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.applicationDate = applicationDate;
    }

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Override
	public String toString() {
		return "PriceRequest [productId=" + productId + ", brandId=" + brandId + ", applicationDate=" + applicationDate
				+ "]";
	}
    
    
}
