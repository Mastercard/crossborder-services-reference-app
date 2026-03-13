package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.ToString;

@ToString
public class IBanValidationRequest {

    private AccountUri accountUri;

    public IBanValidationRequest() {
    }

    public IBanValidationRequest(AccountUri accountUri) {
        this.accountUri = accountUri;
    }

    public AccountUri getAccountUri() {
        return accountUri;
    }

    public void setAccountUri(AccountUri accountUri) {
        this.accountUri = accountUri;
    }

    @Override
    public String toString() {
        return "IBanValidationRequest{" +
                "accountUri=" + accountUri +
                '}';
    }
}
