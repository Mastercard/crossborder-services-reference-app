package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonPropertyOrder(value = {"senderCurrency"})
@XmlType(name = "reverse", propOrder = {"senderCurrency"})
@XmlRootElement(name = "reverse")
public class ReverseFees implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReverseFees() {
        //default constructor
    }
    private String senderCurrency;


    @JsonProperty(value = "sender_currency")
    @XmlElement(name = "sender_currency", required = true, nillable = false)
    public String getSenderCurrency() {
        return senderCurrency;
    }

    public void setSenderCurrency(String senderCurrency) {
        this.senderCurrency = senderCurrency;
    }

}
