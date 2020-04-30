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

    private AdditionalDataData data;
    private String resourceType = "list";
    private String itemCount ="0";

    @JsonProperty(value = "data")
    @XmlElement(name = "data", required = true)
    public AdditionalDataData getData() {
        return data;
    }

    public void setData(AdditionalDataData data) {
        this.data = data;
    }

    @JsonProperty(value = "item_count")
    @XmlElement(name = "item_count", required = true)
    public String getItemCount(){
        return  this.itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
    @JsonProperty(value = "resource_type")
    @XmlElement(name = "resource_type", required = true)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}