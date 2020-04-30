package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "tier", propOrder = { "fromAmount", "rateId", "askRate", "midRate", "bidRate"})
@XmlRootElement(name = "tier")
public class FxTierRate  implements Serializable {

    private String fromAmount;
    private String rateId;
    private String askRate;
    private String midRate;
    private String bidRate;


    @JsonProperty(value="from_amount")
    @XmlElement(name = "from_amount", required = true, nillable = true)
    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }


    @JsonProperty(value="rate_id")
    @XmlElement(name = "rate_id", required = true)
    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    @JsonProperty(value="ask_rate")
    @XmlElement(name = "ask_rate", required = true, nillable = true)
    public String getAskRate() {
        return askRate;
    }

    public void setAskRate(String askRate) {
        this.askRate = askRate;
    }

    @JsonProperty(value="mid_rate")
    @XmlElement(name = "mid_rate", required = true, nillable = true)
    public String getMidRate() {
        return midRate;
    }

    public void setMidRate(String midRate) {
        this.midRate = midRate;
    }

    @JsonProperty(value="bid_rate")
    @XmlElement(name = "bid_rate", required = true)
    public String getBidRate() {
        return bidRate;
    }

    public void setBidRate(String bidRate) {
        this.bidRate = bidRate;
    }


}
