package com.mastercard.crossborder.api.rest.response;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;


public class Proposals implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "proposal")
    public List<Proposal> getProposal() {
        return proposal;
    }

    public void setProposal(List<Proposal> proposal) {
        this.proposal = proposal;
    }

    private List<Proposal> proposal;
}
