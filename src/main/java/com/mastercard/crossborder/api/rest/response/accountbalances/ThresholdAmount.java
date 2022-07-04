package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonPropertyOrder(value = { "amount", "currency"})
public class ThresholdAmount implements Serializable {
    private String thresholdAmt;
    private String thresholdAmtCurrency;

    @JsonProperty(value = "amount")
    public String getThresholdAmt() {
        return thresholdAmt;
    }

    public void setThresholdAmt(String thresholdAmt) {
        this.thresholdAmt = thresholdAmt;
    }

    @JsonProperty(value = "currency")
    public String getThresholdAmtCurrency() {
        return thresholdAmtCurrency;
    }

    public void setThresholdAmtCurrency(String thresholdAmtCurrency) {
        this.thresholdAmtCurrency = thresholdAmtCurrency;
    }

    @Override
    public String toString() {
        return "ThresholdAmount{" +
                "thresholdAmt='" + thresholdAmt + '\'' +
                ", thresholdAmtCurrency='" + thresholdAmtCurrency + '\'' +
                '}';
    }
}
