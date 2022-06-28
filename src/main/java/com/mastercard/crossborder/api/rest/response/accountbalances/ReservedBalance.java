package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = { "amount", "currency"})
public class ReservedBalance implements Serializable {
    private String reservedBalanceAmount;
    private String reservedBalanceCurrency;

    @JsonProperty(value = "amount")
    public String getReservedBalanceAmount() {
        return reservedBalanceAmount;
    }

    public void setReservedBalanceAmount(String reservedBalanceAmount) {
        this.reservedBalanceAmount = reservedBalanceAmount;
    }

    @JsonProperty(value = "currency")
    public String getReservedBalanceCurrency() {
        return reservedBalanceCurrency;
    }

    public void setReservedBalanceCurrency(String reservedBalanceCurrency) {
        this.reservedBalanceCurrency = reservedBalanceCurrency;
    }

    @Override
    public String toString() {
        return "ReservedBalance{" +
                "reservedBalanceAmount='" + reservedBalanceAmount + '\'' +
                ", reservedBalanceCurrency='" + reservedBalanceCurrency + '\'' +
                '}';
    }
}
