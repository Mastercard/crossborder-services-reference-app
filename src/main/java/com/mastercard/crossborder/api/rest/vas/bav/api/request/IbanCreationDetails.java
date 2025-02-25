package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.ToString;

@ToString
public class IbanCreationDetails {
    private AccountUri accountUri;
    private String country;
    private String branchCode;
    private String accountNo;

    public IbanCreationDetails() {
    }

    public IbanCreationDetails(AccountUri accountUri, String country, String branchCode, String accountNo) {
        this.accountUri = accountUri;
        this.country = country;
        this.branchCode = branchCode;
        this.accountNo = accountNo;
    }

    public AccountUri getAccountUri() {
        return accountUri;
    }

    public void setAccountUri(AccountUri accountUri) {
        this.accountUri = accountUri;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
