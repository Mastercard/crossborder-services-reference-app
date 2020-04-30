package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * This class is represents the Address fields for Sender and receiver.
 *
 * @author e037676
 *
 */

@JsonPropertyOrder(value = {"line1", "line2", "city", "countrySubdivision", "postalCode", "country"})
@XmlType(name = "Address", propOrder = {"line1", "line2", "city", "countrySubdivision", "postalCode", "country"})
public  class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String addrLine1 = null;
	private String addrLine2 = null;
	private String city = null;
	private String postalCode = null;
	private String countrySubdivision = null;
	private String country = null;
	public Address() {
		//default constructor
	}

	@JsonProperty(value = "line1")
	@XmlElement(name = "line1", required = false, nillable = false)
	public String getLine1() {
		return addrLine1;
	}

	public void setLine1(final String addrLine1) {
		this.addrLine1 = addrLine1;
	}


	@JsonProperty(value = "line2")
	@XmlElement(name = "line2", required = false, nillable = false)
	public String getLine2() {
		return addrLine2;
	}

	public void setLine2(final String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	@JsonProperty(value = "city")
	@XmlElement(name = "city", required = false, nillable = false)
	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@JsonProperty(value = "country_subdivision")
	@XmlElement(name = "country_subdivision", required = false, nillable = false)
	public String getCountrySubdivision() {
		return countrySubdivision;
	}

	public void setCountrySubdivision(final String countrySubdivision) {
		this.countrySubdivision = countrySubdivision;
	}

	@JsonProperty(value = "postal_code")
	@XmlElement(name = "postal_code", required = false, nillable = false)
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}


	@JsonProperty(value = "country")
	@XmlElement(name = "country", required = false, nillable = false)
	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

}
