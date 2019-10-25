package com.mastercard.crossborder.api.rest.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "RejectedTransactionStatus", propOrder = {"code", "message", "description"})
@XmlRootElement(name = "RejectedTransactionStatus")
public class RejectedTransactionStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private String description;

    @XmlElement(name = "Code", required = true, nillable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "Message", required = true, nillable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name = "Description", required = true, nillable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
