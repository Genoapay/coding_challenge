package com.latitude.genoapay.codingchallenge;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.latitude.genoapay.codingchallenge.service.StockService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

@AutoConfigureMockMvc
@SpringBootTest
public class CodingChallengeApplicationTests {

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
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] stockValues = new int[]{10, 7, 5, 8, 11, 9};
//        StockRequest request = new StockRequest("latitude", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-08 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
                .content(getRequestJsonString("latitude", "2021-08-04 10:01:00", "2021-08-04 10:06:00", stockValues))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }

    @Test
    void testResponseContentType() throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] stockValues = new int[]{10, 7, 5, 8, 11, 9};
//        StockRequest request = new StockRequest("latitude", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-08 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
                .content(getRequestJsonString("latitude", "2021-08-04 10:01:00", "2021-08-04 10:06:00", stockValues))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assert "application/json".equalsIgnoreCase(response.getContentType());

    }

    @Test
        // Bad Request
    void whenInvalidParameters_thenReturns400() throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] stockValues = new int[]{10, 7, 5, 8, 11, 9};
//        StockRequest request = new StockRequest("", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-08 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
                .content(getRequestJsonString("", "2021-08-04 10:01:00", "2021-08-04 10:06:00", stockValues))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("ERROR")));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

    }


    @Test
    void whenValidInput_thenMaxProfit() throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] stockValues = new int[]{10, 7, 5, 8, 11, 9};
//        StockRequest request = new StockRequest("latitude", df.parse("2021-08-04 10:01:00"), df.parse("2021-08-08 10:06:00"), stockValues);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/maxProfit")
                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
                .content(getRequestJsonString("latitude", "2021-08-04 10:01:00", "2021-08-04 10:06:00", stockValues))
                .contentType(MediaType.APPLICATION_JSON);

//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
//                .andExpect(content().mimeType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$['Buy value']", is(5)))
                .andExpect(jsonPath("$['Sell value']", is(11)))
                .andExpect(jsonPath("$['Max profit']", is(6)));

    }


    private String getRequestJsonString(String identifier, String startTime, String endTime, int[] stockValues) {
        JSONArray stockPrices = new JSONArray();
        for (int stockValue : stockValues) {
            stockPrices.add(stockValue);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Identifier", identifier);
        jsonObject.put("Start date time", startTime);
        jsonObject.put("End date time", endTime);
        jsonObject.put("Array of stock prices", stockPrices);
        System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

}
