package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.Calendar;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName(value = "RetrieveQuoteStatus")
public class RetrieveQuoteStatus extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionReference;
    private String resourceType = QUOTE_OBJECT;
    private Calendar created;
    private ProposedQuote quote;
    private String partnerName;
    private static final String QUOTE_OBJECT = "quote";


    @JsonProperty(value = "transactionReference")
    @Override
    public String getTransactionReference() {
        return transactionReference;
    }
    @Override
    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    @JsonProperty(value = "resourceType")
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }


    @JsonProperty(value = "created")
    public Calendar getCreated() {
        return created;
    }
    public void setCreated(Calendar created) {
        this.created = created;
    }

    @JsonProperty("quote")
    public ProposedQuote getQuote() {
        return quote;
    }

    public void setQuote(ProposedQuote quote) {
        this.quote = quote;
    }

    @JsonProperty(value = "partnerName")
    public String getPartnerName() { return partnerName; }

    public void setPartnerName(String partnerName) { this.partnerName = partnerName; }


}
