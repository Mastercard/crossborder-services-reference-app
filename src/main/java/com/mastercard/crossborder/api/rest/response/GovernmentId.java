package com.mastercard.crossborder.api.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"type", "number", "documents"})
public class GovernmentId implements Serializable {

   private static final long serialVersionUID = 1L;

   private Type type;
   private Number number;
   private Documents documents;

   @JsonProperty(value = "type")
   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }

   @JsonProperty(value = "number")
   public Number getNumber() {
      return number;
   }

   public void setNumber(Number number) {
      this.number = number;
   }

   @JsonProperty(value = "documents")
   public Documents getDocuments() {
      return documents;
   }

   public void setDocuments(Documents documents) {
      this.documents = documents;
   }
}
