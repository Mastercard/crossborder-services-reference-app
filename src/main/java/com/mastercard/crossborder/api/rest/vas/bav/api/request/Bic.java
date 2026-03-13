package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.ACH;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.Wire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bic implements Serializable {
     private String type;
     private String value;
     private ACH ach;
     private Wire wire;
}
