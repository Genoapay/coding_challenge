package com.latitude.genoapay.codingchallenge.service;

import com.latitude.genoapay.codingchallenge.request.StockRequest;
import com.latitude.genoapay.codingchallenge.response.StockResponse;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class StockService {

    private static final long MINUTE_MILLISECODS = 60000L;

    public boolean isValidRequest(StockRequest request) {
        return StringUtils.hasText(request.getIdentifier()) && request.getEndDateTime() != null
                && request.getStartDateTime() != null && request.getStockPrices().length != 0;
    }

    public StockResponse getMaximumProfit(StockRequest request) throws Exception {
        Date startTime = request.getStartDateTime();
        Date endTime = request.getEndDateTime();
        int[] stockPricesArr = request.getStockPrices();

        Date dayStartTime = getDayStartTime(startTime);
        int startIdx = getDiffInMinutes(dayStartTime, startTime);
        int endIdx = getDiffInMinutes(dayStartTime, endTime);
        if (startIdx == endIdx) {
            throw new Exception("Start and End Times are out of range");
        }
        if (endIdx > stockPricesArr.length) {
            endIdx = stockPricesArr.length - 1;
        }

        int[] stockPrices = Arrays.copyOfRange(stockPricesArr, startIdx, endIdx);

        int maxProfit = 0;
        int bestBuy = 0;
        int bestSell = 0;

        int profit;
        for (int i = 0; i < stockPrices.length; i++) {
            int buy = stockPrices[i];
            for (int j = i + 1; j < stockPrices.length; j++) {
                int sell = stockPrices[j];
                if (buy >= sell) {
                    continue;
                }
                profit = sell - buy;
                if (profit > maxProfit) {
                    maxProfit = profit;
                    bestBuy = buy;
                    bestSell = sell;
                }
            }
        }
        StockResponse response = new StockResponse(request, new Date(), maxProfit, bestBuy, bestSell);
        System.out.println("Profit : " + maxProfit + " (Buy at $" + bestBuy + ", Sell at $" + bestSell + ")");
        return response;
    }

    Date getDayStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 10);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    int getDiffInMinutes(Date fromDate, Date toDate) {
        return (int) (Math.max(0, toDate.getTime() - fromDate.getTime()) / MINUTE_MILLISECODS);
    }
}
