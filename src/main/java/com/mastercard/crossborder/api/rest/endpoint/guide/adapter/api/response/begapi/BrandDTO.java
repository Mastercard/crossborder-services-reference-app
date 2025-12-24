package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.begapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class BrandDTO {
    private String brandId;
    private String endpointId;
    private String brandName;

    public BrandDTO(String brandName) {
        this.brandName = brandName;
    }
}
