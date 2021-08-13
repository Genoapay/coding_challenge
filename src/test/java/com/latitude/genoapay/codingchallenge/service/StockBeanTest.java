package com.latitude.genoapay.codingchallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.latitude.genoapay.codingchallenge.controller.Status;
import com.latitude.genoapay.codingchallenge.controller.StockDetails;
import com.latitude.genoapay.codingchallenge.controller.StockProfit;
import com.latitude.genoapay.codingchallenge.utils.DateUtils;

class StockBeanTest {
	

	@Test
	void testDuplicateNumbers1() { 
		Date yesterday = DateUtils.addDay(new Date(), -1);			
		Date startDate = DateUtils.setTime(yesterday, 10, 0);
		Date endDate = DateUtils.setTime(yesterday, 10, 5);

		StockPriceService stockBean = new StockPriceService();
		StockDetails stockDetails = new StockDetails();
		stockDetails.setStartDate(startDate);
		stockDetails.setEndDate(endDate);
		
		int[] stockPriceArr = { 5, 5, 5, 4, 4, 4 };
		stockDetails.setStockPricesArr(stockPriceArr);
		StockProfit stockProfit = stockBean.getMaxProfit(stockDetails);
		assertEquals(0, stockProfit.getMaxProfit());
		assertEquals(0, stockProfit.getBuyValue());  //buy value is 0 as there's no profit to make 
		assertEquals(0, stockProfit.getSellValue()); //buy value is 0 as there's no profit to make 	
	}
	
	@Test
	void testDuplicateNumbers2() { 
		Date yesterday = DateUtils.addDay(new Date(), -1);			
		Date startDate = DateUtils.setTime(yesterday, 10, 0);
		Date endDate = DateUtils.setTime(yesterday, 10, 5);

		StockPriceService stockBean = new StockPriceService();
		StockDetails stockDetails = new StockDetails();
		stockDetails.setStartDate(startDate);
		stockDetails.setEndDate(endDate);
		
		int[] stockPriceArr = { 4, 4, 4, 5, 5, 5 };
		stockDetails.setStockPricesArr(stockPriceArr);
		StockProfit stockProfit = stockBean.getMaxProfit(stockDetails);
		assertEquals(1, stockProfit.getMaxProfit());
		assertEquals(4, stockProfit.getBuyValue());  
		assertEquals(5, stockProfit.getSellValue()); 
	}
	
	@Test
	void testAllDuplicateNumbers() { 
		Date yesterday = DateUtils.addDay(new Date(), -1);			
		Date startDate = DateUtils.setTime(yesterday, 10, 0);
		Date endDate = DateUtils.setTime(yesterday, 10, 5);

		StockPriceService stockBean = new StockPriceService();
		StockDetails stockDetails = new StockDetails();
		stockDetails.setStartDate(startDate);
		stockDetails.setEndDate(endDate);
		
		int[] stockPriceArr = { 5, 5, 5, 5, 5, 5 };
		stockDetails.setStockPricesArr(stockPriceArr);
		StockProfit stockProfit = stockBean.getMaxProfit(stockDetails);
		assertEquals(0, stockProfit.getMaxProfit());
		assertEquals(0, stockProfit.getBuyValue());    //buy value is 0 as there's no profit to make 
		assertEquals(0, stockProfit.getSellValue());   //buy value is 0 as there's no profit to make	
	} 
	
	
	@Test
	void testBaseDate() {

		Date yesterday = DateUtils.addDay(new Date(), -1);			
		Date startDate = DateUtils.setTime(yesterday, 10, 0);
		Date endDate = DateUtils.setTime(yesterday, 10, 9);

		StockPriceService stockBean = new StockPriceService();
		StockDetails stockDetails = new StockDetails();
		stockDetails.setStartDate(startDate);
		stockDetails.setEndDate(endDate);
		
		int[] stockPriceArr = { 10, 7, 6, 5, 8, 11, 12, 6, 8, 9 };
		stockDetails.setStockPricesArr(stockPriceArr);
		StockProfit stockProfit = stockBean.getMaxProfit(stockDetails);
		assertEquals(7, stockProfit.getMaxProfit());
		assertEquals(5, stockProfit.getBuyValue());
		assertEquals(12, stockProfit.getSellValue());

		int[] stockPriceArr2 = { 10, 7, 6, 5, 8, 7, 3, 9, 10, 8 };
		stockDetails.setStockPricesArr(stockPriceArr2);
		StockProfit stockProfit2 = stockBean.getMaxProfit(stockDetails);
		assertEquals(7, stockProfit2.getMaxProfit());
		assertEquals(3, stockProfit2.getBuyValue());
		assertEquals(10, stockProfit2.getSellValue());

		int[] stockPriceArr3 = { 10, 7, 6, 5, 8, 7, 3, 4, 6, 7 };
		stockDetails.setStockPricesArr(stockPriceArr3);
		StockProfit stockProfit3 = stockBean.getMaxProfit(stockDetails);
		assertEquals(4, stockProfit3.getMaxProfit());
		assertEquals(3, stockProfit3.getBuyValue());
		assertEquals(7, stockProfit3.getSellValue());
		
		int[] stockPriceArr4 = { 5, 6, 7, 10, 4, 8, 12, 6, 7, 8 };
		stockDetails.setStockPricesArr(stockPriceArr4);
		StockProfit stockProfit4 = stockBean.getMaxProfit(stockDetails);
		assertEquals(8, stockProfit4.getMaxProfit());
		assertEquals(4, stockProfit4.getBuyValue());
		assertEquals(12, stockProfit4.getSellValue());

	}
	
