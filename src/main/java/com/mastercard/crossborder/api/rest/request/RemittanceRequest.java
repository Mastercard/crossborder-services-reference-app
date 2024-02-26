package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonTypeName(value = "paymentrequest")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@XmlType(name = "PaymentRequest", propOrder = {"remittanceReference", "proposalId", "localDateTime","senderAccountUri","recipientAccountUri","paymentAmount","originatingCountry","fxType","receivingBankName","receivingBankBranchName","bankCode","paymentType","sourceOfIncome","senderInformation", "receiverInformation","purposeOfRemittance","fundingHints", "paymentFileIdentifier", "cardRateId","lockId"})
@XmlRootElement(name = "paymentrequest")
public class RemittanceRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    private String remittanceReference;
    private String proposalId;
    private String receivingBankName;
    private String receivingBankBranchName;
    private String sourceOfIncome;
    private CustomerData senderInformation;
    private CustomerData receiverInformation;
    private AdditionalDataData fundingHints;
    private String localDateTime;
    private String purposeOfRemittance;

    private String paymentType;
    private String paymentFileIdentifier;
    private String senderAccountUri;
    private String recipientAccountUri;
    private Amount paymentAmount;
    private String originatingCountry;
    private String bankCode;
    private QuoteType fxType;
    private String cardRateId;
    private String lockId;

    @JsonProperty(value = "transaction_reference")
    @XmlElement(name = "transaction_reference", required = true)
    public String getRemittanceReference() {
        return remittanceReference;
    }

    public void setRemittanceReference(String remittanceReference) {
        this.remittanceReference = remittanceReference;
    }

    @JsonProperty(value = "sender")
    @XmlElement(name = "sender")
    public CustomerData getSenderInformation() {
        return senderInformation;
    }

    public void setSenderInformation(CustomerData senderInformation) {
        this.senderInformation = senderInformation;
    }

    @JsonProperty(value = "recipient")
    @XmlElement(name = "recipient")
    public CustomerData getReceiverInformation() {
        return receiverInformation;
    }

    public void setReceiverInformation(CustomerData receiverInformation) {
        this.receiverInformation = receiverInformation;
    }

    @JsonProperty(value = "proposal_id")
    @XmlElement(name = "proposal_id", required = true)
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    @JsonProperty(value = "funding_hints")
    @XmlElement(name = "funding_hints")
    public AdditionalDataData getFundingHints() {
        return fundingHints;
    }

    public void setFundingHints(AdditionalDataData fundingHints) {
        this.fundingHints = fundingHints;
    }

    @JsonProperty(value = "local_date_time")
    @XmlElement(name = "local_date_time", required = true)
    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    @JsonProperty(value = "purpose_of_payment")
    @XmlElement(name="purpose_of_payment")
    public String getPurposeOfRemittance() {
        return purposeOfRemittance;
    }

    public void setPurposeOfRemittance(String purposeOfRemittance) {
        this.purposeOfRemittance = purposeOfRemittance;
    }

    @JsonProperty(value = "source_of_income")
    @XmlElement(name = "source_of_income")
    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    @JsonProperty(value = "receiving_bank_name")
    @XmlElement(name = "receiving_bank_name")
    public String getReceivingBankName() {
        return receivingBankName;
    }

    public void setReceivingBankName(String receivingBankName) {
        this.receivingBankName = receivingBankName;
    }

    @JsonProperty(value = "receiving_bank_branch_name")
    @XmlElement(name = "receiving_bank_branch_name")
    public String getReceivingBankBranchName() {
        return receivingBankBranchName;
    }

    public void setReceivingBankBranchName(String receivingBankBranchName) {
        this.receivingBankBranchName = receivingBankBranchName;
    }

    @JsonProperty(value = "payment_type")
    @XmlElement(name = "payment_type")
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty(value = "payment_file_identifier")
    @XmlElement(name = "payment_file_identifier")
    public String getPaymentFileIdentifier() {
        return paymentFileIdentifier;
    }

    public void setPaymentFileIdentifier(String paymentFileIdentifier) {
        this.paymentFileIdentifier = paymentFileIdentifier;
    }

    @JsonProperty(value = "sender_account_uri")
    @XmlElement(name="sender_account_uri")
    public String getSenderAccountUri() {
        return senderAccountUri;
    }

    public void setSenderAccountUri(String senderAccountUri) {
        this.senderAccountUri = senderAccountUri;
    }

    @JsonProperty(value = "recipient_account_uri")
    @XmlElement(name="recipient_account_uri")
    public String getRecipientAccountUri() {
        return recipientAccountUri;
    }

    public void setRecipientAccountUri(String recipientAccountUri) {
        this.recipientAccountUri = recipientAccountUri;
    }
    @JsonProperty(value = "payment_amount")
    @XmlElement(name="payment_amount")
    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @JsonProperty(value = "payment_origination_country")
    @XmlElement(name="payment_origination_country", required = true)
    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    @JsonProperty(value = "bank_code")
    @XmlElement(name="bank_code")
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }


    @JsonProperty(value = "fx_type")
    @XmlElement(name="fx_type")
    public QuoteType getFxType() {
        return fxType;
    }

    public void setFxType(QuoteType fxType) {
        this.fxType = fxType;
    }

    @JsonProperty(value = "card_rate_id")
    @XmlElement(name="card_rate_id", nillable = true)
    public String getCardRateId() {
        return cardRateId;
    }

    public void setCardRateId(String cardRateId) {
        this.cardRateId = cardRateId;
    }

    @JsonProperty(value = "lock_id")
    @XmlElement(name="lock_id",  nillable = true)
    public String getLockId() { return lockId; }

    public void setLockId(String lockId) { this.lockId = lockId; }

}
