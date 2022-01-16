package com.latitude.genoapay.codingchallenge.service;

import com.latitude.genoapay.codingchallenge.exception.InvalidStockDetailsException;
import com.latitude.genoapay.codingchallenge.model.Stock;
import com.latitude.genoapay.codingchallenge.model.StockResult;

/**
 * @author ritesh
 */

public interface StockService {

    StockResult getMaxProfitStock(Stock stock) throws InvalidStockDetailsException;
}
