package com.mastercard.crossborder.api.rest.response;


import com.mastercard.crossborder.api.rest.request.AdditionalData;
import com.mastercard.crossborder.api.rest.request.Amount;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Calendar;


@XmlType(name = "Proposal", propOrder = {"proposalId","resourceType","feesIncluded","chargedAmount","creditedAmount","principalAmount","expirationDate","destinationServiceTag","corridorTag","additionalData", "quoteFxRate"})
@XmlRootElement(name = "Proposal")
public class Proposal implements Serializable {
    private static final long serialVersionUID = 1L;
    private String proposalId;
    private Boolean feesIncluded;
    private Amount chargedAmount;
    private Amount creditedAmount;
    private Amount principalAmount;
    private Calendar expirationDate;
    private String destinationServiceTag;
    private String corridorTag;
    private String resourceType;
    private AdditionalData additionalData;

    public void setQuoteFxRate(String quoteFxRate) {
        this.quoteFxRate = quoteFxRate;
    }

    private String quoteFxRate;

    @XmlElement(name = "id")
    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    @XmlElement(name = "resource_type")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @XmlElement(name = "fees_included")
    public Boolean getFeesIncluded() {
        return feesIncluded;
    }

    public void setFeesIncluded(Boolean feesIncluded) {
        this.feesIncluded = feesIncluded;
    }


    @XmlElement(name = "expiration_date")
    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar value) {
        this.expirationDate = value;
    }

    @XmlElement(name = "destination_service_tag")
    public String getDestinationServiceTag() {
        return destinationServiceTag;
    }

    public void setDestinationServiceTag(String value) {
        this.destinationServiceTag = value;
    }

    @XmlElement(name = "corridor_tag")
    public String getCorridorTag() {
        return corridorTag;
    }

    public void setCorridorTag(String value) {
        this.corridorTag = value;
    }

    @XmlElement(name = "additional_data_list")
    public  AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }


    @XmlElement(name = "charged_amount")
    public Amount getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Amount value) {
        this.chargedAmount = value;
    }

    @XmlElement(name = "credited_amount")
    public Amount getCreditedAmount() {
        return creditedAmount;
    }

    public void setCreditedAmount(Amount value) {
        this.creditedAmount = value;
    }

    @XmlElement(name = "principal_amount")
    public Amount getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(Amount value) {
        this.principalAmount = value;
    }

    @XmlElement(name="quote_fx_rate")
    public String getQuoteFxRate() {
        return quoteFxRate;
    }

}
