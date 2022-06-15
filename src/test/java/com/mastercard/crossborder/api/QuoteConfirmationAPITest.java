package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.helper.CrossBorderAPITestHelper;
import com.mastercard.crossborder.api.rest.QuoteConfirmationAPI;
import com.mastercard.crossborder.api.rest.QuotesAPI;
import com.mastercard.crossborder.api.rest.request.QuoteConfirmation;
import com.mastercard.crossborder.api.rest.request.QuotesRequest;
import com.mastercard.crossborder.api.rest.response.Proposal;
import com.mastercard.crossborder.api.rest.response.QuoteConfirmationResponse;
import com.mastercard.crossborder.api.rest.response.QuotesResponse;
import com.mastercard.crossborder.api.rest.response.RetrieveQuoteStatus;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class QuoteConfirmationAPITest {


    private String partnerId;

    @Autowired
    QuoteConfirmationAPI quoteConfirmationAPI;

    @Autowired
    QuotesAPI quotesAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(QuoteConfirmationAPITest.class);

    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }

    @Test
    public void testQuoteConfirmationWithinExpiryTime() {
        logger.info("Running Usecase - 1, Quote Confirmation Within Expiry");
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
                String transactionReference=quotesResponse.getProposalReference();
                QuoteConfirmation quoteConfirmationRequest = CrossBorderAPITestHelper.setDataForQuoteConfirmation(ProposalId,transactionReference);

                QuoteConfirmationResponse quoteConfirmationResponse = quoteConfirmationAPI.getQuoteConfirmation(headers, requestParams, quoteConfirmationRequest);
                if (null != quoteConfirmationResponse) {
                    Assert.assertNotNull(quoteConfirmationResponse.getProposalId());
                } else {
                    Assert.fail("Quote Confirmation within expiry time has failed");
                    logger.info("Quote Confirmation within expiry time has failed");
                }
            }
            else {
                Assert.fail("Quote Confirmation has failed as quotes API has failed");
                logger.info("Quote Confirmation has failed as quotes API has failed");
            }
        } catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("Quote Confirmation within expiry time has failed for the error {}", re.getMessage());
        }
    }

    @Test
    public void testCancelConfirmedQuote() {
        logger.info("Running Usecase - 2, Cancel Confirmed Quote");
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
                String transactionReference=quotesResponse.getProposalReference();
                QuoteConfirmation quoteConfirmationRequest = CrossBorderAPITestHelper.setDataForQuoteConfirmation(ProposalId,transactionReference);
                QuoteConfirmationResponse quoteConfirmationResponse = quoteConfirmationAPI.getQuoteConfirmation(headers, requestParams, quoteConfirmationRequest);

                if(quoteConfirmationResponse.getStatus().equals("CONFIRMED")) {
                    quoteConfirmationResponse = quoteConfirmationAPI.cancelConfirmedQuote(headers, requestParams, CrossBorderAPITestHelper.setDataForQuoteConfirmation(ProposalId, transactionReference));
                }
                if (null != quoteConfirmationResponse) {
                    Assert.assertNotNull(quoteConfirmationResponse.getProposalId());
                } else {
                    Assert.fail("Cancel Confirmed Quote has failed");
                    logger.info("Cancel Confirmed Quote has failed");
                }
            }
            else {
                Assert.fail("Cancel Confirmed Quote has failed as quotes API has failed");
                logger.info("Cancel Confirmed Quote has failed as quotes API has failed");
            }
        } catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("Cancel Confirmed Quote has failed for the error {}", re.getMessage());
        }
    }

    @Test
    public void testCancelQuoteWithoutConfirmation() {
        logger.info("Running Usecase - 3, Cancel Confirmed Quote");
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
                String transactionReference=quotesResponse.getProposalReference();
                quoteConfirmationAPI.cancelConfirmedQuote(headers, requestParams, CrossBorderAPITestHelper.setDataForQuoteConfirmation(ProposalId, transactionReference));
            }
            else {
                Assert.fail("Cancel Confirmed Quote has failed as quotes API has failed");
                logger.info("Cancel Confirmed Quote has failed as quotes API has failed");
            }
        }  catch (ServiceException se){
            boolean error = se.getMessage().contains("Cancellation not permitted on this transaction");
            assertEquals(true, error);
            logger.error("Cancel Confirmed Quote has failed for the error {}", se.getMessage());
        }
    }

    @Test
    public void testRetrieveConfirmedQuote() {
        logger.info("Running Usecase - 4, Retrieve Confirmed Quote");
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
                String transactionReference=quotesResponse.getProposalReference();
                QuoteConfirmation quoteConfirmationRequest = CrossBorderAPITestHelper.setDataForQuoteConfirmation(ProposalId,transactionReference);
                quoteConfirmationAPI.getQuoteConfirmation(headers, requestParams, quoteConfirmationRequest);
                requestParams.put("transaction-reference",transactionReference);
                requestParams.put("proposal-id",ProposalId);
                RetrieveQuoteStatus retrieveQuoteStatus = quoteConfirmationAPI.retrieveConfirmedQuote(headers, requestParams);

                if (null != retrieveQuoteStatus) {
                    logger.info("Retrieve Status is : {}", retrieveQuoteStatus.getQuote().getConfirmStatus().getStatus());
                    Assert.assertEquals("CONFIRMED", retrieveQuoteStatus.getQuote().getConfirmStatus().getStatus());
                } else {
                    Assert.fail("Retrieve Confirmed Quote has failed");
                    logger.info("Retrieve Confirmed Quote has failed");
                }
            }
            else {
                Assert.fail("Retrieve Confirmed Quote has failed as quotes API has failed");
                logger.info("Retrieve Confirmed Quote has failed as quotes API has failed");
            }
        } catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("Retrieve Confirmed Quote has failed for the error {}", re.getMessage());
        }
    }

    @Test
    public void testRetrieveCancelledQuote() {
        logger.info("Running Usecase - 5, Retrieve Cancelled Quote");
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
                String transactionReference=quotesResponse.getProposalReference();
                QuoteConfirmation quoteConfirmationRequest = CrossBorderAPITestHelper.setDataForQuoteConfirmation(ProposalId,transactionReference);
                QuoteConfirmationResponse quoteConfirmationResponse = quoteConfirmationAPI.getQuoteConfirmation(headers, requestParams, quoteConfirmationRequest);

                if(quoteConfirmationResponse.getStatus().equals("CONFIRMED")) {
                    quoteConfirmationAPI.cancelConfirmedQuote(headers, requestParams, CrossBorderAPITestHelper.setDataForQuoteConfirmation(ProposalId, transactionReference));
                }
                requestParams.put("transaction-reference",transactionReference);
                requestParams.put("proposal-id",ProposalId);
                RetrieveQuoteStatus retrieveQuoteStatus = quoteConfirmationAPI.retrieveConfirmedQuote(headers, requestParams);

                if (null != retrieveQuoteStatus) {
                    logger.info("Retrieve Status is : {}", retrieveQuoteStatus.getQuote().getCancelStatus().getStatus());
                    Assert.assertEquals("PENDING", retrieveQuoteStatus.getQuote().getCancelStatus().getStatus());
                } else {
                    Assert.fail("Retrieve Cancelled Quote has failed");
                    logger.info("Retrieve Cancelled Quote has failed");
                }
            }
            else {
                Assert.fail("Retrieve Cancelled Quote has failed as quotes API has failed");
                logger.info("Retrieve Cancelled Quote has failed as quotes API has failed");
            }
        } catch (ServiceException re){
            Assert.fail(re.getMessage());
            logger.error("Retrieve Cancelled Quote has failed for the error {}", re.getMessage());
        }
    }
}
