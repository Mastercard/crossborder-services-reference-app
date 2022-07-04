package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.Calendar;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName(value = "QuoteStatus")
 public class QuoteStatus implements Serializable {
  private static final long serialVersionUID = 1L;

  private String status;
  private Calendar statusUpdateTimestamp;
  private String pendingStage;
  private String errorCode;
  private String errorMessage;

  @JsonProperty(value = "status")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  @JsonProperty(value = "statusTimestamp")
  public Calendar getStatusUpdateTimestamp() {
    return statusUpdateTimestamp;
  }

  public void setStatusUpdateTimestamp(Calendar statusUpdateTimestamp) {
    this.statusUpdateTimestamp = statusUpdateTimestamp;
  }
  @JsonProperty(value = "pendingStage")
  public String getPendingStage() { return pendingStage;  }
  public void setPendingStage(String pendingStage) { this.pendingStage = pendingStage;  }

  @JsonProperty(value = "errorCode")
  public String getErrorCode() { return errorCode;  }
  public void setErrorCode(String errorCode) {    this.errorCode = errorCode;  }

  @JsonProperty(value = "errorMessage")
  public String getErrorMessage() {    return errorMessage;  }
  public void setErrorMessage(String errorMessage) {    this.errorMessage = errorMessage;  }

}

