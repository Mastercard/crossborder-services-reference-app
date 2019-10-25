package com.mastercard.crossborder.api.rest.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "PaymentRequest", propOrder = {"remittanceReference", "proposalId", "localDateTime","senderAccountUri","recipientAccountUri","paymentAmount","originatingCountry","fxType","receivingBankName","receivingBankBranchName","bankCode","paymentType","sourceOfIncome","senderInformation", "receiverInformation","purposeOfRemittance","fundingHints", "paymentFileIdentifier", "cardRateId","lockId"})
@XmlRootElement(name = "paymentrequest")
public class RemittanceRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public RemittanceRequest() {
        //default constructor
    }

    private String remittanceReference;
    private String proposalId;
    private CustomerData senderInformation;
    private CustomerData receiverInformation;
    private AdditionalDataData fundingHints;
    private String localDateTime;
    private String purposeOfRemittance;
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
    private String lockId;

    @XmlElement(name = "transaction_reference", required = true, nillable = false)
    public String getRemittanceReference() {
        return remittanceReference;
    }

    public void setRemittanceReference(String remittanceReference) {
        this.remittanceReference = remittanceReference;
    }

    @XmlElement(name = "sender", required = false)
    public CustomerData getSenderInformation() {
        return senderInformation;
    }

    public void setSenderInformation(CustomerData senderInformation) {
        this.senderInformation = senderInformation;
    }

    @XmlElement(name = "recipient", required = false)
    public CustomerData getReceiverInformation() {
        return receiverInformation;
    }

    public void setReceiverInformation(CustomerData receiverInformation) {
        this.receiverInformation = receiverInformation;
    }

    @XmlElement(name = "proposal_id", required = true, nillable = false)
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    @XmlElement(name = "funding_hints", required = false, nillable = false)
    public AdditionalDataData getFundingHints() {
        return fundingHints;
    }

    public void setFundingHints(AdditionalDataData fundingHints) {
        this.fundingHints = fundingHints;
    }

    @XmlElement(name = "local_date_time", required = true, nillable = false)
    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    @XmlElement(name="purpose_of_payment", required = false, nillable = false)
    public String getPurposeOfRemittance() {
        return purposeOfRemittance;
    }

    public void setPurposeOfRemittance(String purposeOfRemittance) {
        this.purposeOfRemittance = purposeOfRemittance;
    }

    @XmlElement(name = "source_of_income", required = false, nillable = false)
    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    @XmlElement(name = "receiving_bank_name", required = false, nillable = false)
    public String getReceivingBankName() {
        return receivingBankName;
    }

    public void setReceivingBankName(String receivingBankName) {
        this.receivingBankName = receivingBankName;
    }

    @XmlElement(name = "receiving_bank_branch_name", required = false, nillable = false)
    public String getReceivingBankBranchName() {
        return receivingBankBranchName;
    }

    public void setReceivingBankBranchName(String receivingBankBranchName) {
        this.receivingBankBranchName = receivingBankBranchName;
    }

    @XmlElement(name = "payment_type", required = false, nillable = false)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @XmlElement(name = "payment_file_identifier", required = false, nillable = false)
    public String getPaymentFileIdentifier() {
        return paymentFileIdentifier;
    }

    public void setPaymentFileIdentifier(String paymentFileIdentifier) {
        this.paymentFileIdentifier = paymentFileIdentifier;
    }

    @XmlElement(name="sender_account_uri", required = false, nillable = false)
    public String getSenderAccountUri() {
        return senderAccountUri;
    }

    public void setSenderAccountUri(String senderAccountUri) {
        this.senderAccountUri = senderAccountUri;
    }

    @XmlElement(name="recipient_account_uri", required = false, nillable = false)
    public String getRecipientAccountUri() {
        return recipientAccountUri;
    }

    public void setRecipientAccountUri(String recipientAccountUri) {
        this.recipientAccountUri = recipientAccountUri;
    }
    @XmlElement(name="payment_amount", required = false, nillable = false)
    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    
    @XmlElement(name="payment_origination_country", required = true, nillable = false)
    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }
    
    @XmlElement(name="bank_code", required = false, nillable = false)
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @XmlElement(name="fx_type", required = false, nillable = false)
    public QuoteType getFxType() {
        return fxType;
    }

    public void setFxType(QuoteType fxType) {
        this.fxType = fxType;
    }

    @XmlElement(name="card_rate_id", required = false, nillable = true)
    public String getCardRateId() {
        return cardRateId;
    }

    public void setCardRateId(String cardRateId) {
        this.cardRateId = cardRateId;
    }

    @XmlElement(name="lock_id", required = false, nillable = true)
    public String getLockId() { return lockId; }

    public void setLockId(String lockId) { this.lockId = lockId; }

}
