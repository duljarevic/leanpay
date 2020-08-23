package com.leanpay.leanpay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotNull;

public class CalculationParamDTO {

    @NotNull(message = "amount has to be present")
    private Float amount;
    @NotNull(message = "numberOfMonths has to be present")
    private Integer numberOfMonths;
    @NotNull(message = "annualInterestPercent has to be present")
    private Float annualInterestPercent;

    public CalculationParamDTO() {
    }

    public CalculationParamDTO(Float amount, Integer numberOfMonths, Float annualInterestPercent) {
        this.amount = amount;
        this.numberOfMonths = numberOfMonths;
        this.annualInterestPercent = annualInterestPercent;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(Integer numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Float getAnnualInterestPercent() {
        return annualInterestPercent;
    }

    public void setAnnualInterestPercent(Float annualInterestPercent) {
        this.annualInterestPercent = annualInterestPercent;
    }
}
