package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonTypeName(value = "AdditionalDataList")
@XmlType(name = "additionalDataList", propOrder = {"resourceType", "itemCount","data"})
@XmlRootElement(name = "additionalDataList")
public class AdditionalDataList implements Serializable {

    private String resourceType = "list";
    private int itemCount=0;
    private Data data;

    @JsonProperty(value = "resourceType")
    @XmlElement(name = "resourceType")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @JsonProperty(value = "itemCount")
    @XmlElement(name = "itemCount")
    public int getItemCount() {
        if  ( (getData() != null) && (getData().getDataFields() != null)){
            itemCount = getData().getDataFields().size();
        }
        return  itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @JsonProperty(value = "data")
    @XmlElement(name = "data")
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AdditionalDataList{" +
                "resourceType='" + resourceType + '\'' +
                ", itemCount=" + itemCount +
                ", data=" + data +
                '}';
    }
}
