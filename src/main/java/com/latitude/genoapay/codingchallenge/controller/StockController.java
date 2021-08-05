package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.request.StockRequest;
import com.latitude.genoapay.codingchallenge.response.StockResponse;
import com.latitude.genoapay.codingchallenge.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/maxProfit", produces = {"application/json"}, consumes = "application/json")
    public ResponseEntity<Object> getMaxProfit(@RequestBody StockRequest request) {
        if (!stockService.isValidRequest(request)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: Invalid Request - Missing parameters");
        }

        StockResponse response;
        try {
            response = stockService.getMaximumProfit(request);
            if (response.getMaxProfit() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Cannot make any Profit within the Start and End Times");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

}