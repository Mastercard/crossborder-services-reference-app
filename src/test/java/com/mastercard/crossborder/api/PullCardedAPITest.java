package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.PullCardedAPI;
import com.mastercard.crossborder.api.rest.response.FxRateResponse;
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
   Carded Pull:-
   The Carded Rate APIs are part of an opt-in service that provides FX rates for currency pairs supported by an OI.
   It will provide FX rates, rate IDs, effective times, as well as other information.
   If used as the primary mechanism to retrieve FX rates, OI will be required to create a scheduler that will make API calls depending on refresh times.
   Please note carded rate API is JSON Only.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class PullCardedAPITest {

    private String partnerId;

    @Autowired
    PullCardedAPI pullCardedAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(PullCardedAPITest.class);

    private static final String partnerIdStr = "partner-id";

    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();
    }


    /*
        #Usecase - 1 - **Get Fx Rates for partner ID**
        It will pull all the Fx Rates related to Partner ID
    */
    @Test
    public void testGetFxRatesInJsonFormat() {
        logger.info("Running Usecase - 1, RETRIEVE FX RATES IN JSON FORMAT.");
        try {
            Map<String, Object> paymentParams = new HashMap<>();
            paymentParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(partnerIdStr, partnerId);
            FxRateResponse retrieveGetFxRates = pullCardedAPI.getFxRates(headers, requestParams);
            if (null != retrieveGetFxRates) {
                logger.info("Retrieve Fx Rates is Successful with Partner ID {}", partnerId);
                Assert.assertNotNull(retrieveGetFxRates);
                Assert.assertNotEquals(retrieveGetFxRates.getRates().size(),0);
            } else {
                logger.info("Retrieve Fx Rates has failed");
                Assert.fail("Retrieve Fx Rates has failed");
            }
        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Fx Rates has failed {}", re.getMessage());

        }
    }

    /*
         #Usecase - 2 - **RETRIEVE FX RATES IN ENCRYPTED FORM**
         All these calls are encrypted.
       */
    @Test
    public void testGetFxRatesWithEncryption() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 2, RETRIEVE FX RATES IN ENCRYPTED FORM.");
            try {
                Map<String, Object> FXCardRateParams = new HashMap<>();
                FXCardRateParams.put(partnerIdStr, partnerId);
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                Map<String, Object> requestParams = new HashMap<>();
                requestParams.put(partnerIdStr, partnerId);
                FxRateResponse retrieveGetFxRates = pullCardedAPI.getFxRatesEncryption(headers, requestParams);
                if (null != retrieveGetFxRates) {
                    logger.info("Retrieve Fx Rates is Successful with Partner ID {}", partnerId);
                    Assert.assertNotNull(retrieveGetFxRates);
                    Assert.assertNotEquals(retrieveGetFxRates.getRates().size(),0);
                } else {
                    logger.info("Retrieve Fx Rates has failed");
                    Assert.fail("Retrieve Fx Rates has failed");
                }
            }catch (ServiceException re) {
                Assert.fail(re.getMessage());
                logger.error("Retrieve Fx Rates has failed {}", re.getMessage());
            }
        }
    }
}