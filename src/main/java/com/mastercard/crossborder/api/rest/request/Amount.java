package com.mastercard.crossborder.api.rest.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "amount", propOrder = {"currency", "amount"})
@XmlRootElement(name = "amount")
public class Amount implements Serializable {

    private static final long serialVersionUID = 1L;

    public Amount() {
        //default constructor
    }
    private String currency;
    private String amount;

    @XmlElement(name = "currency", required = true, nillable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlElement(name = "amount", required = true, nillable = false)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}