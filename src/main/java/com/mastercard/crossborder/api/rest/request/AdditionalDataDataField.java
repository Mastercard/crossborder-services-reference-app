package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonPropertyOrder(value = {"fieldId", "value"})
@XmlType(name = "AdditionalDataDataField", propOrder = {"fieldId", "value"})
@XmlRootElement(name = "data_field")
public class AdditionalDataDataField implements Serializable {

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