package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName(value = "tiers")
@XmlRootElement(name = "tiers")
public class Tiers  implements Serializable {

    @JsonProperty(value="tier")
    public List<FxTierRate> getTiers() {
        return listTiers;
    }

    public void setTiers(List<FxTierRate> tiers) {
        this.listTiers = tiers;
    }

    private List<FxTierRate> listTiers = new ArrayList();

}
