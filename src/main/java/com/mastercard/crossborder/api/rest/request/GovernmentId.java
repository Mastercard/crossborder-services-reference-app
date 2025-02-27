package com.mastercard.crossborder.api.rest.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"type", "number","documents"})
public class GovernmentId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private String number;
    private transient List< Object > documents = new ArrayList<>();


    @JsonProperty(value = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty(value = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty(value = "documents")
    public List<Object> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Object> documents) {
        this.documents = documents;
    }
}
