package com.leanpay.leanpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


/**
 * A Items.
 */
@Entity
@Table(name = "items")
public class Items implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "month")
    private int month;
    @Column(name = "payment_amount")
    private Double paymentAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private LoanSettlement loanSettlement;

    public Items() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LoanSettlement getLoanSettlement() {
        return loanSettlement;
    }

    public void setLoanSettlement(LoanSettlement loanSettlement) {
        this.loanSettlement = loanSettlement;
    }

}
