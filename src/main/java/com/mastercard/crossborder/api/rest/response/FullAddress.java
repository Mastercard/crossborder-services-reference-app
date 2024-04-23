package com.mastercard.crossborder.api.rest.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.io.Serializable;

@JsonPropertyOrder(value = {"request","review","documents","response" })
public class FullAddress implements Serializable {

   private Request request;
   private Review review;
   private Documents documents;
   private Response response;

   @JsonProperty(value = "request")
   public Request getRequest() {
      return request;
   }

   public void setRequest(Request request) {
      this.request = request;
   }

   @JsonProperty(value = "review")
   public Review getReview() {
      return review;
   }

   @JsonProperty(value = "documents")
   public Documents getDocuments() {
      return documents;
   }

   public void setDocuments(Documents documents) {
      this.documents = documents;
   }

   public void setReview(Review review) {
      this.review = review;
   }

   @JsonProperty(value = "response")
   public Response getResponse() {
      return response;
   }

   public void setResponse(Response response) {
      this.response = response;
   }
}
