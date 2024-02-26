package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.service.RestClientService;
import com.mastercard.crossborder.api.rest.request.CancelRemittance;
import com.mastercard.crossborder.api.rest.response.CancelResponse;
import com.mastercard.crossborder.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;
/*
    This class is to call to cancel payment API.
    If the payment is initiated and it is not yet successful
    then cancel payment will cancel the process of payment
 */
@Component
public class CancelRemittanceAPI {

    private static final Logger logger = LoggerFactory.getLogger(CancelRemittanceAPI.class);

    public static final String CANCEL_REMITTANCE = "/send/v1/partners/{partner-id}/crossborder/{payment-id}/cancel";

    @Autowired
    RestClientService restClientService;

    public CancelResponse cancelPayment(HttpHeaders headers, Map<String, Object> requestParams, CancelRemittance cancelRequest ) throws ServiceException {
         logger.info("Calling cancel payment API");
         return (CancelResponse) restClientService.service(CANCEL_REMITTANCE, headers, HttpMethod.POST, requestParams, cancelRequest, CancelResponse.class);


    }

    public CancelResponse cancelPaymentWithEncryption(HttpHeaders headers, Map<String, Object> requestParams, CancelRemittance cancelRequest ) throws ServiceException {
        logger.info("Calling cancel payment API that supports encryption ");
        return (CancelResponse) restClientService.serviceEncryption(CANCEL_REMITTANCE, headers, HttpMethod.POST, requestParams, cancelRequest, CancelResponse.class);

    }

}
