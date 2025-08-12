package com.mastercard.crossborder.api.rest.vas.bav.api;

import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.BankInfoLookupRequest;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.IBanValidationRequest;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.IbanCreationDetails;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.BankInfoLookupResponse;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.IBanGenerationResponse;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.ValidateAccountResponse;
import com.mastercard.crossborder.api.service.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BAVApi {

    @Autowired
    RestClientService restClientService; // NOSONAR

    private static final Logger logger = LoggerFactory.getLogger(BAVApi.class);

    public static final String IBAN_GENERATION_URL = "/send/partners/{partner-id}/crossborder/accounts/generate-ibans" ;

    public static final String ACCOUNT_VALIDATION_URL = "/send/partners/{partner-id}/crossborder/accounts/validations" ;

    public static final String BANK_INFO_LOOKUP_URL = "/send/partners/{partner-id}/crossborder/banks/details" ;

    public static final String LOGGER_MESSAGE = "Calling BAV Api";
    /***
     * IBAN Generation methods
     */
    public IBanGenerationResponse generateIBan(HttpHeaders httpHeaders, Map<String, Object> requestParams, IbanCreationDetails request) throws ServiceException {
        logger.info(LOGGER_MESSAGE);
        return (IBanGenerationResponse) restClientService.serviceEncryption(IBAN_GENERATION_URL, httpHeaders, HttpMethod.POST, requestParams, request, IBanGenerationResponse.class);
    }

    public ValidateAccountResponse validateAccount(HttpHeaders httpHeaders, Map<String, Object> requestParams, IBanValidationRequest request) throws ServiceException {
        logger.info(LOGGER_MESSAGE);
        return (ValidateAccountResponse) restClientService.serviceEncryption(ACCOUNT_VALIDATION_URL, httpHeaders, HttpMethod.POST, requestParams, request, ValidateAccountResponse.class);
    }

    public BankInfoLookupResponse getBankDetails(HttpHeaders httpHeaders, Map<String, Object> requestParams, BankInfoLookupRequest request) throws ServiceException {
        logger.info(LOGGER_MESSAGE);
        return (BankInfoLookupResponse) restClientService.serviceEncryption(BANK_INFO_LOOKUP_URL, httpHeaders, HttpMethod.POST, requestParams, request, BankInfoLookupResponse.class);
    }

}
