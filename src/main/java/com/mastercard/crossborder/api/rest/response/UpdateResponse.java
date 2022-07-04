package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;


@JsonTypeName(value = "updateResponse")
//@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonPropertyOrder(value = {"referenceId","requestStatus"})
public class UpdateResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String referenceId;
    private String requestStatus;

    @JsonProperty(value = "referenceId")
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @JsonProperty(value = "requestStatus")
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}
