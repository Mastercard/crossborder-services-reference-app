package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class IBanGenerationResponse {
    IbanDetails ibanDetails;

    public IBanGenerationResponse() {
    }

    public IBanGenerationResponse(IbanDetails ibanDetails) {
        this.ibanDetails = ibanDetails;
    }

    public IbanDetails getIbanDetails() {
        return ibanDetails;
    }

    public void setIbanDetails(IbanDetails ibanDetails) {
        this.ibanDetails = ibanDetails;
    }

    @Override
    public String toString() {
        return "IBanGenerationResponse{" +
                "ibanDetails=" + ibanDetails +
                '}';
    }
}
