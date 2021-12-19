package com.latitude.genoapay.codingchallenge.dto.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocalDateTimeAdapterTest {

    @Autowired
    private Gson gson;

    @Test
    public void readLocalDateTime() {
        String dateTimeString = "\"2021-12-25 01:23\"";
        LocalDateTime localDateTime = gson.fromJson(dateTimeString, LocalDateTime.class);
        assertEquals(2021, localDateTime.getYear());
        assertEquals(12, localDateTime.getMonth().getValue());
        assertEquals(25, localDateTime.getDayOfMonth());
        assertEquals(1, localDateTime.getHour());
        assertEquals(23, localDateTime.getMinute());
        assertEquals(0, localDateTime.getSecond());
    }

    @Test
    public void readMalformedDateTime() {
        String dateTimeString = "2021/12/25 11:23";
        Assertions.assertThrows(JsonSyntaxException.class, () -> gson.fromJson(dateTimeString, LocalDateTime.class));
    }

    @Test
    public void writeNullValueLocalDateTime() {
        assertEquals("null", gson.toJson(null, LocalDateTime.class));
    }

    @Test
    public void writeValueLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 12, 21, 1, 12);
        assertEquals("\"2021-12-21 01:12\"", gson.toJson(localDateTime, LocalDateTime.class));
    }

}
