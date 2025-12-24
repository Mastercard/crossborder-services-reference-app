package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse {
    @JsonIgnore
    private String correlationId;
    @JsonIgnore
    private HttpStatus responseCode;
}
