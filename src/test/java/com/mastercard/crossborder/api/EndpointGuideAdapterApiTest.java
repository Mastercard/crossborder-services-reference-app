package com.mastercard.crossborder.api.endpoint.guide.adapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.endpoint.guide.adapter.api.helper.EndpointGuideAdapter;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.EndpointGuideAdapterApi;
import com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.EndpointGuideResponse;
import com.mastercard.crossborder.api.rest.response.Error;
import com.mastercard.crossborder.api.rest.response.Errors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mastercard.crossborder.api.constants.MastercardHttpHeaders.ENCRYPTED_HEADER;
import static com.mastercard.crossborder.api.constants.MastercardHttpHeaders.PARTNER_REF_ID;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MastercardApiConfig.class)
public class EndpointGuideAdapterApiTest {
    private static final Logger logger = LoggerFactory.getLogger(EndpointGuideAdapterApiTest.class);

    @Autowired
    private MastercardApiConfig apiConfig;
    @Autowired
    private EndpointGuideAdapterApi endpointGuideAdapterApi;

    private String partnerId;

    @Before
    public void init() {
        this.partnerId = apiConfig.getPartnerId();
    }

    @Test
    public void testEndpointGuideAdapterApiWithEncryption() {
        logger.info("Running Endpoint Guide Adapter Api");
        try {

            Map<String, Object> requestParams = EndpointGuideAdapter.getCorrectRequestParams();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            httpHeaders.add(PARTNER_REF_ID.toString(), partnerId);
            httpHeaders.add("Specification-Type","TEG");
            List<EndpointGuideResponse> endpointGuideResponse = endpointGuideAdapterApi.getEndpointWithEncryption(httpHeaders, requestParams);
            if (endpointGuideResponse != null) {
                logger.info("Retrieve Endpoint is Successful");
                assertEquals("AED", endpointGuideResponse.get(0).getDestinationCurrency());
                assertEquals("ARE", endpointGuideResponse.get(0).getDestinationCountry());
                assertEquals("BANK", endpointGuideResponse.get(0).getDestinationPaymentInstrument().toString());
                assertEquals("P2P", endpointGuideResponse.get(0).getPaymentType().toString());
            } else {
                logger.info("Retrieve Endpoint failed");
                Assert.fail("Retrieve Endpoint failed");
            }
        } catch (Exception e) {
            logger.error("Retrieve endpoint failed  {}", e);
        }
    }

    @Test
    public void testEndpointGuideAdapterApi() {
        logger.info("Running Endpoint Guide Adapter Api");
        try {
            Map<String, Object> requestParams = EndpointGuideAdapter.getCorrectRequestParams();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            httpHeaders.add(PARTNER_REF_ID.toString(), partnerId);
            httpHeaders.add("Specification-Type","TEG");
            httpHeaders.add(ENCRYPTED_HEADER.toString(), "false");
            List<?> rawResponse = endpointGuideAdapterApi.getEndpoint(httpHeaders, requestParams);
            if (rawResponse != null) {
                ObjectMapper mapper = new ObjectMapper();
                List<EndpointGuideResponse> endpointGuideResponse = rawResponse.stream()
                .map(item -> mapper.convertValue(item, EndpointGuideResponse.class))
                .collect(Collectors.toList());

                logger.info("Retrieve Endpoint is Successful");
                assertEquals("AED", endpointGuideResponse.get(0).getDestinationCurrency());
                assertEquals("ARE", endpointGuideResponse.get(0).getDestinationCountry());
                assertEquals("BANK", endpointGuideResponse.get(0).getDestinationPaymentInstrument().toString());
                assertEquals("P2P", endpointGuideResponse.get(0).getPaymentType().toString());
                assertNotNull(endpointGuideResponse.get(0).getTechnical());
            } else {
                logger.info("Retrieve Endpoint failed");
                fail("Retrieve Endpoint failed");
            }
        } catch (Exception e) {
            logger.error("Retrieve endpoint failed  {}", e);
        }
    }

