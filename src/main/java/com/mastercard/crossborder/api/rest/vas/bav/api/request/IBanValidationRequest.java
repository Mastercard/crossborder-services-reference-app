package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.validate.AccountDetails;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.validate.ValidateRequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IBanValidationRequest {

    @JsonInclude(Include.NON_NULL)
    private ValidateRequestType requestType;

    private AccountUri accountUri;

    @JsonInclude(Include.NON_NULL)
    private AccountDetails accountDetails;

    public IBanValidationRequest(AccountUri accountUri) {
        this.accountUri = accountUri;
    }

}
