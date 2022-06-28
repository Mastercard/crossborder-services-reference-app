package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.DownloadDocumentAPI;
import com.mastercard.crossborder.api.rest.response.DownloadDocumentResponse;
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
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class DownloadDocumentAPITest {

    private String partnerId;

    @Autowired
    DownloadDocumentAPI downloadDocumentAPI;


    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(DownloadDocumentAPITest.class);

    private static final String partnerIdStr ="partner-id";
    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }

    @Test
    public void testDownloadDocument() {
        logger.info("Running Usecase - 1, UPLOAD DOCUMENT API.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner_id", "ConnectorApp");
        requestParams.put("document_id","a04b4f7d-34bc-4f58-a3d6-cbb65e00a9c5");
        /* set the input */
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            DownloadDocumentResponse downloadDocumentResponse = downloadDocumentAPI.downloadDocumentById(headers,requestParams);

            if (null != downloadDocumentResponse) {
                logger.info("ReferenceId for the Download Document  is : {}", (downloadDocumentResponse.getReferenceId()));
                Assert.assertNotNull(((downloadDocumentResponse.getReferenceId())));
            } else {
                logger.info("Download Document  request has failed");
                Assert.fail("Download Document  request has failed");
            }

        }
        catch (ServiceException re){
            logger.error("Download Document request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }
    @Test
    public void testDownloadDocumentWithEncryption() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 2, DOWNLOAD DOCUMENT REQUEST WITH ENCRYPTION.");
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("partner_id", "ConnectorApp");
            requestParams.put("document_id","a04b4f7d-34bc-4f58-a3d6-cbb65e00a9c5");

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            /* set the input */
            try {
                //This API call makes sure your request is encrypted before being sent over the network
                DownloadDocumentResponse downloadDocumentResponse = downloadDocumentAPI.downloadDocumentByIdWithEncryption(headers, requestParams);
                if (null != downloadDocumentResponse){
                    logger.info("ReferenceId for the download document is : {}", (downloadDocumentResponse.getReferenceId()));
                    Assert.assertNotNull(((downloadDocumentResponse.getReferenceId())));
                } else {
                    logger.info("Download Document  request has failed");
                    Assert.fail("Download Document  request has failed");
                }
            } catch (ServiceException re) {
                logger.error("Download Document failed as : {}", re.getMessage());
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
        requestParams.put("document_id","d0b7db34-eff8-47fa-b707-2ad12929f0a8");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);

        try {
            DownloadDocumentResponse downloadDocumentResponse = downloadDocumentAPI.downloadDocumentById(headers, requestParams);
            Assert.fail("Download Document Request has to fail for wrong document Id");
        } catch (ServiceException se){
            Errors errors = se.getErrors();
            Error error = errors.getError();
            Assert.assertFalse(errors== null);
            if( error != null) {
                assertEquals("document_id", error.getSource());
                assertEquals("DECLINE", error.getReasonCode());
            }
            logger.error("Download Document Request  has failed for the error {}", se.getMessage());
        }
    }

}




