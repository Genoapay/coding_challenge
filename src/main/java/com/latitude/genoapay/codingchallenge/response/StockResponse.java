package com.latitude.genoapay.codingchallenge.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.latitude.genoapay.codingchallenge.request.StockRequest;

import java.util.Date;

public class StockResponse {

    private StockRequest stockRequest;

    @JsonProperty("processedDateTime")
//    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date processedDateTime;

    @JsonProperty("maxProfit")
    private int maxProfit;

    @JsonProperty("buyValue")
    private int buyValue;

    @JsonProperty("sellValue")
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
