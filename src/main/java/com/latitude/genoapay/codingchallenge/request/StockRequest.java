package com.latitude.genoapay.codingchallenge.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.latitude.genoapay.codingchallenge.util.JsonDateDeserializer;

import java.util.Date;

public class StockRequest {

    @JsonProperty("Identifier")
    private String identifier;

    @JsonProperty("Start date time")
    private Date startDateTime;

    @JsonProperty("End date time")
    private Date endDateTime;

    @JsonProperty("Array of stock prices")
    private int[] stockPrices;

    public StockRequest(String identifier, Date startDateTime, Date endDateTime, int[] stockPrices) {
        this.identifier = identifier;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.stockPrices = stockPrices;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int[] getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(int[] stockPrices) {
        this.stockPrices = stockPrices;
    }
}
