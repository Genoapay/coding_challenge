package com.latitude.genoapay.codingchallenge.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.latitude.genoapay.codingchallenge.request.StockRequest;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class StockResponse {

    private StockRequest stockRequest;

    @JsonProperty("Processed date time")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
//    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date processedDateTime;

    @JsonProperty("Max profit")
    private int maxProfit;

    @JsonProperty("Buy value")
    private int buyValue;

    @JsonProperty("Sell value")
    private int sellValue;

    public StockResponse(StockRequest request, Date processedDateTime, int maxProfit, int buyValue, int sellValue) {
        this.stockRequest = request;
        this.processedDateTime = processedDateTime;
        this.maxProfit = maxProfit;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    public StockRequest getStockRequest() {
        return stockRequest;
    }

    public void setStockRequest(StockRequest stockRequest) {
        this.stockRequest = stockRequest;
    }

    public Date getProcessedDateTime() {
        return processedDateTime;
    }

    public void setProcessedDateTime(Date processedDateTime) {
        this.processedDateTime = processedDateTime;
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(int maxProfit) {
        this.maxProfit = maxProfit;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(int buyValue) {
        this.buyValue = buyValue;
    }

    public int getSellValue() {
        return sellValue;
    }

    public void setSellValue(int sellValue) {
        this.sellValue = sellValue;
    }
}
