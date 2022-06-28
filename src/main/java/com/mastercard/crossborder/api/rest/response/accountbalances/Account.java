package com.mastercard.crossborder.api.rest.response.accountbalances;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = { "accountId", "settlementCurrency", "accountState","openingBalanceTimestamp", "balanceAsOfTimestamp", "balanceDetails"})
public class Account implements Serializable {
    private String accountId;
    private String settlementCurrency;
    private String accountState;
    private String openingBalanceTimestamp;
    private String balanceAsOfTimestamp;
    private BalanceDetails balanceDetails;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getOpeningBalanceTimestamp() {
        return openingBalanceTimestamp;
    }

    public void setOpeningBalanceTimestamp(String openingBalanceTimestamp) {
        this.openingBalanceTimestamp = openingBalanceTimestamp;
    }

    public String getBalanceAsOfTimestamp() {
        return balanceAsOfTimestamp;
    }

    public void setBalanceAsOfTimestamp(String balanceAsOfTimestamp) {
        this.balanceAsOfTimestamp = balanceAsOfTimestamp;
    }

    public BalanceDetails getBalanceDetails() {
        return balanceDetails;
    }

    public void setBalanceDetails(BalanceDetails balanceDetails) {
        this.balanceDetails = balanceDetails;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                "settlementCurrency='" + settlementCurrency + '\'' +
                ", accountState='" + accountState + '\'' +
                ", openingBalanceTimestamp='" + openingBalanceTimestamp + '\'' +
                ", balanceAsOfTimestamp='" + balanceAsOfTimestamp + '\'' +
                ", balanceDetails=" + balanceDetails +
                '}';
    }
}
