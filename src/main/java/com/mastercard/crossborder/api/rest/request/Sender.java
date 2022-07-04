package com.mastercard.crossborder.api.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"fullName", "dateOfBirth", "placeOfBirth", "nationality", "governmentId", "fullAddress", "sourceOfIncome", "additionalQuestion","additionalDocuments"})
public class Sender implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private GovernmentId governmentId;
    private FullAddress fullAddress;
    private String sourceOfIncome;
    private String additionalQuestion;
    private String additionalDocuments;

    @JsonProperty(value = "fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty(value = "dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty(value = "placeOfBirth")
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @JsonProperty(value = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty(value = "governmentId")
    public GovernmentId getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(GovernmentId governmentId) {
        this.governmentId = governmentId;
    }

    @JsonProperty(value = "fullAddress")
    public FullAddress getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(FullAddress fullAddress) {
        this.fullAddress = fullAddress;
    }

    @JsonProperty(value = "sourceOfIncome")
    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }


    @JsonProperty(value = "additionalQuestion")
    public String getAdditionalQuestion() {
        return additionalQuestion;
    }

    public void setAdditionalQuestion(String additionalQuestion) {
        this.additionalQuestion = additionalQuestion;
    }

    @JsonProperty(value = "additionalDocuments")
    public String getAdditionalDocuments() {
        return additionalDocuments;
    }

    public void setAdditionalDocuments(String additionalDocuments) {
        this.additionalDocuments = additionalDocuments;
    }
}
