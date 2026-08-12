package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountUri {
    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "value")
    private String value;

}
