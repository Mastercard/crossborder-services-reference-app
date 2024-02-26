package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class AccountMatch {
    private Accounts accounts;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private Bank bank;

    public AccountMatch(Accounts accounts) {
        this.accounts = accounts;
    }

    public AccountMatch() {
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

}
