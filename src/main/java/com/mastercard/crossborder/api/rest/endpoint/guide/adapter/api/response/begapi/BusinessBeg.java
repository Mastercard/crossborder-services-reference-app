package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BusinessBeg {

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
    @JsonProperty(value="cashPickupInformation", access = JsonProperty.Access.WRITE_ONLY)
    private CashPickupDTO cashPickup;
    private ReceivingInstitutionDTO receivingInstitution;
    private LimitDTO sendTransactionAmountLimit;
    private LimitDTO receiveTransactionAmountLimit;
    @JsonProperty(value = "clearingSystemDetails", access = JsonProperty.Access.WRITE_ONLY)
    private List<ClearingHouseDetailDTO> clearingHouseDetails;
    private List<DocumentationDTO> documentations;
    private List<PreApprovedRequirementDTO> preApprovedRequirements;

}
