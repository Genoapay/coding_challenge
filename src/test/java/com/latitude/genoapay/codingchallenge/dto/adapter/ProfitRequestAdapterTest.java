package com.latitude.genoapay.codingchallenge.dto.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProfitRequestAdapterTest {

    @Autowired
    private Gson gson;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(LocalDateTimeAdapter.DATETIME_FORMAT);

    @Test
    public void readRequest() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(10, 0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(10, 6));
        String json = "{\n" +
            "\"identifier\": \"123\",\n" +
            "\"startTime\": \"" + startTime.format(dateTimeFormatter) + "\",\n" +
            "\"endTime\": \"" + endTime.format(dateTimeFormatter) + "\",\n" +
            "\"priceList\": [10, null, null, null, null, null, null]\n" +
        "}";

        ProfitRequest profitRequest = gson.fromJson(json, ProfitRequest.class);
        Assertions.assertNotNull(profitRequest);
        assertEquals("123", profitRequest.getIdentifier());
        assertEquals(startTime.format(dateTimeFormatter), profitRequest.getStartTime().format(dateTimeFormatter));
        assertEquals(endTime.format(dateTimeFormatter), profitRequest.getEndTime().format(dateTimeFormatter));
        Assertions.assertNotNull(profitRequest.getPriceList());
        assertEquals(7, profitRequest.getPriceList().size());
        assertEquals(10, profitRequest.getPriceList().get(0));
        assertEquals(6, profitRequest.getPriceList().stream().filter(Objects::isNull).count());
    }

    @Test
    public void readRequestWithMissedFields() {
        String json = "{\n" +
                "\"identifier\": \"123\",\n" +
                "\"priceList\": [10, null, null, null, null, null]\n" +
                "}";
        Assertions.assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, ProfitRequest.class));
    }

    @Test
    public void readRequestWithWrongTime() {
        String json = "{\n" +
                "\"identifier\": \"123\",\n" +
                "\"startTime\": \"2021-12-17 11:00\",\n" +
                "\"endTime\": \"2021-12-17 10:06\",\n" +
                "\"priceList\": [10, null, null, null, null, null]\n" +
                "}";
        Assertions.assertThrows(JsonSyntaxException.class, () -> gson.fromJson(json, ProfitRequest.class));
    }

    @Test
    public void writeRequest() {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setIdentifier("123");
        LocalDateTime startTime = LocalDateTime.now().minusDays(1).withHour(11);
        LocalDateTime endTime = LocalDateTime.now().minusDays(1).withHour(15);
        profitRequest.setStartTime(startTime);
        profitRequest.setEndTime(endTime);
        profitRequest.setPriceList(Arrays.asList(100, -1, 0, 8, 8, null, null, 2));
        String json = gson.toJson(profitRequest);
        Assertions.assertNotNull(json);
        Assertions.assertTrue(json.contains("\"identifier\":\"123\""));
        Assertions.assertTrue(json.contains("\"startTime\":\""+ startTime.format(dateTimeFormatter) +"\""));
        Assertions.assertTrue(json.contains("\"endTime\":\""+ endTime.format(dateTimeFormatter) +"\""));
        Assertions.assertTrue(json.contains("\"priceList\":[100,-1,0,8,8,null,null,2]"));
    }
}
