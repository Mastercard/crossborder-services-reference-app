package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mastercard.crossborder.api.rest.request.AdditionalData;
import com.mastercard.crossborder.api.rest.request.Amount;
import com.mastercard.crossborder.api.rest.request.CustomerData;
import com.mastercard.crossborder.api.rest.request.QuoteType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Calendar;

@JsonTypeName(value = "payment")
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonPropertyOrder(value = {"transactionReference","responseCode", "remittanceId", "proposalId", "cardRateId", "lockId", "resourceType","created","partnerName","statusTimeStamp","pendingStage", "pendingMaxCompletionDate", "rejectedStatus", "returnStatus", "feesAmount", "chargedAmount", "creditedAmount", "principalAmount", "senderAccountUri", "recipientAccountUri", "paymentAmount", "originatingCountry", "fxType", "receivingBankName", "receivingBankBranchName", "bankCode","paymentType", "sourceOfIncome", "settlementDetails", "cashoutCode", "fxRate", "additionalData", "paymentFileIdentifier","localDateTime","sender","recipient","transactionStatusHistory","homesendDetails"})
@XmlType(name = "PaymentResponse", propOrder = {"transactionReference","responseCode", "remittanceId", "proposalId", "cardRateId", "lockId", "resourceType","created","partnerName","statusTimeStamp","pendingStage", "pendingMaxCompletionDate", "rejectedStatus", "returnStatus", "feesAmount", "chargedAmount", "creditedAmount", "principalAmount", "senderAccountUri", "recipientAccountUri", "paymentAmount", "originatingCountry", "fxType", "receivingBankName", "receivingBankBranchName", "bankCode","paymentType", "sourceOfIncome", "settlementDetails", "cashoutCode", "fxRate", "additionalData", "paymentFileIdentifier","localDateTime","sender","recipient"})
@XmlRootElement(name = "payment")
public class RemittanceResponse implements Serializable {

    private static final long serialVersionUID = 1L;


    private String responseCode;
    private RejectedTransactionStatus rejectedStatus;
    private RefundTransactionStatus returnStatus;
    private String transactionReference;
    private String pendingStage;
    private String resourceType;
    private Calendar pendingMaxCompletionDate;
    private Amount feesAmount;
    private Amount chargedAmount;
    private Amount creditedAmount;
    private Amount principalAmount;
    private AdditionalData additionalData;
    private String proposalId;
    private String created;
    private String partnerName;
    private String statusTimeStamp;
    private String remittanceId;

    private String cashoutCode;
    private Amount settlementDetails;
    private String fxRate;
    private String sourceOfIncome;
    private String receivingBankName;
    private String receivingBankBranchName;
    private String paymentType;
    private String paymentFileIdentifier;
    private String senderAccountUri;
    private String recipientAccountUri;
    private Amount paymentAmount;
    private String originatingCountry;
    private String bankCode;
    private QuoteType fxType;
    private String cardRateId;

    private String localDateTime;
    private CustomerData sender;
    private CustomerData recipient;
    private String lockId;

    @JsonProperty(value = "status")
    @XmlElement(name = "status", required = true)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty(value = "rejected_status")
    @XmlElement(name = "rejected_status")
    public RejectedTransactionStatus getRejectedStatus() {
        return rejectedStatus;
    }

    public void setRejectedStatus(RejectedTransactionStatus rejectedStatus) {
        this.rejectedStatus = rejectedStatus;
    }

    @JsonProperty(value = "return_status")
    @XmlElement(name = "return_status")
    public RefundTransactionStatus getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(RefundTransactionStatus returnStatus) {
        this.returnStatus = returnStatus;
    }

    @JsonProperty(value = "transaction_reference")
    @XmlElement(name = "transaction_reference", required = true)
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    @JsonProperty(value = "pending_stage")
    @XmlElement(name = "pending_stage")
    public String getPendingStage() {
        return pendingStage;
    }

    public void setPendingStage(String pendingStage) {
        this.pendingStage = pendingStage;
    }

    @JsonProperty(value = "pending_max_completion_date")
    @XmlElement(name = "pending_max_completion_date")
    public Calendar getPendingMaxCompletionDate() {
        return pendingMaxCompletionDate;
    }

    public void setPendingMaxCompletionDate(Calendar pendingMaxCompletionDate) {
        this.pendingMaxCompletionDate = pendingMaxCompletionDate;
    }

    @JsonProperty(value = "fees_amount")
    @XmlElement(name = "fees_amount")
    public Amount getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(Amount feesAmount) {
        this.feesAmount = feesAmount;
    }

