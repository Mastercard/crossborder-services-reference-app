package com.mastercard.crossborder.api.rest.response;

import com.mastercard.crossborder.api.rest.request.AdditionalData;
import com.mastercard.crossborder.api.rest.request.Amount;
import com.mastercard.crossborder.api.rest.request.CustomerData;
import com.mastercard.crossborder.api.rest.request.QuoteType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Calendar;

@XmlType(name = "PaymentResponse", propOrder = {"transactionReference","responseCode", "remittanceId", "proposalId", "cardRateId", "lockId", "resourceType","created","partnerName","statusTimeStamp","pendingStage", "pendingMaxCompletionDate", "rejectedStatus", "returnStatus", "feesAmount", "chargedAmount", "creditedAmount", "principalAmount", "senderAccountUri", "recipientAccountUri", "paymentAmount", "originatingCountry", "fxType", "receivingBankName", "receivingBankBranchName", "bankCode","paymentType", "sourceOfIncome", "settlementDetails", "cashoutCode", "fxRate", "additionalData", "paymentFileIdentifier","localDateTime","sender","recipient"})
@XmlRootElement(name = "payment")
public class RemittanceResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public RemittanceResponse() {
        //Default constructor
    }

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

    @XmlElement(name = "status", required = true, nillable = false)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @XmlElement(name = "rejected_status")
    public RejectedTransactionStatus getRejectedStatus() {
        return rejectedStatus;
    }

    public void setRejectedStatus(RejectedTransactionStatus rejectedStatus) {
        this.rejectedStatus = rejectedStatus;
    }

    @XmlElement(name = "return_status")
    public RefundTransactionStatus getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(RefundTransactionStatus returnStatus) {
        this.returnStatus = returnStatus;
    }

    @XmlElement(name = "transaction_reference", required = true, nillable = false)
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    @XmlElement(name = "pending_stage")
    public String getPendingStage() {
        return pendingStage;
    }

    public void setPendingStage(String pendingStage) {
        this.pendingStage = pendingStage;
    }

    @XmlElement(name = "pending_max_completion_date")
    public Calendar getPendingMaxCompletionDate() {
        return pendingMaxCompletionDate;
    }

    public void setPendingMaxCompletionDate(Calendar pendingMaxCompletionDate) {
        this.pendingMaxCompletionDate = pendingMaxCompletionDate;
    }

    @XmlElement(name = "fees_amount")
    public Amount getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(Amount feesAmount) {
        this.feesAmount = feesAmount;
    }

    @XmlElement(name = "charged_amount")
    public Amount getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Amount chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    @XmlElement(name = "credited_amount")
    public Amount getCreditedAmount() {
        return creditedAmount;
    }

    public void setCreditedAmount(Amount creditedAmount) {
        this.creditedAmount = creditedAmount;
    }

    @XmlElement(name = "principal_amount")
    public Amount getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(Amount principalAmount) {
        this.principalAmount = principalAmount;
    }

    @XmlElement(name = "additional_data_list")
    public  AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    @XmlElement(name = "resource_type")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @XmlElement(name = "proposal_id", required = true, nillable = false)
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    @XmlElement(name = "created", required = true, nillable = false)
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @XmlElement(name = "status_timestamp", required = true, nillable = false)
    public String getStatusTimeStamp() {
        return statusTimeStamp;
    }

    public void setStatusTimeStamp(String statusTimeStamp) {
        this.statusTimeStamp = statusTimeStamp;
    }

    @XmlElement(name = "id", required = true, nillable = false)
    public String getRemittanceId() {
        return remittanceId;
    }

    public void setRemittanceId(String remittanceId) {
        this.remittanceId = remittanceId;
    }

    @XmlElement(name = "cashout_code")
    public String getCashoutCode() {
        return cashoutCode;
    }

    public void setCashoutCode(String cashoutCode) {
        this.cashoutCode = cashoutCode;
    }

    @XmlElement(name = "settlement_details")
    public Amount getSettlementDetails() {
        return settlementDetails;
    }

    public void setSettlementDetails(Amount settlementDetails) {
        this.settlementDetails = settlementDetails;
    }

    @XmlElement(name = "source_of_income")
    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    @XmlElement(name = "receiving_bank_name")
    public String getReceivingBankName() {
        return receivingBankName;
    }

    public void setReceivingBankName(String receivingBankName) {
        this.receivingBankName = receivingBankName;
    }

    @XmlElement(name = "receiving_bank_branch_name")
    public String getReceivingBankBranchName() {
        return receivingBankBranchName;
    }

    public void setReceivingBankBranchName(String receivingBankBranchName) {
        this.receivingBankBranchName = receivingBankBranchName;
    }

    @XmlElement(name = "payment_type")
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @XmlElement(name = "payment_file_identifier")
    public String getPaymentFileIdentifier() {
        return paymentFileIdentifier;
    }

    public void setPaymentFileIdentifier(String paymentFileIdentifier) {
        this.paymentFileIdentifier = paymentFileIdentifier;
    }

    @XmlElement(name ="fx_rate")
    public String getFxRate() {
        return fxRate;
    }

    public void setFxRate(String fxRate) {
        this.fxRate = fxRate;
    }

    @XmlElement(name = "sender_account_uri")
    public String getSenderAccountUri() {
        return senderAccountUri;
    }

    public void setSenderAccountUri(String senderAccountUri) {
        this.senderAccountUri = senderAccountUri;
    }

    @XmlElement(name = "recipient_account_uri")
    public String getRecipientAccountUri() {
        return recipientAccountUri;
    }

    public void setRecipientAccountUri(String recipientAccountUri) {
        this.recipientAccountUri = recipientAccountUri;
    }

    @XmlElement(name = "payment_amount")
    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

	@XmlElement(name = "payment_origination_country")
    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

	@XmlElement(name = "bank_code")
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

	@XmlElement(name = "fx_type")
    public QuoteType getFxType() {
        return fxType;
    }

    public void setFxType(QuoteType fxType) {
        this.fxType = fxType;
    }

	@XmlElement(name = "card_rate_id")
    public String getCardRateId() {
        return cardRateId;
    }

    public void setCardRateId(String cardRateId) {
        this.cardRateId = cardRateId;
    }

	@XmlElement(name = "sender", nillable = false)
    public CustomerData getSender() { return sender; }

    public void setSender(CustomerData sender) { this.sender = sender; }

	@XmlElement(name = "recipient", nillable = false)
    public CustomerData getRecipient() { return recipient; }

    public void setRecipient(CustomerData recipient) { this.recipient = recipient; }

	@XmlElement(name = "local_date_time", nillable = false)
    public String getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(String localDateTime) { this.localDateTime = localDateTime; }

	@XmlElement(name = "partner_name", nillable = false)
    public String getPartnerName() { return partnerName; }

    public void setPartnerName(String partnerName) { this.partnerName = partnerName; }

    @XmlElement(name="lock_id")
    public String getLockId() { return lockId; }

    public void setLockId(String lockId) { this.lockId = lockId; }
}
