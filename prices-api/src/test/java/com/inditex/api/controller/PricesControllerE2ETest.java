package com.inditex.api.controller;

import static com.inditex.prices.utils.ErrorCatalog.INVALID_REQUEST_INPUT;
import static com.inditex.prices.utils.ErrorCatalog.PRICE_NOT_FOUND;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.inditex.api.dto.PriceRequest;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class PricesControllerE2ETest {

    @LocalServerPort
    private int port;
    private static final String GET_PRICE_FOR_APPLICATION_URL = "/prices/v1/api/price";
    
    private final Long productId = 35455L;
    private final Long brandId = 1L;
    private final Integer priceList = 1;
    private final LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
    private final String startDate = "2020-06-14T00:00:00";
    private final String endDate = "2020-12-31T23:59:59";
    private final BigDecimal priceAmount = BigDecimal.valueOf(35.50);
    
    private final PriceRequest request;
    
    PricesControllerE2ETest() {
        
        this.request = new PriceRequest();
        request.setProductId(productId);
        request.setBrandId(brandId);
        request.setApplicationDate(applicationDate);
    }
    
    @BeforeEach
    void setUp() {
    	RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "http://localhost:" + port;    	
    }

    @Test
    void testGetPriceForApplication_Success() {
    	System.out.println("Sending request to: " + RestAssured.baseURI + "/prices/v1/api/price");
        System.out.println("Request Body: " + request);
        
        given()
            .port(port)
            .contentType("application/json")
            .body(request)
        .when()
            .post(GET_PRICE_FOR_APPLICATION_URL)
        .then()
            .log().ifError()
            .statusCode(200)
            .body("productId", equalTo(productId.intValue()))
            .body("brandId", equalTo(brandId.intValue()))
            .body("priceList", equalTo(priceList))
            .body("startDate", equalTo(startDate))
            .body("endDate", equalTo(endDate))
            .body("price", equalTo(priceAmount.floatValue()));        
    }
    
    @Test
    void testGetPriceForNonExistingDate() {
        String productId = "35455";
        String brandId = "1";
        String applicationDate = "2024-01-10T10:00:00";

        given()
            .contentType("application/json")
            .body("{ \"productId\": " + productId + ", \"brandId\": " + brandId + ", \"applicationDate\": \"" + applicationDate + "\" }")
        .when()
            .post(GET_PRICE_FOR_APPLICATION_URL)
        .then()
        	.log().ifError()
            .statusCode(404)
            .body("code", equalTo(PRICE_NOT_FOUND.getCode()))
            .body("message", equalTo(PRICE_NOT_FOUND.getMessage()));        
    }
    
    @Test
    void testGetPriceForMalformedRequestBody() {
        given()
            .contentType("application/json")
            /*falta una coma entre productId y brandId*/
            .body("{ \"productId\": " + productId + " \"brandId\": " + brandId + ", \"applicationDate\": \"" + applicationDate + "\" }")
        .when()
            .post(GET_PRICE_FOR_APPLICATION_URL)
        .then()
        	.log().ifError()
            .statusCode(400)
            .body("code", equalTo(INVALID_REQUEST_INPUT.getCode()))
            .body("message", equalTo(INVALID_REQUEST_INPUT.getMessage()));        
    }

}