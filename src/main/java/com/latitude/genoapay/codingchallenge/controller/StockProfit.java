package com.latitude.genoapay.codingchallenge.controller;

import java.util.Date;

public class StockProfit {
	
	private StockDetails stockDetails;
	private Date processedDate;
	private int maxProfit;
	private int buyValue;
	private int sellValue;
	private Status status;
	private String message;
	
	public StockDetails getStockDetails() {
		return stockDetails;
	}
	public void setStockDetails(StockDetails stockPrices) {
		this.stockDetails = stockPrices;
	}
	public Date getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
