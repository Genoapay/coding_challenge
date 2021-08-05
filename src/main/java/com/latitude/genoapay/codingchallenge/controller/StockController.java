package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.request.StockRequest;
import com.latitude.genoapay.codingchallenge.response.StockResponse;
import com.latitude.genoapay.codingchallenge.service.StockService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping(value = "/maxProfit", produces = {"application/json"}, consumes = "application/json")
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

    @GetMapping(value = "/maxProfit2", produces = {"application/json"}, consumes = "application/json")
    public ResponseEntity<Object> getMaxProfit(@RequestParam("identifier") String identifier,
            @RequestParam("startDateTime") String startDateTime, @RequestParam("endDateTime") String endDateTime,
            @RequestParam("stockPrices") String stockPrices){

//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String[] strArr = stockPrices.split(",");
        int[] stockPricesArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
             stockPricesArr[i] = Integer.parseInt(strArr[i]);

        }
        StockRequest request = new StockRequest(identifier, (new DateTime( startDateTime)).toDate(), (new DateTime( endDateTime)).toDate(), stockPrices);

//        try {
//            request = new StockRequest(identifier, (new DateTime( startDateTime)).toDate(), (new DateTime( endDateTime)).toDate(), stockPricesArr);
//        } catch (ParseException e) {
//            throw new ParseException("Start/End time format is incorrect", e.getErrorOffset());
//        }

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

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}