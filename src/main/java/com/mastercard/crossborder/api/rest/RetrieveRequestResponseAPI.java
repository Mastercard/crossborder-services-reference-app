package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.RetrieveRequestResponse;
import com.mastercard.crossborder.api.rest.response.RetrieveResponse;
import com.mastercard.crossborder.api.service.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RetrieveRequestResponseAPI {
    private static final Logger logger = LoggerFactory.getLogger(RetrieveRequestResponseAPI.class);

    public static final String RETRIEVE_REQUEST = "/send/partners/{partner_id}/crossborder/rfi/requests/{request_id}";

    @Autowired
    RestClientService restClientService;

    public RetrieveRequestResponse getRequestById(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling RetrieveRequestResponseAPI by Request ID API");
        return (RetrieveRequestResponse) restClientService.service(RETRIEVE_REQUEST, headers, HttpMethod.GET, requestParams,null, RetrieveRequestResponse.class);
    }

    public RetrieveRequestResponse getRequestByIdWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling RetrieveRequest with Encryption by Request ID API");
        return (RetrieveRequestResponse) restClientService.serviceEncryption(RETRIEVE_REQUEST, headers, HttpMethod.GET, requestParams, null, RetrieveRequestResponse.class);
    }


}
