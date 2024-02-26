package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName(value = "ProposedQuote")
public class ProposedQuote implements Serializable {

  private static final long serialVersionUID = 1L;

  private String proposalId;
  private QuoteStatus confirmStatus;
  private QuoteStatus cancelStatus;
  private ReleasedReversedAmount releasedReservedAmount;
  private Calendar statusUpdateTimestamp;
  private Calendar paymentSubmissionExpiryTime;
  private List<ProposedProposal> proposals;
  private String paymentType;

  @JsonProperty(value = "proposalId")
  public String getProposalId() {
    return proposalId;
  }
  public void setProposalId(String proposalId) {
    this.proposalId = proposalId;
  }

  @JsonProperty(value = "confirmStatus")
  public QuoteStatus getConfirmStatus() {
    return confirmStatus;
  }
  public void setConfirmStatus(QuoteStatus confirmStatus) { this.confirmStatus = confirmStatus; }

  @JsonProperty(value = "cancelStatus")
  public QuoteStatus getCancelStatus() {
    return cancelStatus;
  }
  public void setCancelStatus(QuoteStatus cancelStatus) {
    this.cancelStatus = cancelStatus;
  }

  @JsonProperty(value = "releasedReservedAmount")
  public ReleasedReversedAmount getReleasedReservedAmount() {
    return releasedReservedAmount;
  }

  public void setReleasedReservedAmount(ReleasedReversedAmount releasedReservedAmount) {
    this.releasedReservedAmount = releasedReservedAmount;
  }

  @JsonProperty(value = "statusTimestamp")
  public Calendar getStatusUpdateTimestamp() {
    return statusUpdateTimestamp;
  }

  public void setStatusUpdateTimestamp(Calendar statusUpdateTimestamp) {
    this.statusUpdateTimestamp = statusUpdateTimestamp;
  }

  @JsonProperty("paymentSubmissionExpiryTime")
  public Calendar getPaymentSubmissionExpiryTime() {  return paymentSubmissionExpiryTime;    }
  public void setPaymentSubmissionExpiryTime(Calendar paymentSubmissionExpiryTime) { this.paymentSubmissionExpiryTime = paymentSubmissionExpiryTime;    }


  @JsonProperty(value = "paymentType")
  public String getPaymentType() {
    return paymentType;
  }
  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  @JsonProperty(value = "proposals")
  public List<ProposedProposal> getProposals() { return proposals;  }
  public void setProposals(List<ProposedProposal> proposals) {
    this.proposals = proposals;
  }


}

