package com.mastercard.crossborder.api.rest.request;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "QuotesRequest", propOrder = { "proposalReference", "senderAccountUri", "recipientAccountUri", "quoteType", "remittanceAmount", "originatingCountry", "paymentType", "bankCode"})
@XmlRootElement(name = "quoterequest")
public class QuotesRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public QuotesRequest() {
        //default constructor
    }

    private String senderAccountUri;
    private String recipientAccountUri;
    private Amount remittanceAmount;
    private QuoteType quoteType;
    private String originatingCountry;
    private String paymentType;
    private String proposalReference;
    private String bankCode;

    @XmlElement(name = "transaction_reference", required = true, nillable = false)
    public String getProposalReference() {
        return proposalReference;
    }

    public void setProposalReference(String proposalReference) {
        this.proposalReference = proposalReference;
    }


    @XmlElement(name = "sender_account_uri", required = true, nillable = false)
    public String getSenderAccountUri() {
        return senderAccountUri;
    }

    public void setSenderAccountUri(String senderAccountUri) {
        this.senderAccountUri = senderAccountUri;
    }

    @XmlElement(name = "recipient_account_uri", required = true, nillable = false)
    public String getRecipientAccountUri() {
        return recipientAccountUri;
    }

    public void setRecipientAccountUri(String recipientAccountUri) {
        this.recipientAccountUri = recipientAccountUri;
    }

    @XmlElement(name = "payment_amount", required = true, nillable = false)
    public Amount getRemittanceAmount() {
        return remittanceAmount;
    }

    public void setRemittanceAmount(Amount remittanceAmount) {
        this.remittanceAmount = remittanceAmount;
    }

    @XmlElement(name = "quote_type", required = true, nillable = false)
    public QuoteType getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    @XmlElement(name = "payment_origination_country", required = false, nillable = true)
    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    @XmlElement(name="payment_type", required = true, nillable = false)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @XmlElement(name="bank_code", required = false, nillable = true)
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }


}

