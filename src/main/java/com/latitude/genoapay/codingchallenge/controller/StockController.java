package com.latitude.genoapay.codingchallenge.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.latitude.genoapay.codingchallenge.service.StockPriceService;


@RestController
public class StockController {

	public Object getMaxProfit() {
		return null;
	}
	
	@PostMapping(path = "/stockprofit", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public StockProfit getMaxStockPrice(@RequestBody StockDetails stockPrices) {
		StockPriceService stockPriceService = new StockPriceService();
        return stockPriceService.getMaxProfit(stockPrices);
    }
}