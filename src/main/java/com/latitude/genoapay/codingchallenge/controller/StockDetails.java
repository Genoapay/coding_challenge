package com.latitude.genoapay.codingchallenge.controller;

import java.util.Date;

public class StockDetails {
	
	private String identifier;
	private Date startDate;
	private Date endDate;
	private int[] stockPricesArr;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int[] getStockPricesArr() {
		return stockPricesArr;
	}
	public void setStockPricesArr(int[] stockPricesArr) {
		this.stockPricesArr = stockPricesArr;
	}

}
