package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mastercard.crossborder.api.rest.request.AdditionalData;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Calendar;

@JsonPropertyOrder(value = {"proposalId","resourceType","feesIncluded","chargedAmount","creditedAmount","principalAmount","expirationDate","destinationServiceTag","corridorTag","additionalData", "quoteFxRate", "confirmationExpiryTime"})
@XmlType(name = "Proposal", propOrder = {"proposalId","resourceType","feesIncluded","chargedAmount","creditedAmount","principalAmount","expirationDateString","destinationServiceTag","corridorTag","additionalData", "quoteFxRate", "confirmationExpiryTimeString"})
@XmlRootElement(name = "Proposal")
public class Proposal implements Serializable {
    private static final long serialVersionUID = 1L;
    private String proposalId;
    private String resourceType;
    private Boolean feesIncluded;
    private Calendar expirationDate;
    private String expirationDateString;
    private String quoteFxRate;
    private ChargedAmount chargedAmount;
    private CreditedAmount creditedAmount;
    private PrincipalAmount principalAmount;
    private AdditionalData additionalData;
    private String destinationServiceTag;
    private String corridorTag;
    private Calendar confirmationExpiryTime;
    private String confirmationExpiryTimeString;

    public void setQuoteFxRate(String quoteFxRate) {
        this.quoteFxRate = quoteFxRate;
    }


    /**
     * Gets the value of the proposalId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @JsonProperty(value = "id")
    @XmlElement(name = "id")
    public String getProposalId() {
        return proposalId;
    }

    /**
     * Sets the value of the proposalId property.
     *
     * @param proposalId
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    /**
     * Gets the value of the proposalId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @JsonProperty(value = "resource_type")
    @XmlElement(name = "resource_type")
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Sets the value of the resourceType property.
     *
     * @param resourceType
     *     allowed object is
     *     {@link String }
     *
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Gets the value of the feesIncluded property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    @JsonProperty(value = "fees_included")
    @XmlElement(name = "fees_included")
    public Boolean getFeesIncluded() {
        return feesIncluded;
    }

    /**
     * Sets the value of the feesIncluded property.
     *
     * @param feesIncluded
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setFeesIncluded(Boolean feesIncluded) {
        this.feesIncluded = feesIncluded;
    }

    /**
     * Gets the value of the expirationDate property.
     *
     * @return
     *     possible object is
     *     {@link Calendar }
     *
     */
    @XmlElement(name = "expiration_date")
    @JsonIgnore
    public String getExpirationDateString() {
        return expirationDateString;
    }

    /**
     * Sets the value of the expirationDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Calendar }
     *
     */
    public void setExpirationDateString(String value) {
        this.expirationDateString = value;
    }


    @JsonProperty(value = "expiration_date")
    @XmlTransient
    public Calendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Calendar }
     *
     */
    public void setExpirationDate(Calendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the destinationServiceTag property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @JsonProperty(value = "destination_service_tag")
    @XmlElement(name = "destination_service_tag")
    public String getDestinationServiceTag() {
        return destinationServiceTag;
    }

    /**
     * Sets the value of the destinationServiceTag property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDestinationServiceTag(String value) {
        this.destinationServiceTag = value;
    }

    /**
     * Gets the value of the corridorTag property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @JsonProperty(value = "corridor_tag")
    @XmlElement(name = "corridor_tag")
    public String getCorridorTag() {
        return corridorTag;
    }

    /**
     * Sets the value of the corridorTag property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCorridorTag(String value) {
        this.corridorTag = value;
    }

    @JsonProperty(value = "additional_data_list")
    @XmlElement(name = "additional_data_list")

    public  AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }


    /**
     * Gets the value of the chargedAmount property.
     *
     * @return
     *     possible object is
     *     {@link ChargedAmount }
     *
     */
    @JsonProperty(value = "charged_amount")
    @XmlElement(name = "charged_amount")
    public ChargedAmount getChargedAmount() {
        return chargedAmount;
    }

    /**
     * Sets the value of the chargedAmount property.
     *
     * @param value
     *     allowed object is
     *     {@link ChargedAmount }
     *
     */
    public void setChargedAmount(ChargedAmount value) {
        this.chargedAmount = value;
    }

    /**
     * Gets the value of the creditedAmount property.
     *
     * @return
     *     possible object is
     *     {@link CreditedAmount }
     *
     */
    @JsonProperty(value = "credited_amount")
    @XmlElement(name = "credited_amount")
    public CreditedAmount getCreditedAmount() {
        return creditedAmount;
    }

    /**
     * Sets the value of the creditedAmount property.
     *
     * @param value
     *     allowed object is
     *     {@link CreditedAmount }
     *
     */
    public void setCreditedAmount(CreditedAmount value) {
        this.creditedAmount = value;
    }

    /**
     * Gets the value of the principalAmount property.
     *
     * @return
     *     possible object is
     *     {@link PrincipalAmount }
     *
     */
    @JsonProperty(value = "principal_amount")
    @XmlElement(name = "principal_amount")
    public PrincipalAmount getPrincipalAmount() {
        return principalAmount;
    }

    /**
     * Sets the value of the principalAmount property.
     *
     * @param value
     *     allowed object is
     *     {@link PrincipalAmount }
     *
     */
    public void setPrincipalAmount(PrincipalAmount value) {
        this.principalAmount = value;
    }

    @JsonProperty(value = "quote_fx_rate")
    @XmlElement(name="quote_fx_rate")
    public String getQuoteFxRate() {
        return quoteFxRate;
    }

    /**
     * Gets the value of the confirmationExpiryTime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @XmlElement(name = "confirmation_expiry_time")
    @JsonIgnore
    public String getConfirmationExpiryTimeString() {
        return confirmationExpiryTimeString;
    }

    /**
     * Sets the value of the confirmationExpiryTime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setConfirmationExpiryTimeString(String value) {
        this.confirmationExpiryTimeString = value;
    }
    /**
     * Gets the value of the confirmationExpiryTime property.
     *
     * @return
     *     possible object is
     *     {@link Calendar }
     *
     */
    @XmlTransient
    @JsonProperty(value = "confirmation_expiry_time")
    public Calendar getConfirmationExpiryTime() {
        return confirmationExpiryTime;
    }

    /**
     * Sets the value of the confirmationExpiryTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Calendar }
     *
     */
    public void setConfirmationExpiryTime(Calendar value) {
        this.confirmationExpiryTime = value;
    }
}
