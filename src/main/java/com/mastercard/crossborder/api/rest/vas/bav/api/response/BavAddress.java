package com.mastercard.crossborder.api.rest.vas.bav.api.response;

public class BavAddress {
    private String line1;
    private String city;
    private String postalCode;
    private String country;

    public BavAddress() {
    }

    public BavAddress(String line1, String city, String postalCode, String country) {
        this.line1 = line1;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
