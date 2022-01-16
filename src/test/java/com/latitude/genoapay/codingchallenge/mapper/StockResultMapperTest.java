package com.latitude.genoapay.codingchallenge.mapper;

import com.latitude.genoapay.codingchallenge.model.GetMaxProfitRequest;
import com.latitude.genoapay.codingchallenge.model.GetMaxProfitResponse;
import com.latitude.genoapay.codingchallenge.model.Stock;
import com.latitude.genoapay.codingchallenge.model.StockResult;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ritesh
 */
class StockResultMapperTest {

    @Test
    void mapLocalDateTimeToOffsetDateTimeTest() {
        //given
        LocalDateTime yesterdayLocalDateTime = LocalDateTime
                .now()
                .withHour(10)
                .withMinute(10)
                .withSecond(10);
        //when
        OffsetDateTime offsetDateTime = StockResultMapper.INSTANCE.map(yesterdayLocalDateTime);
        //then
        assertNotNull(offsetDateTime);
        assertEquals(yesterdayLocalDateTime.toLocalDate(), offsetDateTime.toLocalDate());
        assertEquals(yesterdayLocalDateTime.toLocalTime(), offsetDateTime.toLocalTime());
    }

    @Test
    void toMaxProfitResponse() {
        //given
        Stock stock = new Stock(null, null, null, null);
        StockResult stockResult = new StockResult(stock.getName(), LocalDateTime.now(), 10, new Pair<>(0, 1), new Pair<>(1, 11));
        GetMaxProfitRequest getMaxProfitRequest = new GetMaxProfitRequest()
                .name(null)
                .startDate(null)
                .endDate(null)
                .stockPrices(null);
        //when
        GetMaxProfitResponse response = StockResultMapper.INSTANCE.toMaxProfitResponse(stockResult, getMaxProfitRequest);
        //then
        assertNotNull(response);
        assertNotNull(response.getStockRequest());
        assertNotNull(response.getMaxProfit());
    }

    @Test
    void toMaxProfitResponse_StockResultNull() {
        //given
        GetMaxProfitRequest getMaxProfitRequest = new GetMaxProfitRequest()
                .name(null)
                .startDate(null)
                .endDate(null)
                .stockPrices(null);
        //when
        GetMaxProfitResponse response = StockResultMapper.INSTANCE.toMaxProfitResponse(null, getMaxProfitRequest);
        //then
        assertNotNull(response);
        assertNotNull(response.getStockRequest());
        assertNull(response.getMaxProfit());
    }
}