package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(
        name = "Details",
        propOrder = {"details"}
)
public class Details implements Serializable {
    private static final long serialVersionUID = 1L;
    private Detail detail;

    @XmlElement(
            name = "Detail",
            required = true
    )
    @JsonProperty(value = "Detail")
    public Detail getDetails() {
        return this.detail;
    }

    public void setDetails(Detail detail) {
        this.detail = detail;
    }
}
