package com.latitude.genoapay.codingchallenge.dto.validator;

import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfitRequestPriceValidatorTest {

    private ProfitRequestPriceValidator profitRequestPriceValidator = new ProfitRequestPriceValidator();

    @Test
    public void normalPriceList() throws IOException {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.of(2021, 12, 11, 1, 0));
        profitRequest.setEndTime(LocalDateTime.of(2021, 12, 11, 1, 5));
        Integer [] priceArr = { 2, 0, -1, null, 3, 3 };
        profitRequest.setPriceList(Arrays.asList(priceArr));

        profitRequestPriceValidator.validate(profitRequest);
    }

    @Test
    public void pricesNotMatchTradingMinutes() {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.of(2021, 12, 11, 1, 0));
        profitRequest.setEndTime(LocalDateTime.of(2021, 12, 11, 1, 5));
        Integer [] priceArr = { 2, null, 0, -1, null, 3, 1 };
        List<Integer> priceList = new ArrayList<>(Arrays.asList(priceArr));
        profitRequest.setPriceList(priceList);

        assertThrows(IOException.class, () -> profitRequestPriceValidator.validate(profitRequest));
    }

    @Test
    public void pricesAllNullValue() {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.of(2021, 12, 11, 1, 0));
        profitRequest.setEndTime(LocalDateTime.of(2021, 12, 11, 1, 5));
        Integer [] priceArr = { null, null, null, null, null};
        List<Integer> priceList = new ArrayList<>(Arrays.asList(priceArr));
        profitRequest.setPriceList(priceList);

        assertThrows(IOException.class, () -> profitRequestPriceValidator.validate(profitRequest));
    }
}
