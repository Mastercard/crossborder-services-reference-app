package com.mastercard.crossborder.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
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
import org.springframework.web.client.HttpServerErrorException;
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
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mastercard.crossborder.api.constants.MastercardHttpHeaders.ENCRYPTED_HEADER;

@Component
public class RestClientServiceImpl<T> implements RestClientService<T> {

    private static final Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private TransformerFactory tf = TransformerFactory.newInstance();
    private ObjectMapper mapper = new ObjectMapper();

    private MastercardApiConfig mastercardApiConfig;

    @Autowired
    public RestClientServiceImpl(MastercardApiConfig mastercardApiConfig) {
        this.mastercardApiConfig = mastercardApiConfig;
    }

    @Override
    public T service(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, Object> requestParams,Object request, Class<T> responseClass,boolean isListResponse,Class<T> listElementClass) throws ServiceException{

    	String url = buildURL(baseURL, requestParams);

        String requestStr = convertToString(headers, request);

        /* Generate oauth*/
        String oAuthString = authenticate(url, httpMethod, requestStr);

        /*Build requestEntity */
        HttpEntity<MultiValueMap<String, String>> requestEntity = generateRequestEntity(Boolean.FALSE , headers, requestStr, oAuthString);
        logger.info("Request payload : {}", requestEntity);

        /*make API call*/
        try {
            T response = callCrossBorderAPI(url, httpMethod, requestParams, requestEntity, responseClass);
            String responseLog = convertToString(headers, response);
            logger.info("Response payload : {}", responseLog);
            if(isListResponse && listElementClass!=null) {
            	return (T) mapAccountValues(responseLog,listElementClass,headers,responseClass);
            }
            return response;
        }catch (HttpClientErrorException | HttpServerErrorException he){
            T errors = getContentFromString(headers, he.getResponseBodyAsString(), (Class<T>) Errors.class);
            throw new ServiceException(he.getResponseBodyAsString(), (Errors)errors );
        }
    }

    @Override
    public T service(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, Object> requestParams,Object request, Class<T> responseClass) throws ServiceException{
    	return service(baseURL,headers,httpMethod,requestParams,request,responseClass,false,null);
    }

    @Override
    public T serviceEncryption(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, Object> requestParams, Object request, Class<T> responseClass) throws ServiceException{
    	return serviceEncryption(baseURL,headers,httpMethod,requestParams,request,responseClass,false,null);
        }

