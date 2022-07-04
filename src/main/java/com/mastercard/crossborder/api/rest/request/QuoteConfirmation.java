package com.mastercard.crossborder.api.rest.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "quoteconfirmation")
public class QuoteConfirmation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionReference;
    private String proposalId;

    @JsonProperty(value = "transactionReference")
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    @JsonProperty(value = "proposalId")
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }
}
