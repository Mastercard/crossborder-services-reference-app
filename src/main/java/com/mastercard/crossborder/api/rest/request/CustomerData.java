package com.mastercard.crossborder.api.rest.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

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
        //default constructor
	}

    @XmlElement(name = "first_name", required = false, nillable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

    @XmlElement(name = "middle_name", required = false, nillable = false)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    @XmlElement(name = "last_name", required = false, nillable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "nationality", required = false)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @XmlElement(name = "address", required = false, nillable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElement(name = "government_ids", required = false, nillable = false)
    public GovernmentIdData getGovernmentIdData() {
        return governmentIdData;
    }

    public void setGovernmentIdData(GovernmentIdData governmentIdData) {
        this.governmentIdData = governmentIdData;
    }

    @XmlElement(name = "date_of_birth", required = false, nillable = false)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlElement(name = "phone", required = false, nillable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "organization_name", required = false, nillable = false)
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @XmlElement(name="email", required = false, nillable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String receiverEmailAdress) {
        this.email = receiverEmailAdress;
    }
}
