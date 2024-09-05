package com.inditex.api.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.api.dto.PriceRequest;
import com.inditex.api.dto.PriceResponse;
import com.inditex.application.port.in.GetPriceForApplicationQuery;
import com.inditex.domain.model.Price;
import com.inditex.mapper.PriceResponseMapper;

@WebMvcTest(PriceController.class)
class PriceControllerContractTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetPriceForApplicationQuery getPriceForApplicationQuery;

    @MockBean
    private PriceResponseMapper priceResponseMapper;

    private final long productId = 35455L;
    private final long brandId = 1L;
    private final int priceList = 1;
    private final LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
    private final LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
    private final BigDecimal priceAmount = BigDecimal.valueOf(35.50);
    private final Price mockPrice;
    private final PriceResponse mockResponse;

    PriceControllerContractTest() {
        this.mockPrice = new Price.Builder()
            .id(1L)
            .brandId(brandId)
            .startDate(startDate)
            .endDate(endDate)
            .priceList(1)
            .productId(productId)
            .priority(0)
            .unitPrice(priceAmount)
            .currency("EUR")
            .build();  

        this.mockResponse = new PriceResponse(productId, brandId, priceList, startDate, endDate, priceAmount);
    }

    @BeforeEach
    void setup() {
        when(getPriceForApplicationQuery.getPriceForApplication(any(Long.class), any(Long.class), any(LocalDateTime.class)))
            .thenReturn(mockPrice);

        when(priceResponseMapper.toPriceResponse(mockPrice))
            .thenReturn(mockResponse);
    }

    @Test
    void testGetPriceForApplication() throws Exception {
        PriceRequest request = new PriceRequest();
        request.setProductId(productId);
        request.setBrandId(brandId);
        request.setApplicationDate(LocalDateTime.of(2020, 6, 14, 10, 0));       
        
        mockMvc.perform(post("/prices/v1/api/price")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId", is((int) brandId)))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.productId", is((int) productId)))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate", is(endDate.toString())))
                .andExpect(jsonPath("$.price", is(priceAmount.doubleValue())));
    }
}
