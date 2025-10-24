package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

}
