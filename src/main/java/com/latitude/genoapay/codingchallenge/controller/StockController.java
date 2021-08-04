package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.request.StockRequest;
import com.latitude.genoapay.codingchallenge.response.StockResponse;
import com.latitude.genoapay.codingchallenge.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

//    public Object getMaxProfit() {
//        return null;
//    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/maxProfit")
    public ResponseEntity<StockResponse> getMaxProfit(@RequestBody StockRequest request) {
//        buildRequest(request);
        if (!stockService.isValidRequest(request)) {
            return ResponseEntity.badRequest().build();
//            throw new Exception("Invalid Request");
        }

        StockResponse response;
        try {
            response = stockService.getMaximumProfit(request);
        } catch (Exception e) {
            //TODO send error message
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    private void buildRequest(StockRequest request) {
        request.setIdentifier("chinna");

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {

            request.setStartDateTime(sdf.parse("04/08/2021 10:00"));
            request.setStartDateTime(sdf.parse("04/08/2021 10:05"));

            request.setStockPrices(new int[]{10, 7, 5, 8, 11, 9});
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int[] generateData() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i <= 60; i++) {
            list.add(getRandomNumber(1, 11));
        }
        return list.stream().mapToInt(i -> i).toArray();
    }


}