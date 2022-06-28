package com.mastercard.crossborder.api;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.helper.CrossBorderAPITestHelper;
import com.mastercard.crossborder.api.rest.UploadDocumentAPI;
import com.mastercard.crossborder.api.rest.request.UploadDocumentRequest;
import com.mastercard.crossborder.api.rest.response.Error;
import com.mastercard.crossborder.api.rest.response.Errors;
import com.mastercard.crossborder.api.rest.response.UploadDocumentResponse;
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

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class UploadDocumentAPITest {

    private String partnerId;

    @Autowired
    UploadDocumentAPI uploadDocumentAPI;


    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(UploadDocumentAPITest.class);

    private static final String partnerIdStr ="partner-id";
    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }

    @Test
    public void testUploadDocument() {
        logger.info("Running Usecase - 1, UPLOAD DOCUMENT API.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner_id", partnerId);
        /* set the input */
        try {
            UploadDocumentRequest request = CrossBorderAPITestHelper.setDataForUploadDocument();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            headers.add("idempotency-key","7da7a728-f910-11e6-942a-68f728c1ba70");
            UploadDocumentResponse uploadDocumentResponse = uploadDocumentAPI.uploadDocument( headers, requestParams, request);

            if (null != uploadDocumentResponse) {
                logger.info("DocumentId for the upload document is : {}", (uploadDocumentResponse.getDocumentId()));
                Assert.assertNotNull(((uploadDocumentResponse.getDocumentId())));
            } else {
                logger.info("Upload Document  request has failed");
                Assert.fail("Upload Document  request has failed");
            }

        }
        catch (ServiceException re){
            logger.error("upload Document request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    @Test
    public void testUploadDocumentWithEncryption() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 2, UPLOAD DOCUMENT REQUEST WITH ENCRYPTION.");
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("partner_id", partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            headers.add("idempotency-key","7da7a728-f910-11e6-942a-68f728c1ba70");
            /* set the input */
            try {
                UploadDocumentRequest request = CrossBorderAPITestHelper.setDataForUploadDocument();
                //This API call makes sure your request is encrypted before being sent over the network
                UploadDocumentResponse uploadDocumentResponse = uploadDocumentAPI.uploadDocumentWithEncryption(headers, requestParams, request);
                if (null != uploadDocumentResponse){
                    logger.info("DocumentId for the upload document is : {}", (uploadDocumentResponse.getDocumentId()));
                    Assert.assertNotNull(((uploadDocumentResponse.getDocumentId())));
                } else {
                    logger.info("Upload Document  request has failed");
                    Assert.fail("Upload Document  request has failed");
                }
            } catch (ServiceException re) {
                logger.error("Upload Document failed as : {}", re.getMessage());
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
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        headers.add("idempotency-key","7da7a728-f910-11e6-942a-68f728c1ba70");

        try {
            UploadDocumentRequest request = CrossBorderAPITestHelper.setDataForUploadDocument();
            UploadDocumentResponse uploadDocumentResponse = uploadDocumentAPI.uploadDocument(headers, requestParams, request);
            Assert.fail("Upload Document Request has to fail for wrong File Name");
        } catch (ServiceException se){
            Errors errors = se.getErrors();
            Error error = errors.getError();
            //Assert.assertFalse(error== null);
            if( error != null) {
                assertEquals("FileName", error.getSource());
                assertEquals("DECLINE", error.getReasonCode());
            }
            logger.error("Upload Document Request  has failed for the error {}", se.getMessage());
        }
    }

}
