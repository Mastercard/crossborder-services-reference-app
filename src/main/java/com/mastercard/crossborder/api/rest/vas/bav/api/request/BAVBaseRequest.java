package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.ToString;

@ToString
public abstract class BAVBaseRequest {
    private String clientId;
    private String partnerRefId;
    private String correlationId;
    private String apiName;
    private Long requestId;
    private String responseCode;
    private Boolean simulationSwitch;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPartnerRefId() {
        return partnerRefId;
    }

    public void setPartnerRefId(String partnerRefId) {
        this.partnerRefId = partnerRefId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Boolean getSimulationSwitch() {
        return simulationSwitch;
    }

    public void setSimulationSwitch(Boolean simulationSwitch) {
        this.simulationSwitch = simulationSwitch;
    }
}
