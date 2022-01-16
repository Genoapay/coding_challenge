package com.latitude.genoapay.codingchallenge.model;

import java.time.LocalDateTime;

/**
 * @author ritesh
 */
public class Stock {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int[] stockPrices;

    public Stock(String name, LocalDateTime startDate, LocalDateTime endDate, int[] stockPrices) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stockPrices = stockPrices;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int[] getStockPrices() {
        return stockPrices;
    }
}
