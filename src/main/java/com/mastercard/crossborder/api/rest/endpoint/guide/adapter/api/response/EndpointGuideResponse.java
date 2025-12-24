package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi.Business;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;


@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EndpointGuideResponse extends BaseResponse {
    private PaymentType paymentType;
    private String destinationCountry;
    private String destinationCurrency;
    private DestinationPaymentInstrument destinationPaymentInstrument;
    private Technical technical;
    private Business business;

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY).writeValueAsString(this);
    }
}

