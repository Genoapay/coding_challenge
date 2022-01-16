package com.latitude.genoapay.codingchallenge.service;

import com.latitude.genoapay.codingchallenge.exception.InvalidStockDetailsException;
import com.latitude.genoapay.codingchallenge.model.Stock;
import com.latitude.genoapay.codingchallenge.model.StockResult;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ritesh
 */
@Service
public class StockServiceImpl implements StockService {

    public static final String STOCK_OBJECT_NOT_AVAILABLE = "provide stock object";
    public static final String STOCK_PRICE_ITEMS_NOT_AVAILABLE = "provide array of stock prices";
    public static final String NEGATIVE_VALUES_NOT_ALLOWED_ON_STOCK_PRICE = "negative values not allowed on stock price";

    @Override
    public StockResult getMaxProfitStock(Stock stock) throws InvalidStockDetailsException {
        validateStockPrices(stock);
        Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Integer> maxPriceStock = getMaxPriceStock(stock);
        if (maxPriceStock == null) {
            return null;
        }
        StockResult result = new StockResult(stock.getName(), LocalDateTime.now(),
                maxPriceStock.getValue2(), maxPriceStock.getValue0(), maxPriceStock.getValue1());
        return result;
    }

    private Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Integer> getMaxPriceStock(Stock stock) {
        //stock array from stock
        int[] stockPrices = stock.getStockPrices();
        // List of triplet to hold Buy, Sell and MaxProfit. Capturing buy/sell in Pair
        // will help us in holding time(value0) and stock price(value1) for any further processing or publishing
        List<Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Integer>> buySellProfit = new ArrayList<>();
        // iterating through the stock prices array backward in order to respect time series buy and then sell.
        for (int i = stockPrices.length - 1; i >= 0; i--) {

            int lowestPrice = stockPrices[i];
            int lowestPriceIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (lowestPrice > stockPrices[j]) {
                    lowestPrice = stockPrices[j];
                    lowestPriceIndex = j;
                }
            }
            if (lowestPrice < stockPrices[i]) {
                int maxProfit = stockPrices[i] - lowestPrice;
                Pair<Integer, Integer> lowestSalePrice = new Pair<>(lowestPriceIndex, lowestPrice);
                Pair<Integer, Integer> highestSalePrice = new Pair<>(i, stockPrices[i]);
                buySellProfit.add(new Triplet(lowestSalePrice, highestSalePrice, maxProfit));
            }
        }
        return buySellProfit.stream().max(Comparator.comparing(Triplet::getValue2)).orElse(null);
    }

    private void validateStockPrices(Stock stock) {
        if (stock == null) {
            throw new InvalidStockDetailsException(STOCK_OBJECT_NOT_AVAILABLE);
        }
        if (stock.getStockPrices().length == 0) {
            throw new InvalidStockDetailsException(STOCK_PRICE_ITEMS_NOT_AVAILABLE);
        }
        if (checkNegativeItems(stock.getStockPrices())) {
            throw new InvalidStockDetailsException(NEGATIVE_VALUES_NOT_ALLOWED_ON_STOCK_PRICE);
        }
    }

    private boolean checkNegativeItems(int[] stockPrices) {
        boolean present = Arrays.stream(stockPrices).filter(x -> x < 0).findAny().isPresent();
        return present;
    }
}
