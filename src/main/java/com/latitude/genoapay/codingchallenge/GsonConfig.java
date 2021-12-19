package com.latitude.genoapay.codingchallenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import com.latitude.genoapay.codingchallenge.dto.ProfitResponse;
import com.latitude.genoapay.codingchallenge.dto.adapter.LocalDateTimeAdapter;
import com.latitude.genoapay.codingchallenge.dto.adapter.ProfitRequestAdapter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class GsonConfig {

    @Bean
    public HttpMessageConverters customConverters(Gson gson) {
        Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter(gson);
        messageConverters.add(gsonHttpMessageConverter);
        return new HttpMessageConverters(true, messageConverters);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(ProfitRequest.class, new ProfitRequestAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }
}
