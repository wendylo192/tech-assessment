package com.inditex.prices.utils;

public enum ErrorCatalog {
	
	
	PRICE_NOT_FOUND("ERR_PRICES_001", "Price not found."),
	INVALID_SEARCH_INPUT("ERR_PRICES_002", "Invalid search parameters."),
	INVALID_REQUEST_INPUT("ERR_PRICES_003", "The request body is invalid or malformed."),
	GENERIC_ERROR("ERR_GENERIC_001", "An unexpected error occurred.");
	
	private final String code;
	private final String message;
	
	ErrorCatalog(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

}
