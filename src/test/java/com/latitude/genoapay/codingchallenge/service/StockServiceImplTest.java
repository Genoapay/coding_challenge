package com.latitude.genoapay.codingchallenge.service;

import com.latitude.genoapay.codingchallenge.model.Stock;
import com.latitude.genoapay.codingchallenge.model.StockResult;
import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.latitude.genoapay.codingchallenge.service.StockServiceImpl.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ritesh
 */
class StockServiceImplTest {

    private StockService stockService;

    @BeforeEach
    void setUp() {
        stockService = new StockServiceImpl();
    }


    @Test
    void getMaxPriceStockTest() {
        //given
        int[] stockPrices = {7, 15, 56, 3, 6, 8, 22};
        Stock stock = new Stock(null, null, null, stockPrices);
        int expectedMaxProfit = 49;
        Pair<Integer, Integer> expectedBuyAt = new Pair<>(0, 7);
        Pair<Integer, Integer> expectedSellAt = new Pair<>(2, 56);
        //when
        StockResult stockResult = stockService.getMaxProfitStock(stock);
        //then
        assertNotNull(stockResult);
        assertEquals(expectedBuyAt, stockResult.getBuyAt());
        assertEquals(expectedBuyAt.getValue1(), stockResult.getBuyPrice());
        assertEquals(expectedSellAt, stockResult.getSellAt());
        assertEquals(expectedSellAt.getValue1(), stockResult.getSellPrice());
        assertEquals(expectedMaxProfit, stockResult.getMaxProfit());
    }

    @Test
    void getMaxProfitStockResult_With_ZeroVariation_InStockPriceTest() {
        //given
        int[] stockPrices = {5, 5, 5, 5, 5, 5};
        Stock stock = new Stock(null, null, null, stockPrices);
        //when
        StockResult stockResult = stockService.getMaxProfitStock(stock);
        //then
        assertNull(stockResult);
    }

    @Test
    void getMaxProfitStockResult_With_SingleItem_InStockPriceTest() {
        //given
        int[] stockPrices = {5};
        Stock stock = new Stock(null, null, null, stockPrices);
        //when
        StockResult maxProfitStock = stockService.getMaxProfitStock(stock);
        //then
        assertNull(maxProfitStock);
    }

    @Test
    void getMaxProfitStockResult_With_nullItem_InStockPriceTest() {
        //given
        int[] stockPrices = {};
        Stock stock = new Stock(null, null, null, stockPrices);
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> stockService.getMaxProfitStock(stock));
        assertEquals(STOCK_PRICE_ITEMS_NOT_AVAILABLE, illegalArgumentException.getMessage());
    }

    @Test
    void getMaxProfitStockResult_With_NegativeStockPrice_Test() {
        //given
        int[] stockPrices = {5, 10, 15, -1};
        Stock stock = new Stock(null, null, null, stockPrices);
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> stockService.getMaxProfitStock(stock));
        assertEquals(NEGATIVE_VALUES_NOT_ALLOWED_ON_STOCK_PRICE, illegalArgumentException.getMessage());
    }

    @Test
    void getMaxProfitStockResult_With_StockNull_Test() {
        //given
        Stock stock = null;
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> stockService.getMaxProfitStock(stock));
        assertEquals(STOCK_OBJECT_NOT_AVAILABLE, illegalArgumentException.getMessage());
    }
}