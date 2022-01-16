package com.latitude.genoapay.codingchallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.latitude.genoapay.codingchallenge.model.GetMaxProfitRequest;
import com.latitude.genoapay.codingchallenge.model.StockResult;
import com.latitude.genoapay.codingchallenge.service.StockService;
import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author ritesh
 */
@ExtendWith(MockitoExtension.class)
class StockControllerTest {

    @Mock
    StockService stockService;

    @InjectMocks
    StockController stockController;

    MockMvc mockMvc;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    void getMaxProfitTest() throws Exception {
        //given
        List<Integer> stockPriceList = Arrays.asList(7, 15, 56, 3, 6, 8, 22);
        OffsetDateTime startDate = OffsetDateTime
                .now()
                .minusDays(1)
                .withHour(10)
                .withMinute(00)
                .withSecond(00);
        OffsetDateTime endDate = OffsetDateTime
                .now()
                .minusDays(1)
                .withHour(17)
                .withMinute(00)
                .withSecond(00);
        GetMaxProfitRequest getMaxProfitRequest = new GetMaxProfitRequest()
                .stockPrices(stockPriceList)
                .startDate(startDate)
                .endDate(endDate)
                .name("test stock");
        StockResult stockResult = new StockResult("test stock", LocalDateTime.now(), 10, new Pair<>(0, 7), new Pair<>(2, 56));
        given(stockService.getMaxProfitStock(any())).willReturn(stockResult);
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/stock/getmaxprofit")
                        .content(this.mapper.writeValueAsString(getMaxProfitRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.stockRequest.name", is("test stock")))
                .andExpect(jsonPath("$.maxProfit", is(10)));
    }

    @Test
    void getMaxProfit_withNull_StockPrice_BadRequestTest() throws Exception {
        //given
        OffsetDateTime startDate = OffsetDateTime
                .now()
                .minusDays(1)
                .withHour(10)
                .withMinute(00)
                .withSecond(00);
        OffsetDateTime endDate = OffsetDateTime
                .now()
                .minusDays(1)
                .withHour(17)
                .withMinute(00)
                .withSecond(00);
        GetMaxProfitRequest getMaxProfitRequest = new GetMaxProfitRequest()
                .stockPrices(null)
                .startDate(startDate)
                .endDate(endDate)
                .name("test stock");
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/stock/getmaxprofit")
                        .content(this.mapper.writeValueAsString(getMaxProfitRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}