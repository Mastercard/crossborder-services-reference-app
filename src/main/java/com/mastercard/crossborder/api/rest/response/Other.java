package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"comments", "responseDocuments", "requestDocuments"})
public class Other implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Comments> comments = new ArrayList();
    ResponseDocuments responseDocuments;
    RequestDocuments requestDocuments;

    @JsonProperty(value = "comments")
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @JsonProperty(value = "responseDocuments")
    public ResponseDocuments getResponseDocuments() {
        return responseDocuments;
    }

    public void setResponseDocuments(ResponseDocuments responseDocuments) {
        this.responseDocuments = responseDocuments;
    }

    @JsonProperty(value = "requestDocuments")
    public RequestDocuments getRequestDocuments() {
        return requestDocuments;
    }

    public void setRequestDocuments(RequestDocuments requestDocuments) {
        this.requestDocuments = requestDocuments;
    }
}
