package com.mastercard.crossborder.api.rest.vas.bav.api.request.validate;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonTypeName("accountHolder")
public class AccountHolder {
    private AccountHolderName name;
    private String governmentId;
}
