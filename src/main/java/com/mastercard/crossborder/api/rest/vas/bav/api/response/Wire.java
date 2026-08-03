package com.mastercard.crossborder.api.rest.vas.bav.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the Wire information for a bank account.
 * This class contains details about whether wire transfers are enabled
 * and the preferred routing number for wire transfers.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "enabled", "preferredRoutingNumber"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wire {

    /**
     * Indicates whether wire transfers are enabled.
     */
    private boolean enabled;

    /**
     * The preferred routing number for wire transfers.
     */
    private String preferredRoutingNumber;

    /**
     * Returns a string representation of the Wire object.
     *
     * @return a string containing the enabled status and preferred routing number.
     */
    @Override
    public String toString() {
        return "Wire{" +
                "enabled=" + enabled +
                ", preferredRoutingNumber='" + preferredRoutingNumber + '\'' +
                '}';
    }
}
