package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonPropertyOrder(value = {"currency", "amount"})
@XmlType(name = "amount", propOrder = {"currency", "amount"})
@XmlRootElement(name = "amount")
public class Amount implements Serializable {

    private static final long serialVersionUID = 1L;

    private String currency;
    private String amount;

    @JsonProperty(value = "currency")
    @XmlElement(name = "currency", required = true)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty(value = "amount")
    @XmlElement(name = "amount", required = true)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}