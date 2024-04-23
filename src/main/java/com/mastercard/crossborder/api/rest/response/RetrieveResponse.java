package com.mastercard.crossborder.api.rest.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName(value = "retrieveResponse")
@JsonPropertyOrder(value = {"assignee", "creator", "firstResponseDate", "lastUpdatedDate", "other", "paymentAndDocs", "recipient", "requestCreateDate", "requestId","requestInstruction", "requestStatus","responseType", "sender","transactions","closureDate"})
public class RetrieveResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String assignee;
    private String closureDate;
    private String creator;
    private String firstResponseDate;
    private String lastUpdatedDate;
    private String requestId;
    private String requestCreateDate;
    private String requestInstruction;
    private String requestStatus;
    private String responseType;
    private List<Transactions> transactions =  new ArrayList<>();
    private Sender sender;
    private Recipient recipient;
    private PaymentAndDocs paymentAndDocs;
    private Other other;

    @JsonProperty(value = "assignee")
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @JsonProperty(value = "closureDate")
    public String getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(String closureDate) {
        this.closureDate = closureDate;
    }

    @JsonProperty(value = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @JsonProperty(value = "firstResponseDate")
    public String getFirstResponseDate() {
        return firstResponseDate;
    }

    public void setFirstResponseDate(String firstResponseDate) {
        this.firstResponseDate = firstResponseDate;
    }

    @JsonProperty(value = "lastUpdatedDate")
    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @JsonProperty(value = "requestId")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty(value = "requestCreateDate")
    public String getRequestCreateDate() {
        return requestCreateDate;
    }

    public void setRequestCreateDate(String requestCreateDate) {
        this.requestCreateDate = requestCreateDate;
    }

    @JsonProperty(value = "requestInstruction")
    public String getRequestInstruction() {
        return requestInstruction;
    }

    public void setRequestInstruction(String requestInstruction) {
        this.requestInstruction = requestInstruction;
    }

    @JsonProperty(value = "requestStatus")
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @JsonProperty(value = "responseType")
    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    @JsonProperty(value = "transactions")
    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    @JsonProperty(value = "sender")
    public Sender getSender() {
        return sender;
    }

    @JsonProperty(value = "recipient")
    public Recipient getRecipient() {
        return recipient;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
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










