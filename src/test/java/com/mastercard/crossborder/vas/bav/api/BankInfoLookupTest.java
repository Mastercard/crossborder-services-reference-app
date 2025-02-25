package com.mastercard.crossborder.vas.bav.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.vas.bav.api.BAVApi;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.Bank;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.BankInfoLookupRequest;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.BankInfoLookupResponse;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.BankInfoResponse;
import com.mastercard.crossborder.api.rest.response.Error;
import com.mastercard.crossborder.api.rest.response.Errors;
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
public class BankInfoLookupTest {

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
    public void getBankDetails() {
        logger.info("Test case Search for a Bank");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            BankInfoLookupRequest request = BavHelperApi.createBank();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            BankInfoLookupResponse response = bavApi.getBankDetails(httpHeaders, requestParams, request);
            if (response != null) {
                logger.info("Bank is Present {} ", response.getBankInfo().getBanks().getBankData().get(0).getName());
                Assert.assertTrue(response.getBankInfo().getBanks().getBankData().get(0).getName().contains(request.getBank().getName()));
            } else {
                logger.info("Bank Info request has failed, Bank does not exist");
                Assert.fail("Bank Info request has failed, Bank does not exist");
            }
        } catch (ServiceException re) {
            logger.error("Bank Info request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    @Test
    public void getBankDetailsWithNameAndCountryMultiBank() {
        logger.info("Test case Search for a Bank");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            BankInfoLookupRequest request = BavHelperApi.createBankWithNameAndCountry();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            BankInfoLookupResponse response = bavApi.getBankDetails(httpHeaders, requestParams, request);
            if (response != null) {
                logger.info("Bank is Present {} ", response.getBankInfo().getBanks().getBankData().get(0).getName());
                Assert.assertTrue(response.getBankInfo().getBanks().getBankData().get(0).getName().contains(request.getBank().getName()));
            } else {
                logger.info("Bank Info request has failed, Bank does not exist");
                Assert.fail("Bank Info request has failed, Bank does not exist");
            }
        } catch (ServiceException re) {
            logger.error("Bank Info request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    @Test
    public void getBanDetailsWithBicValue() {
        logger.info("Test case Search for a Bank");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            BankInfoLookupRequest request = BavHelperApi.createBankWithBicDetails();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            BankInfoLookupResponse response = bavApi.getBankDetails(httpHeaders, requestParams, request);
            if (response != null) {
                logger.info("Bank is Present {}", response.getBankInfo().getBanks().getBankData().get(0).getName());
                Assert.assertEquals("Commonwealth Commercial ", response.getBankInfo().getBanks().getBankData().get(0).getName());
            } else {
                logger.info("Bank Info request has failed, Bank does not exist");
                Assert.fail("Bank Info request has failed, Bank does not exist");
            }
        } catch (ServiceException re) {
            logger.error("Bank Info request failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }


    //ERROR HANDLING

    /***
     * Testcase to reciprocate Missing Required Input
     * Bank can be searched if both BankName and Country in the address is present
     * This is an error scenario test case, where the mandatory field i.e the address is null but bank name is present.
     */
    @Test
    public void missingRequiredInputTest() {
        logger.info("Test case Search for a Bank");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            BankInfoLookupRequest request = BavHelperApi.createBankWithBankNameAndNoCountry();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            bavApi.getBankDetails(httpHeaders, requestParams, request);
            logger.error("Bank Validation is failed due to Missing required input");
            Assert.fail("Bank Validation is failed due to Missing required input");
        } catch (ServiceException serviceException) {
            Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            System.out.println("Error Response >>>>>>>>>>>>>>>> "+errorList.get(0));
            if (errorList != null && ! errorList.isEmpty()) {
                assertEquals("bank.country", errorList.get(0).getSource());
                assertEquals("MISSING_REQUIRED_INPUT", errorList.get(0).getReasonCode());
            }else {
                logger.error("Bank Validation request is failed for errors : {}", serviceException.getMessage());
                Assert.fail(serviceException.getMessage());
            }
        }
    }
    /***
     * Testcase to reciprocate Invalid length. Country has more tha 3 characters
     */
    @Test
    public void invalidInputLengthTest() {
        logger.info("Test case Search for a Bank");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            BankInfoLookupRequest request = BavHelperApi.createBankWithInvalidCountryLengthAndPostalCodeLength();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            bavApi.getBankDetails(httpHeaders, requestParams, request);
            logger.error("Bank Validation is failed due to Invalid length");
            Assert.fail("Bank Validation is failed due to Invalid length");
        } catch (ServiceException serviceException) {
           Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            System.out.println("Error Response >>>>>>>>>>>>>>>> "+errorList.get(0));
            System.out.println("Error Response >>>>>>>>>>>>>>>> "+errorList.get(1));
            if (errorList != null && ! errorList.isEmpty()) {
                assertEquals("INVALID_INPUT_LENGTH", errorList.get(0).getReasonCode());
                assertEquals("INVALID_INPUT_LENGTH", errorList.get(1).getReasonCode());
            }else {
                logger.error("Bank Validation request is failed for errors : {}", serviceException.getMessage());
                Assert.fail(serviceException.getMessage());
            }
        }
    }

    /***
     * Testcase to reciprocate Invalid Input format. Country has special symbol
     */


    @Test
    public void noRecordFoundTest() {
        logger.info("Test case Search for a Bank");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            BankInfoLookupRequest request = BavHelperApi.createBankWithNoRecord();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            bavApi.getBankDetails(httpHeaders, requestParams, request);
            logger.error("Bank Validation is failed due to No record found");
            //Assert.fail("Bank Validation is failed due to No record found");
        } catch (ServiceException serviceException) {
            Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            System.out.println("Error Response >>>>>>>>>>>>>>>> "+errorList.get(0));

            if (errorList != null && ! errorList.isEmpty()) {
                assertEquals("RESOURCE_UNKNOWN", errorList.get(0).getReasonCode());
            }
            else {
                logger.error("Bank Validation request is failed for errors : {}", serviceException.getMessage());
                Assert.fail(serviceException.getMessage());
            }
        }
    }

}
