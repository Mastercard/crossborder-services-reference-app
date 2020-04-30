package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonPropertyOrder(value = {"resourceType","itemCount","data"})
@XmlType(name = "AdditionalData", propOrder = {"resourceType","itemCount","data"})
@XmlRootElement(name = "additional_data_list")
public class AdditionalData implements Serializable {

    private static final long serialVersionUID = 1L;

    public AdditionalData() {
        //Default constructor
    }

    private AdditionalDataData data;
    private String resourceType = "list";
    private String itemCount ="0";

    @JsonProperty(value = "data")
    @XmlElement(name = "data", required = true, nillable = false)
    public AdditionalDataData getData() {
        return data;
    }

    public void setData(AdditionalDataData data) {
        this.data = data;
    }

    @JsonProperty(value = "item_count")
    @XmlElement(name = "item_count", required = true, nillable = false)
    public String getItemCount(){
        Integer countSize = 0;
        if  ( (getData() != null) && (getData().getData() != null)){
            countSize = getData().getData().size();
        }
        return  countSize.toString();
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
    @JsonProperty(value = "resource_type")
    @XmlElement(name = "resource_type", required = true, nillable = false)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}