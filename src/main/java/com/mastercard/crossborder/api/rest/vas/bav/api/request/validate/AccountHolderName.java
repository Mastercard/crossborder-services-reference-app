package com.mastercard.crossborder.api.rest.vas.bav.api.request.validate;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("name")
@ToString
@Getter
@Setter
public class AccountHolderName {
    private String firstName;
    private String middleName;
    private String lastName;
}
