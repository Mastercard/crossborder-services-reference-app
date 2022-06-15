package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "ChargedAmount")
@XmlType(name = "charged_amount", propOrder = {"chrgAmt", "chrgCurr"})
@XmlRootElement(name = "charged_amount")
public class ChargedAmount implements Serializable {

    private String chrgAmt;
    private String chrgCurr;

    @JsonProperty(value = "amount")
    @XmlElement(name = "amount", nillable = false)
    public String getChrgAmt() {
        return chrgAmt;
    }

    public void setChrgAmt(String chrgAmt) {
        this.chrgAmt = chrgAmt;
    }

    @JsonProperty(value = "currency")
    @XmlElement(name = "currency", nillable = false)
    public String getChrgCurr() {
        return chrgCurr;
    }

    public void setChrgCurr(String chrgCurr) {
        this.chrgCurr = chrgCurr;
    }

    @Override
    public String toString() {
        return "ChargedAmount{" +
                "currency='" + chrgCurr + '\'' +
                ",amount='" + chrgAmt + '\'' +
                '}';
    }

    public String toString(String fieldName) {
        return ","+ fieldName +
                "_currency='" + chrgCurr + "'," +
                fieldName + "_amount='" + chrgAmt + '\'' ;
    }

}
