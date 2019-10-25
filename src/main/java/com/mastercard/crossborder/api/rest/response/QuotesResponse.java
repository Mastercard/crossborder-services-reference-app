package com.mastercard.crossborder.api.rest.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "QuotesResponse", propOrder = {"proposalReference","paymentType", "proposals"})
@XmlRootElement(name = "quote")
public class QuotesResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public QuotesResponse() {
        //Default constructor
    }
    private String proposalReference;
    private Proposals proposals;
    private String paymentType;

    @XmlElement(name = "transaction_reference", required = true, nillable = false)
    public String getProposalReference() {
        return proposalReference;
    }

    public void setProposalReference(String proposalReference) {
        this.proposalReference = proposalReference;
    }

    @XmlElement(name="payment_type", required = true, nillable = false)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @XmlElement(name = "proposals")
    public Proposals getProposals() {
        return proposals;
    }

    public void setProposals(Proposals value) {
        this.proposals = value;
    }

}
