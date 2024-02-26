package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Account {
    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "value")
    private String value;

    public String getType() {
        return type;
    }

    public Account() {
    }

    public Account(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
