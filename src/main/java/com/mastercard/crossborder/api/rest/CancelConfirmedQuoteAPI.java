package com.mastercard.crossborder.api.rest;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.request.CancelConfirmedQuoteRequest;
import com.mastercard.crossborder.api.rest.response.CancelConfirmedQuoteResponse;
import com.mastercard.crossborder.api.service.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
    This class is to make a Quotes API call.
    Quotes can be requested for given partnerID, amount and currency.
    Same quote can be used while making a payment by passing a proposalID returned in quotes response.
 */

@Component
public class CancelConfirmedQuoteAPI {

    @Autowired
    RestClientService restClientService;

    private static final Logger logger = LoggerFactory.getLogger(CancelConfirmedQuoteAPI.class);

    public static final String CANCEL_CONFIRMED_QUOTE = "/send/partners/{partner_id}/crossborder/quotes/cancellations" ;

    public CancelConfirmedQuoteResponse getQuote(HttpHeaders headers, Map<String, Object> requestParams, CancelConfirmedQuoteRequest cancelConfirmedQuoteRequest) throws ServiceException {
        logger.info("Calling Cancel Confirmed Quote API");
        return (CancelConfirmedQuoteResponse) restClientService.service(CANCEL_CONFIRMED_QUOTE, headers, HttpMethod.POST, requestParams, cancelConfirmedQuoteRequest, CancelConfirmedQuoteResponse.class);

    }
    public CancelConfirmedQuoteResponse getQuoteWithEncryption(HttpHeaders headers, Map<String, Object> requestParams, CancelConfirmedQuoteRequest cancelConfirmedQuoteRequest) throws ServiceException {
        logger.info("Calling Cancel Confirmed Quote API With Encryption");
        return (CancelConfirmedQuoteResponse) restClientService.serviceEncryption(CANCEL_CONFIRMED_QUOTE, headers, HttpMethod.POST, requestParams, cancelConfirmedQuoteRequest, CancelConfirmedQuoteResponse.class);
    }

}
