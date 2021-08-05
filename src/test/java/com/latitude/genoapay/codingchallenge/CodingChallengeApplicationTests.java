package com.latitude.genoapay.codingchallenge;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.latitude.genoapay.codingchallenge.request.StockRequest;
import com.latitude.genoapay.codingchallenge.service.StockService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@AutoConfigureMockMvc
@SpringBootTest
public class CodingChallengeApplicationTests {

    private Logger logger = LogManager.getLogger(CodingChallengeApplicationTests.class);

    @Autowired
    StockService stockService;

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stockValues = "10, 7, 5, 8, 11, 9";
        StockRequest request = new StockRequest("latitude", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-04 10:06:00"), stockValues);

        logger.debug(objectMapper.writeValueAsString(request));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }

    @Test
    void testResponseContentType() throws Exception {
        String stockValues = "10, 7, 5, 8, 11, 9";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("latitude", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-04 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assert MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(response.getContentType());

    }

    @Test
        // Bad Request
    void whenMissingParameters_thenReturns400() throws Exception {
        String stockValues = "10, 7, 5, 8, 11, 9";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-04 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));

    }

    @Test
        //Outside of the stock value (mins - assuming 1 value per minute)
    void whenInvalidParameters_TimesOutOfRange_thenReturns400() throws Exception {
        String stockValues = "10, 7, 5, 8, 11, 9";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("", df.parse("2021-08-04 10:10:00"), df.parse("2021-08-04 10:20:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));
    }

    @Test
        // Equal start end times
    void whenInvalidParameters_EqualStartEndTime_thenReturns400() throws Exception {
        String stockValues = "10, 7, 5, 8, 11, 9";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-04 10:01:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));
    }

    @Test
        // Both start and end times are before 10:00 - the start of day
    void whenInvalidParameters_BothStartAndEndBefore10_thenReturns400() throws Exception {
        String stockValues = "10, 7, 5, 8, 11, 9";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("", df.parse("2021-08-04 09:01:00"), df.parse("2021-08-04 09:10:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));
    }


    @Test
        //End date is before start Date
    void whenInvalidParameters_EndDateBeforeStartDate_thenReturns400() throws Exception {
        String stockValues = "10, 7, 5, 8, 11, 9";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("", df.parse("2021-08-04 10:06:00"), df.parse("2021-08-04 10:01:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));
    }

    @Test
        // Atleast 2 stock values should be provided
    void whenInvalidParameters_OnlyOneStockValue_thenReturns400() throws Exception {
        String stockValues = "10";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-04 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));
    }

    @Test
        // All stock values are descending. In this case, NO profit can be made. Hence throwing an error
    void whenInvalidParameters_AllStockValueDesc_thenReturns400() throws Exception {
        String stockValues = "10";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-04 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));
    }


    @Test
    void whenValidInput_thenReturnsMaxProfit() throws Exception {
        String stockValues = "10, 7, 5, 8, 11, 9";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StockRequest request = new StockRequest("latitude", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-04 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/stock/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$['buyValue']", is(5)))
                .andExpect(jsonPath("$['sellValue']", is(11)))
                .andExpect(jsonPath("$['maxProfit']", is(6)));

    }

}
