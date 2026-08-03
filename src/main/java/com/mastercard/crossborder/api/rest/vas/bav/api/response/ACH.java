package com.mastercard.crossborder.api.rest.vas.bav.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Represents ACH (Automated Clearing House) information for a bank account.
 * <p>
 * This class contains details about the ACH enablement status and the preferred routing number.
 * </p>
 */

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "enabled", "preferredRoutingNumber"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ACH implements Serializable {

    /**
     * Indicates whether ACH transfers are enabled.
     */
    private boolean enabled;

    /**
     * The preferred routing number for ACH transfers.
     */
    private String preferredRoutingNumber;

    /**
     * Returns a string representation of the ACH object.
     *
     * @return a string containing the enabled status and preferred routing number.
     */
    @Override
    public String toString() {
        return "ACH{" +
                "enabled=" + enabled +
                ", preferredRoutingNumber='" + preferredRoutingNumber + '\'' +
                '}';
    }
}
