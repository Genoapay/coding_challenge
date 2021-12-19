package com.latitude.genoapay.codingchallenge.dto;

import java.time.LocalDateTime;

public class ProfitResponse {

    private ProfitRequest profitRequest;

    private LocalDateTime processedDateTime;

    private int maxProfit;

    private int buyValue;

    private int sellValue;

    public ProfitRequest getProfitRequest() {
        return profitRequest;
    }

    public void setProfitRequest(ProfitRequest profitRequest) {
        this.profitRequest = profitRequest;
    }

    public LocalDateTime getProcessedDateTime() {
        return processedDateTime;
    }

    public void setProcessedDateTime(LocalDateTime processedDateTime) {
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
