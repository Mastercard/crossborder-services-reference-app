package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.helper.CrossBorderAPITestHelper;
import com.mastercard.crossborder.api.rest.request.CancelRemittance;
import com.mastercard.crossborder.api.rest.request.RemittanceRequest;
import com.mastercard.crossborder.api.rest.response.CancelResponse;
import com.mastercard.crossborder.api.rest.response.RemittanceResponse;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.CancelRemittanceAPI;
import com.mastercard.crossborder.api.rest.GetRemittanceAPI;
import com.mastercard.crossborder.api.rest.RemittanceAPI;
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
Cancel Payment:-
This facility of cancelling a payment is available only for some mobile money providers and cash-out. It is not available for banks account channels.
Payment can be cancelled if the payment is in 'Pending' status.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MastercardApiConfig.class)
public class CancelRemittanceAPITest  {


    private String partnerId;

    @Autowired
    CancelRemittanceAPI cancelRemittanceAPI;

    @Autowired
    RemittanceAPI remittanceAPI;

    @Autowired
    GetRemittanceAPI getRemittanceAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(CancelRemittanceAPITest.class);

    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }
    /*
    #Usecase - 1 - **CANCEL A PAYMENT**
    It will first make a payment and then cancel it using its paymentId
    Note: For Cancellation, payment has to be in Pending status
    */
    @Test
    public void testCancelPaymentAfterPendingPayment() {
        logger.info("Running Usecase - 1, CANCEL A PAYMENT.");
        try {
            //make a payment
            Map<String, Object> paymentParams = new HashMap<>();
            paymentParams.put("partner-id", partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);

            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncluded();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, paymentParams, paymentRequest);
            if (null != paymentDetails) {
                //use payment Id to cancel payment
                String paymentId = paymentDetails.getRemittanceId();
                Map<String, Object> requestParams = new HashMap<>();
                requestParams.put("partner-id", partnerId);
                requestParams.put("payment-id", paymentId);
                CancelRemittance cancelRequest = new CancelRemittance();
                CancelResponse cancelResponse = cancelRemittanceAPI.cancelPayment(headers, requestParams, cancelRequest);
                if (null != cancelResponse) {
                    logger.info("Payment is cancelled Successfully");
                    Assert.assertEquals( "SUCCESS", cancelResponse.getResponseCode());
                } else {
                    logger.info("Cancel payment has failed");
                    Assert.fail("Cancel payment has failed");
                }
            }else {
                logger.info("Cancel payment has failed due to make payment failure");
                Assert.fail("Cancel payment has failed due to make payment failure");
            }
        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Cancel payment has failed due to {}", re.getMessage());
        }
    }
    /*
    #Usecase - 2 - **CANCEL A PAYMENT WITH ENCRYPTION**
    It will first make a payment and read paymentId and pass it for cancellation.
    Both these API request and response are in encrypted form.
    */
    @Test
    public void testCancelPaymentAfterPendingPaymentForEncryption() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 2, CANCEL A PAYMENT WITH ENCRYPTION. ");
            try {
                //make a payment
                Map<String, Object> paymentParams = new HashMap<>();
                paymentParams.put("partner-id", partnerId);
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);

                RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncluded();
                RemittanceResponse paymentDetails = remittanceAPI.makePaymentWithEncryption(headers, paymentParams, paymentRequest);
                if (null != paymentDetails) {
                    //use payment Id to cancel payment
                    String paymentId = paymentDetails.getRemittanceId();
                    Map<String, Object> requestParams = new HashMap<>();
                    requestParams.put("partner-id", partnerId);
                    requestParams.put("payment-id", paymentId);
                    CancelRemittance cancelRequest = new CancelRemittance();
                    CancelResponse cancelResponse = cancelRemittanceAPI.cancelPaymentWithEncryption(headers, requestParams, cancelRequest);
                    if (null != cancelResponse) {
                        logger.info("Payment is cancelled Successfully");
                        Assert.assertEquals("SUCCESS", cancelResponse.getResponseCode());
                    } else {
                        logger.info("Cancel payment has failed");
                        Assert.fail("Cancel payment has failed");
                    }
                } else {
                    logger.info("Cancel payment has failed due to make payment failure");
                    Assert.fail("Cancel payment has failed due to make payment failure");
                }
            } catch (ServiceException re) {
                Assert.fail(re.getMessage());
                logger.error("Cancel payment has failed due to {}", re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }
    /*
    #Usecase - 3 - **CANCEL A PAYMENT IN JSON FORMAT **
    It will first make a payment and then cancel it using its paymentId
    Note: In this use case makePayment and cancellation of payment, both these operations
    are performed in Json format
    */
    @Test
    public void testCancelPaymentAfterPendingPaymentInJsonFormat() {
        logger.info("Running Usecase - 3, CANCEL A PAYMENT IN JSON FORMAT.");
        try {
            //make a payment
            Map<String, Object> paymentParams = new HashMap<>();
            paymentParams.put("partner-id", partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncluded();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, paymentParams, paymentRequest);
            if (null != paymentDetails) {
                //use payment Id to cancel payment
                String paymentId = paymentDetails.getRemittanceId();
                Map<String, Object> requestParams = new HashMap<>();
                requestParams.put("partner-id", partnerId);
                requestParams.put("payment-id", paymentId);
                CancelRemittance cancelRequest = new CancelRemittance();
                CancelResponse cancelResponse = cancelRemittanceAPI.cancelPayment(headers, requestParams, cancelRequest);
                if (null != cancelResponse) {
                    logger.info("Payment is cancelled Successfully");
                    Assert.assertEquals( "SUCCESS", cancelResponse.getResponseCode());
                } else {
                    logger.info("Cancel payment has failed");
                    Assert.fail("Cancel payment has failed");
                }
            }else {
                logger.info("Cancel payment has failed due to make payment failure");
                Assert.fail("Cancel payment has failed due to make payment failure");
            }
        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Cancel payment has failed due to {}", re.getMessage());
        }
    }

}
