package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BaseRequest", propOrder = { "transactionReference", "additionalField"})
public class BaseRequest {

    private static final long serialVersionUID = 1L;

    private String transactionReference;
    private AdditionalField additionalField;

    @JsonProperty(value = "additional_data")
    @XmlElement(name = "additional_data")
    public AdditionalField getAdditionalField() {
        return additionalField;
    }

    public void setAdditionalField(AdditionalField additionalField) {
        this.additionalField = additionalField;
    }

    @JsonProperty(value = "TransactionReference")
    @XmlElement(name = "TransactionReference", required = true)
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }


}
