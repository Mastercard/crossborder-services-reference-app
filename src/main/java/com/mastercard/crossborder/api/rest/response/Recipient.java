package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"fullName", "dateOfBirth", "placeOfBirth", "nationality", "governmentId","fullAddress","additionalDocuments","additionalQuestion","sourceOfIncome" })
public class Recipient implements Serializable{

   private static final long serialVersionUID = 1L;

   private FullName fullName;
   private DateOfBirth dateOfBirth;
   private PlaceOfBirth placeOfBirth;
   private Nationality nationality;
   private GovernmentId governmentId;
   private FullAddress fullAddress;
   private SourceOfIncome sourceOfIncome;
   private AdditionalDocuments additionalDocuments;
   private AdditionalQuestion additionalQuestion;

   @JsonProperty(value = "fullName")
   public FullName getFullName() {
      return fullName;
   }

   public void setFullName(FullName fullName) {
      this.fullName = fullName;
   }

   @JsonProperty(value = "dateOfBirth")
   public DateOfBirth getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(DateOfBirth dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   @JsonProperty(value = "placeOfBirth")
   public PlaceOfBirth getPlaceOfBirth() {
      return placeOfBirth;
   }

   public void setPlaceOfBirth(PlaceOfBirth placeOfBirth) {
      this.placeOfBirth = placeOfBirth;
   }

   @JsonProperty(value = "nationality")
   public Nationality getNationality() {
      return nationality;
   }

   public void setNationality(Nationality nationality) {
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
   public SourceOfIncome getSourceOfIncome() {
      return sourceOfIncome;
   }

   public void setSourceOfIncome(SourceOfIncome sourceOfIncome) {
      this.sourceOfIncome = sourceOfIncome;
   }

   @JsonProperty(value = "additionalDocuments")
   public AdditionalDocuments getAdditionalDocuments() {
      return additionalDocuments;
   }

   public void setAdditionalDocuments(AdditionalDocuments additionalDocuments) {
      this.additionalDocuments = additionalDocuments;
   }

   @JsonProperty(value = "additionalQuestion")
   public AdditionalQuestion getAdditionalQuestion() {
      return additionalQuestion;
   }

   public void setAdditionalQuestion(AdditionalQuestion additionalQuestion) {
      this.additionalQuestion = additionalQuestion;
   }
}