    @JsonProperty(value = "charged_amount")
    @XmlElement(name = "charged_amount")
    public Amount getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Amount chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    @JsonProperty(value = "credited_amount")
    @XmlElement(name = "credited_amount")
    public Amount getCreditedAmount() {
        return creditedAmount;
    }

    public void setCreditedAmount(Amount creditedAmount) {
        this.creditedAmount = creditedAmount;
    }

    @JsonProperty(value = "principal_amount")
    @XmlElement(name = "principal_amount")
    public Amount getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(Amount principalAmount) {
        this.principalAmount = principalAmount;
    }

    @JsonProperty(value = "additional_data_list")
    @XmlElement(name = "additional_data_list")
    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    @JsonProperty(value = "resource_type")
    @XmlElement(name = "resource_type")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @JsonProperty(value = "proposal_id")
    @XmlElement(name = "proposal_id", required = true)
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    @JsonProperty(value = "created")
    @XmlElement(name = "created", required = true)
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty(value = "status_timestamp")
    @XmlElement(name = "status_timestamp", required = true)
    public String getStatusTimeStamp() {
        return statusTimeStamp;
    }

    public void setStatusTimeStamp(String statusTimeStamp) {
        this.statusTimeStamp = statusTimeStamp;
    }

    @JsonProperty(value = "id")
    @XmlElement(name = "id", required = true)
    public String getRemittanceId() {
        return remittanceId;
    }

    public void setRemittanceId(String remittanceId) {
        this.remittanceId = remittanceId;
    }

    @JsonProperty(value = "cashout_code")
    @XmlElement(name = "cashout_code")
    public String getCashoutCode() {
        return cashoutCode;
    }

    public void setCashoutCode(String cashoutCode) {
        this.cashoutCode = cashoutCode;
    }

    @JsonProperty(value = "settlement_details")
    @XmlElement(name = "settlement_details")
    public Amount getSettlementDetails() {
        return settlementDetails;
    }

    public void setSettlementDetails(Amount settlementDetails) {
        this.settlementDetails = settlementDetails;
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

    @JsonProperty(value = "fx_rate")
    @XmlElement(name ="fx_rate")
    public String getFxRate() {
        return fxRate;
    }

    public void setFxRate(String fxRate) {
        this.fxRate = fxRate;
    }

    @JsonProperty(value = "sender_account_uri")
    @XmlElement(name = "sender_account_uri")
    public String getSenderAccountUri() {
        return senderAccountUri;
    }

    public void setSenderAccountUri(String senderAccountUri) {
        this.senderAccountUri = senderAccountUri;
    }

    @JsonProperty(value = "recipient_account_uri")
    @XmlElement(name = "recipient_account_uri")
    public String getRecipientAccountUri() {
        return recipientAccountUri;
    }

    public void setRecipientAccountUri(String recipientAccountUri) {
        this.recipientAccountUri = recipientAccountUri;
    }

    @JsonProperty(value = "payment_amount")
    @XmlElement(name = "payment_amount")
    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @JsonProperty(value = "payment_origination_country")
    @XmlElement(name = "payment_origination_country")
    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    @JsonProperty(value = "bank_code")
    @XmlElement(name = "bank_code")
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @JsonProperty(value = "fx_type")
    @XmlElement(name = "fx_type")
    public QuoteType getFxType() {
        return fxType;
    }

    public void setFxType(QuoteType fxType) {
        this.fxType = fxType;
    }

    @JsonProperty(value = "card_rate_id")
    @XmlElement(name = "card_rate_id")
    public String getCardRateId() {
        return cardRateId;
    }

    public void setCardRateId(String cardRateId) {
        this.cardRateId = cardRateId;
    }

    @JsonProperty(value = "sender")
    @XmlElement(name = "sender")
    public CustomerData getSender() { return sender; }

    public void setSender(CustomerData sender) { this.sender = sender; }

    @JsonProperty(value = "recipient")
    @XmlElement(name = "recipient")
    public CustomerData getRecipient() { return recipient; }

    public void setRecipient(CustomerData recipient) { this.recipient = recipient; }

    @JsonProperty(value = "local_date_time")
    @XmlElement(name = "local_date_time")
    public String getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(String localDateTime) { this.localDateTime = localDateTime; }

    @JsonProperty(value = "partner_name")
    @XmlElement(name = "partner_name")
    public String getPartnerName() { return partnerName; }

    public void setPartnerName(String partnerName) { this.partnerName = partnerName; }

    @JsonProperty(value = "lock_id")
    @XmlElement(name="lock_id")
    public String getLockId() { return lockId; }

    public void setLockId(String lockId) { this.lockId = lockId; }

}
