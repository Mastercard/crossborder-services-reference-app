package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.request.RemittanceRequest;
import com.mastercard.crossborder.api.rest.response.EncryptedPayload;
import com.mastercard.crossborder.api.rest.response.RemittanceResponse;
import com.mastercard.crossborder.api.service.EncryptionService;
import com.mastercard.crossborder.api.service.RestClientService;
import com.mastercard.crossborder.api.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import java.util.Map;
import static com.mastercard.crossborder.api.constants.MastercardHttpHeaders.ENCRYPTED_HEADER;

/*
    This class is to make a call to payment API.
    There are two ways by which payment can be made
    Make a payment in one shot - which does not require a separate quotes call
    Make a payment with quote - which requires to request a quote and use that proposalId to make payment
 */
@Component
public class RemittanceAPI {
    private static final Logger logger = LoggerFactory.getLogger(RemittanceAPI.class);

    public static final String PAYMENT = "/send/v1/partners/{partner-id}/crossborder/payment";

    @Autowired
    RestClientService restClientService;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    MastercardApiConfig mastercardApiConfig;

    @Autowired
    CommonUtils commonUtils;

    public RemittanceResponse makePayment(HttpHeaders headers, Map<String, Object> requestParams, RemittanceRequest payment) throws ServiceException {

        logger.info("Calling payment API");
        String requestStr = commonUtils.convertToString(headers, payment);
        return (RemittanceResponse) restClientService.service(PAYMENT, headers, HttpMethod.POST, requestParams, requestStr, RemittanceResponse.class);
    }

    public RemittanceResponse makePaymentWithEncryption(HttpHeaders headers, Map<String, Object> requestParams, RemittanceRequest payment) throws ServiceException {

        logger.info("Calling payment API with Encryption");

        String requestStr = commonUtils.convertToString(headers, payment);
        /*Encrypt the request payload and return */
        requestStr = encryptionService.getEncryptedRequestBody(headers, requestStr);
        headers.add(ENCRYPTED_HEADER.toString(), "true");
        EncryptedPayload response = (EncryptedPayload) restClientService.service(PAYMENT, headers, HttpMethod.POST, requestParams, requestStr, EncryptedPayload.class);
        return (RemittanceResponse) encryptionService.getDecryptedResponse(response, headers, RemittanceResponse.class);
    }
}
