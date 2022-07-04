package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "cancelConfirmedQuoteResponse")
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonPropertyOrder(value = {"transactionReference","status", "proposalId"})
public class CancelConfirmedQuoteResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionReference;
    private String  status;
    private String proposalId;

    @JsonProperty(value = "transactionReference")
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    @JsonProperty(value = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty(value = "proposalId")
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }
}
