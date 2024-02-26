package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import com.mastercard.crossborder.api.rest.vas.bav.api.request.Bank;
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
