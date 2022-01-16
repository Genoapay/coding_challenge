package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.exception.InvalidStockDetailsException;
import com.latitude.genoapay.codingchallenge.mapper.StockMapper;
import com.latitude.genoapay.codingchallenge.mapper.StockResultMapper;
import com.latitude.genoapay.codingchallenge.model.*;
import com.latitude.genoapay.codingchallenge.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "/stock/getmaxprofit",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<GetMaxProfitResponse> getMaxProfit(@Valid @RequestBody GetMaxProfitRequest getMaxProfitRequest) {
        try {
            //Mapping getMaxProfitRequest into Stock
            Stock stock = StockMapper.INSTANCE.stockRequestToStock(getMaxProfitRequest);
            //calling stockservice to calculate maxProfit and return result
            StockResult stockResult = stockService.getMaxProfitStock(stock);
            //mapping StockResult to GetMaxProfitResponse and getMaxProfitRequest to getMaxProfitResponse.stockRequest
            GetMaxProfitResponse response = StockResultMapper.INSTANCE.toMaxProfitResponse(stockResult, getMaxProfitRequest);
            return ResponseEntity.ok().body(response);
        } catch (InvalidStockDetailsException ex) {
            return new ResponseEntity(
                    new ErrorMessage()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .errorMessage(ex.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}