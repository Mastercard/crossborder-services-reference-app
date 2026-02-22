package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BusinessNew {

    private String region;
    private List<String> supportedCustomerTypes;
    private String supportedFxModel;
    private Boolean recipientNotification;
    private List<String> flowTypes;
    private String channelReach;
    private String deliveryTime;
    private String notes;
    private List<String> brands;
    private String channels;
    private String onFlyWalletCreation;
    private List<String> restrictedFlows;
    private String registrationRequirements;
    private ClearingPaymentInformationDTO clearingPaymentInformation;
    private CashPickupDTO cashPickup;
    private ReceivingInstitutionDTO receivingInstitution;
    private LimitDTO sendAmountLimit;
    private LimitDTO receiveAmountLimit;
    private List<ClearingHouseDetailDTO> clearingHouseDetails;
    private List<DocumentationDTO> documentations;
    private List<PreApprovedRequirementDTO> preApprovedRequirements;

}