    @Override
    public T serviceEncryption(String baseURL, HttpHeaders headers, HttpMethod httpMethod, Map<String, Object> requestParams, Object request, Class<T> responseClass,boolean isListResponse,Class<T> listElementClass) throws ServiceException{

        if(mastercardApiConfig.getRunWithEncryptedPayload().booleanValue()) {

            String url = buildURL(baseURL, requestParams);

            String requestStr = convertToString(headers, request);

            /*Encrypt the request payload and return */
            String requestBody = getEncryptedRequestBody(headers, requestStr);

            /* Generate oauth*/
            String oAuthString = authenticate(url, httpMethod, requestBody);

            /*Build requestEntity */
            HttpEntity<MultiValueMap<String, String>> requestEntity = generateRequestEntity(Boolean.TRUE, headers, requestBody, oAuthString);
            logger.info("Encrypted Request payload : {}", requestEntity);

            try{
                T response = callCrossBorderAPI(url, httpMethod, requestParams, requestEntity, (Class<T>) EncryptedPayload.class);
                /*Decrypt the response payload and return*/
                if (null != response) {
                	
                    logger.info("Encrypted Response payload : {}", ((EncryptedPayload) response).getData());
                    String responseStr = EncryptionUtils.jweDecrypt(((EncryptedPayload) response).getData(), mastercardApiConfig.getDecryptionKeyFile(),mastercardApiConfig.getDecryptionKeyAlias(),mastercardApiConfig.getDecryptionKeyPassword());
                    logger.info("Decrypted Response payload: {}:",responseStr);
                    if(isListResponse && listElementClass!=null) {
                    	return (T) mapAccountValues(responseStr,listElementClass,requestEntity.getHeaders(),responseClass);
                    }
                    return getContentFromString(headers, responseStr, responseClass);
                }
            }catch (HttpClientErrorException he){
                T errors = getContentFromString(headers, he.getResponseBodyAsString(), (Class<T>) Errors.class);
                throw new ServiceException(he.getResponseBodyAsString(), (Errors)errors );
            }
        }
        return null;
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

    private HttpEntity<MultiValueMap<String, String>> generateRequestEntity(Boolean encrypt, HttpHeaders headers, String requestStr, String oAuthString) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-mc-routing","nextgen-apigw");
        httpHeaders.add(HttpHeaders.AUTHORIZATION, oAuthString);

        //if content type is not already added, use application_xml
        if(headers.getContentType() !=null && headers.containsKey(HttpHeaders.CONTENT_TYPE))
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, headers.getContentType().toString()); // NOSONAR
        else
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);

        //Below header need to be added to process the encryption request
        if (processForEncryption(encrypt))
        {
            httpHeaders.add(ENCRYPTED_HEADER.toString(), "true");
        }
        return (HttpEntity<MultiValueMap<String, String>>) new HttpEntity(requestStr, httpHeaders); // NOSONAR
    }

    private String getEncryptedRequestBody(HttpHeaders headers, String requestStr)throws ServiceException {
        String encryptedStr ;
        if (null != requestStr && processForEncryption(Boolean.TRUE) &&  headers.containsKey(HttpHeaders.CONTENT_TYPE) )
        {
            if (headers.getContentType() != null && MediaType.APPLICATION_XML.equals(headers.getContentType().toString())) { // NOSONAR
                encryptedStr = EncryptionUtils.jweEncrypt(requestStr, mastercardApiConfig.getCertificateFile(), mastercardApiConfig.getEncryptionFP(), MediaType.APPLICATION_XML);
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<encrypted_payload><data>" + encryptedStr + "</data></encrypted_payload>";
            }
            if(headers.getContentType() != null && MediaType.APPLICATION_JSON.equals(headers.getContentType().toString())) { // NOSONAR
                encryptedStr = EncryptionUtils.jweEncrypt(requestStr, mastercardApiConfig.getCertificateFile(), mastercardApiConfig.getEncryptionFP(), MediaType.APPLICATION_JSON);
                return "{\"encrypted_payload\":{\"data\":"+"\"" + encryptedStr +"\""+"}}";
            }
        }
        return null;
    }

    private T callCrossBorderAPI(String url, HttpMethod httpMethod, Map<String, Object> requestParams, HttpEntity<MultiValueMap<String, String>> requestEntity, Class<T> responseClass)  {
        RestTemplate restTemplate = new RestTemplate();
        T response;
        switch (httpMethod) {
            case GET:
                ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseClass, requestParams);
                response = result.getBody();
                break;
            case POST:
                response = restTemplate.postForObject(url, requestEntity, responseClass, requestParams);
                break;
            default:
                response = null;
        }

        return response;
    }


    private boolean processForEncryption( Boolean encrypt){
        return encrypt && mastercardApiConfig.getRunWithEncryptedPayload();
    }

    private T getContentFromString(HttpHeaders headers, String responseBodyAsString, Class<T> responseClass) throws ServiceException {
        if( headers.getContentType() != null && MediaType.APPLICATION_XML.equals(headers.getContentType().toString())){ // NOSONAR
            return convertStringToXMLDocument(responseBodyAsString, responseClass);
        }
        if( headers.getContentType() != null && MediaType.APPLICATION_JSON.equals(headers.getContentType().toString())){ // NOSONAR
            return convertStringToJSON(responseBodyAsString,responseClass);
        }
        return null;

    }

    private T convertStringToXMLDocument( String xmlString, Class<T> responseClass) throws ServiceException
    {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(responseClass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        }catch(JAXBException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    private T convertStringToJSON( String jsonString, Class<T> responseClass) throws ServiceException
    {
        try {
            return mapper.readValue(jsonString, responseClass);
        }
        catch ( IOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    private String convertToString(HttpHeaders headers, Object data) throws ServiceException {
        if(data != null){
            if (headers.getContentType() != null && MediaType.APPLICATION_JSON.equals(headers.getContentType().toString())) { // NOSONAR
                return convertJsonToString(data);
            }
            if (headers.getContentType() != null && MediaType.APPLICATION_XML.equals(headers.getContentType().toString())) { // NOSONAR
                return convertDocumentToString(data);
            }
        }
        return null;
    }
    private String convertDocumentToString(Object obj) throws ServiceException {

        if(null == obj)
            return "";
        Document document = createXMLDocument(obj);
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

    public String convertJsonToString(Object jsonObject) throws ServiceException{

        try {
            return mapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private Document createXMLDocument(Object request) throws ServiceException{
        try {
            Document doc = dbf.newDocumentBuilder().newDocument();
            JAXBContext context = JAXBContext.newInstance(request.getClass());
            context.createMarshaller().marshal(request, doc);
            return doc;
        }catch (ParserConfigurationException | JAXBException e){
            throw new ServiceException(e.getMessage());
        }
    }
    private List<Object> mapAccountValues(String response,Class<T> listElementClass ,HttpHeaders headers,Class<T> responseClass) throws ServiceException  {
        if (headers.getContentType() != null && MediaType.APPLICATION_XML.equals(headers.getContentType().toString())) { // NOSONAR
            return (List) convertStringToXMLDocument(response,responseClass);
        }
        if (headers.getContentType() != null && MediaType.APPLICATION_JSON.equals(headers.getContentType().toString())) { // NOSONAR
            try {
                CollectionType listType =
                        mapper.getTypeFactory().constructCollectionType(ArrayList.class,listElementClass);
                return mapper.readValue(response, listType);
            }catch(IOException e){
                logger.warn("Error while processing response");
            }
        }
        return new ArrayList<>();
    }


}