	@Test 
	void testPastBaseDate() {
	  
	   Date yesterday = DateUtils.addDay(new Date(), -1); 
	   Date startDate = DateUtils.setTime(yesterday, 10, 5); 
	   Date endDate = DateUtils.setTime(yesterday, 10, 9);
	  
	   StockPriceService stockBean = new StockPriceService(); 
	   StockDetails stockDetails = new StockDetails(); 
	   stockDetails.setStartDate(startDate);
	   stockDetails.setEndDate(endDate);
	  
	  
	   int[] stockPriceArr = { 10, 7, 6, 5, 8, 11, 12, 6, 8, 9 };
	   stockDetails.setStockPricesArr(stockPriceArr); 
	   StockProfit stockProfit = stockBean.getMaxProfit(stockDetails); 
	   assertEquals(3, stockProfit.getMaxProfit());
	   assertEquals(6, stockProfit.getBuyValue());
	   assertEquals(9, stockProfit.getSellValue());
	  
	   int[] stockPriceArr2 = { 10, 7, 6, 5, 8, 7, 3, 9, 10, 8 };
	   stockDetails.setStockPricesArr(stockPriceArr2); 
	   StockProfit stockProfit2 = stockBean.getMaxProfit(stockDetails); 
	   assertEquals(7, stockProfit2.getMaxProfit());
	   assertEquals(3, stockProfit2.getBuyValue());
	   assertEquals(10, stockProfit2.getSellValue());
	  
	   int[] stockPriceArr3 = { 10, 7, 6, 5, 8, 7, 3, 4, 6, 7 };
	   stockDetails.setStockPricesArr(stockPriceArr3); 
	   StockProfit stockProfit3 = stockBean.getMaxProfit(stockDetails); 
	   assertEquals(4, stockProfit3.getMaxProfit());
	   assertEquals(3, stockProfit3.getBuyValue());
	   assertEquals(7, stockProfit3.getSellValue());
	  
	   int[] stockPriceArr4 = { 5, 6, 7, 10, 4, 8, 12, 6, 7, 8 };
	   stockDetails.setStockPricesArr(stockPriceArr4); 
	   StockProfit stockProfit4 = stockBean.getMaxProfit(stockDetails); 
	   assertEquals(4, stockProfit4.getMaxProfit());
	   assertEquals(8, stockProfit4.getBuyValue());
	   assertEquals(12, stockProfit4.getSellValue());
  
	}
	
	@Test 
	void testEarlyStartDate() {
		
		Date yesterday = DateUtils.addDay(new Date(), -1);			
		Date startDate = DateUtils.setTime(yesterday, 9, 0);
		Date endDate = DateUtils.setTime(yesterday, 10, 9);

		StockPriceService stockBean = new StockPriceService();
		StockDetails stockDetails = new StockDetails();
		stockDetails.setStartDate(startDate);
		stockDetails.setEndDate(endDate);

		int[] stockPriceArr = { 10, 7, 6, 5, 8, 11, 12, 6, 8, 9 };
		stockDetails.setStockPricesArr(stockPriceArr); 
		StockProfit stockProfit = stockBean.getMaxProfit(stockDetails);
		assertEquals(Status.FAIL, stockProfit.getStatus());		
		
	}
	
	@Test 
	void testEndDate() {
		
		Date yesterday = DateUtils.addDay(new Date(), -1);			
		Date startDate = DateUtils.setTime(yesterday, 10, 0);
		Date endDate = DateUtils.setTime(yesterday, 10, 10);

		StockPriceService stockBean = new StockPriceService();
		StockDetails stockDetails = new StockDetails();
		stockDetails.setStartDate(startDate);
		stockDetails.setEndDate(endDate);

		int[] stockPriceArr = { 10, 7, 6, 5, 8, 11, 12, 6, 8, 9 };
		stockDetails.setStockPricesArr(stockPriceArr); 
		StockProfit stockProfit = stockBean.getMaxProfit(stockDetails);
		assertEquals(Status.FAIL, stockProfit.getStatus());		
		
	}

}
