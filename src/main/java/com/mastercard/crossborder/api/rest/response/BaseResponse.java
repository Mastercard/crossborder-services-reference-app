package com.mastercard.crossborder.api.rest.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mastercard.crossborder.api.rest.request.AdditionalData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@JsonPropertyOrder(value = {"requestId","transactionReference", "responseCode", "additionalData"})
@XmlType(name = "BaseResponse", propOrder = {"requestId","transactionReference", "responseCode", "additionalData"})
public class BaseResponse {

    private static final long serialVersionUID = 1L;

    private String requestId;
    private String transactionReference;
    private String responseCode;
    private AdditionalData additionalData;

    @JsonProperty(value = "RequestId")
    @XmlElement(name = "RequestId", required = true)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty(value = "TransactionReference")
    @XmlElement(name = "TransactionReference", required = true)
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    @JsonProperty(value = "status")
    @XmlElement(name = "status", required = true)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty(value = "additional_data_list")
    @XmlElement(name = "additional_data_list")
    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    /**
     * Sets the value of the additionalInformationFields property.
     *
     */
    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

}