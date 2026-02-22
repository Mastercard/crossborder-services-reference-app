package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class SupportedCustomerTypeDTO {
    private String supportedCustomerTypeId;
    private String endpointId;
    private String supportedCustomer;
}
