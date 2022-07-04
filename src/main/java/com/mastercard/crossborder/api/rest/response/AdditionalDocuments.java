package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "additionalDocuments")
@JsonPropertyOrder(value = {"review","request","response"})
public class AdditionalDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    private Request request;
    private Review review;
    private Response response;

    @JsonProperty(value = "request")
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @JsonProperty(value = "review")
    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @JsonProperty(value = "response")
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
