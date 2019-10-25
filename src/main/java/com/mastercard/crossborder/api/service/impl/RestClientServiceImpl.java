package com.mastercard.crossborder.api.service.impl;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.EncryptedPayload;
import com.mastercard.crossborder.api.rest.response.Errors;
import com.mastercard.crossborder.api.service.RestClientService;
import com.mastercard.crossborder.api.util.EncryptionUtils;
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
import org.w3c.dom.Document;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.util.Map;

import static com.mastercard.crossborder.api.constants.MastercardHttpHeaders.ENCRYPTED_HEADER;

@Component
public class RestClientServiceImpl<T> implements RestClientService<T> {

    private static final Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    TransformerFactory tf = TransformerFactory.newInstance();


    @Autowired
    MastercardApiConfig mastercardApiConfig;

    @Override
    public T service(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, java.lang.Object> requestParams, Object request, Class<T> responseClass) throws ServiceException{

        String url = buildURL(baseURL, requestParams);
        String requestStr = convertDocumentToString(request);

        /* Generate oauth*/
        String oAuthString = authenticate(url, httpMethod, requestStr);

        /*Build requestEntity */
        HttpEntity<MultiValueMap<String, String>> requestEntity = generateRequestEntity(Boolean.FALSE , headers, requestStr, oAuthString);
        logger.info("Request payload : {}", requestEntity);

        /*make API call*/
        T response = callCrossBorderAPI(url, httpMethod, requestParams, requestEntity, responseClass);

        if (null != response) {
            String responseLog = convertDocumentToString(response);
            logger.info("Response payload : {}", responseLog);
        }

        return response;
    }


    @Override
    public T serviceEncryption(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, java.lang.Object> requestParams, Object request, Class<T> responseClass) throws ServiceException{

        if(mastercardApiConfig.getRunWithEncryptedPayload().booleanValue()) {

            String url = buildURL(baseURL, requestParams);
            String requestStr = convertDocumentToString(request);
            /*Encrypt the request payload and return */
            String requestBody = getEncryptedRequestBody(headers, requestStr);

            /* Generate oauth*/
            String oAuthString = authenticate(url, httpMethod, requestBody);

            /*Build requestEntity */
            HttpEntity<MultiValueMap<String, String>> requestEntity = generateRequestEntity(Boolean.TRUE, headers, requestBody, oAuthString);
            logger.info("Encrypted Request payload : {}", requestEntity);

            T response = callCrossBorderAPI(url, httpMethod, requestParams, requestEntity, EncryptedPayload.class);

            /*Decrypt the response payload and return*/
            if (null != response) {
                logger.info("Encrypted Response payload : {}", ((EncryptedPayload) response).getData());
                String responseStr = EncryptionUtils.jweDecrypt(((EncryptedPayload) response).getData(), mastercardApiConfig.getDecryptionKeyFile());
                return convertStringToXMLDocument(responseStr, responseClass);
            }
        }
        return null;
    }

    private T callCrossBorderAPI(String url, HttpMethod httpMethod, Map<String, java.lang.Object> requestParams, HttpEntity<MultiValueMap<String, String>> requestEntity, Class responseClass) throws ServiceException {
        RestTemplate restTemplate = new RestTemplate();
        T response=null;
        try {
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
        }
        catch (HttpClientErrorException he){
            T errors = convertStringToXMLDocument(he.getResponseBodyAsString(), Errors.class);
            throw new ServiceException(he.getResponseBodyAsString(), (Errors)errors );
        }
        return response;
    }

    private String getEncryptedRequestBody(HttpHeaders headers, String requestStr)throws ServiceException {

        if ( null != requestStr && processForEncryption(Boolean.TRUE) && headers.containsKey(HttpHeaders.CONTENT_TYPE) &&
            MediaType.APPLICATION_XML.equals(headers.get(HttpHeaders.CONTENT_TYPE).get(0) )) {
            String encryptedStr = EncryptionUtils.jweEncrypt(requestStr, mastercardApiConfig.getCertificateFile(), mastercardApiConfig.getEncryptionFP(), MediaType.APPLICATION_XML);
            return  "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<encrypted_payload><data>" + encryptedStr + "</data></encrypted_payload>";
        }
        else
            return requestStr;
    }

    private String buildURL(String baseURL, Map<String, java.lang.Object> requestParams){
        String builtURL = UriComponentsBuilder.fromHttpUrl( mastercardApiConfig.getEndPointURL() + "/" + baseURL).uriVariables(requestParams).build().toUriString();
        logger.info("requestURL : {}", builtURL);
        return builtURL;
    }
    private T convertStringToXMLDocument( String xmlString, Class responseClass) throws ServiceException
    {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(responseClass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        }catch(JAXBException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    private String authenticate(String url, HttpMethod httpMethod, String requestStr) throws ServiceException {
        try {
            PrivateKey privateKey = AuthenticationUtils.loadSigningKey(mastercardApiConfig.getP12File().getFile().getAbsolutePath(), mastercardApiConfig.getKeyAlias(), mastercardApiConfig.getKeyPassword());
            return OAuth.getAuthorizationHeader(new URI(url), httpMethod.name(), requestStr, StandardCharsets.UTF_8, mastercardApiConfig.getConsumerKey(), privateKey);
        }catch (IOException | KeyStoreException | CertificateException | NoSuchProviderException | NoSuchAlgorithmException | URISyntaxException | UnrecoverableKeyException e){
            throw new ServiceException(e.getMessage());
        }

    }

    private HttpEntity<MultiValueMap<String, String>> generateRequestEntity(Boolean encrypt, HttpHeaders headers, String requestStr, String oAuthString) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, oAuthString);
        //if content type is not already added, use application_xml
        if(headers.containsKey(HttpHeaders.CONTENT_TYPE))
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, headers.get(HttpHeaders.CONTENT_TYPE).get(0));
        else
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);

        //Below header need to be added to process the encryption request
        if (processForEncryption(encrypt))
        {
            httpHeaders.add(ENCRYPTED_HEADER.toString(), "true");
        }
        return (HttpEntity<MultiValueMap<String, String>>) new HttpEntity(requestStr, httpHeaders);
    }

    private boolean processForEncryption( Boolean encrypt){
        return encrypt && mastercardApiConfig.getRunWithEncryptedPayload().booleanValue();
    }

    private Document getDocument(Object request) throws ServiceException{
        try {
            Document doc = dbf.newDocumentBuilder().newDocument();
            JAXBContext context = JAXBContext.newInstance(request.getClass());
            context.createMarshaller().marshal(request, doc);
            return doc;
        }catch (ParserConfigurationException | JAXBException e){
            throw new ServiceException(e.getMessage());
        }
    }

    private String convertDocumentToString(Object obj) throws ServiceException {

        if(null == obj)
            return "";
        Document document = getDocument(obj);
        Transformer transformer;
        if(null != document ) {
            try {
                transformer = tf.newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(document), new StreamResult(writer));
                return writer.getBuffer().toString();
            } catch (TransformerException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        return "";
    }

}
