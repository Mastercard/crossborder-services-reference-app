package com.mastercard.crossborder.api.rest.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "AdditionalDataData", propOrder = {"data"})
@XmlRootElement(name = "data")
public class AdditionalDataData implements Serializable {

    private static final long serialVersionUID = 1L;

    public AdditionalDataData() {
        //Default constructor
    }

    private List<AdditionalDataDataField> data = new ArrayList<>() ;

    @XmlElement(name = "data_field", required = true, nillable = false)
    public List<AdditionalDataDataField> getData() {
        return data;
    }

    public void setData(List<AdditionalDataDataField> data) {
        this.data = data;
    }

    public void addData(AdditionalDataDataField dataField) {

        data.add(dataField);
    }
}