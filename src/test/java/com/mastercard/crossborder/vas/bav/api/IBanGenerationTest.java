package com.mastercard.crossborder.vas.bav.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.vas.bav.api.BAVApi;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.Account;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.AccountUri;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.IbanCreationDetails;
import com.mastercard.crossborder.api.rest.response.Error;
import com.mastercard.crossborder.api.rest.response.Errors;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.Accounts;
import com.mastercard.crossborder.api.rest.vas.bav.api.response.IBanGenerationResponse;
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
public class IBanGenerationTest {
    private String partnerId;

    private static final Logger logger = LoggerFactory.getLogger(IBanGenerationTest.class);

    @Autowired
    private MastercardApiConfig apiConfig;

    @Autowired
    private BAVApi bavApi;

    @Before
    public void init() {
        this.partnerId = apiConfig.getPartnerId();
    }

    /**
     * This is a test method to generate the IBAN with all input details
     */
    @Test
    public void generateIBanWithAccountAndBankDetails() {
        logger.info("Test case to Generate the IBAN");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

        try {
            IbanCreationDetails request = BavHelperApi.createIban();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            System.out.println("Headers: >>>>>>>>>>>>>>> "+httpHeaders);
            IBanGenerationResponse response = bavApi.generateIBan(httpHeaders, requestParams, request);
            if (response != null) {
                Account actualIBanReceived = response.getIbanDetails().getAccounts().getAccount()
                        .stream()
                        .filter(iban -> iban.getType().equals("IBAN"))
                        .findFirst()
                        .orElse(null);
                assert actualIBanReceived != null;
                logger.info("IBAN successfully generation {}", actualIBanReceived.getValue());
                Assert.assertEquals("FR1420041010050500013M02606", actualIBanReceived.getValue());
                Assert.assertEquals("2004101005", response.getIbanDetails().getBank().getBranchCode());
            } else {
                logger.info("IBAN generation request has failed, IBAN is not created");
                Assert.fail("IBAN generation request has failed, IBAN is not created");
            }
        } catch (ServiceException re) {
            logger.error("IBAN generation request has failed as : {}", re.getStackTrace());
            Assert.fail(re.getMessage());
        }
    }


 /*   *
     * This is a test method to generate the IBAN without Account details
     */
    @Test
    public void generateIBanWithoutAccount() {
        logger.info("Test case to Generate the IBAN");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            IbanCreationDetails request = BavHelperApi.createIbanWithoutAccount();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            IBanGenerationResponse response = bavApi.generateIBan(httpHeaders, requestParams, request);
            if (response != null) {
                Account actualIBanReceived = response.getIbanDetails().getAccounts().getAccount()
                        .stream()
                        .filter(iban -> iban.getType().equals("IBAN"))
                        .findFirst()
                        .orElse(null);
                assert actualIBanReceived != null;
                logger.info("IBAN successfully generation {}", actualIBanReceived.getValue());
                Assert.assertEquals("2004101005", response.getIbanDetails().getBank().getBranchCode());
            } else {
                logger.info("IBAN generation request has failed, IBAN is not created");
                Assert.fail("IBAN generation request has failed, IBAN is not created");
            }
        } catch (ServiceException re) {
            logger.error("IBAN generation request has failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

   /* *
     * This is a test method to generate the IBAN without Branch code and Account Number
     */
    @Test
    public void generateIBanWithoutBranchCodeAndAccountNumber() {
        logger.info("Test case to Generate the IBAN");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        try {
            IbanCreationDetails request = BavHelperApi.createIbanWithoutBranchCodeAndAccountNumber();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            IBanGenerationResponse response = bavApi.generateIBan(httpHeaders, requestParams, request);
            if (response != null) {
                logger.info("IBAN successfully generation {}", response.getIbanDetails().getAccounts().getAccount().get(0).getValue());
                Assert.assertEquals("FR1420041010050500013M02606", response.getIbanDetails().getAccounts().getAccount().get(0).getValue());
            } else {
                logger.info("IBAN generation request has failed, IBAN is not created");
                Assert.fail("IBAN generation request has failed, IBAN is not created");
            }
        } catch (ServiceException re) {
            logger.error("IBAN generation request has failed as : {}", re.getMessage());
            Assert.fail(re.getMessage());
        }
    }

    //Test cases to handle failures

/*    **
     *IBan will not be generated if the request payload is invalid. This test case is for generating an IBAN where the country has a special symbol
     */

    @Test
    public void invalidInputFormatTest() {
        logger.info("Test case to Generate the IBAN with Account in input is null");
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("partner-id", partnerId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        logger.info("IBan Generation has failed due to invalid account");
        try {
            IbanCreationDetails request = BavHelperApi.createIbanWithInvalidCountryFormat();
            System.out.println("Request Payload >>>>>>>>>>>>>  "+request);
            bavApi.generateIBan(httpHeaders, requestParams, request);
            logger.error("IBan Generation is failed due to the country contains invalid character");
            Assert.fail("IBan Generation is failed due to the country contains invalid character");
        } catch (ServiceException serviceException) {
            Errors errors = serviceException.getErrors();
            List<Error> errorList = errors.getErrorList();
            System.out.println("Error Response >>>>>>>>>>>>>>>> "+errorList.get(0));
            if (errorList != null && ! errorList.isEmpty()) {
                assertEquals("country", errorList.get(0).getSource());
                assertEquals("INVALID_INPUT_FORMAT", errorList.get(0).getReasonCode());
            }else {
                logger.error("Generate IBan request is failed for errors : {}", serviceException.getMessage());
                Assert.fail(serviceException.getMessage());
            }
        }
    }
}
