package com.latitude.genoapay.codingchallenge.service;

import java.util.Date;

import com.latitude.genoapay.codingchallenge.controller.Status;
import com.latitude.genoapay.codingchallenge.controller.StockDetails;
import com.latitude.genoapay.codingchallenge.controller.StockProfit;
import com.latitude.genoapay.codingchallenge.utils.DateUtils;

public class StockPriceService {

	
	public StockProfit getMaxProfit(StockDetails stockDetails) {
		Date yesterday = DateUtils.addDay(new Date(), -1);
		yesterday = DateUtils.setTime(yesterday, 10, 0);
		
		int startingIdx = DateUtils.getDateDiffInMin(yesterday, stockDetails.getStartDate());		
		int endingIdx = DateUtils.getDateDiffInMin(yesterday, stockDetails.getEndDate());
		
		StockProfit stockProfit = new StockProfit();
		int[] stockPrices = stockDetails.getStockPricesArr();
		
		stockProfit = validateStockDetails(stockDetails, stockPrices, startingIdx, endingIdx);
		if (stockProfit.getStatus()==Status.FAIL) {
			return stockProfit;
		}

		//first value in the start index is set as 'lowestBuyPrice', once a value is less than the set 'lowestBuyPrice' then process it to check if the profit is bigger
		int lowestBuyPrice = stockPrices[startingIdx];
		int maxProfit = 0;
		int sellPrice = 0;
		int buyPrice = 0;
		
		for(int i = startingIdx; i <= endingIdx; i++) {
			
			//this should not happen as there's no negative dollar value in stocks
			if (stockPrices[i] < 0) 
				continue;
			
			
			if(stockPrices[i] > lowestBuyPrice) {
				//set the profit as we have identified the 'lowest'
				int profit = stockPrices[i] - lowestBuyPrice;
				
				if(profit > maxProfit) {
					maxProfit = profit;
					sellPrice = stockPrices[i];
					buyPrice = lowestBuyPrice;
				}			
			}
			else {
				lowestBuyPrice = stockPrices[i];
			}
		}	
		
		
		stockProfit.setBuyValue(buyPrice);
		stockProfit.setSellValue(sellPrice);
		stockProfit.setMaxProfit(maxProfit);
		stockProfit.setProcessedDate(new Date());
		stockProfit.setStockDetails(stockDetails);
		stockProfit.setStatus(Status.SUCCESS);
		
		return stockProfit;
	}
	
	protected StockProfit validateStockDetails(StockDetails stockDetails, int[] stockPrices, int startingIdx, int endingIdx) {
		StockProfit stockProfit = new StockProfit();
		
		if(stockPrices == null ) {
			stockProfit.setStockDetails(stockDetails);
			stockProfit.setStatus(Status.FAIL);
			stockProfit.setMessage("Stock prices should not be empty");
			return stockProfit;
		}
		
		if (startingIdx < 0) {
			stockProfit.setStockDetails(stockDetails);
			stockProfit.setStatus(Status.FAIL);
			stockProfit.setMessage("Start date should not be earlier than opening time.");
			return stockProfit;
		}
		
		if (stockPrices.length <= startingIdx) {
			stockProfit.setStockDetails(stockDetails);
			stockProfit.setStatus(Status.FAIL);
			stockProfit.setMessage("Invalid start date. " + Integer.toString(startingIdx));
			return stockProfit;
		}
		if (stockPrices.length <= endingIdx) {
			stockProfit.setStockDetails(stockDetails);
			stockProfit.setStatus(Status.FAIL);
			stockProfit.setMessage("Invalid end date.");
			return stockProfit;
		}
		
		return stockProfit;
	}
	
}
