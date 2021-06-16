package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.helper.CrossBorderAPITestHelper;
import com.mastercard.crossborder.api.rest.request.RemittanceRequest;
import com.mastercard.crossborder.api.rest.response.RemittanceResponse;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.GetRemittanceAPI;
import com.mastercard.crossborder.api.rest.RemittanceAPI;
import org.apache.logging.log4j.util.Strings;
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
/*
 * Retrieve Payment:-
 * Status of a payment can be seen by retrieve payment API calls. There are two ways of knowing the details of payment,
 * Get payment by ID and Get payment by reference. These getPayment APIs also support encryption.
 *
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class GetRemittanceAPITest  {

    private String partnerId;

    @Autowired
    GetRemittanceAPI getRemittanceAPI;

    @Autowired
    RemittanceAPI remittanceAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(GetRemittanceAPITest.class);

    private static final String partnerIdStr ="partner-id";
    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }

    /*
        #Usecase - 1 - **RETRIEVE PAYMENT BY MASTERCARD PROVIDED ID**
        It will first make a payment and then get its details of this transaction using the paymentId
    */
    @Test
    public void testGetPaymentByIdAfterSuccessfulPayment() {
        logger.info("Running Usecase - 1, RETRIEVE PAYMENT BY MASTERCARD PROVIDED ID.");
        try {
            Map<String, Object> paymentParams = new HashMap<>();
            paymentParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);

            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncluded();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, paymentParams, paymentRequest);
            if (null != paymentDetails) {
                String paymentId = paymentDetails.getRemittanceId();
                Map<String, Object> requestParams = new HashMap<>();
                requestParams.put(partnerIdStr, partnerId);
                requestParams.put("payment-id", paymentId);
                RemittanceResponse retrievePayment = getRemittanceAPI.getPaymentById(headers, requestParams);
                if (null != retrievePayment) {
                    logger.info("Retrieve payment by ID is Successful with paymentId {}", retrievePayment.getRemittanceId());
                    Assert.assertEquals(retrievePayment.getRemittanceId(), paymentId);
                } else {
                    logger.info("Retrieve payment by ID has failed");
                    Assert.fail("Retrieve payment by ID has failed");
                }
            }
            else{
                logger.info("Retrieve payment by ID has failed, due to payment API failure");
                Assert.fail("Retrieve payment by ID has failed, due to payment API failure.");
            }
        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve payment by ID has failed {}", re.getMessage());
        }
    }

    /*
         #Usecase - 2 - **RETRIEVE PAYMENT BY OI PROVIDED TRANSACTION REFERENCE ID**
        It will first make a payment and then get its details of this transaction using its transaction_reference
      */
    @Test
    public void testGetPaymentByReferenceAfterSuccessfulPayment() {
        logger.info("Running Usecase - 2, RETRIEVE PAYMENT BY OI PROVIDED TRANSACTION REFERENCE ID.");
        try {
            Map<String, Object> paymentParams = new HashMap<>();
            paymentParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForReverseQuote();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, paymentParams, paymentRequest);
            if (null != paymentDetails) {
                //transaction_reference can also be used from request, paymentRequest.getTransactionReference()
                String transactionReference = paymentDetails.getTransactionReference();

                Map<String, Object> requestParams = new HashMap<>();
                requestParams.put(partnerIdStr, partnerId);
                requestParams.put("payment-reference", transactionReference);

                RemittanceResponse retrievePayment = getRemittanceAPI.getPaymentByRef(headers, requestParams);
                if (null != retrievePayment) {
                    logger.info("Retrieve payment by reference is Successful");
                    Assert.assertEquals(retrievePayment.getTransactionReference(), transactionReference);
                } else {
                    logger.info("Retrieve payment by reference has failed");
                    Assert.fail("Retrieve payment by reference has failed");
                }
            } else {
                logger.info("Retrieve payment by reference failed, due to payment API failure");
                Assert.fail("Retrieve payment by reference failed, due to payment API failure");
            }
        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve payment by reference failed {}", re.getMessage());
        }
    }
    /*
         #Usecase - 3 - **RETRIEVE PAYMENT IN ENCRYPTED FORM**
         It will first make a payment and then get its details of this transaction using paymentId.
         All these calls are encrypted.
       */
    @Test
    public void testGetPaymentByIdWithEncryptionAfterSuccessfulPayment() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 3, RETRIEVE PAYMENT IN ENCRYPTED FORM.");
            try {
                Map<String, Object> paymentParams = new HashMap<>();
                paymentParams.put(partnerIdStr, partnerId);
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
                RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncluded();
                RemittanceResponse paymentDetails = remittanceAPI.makePaymentWithEncryption(headers, paymentParams, paymentRequest);
                if (null != paymentDetails) {
                    String paymentId = paymentDetails.getRemittanceId();
                    Map<String, Object> requestParams = new HashMap<>();
                    requestParams.put(partnerIdStr, partnerId);
                    requestParams.put("payment-id", paymentId);
                    RemittanceResponse retrievePayment = getRemittanceAPI.getPaymentByIdWithEncryption(headers, requestParams);
                    if (null != retrievePayment) {
                        logger.info("Retrieve payment by ID is Successful with paymentId {}", retrievePayment.getRemittanceId());
                        Assert.assertEquals(retrievePayment.getRemittanceId(), paymentId);
                    } else {
                        logger.info("Retrieve payment by ID has failed");
                        Assert.fail("Retrieve payment by ID has failed");
                    }
                } else {
                    logger.info("Retrieve payment by ID has failed, due to payment API failure");
                    Assert.fail("Retrieve payment by ID has failed, due to payment API failure.");
                }
            } catch (ServiceException re) {
                Assert.fail(re.getMessage());
                logger.error("Retrieve payment by ID has failed {}", re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }

    /*
      #Usecase - 4 - **RETRIEVE PAYMENT BY OI PROVIDED TRANSACTION REFERENCE ID IN JSON FORMAT**
     It will first make a payment and then get its details of this transaction using its transaction_reference
     Both these API calls will be done using JSON format.
   */
    @Test
    public void testGetPaymentByReferenceInJsonFormat() {
        logger.info("Running Usecase - 4, RETRIEVE PAYMENT BY OI PROVIDED TRANSACTION REFERENCE ID IN JSON FORMAT.");
        try {
            Map<String, Object> paymentParams = new HashMap<>();
            paymentParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForReverseQuote();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, paymentParams, paymentRequest);
            if (null != paymentDetails) {
                //transaction_reference can also be used from request, paymentRequest.getTransactionReference()
                String transactionReference = paymentDetails.getTransactionReference();
                if (!Strings.isBlank(transactionReference)) {
                    Map<String, Object> requestParams = new HashMap<>();
                    requestParams.put(partnerIdStr, partnerId);
                    requestParams.put("payment-reference", transactionReference);

                    RemittanceResponse retrievePayment = getRemittanceAPI.getPaymentByRef(headers, requestParams);
                    if (null != retrievePayment) {
                        logger.info("Retrieve payment by reference is Successful");
                        Assert.assertEquals(retrievePayment.getTransactionReference(), transactionReference);
                    } else {
                        logger.info("Retrieve payment by reference has failed");
                        Assert.fail("Retrieve payment by reference has failed");
                    }
                } else {
                    logger.info("Retrieve payment by reference failed, due to payment API failure");
                    Assert.fail("Retrieve payment by reference failed, due to payment API failure");
                }
            }
            else
                logger.info("Payment failed causing failure to retrieve payment by reference.");
        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve payment by reference failed {}", re.getMessage());
        }
    }

}