    @Test
    public void testEndpointGuideAdapterApiFailureWithEncryption() {
        logger.info("Running Endpoint Guide Adapter Api");
        try {

            Map<String, Object> requestParams = EndpointGuideAdapter.getIncorrectRequestParams();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            httpHeaders.add(PARTNER_REF_ID.toString(), partnerId);
            //only for perf env
            httpHeaders.add("Specification-Type","TEG");
            List<EndpointGuideResponse> endpointGuideResponse = endpointGuideAdapterApi.getEndpointWithEncryption(httpHeaders, requestParams);
            logger.error("Endpoint Guide Adapter Api failed due to Resource Unknown");
            fail("Endpoint Guide Adapter Api failed due to Resource Unknown");
        } catch (ServiceException serviceException) {
            logger.error("service error: {}",serviceException.getMessage());
            Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            logger.info("Error Response >>>>>>>>>>>>>>>> " + errorList.get(0));
            if (errorList != null && !errorList.isEmpty()) {
                assertEquals("Endpoint", errorList.get(0).getSource());
                assertEquals("RESOURCE_UNKNOWN", errorList.get(0).getReasonCode());
            } else {
                logger.error("Endpoint Guide Adapter Api request is failed for errors : {}", serviceException.getMessage());
                fail(serviceException.getMessage());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testEndpointGuideAdapterApiFailure() {
        logger.info("Running Endpoint Guide Adapter Api");
        try {

            Map<String, Object> requestParams = EndpointGuideAdapter.getIncorrectRequestParams();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            httpHeaders.add(PARTNER_REF_ID.toString(), partnerId);
            //only for perf env
            httpHeaders.add("Specification-Type","TEG");
            List<EndpointGuideResponse> endpointGuideResponse = endpointGuideAdapterApi.getEndpoint(httpHeaders, requestParams);
            logger.error("Endpoint Guide Adapter Api failed due to Resource Unknown");
            fail("Endpoint Guide Adapter Api failed due to Resource Unknown");
        } catch (ServiceException serviceException) {
            logger.error("service error: {}",serviceException.getMessage());
            Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            logger.info("Error Response >>>>>>>>>>>>>>>> " + errorList.get(0));
            if (errorList != null && !errorList.isEmpty()) {
                assertEquals("Endpoint", errorList.get(0).getSource());
                assertEquals("RESOURCE_UNKNOWN", errorList.get(0).getReasonCode());
            } else {
                logger.error("Endpoint Guide Adapter Api request is failed for errors : {}", serviceException.getMessage());
                fail(serviceException.getMessage());
            }
        } catch (Exception e) {
            fail(e.getMessage());
            logger.error("Retrieve endpoint failed  {}", e.getMessage());

        }
    }

    @Test
    public void testEndpointGuideAdapterApiWithBEG() {
        logger.info("Running Endpoint Guide Adapter Api");
        try {
            Map<String, Object> requestParams = EndpointGuideAdapter.getBegRequestParams();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            httpHeaders.add(PARTNER_REF_ID.toString(), partnerId);
            httpHeaders.add("Specification-Type","BEG");
            httpHeaders.add(ENCRYPTED_HEADER.toString(), "false");
            List<?> rawResponse = endpointGuideAdapterApi.getEndpoint(httpHeaders, requestParams);
            if (rawResponse != null) {
                ObjectMapper mapper = new ObjectMapper();
                List<EndpointGuideResponse> endpointGuideResponse = rawResponse.stream()
                        .map(item -> mapper.convertValue(item, EndpointGuideResponse.class))
                        .collect(Collectors.toList());

                logger.info("Retrieve Endpoint is Successful");
                assertEquals("PKR", endpointGuideResponse.get(0).getDestinationCurrency());
                assertEquals("PAK", endpointGuideResponse.get(0).getDestinationCountry());
                assertEquals("BANK", endpointGuideResponse.get(0).getDestinationPaymentInstrument().toString());
                assertEquals("P2P", endpointGuideResponse.get(0).getPaymentType().toString());
                assertNotNull(endpointGuideResponse.get(0).getBusiness());
            } else {
                logger.info("Retrieve Endpoint failed");
                fail("Retrieve Endpoint failed");
            }
        } catch (Exception e) {
            logger.error("Retrieve endpoint failed  {}", e);
        }
    }


    @Test
    public void testEndpointGuideAdapterApiWithEncryptionBEG() {
        logger.info("Running Endpoint Guide Adapter Api");
        try {
            Map<String, Object> requestParams = EndpointGuideAdapter.getBegRequestParams();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            httpHeaders.add(PARTNER_REF_ID.toString(), partnerId);
            httpHeaders.add("Specification-Type","BEG");
            List<EndpointGuideResponse> endpointGuideResponse = endpointGuideAdapterApi.getEndpointWithEncryption(httpHeaders, requestParams);
            if (endpointGuideResponse != null) {
                logger.info("Retrieve Endpoint is Successful");
                assertEquals("PKR", endpointGuideResponse.get(0).getDestinationCurrency());
                assertEquals("PAK", endpointGuideResponse.get(0).getDestinationCountry());
                assertEquals("BANK", endpointGuideResponse.get(0).getDestinationPaymentInstrument().toString());
                assertEquals("P2P", endpointGuideResponse.get(0).getPaymentType().toString());
                assertNotNull(endpointGuideResponse.get(0).getBusiness());
            } else {
                logger.info("Retrieve Endpoint failed");
                Assert.fail("Retrieve Endpoint failed");
            }
        } catch (Exception e) {
            logger.error("Retrieve endpoint failed  {}", e);
        }
    }
}
