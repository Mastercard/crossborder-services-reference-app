package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@JsonTypeName(value = "proposals")
@XmlRootElement(name = "proposals")
public class Proposals implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "proposal")
    @XmlElement(name = "proposal")
    public List<Proposal> getProposal() {
        return proposal;
    }

    public void setProposal(List<Proposal> proposal) {
        this.proposal = proposal;
    }

    private List<Proposal> proposal;
}
