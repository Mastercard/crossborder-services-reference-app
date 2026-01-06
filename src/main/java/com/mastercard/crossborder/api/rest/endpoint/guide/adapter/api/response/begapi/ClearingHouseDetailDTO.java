package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClearingHouseDetailDTO {
    private String clearingHouseDetailId;
    private String endpointId;
    private String name;
    private String cutoffTime;
    private String processingStartTime;
    private List<String> processingDaysOfWeeks;
    private AmountDTO maxLimit;
    private AmountDTO minLimit;
    private String deliveryTime;
}
