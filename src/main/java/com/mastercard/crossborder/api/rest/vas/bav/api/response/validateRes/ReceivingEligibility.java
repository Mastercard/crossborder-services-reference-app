package com.mastercard.crossborder.api.rest.vas.bav.api.response.validateRes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReceivingEligibility {

    private CrossBorder crossBorder;
    private String paymentSystem;
    private String product;
    private AccountStatus status;
}

