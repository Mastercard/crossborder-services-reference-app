package com.mastercard.crossborder.api.rest.response;

import com.mastercard.crossborder.api.rest.request.AdditionalData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "BaseResponse", propOrder = {"requestId","transactionReference", "responseCode", "additionalData"})
public class BaseResponse {

    private static final long serialVersionUID = 1L;

    public BaseResponse() {
        //default constructor
    }
    private String requestId;
    private String transactionReference;
    private String responseCode;
    private AdditionalData additionalData;

	@XmlElement(name = "RequestId", required = true, nillable = false)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

	@XmlElement(name = "TransactionReference", required = true, nillable = false)
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

	@XmlElement(name = "status", required = true, nillable = false)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

	@XmlElement(name = "additional_data_list")
    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }
}
