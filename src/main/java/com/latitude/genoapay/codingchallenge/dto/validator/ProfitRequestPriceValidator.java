package com.latitude.genoapay.codingchallenge.dto.validator;

import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProfitRequestPriceValidator extends ProfitJsonValidator {

    @Override
    public void validate(ProfitRequest profitRequest) throws IOException {
        LocalDateTime startTime = profitRequest.getStartTime();
        LocalDateTime endTime = profitRequest.getEndTime();
        int tradeTotalMinutes = (int) (Duration.between(startTime, endTime).getSeconds() / 60) + 1;

        if(profitRequest.getPriceList().size() != tradeTotalMinutes) {
            throw new IOException("Trading minutes not match the price list.");
        }

        if(profitRequest.getPriceList().stream().allMatch(Objects::isNull)) {
            throw new IOException("There must be a price at least.");
        }
        if(validatorSuccessor != null) {
            validatorSuccessor.validate(profitRequest);
        }
    }

}
