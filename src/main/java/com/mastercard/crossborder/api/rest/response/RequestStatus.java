package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"requestHistory"})
public class RequestStatus implements Serializable {

    private static final long serialVersionUID = 1L;


    List<RequestHistory> requestHistory = new ArrayList();

    @JsonProperty(value = "requestHistory")
    public List<RequestHistory> getRequestHistory() {
        return requestHistory;
    }

    public void setRequestHistory(List<RequestHistory> requestHistory) {
        this.requestHistory = requestHistory;
    }
}



