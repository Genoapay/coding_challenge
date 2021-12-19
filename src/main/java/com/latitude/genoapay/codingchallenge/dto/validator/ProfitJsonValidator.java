package com.latitude.genoapay.codingchallenge.dto.validator;

import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;

import java.io.IOException;

public abstract class ProfitJsonValidator {

    protected ProfitJsonValidator validatorSuccessor;

    public ProfitJsonValidator setValidatorSuccessor(ProfitJsonValidator validatorSuccessor) {
        this.validatorSuccessor = validatorSuccessor;
        return validatorSuccessor;
    }

    abstract public void validate(ProfitRequest profitRequest) throws IOException;
}
