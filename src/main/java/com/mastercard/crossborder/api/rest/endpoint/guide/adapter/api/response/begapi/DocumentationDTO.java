package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DocumentationDTO  {
    private String documentationId;
    private String endpointId;
    private String documentationType;
    private Boolean isRequired;
    private String process;
    private String requirementsThreshold;

}
