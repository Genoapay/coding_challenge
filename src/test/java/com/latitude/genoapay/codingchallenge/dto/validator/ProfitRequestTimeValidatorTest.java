package com.latitude.genoapay.codingchallenge.dto.validator;

import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfitRequestTimeValidatorTest {

    private ProfitRequestTimeValidator profitRequestTimeValidator = new ProfitRequestTimeValidator();

    @Test
    public void normalTime() throws IOException {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.now().minusDays(1).withHour(10));
        profitRequest.setEndTime(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(16, 0)));
        profitRequestTimeValidator.validate(profitRequest);
    }

    @Test
    public void startTimerAfterEndTimer() {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.of(2021, 12, 11, 3, 0));
        profitRequest.setEndTime(LocalDateTime.of(2021, 12, 10, 3, 0));
        assertThrows(IOException.class, () -> profitRequestTimeValidator.validate(profitRequest));
    }

    @Test
    public void earlyStartTime() {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.now().minusDays(1).withHour(9));
        profitRequest.setEndTime(LocalDateTime.now().minusDays(1).withHour(15));
        assertThrows(IOException.class, () -> profitRequestTimeValidator.validate(profitRequest));
    }

    @Test
    public void lateEndTime() {
        ProfitRequest profitRequest = new ProfitRequest();
        profitRequest.setStartTime(LocalDateTime.now().minusDays(1).withHour(11));
        profitRequest.setEndTime(LocalDateTime.now().minusDays(1).withHour(16).withMinute(1));
        assertThrows(IOException.class, () -> profitRequestTimeValidator.validate(profitRequest));
    }
}
