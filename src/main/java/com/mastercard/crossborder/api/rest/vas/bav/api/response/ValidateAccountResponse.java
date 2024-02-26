package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class ValidateAccountResponse {
    private String status;
    private String message;
    private AccountMatch accountMatch;

    public ValidateAccountResponse() {
    }

    public ValidateAccountResponse(String status, String message, AccountMatch accountMatch, Bank bank) {
        this.status = status;
        this.message = message;
        this.accountMatch = accountMatch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AccountMatch getAccountMatch() {
        return accountMatch;
    }

    public void setAccountMatch(AccountMatch accountMatch) {
        this.accountMatch = accountMatch;
    }

}
