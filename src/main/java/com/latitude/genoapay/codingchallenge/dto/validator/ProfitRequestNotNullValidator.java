package com.latitude.genoapay.codingchallenge.dto.validator;

import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;

public class ProfitRequestNotNullValidator extends ProfitJsonValidator {

    @Override
    public void validate(ProfitRequest profitRequest) throws IOException {
        if(profitRequest == null) {
            throw new IOException("Invalid profitRequest object.");
        }

        if(profitRequest.getIdentifier() == null || profitRequest.getStartTime() == null
                || profitRequest.getEndTime() == null || CollectionUtils.isEmpty(profitRequest.getPriceList())) {
            throw new IOException("Invalid request body.");
        }

        if(validatorSuccessor != null) {
            validatorSuccessor.validate(profitRequest);
        }
    }
}
