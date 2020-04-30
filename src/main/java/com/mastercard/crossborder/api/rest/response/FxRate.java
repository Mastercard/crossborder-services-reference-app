package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "rate", propOrder = { "providerAssignedId", "eventType", "type", "use",  "fromCurrencyCode", "toCurrencyCode","validFrom", "validTo", "tierIdentifierRate", "tiers", "accountType"})
@XmlRootElement(name = "rate")
public class FxRate  implements Serializable {



        private String type;
        private String use;
        private String fromCurrencyCode;
        private String toCurrencyCode;
        private String validFrom;
        private String validTo;
        private String tierIdentifierRate;
        private String accountType;
        private String eventType;
        private String providerAssignedId;

        private Tiers tiers;

        @JsonProperty(value="type")
        @XmlElement(name = "type", required = true)
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty(value="use")
        @XmlElement(name = "use", required = true)
        public String getUse() {
            return use;
        }

        public void setUse(String use) {
            this.use = use;
        }

        @JsonProperty(value="from_currency_code")
        @XmlElement(name = "from_currency_code", required = true)
        public String getFromCurrencyCode() {
            return fromCurrencyCode;
        }

        public void setFromCurrencyCode(String fromCurrencyCode) {
            this.fromCurrencyCode = fromCurrencyCode;
        }

        @JsonProperty(value="to_currency_code")
        @XmlElement(name = "to_currency_code", required = true)
        public String getToCurrencyCode() {
            return toCurrencyCode;
        }

        public void setToCurrencyCode(String toCurrencyCode) {
            this.toCurrencyCode = toCurrencyCode;
        }


        @JsonProperty(value="valid_from")
        @XmlElement(name = "valid_from", required = true)
        public String getValidFrom() {
            return validFrom;
        }

        public void setValidFrom(String validFrom) {
            this.validFrom = validFrom;
        }


        @JsonProperty(value="valid_to")
        @XmlElement(name = "valid_to", required = true)
        public String getValidTo() {
            return validTo;
        }

        public void setValidTo(String validTo) {
            this.validTo = validTo;
        }

        @JsonProperty(value="tier_identifier_rate")
        @XmlElement(name = "tier_identifier_rate", nillable = true)
        public String getTierIdentifierRate() {
            return tierIdentifierRate;
        }

        public void setTierIdentifierRate(String tierIdentifierRate) {
            this.tierIdentifierRate = tierIdentifierRate;
        }


        @JsonProperty(value="tiers")
        @XmlElement(name = "tiers", required = true)
        public Tiers getTiers() {
            return tiers;
        }

        public void setTiers(Tiers tiers) {
            this.tiers = tiers;
        }


        @JsonProperty(value="account_type")
        @XmlElement(name = "account_type")
        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }


        @JsonProperty(value="event_type")
        @XmlElement(name = "event_type")
        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        @JsonProperty(value="partner_id")
        @XmlElement(name = "partner_id")
        public String getProviderAssignedId() {
            return providerAssignedId;
        }

        public void setProviderAssignedId(String providerAssignedId) {
            this.providerAssignedId = providerAssignedId;
        }

    }
