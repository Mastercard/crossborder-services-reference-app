package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class BankInfoResponse {
    private String total;
    private Banks banks;

    public BankInfoResponse() {
    }

    public BankInfoResponse(String total, Banks banks) {
        this.total = total;
        this.banks = banks;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Banks getBanks() {
        return banks;
    }

    public void setBanks(Banks banks) {
        this.banks = banks;
    }
}
