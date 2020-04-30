package com.mastercard.crossborder.api.rest.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "quoterequest")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@XmlType(name = "QuotesRequest", propOrder = { "proposalReference", "senderAccountUri", "recipientAccountUri", "quoteType", "remittanceAmount", "originatingCountry", "paymentType", "bankCode"})
@XmlRootElement(name = "quoterequest")
public class QuotesRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String senderAccountUri;
    private String recipientAccountUri;
    private Amount remittanceAmount;
    private QuoteType quoteType;
    private String originatingCountry;
    private String paymentType;
    private String proposalReference;
    private String bankCode;

    @JsonProperty(value = "transaction_reference", required = true)
    @XmlElement(name = "transaction_reference", required = true)
    public String getProposalReference() {
        return proposalReference;
    }

    public void setProposalReference(String proposalReference) {
        this.proposalReference = proposalReference;
    }


    @JsonProperty(value = "sender_account_uri")
    @XmlElement(name = "sender_account_uri", required = true)
    public String getSenderAccountUri() {
        return senderAccountUri;
    }

    public void setSenderAccountUri(String senderAccountUri) {
        this.senderAccountUri = senderAccountUri;
    }

    @JsonProperty(value = "recipient_account_uri")
    @XmlElement(name = "recipient_account_uri", required = true)
    public String getRecipientAccountUri() {
        return recipientAccountUri;
    }

    public void setRecipientAccountUri(String recipientAccountUri) {
        this.recipientAccountUri = recipientAccountUri;
    }

    @JsonProperty(value = "payment_amount")
    @XmlElement(name = "payment_amount", required = true)
    public Amount getRemittanceAmount() {
        return remittanceAmount;
    }

    public void setRemittanceAmount(Amount remittanceAmount) {
        this.remittanceAmount = remittanceAmount;
    }

    @JsonProperty(value = "quote_type")
    @XmlElement(name = "quote_type", required = true)
    public QuoteType getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    @JsonProperty(value = "payment_origination_country")
    @XmlElement(name = "payment_origination_country", nillable = true)
    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    @JsonProperty(value = "payment_type")
    @XmlElement(name="payment_type", required = true)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty(value = "bank_code")
    @XmlElement(name="bank_code", nillable = true)
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

}
