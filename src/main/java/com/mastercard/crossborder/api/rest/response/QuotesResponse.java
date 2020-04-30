package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "quote")
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonPropertyOrder(value = {"proposalReference","paymentType", "proposals"})
@XmlType(name = "QuotesResponse", propOrder = {"proposalReference","paymentType", "proposals"})
@XmlRootElement(name = "quote")
public class QuotesResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String proposalReference;
    private Proposals proposals;
    private String paymentType;

    @JsonProperty(value = "transaction_reference")
    @XmlElement(name = "transaction_reference", required = true)
    public String getProposalReference() {
        return proposalReference;
    }

    public void setProposalReference(String proposalReference) {
        this.proposalReference = proposalReference;
    }

    @JsonProperty(value = "payment_type")
    @XmlElement(name="payment_type", required = true)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty(value = "proposals")
    @XmlElement(name = "proposals")
    public Proposals getProposals() {
        return proposals;
    }

    public void setProposals(Proposals value) {
        this.proposals = value;
    }

}
