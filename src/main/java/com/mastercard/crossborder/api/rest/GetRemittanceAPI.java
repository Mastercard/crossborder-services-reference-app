package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.EncryptedPayload;
import com.mastercard.crossborder.api.rest.response.RemittanceResponse;
import com.mastercard.crossborder.api.service.EncryptionService;
import com.mastercard.crossborder.api.service.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import java.util.Map;

import static com.mastercard.crossborder.api.constants.MastercardHttpHeaders.ENCRYPTED_HEADER;

/*
    This class is to get payment API.
    There are two ways by which get payment can be achieved
    Get payment by payment Id and get payment by transaction reference.
    This can be used to check the status of payment
 */
@Component
public class GetRemittanceAPI {

    private static final Logger logger = LoggerFactory.getLogger(GetRemittanceAPI.class);

    public static final String GET_PAYMENT_BY_ID = "/send/v1/partners/{partner-id}/crossborder/{payment-id}";

    public static final String GET_PAYMENT_BY_REF = "/send/v1/partners/{partner-id}/crossborder?ref={payment-reference}";

    @Autowired
    RestClientService restClientService;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    MastercardApiConfig mastercardApiConfig;

    public RemittanceResponse getPaymentById(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling retrieve payment by ID API");
        return (RemittanceResponse) restClientService.service(GET_PAYMENT_BY_ID, headers, HttpMethod.GET, requestParams, null, RemittanceResponse.class);
    }

    public RemittanceResponse getPaymentByRef(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling retrieve payment by reference API");
        return (RemittanceResponse) restClientService.service(GET_PAYMENT_BY_REF, headers, HttpMethod.GET, requestParams, null, RemittanceResponse.class);
    }

    public RemittanceResponse getPaymentByIdWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling retrieve payment by ID API with Encryption");
        if (mastercardApiConfig.getRunWithEncryptedPayload())
            headers.add(ENCRYPTED_HEADER.toString(), "true");
        EncryptedPayload response = (EncryptedPayload) restClientService.service(GET_PAYMENT_BY_ID, headers, HttpMethod.GET, requestParams, null, EncryptedPayload.class);

        return (RemittanceResponse) encryptionService.getDecryptedResponse(response, headers, RemittanceResponse.class);
    }

    public RemittanceResponse getPaymentByRefWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling retrieve payment by ID API with Encryption");
        headers.add(ENCRYPTED_HEADER.toString(), "true");
        EncryptedPayload response = (EncryptedPayload) restClientService.service(GET_PAYMENT_BY_REF, headers, HttpMethod.GET, requestParams, null, EncryptedPayload.class);

        return (RemittanceResponse) encryptionService.getDecryptedResponse(response, headers, RemittanceResponse.class);
    }
}
