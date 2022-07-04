package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName(value = "Data")
@XmlType(name = "data", propOrder = {"dataFields"})
@XmlRootElement(name = "data")
public class Data implements Serializable {

    private List<DataFields> dataFields= new ArrayList<>();

    @JsonProperty(value = "dataFields")
    @XmlElement(name = "dataFields")
    public List<DataFields> getDataFields() {
        return dataFields;
    }

    public void setDataFields(List<DataFields> dataFields) {
        this.dataFields = dataFields;
    }
}
