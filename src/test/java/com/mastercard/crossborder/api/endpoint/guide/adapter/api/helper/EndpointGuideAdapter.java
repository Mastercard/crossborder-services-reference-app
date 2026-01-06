package com.mastercard.crossborder.api.endpoint.guide.adapter.api.helper;

import java.util.HashMap;
import java.util.Map;

public class EndpointGuideAdapter {

    public static Map<String, Object> getCorrectRequestParams() {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("paymentType", "P2P");
        requestParams.put("destinationPaymentInstrument", "BANK");
        requestParams.put("destinationCountry", "ARE");
        requestParams.put("destinationCurrency", "AED");
        return requestParams;
    }

    public static Map<String, Object> getBegRequestParams() {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("paymentType", "P2P");
        requestParams.put("destinationPaymentInstrument", "BANK");
        requestParams.put("destinationCountry", "PAK");
        requestParams.put("destinationCurrency", "PKR");
        return requestParams;
    }

    public static Map<String, Object> getIncorrectRequestParams() {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("paymentType", "P2P");
        requestParams.put("destinationPaymentInstrument", "BANK");
        requestParams.put("destinationCountry", "USA");
        requestParams.put("destinationCurrency", "INR");
        return requestParams;
    }
}
