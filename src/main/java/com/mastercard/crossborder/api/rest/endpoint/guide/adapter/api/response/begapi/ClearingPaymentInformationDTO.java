package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class ClearingPaymentInformationDTO  {

    private String clearingPaymentInformationId;
    private String endpointId;
    private Boolean remitterName;
    private Boolean paymentInformation;
    private int characterLimit;
}
