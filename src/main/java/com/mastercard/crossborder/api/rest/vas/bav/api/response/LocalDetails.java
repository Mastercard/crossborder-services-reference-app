package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class LocalDetails {
    private String bankName;
    private String branchName;
    private String addressLine;
    private String city;

    public LocalDetails() {
    }

    public LocalDetails(String bankName, String branchName, String addressLine, String city) {
        this.bankName = bankName;
        this.branchName = branchName;
        this.addressLine = addressLine;
        this.city = city;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
