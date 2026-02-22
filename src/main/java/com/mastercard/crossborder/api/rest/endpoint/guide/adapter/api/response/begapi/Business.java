package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Business {

    private BegFieldDTO begField;
    private String endPointId;
    private String status;
    private List<SupportedCustomerTypeDTO> supportedCustomerType;
    private List<BrandDTO> brand;
    private List<DocumentationDTO> documentation;
    private List<PreApprovedRequirementDTO> preApprovedRequirement;
    private CashPickupDTO cashPickup;
    private List<ClearingHouseDetailDTO> clearingHouseDetail;
    private List<ClearingPaymentInformationDTO> clearingPaymentInFormation;
    private List<FlowTypeDTO> flowType;
    private ReceiveAmountLimitDTO receiveAmountLimit;
    private ReceivingInstitutionDTO receivingInstitution;
    private List<RestrictedFlowDTO> restrictedFlow;
    private SendAmountLimitDTO sendAmountLimit;
    private List<SupportedFxModelDTO> supportedFxModel;

    @Override
    public String toString() {
        return "Business{" +
                "begField=" + begField +
                ", supportedCustomerType=" + supportedCustomerType +
                ", brand=" + brand +
                ", documentation=" + documentation +
                ", preApprovedRequirement=" + preApprovedRequirement +
                ", cashPickup=" + cashPickup +
                ", clearingHouseDetail=" + clearingHouseDetail +
                ", clearingPaymentInFormation=" + clearingPaymentInFormation +
                ", flowType=" + flowType +
                ", receiveAmountLimit=" + receiveAmountLimit +
                ", receivingInstitution=" + receivingInstitution +
                ", restrictedFlow=" + restrictedFlow +
                ", sendAmountLimit=" + sendAmountLimit +
                ", supportedFxModel=" + supportedFxModel +
                ", status='" + status + '\'' +
                '}';
    }
}
