package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.RetrieveRequestAPI;
import com.mastercard.crossborder.api.rest.RetrieveRequestResponseAPI;
import com.mastercard.crossborder.api.rest.response.RetrieveRequestResponse;
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
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class RetrieveRequestAPITest {

    private String partnerId;

    @Autowired
    RetrieveRequestAPI retrieveRequestAPI;

    @Autowired
    RetrieveRequestResponseAPI retrieveRequestResponseAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(RetrieveRequestAPITest.class);

    private static final String partnerIdStr ="partner_id";
    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }

    @Test
    public void testGetRequestById(){
        logger.info("Running Usecase - 1, RETRIEVE REQUEST BY REQUEST ID.");
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(partnerIdStr, partnerId);
            requestParams.put("request_id", "33TestRequest");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            headers.add("idempotency-key","7da7a728-f910-11e6-942a-68f728c1ba70");
            RetrieveRequestResponse retrieveRequestResponse = retrieveRequestResponseAPI.getRequestById(headers,requestParams);
            if (null != retrieveRequestResponse) {
                logger.info("Retrieve Request by REQUEST ID is Successful with requestId {}", retrieveRequestResponse.getRetrieveResponse().getRequestId());
                Assert.assertNotNull(((retrieveRequestResponse.getRetrieveResponse().getRequestId())));
            } else {
                logger.info("Retrieve Request by ID has failed");
                Assert.fail("Retrieve Request by ID has failed");
            }
        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Request by ID has failed {}", re.getMessage());
        }
    }

    @Test
    public void testGetRequestByIdWithEncryption() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 2, RETRIEVE REQUEST BY REQUEST ID WITH ENCRYPTION.");
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(partnerIdStr, partnerId);
            requestParams.put("request_id", "33TestRequest");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            /* set the input */
            try {
                //This API call makes sure your request is encrypted before being sent over the network
                RetrieveRequestResponse retrieveRequestResponse = retrieveRequestResponseAPI.getRequestByIdWithEncryption(headers,requestParams);
                if (null != retrieveRequestResponse) {
                    logger.info("Retrieve Request by REQUEST ID is Successful with requestId {}", retrieveRequestResponse.getRetrieveResponse().getRequestId());
                    Assert.assertNotNull(((retrieveRequestResponse.getRetrieveResponse().getRequestId())));
                } else {
                    logger.info("Retrieve Request by ID has failed");
                    Assert.fail("Retrieve Request by ID has failed");
                }
            } catch (ServiceException re) {
                Assert.fail(re.getMessage());
                logger.error("Retrieve Request by ID has failed {}", re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }

}
