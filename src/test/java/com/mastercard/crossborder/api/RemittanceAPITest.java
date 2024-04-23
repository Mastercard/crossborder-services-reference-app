package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.helper.CrossBorderAPITestHelper;
import com.mastercard.crossborder.api.rest.GetRemittanceAPI;
import com.mastercard.crossborder.api.rest.QuotesAPI;
import com.mastercard.crossborder.api.rest.RemittanceAPI;
import com.mastercard.crossborder.api.rest.request.QuotesRequest;
import com.mastercard.crossborder.api.rest.request.RemittanceRequest;
import com.mastercard.crossborder.api.rest.response.Error;
import com.mastercard.crossborder.api.rest.response.*;
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
import java.util.*;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/*
   Payment:-
   This class covers all the use cases to make a payment
   Payment can be made in two ways
   a) Make payment using quote – OI needs to pass quote details (proposal ID) while making payment.
   b) One shot payment (payment without quotes) – OI can directly make a payment without requesting a quote.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class RemittanceAPITest  {

    private String partnerId;

    @Autowired
    RemittanceAPI remittanceAPI;

    @Autowired
    QuotesAPI quotesAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    @Autowired
    GetRemittanceAPI getRemittanceAPI;

    private static final Logger logger = LoggerFactory.getLogger(RemittanceAPITest.class);

    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }
    /*
       #Usecase - 1 - **PAYMENT WITH FORWARD QUOTE**
       Make a forward quote and use same proposal ID to make payment
       NOTE: Proposal returned from quotes response is time bound
    */
    @Test
    public void testPaymentWithQuote() {
        logger.info("Running Usecase - 1, PAYMENT WITH FORWARD QUOTE.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            QuotesRequest request = CrossBorderAPITestHelper.setDataForForwardQuoteWithFeesIncluded();
            QuotesResponse quotesResponse = quotesAPI.getQuote(headers, requestParams, request);
            Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
            if ( proposal.isPresent()) {
                String ProposalId = ((Proposal) proposal.get()).getProposalId();
                RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataWithQuote(ProposalId);
                RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, requestParams, paymentRequest);
                if (null != paymentDetails) {
                    String paymentId = paymentDetails.getRemittanceId();
                    Assert.assertNotNull(paymentId);
                    if(paymentDetails.getCreditedAmount()!=null)
                        assertEquals( paymentDetails.getCreditedAmount().getAmount(), ((Proposal) proposal.get()).getCreditedAmount().getCrAmt());
                    logger.info("Payment with quote is successful, paymentId is {}", paymentId);
                } else {
                    Assert.fail("Payment with quote has failed as Payment API has failed");
                    logger.info("Payment with quote has failed as Payment API has failed");
                }
            }
            else {
                Assert.fail("Payment with quote has failed as quotes API has failed");
                logger.info("Payment with quote has failed as quotes API has failed");
            }
        } catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("Payment with quote has failed for the error {}", re.getMessage());
        }
    }

    /*
      #Usecase - 2 - **FORWARD PAYMENT WITHOUT QUOTE WITH NOT FEES INCLUDED FOR PERSON TO BUSINESS PAYMENT TYPE**
      There is no separate call made to get the quote, in single API call we are making payment.
   */
    @Test
    public void testOneShotPaymentWithForwardQuote() {
        logger.info("Running Usecase - 2, FORWARD PAYMENT WITHOUT QUOTE WITH NOT FEES INCLUDED FOR PERSON TO BUSINESS PAYMENT TYPE");

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncluded();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, requestParams, paymentRequest);

            if (null != paymentDetails) {
                String paymentId = paymentDetails.getRemittanceId();
                Assert.assertNotNull(paymentId);
                logger.info("One shot payment is successful, paymentId is {}", paymentId);
                //This is to verify that the payment amount and the principal amount are equal when fees are not included in the quote request
                Assert.assertEquals(Double.valueOf(paymentRequest.getPaymentAmount().getAmount()), Double.valueOf(paymentDetails.getPrincipalAmount().getAmount()));
                //This is to verify that the charged amount is greater than the payment amount when fees are not included in the quote request
                Assert.assertTrue(Double.valueOf(paymentDetails.getChargedAmount().getAmount()) > Double.valueOf(paymentRequest.getPaymentAmount().getAmount()));

            } else {
                Assert.fail("One shot payment has failed.");
                logger.info("One shot payment has failed.");
            }
        }
        catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("One shot payment API has failed for the error {}", re.getMessage());
        }
    }
    /*
      #Usecase - 3 - **FORWARD PAYMENT WITHOUT QUOTE WITH FEES INCLUDED FOR PERSON TO PERSON PAYMENT TYPE**
      There is no separate call made to get the quote, in single API call we are making payment.
   */
    @Test
    public void testOneShotPaymentWithForwardQuoteFeesIncluded() {
        logger.info("Running Usecase - 3, FORWARD PAYMENT WITHOUT QUOTE WITH FEES INCLUDED FOR PERSON TO PERSON PAYMENT TYPE");

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesIncluded();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, requestParams, paymentRequest);

            if (null != paymentDetails) {
                String paymentId = paymentDetails.getRemittanceId();
                Assert.assertNotNull(paymentId);
                logger.info("One shot payment is successful, paymentId is {}", paymentId);
                //This is to verify that the payment amount and the charged amount are equal when fees are included in the quote request
                Assert.assertEquals(Double.valueOf(paymentRequest.getPaymentAmount().getAmount()), Double.valueOf(paymentDetails.getChargedAmount().getAmount()));

            } else {
                Assert.fail("One shot payment has failed.");
                logger.info("One shot payment has failed.");
            }
        }
        catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("One shot payment API has failed for the error {}", re.getMessage());
        }
    }

    /*
         #Usecase - 4 - **REVERSE PAYMENT WITHOUT QUOTE FOR BUSINESS TO PERSON PAYMENT TYPE**
         There is no separate call made to get the quote, in a single API call we are making payment
      */
    @Test
    public void testOneShotPaymentWithReverseQuote() {
        logger.info("Running Usecase - 4,REVERSE PAYMENT WITHOUT QUOTE FOR BUSINESS TO PERSON PAYMENT TYPE");

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForReverseQuote();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, requestParams, paymentRequest);
            if (null != paymentDetails) {
                String paymentId = paymentDetails.getRemittanceId();
                Assert.assertNotNull(paymentId);
                logger.info("One shot payment is successful, paymentId is {}", paymentId);
            } else {
                Assert.fail("One shot payment has failed.");
                logger.info("One shot payment has failed.");
            }
        }
        catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("One shot payment has failed for the error {}", re.getMessage());
        }
    }

    /*
      #Usecase - 5 - **FORWARD PAYMENT WITHOUT QUOTE FOR BUSINESS TO BUSINESS PAYMENT TYPE**
      There is no separate call made to get the quote, in single API call we are making payment.
   */
    @Test
    public void testOneShotPaymentForBusinessToBusiness() {
        logger.info("Running Usecase - 5, FORWARD PAYMENT WITHOUT QUOTE FOR BUSINESS TO BUSINESS PAYMENT TYPE");

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForBusinessToBusiness();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, requestParams, paymentRequest);

            if (null != paymentDetails) {
                String paymentId = paymentDetails.getRemittanceId();
                Assert.assertNotNull(paymentId);
                logger.info("One shot payment is successful, paymentId is {}", paymentId);
                Assert.assertEquals(Double.valueOf(paymentRequest.getPaymentAmount().getAmount()), Double.valueOf(paymentDetails.getChargedAmount().getAmount()));

            } else {
                Assert.fail("One shot payment has failed.");
                logger.info("One shot payment has failed.");
            }
        }
        catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("One shot payment API has failed for the error {}", re.getMessage());
        }
    }

    /*
      #Usecase - 6 - **FORWARD PAYMENT WITHOUT QUOTE FOR GOVERNMENT TO PERSON PAYMENT TYPE**
      There is no separate call made to get the quote, in single API call we are making payment.
   */
    @Test
    public void testOneShotPaymentForGovernmentToPerson() {
        logger.info("Running Usecase - 6, FORWARD PAYMENT WITHOUT QUOTE FOR GOVERNMENT TO PERSON PAYMENT TYPE");

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForGovernmentToPerson();
            RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, requestParams, paymentRequest);

            if (null != paymentDetails) {
                String paymentId = paymentDetails.getRemittanceId();
                Assert.assertNotNull(paymentId);
                logger.info("One shot payment is successful, paymentId is {}", paymentId);
                Assert.assertEquals(Double.valueOf(paymentRequest.getPaymentAmount().getAmount()), Double.valueOf(paymentDetails.getChargedAmount().getAmount()));

            } else {
                Assert.fail("One shot payment has failed.");
                logger.info("One shot payment has failed.");
            }
        }
        catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("One shot payment API has failed for the error {}", re.getMessage());
        }
    }

    /*

    /*
      #Usecase - 7 - **ERROR HANDLING**
      Payment can fail for various reasons, this scenario is added so that user should know what to expect where there is a failure
      Refer https://developer.mastercard.com/documentation/mastercard-send-cross-border/1#error-codes for details
   */
    @Test
    public void testErrorHandling() {
        logger.info("Running Usecase - 7, ERROR HANDLING.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataRejectedStatus();
            remittanceAPI.makePayment(headers, requestParams, paymentRequest);
            Assert.fail("Payment has to fail for wrong proposal ID");
        } catch (ServiceException se){
            Errors errors = se.getErrors();
           // Error error = errors.getError();
            List<Error> error = errors.getErrorList();
            Assert.assertFalse(error== null);
            if( error != null && !error.isEmpty()) {
                assertEquals("proposal_id", error.get(0).getSource());
                assertEquals("DECLINE", error.get(0).getReasonCode());
            }
            logger.error("Payment with quote has failed for the error {}", se.getMessage());
        }
    }

    /* #Usecase - 8 - **PAYMENT WITH ENCRYPTION SUPPORTED**
       By making a call to makePaymentWithEncryption, we are encrypting payment request and response
     */
    @Test
    public void testOneShotPaymentWithEncryption() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 8, PAYMENT WITH ENCRYPTION SUPPORTED.");
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("partner-id", partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
            try {
                RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncluded();
                RemittanceResponse paymentDetails = remittanceAPI.makePaymentWithEncryption(headers, requestParams, paymentRequest);
                if (null != paymentDetails) {
                    String paymentId = paymentDetails.getRemittanceId();
                    Assert.assertNotNull(paymentId);
                    logger.info("One shot payment with encryption is successful, paymentId is {}", paymentId);
                } else {
                    Assert.fail("One shot payment with encryption has failed.");
                    logger.info("One shot payment with encryption has failed.");
                }
            } catch (ServiceException re) {
                Assert.fail(re.getMessage());
                logger.error("One shot payment with encryption has failed for the error {}", re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }

    /*
       #Usecase - 9 - **PAYMENT WITH FORWARD QUOTE IN JSON FORMAT**
       Make a forward quote and use same proposal ID to make payment
       Both these operations are performed with request / response in Json format
       NOTE: Proposal returned from quotes response is time bound
    */
    @Test
    public void testPaymentWithQuoteInJsonFormat() {
        logger.info("Running Usecase - 9, PAYMENT WITH FORWARD QUOTE in Json format.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        try {
            QuotesRequest request = CrossBorderAPITestHelper.setDataForForwardQuoteWithFeesIncluded();
            QuotesResponse quotesResponse = quotesAPI.getQuote(headers, requestParams, request);
            Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
            if ( proposal.isPresent()) {
                String ProposalId = ((Proposal) proposal.get()).getProposalId();
                RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataWithQuote(ProposalId);
                RemittanceResponse paymentDetails = remittanceAPI.makePayment(headers, requestParams, paymentRequest);
                if (null != paymentDetails) {
                    String paymentId = paymentDetails.getRemittanceId();
                    Assert.assertNotNull(paymentId);
                    if(paymentDetails.getCreditedAmount()!=null)
                        assertEquals ( paymentDetails.getCreditedAmount().getAmount(), ((Proposal) proposal.get()).getCreditedAmount().getCrAmt());
                    logger.info("Payment with quote is successful, paymentId is {}", paymentId);
                } else {
                    Assert.fail("Payment with quote has failed as Payment API has failed");
                    logger.info("Payment with quote has failed as Payment API has failed");
                }
            }
            else {
                Assert.fail("Payment with quote has failed as quotes API has failed");
                logger.info("Payment with quote has failed as quotes API has failed");
            }
        } catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("Payment with quote has failed for the error {}", re.getMessage());
        }
    }

    /*
      #Usecase - 10 - **ERROR HANDLING IN JSON FORMAT**
      Payment can fail for various reasons, this scenario is added so that user should know what to expect where there is a failure
      Refer https://developer.mastercard.com/documentation/mastercard-send-cross-border/1#error-codes for details
   */
    @Test
    public void testErrorHandlingInJsonFormat() {
        logger.info("Running Usecase - 10, ERROR HANDLING IN JSON FORMAT.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataRejectedStatus();
            remittanceAPI.makePayment(headers, requestParams, paymentRequest);
            Assert.fail("Payment has to fail for wrong proposal ID");
        } catch (ServiceException se){
            Errors errors = se.getErrors();
            // Error error = errors.getError();
            List<Error> error = errors.getErrorList();
            Assert.assertFalse(error== null);
            if( error != null && !error.isEmpty()) {
                assertEquals("proposal_id", error.get(0).getSource());
                assertEquals("DECLINE", error.get(0).getReasonCode());
            }
            logger.error("Payment with quote has failed for the error {}", se.getMessage());
        }
    }

    /*
     * #Usecase - 11 - **ERROR HANDLING IN JSON FORMAT**
     * Method to test timeout for one shot payment
     * **ONE SHOT PAYMENT TIMEOUT RESPONSE HANDLING**
     * */
    @Test
    public void testTimeoutForOneShotPayment() {
        logger.info("Running Usecase - 11, timeout scenario for one shot payment");

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        String currentTransRef = "";
        try {
            RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataForwardQuoteFeesNotIncludedForTimeout();
            currentTransRef = paymentRequest.getRemittanceReference();
            remittanceAPI.makePayment(headers, requestParams, paymentRequest);
        }
        catch(ServiceException ex){
            if(ex != null && ex.getErrors() != null && ex.getErrors().getErrorList() != null && !ex.getErrors().getErrorList().isEmpty()) {
                String source  = ex.getErrors().getErrorList().get(0).getSource();
                if (source!=null && (source.equalsIgnoreCase("Service") || source.equalsIgnoreCase("Gateway"))) {
                    logger.error("One shot payment has been timed out {}", ex.getMessage());
                    initiateBackoffAlgo(currentTransRef);
                } else {
                    logger.error("One shot payment has failed");
                    Assert.fail("One shot payment has failed");
                }
            }else {
                logger.error("One shot payment has failed");
                Assert.fail("One shot payment has failed");
            }
        }
    }

    /*
     * #Usecase - 12 - **ERROR HANDLING IN JSON FORMAT**
     * Method to test timeout for Payment with quote API
     * **TIMEOUT FOR PAYMENT WITH QUOTE**
     * */
    @Test
    public void testTimeoutForPaymentWithQuote() {
        logger.info("Running Usecase - 12, timeout scenario for Payment with quote API");

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        String transactionRef = "";
        try {
            QuotesRequest request = CrossBorderAPITestHelper.setDataForForwardQuoteWithFeesIncluded();
            request.setProposalReference("08" + System.currentTimeMillis() + "TO");
            QuotesResponse quotesResponse = quotesAPI.getQuote(headers, requestParams, request);
            Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
            if (proposal.isPresent()) {
                String ProposalId = ((Proposal) proposal.get()).getProposalId();
                RemittanceRequest paymentRequest = CrossBorderAPITestHelper.setPaymentDataWithQuote(ProposalId);
                paymentRequest.setRemittanceReference(request.getProposalReference());
                transactionRef =  paymentRequest.getRemittanceReference();
                remittanceAPI.makePayment(headers, requestParams, paymentRequest);
            } else {
                Assert.fail("Payment with quote has failed as quotes API has failed");
                logger.info("Payment with quote has failed as quotes API has failed");
            }
        } catch (ServiceException ex) {
            if (ex != null && ex.getErrors() != null && ex.getErrors().getErrorList() != null && !ex.getErrors().getErrorList().isEmpty()) {
                logger.error("Payment with quote has been timed out {}", ex.getMessage());
                String source = ex.getErrors().getErrorList().get(0).getSource();
                if (source.equalsIgnoreCase("Service") || source.equalsIgnoreCase("Gateway")) {
                    initiateBackoffAlgo(transactionRef);
                }else {
                    logger.error("Payment with quote has failed");
                    Assert.fail("Payment with quote has failed");
                }
            } else{
                logger.error("Payment with quote has failed");
                Assert.fail("Payment with quote has failed");
            }
        }
    }

    public void initiateBackoffAlgo(String currentTransRef){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        requestParams.put("payment-reference", currentTransRef);

        try {
            RemittanceResponse retrievePayment = getRemittanceAPI.getPaymentByRef(headers, requestParams);
            if (null != retrievePayment) {
                logger.info("Retrieve payment by reference is Successful");
                assertEquals(retrievePayment.getTransactionReference(), currentTransRef);
            }else {
                logger.info("Retrieve payment by reference has failed");
                Assert.fail("Retrieve payment by reference has failed");
            }

        }catch(ServiceException se){
            logger.error("Retrieve payment by reference has failed {}",se.getMessage());
            Assert.fail(se.getMessage());
        }
    }

}
