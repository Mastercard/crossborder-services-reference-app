package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.request.QuotesRequest;
import com.mastercard.crossborder.api.rest.response.EncryptedPayload;
import com.mastercard.crossborder.api.rest.response.QuotesResponse;
import com.mastercard.crossborder.api.service.EncryptionService;
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
public class QuotesAPI {

    @Autowired
    RestClientService restClientService;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    MastercardApiConfig mastercardApiConfig;

    private static final Logger logger = LoggerFactory.getLogger(QuotesAPI.class);

    public static final String QUOTES = "/send/v1/partners/{partner-id}/crossborder/quotes" ;

    public QuotesResponse getQuote(HttpHeaders headers, Map<String, Object> requestParams, QuotesRequest quotesRequest) throws ServiceException {
        logger.info("Calling Quotes API");
        String requestStr = getRequestString(headers, quotesRequest);
        return (QuotesResponse) restClientService.service(QUOTES, headers, HttpMethod.POST, requestParams, requestStr, QuotesResponse.class, false);
    }

    public QuotesResponse getQuoteWithEncryption(HttpHeaders headers, Map<String, Object> requestParams, QuotesRequest quotesRequest) throws ServiceException {
        logger.info("Calling Quotes API with Encryption");

        String requestStr = getRequestString(headers, quotesRequest);
        /*Encrypt the request payload and return */
        requestStr = encryptionService.getEncryptedRequestBody(headers, requestStr);

        EncryptedPayload response = (EncryptedPayload) restClientService.service(QUOTES, headers, HttpMethod.POST, requestParams, requestStr, EncryptedPayload.class, true);
        return (QuotesResponse) encryptionService.getDecryptedResponse(response, headers, QuotesResponse.class);
    }

    private String getRequestString(HttpHeaders headers, QuotesRequest quotesRequest) throws ServiceException {
        return restClientService.convertToString(headers, quotesRequest);
    }

}
