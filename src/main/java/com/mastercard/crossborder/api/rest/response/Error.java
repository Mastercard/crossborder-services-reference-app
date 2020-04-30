package com.mastercard.crossborder.api.rest.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

public class Error implements Serializable {
    static final long serialVersionUID = 1L;
    private String source;
    private String reasonCode;
    private String key;
    private String description;
    private Boolean recoverable;
    private String requestId;
    private Details details;

    public Error() {
        this.source = null;
        this.reasonCode = null;
        this.key = null;
        this.description = null;
        this.recoverable = null;
        this.requestId = null;
        this.details = null;
    }

    public Error(String source, String reasonCode) {
        this(source, reasonCode, (String)null);
    }

    public Error(String source, String reasonCode, String description) {
        this(source, reasonCode, description, false);
    }

    public Error(String source, String reasonCode, String description, Boolean recoverable) {
        this.source = null;
        this.reasonCode = null;
        this.key = null;
        this.description = null;
        this.recoverable = null;
        this.requestId = null;
        this.details = null;
        this.source = source;
        this.reasonCode = reasonCode;
        this.description = description;
        this.recoverable = recoverable;
    }

    @XmlElement(
            name = "Source",
            required = true
    )
    @JsonProperty(value = "Source")
    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlElement(
            name = "ReasonCode",
            required = true
    )
    @JsonProperty(value = "ReasonCode")
    public String getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    @XmlTransient
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @XmlElement(
            name = "Description"
    )
    @JsonProperty(value = "Description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(
            name = "Recoverable",
            required = true
    )
    @JsonProperty(value = "Recoverable")
    public Boolean getRecoverable() {
        return this.recoverable;
    }

    public void setRecoverable(Boolean recoverable) {
        this.recoverable = recoverable;
    }

    @XmlElement(
            name = "RequestId"
    )
    @JsonProperty(value = "RequestId")
    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @XmlElement(
            name = "Details"
    )
    @JsonProperty(value = "Details")
    public Details getDetails() {
        return this.details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }
}

