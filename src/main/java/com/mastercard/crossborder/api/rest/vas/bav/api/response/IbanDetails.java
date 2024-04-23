package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class IbanDetails {
    Accounts accounts;
    Bank bank;

    public IbanDetails() {
    }

    public IbanDetails(Accounts accounts, Bank bank) {
        this.accounts = accounts;
        this.bank = bank;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "{" +
                "accounts=" + accounts +
                ", bank=" + bank +
                '}';
    }
}
