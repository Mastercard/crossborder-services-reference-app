package com.mastercard.crossborder.api.rest.vas.bav.api.response;

import com.mastercard.crossborder.api.rest.vas.bav.api.request.Account;

import java.util.List;

public class Accounts {

    private List<Account> account;

    public Accounts() {
    }

    public Accounts(List<Account> account) {
        this.account = account;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}
