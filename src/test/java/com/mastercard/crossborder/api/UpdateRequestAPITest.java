package com.mastercard.crossborder.api;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.helper.CrossBorderAPITestHelper;
import com.mastercard.crossborder.api.rest.UpdateRequestAPI;
import com.mastercard.crossborder.api.rest.request.UpdateRequest;
import com.mastercard.crossborder.api.rest.response.Error;
import com.mastercard.crossborder.api.rest.response.Errors;
import com.mastercard.crossborder.api.rest.response.UpdateResponse;
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
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class UpdateRequestAPITest {

    private String partnerId;

    @Autowired
    UpdateRequestAPI updateRequestAPI;


    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(UpdateRequestAPITest.class);

    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }

    @Test
    public void testUpdateRequest() {
        logger.info("Running Usecase - 1, UPDATE REQUEST API.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner_id", partnerId);
        requestParams.put("request_id","03377dab-c4d2-4af2-a9cc-04322a25639e");
        /* set the input */
        try {
            UpdateRequest request = CrossBorderAPITestHelper.setDataForUpdateRequest();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            headers.add("idempotency-key","7da7a728-f910-11e6-942a-68f728c1ba70");
            UpdateResponse updateResponse = updateRequestAPI.updateRequest(headers,requestParams,request);

            if (null != updateResponse) {
                logger.info("Reference Id for the update request is : {}", (updateResponse.getReferenceId()));
                Assert.assertNotNull((updateResponse.getReferenceId()));
            } else {
                logger.info("Update Request has failed");
                Assert.fail("Update Request has failed");
            }

        }
        catch (ServiceException re){
            logger.error("Update Request  failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }
    @Test
    public void testUpdateRequestWithEncryption() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 2, UPDATE REQUEST WITH ENCRYPTION.");
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("partner_id", partnerId);
            requestParams.put("request_id","03377dab-c4d2-4af2-a9cc-04322a25639e");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            headers.add("idempotency-key","7da7a728-f910-11e6-942a-68f728c1ba70");
            /* set the input */
            try {
                UpdateRequest request = CrossBorderAPITestHelper.setDataForUpdateRequest();
                //This API call makes sure your request is encrypted before being sent over the network
                UpdateResponse updateResponse = updateRequestAPI.updateRequestWithEncryption(headers, requestParams, request);
                if (null != updateResponse){
                    logger.info("Reference Id for the update request is : {}", (updateResponse.getReferenceId()));
                    Assert.assertNotNull((updateResponse.getReferenceId()));
                } else {
                    logger.info("Update Request  has failed");
                    Assert.fail("Update Request  has failed");
                }
            } catch (ServiceException re) {
                logger.error("Update Request failed as : {}", re.getMessage());
                Assert.fail(re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }
    @Test
    public void testErrorHandling() {
        logger.info("Running Usecase - 3, ERROR HANDLING.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner_id", partnerId);
        requestParams.put("request_id","497f6eca-6276-4993-bfeb-53cbbbba6f08");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        headers.add("idempotency-key","7da7a728-f910-11e6-942a-68f728c1ba70");

        try {
            UpdateRequest request = CrossBorderAPITestHelper.setDataForUpdateRequest();
            updateRequestAPI.updateRequest(headers, requestParams, request);
            Assert.fail("Update Request has to fail for wrong request id");
        } catch (ServiceException se){
            Errors errors = se.getErrors();
            List<Error> error = errors.getErrors();
            Assert.assertNotNull(se.getErrors());
            if( error != null && !error.isEmpty()) {
                assertEquals("request_id", error.get(0).getSource());
                assertEquals("DECLINE", error.get(0).getReasonCode());
            }
            logger.error("Update Request  has failed for the error {}", se.getMessage());
        }
    }




}

