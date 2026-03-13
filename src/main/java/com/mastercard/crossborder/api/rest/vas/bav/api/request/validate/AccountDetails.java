package com.mastercard.crossborder.api.rest.vas.bav.api.request.validate;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.Bic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonTypeName("accountDetails")
public class AccountDetails {

    private String accountCurrency;
    private AccountHolder accountHolder;
    private Bic bic;
}
