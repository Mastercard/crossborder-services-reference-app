package com.mastercard.crossborder.api.service;

import com.mastercard.crossborder.api.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Map;

/*
    RestClientService is a service used for cross border API calls
    1. builds the request URL to make a rest call
    2. Generates authentication token with help of consumer key
    3. makes a Get or the Post call depending on the passed parameters
 */
public interface RestClientService<T> {

    T service(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, Object> requestParams, String request, Class<T> responseClass, Boolean encryptionFlag) throws ServiceException;

    String convertToString(HttpHeaders headers, Object data) throws ServiceException;
}
