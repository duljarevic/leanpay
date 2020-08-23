package com.leanpay.leanpay.service;

import com.leanpay.leanpay.dto.CalculationParamDTO;
import com.leanpay.leanpay.model.LoanSettlement;

public interface CalculationService {

    LoanSettlement runCalculation(CalculationParamDTO calculationParamDTO);
}
