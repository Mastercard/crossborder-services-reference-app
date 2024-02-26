package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = { "amount", "currency"})
public class QueuedBalance implements Serializable {
    private String queuedBalanceAmount;
    private String queuedBalanceCurrency;

    @JsonProperty(value = "amount")
    public String getQueuedBalanceAmount() {
        return queuedBalanceAmount;
    }

    public void setQueuedBalanceAmount(String queuedBalanceAmount) {
        this.queuedBalanceAmount = queuedBalanceAmount;
    }

    @JsonProperty(value = "currency")
    public String getQueuedBalanceCurrency() {
        return queuedBalanceCurrency;
    }

    public void setQueuedBalanceCurrency(String queuedBalanceCurrency) {
        this.queuedBalanceCurrency = queuedBalanceCurrency;
    }

    @Override
    public String toString() {
        return "QueuedBalance{" +
                "queuedBalanceAmount='" + queuedBalanceAmount + '\'' +
                ", queuedBalanceCurrency='" + queuedBalanceCurrency + '\'' +
                '}';
    }
}
