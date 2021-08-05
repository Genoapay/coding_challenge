package com.latitude.genoapay.codingchallenge.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.latitude.genoapay.codingchallenge.util.JsonDateDeserializer;

import java.util.Date;

public class StockRequest {

    @JsonProperty("identifier")
    private String identifier;

    @JsonProperty("startDateTime")
    private Date startDateTime;

    @JsonProperty("endDateTime")
    private Date endDateTime;

    @JsonProperty("stockPrices")
    private String stockPrices;

    public StockRequest(String identifier, Date startDateTime, Date endDateTime, String stockPrices) {
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

    public String getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(String stockPrices) {
        this.stockPrices = stockPrices;
    }
}
