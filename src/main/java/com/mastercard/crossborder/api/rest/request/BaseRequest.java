package com.mastercard.crossborder.api.rest.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BaseRequest", propOrder = { "transactionReference", "additionalField"})
public class BaseRequest {

    private static final long serialVersionUID = 1L;

    public BaseRequest() {
        //default constructor
    }

    private String transactionReference;
    private AdditionalField additionalField;


    @XmlElement(name = "additional_data", required = false, nillable = false)
    public AdditionalField getAdditionalField() {
        return additionalField;
    }

    public void setAdditionalField(AdditionalField additionalField) {
        this.additionalField = additionalField;
    }


    @XmlElement(name = "transaction_reference", required = true, nillable = false)
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }


}
