package com.leanpay.leanpay.service.impl;

import com.leanpay.leanpay.dto.CalculationParamDTO;
import com.leanpay.leanpay.model.Items;
import com.leanpay.leanpay.model.LoanSettlement;
import com.leanpay.leanpay.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.leanpay.leanpay.repository.CalculationRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("calculationService")
@Transactional
public class CalculationServiceImpl implements CalculationService {

    private final Logger log = LoggerFactory.getLogger(CalculationServiceImpl.class);

    private CalculationRepository calculationRepository;

    public CalculationServiceImpl(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    @Override
    public LoanSettlement runCalculation(CalculationParamDTO calculationParamDTO) {
        log.debug("Service for runCalculation {}", calculationParamDTO);
        List<Items> listItems = new ArrayList<>();
        double interestRatePerMonth = calculationParamDTO.getAnnualInterestPercent() / 100 / 12;
        Double payment = calculationParamDTO.getAmount() * interestRatePerMonth * Math.pow((1 + interestRatePerMonth), calculationParamDTO.getNumberOfMonths()) / (Math.pow((1 + interestRatePerMonth), calculationParamDTO.getNumberOfMonths())-1);

        LoanSettlement loanSettlement = new LoanSettlement();
        loanSettlement.setAmount(calculationParamDTO.getAmount());
        loanSettlement.setTotalAmount(Math.round((payment * calculationParamDTO.getNumberOfMonths()) * 100.0) / 100.0);
        loanSettlement.setInterestAmount(Math.round(((payment * calculationParamDTO.getNumberOfMonths()) - calculationParamDTO.getAmount()) * 100.0) / 100.0);
        calculationRepository.save(loanSettlement);
        for (int i = 0; i < calculationParamDTO.getNumberOfMonths() ; i++) {
            Items items = new Items();
            items.setMonth(i);
            items.setPaymentAmount(Math.round((payment) * 100.0) / 100.0);
            items.setLoanSettlement(loanSettlement);
            listItems.add(items);
        }
        loanSettlement.setItems(listItems);
        calculationRepository.save(loanSettlement);
        return loanSettlement;
    }
}
