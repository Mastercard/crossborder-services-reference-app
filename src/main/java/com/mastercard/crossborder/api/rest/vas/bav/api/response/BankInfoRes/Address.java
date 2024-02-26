package com.mastercard.crossborder.api.rest.vas.bav.api.response.BankInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@JsonPropertyOrder(value = {"line1", "line2", "city", "countrySubdivision", "postalCode", "country"})
@XmlType(name = "Address", propOrder = {"line1", "line2", "city", "countrySubdivision", "postalCode", "country"})
public  class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String addrLine1 = null;
	private String addrLine2 = null;
	private String city = null;
	private String postalCode = null;
	@JsonIgnoreProperties(ignoreUnknown=true)
	private String countrySubdivision = null;
	private String country = null;

	@JsonProperty(value = "line1")
	@XmlElement(name = "line1")
	public String getLine1() {
		return addrLine1;
	}

	public void setLine1(final String addrLine1) {
		this.addrLine1 = addrLine1;
	}


	@JsonProperty(value = "line2")
	@XmlElement(name = "line2")
	public String getLine2() {
		return addrLine2;
	}

	public void setLine2(final String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	@JsonProperty(value = "city")
	@XmlElement(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@JsonProperty(value = "countrySubdivision")
	@XmlElement(name = "countrySubdivision")
	public String getCountrySubdivision() {
		return countrySubdivision;
	}

	public void setCountrySubdivision(final String countrySubdivision) {
		this.countrySubdivision = countrySubdivision;
	}

	@JsonProperty(value = "postalCode")
	@XmlElement(name = "postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}


	@JsonProperty(value = "country")
	@XmlElement(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

}
