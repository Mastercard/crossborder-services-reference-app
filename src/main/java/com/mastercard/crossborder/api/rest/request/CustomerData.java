package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mastercard.crossborder.api.rest.request.deserializer.GovernmentIdDeserializer;
import com.mastercard.crossborder.api.rest.request.serializer.GovernmentIdSerializer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@JsonPropertyOrder(value = {"firstName","middleName","lastName","organizationName","nationality","address", "governmentIdData", "dateOfBirth", "phone", "email"})
@XmlType(name = "customerdata", propOrder = {"firstName","middleName","lastName","organizationName","nationality","address", "governmentIdData", "dateOfBirth", "phone", "email"})
public  class CustomerData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName = null;
    private String middleName = null;
    private String lastName = null;
    private String nationality= null;
    private Address address = null;
    private GovernmentIdData governmentIdData = null;
    private String dateOfBirth = null;
    private String phone = null;
    private String organizationName = null;

    private String email;

    public CustomerData() {
        //Default constructor
    }

    @JsonProperty(value = "first_name")
    @XmlElement(name = "first_name", required = false, nillable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty(value = "middle_name")
    @XmlElement(name = "middle_name", required = false, nillable = false)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty(value = "last_name")
    @XmlElement(name = "last_name", required = false, nillable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty(value = "nationality")
    @XmlElement(name = "nationality", required = false)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty(value = "address")
    @XmlElement(name = "address", required = false, nillable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty(value = "government_ids")
    @JsonDeserialize(using = GovernmentIdDeserializer.class)
    @JsonSerialize(using = GovernmentIdSerializer.class , include = JsonSerialize.Inclusion.NON_NULL)
    @XmlElement(name = "government_ids", required = false, nillable = false)
    public GovernmentIdData getGovernmentIdData() {
        return governmentIdData;
    }

    public void setGovernmentIdData(GovernmentIdData governmentIdData) {
        this.governmentIdData = governmentIdData;
    }

    @JsonProperty(value = "date_of_birth")
    @XmlElement(name = "date_of_birth", required = false, nillable = false)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty(value = "phone")
    @XmlElement(name = "phone", required = false, nillable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty(value = "organization_name")
    @XmlElement(name = "organization_name", required = false, nillable = false)
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    @JsonProperty(value = "email")
    @XmlElement(name="email", required = false, nillable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String receiverEmailAdress) {
        this.email = receiverEmailAdress;
    }
}
