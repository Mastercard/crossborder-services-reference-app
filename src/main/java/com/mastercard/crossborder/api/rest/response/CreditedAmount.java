package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "CreditedAmount")
@XmlType(name = "credited_amount", propOrder = {"crAmt", "crCurr"})
@XmlRootElement(name = "credited_amount")
public class CreditedAmount implements Serializable {

    private String crAmt;
    private String crCurr;

    @JsonProperty(value = "amount")
    @XmlElement(name = "amount", nillable = false)

    public String getCrAmt() {
        return crAmt;
    }

    public void setCrAmt(String crAmt) {
        this.crAmt = crAmt;
    }

    @JsonProperty(value = "currency")
    @XmlElement(name = "currency", nillable = false)
    public String getCrCurr() {
        return crCurr;
    }

    public void setCrCurr(String crCurr) {
        this.crCurr = crCurr;
    }

    @Override
    public String toString() {
        return "CreditedAmount{" +
                "currency='" + crCurr + '\'' +
                ",amount='" + crAmt + '\'' +
                '}';
    }

    public String toString(String fieldName) {
        return ","+ fieldName +
                "_currency='" + crCurr + "'," +
                fieldName + "_amount='" + crAmt + '\'' ;
    }
}
