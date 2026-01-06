package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class ReceiveAmountLimitDTO {
    private String receiveAmountLimitId;
    private String endpointId;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private String currency;
    private String limitType;
}
