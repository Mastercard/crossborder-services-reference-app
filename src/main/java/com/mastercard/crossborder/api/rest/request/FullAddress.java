package com.mastercard.crossborder.api.rest.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"addressLine1", "addressLine2", "city", "countrySubdivision","country","postalCode","documents"})
public class FullAddress implements Serializable {

    private static final long serialVersionUID = 1L;


    private String addressLine1;
    private String addressLine2;
    private String city;
    private String countrySubdivision;
    private String country;
    private String postalCode;
    private transient List< Object > documents = new ArrayList<>();

    @JsonProperty(value = "addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty(value = "addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @JsonProperty(value = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty(value = "countrySubdivision")
    public String getCountrySubdivision() {
        return countrySubdivision;
    }

    public void setCountrySubdivision(String countrySubdivision) {
        this.countrySubdivision = countrySubdivision;
    }

    @JsonProperty(value = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty(value = "postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty(value = "documents")
    public List<Object> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Object> documents) {
        this.documents = documents;
    }
}
