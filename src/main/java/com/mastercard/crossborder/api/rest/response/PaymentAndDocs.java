package com.mastercard.crossborder.api.rest.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;


import java.io.Serializable;
@JsonTypeName(value = "paymentAndDocs")
@JsonPropertyOrder(value = {"additionalDocuments","paymentPurpose","senderRecipientRelation","supportingDocs", "additionalQuestion" })
public class PaymentAndDocs implements Serializable {

    private static final long serialVersionUID = 1L;


    private SenderRecipientRelation senderRecipientRelation;
    private PaymentPurpose paymentPurpose;
    private SupportingDocs supportingDocs;
    private AdditionalDocuments additionalDocuments;
    private AdditionalQuestion additionalQuestion;

    @JsonProperty(value = "senderRecipientRelation")
    public SenderRecipientRelation getSenderRecipientRelation() {
        return senderRecipientRelation;
    }

    public void setSenderRecipientRelation(SenderRecipientRelation senderRecipientRelation) {
        this.senderRecipientRelation = senderRecipientRelation;
    }

    @JsonProperty(value = "paymentPurpose")
    public PaymentPurpose getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(PaymentPurpose paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    @JsonProperty(value = "supportingDocs")
    public SupportingDocs getSupportingDocs() {
        return supportingDocs;
    }

    public void setSupportingDocs(SupportingDocs supportingDocs) {
        this.supportingDocs = supportingDocs;
    }

    @JsonProperty(value = "additionalDocuments")
    public AdditionalDocuments getAdditionalDocuments() {
        return additionalDocuments;
    }

    public void setAdditionalDocuments(AdditionalDocuments additionalDocuments) {
        this.additionalDocuments = additionalDocuments;
    }

    @JsonProperty(value = "additionalQuestion")
    public AdditionalQuestion getAdditionalQuestion() {
        return additionalQuestion;
    }

    public void setAdditionalQuestion(AdditionalQuestion additionalQuestion) {
        this.additionalQuestion = additionalQuestion;
    }
}
