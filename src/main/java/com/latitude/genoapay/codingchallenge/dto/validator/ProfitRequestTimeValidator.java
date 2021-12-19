package com.latitude.genoapay.codingchallenge.dto.validator;

import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProfitRequestTimeValidator extends ProfitJsonValidator {

    public static final LocalDateTime YESTERDAY_OPENING_TIME = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(10, 0));
    public static final LocalDateTime YESTERDAY_CLOSING_TIME = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(16, 0));

    @Override
    public void validate(ProfitRequest profitRequest) throws IOException {
        LocalDateTime startTime = profitRequest.getStartTime();
        LocalDateTime endTime = profitRequest.getEndTime();
        if(startTime.isAfter(endTime)) {
            throw new IOException("start time must before end time.");
        }

        if(startTime.isBefore(YESTERDAY_OPENING_TIME)) {
            throw new IOException("start time must after trade opening time.");
        }

        if(endTime.isAfter(YESTERDAY_CLOSING_TIME)) {
            throw new IOException("end time must before trade closing time.");
        }

        if(validatorSuccessor != null) {
            validatorSuccessor.validate(profitRequest);
        }
    }

}
