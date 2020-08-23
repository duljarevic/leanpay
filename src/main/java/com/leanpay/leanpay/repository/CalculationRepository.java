package com.leanpay.leanpay.repository;

import com.leanpay.leanpay.model.LoanSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalculationRepository extends JpaRepository<LoanSettlement, Long> {

}
