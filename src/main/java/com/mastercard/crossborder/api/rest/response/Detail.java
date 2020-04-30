package com.mastercard.crossborder.api.rest.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(
        name = "Detail",
        propOrder = {"name", "value"}
)
public class Detail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name = null;
    private String value = null;

    public Detail() {
    }

    public Detail(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @XmlElement(
            name = "Name",
            required = true
    )
    @JsonProperty(value="Name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(
            name = "Value",
            required = true
    )
    @JsonProperty(value="Value")
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }
}

