package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.ValidateReq.ValidateRequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatusValidation {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ValidateRequestType requestType;

    private AccountUri accountUri;

    private AccountDetails accountDetails;
}
