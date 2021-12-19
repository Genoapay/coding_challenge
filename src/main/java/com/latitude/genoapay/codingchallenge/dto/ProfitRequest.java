package com.latitude.genoapay.codingchallenge.dto;

import java.time.LocalDateTime;
import java.util.List;

//@JsonDeserialize(using = ProfitRequestDeserializer.class)
public class ProfitRequest {

    private String identifier;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private List<Integer> priceList;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Integer> priceList) {
        this.priceList = priceList;
    }
}
