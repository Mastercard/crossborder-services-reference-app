package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonPropertyOrder(value = {"feesIncluded", "receiverCurrency"})
@XmlType(name = "forward", propOrder = {"feesIncluded", "receiverCurrency"})
@XmlRootElement(name = "forward")
public class ForwardFees implements Serializable {

    private static final long serialVersionUID = 1L;

    public ForwardFees() {
        //default constructor
    }
    private String feesIncluded;

    private String receiverCurrency;

    @JsonProperty(value = "fees_included")
    @XmlElement(name = "fees_included", required = true, nillable = false)
    public String getFeesIncluded() {
        return feesIncluded;
    }

    public void setFeesIncluded(String feesIncluded) {
        this.feesIncluded = feesIncluded;
    }

    @JsonProperty(value = "receiver_currency")
    @XmlElement(name ="receiver_currency")
    public String getReceiverCurrency() {
        return receiverCurrency;
    }

    public void setReceiverCurrency(String receiverCurrency) {
        this.receiverCurrency = receiverCurrency;
    }


}

