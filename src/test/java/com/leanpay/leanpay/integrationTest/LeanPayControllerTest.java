package com.leanpay.leanpay.integrationTest;

import com.leanpay.leanpay.LeanpayApplication;
import com.leanpay.leanpay.dto.CalculationParamDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = LeanpayApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class LeanPayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculatonLeanPay() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/installment-plan")
                .accept(MediaType.APPLICATION_JSON)
                .content(String.valueOf(new CalculationParamDTO(20000.00F, 60, 5.00F)))
        ).andReturn();
    }
}
