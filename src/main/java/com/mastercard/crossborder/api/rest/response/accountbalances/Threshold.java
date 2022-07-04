package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "high",
    "low"
})
public class Threshold implements Serializable {
  @JsonProperty("high")
  private BigDecimal high;

  @JsonProperty("low")
  private BigDecimal low;

  @JsonProperty("high")
  public BigDecimal getHigh() {
    return this.high;
  }

  @JsonProperty("high")
  public void setHigh(BigDecimal high) {
    this.high = high;
  }

  @JsonProperty("low")
  public BigDecimal getLow() {
    return this.low;
  }

  @JsonProperty("low")
  public void setLow(BigDecimal low) {
    this.low = low;
  }

}
