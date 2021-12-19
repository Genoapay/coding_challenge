package com.latitude.genoapay.codingchallenge.service;

import com.latitude.genoapay.codingchallenge.dto.ProfitResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfitServiceTest {

    private ProfitService profitService = new ProfitService();

    @Test
    @DisplayName("Test cases: { 10, 7, 5, 8, 11, 9 }")
    public void normalPriceList() {
        Integer [] priceList = new Integer[] { 10, 7, 5, 8, 11, 9 };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(6, profitResponse.getMaxProfit());
        assertEquals(5, profitResponse.getBuyValue());
        assertEquals(11, profitResponse.getSellValue());
    }

    @Test
    @DisplayName("Test cases: { 20, 2, 7, 5, 8, 11, 19, 13 }")
    public void longerPriceList() {
        Integer [] priceList = new Integer[] { 20, 2, 7, 5, 8, 11, 19, 13 };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(17, profitResponse.getMaxProfit());
        assertEquals(2, profitResponse.getBuyValue());
        assertEquals(19, profitResponse.getSellValue());
    }

    @Test
    @DisplayName("Test cases: { 3, -100, 0, -1, 0, 7, -3 }")
    public void priceWithNegativePrice() {
        Integer [] priceList = new Integer[] { 3, -100, 0, -1, 0, 7, -3 };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(107, profitResponse.getMaxProfit());
        assertEquals(-100, profitResponse.getBuyValue());
        assertEquals(7, profitResponse.getSellValue());
    }

    @Test
    @DisplayName("Test cases: { null, 10, null, null, 7, 5, null, 8, 11, 9, null, null }")
    public void priceWitNullValue() {
        Integer [] priceList = new Integer[] { null, 10, null, null, 7, 5, null, 8, 11, 9, null, null };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(6, profitResponse.getMaxProfit());
        assertEquals(5, profitResponse.getBuyValue());
        assertEquals(11, profitResponse.getSellValue());
    }

    @Test
    @DisplayName("Test cases: { null, 8, null, 8, null }")
    public void priceWithSinglePrice() {
        Integer [] priceList = new Integer[] { null, 8, null, 8, null };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(0, profitResponse.getMaxProfit());
        assertEquals(8, profitResponse.getBuyValue());
        assertEquals(8, profitResponse.getSellValue());
    }

    @Test
    @DisplayName("Test cases: { null, -1, null, -3, null }")
    public void priceWithAllNegativePrices() {
        Integer [] priceList = new Integer[] { null, -1, null, -3, null };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(0, profitResponse.getMaxProfit());
        assertEquals(-1, profitResponse.getBuyValue());
        assertEquals(-1, profitResponse.getSellValue());
    }


    @Test
    @DisplayName("Test cases: { 10, 7, 6, 5, 4, 1 }")
    public void priceWithNoRaise() {
        Integer [] priceList = new Integer[] { 10, 7, 6, 5, 4, 1 };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(0, profitResponse.getMaxProfit());
        assertEquals(10, profitResponse.getBuyValue());
        assertEquals(10, profitResponse.getSellValue());
    }

    @Test
    @DisplayName("Test cases: { 1, 21, 33, 33, 33, 42 }")
    public void priceWithNoDecline() {
        Integer [] priceList = new Integer[] { 1, 21, 33, 33, 33, 42 };
        ProfitResponse profitResponse = profitService.calculateMaxProfit(Arrays.asList(priceList));
        assertNotNull(profitResponse);
        assertEquals(41, profitResponse.getMaxProfit());
        assertEquals(1, profitResponse.getBuyValue());
        assertEquals(42, profitResponse.getSellValue());
    }

}
