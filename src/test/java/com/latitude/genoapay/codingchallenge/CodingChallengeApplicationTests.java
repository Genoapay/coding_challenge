package com.latitude.genoapay.codingchallenge;

import com.google.gson.Gson;
import com.latitude.genoapay.codingchallenge.controller.StockController;
import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import com.latitude.genoapay.codingchallenge.dto.adapter.LocalDateTimeAdapter;
import com.latitude.genoapay.codingchallenge.dto.adapter.ProfitRequestAdapter;
import com.latitude.genoapay.codingchallenge.service.ProfitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class CodingChallengeApplicationTests {

	@Autowired
	private StockController stockController;

	@Autowired
	private ProfitService service;

	@Autowired
	private Gson gson;


	@Test
	public void contextLoads() {
		Assertions.assertNotNull(stockController);
		Assertions.assertNotNull(service);
		Assertions.assertNotNull(gson);
		Assertions.assertEquals(ProfitRequestAdapter.class, gson.getAdapter(ProfitRequest.class).getClass());
		Assertions.assertEquals(LocalDateTimeAdapter.class, gson.getAdapter(LocalDateTime.class).getClass());
	}

}
