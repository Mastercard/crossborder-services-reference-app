package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountHolder {

    private Name name;
    private String governmentId;

}
