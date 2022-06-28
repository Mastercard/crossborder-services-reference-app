package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"eventRef", "eventType", "eventActor", "timestamp" })
public class RequestHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String eventRef;
    private String eventType;
    private String eventActor;
    private String timestamp;

    @JsonProperty(value = "eventRef")
    public String getEventRef() {
        return eventRef;
    }

    public void setEventRef(String eventRef) {
        this.eventRef = eventRef;
    }

    @JsonProperty(value = "eventType")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @JsonProperty(value = "eventActor")
    public String getEventActor() {
        return eventActor;
    }

    public void setEventActor(String eventActor) {
        this.eventActor = eventActor;
    }

    @JsonProperty(value = "timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
