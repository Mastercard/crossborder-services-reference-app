package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"recipientUri", "senderUri", "paymentDateTime", "transactionReference" })
public class Transactions implements Serializable{

    private static final long serialVersionUID = 1L;

    private String recipientUri;
    private String senderUri;
    private String paymentDateTime;
    private String transactionReference;

    @JsonProperty(value = "recipientUri")
    public String getRecipientUri() {
        return recipientUri;
    }

    public void setRecipientUri(String recipientUri) {
        this.recipientUri = recipientUri;
    }


    @JsonProperty(value = "senderUri")
    public String getSenderUri() {
        return senderUri;
    }

    public void setSenderUri(String senderUri) {
        this.senderUri = senderUri;
    }

    @JsonProperty(value = "paymentDateTime")
    public String getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(String paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    @JsonProperty(value = "transactionReference")
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }





}
