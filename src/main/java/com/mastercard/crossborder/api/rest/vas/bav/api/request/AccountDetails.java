package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetails {
    private AccountHolder accountHolder;
    private String accountCurrency;
    private Bic bic;
}
