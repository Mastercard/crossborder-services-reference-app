package com.mastercard.crossborder.api.rest.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"senderRecipientRelation", "paymentPurpose", "additionalQuestion", "supportingDocs", "additionalDocuments"})
public class PaymentAndDocs implements Serializable {

    private static final long serialVersionUID = 1L;

    private String senderRecipientRelation;
    private String paymentPurpose;
    private String additionalQuestion;
    private String supportingDocs;
    private String additionalDocuments;

    @JsonProperty(value = "senderRecipientRelation")
    public String getSenderRecipientRelation() {
        return senderRecipientRelation;
    }

    public void setSenderRecipientRelation(String senderRecipientRelation) {
        this.senderRecipientRelation = senderRecipientRelation;
    }

    @JsonProperty(value = "paymentPurpose")
    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    @JsonProperty(value = "additionalQuestion")
    public String getAdditionalQuestion() {
        return additionalQuestion;
    }

    public void setAdditionalQuestion(String additionalQuestion) {
        this.additionalQuestion = additionalQuestion;
    }

    @JsonProperty(value = "supportingDocs")
    public String getSupportingDocs() {
        return supportingDocs;
    }

    public void setSupportingDocs(String supportingDocs) {
        this.supportingDocs = supportingDocs;
    }

    @JsonProperty(value = "additionalDocuments")
    public String getAdditionalDocuments() {
        return additionalDocuments;
    }

    public void setAdditionalDocuments(String additionalDocuments) {
        this.additionalDocuments = additionalDocuments;
    }
}
