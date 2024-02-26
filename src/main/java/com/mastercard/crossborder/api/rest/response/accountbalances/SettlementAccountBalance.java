package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonPropertyOrder(value = { "amount", "currency" })
public class SettlementAccountBalance {
	private String settlementBalanceAmount;
	private String settlementBalanceCurrency;

	@JsonProperty(value = "amount")
	public String getSettlementBalanceAmount() {
		return settlementBalanceAmount;
	}

	public void setSettlementBalanceAmount(String openingBalanceAmount) {
		this.settlementBalanceAmount = openingBalanceAmount;
	}

	@JsonProperty(value = "currency")
	public String getSettlementBalanceCurrency() {
		return settlementBalanceCurrency;
	}

	public void setSettlementBalanceCurrency(String settlementBalanceCurrency) {
		this.settlementBalanceCurrency = settlementBalanceCurrency;
	}

	@Override
	public String toString() {
		return "SettlementAccountBalance{" + "settlementBalanceAmount='" + settlementBalanceAmount + '\''
				+ ", settlementBalanceCurrency='" + settlementBalanceCurrency + '\'' + '}';
	}
}
