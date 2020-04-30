package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName(value = "rates")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@XmlType(name = "rates", propOrder = { "rates"})
@XmlRootElement(name = "rates")
public class FxRateResponse implements Serializable {

    private List<FxRate> rates = new ArrayList();

    @JsonProperty(value = "rate")
    @XmlElement(name = "rate", required = true)
    public List<FxRate> getRates() {
        return rates;
    }

    public void setRates(List<FxRate> rates) {
        this.rates = rates;
    }



}
