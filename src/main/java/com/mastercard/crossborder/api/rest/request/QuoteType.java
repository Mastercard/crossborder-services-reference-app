package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonPropertyOrder(value = {"reverseFees", "forwardFees"})
@XmlType(name = "quote_type", propOrder = {"reverseFees", "forwardFees"})
@XmlRootElement(name = "quote_type")
public class QuoteType implements Serializable {

    private static final long serialVersionUID = 1L;

    public QuoteType() {
        //default constructor
    }

    private ReverseFees reverseFees;
    private ForwardFees forwardFees;

    @JsonProperty(value = "forward")
    @XmlElement(name = "forward", required = true, nillable = false)
    public ForwardFees getForwardFees() {
        return forwardFees;
    }

    public void setForwardFees(ForwardFees forwardFees) {
        this.forwardFees = forwardFees;
    }

    @JsonProperty(value = "reverse")
    @XmlElement(name = "reverse", required = true, nillable = false)
    public ReverseFees getReverseFees() {
        return reverseFees;
    }

    public void setReverseFees(ReverseFees reverseFees) {
        this.reverseFees = reverseFees;
    }

}
