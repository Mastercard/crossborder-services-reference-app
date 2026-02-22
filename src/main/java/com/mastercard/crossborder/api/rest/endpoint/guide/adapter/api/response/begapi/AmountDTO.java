package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AmountDTO {
    private double value;
    private String currency;
}
