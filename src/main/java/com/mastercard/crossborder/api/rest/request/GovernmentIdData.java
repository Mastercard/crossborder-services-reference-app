package com.mastercard.crossborder.api.rest.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

@XmlType(name = "GovernmentIdData", propOrder = {"governmentIdURIs"})
@XmlRootElement(name = "government_id_uri")
public class GovernmentIdData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> governmentIdURIs;

    @XmlElement(name = "government_id_uri", required = true)
    public List<String> getGovernmentIdURIs() {
        return governmentIdURIs;
    }

    public void setGovernmentIdURIs(List<String> governmentIdURIs) {
        this.governmentIdURIs = governmentIdURIs;
    }
}
