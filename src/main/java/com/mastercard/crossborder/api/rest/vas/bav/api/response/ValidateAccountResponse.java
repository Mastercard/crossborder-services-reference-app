package com.mastercard.crossborder.api.rest.vas.bav.api.response;

import com.mastercard.crossborder.api.rest.vas.bav.api.response.validateRes.ReceivingEligibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ValidateAccountResponse {
    private String refId;
    private String status;
    private String message;
    private AccountMatch accountMatch;
    private ReceivingEligibility receivingEligibility;
}
