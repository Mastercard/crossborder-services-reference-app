package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "DataFields")
@JsonPropertyOrder(value = {"fieldId", "value"})
@XmlType(name = "dataFields", propOrder = {"fieldId", "value"})
@XmlRootElement(name = "dataFields")
public class DataFields implements Serializable {

    private int fieldId;
    private String value;

    @JsonProperty(value = "name")
    @XmlElement(name = "name")
    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    @JsonProperty(value = "value")
    @XmlElement(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataFields{" +
                "name=" + fieldId +
                ", value='" + value + '\'' +
                '}';
    }
}
