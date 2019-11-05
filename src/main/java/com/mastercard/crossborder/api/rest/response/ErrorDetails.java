package com.mastercard.crossborder.api.rest.response;


import java.util.List;

public class ErrorDetails {

    private List<ErrorDetail> details;

    public List<ErrorDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorDetail> details) {
        this.details = details;
    }
}
