package com.latitude.genoapay.codingchallenge.controller;

import com.google.gson.Gson;
import com.latitude.genoapay.codingchallenge.dto.ExceptionalResponse;
import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import com.latitude.genoapay.codingchallenge.dto.ProfitResponse;
import com.latitude.genoapay.codingchallenge.dto.adapter.LocalDateTimeAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(LocalDateTimeAdapter.DATETIME_FORMAT);

    @Test
    public void calculateMaxProfitNormalRoundTrip() throws Exception {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setIdentifier("123");
        LocalDateTime startTime = LocalDateTime.now().minusDays(1).withHour(11).withMinute(6);
        LocalDateTime endTime = LocalDateTime.now().minusDays(1).withHour(11).withMinute(11);
        profitRequest.setStartTime(startTime);
        profitRequest.setEndTime(endTime);
        Integer [] priceArr = { 10, 7, 5, 8, 11, 9 };
        profitRequest.setPriceList(Arrays.asList(priceArr));
        String jsonPayload = gson.toJson(profitRequest);
        MvcResult mvcResult = mockMvc.perform(post("/maxProfit").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload)).andExpect(status().isOk()).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        ProfitResponse profitResponse = gson.fromJson(responseBody, ProfitResponse.class);
        Assertions.assertNotNull(profitResponse);
        Assertions.assertNotNull(profitResponse.getProfitRequest());
        ProfitRequest request = profitResponse.getProfitRequest();
        Assertions.assertEquals("123", profitRequest.getIdentifier());
        Assertions.assertEquals(startTime.format(dateTimeFormatter), request.getStartTime().format(dateTimeFormatter));
        Assertions.assertEquals(endTime.format(dateTimeFormatter), request.getEndTime().format(dateTimeFormatter));
        Assertions.assertNotNull(request.getPriceList());
        Assertions.assertEquals("{10,7,5,8,11,9}"
                , String.join(",", request.getPriceList().stream()
                        .map(String::valueOf).collect(Collectors.joining(",", "{", "}"))));

        Assertions.assertEquals(6, profitResponse.getMaxProfit());
        Assertions.assertEquals(5, profitResponse.getBuyValue());
        Assertions.assertEquals(11, profitResponse.getSellValue());
    }

    @Test
    public void calculateMaxProfitWithInvalidPayload() throws Exception {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.now().minusDays(1).withHour(9).withMinute(6));
        profitRequest.setEndTime(LocalDateTime.now().minusDays(1).withHour(11).withMinute(12));
        Integer [] priceArr = { 1 };
        profitRequest.setPriceList(Arrays.asList(priceArr));
        String jsonPayload = gson.toJson(profitRequest);
        MvcResult mvcResult = mockMvc.perform(post("/maxProfit").contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)).andExpect(status().isBadRequest()).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        ExceptionalResponse exceptionalResponse = gson.fromJson(responseBody, ExceptionalResponse.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exceptionalResponse.getCode());
        Assertions.assertEquals("Invalid request body.", exceptionalResponse.getMessage());
    }
}
