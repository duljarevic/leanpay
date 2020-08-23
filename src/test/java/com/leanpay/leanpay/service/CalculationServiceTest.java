package com.leanpay.leanpay.service;

import com.leanpay.leanpay.dto.CalculationParamDTO;
import com.leanpay.leanpay.model.Items;
import com.leanpay.leanpay.model.LoanSettlement;
import com.leanpay.leanpay.repository.CalculationRepository;
import com.leanpay.leanpay.service.impl.CalculationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculationServiceTest {

    private CalculationService calculationService;

    @Mock
    private CalculationRepository calculationRepository;

    @BeforeEach
    public void init() {
        calculationService = new CalculationServiceImpl(calculationRepository);
    }

    @Test
    public void shouldReturnLoanSettlement(){
        mockLoanSettlement();
        LoanSettlement result = calculationService.runCalculation(new CalculationParamDTO(20000.00F, 60, 5.00F));
        assertNotNull(result);
        assertTrue(result.getAmount() == 20000.0);
        assertTrue(result.getTotalAmount() == 22645.48);
        assertTrue(result.getItems().size() == 60);
    }

    private void mockLoanSettlement() {
        List<Items> listItems = new ArrayList<>();
        LoanSettlement loanSettlement = new LoanSettlement();
        loanSettlement.setId(1L);
        loanSettlement.setAmount(20000F);
        loanSettlement.setTotalAmount(22645.48);
        loanSettlement.setInterestAmount(2645.48);
        for (int i = 0; i < 60; i++) {
            Items items = new Items();
            items.setId(1L);
            items.setMonth(i);
            items.setPaymentAmount(377.42);
            items.setLoanSettlement(loanSettlement);
            listItems.add(items);
        }
        loanSettlement.setItems(listItems);
        when(calculationRepository
                .findOne(Mockito.any())).thenReturn(java.util.Optional.of(loanSettlement));
    }
}
