package com.mastercard.crossborder.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.Errors;
import com.mastercard.crossborder.api.service.RestClientService;
import com.mastercard.developer.oauth.OAuth;
import com.mastercard.developer.utils.AuthenticationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import static com.mastercard.crossborder.api.constants.MastercardHttpHeaders.ENCRYPTED_HEADER;

@Component
public class RestClientServiceImpl<T> implements RestClientService<T> {

    private static final Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MastercardApiConfig mastercardApiConfig;

    @Override
    public T service(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, Object> requestParams, String request, Class<T> responseClass) throws ServiceException {

        String url = buildURL(baseURL, requestParams);

        /* Generate oauth*/
        String oAuthString = authenticate(url, httpMethod, request);

        /*Build requestEntity */
        HttpEntity<MultiValueMap<String, String>> requestEntity = generateRequestEntity(headers, request, oAuthString);
        logger.info("Request payload : {}", requestEntity);

        /*make API call*/
        try {
            T response = callCrossBorderAPI(url, httpMethod, requestParams, requestEntity, responseClass);
            return response;
        }catch (HttpClientErrorException he){
            T errors = getContentFromString(headers, he.getResponseBodyAsString(), (Class<T>) Errors.class);
            throw new ServiceException(he.getResponseBodyAsString(), (Errors)errors );
        }

    }

    private String buildURL(String baseURL, Map<String, Object> requestParams){
        String builtURL = UriComponentsBuilder.fromHttpUrl( mastercardApiConfig.getEndPointURL() + "/" + baseURL).uriVariables(requestParams).build().toUriString();
        logger.info("requestURL : {}", builtURL);
        return builtURL;
    }

    private String authenticate(String url, HttpMethod httpMethod, String requestStr) throws ServiceException {
        try {
            PrivateKey privateKey = AuthenticationUtils.loadSigningKey(mastercardApiConfig.getP12File().getFile().getAbsolutePath(), mastercardApiConfig.getKeyAlias(), mastercardApiConfig.getKeyPassword());
            return OAuth.getAuthorizationHeader(new URI(url), httpMethod.name(), requestStr, StandardCharsets.UTF_8, mastercardApiConfig.getConsumerKey(), privateKey);
        }catch (IOException | KeyStoreException | CertificateException | NoSuchProviderException | NoSuchAlgorithmException | URISyntaxException | UnrecoverableKeyException e){
            throw new ServiceException(e.getMessage());
        }

    }

    private HttpEntity<MultiValueMap<String, String>> generateRequestEntity(HttpHeaders headers, String requestStr, String oAuthString) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, oAuthString);
        //if content type is not already added, use application_xml
        if(headers.containsKey(HttpHeaders.CONTENT_TYPE) && null != headers.getContentType()) {
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, headers.getContentType().toString());
        } else {
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        }
        if(headers.containsKey(ENCRYPTED_HEADER.toString()))
            httpHeaders.add(ENCRYPTED_HEADER.toString(), "true");
        return (HttpEntity<MultiValueMap<String, String>>) new HttpEntity(requestStr, httpHeaders);
    }

    private T callCrossBorderAPI(String url, HttpMethod httpMethod, Map<String, Object> requestParams, HttpEntity<MultiValueMap<String, String>> requestEntity, Class responseClass)  {
        RestTemplate restTemplate = new RestTemplate();
        T response;
        switch (httpMethod) {
            case GET:
                ResponseEntity result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseClass, requestParams);
                response = (T) result.getBody();
                break;
            case POST:
                response = (T) restTemplate.postForObject(url, requestEntity, responseClass, requestParams);
                break;
            default:
                response = null;
        }

        return response;
    }

    public T getContentFromString(HttpHeaders headers, String responseBodyAsString, Class<T> responseClass) throws ServiceException {
        if (null != headers.getContentType() && MediaType.APPLICATION_XML.equals(headers.getContentType().toString())) {
            return convertStringToXMLDocument(responseBodyAsString, responseClass);
        }
        if (null != headers.getContentType() && MediaType.APPLICATION_JSON.equals(headers.getContentType().toString())) {
            return convertStringToJSON(responseBodyAsString, responseClass);
        }
        return null;
    }

    private T convertStringToXMLDocument(String xmlString, Class<T> responseClass) throws ServiceException {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(responseClass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        } catch (JAXBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private T convertStringToJSON(String jsonString, Class responseClass) throws ServiceException {
        try {
            return (T) mapper.readValue(jsonString, responseClass);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
