package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BegFieldDTO {

    private String begFieldMappingId;
    private String endPointId;
    private String region;
    private Boolean recipientNotification;
    private String channelReach;
    private String deliveryTime;
    private String notes;
    private String country;
    private String channels;
    private String onTheFlyWalletCreation;
    private String registrationRequirements;
    private Boolean isInternal;
    //Need to do its mapping from the RSD API Response
    private String serviceType;
    private String paymentType;
    private String destinationCurrency;
}
