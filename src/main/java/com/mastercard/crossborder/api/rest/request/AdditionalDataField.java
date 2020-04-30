package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@XmlType(name = "AdditionalDataField", propOrder = {"fieldId", "value"})
public class AdditionalDataField implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldId;
    private String value;

    @JsonProperty(value = "name")
    @XmlElement(name = "name", required = true)
    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    @JsonProperty(value = "value")
    @XmlElement(name = "value", required = true)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}