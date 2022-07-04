package com.mastercard.crossborder.api.rest.response.accountbalances;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder(value = { "openingBalance", "processedAmount", "reservedBalance", "availableBalance", "settlementAccountBalance", "thresholdAmount", "thresholds"})
public class BalanceDetails implements Serializable {
    private OpeningBalance openingBalance;
    private ProcessedAmount processedAmount;
    private ReservedBalance reservedBalance;
    private AvailableBalance availableBalance;
    private SettlementAccountBalance settlementAccountBalance;
    private ThresholdAmount thresholdAmount;
    private List<BalanceThreshold> thresholds;


    public OpeningBalance getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(OpeningBalance openingBalance) {
        this.openingBalance = openingBalance;
    }

    public ProcessedAmount getProcessedAmount() {
        return processedAmount;
    }

    public void setProcessedAmount(ProcessedAmount processedAmount) {
        this.processedAmount = processedAmount;
    }

    public ReservedBalance getReservedBalance() {
        return reservedBalance;
    }

    public void setReservedBalance(ReservedBalance reservedBalance) {
        this.reservedBalance = reservedBalance;
    }

    public AvailableBalance getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(AvailableBalance availableBalance) {
        this.availableBalance = availableBalance;
    }

    public ThresholdAmount getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(ThresholdAmount thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public List<BalanceThreshold> getThresholds() {
        return thresholds;
    }

    public void setThresholds(List<BalanceThreshold> thresholds) {
        this.thresholds = thresholds;
    }

    public SettlementAccountBalance getSettlementAccountBalance() {
        return settlementAccountBalance;
    }

    public void setSettlementAccountBalance(SettlementAccountBalance settlementAccountBalance) {
        this.settlementAccountBalance = settlementAccountBalance;
    }

    @Override
    public String toString() {
        return "BalanceDetails{" +
                "openingBalance=" + openingBalance +
                ", processedAmount=" + processedAmount +
                ", reservedBalance=" + reservedBalance +
                ", availableBalance=" + availableBalance +
                ", settlementAccountBalance=" + settlementAccountBalance +
                ", thresholdAmount=" + thresholdAmount +
                '}';
    }
}
