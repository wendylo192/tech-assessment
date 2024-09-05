package com.inditex.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.api.dto.PriceRequest;
import com.inditex.api.dto.PriceResponse;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final String GET_PRICE_FOR_APPLICATION_URL = "/prices/v1/api/price";

    @DisplayName("Test 1: Request a las 10:00 del 14/06 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testGetPriceForApplication_case1() throws Exception {
        PriceRequest request = new PriceRequest();
        request.setProductId(35455L);
        request.setBrandId(1L);
        request.setApplicationDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0));
        
        PriceResponse expectedResponse = new PriceResponse(35455L, 1L, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), new BigDecimal("35.50"));
        
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(post(GET_PRICE_FOR_APPLICATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
    
    @DisplayName("Test 2: Request a las 16:00 del 14/06 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testGetPriceForApplication_case2() throws Exception {
        PriceRequest request = new PriceRequest();
        request.setProductId(35455L);
        request.setBrandId(1L);
        request.setApplicationDate(LocalDateTime.of(2020, 6, 14, 16, 0, 0));
        
        PriceResponse expectedResponse = new PriceResponse(35455L, 1L, 2,
                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0), new BigDecimal("25.45"));
        
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(post(GET_PRICE_FOR_APPLICATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
    
    @DisplayName("Test 3: Request a las 21:00 del 14/06 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testGetPriceForApplication_case3() throws Exception {
        PriceRequest request = new PriceRequest();
        request.setProductId(35455L);
        request.setBrandId(1L);
        request.setApplicationDate(LocalDateTime.of(2020, 6, 14, 21, 0, 0));
        
        PriceResponse expectedResponse = new PriceResponse(35455L, 1L, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), new BigDecimal("35.50"));
        
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(post(GET_PRICE_FOR_APPLICATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
    
    @DisplayName("Test 4: Request a las 10:00 del 15/06 del producto 35455 para la brand 1 (ZARA")
    @Test
    void testGetPriceForApplication_case4() throws Exception {
        PriceRequest request = new PriceRequest();
        request.setProductId(35455L);
        request.setBrandId(1L);
        request.setApplicationDate(LocalDateTime.of(2020, 6, 15, 10, 0, 0));
        
        PriceResponse expectedResponse = new PriceResponse(35455L, 1L, 3,
                LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0, 0), new BigDecimal("30.50"));
        
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(post(GET_PRICE_FOR_APPLICATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
    
    @DisplayName("Test 5: Request a las 21:00 del 16/06 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testGetPriceForApplication_case5() throws Exception {
        PriceRequest request = new PriceRequest();
        request.setProductId(35455L);
        request.setBrandId(1L);
        request.setApplicationDate(LocalDateTime.of(2020, 6, 16, 21, 0, 0));
        
        PriceResponse expectedResponse = new PriceResponse(35455L, 1L, 4,
                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), new BigDecimal("38.95"));
        
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(post(GET_PRICE_FOR_APPLICATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

}