package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.helper.CrossBorderAPITestHelper;
import com.mastercard.crossborder.api.rest.request.QuotesRequest;
import com.mastercard.crossborder.api.rest.response.Proposal;
import com.mastercard.crossborder.api.rest.response.QuotesResponse;
import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.QuotesAPI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.http.HttpHeaders;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
   Quotes:-
  There are three ways by which a quote can be requested. Forward quote with fees included, forward quote with fees not included and reverse quote.
  Quotes request and response can also be encrypted.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class QuotesAPITest {

    private String partnerId;

    @Autowired
    QuotesAPI quotesAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(QuotesAPITest.class);

    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }
    /*
        #Usecase - 1 - **FORWARD QUOTE WITH FEES INCLUDED**
     */
    @Test
    public void testRequestForwardQuoteWithFeesIncluded() {
        logger.info("Running Usecase - 1, FORWARD QUOTE WITH FEES INCLUDED.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);

        /* set the input */
        try {
            QuotesRequest request = CrossBorderAPITestHelper.setDataForForwardQuoteWithFeesIncluded();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
            QuotesResponse quotesResponse = quotesAPI.getQuote( headers, requestParams, request);

            Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
            if ( proposal.isPresent()) {
                logger.info("ProposalId for the quotes are : {}", ((Proposal) proposal.get()).getProposalId());
                Assert.assertNotNull(((Proposal) proposal.get()).getProposalId());
                //This is to verify that the payment amount and the charged amount are equal when fees are included in the quote request
                Assert.assertEquals(Double.valueOf(request.getRemittanceAmount().getAmount()),Double.valueOf(((Proposal) proposal.get()).getChargedAmount().getAmount()));

            } else {
                logger.info("Quotes request has failed, ProposalId does not exist");
                Assert.fail("Quotes request has failed, ProposalId does not exist");
            }

        }
        catch (ServiceException re){
            logger.error("Quotes request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    /*
       #Usecase - 2 - **FORWARD QUOTE WITH FEES NOT INCLUDED**
     */
    @Test
    public void testRequestForwardQuoteWithFeesNotIncluded() {
        logger.info("Running Usecase - 2, FORWARD QUOTE WITH FEES NOT INCLUDED.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        /* set the input */
        try {
            QuotesRequest request = CrossBorderAPITestHelper.setDataForForwardQuoteWithFeesNotIncluded();
            QuotesResponse quotesResponse = quotesAPI.getQuote(headers, requestParams, request);
            Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
            if ( proposal.isPresent()) {
                logger.info("ProposalId for the quotes are : {}", ((Proposal) proposal.get()).getProposalId());
                Assert.assertNotNull(((Proposal) proposal.get()).getProposalId());
                //This is to verify that the charged amount is greater than the payment amount when fees are not included in the quote request
                Assert.assertTrue(Double.valueOf(((Proposal) proposal.get()).getChargedAmount().getAmount()) > Double.valueOf(request.getRemittanceAmount().getAmount()));
                //This is to verify that the payment amount and the principal amount are equal when fees are not included in the quote request
                Assert.assertEquals(Double.valueOf(request.getRemittanceAmount().getAmount()), Double.valueOf((((Proposal) proposal.get()).getPrincipalAmount().getAmount())));
            } else {
                logger.info("Quotes request has failed, ProposalId does not exist");
                Assert.fail("Quotes request has failed, ProposalId does not exist");
            }
        }
        catch (ServiceException re){
            logger.error("Quotes request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    /*
       #Usecase - 3 - **REVERSE QUOTE**
     */
    @Test
    public void testRequestReverseQuote() {
        logger.info("Running Usecase - 3, REVERSE QUOTE.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
        /* set the input */
        try {
            QuotesRequest request = CrossBorderAPITestHelper.setDataForReverseQuote();
            QuotesResponse quotesResponse = quotesAPI.getQuote(headers, requestParams, request);
            Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
            if ( proposal.isPresent()) {
                logger.info("Quotes request is successful, ProposalId : {}", ((Proposal) proposal.get()).getProposalId());
                Assert.assertNotNull(((Proposal) proposal.get()).getProposalId());
            } else {
                logger.info("Quotes request has failed, ProposalId does not exist");
                Assert.fail("Quotes request has failed, ProposalId does not exist");
            }
        }
        catch (ServiceException re){
            logger.error("Quotes request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    /*
        #Usecase - 4 - **QUOTES REQUEST WITH ENCRYPTION**
        Any type of quote can be encrypted, here we have shown a forward quote for reference.
      */
    @Test
    public void testRequestQuoteEncrypted() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 4, QUOTES REQUEST WITH ENCRYPTION.");
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("partner-id", partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
            /* set the input */
            try {
                QuotesRequest request = CrossBorderAPITestHelper.setDataForForwardQuoteWithFeesIncluded();
                //This API call makes sure your request is encrypted before being sent over the network
                QuotesResponse quotesResponse = quotesAPI.getQuoteWithEncryption(headers, requestParams, request);
                Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
                if (proposal.isPresent()) {
                    logger.info("Quotes request is successful, ProposalId : {}", ((Proposal) proposal.get()).getProposalId());
                    Assert.assertNotNull(((Proposal) proposal.get()).getProposalId());
                } else {
                    logger.info("Quotes request has failed, ProposalId does not exist");
                    Assert.fail("Quotes request has failed, ProposalId does not exist");
                }
            } catch (ServiceException re) {
                logger.error("Quotes request failed as : {}", re.getMessage());
                Assert.fail(re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }

    /*
       #Usecase - 5 - **REVERSE QUOTE IN JSON FORMAT**
     */
    @Test
    public void testRequestReverseQuoteInJsonFormat() {
        logger.info("Running Usecase - 5, REVERSE QUOTE WIH JSON.");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        /* set the input */
        try {
            QuotesRequest request = CrossBorderAPITestHelper.setDataForReverseQuote();
            QuotesResponse quotesResponse = quotesAPI.getQuote(headers, requestParams, request);
            Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
            if ( proposal.isPresent()) {
                logger.info("Quotes request is successful, ProposalId : {}", ((Proposal) proposal.get()).getProposalId());
                Assert.assertNotNull(((Proposal) proposal.get()).getProposalId());
            } else {
                logger.info("Quotes request has failed, ProposalId does not exist");
                Assert.fail("Quotes request has failed, ProposalId does not exist");
            }
        }
        catch (ServiceException re){
            logger.error("Quotes request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    /*
        #Usecase - 6 - **QUOTES REQUEST WITH ENCRYPTION IN JSON FORMAT**
        Any type of quote can be encrypted, here we have shown a forward quote for reference.
        Request and response will be in JSON format
      */
    @Test
    public void testRequestQuoteEncryptedInJsonFormat() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 6, QUOTES REQUEST WITH ENCRYPTION IN JSON FORMAT.");
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("partner-id", partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            /* set the input */
            try {
                QuotesRequest request = CrossBorderAPITestHelper.setDataForForwardQuoteWithFeesIncluded();
                //This API call makes sure your request is encrypted before being sent over the network
                QuotesResponse quotesResponse = quotesAPI.getQuoteWithEncryption(headers, requestParams, request);
                Optional proposal = quotesResponse.getProposals().getProposal().stream().findFirst();
                if (proposal.isPresent()) {
                    logger.info("Quotes request is successful, ProposalId : {}", ((Proposal) proposal.get()).getProposalId());
                    Assert.assertNotNull(((Proposal) proposal.get()).getProposalId());
                } else {
                    logger.info("Quotes request has failed, ProposalId does not exist");
                    Assert.fail("Quotes request has failed, ProposalId does not exist");
                }
            } catch (ServiceException re) {
                logger.error("Quotes request failed as : {}", re.getMessage());
                Assert.fail(re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }

}