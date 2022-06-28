package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = { "amount", "currency"})
public class ProcessedAmount implements Serializable {
    private String processedAmt;
    private String processedAmtCurrency;

    @JsonProperty(value = "amount")
    public String getProcessedAmt() {
        return processedAmt;
    }

    public void setProcessedAmt(String processedAmt) {
        this.processedAmt = processedAmt;
    }

    @JsonProperty(value = "currency")
    public String getProcessedAmtCurrency() {
        return processedAmtCurrency;
    }

    public void setProcessedAmtCurrency(String processedAmtCurrency) {
        this.processedAmtCurrency = processedAmtCurrency;
    }

    @Override
    public String toString() {
        return "ProcessedAmount{" +
                "processedAmt='" + processedAmt + '\'' +
                ", processedAmtCurrency='" + processedAmtCurrency + '\'' +
                '}';
    }
}
