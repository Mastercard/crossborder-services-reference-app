package com.mastercard.crossborder.api.rest.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "updateRequest")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonPropertyOrder(value = {"sender", "recipient", "paymentAndDocs", "other" })
public class UpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Sender sender;
    private Recipient recipient;
    private PaymentAndDocs paymentAndDocs;
    private Other other;

    @JsonProperty(value = "sender")
    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @JsonProperty(value = "recipient")
    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    @JsonProperty(value = "paymentAndDocs")
    public PaymentAndDocs getPaymentAndDocs() {
        return paymentAndDocs;
    }

    public void setPaymentAndDocs(PaymentAndDocs paymentAndDocs) {
        this.paymentAndDocs = paymentAndDocs;
    }

    @JsonProperty(value = "other")
    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }
}
