package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "responseDocuments")
@JsonPropertyOrder(value = {"response"})
public class ResponseDocuments implements Serializable {

    private static final long serialVersionUID = 1L;
    private Response response;
    @JsonProperty(value = "response")
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


}
