//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.mastercard.crossborder.api.rest.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@XmlType(
        name = "Details",
        propOrder = {"details"}
)
@ToString
public class Details implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Detail> details = null;

    public Details() {
    }

    @XmlElement(
            name = "Detail",
            required = true,
            nillable = false
    )
    @JsonProperty("Detail")
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
/*
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }*/
}
