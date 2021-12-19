package com.latitude.genoapay.codingchallenge.dto.validator;

import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfitRequestNotNullValidatorTest {

    private ProfitRequestNotNullValidator profitRequestNotNullValidator = new ProfitRequestNotNullValidator();
    private ProfitRequest profitRequest;

    @BeforeEach
    public void setup() {
        profitRequest = new ProfitRequest();
        profitRequest.setIdentifier("identifier");
        profitRequest.setStartTime(LocalDateTime.of(2021, 12, 2, 10, 11));
        profitRequest.setEndTime(LocalDateTime.of(2021, 12, 9, 10, 11));
        profitRequest.setPriceList(Collections.singletonList(100));
    }

    @Test
    public void profitRequestValidated() throws IOException {
        profitRequestNotNullValidator.validate(profitRequest);
    }

    @Test
    public void profitRequestWithoutIdentifier() {
        profitRequest.setIdentifier(null);
        assertThrows(IOException.class, () -> profitRequestNotNullValidator.validate(profitRequest));
    }

    @Test
    public void profitRequestWithoutStartTime() {
        profitRequest.setStartTime(null);
        assertThrows(IOException.class, () -> profitRequestNotNullValidator.validate(profitRequest));
    }

    @Test
    public void profitRequestWithoutEndTime() {
        profitRequest.setEndTime(null);
        assertThrows(IOException.class, () -> profitRequestNotNullValidator.validate(profitRequest));
    }

    @Test
    public void profitRequestWithoutPriceList() {
        profitRequest.setPriceList(Lists.emptyList());
        assertThrows(IOException.class, () -> profitRequestNotNullValidator.validate(profitRequest));
    }
}
