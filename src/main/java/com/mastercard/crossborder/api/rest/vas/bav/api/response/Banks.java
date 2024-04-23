package com.mastercard.crossborder.api.rest.vas.bav.api.response;

import java.util.List;

public class Banks {
    private List<BankData> bankData;

    public Banks() {
    }

    public Banks(List<BankData> banks) {
        this.bankData = banks;
    }

    public List<BankData> getBankData() {
        return bankData;
    }

    public void setBankData(List<BankData> banks) {
        this.bankData = banks;
    }
}
