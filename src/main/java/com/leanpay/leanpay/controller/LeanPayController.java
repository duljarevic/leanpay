package com.leanpay.leanpay.controller;

import com.leanpay.leanpay.dto.CalculationParamDTO;
import com.leanpay.leanpay.model.LoanSettlement;
import com.leanpay.leanpay.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LeanPayController {

    private final Logger log = LoggerFactory.getLogger(LeanPayController.class);

   private final CalculationService calculationService;

    public LeanPayController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/installment-plan")
    public ResponseEntity runCalculation(@Valid @RequestBody CalculationParamDTO calculationParamDTO) {
        log.debug("Request for runCalculation {}", calculationParamDTO);
        LoanSettlement result = calculationService.runCalculation(calculationParamDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
