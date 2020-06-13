package com.ecit.domain;

public class Account {
    private String accountNo;

    private Double amount;

    private Double freezedAmount;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFreezedAmount() {
        return freezedAmount;
    }

    public void setFreezedAmount(Double freezedAmount) {
        this.freezedAmount = freezedAmount;
    }
}