package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import java.util.Calendar;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName(value = "ProposedProposal")
public class ProposedProposal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String resourceType = PROPOSAL_OBJECT;
    private Boolean feesIncluded;
    private Calendar expirationDate;
    private String quoteFxRate;
    private ChargedAmount chargedAmount;
    private CreditedAmount creditedAmount;
    private PrincipalAmount principalAmount;
    private AdditionalDataList additionalDataList;
    private Calendar confirmationExpiryTime;
    private static final String PROPOSAL_OBJECT = "proposal";

    @JsonProperty(value = "id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty(value = "resourceType")
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @JsonProperty(value = "feesIncluded")
    public Boolean getFeesIncluded() {
        return feesIncluded;
    }
    public void setFeesIncluded(Boolean feesIncluded) {
        this.feesIncluded = feesIncluded;
    }

    @JsonProperty(value = "expirationDate")
    public Calendar getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty(value = "quoteFxRate")
    public String getQuoteFxRate() {
        return quoteFxRate;
    }
    public void setQuoteFxRate(String quoteFxRate) {
        this.quoteFxRate = quoteFxRate;
    }

    @JsonProperty(value = "chargedAmount")
    public ChargedAmount getChargedAmount() {
        return chargedAmount;
    }
    public void setChargedAmount(ChargedAmount chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    @JsonProperty(value = "creditedAmount")
    public CreditedAmount getCreditedAmount() {
        return creditedAmount;
    }
    public void setCreditedAmount(CreditedAmount creditedAmount) {
        this.creditedAmount = creditedAmount;
    }

    @JsonProperty(value = "principalAmount")
    public PrincipalAmount getPrincipalAmount() {
        return principalAmount;
    }
    public void setPrincipalAmount(PrincipalAmount principalAmount) {
        this.principalAmount = principalAmount;
    }

    @JsonProperty(value = "additionalDataList")
    public AdditionalDataList getAdditionalDataList() {
        return additionalDataList;
    }
    public void setAdditionalDataList(AdditionalDataList additionalDataList) {
        this.additionalDataList = additionalDataList;
    }

    @JsonProperty(value = "confirmationExpiryTime")
    public Calendar getConfirmationExpiryTime() {   return confirmationExpiryTime;  }
    public void setConfirmationExpiryTime(Calendar confirmationExpiryTime) { this.confirmationExpiryTime = confirmationExpiryTime; }

}
