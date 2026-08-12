package com.mastercard.crossborder.vas.bav.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.Error;
import com.mastercard.crossborder.api.rest.response.Errors;
import com.mastercard.crossborder.api.rest.vas.bav.api.BAVApi;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.IBanValidationRequest;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.ValidateAccountResponse;
import com.mastercard.crossborder.vas.bav.api.helper.BavHelperApi;
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
public class AccountValidationTest {

    private String partnerId;

    private static final Logger logger = LoggerFactory.getLogger(AccountValidationTest.class);

    @Autowired
    private MastercardApiConfig apiConfig;

    @Autowired
    private BAVApi bavApi;

    @Before
    public void init() {
        this.partnerId = apiConfig.getPartnerId();
    }

    @Test
    public void validateAccount() {
        logger.info("Test case to Generate the IBAN");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            IBanValidationRequest request = BavHelperApi.createAccount();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            ValidateAccountResponse response = bavApi.validateAccount(httpHeaders, requestParams, request);
            if(response != null) {
                logger.info("Account is Present {} ", response.getMessage());
                Assert.assertEquals("SUCCESS",response.getStatus());
            }
            else{
                logger.info("Account validation request has failed, Account does not exist");
                Assert.fail("Account validation request has failed, Account does not exist");
            }
        } catch (ServiceException re) {
            logger.error("Account validation request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    @Test
    public void validateCard() {
        logger.info("Test case to validate card for Cross-Border Services");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            IBanValidationRequest request = BavHelperApi.validateCardEligibility();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            ValidateAccountResponse response = bavApi.validateAccount(httpHeaders, requestParams, request);
            if(response != null) {
                logger.info("Card Eligibility response message {} ", response.getMessage());
                Assert.assertEquals("SUCCESS",response.getStatus());
            }
            else{
                logger.info("Card Eligibility request has failed");
                Assert.fail("Card Eligibility request has failed");
            }
        } catch (ServiceException re) {
            logger.error("Card Eligibility request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

  /*  **
     * This is an error scenario test case, where the mandatory field i.e the type is sent as "" empty
     */
    @Test
    public void missingRequiredInputTest() {
        logger.info("Test case to Generate the IBAN");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            IBanValidationRequest request = BavHelperApi.createAccountWithEmptyType();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            bavApi.validateAccount(httpHeaders, requestParams, request);
            logger.error("Account Validation is failed due to Missing required input");
            Assert.fail("Account Validation is failed due to Missing required input");
        } catch (ServiceException serviceException) {
            Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            System.out.println("Error Response >>>>>>>>>>>>>>>> "+errorList.get(0));
            if (errorList != null && ! errorList.isEmpty()) {
                assertEquals("accountUri.value", errorList.get(0).getSource());
                assertEquals("MISSING_REQUIRED_INPUT", errorList.get(0).getReasonCode());
            }
            else {
                logger.error("Bank Validation request is failed for errors : {}", serviceException.getMessage());
                Assert.fail(serviceException.getMessage());
            }
        }
    }

    @Test
    public void invalidInputValueTest(){
        logger.info("Test case to Generate the IBAN");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            IBanValidationRequest request = BavHelperApi.createAccountWithWrongType();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            bavApi.validateAccount(httpHeaders, requestParams, request);
            logger.error("Account Validation is failed due to Invalid input value");
            Assert.fail("Account Validation is failed due to Invalid input value");
        } catch (ServiceException serviceException) {
            Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            System.out.println("Error Response >>>>>>>>>>>>>>>> "+errorList.get(0));
            if (errorList != null && ! errorList.isEmpty()) {
                assertEquals("accountUri.type", errorList.get(0).getSource());
                assertEquals("INVALID_INPUT_VALUE", errorList.get(0).getReasonCode());
            } else {
                logger.error("Bank Validation request is failed for errors : {}", serviceException.getMessage());
                Assert.fail(serviceException.getMessage());
            }
        }
    }

}
