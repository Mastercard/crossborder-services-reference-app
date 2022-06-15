package com.mastercard.crossborder.api.rest.response;

import java.io.Serializable;

public class ReleasedReversedAmount implements Serializable {
  private static final long serialVersionUID = 1L;

  private String amount;
  private String currency;

  public String getAmount() {
    return amount;
  }
  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }

}

