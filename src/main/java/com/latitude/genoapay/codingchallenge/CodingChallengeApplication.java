package com.latitude.genoapay.codingchallenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import com.latitude.genoapay.codingchallenge.dto.adapter.ProfitRequestAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class CodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingChallengeApplication.class, args);
	}


}
