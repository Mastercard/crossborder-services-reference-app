package com.mastercard.crossborder.api.rest.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlType(
        name = "Details",
        propOrder = {"details"}
)
public class Details implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Detail> details = null;

    public Details() {
        //Default constructor
    }

    @XmlElement(
            name = "Detail",
            required = true,
            nillable = false
    )
    public List<Detail> getDetails() {
        return this.details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public void addDetail(Detail detail) {
        if (this.getDetails() == null) {
            this.setDetails(new ArrayList());
        }

        this.getDetails().add(detail);
    }
}
