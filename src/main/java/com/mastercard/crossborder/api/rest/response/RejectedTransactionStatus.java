package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "RejectedTransactionStatus")
@XmlType(name = "RejectedTransactionStatus", propOrder = {"code", "message", "description"})
@XmlRootElement(name = "RejectedTransactionStatus")
public class RejectedTransactionStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private String description;

    @JsonProperty(value = "Code")
    @XmlElement(name = "Code", required = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty(value = "Message")
    @XmlElement(name = "Message", required = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty(value = "Description")
    @XmlElement(name = "Description", required = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
