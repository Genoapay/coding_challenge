package com.latitude.genoapay.codingchallenge.mapper;

import com.latitude.genoapay.codingchallenge.model.GetMaxProfitRequest;
import com.latitude.genoapay.codingchallenge.model.Stock;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author ritesh
 */
class StockMapperTest {

    @Test
    void mapToOffsetDateTimeToLocalDateTimeTest() {
        //given
        OffsetDateTime YesterdayOffsetDateTime = OffsetDateTime
                .now()
                .minusDays(1)
                .withHour(10)
                .withMinute(10)
                .withSecond(10);
        //when
        LocalDateTime localDateTime = StockMapper.INSTANCE.map(YesterdayOffsetDateTime);
        //then
        assertNotNull(localDateTime);
        assertEquals(localDateTime.toLocalDate(), YesterdayOffsetDateTime.toLocalDate());
        assertEquals(localDateTime.toLocalTime(), YesterdayOffsetDateTime.toLocalTime());
    }

    @Test
    void stockRequestToStock() {
        //given
        List<Integer> stockPriceList = Arrays.asList(1, 4, 3, 8, 4, 9, 10);
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
                .name("test")
                .startDate(startDate)
                .endDate(endDate)
                .stockPrices(stockPriceList);
        //when
        Stock stock = StockMapper.INSTANCE.stockRequestToStock(getMaxProfitRequest);
        //then
        assertNotNull(stock);
        assertEquals(stockPriceList.toArray().length, stock.getStockPrices().length);
        assertEquals(stockPriceList.toArray()[3], stock.getStockPrices()[3]);
    }
}