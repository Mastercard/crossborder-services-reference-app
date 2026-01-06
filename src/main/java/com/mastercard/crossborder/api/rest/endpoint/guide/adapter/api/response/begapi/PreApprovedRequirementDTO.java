package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class PreApprovedRequirementDTO {
    private String preApprovedRequirementId;
    private String endpointId;
    private String requirementType;
    private Boolean isRequired;
    private String requestedInformation;
    private String notes;
    private String averageRSPResponseTime;

}
