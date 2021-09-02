package com.mastercard.crossborder.api.service;

import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.EncryptedPayload;
import org.springframework.http.HttpHeaders;

public interface EncryptionService<T> {

    String getEncryptedRequestBody(HttpHeaders headers, String requestStr)throws ServiceException;

    T getDecryptedResponse(EncryptedPayload response, HttpHeaders headers, Class<T> responseClass) throws ServiceException;

}
