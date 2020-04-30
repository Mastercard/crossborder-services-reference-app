package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "RefundTransactionStatus")
@XmlType(name = "RefundTransactionStatus", propOrder = { "message"})
@XmlRootElement(name = "RefundTransactionStatus")
public class RefundTransactionStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    @JsonProperty(value = "Message")
    @XmlElement(name = "Message", required = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
