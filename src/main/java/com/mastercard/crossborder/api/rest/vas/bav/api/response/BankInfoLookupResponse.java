package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class BankInfoLookupResponse {

    BankInfoResponse bankInfo;

    public BankInfoLookupResponse(){
    }

    public BankInfoResponse getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfoResponse bankInfo) {
        this.bankInfo = bankInfo;
    }

    @Override
    public String toString() {
        return "BankInfoLookupResponse{" +
                "bankInfo=" + bankInfo +
                '}';
    }
}
