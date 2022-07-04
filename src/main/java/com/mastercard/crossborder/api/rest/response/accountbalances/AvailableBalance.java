package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonPropertyOrder(value = { "amount", "currency"})
public class AvailableBalance implements Serializable {
    private String availableBalanceAmount;
    private String availableBalanceCurrency;

    @JsonProperty(value = "amount")
    public String getAvailableBalanceAmount() {
        return availableBalanceAmount;
    }

    public void setAvailableBalanceAmount(String availableBalanceAmount) {
        this.availableBalanceAmount = availableBalanceAmount;
    }

    @JsonProperty(value = "currency")
    public String getAvailableBalanceCurrency() {
        return availableBalanceCurrency;
    }

    public void setAvailableBalanceCurrency(String availableBalanceCurrency) {
        this.availableBalanceCurrency = availableBalanceCurrency;
    }

    @Override
    public String toString() {
        return "AvailableBalance{" +
                "availableBalanceAmount='" + availableBalanceAmount + '\'' +
                ", availableBalanceCurrency='" + availableBalanceCurrency + '\'' +
                '}';
    }
}
