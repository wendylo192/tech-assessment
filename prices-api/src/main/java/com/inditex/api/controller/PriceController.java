package com.inditex.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.api.dto.PriceRequest;
import com.inditex.api.dto.PriceResponse;
import com.inditex.application.port.in.GetPriceForApplicationQuery;
import com.inditex.domain.model.Price;
import com.inditex.domain.model.PriceErrorResponse;
import com.inditex.mapper.PriceResponseMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/prices")
@Tag(name = "Prices")
public class PriceController {

    private final GetPriceForApplicationQuery getPriceForApplicationQuery;
    private final PriceResponseMapper priceResponseMapper;

    public PriceController(GetPriceForApplicationQuery getPriceForApplicationQuery, PriceResponseMapper priceResponseMapper) {
        this.getPriceForApplicationQuery = getPriceForApplicationQuery;
        this.priceResponseMapper = priceResponseMapper;
    }

	@Operation(
			description = "Gives a final price based on a brand, a product and an application date"
	)
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok", content = 
	          { @Content(mediaType = "application/json", schema = 
	            @Schema(implementation = PriceResponse.class)) }),
	        @ApiResponse(responseCode = "400", description = "Invalid request body supplied", content =
	        	 { @Content(mediaType = "application/json", schema = 
		            @Schema(implementation = PriceErrorResponse.class)) }), 
	        @ApiResponse(responseCode = "404", description = "Price not found", content =
        	 { @Content(mediaType = "application/json", schema = 
	            @Schema(implementation = PriceErrorResponse.class)) }),
	        @ApiResponse(responseCode = "500", description = "Internal server error", content = 
	          { @Content(mediaType = "application/json", schema = 
	            @Schema(implementation = PriceErrorResponse.class)) }) })
	@PostMapping("${price.endpoint}")
	public ResponseEntity<PriceResponse> getPriceForApplication(@RequestBody PriceRequest request) {
	    Price price = getPriceForApplicationQuery.getPriceForApplication(
	        request.getProductId(), request.getBrandId(), request.getApplicationDate()
	    );

	    PriceResponse response = priceResponseMapper.toPriceResponse(price);
	    return ResponseEntity.ok(response);
	}

}