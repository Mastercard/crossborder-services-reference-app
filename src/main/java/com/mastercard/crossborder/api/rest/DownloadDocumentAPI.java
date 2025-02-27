package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.DownloadDocumentResponse;
import com.mastercard.crossborder.api.service.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DownloadDocumentAPI {

    private final RestClientService<DownloadDocumentResponse> restClientService;

    private static final Logger logger = LoggerFactory.getLogger(DownloadDocumentAPI.class);

    public static final String DOWNLOAD_DOCUMENT = "/send/partners/{partner_id}/crossborder/rfi/documents/{document_id}";

    @Autowired
    public DownloadDocumentAPI(RestClientService<DownloadDocumentResponse> restClientService) {
        this.restClientService = restClientService;
    }

    public DownloadDocumentResponse downloadDocumentById(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling DownloadDocument API by Document ID ");
        return restClientService.service(DOWNLOAD_DOCUMENT, headers, HttpMethod.GET, requestParams, null, DownloadDocumentResponse.class);
    }


    public DownloadDocumentResponse downloadDocumentByIdWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling DownloadDocument API with Encryption by Document ID");
        return restClientService.serviceEncryption(DOWNLOAD_DOCUMENT, headers, HttpMethod.GET, requestParams, null, DownloadDocumentResponse.class);
    }
}

