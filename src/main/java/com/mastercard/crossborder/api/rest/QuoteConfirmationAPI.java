package com.mastercard.crossborder.api.rest;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.request.QuoteConfirmation;
import com.mastercard.crossborder.api.rest.response.QuoteConfirmationResponse;
import com.mastercard.crossborder.api.rest.response.RetrieveQuoteStatus;
import com.mastercard.crossborder.api.service.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import java.util.Map;

/*
    This class is to make a Quotes Confirmation API call.
 */

@Component
public class QuoteConfirmationAPI {


    @Autowired
    RestClientService restClientService; //NOSONAR

    private static final Logger logger = LoggerFactory.getLogger(QuoteConfirmationAPI.class);

    public static final String QUOTE_CONFIRMATION = "/send/partners/{partner-id}/crossborder/quotes/confirmations" ;
    public static final String CANCEL_CONFIRMED_QUOTE = "/send/partners/{partner-id}/crossborder/quotes/cancellations" ;
    public static final String RETRIEVE_CONFIRMED_QUOTE = "/send/partners/{partner-id}/crossborder/quotes/{transaction-reference}/proposals/{proposal-id}";

    public QuoteConfirmationResponse getQuoteConfirmation(HttpHeaders headers, Map<String, Object> requestParams, QuoteConfirmation quoteConfirmRequest) throws ServiceException {
        logger.info("Calling Quote Confirmation API");
        return (QuoteConfirmationResponse) restClientService.service(QUOTE_CONFIRMATION, headers, HttpMethod.POST, requestParams, quoteConfirmRequest, QuoteConfirmationResponse.class);
    }

    public QuoteConfirmationResponse getQuoteConfirmationWithEncryption(HttpHeaders headers, Map<String, Object> requestParams, QuoteConfirmation quoteConfirmRequest) throws ServiceException {
        logger.info("Calling Quote Confirmation API With Encryption");
        return (QuoteConfirmationResponse) restClientService.serviceEncryption(QUOTE_CONFIRMATION, headers, HttpMethod.POST, requestParams, quoteConfirmRequest, QuoteConfirmationResponse.class);
    }

    public QuoteConfirmationResponse cancelConfirmedQuote(HttpHeaders headers, Map<String, Object> requestParams, QuoteConfirmation quoteConfirmRequest) throws ServiceException {
        logger.info("Calling Cancel Confirmed Quote API");
        return (QuoteConfirmationResponse) restClientService.service(CANCEL_CONFIRMED_QUOTE, headers, HttpMethod.POST, requestParams, quoteConfirmRequest, QuoteConfirmationResponse.class);
    }

    public RetrieveQuoteStatus retrieveConfirmedQuote(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {
        logger.info("Calling Retrieve Confirmed Quote API");
        return (RetrieveQuoteStatus) restClientService.service(RETRIEVE_CONFIRMED_QUOTE, headers, HttpMethod.GET, requestParams, null, RetrieveQuoteStatus.class);
    }
}
