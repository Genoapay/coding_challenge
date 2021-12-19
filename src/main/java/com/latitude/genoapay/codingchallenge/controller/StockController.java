package com.latitude.genoapay.codingchallenge.controller;

import com.latitude.genoapay.codingchallenge.dto.ExceptionalResponse;
import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import com.latitude.genoapay.codingchallenge.dto.ProfitResponse;
import com.latitude.genoapay.codingchallenge.service.ProfitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class StockController {

	@Autowired
	private ProfitService profitService;

	@CrossOrigin
	@PostMapping(path = "/maxProfit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfitResponse> getMaxProfit(@RequestBody ProfitRequest profitRequest) {
		ProfitResponse profitResponse = profitService.calculateMaxProfit(profitRequest.getPriceList());
		profitResponse.setProfitRequest(profitRequest);
		profitResponse.setProcessedDateTime(LocalDateTime.now());
		return ResponseEntity.ok(profitResponse);
	}

	@ExceptionHandler({IOException.class})
	public ResponseEntity<ExceptionalResponse> handleException(IOException exception) {
		ExceptionalResponse exceptionalResponse = new ExceptionalResponse();
		exceptionalResponse.setCode(HttpStatus.BAD_REQUEST.value());
		exceptionalResponse.setMessage(exception.getMessage());
		return ResponseEntity.badRequest().body(exceptionalResponse);
	}

}