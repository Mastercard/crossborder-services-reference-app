package com.mastercard.crossborder.api.rest.vas.bav.api.response.validateRes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CrossBorder {

    private YesNo eligible;
    private YesNo fasterFunds;
}
