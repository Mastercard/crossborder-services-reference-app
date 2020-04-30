package com.mastercard.crossborder.api.rest.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder(value = {"governmentIdURIs"})
@XmlType(name = "GovernmentIdData", propOrder = {"governmentIdURIs"})
@XmlRootElement(name = "government_id_uri")
public class GovernmentIdData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> governmentIdURIs;

    @JsonProperty(value = "government_id_uri")
    @XmlElement(name = "government_id_uri", required = true)
    public List<String> getGovernmentIdURIs() {
        return governmentIdURIs;
    }

    public void setGovernmentIdURIs(List<String> governmentIdURIs) {
        this.governmentIdURIs = governmentIdURIs;
    }
}