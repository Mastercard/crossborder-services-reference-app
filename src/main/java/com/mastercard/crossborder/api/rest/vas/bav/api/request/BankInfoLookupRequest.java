package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.ToString;

@ToString
public class BankInfoLookupRequest {
    private Bank bank;

    public BankInfoLookupRequest() {
    }

    public BankInfoLookupRequest(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
