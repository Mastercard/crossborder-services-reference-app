package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;


@JsonTypeName(value = "retrieveRequestResponse")

@JsonPropertyOrder(value = {"retrieveResponse","requestStatus"})
public class RetrieveRequestResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private RetrieveResponse retrieveResponse;
    private RequestStatus requestStatus;

    @JsonProperty(value = "retrieveResponse")
    public RetrieveResponse getRetrieveResponse() {
        return retrieveResponse;
    }

    public void setRetrieveResponse(RetrieveResponse retrieveResponse) {
        this.retrieveResponse = retrieveResponse;
    }

    @JsonProperty(value = "requestStatus")
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
