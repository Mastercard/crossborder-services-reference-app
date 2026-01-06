package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class CashPickupDTO {
    private String cashPickupId;
    private String endpointId;
    private String payoutCapability;
    private String availability;
    private String maxTimeAllowed;
}
