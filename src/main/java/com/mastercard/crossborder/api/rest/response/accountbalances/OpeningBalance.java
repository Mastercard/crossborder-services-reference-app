package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = { "amount", "currency"})
public class OpeningBalance implements Serializable {
    private String openingBalanceAmount;
    private String openingBalanceCurrency;

    @JsonProperty(value = "amount")
    public String getOpeningBalanceAmount() {
        return openingBalanceAmount;
    }

    public void setOpeningBalanceAmount(String openingBalanceAmount) {
        this.openingBalanceAmount = openingBalanceAmount;
    }

    @JsonProperty(value = "currency")
    public String getOpeningBalanceCurrency() {
        return openingBalanceCurrency;
    }

    public void setOpeningBalanceCurrency(String openingBalanceCurrency) {
        this.openingBalanceCurrency = openingBalanceCurrency;
    }

    @Override
    public String toString() {
        return "OpeningBalance{" +
                "openingBalanceAmount='" + openingBalanceAmount + '\'' +
                ", openingBalanceCurrency='" + openingBalanceCurrency + '\'' +
                '}';
    }
}
