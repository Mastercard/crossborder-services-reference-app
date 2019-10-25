package com.mastercard.crossborder.api.rest.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@XmlType(name = "AdditionalDataField", propOrder = {"fieldId", "value"})
public class AdditionalDataField implements Serializable {

    private static final long serialVersionUID = 1L;

    public AdditionalDataField() {
        //Default constructor
    }

    private String fieldId;
    private String value;

    @XmlElement(name = "name", required = true, nillable = false)
    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    @XmlElement(name = "value", required = true, nillable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}