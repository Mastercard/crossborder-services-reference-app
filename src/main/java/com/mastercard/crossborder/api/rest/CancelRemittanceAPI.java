package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.request.CancelRemittance;
import com.mastercard.crossborder.api.rest.response.CancelResponse;
import com.mastercard.crossborder.api.rest.response.EncryptedPayload;
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

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    MastercardApiConfig mastercardApiConfig;

    @Autowired
    CommonUtils commonUtils;

    public CancelResponse cancelPayment(HttpHeaders headers, Map<String, Object> requestParams, CancelRemittance cancelRequest) throws ServiceException {
        logger.info("Calling cancel payment API");
        String requestStr = commonUtils.convertToString(headers, cancelRequest);
        return (CancelResponse) restClientService.service(CANCEL_REMITTANCE, headers, HttpMethod.POST, requestParams, requestStr, CancelResponse.class);
    }

    public CancelResponse cancelPaymentWithEncryption(HttpHeaders headers, Map<String, Object> requestParams, CancelRemittance cancelRequest ) throws ServiceException {
        logger.info("Calling cancel payment API with Encryption");
        String requestStr = commonUtils.convertToString(headers, cancelRequest);
        /*Encrypt the request payload and return */
        requestStr = encryptionService.getEncryptedRequestBody(headers, requestStr);
        headers.add(ENCRYPTED_HEADER.toString(), "true");
        EncryptedPayload response = (EncryptedPayload) restClientService.service(CANCEL_REMITTANCE, headers, HttpMethod.POST, requestParams, requestStr, EncryptedPayload.class);
        return (CancelResponse) encryptionService.getDecryptedResponse(response, headers, CancelResponse.class);
    }
}
