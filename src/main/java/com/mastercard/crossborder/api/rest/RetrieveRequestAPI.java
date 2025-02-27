package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.exception.ServiceException;
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
public class RetrieveRequestAPI {

    private final RestClientService<RetrieveResponse> restClientService;

    private static final Logger logger = LoggerFactory.getLogger(RetrieveRequestAPI.class);

    public static final String RETRIEVE_REQUEST = "/send/partners/{partner_id}/crossborder/rfi/requests/{request_id}";

    @Autowired
    public RetrieveRequestAPI(RestClientService<RetrieveResponse> restClientService) {
        this.restClientService = restClientService;
    }

    public RetrieveResponse getRequestById(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling RetrieveRequest by Request ID API");
        return restClientService.service(RETRIEVE_REQUEST, headers, HttpMethod.GET, requestParams,null, RetrieveResponse.class);
    }


    public RetrieveResponse getRequestByIdWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling RetrieveRequest with Encryption by Request ID API");
        return restClientService.serviceEncryption(RETRIEVE_REQUEST, headers, HttpMethod.GET, requestParams, null, RetrieveResponse.class);
    }
}
