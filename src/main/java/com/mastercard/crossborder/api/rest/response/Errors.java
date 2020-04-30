package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@XmlType(
        name = "Errors",
        propOrder = {"error"}
)
@XmlRootElement(
        name = "Errors"
)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Errors implements Serializable {
    private static final long serialVersionUID = 1L;
    private Error error;

    @XmlElement(
            name = "Error",
            required = true
    )
    @JsonProperty(value = "Error")
    public Error getError() {
        return this.error;
    }
    public void setError(Error errors){
        this.error = errors;
    }

}

