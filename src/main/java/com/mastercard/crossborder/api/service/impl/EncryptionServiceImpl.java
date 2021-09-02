package com.mastercard.crossborder.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.EncryptedPayload;
import com.mastercard.crossborder.api.rest.response.Errors;
import com.mastercard.crossborder.api.service.EncryptionService;
import com.mastercard.crossborder.api.util.EncryptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import java.io.StringReader;

@Component
public class EncryptionServiceImpl<T> implements EncryptionService<T> {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionServiceImpl.class);
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private TransformerFactory tf = TransformerFactory.newInstance();
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MastercardApiConfig mastercardApiConfig;

    public T getDecryptedResponse(EncryptedPayload response, HttpHeaders headers, Class<T> responseClass) throws ServiceException {
        try {

            /*Decrypt the response payload and return*/
            if (null != response) {
                logger.info("Encrypted Response payload : {}", response.getData());
                String responseStr = com.mastercard.crossborder.api.util.EncryptionUtils.jweDecrypt(response.getData(), mastercardApiConfig.getDecryptionKeyFile());
                return getContentFromString(headers, responseStr, responseClass);
            }
        } catch (HttpClientErrorException he) {
            T errors = getContentFromString(headers, he.getResponseBodyAsString(), (Class<T>) Errors.class);
            throw new ServiceException(he.getResponseBodyAsString(), (Errors) errors);
        }
        return null;
    }

    public String getEncryptedRequestBody(HttpHeaders headers, String requestStr)throws ServiceException {

        String encryptedStr ;
        if (null != requestStr && processForEncryption(Boolean.TRUE) &&  headers.containsKey(HttpHeaders.CONTENT_TYPE) )
        {
            if ( null!= headers.getContentType()&& MediaType.APPLICATION_XML.equals(headers.getContentType().toString())) {
                encryptedStr = com.mastercard.crossborder.api.util.EncryptionUtils.jweEncrypt(requestStr, mastercardApiConfig.getCertificateFile(), mastercardApiConfig.getEncryptionFP(), MediaType.APPLICATION_XML);
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<encrypted_payload><data>" + encryptedStr + "</data></encrypted_payload>";
            }
            if(null != headers.getContentType() && MediaType.APPLICATION_JSON.equals(headers.getContentType().toString())) {
                encryptedStr = EncryptionUtils.jweEncrypt(requestStr, mastercardApiConfig.getCertificateFile(), mastercardApiConfig.getEncryptionFP(), MediaType.APPLICATION_JSON);
                return "{\"encrypted_payload\":{\"data\":"+"\"" + encryptedStr +"\""+"}}";
            }
        }
        return null;
    }

    private boolean processForEncryption(Boolean encrypt) {
        return encrypt && mastercardApiConfig.getRunWithEncryptedPayload();
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
