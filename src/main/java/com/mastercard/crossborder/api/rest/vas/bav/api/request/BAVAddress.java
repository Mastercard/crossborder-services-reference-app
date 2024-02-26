package com.mastercard.crossborder.api.rest.vas.bav.api.request;

import lombok.ToString;

@ToString
public class BAVAddress {

    private String city;
    private String countrySubdivision;
    private String postalCode;

    public BAVAddress() {
    }

    public BAVAddress(String city, String countrySubdivision, String postalCode/*, String country*/) {
        this.city = city;
        this.countrySubdivision = countrySubdivision;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountrySubdivision() {
        return countrySubdivision;
    }

    public void setCountrySubdivision(String countrySubdivision) {
        this.countrySubdivision = countrySubdivision;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
