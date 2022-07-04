package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName(value = "response")
@JsonPropertyOrder(value = {"value"})
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private T value;

    @JsonProperty(value = "value")
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
