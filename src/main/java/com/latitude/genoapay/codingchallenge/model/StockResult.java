package com.latitude.genoapay.codingchallenge.model;

import org.javatuples.Pair;

import java.time.LocalDateTime;

/**
 * @author ritesh
 */
public class StockResult {

    private String name;
    private LocalDateTime processedDateTime;
    private int maxProfit;
    private Pair<Integer, Integer> buyAt;
    private Pair<Integer, Integer> sellAt;

    public StockResult(String name, LocalDateTime processedDateTime, int maxProfit, Pair<Integer, Integer> buyAt, Pair<Integer, Integer> sellAt) {
        this.name = name;
        this.processedDateTime = processedDateTime;
        this.maxProfit = maxProfit;
        this.buyAt = buyAt;
        this.sellAt = sellAt;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getProcessedDateTime() {
        return processedDateTime;
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public Pair<Integer, Integer> getBuyAt() {
        return buyAt;
    }

    public Pair<Integer, Integer> getSellAt() {
        return sellAt;
    }

    public int getBuyTime() {
        return buyAt.getValue0();
    }

    public int getBuyPrice() {
        return buyAt.getValue1();
    }

    public int getSellTime() {
        return sellAt.getValue0();
    }

    public int getSellPrice() {
        return sellAt.getValue1();
    }
}
