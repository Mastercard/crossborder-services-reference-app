package com.mastercard.crossborder.api;

import com.mastercard.crossborder.api.config.MastercardApiConfig;
import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.BalanceAPI;
import com.mastercard.crossborder.api.rest.response.accountbalances.Account;
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


import static org.junit.Assert.assertNotNull;

/*
    This class is to get Accounts Balances API.
    There are two ways by which get Account Balances can be achieved
    Get All Accounts Balance and Get Accounts Balance by Account Id .
    This can be used to check the status of Account Balance.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MastercardApiConfig.class})
public class BalanceAPITest {

    private String partnerId;

    @Autowired
    BalanceAPI balanceAPI;

    @Autowired
    MastercardApiConfig apiConfig;

    private static final Logger logger = LoggerFactory.getLogger(BalanceAPITest.class);

    private static final String partnerIdStr = "partner-id";
    private static final String includeBalance="include_balance";
    private static final Boolean value=true;
    private static final String status = "ACTIVE";
    private static final String accountId="account_id";

    @Before
    public void init() {
        partnerId = apiConfig.getPartnerId();

    }

    /*
        #Usecase - 1 - **RETRIEVE ACCOUNTS BALANCES BY MASTERCARD PROVIDED ID WITH BALANCE INCLUDED**

    */
   @Test
    public void testGetAllAccntsBalancesWithIncludeBalanceTrue() {
        logger.info("Running Usecase - 1, RETRIEVING ACCOUNTS BALANCES BY MASTERCARD PROVIDED ID WITH BALANCE INCLUDED.");
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(includeBalance,"true");
            requestParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            List<Account> accountResponse= balanceAPI.getAllAccountsBalances(headers, requestParams);
            if (null != accountResponse) {
                logger.info("Retrieve Accounts Balances by Partner ID with balance included is Successful with partnerId {}", partnerId);
                assertNotNull( accountResponse);
            } else {
                logger.info("Retrieve Accounts Balance by Partner-ID with balance included has failed");
                Assert.fail("Retrieve Accounts Balance by Partner-ID with balance included  has failed");
            }

        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Balance by PartnerID with balance included has failed {}", re.getMessage());
        }
    }
    /*
        #Usecase - 2 - **RETRIEVE ACCOUNTS BALANCES BY ACCOUNT ID WITH BALANCE INCLUDED**

    */
    @Test
    public void testGetAcctByAcctIdWithIncludeBalanceTrue() {
        logger.info("Running Usecase - 2, RETRIEVING ACCOUNT BALANCES BY ACCOUNT_ID WITH BALANCE INCLUDED.");
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(includeBalance,"true");
            requestParams.put(partnerIdStr, partnerId);
            requestParams.put(accountId,"acct_1234");

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            Account accountResponse = balanceAPI.getAcctBalanceById(headers, requestParams);
            if (null == accountResponse) {
                logger.info("Retrieve Accounts Balance by Account-ID  with balance included has failed");
                Assert.fail("Retrieve Accounts Balance by Account-ID with balance included has failed");
            } else {
                logger.info("Retrieve Accounts Balances by Account ID with balance included is Successful with account_Id {}", accountResponse.getAccountId());
                Assert.assertEquals(accountResponse.getAccountState(), status);
            }

        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Balace by PartnerID with balance included has failed {}", re.getMessage());
        }
    }
     /*
        #Usecase - 3 - **RETRIEVE ACCOUNTS BALANCES BY PARTNER ID WITH ENCRPTION WITH BALANCE INCLUDED**

    */

    @Test
    public void testGetAllAccntsBalancesWithEncryptionWithIncludeBalanceTrue() {
        logger.info("Running Usecase - 3, RETRIEVING ACCOUNTS BALANCES BY MASTERCARD PROVIDED ID WITH ENCRYPTION WITH BALANCE INCLUDED.");
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(includeBalance,"true");
            requestParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            List<Account> accountResponse = balanceAPI.getAllAccountsBalancesWithEncryption(headers, requestParams);
            if (null != accountResponse) {
                logger.info("Retrieve Accounts Balances by Partner ID is Successful with Encryption balance included for partnerId {}", partnerId);
                assertNotNull(accountResponse);
            } else {
                logger.info("Retrieve Accounts Balance by Partner-ID with Encryption balance included has failed");
                Assert.fail("Retrieve Accounts Balance by Partner-ID with Encryption balance Included has failed");
            }

        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Balance by PartnerID With Encryption balance included has failed {}", re.getMessage());
        }
    }
     /*
        #Usecase - 4 - **RETRIEVE ACCOUNTS BALANCES BY ACCOUNT ID WITH BALANCE INCLUDED**

    */

    @Test
    public void testGetAcctBalancesByIdWithEncryptionWithIncludeBalanceTrue() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 4, RETRIEVE BALANCE IN ENCRYPTED FORM BY ACCOUNT ID WITH BALANCE INCLUDED.");
            try {
                Map<String, Object> requestParams = new HashMap<>();
                requestParams.put(includeBalance,"true");
                requestParams.put(partnerIdStr, partnerId);
                requestParams.put(accountId,"acct_1234");
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                Account accountResponse = balanceAPI.getAcctBalanceByIdWithEncryption(headers, requestParams);
                if (null != accountResponse) {
                    logger.info("Retrieve Balance by ID  with Encryption balance included is Successful with AccountId {}", accountResponse.getAccountId());
                    Assert.assertEquals(accountResponse.getAccountState(), status);
                } else {
                    logger.info("Retrieve Balance by ID with Encryption balance included has failed");
                    Assert.fail("Retrieve Balance by ID with Encryption balance included has failed");
                }
            } catch (ServiceException re) {
                Assert.fail(re.getMessage());
                logger.error("Retrieve Balance by ID with Encryption balance included has failed {}", re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }
    /*
       #Usecase - 5 - **RETRIEVE ACCOUNTS BALANCES BY PARTNER ID WITH BALANCE NOT INCLUDED**

   */
    @Test
    public void testGetAllAccntsBalancesWithIncludeBalanceFalse() {
        logger.info("Running Usecase - 5, RETRIEVING ACCOUNTS BALANCES BY MASTERCARD PROVIDED ID WITH BALANCE NOT INCLUDED.");
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(includeBalance,"false");
            requestParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            List<Account> accountResponse= balanceAPI.getAllAccountsBalances(headers, requestParams);
            if (null != accountResponse) {
                logger.info("Retrieve Accounts Balances by Partner ID With balance not included is Successful with partnerId {}", partnerId);
                assertNotNull( accountResponse);
            } else {
                logger.info("Retrieve Accounts Balance by Partner-ID with balance not included  has failed");
                Assert.fail("Retrieve Accounts Balance by Partner-ID with balance not included has failed");
            }

        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Balance by PartnerID has failed {}", re.getMessage());
        }
    }
    /*
       #Usecase - 6 - **RETRIEVE ACCOUNTS BALANCES BY ACCOUNT ID WITH BALANCE NOT INCLUDED**

   */
    @Test
    public void testGetAcctByAcctIdWithIncludeBalanceFalse() {
        logger.info("Running Usecase - 6, RETRIEVING ACCOUNTS BY ACCOUNT_ID WITH BALANCE NOT INCLUDED.");
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(includeBalance,"False");
            requestParams.put(partnerIdStr, partnerId);
            requestParams.put(accountId,"acct_1234");

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            Account accountResponse = balanceAPI.getAcctBalanceById(headers, requestParams);
            if (null == accountResponse) {
                logger.info("Retrieve Accounts Balance by Account-ID with balance not included has failed");
                Assert.fail("Retrieve Accounts Balance by Account-ID with balance not included has failed");
            } else {
                logger.info("Retrieve Accounts Balances by Account ID with balance not included is Successful with account_Id {}", accountResponse.getAccountId());
                Assert.assertEquals(accountResponse.getAccountState(), status);
            }

        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Balace by PartnerID has failed {}", re.getMessage());
        }
    }
    /*
      #Usecase - 7 - **RETRIEVE ACCOUNTS BALANCES BY PARTNER ID WITH ENCRPTION AND BALANCE NOT INCLUDED**

  */
    @Test
    public void testGetAllAccntsBalancesWithEncryptionWithIncludeBalanceFalse() {
        logger.info("Running Usecase - 7, RETRIEVING ACCOUNTS BALANCES BY MASTERCARD PROVIDED ID WITH ENCRYPTION WITH BALANCE NOT INCLUDED.");
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(includeBalance,"False");
            requestParams.put(partnerIdStr, partnerId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            List<Account> accountResponse = balanceAPI.getAllAccountsBalancesWithEncryption(headers, requestParams);
            if (null != accountResponse) {
                logger.info("Retrieve Accounts Balances by Partner ID With Encryption and balance not included is Successful with Encryption for partnerId {}", partnerId);
                assertNotNull(accountResponse);
            } else {
                logger.info("Retrieve Accounts Balance by Partner-ID With Encryption and balance not included has failed");
                Assert.fail("Retrieve Accounts Balance by Partner-ID With Encryption and balance not included  has failed");
            }

        } catch (ServiceException re) {
            Assert.fail(re.getMessage());
            logger.error("Retrieve Balance by PartnerID With Encryption and balance not included  has failed {}", re.getMessage());
        }
    }
    /*
     #Usecase - 8- **RETRIEVE ACCOUNTS BALANCES BY ACCOUNT ID WITH ENCRPTION AND BALANCE NOT INCLUDED**

 */
    @Test
    public void testGetAcctBalancesByIdWithEncryptionWithIncludeBalanceFalse() {
        if (apiConfig.getRunWithEncryptedPayload()) {
            logger.info("Running Usecase - 8, RETRIEVE ACCOUNT BALANCES IN ENCRYPTED FORM WITH BALANCE NOT INCLUDED");
            try {
                Map<String, Object> requestParams = new HashMap<>();
                requestParams.put(includeBalance,"false");
                requestParams.put(partnerIdStr, partnerId);
                requestParams.put(accountId,"acct_1234");
                HttpHeaders headers = new HttpHeaders();
                // headers.add("x-encrypted","true");
                //  headers.add("NextGenRouter" ,"99c0346643f24c5011ca7b1c7b3c13a0");
                //headers.add("x-mc-routing","nextgen-apigw");
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                Account accountResponse = balanceAPI.getAcctBalanceByIdWithEncryption(headers, requestParams);
                if (null != accountResponse) {
                    logger.info("Retrieve Balance by ID with Encrption and balance not included is Successful with AccountId {}", accountResponse.getAccountId());
                    Assert.assertEquals(accountResponse.getAccountState(), status);
                } else {
                    logger.info("Retrieve Balance by ID with Encrption and balance not included has failed");
                    Assert.fail("Retrieve Balance by ID with Encrption and balance not included has failed");
                }
            } catch (ServiceException re) {
                Assert.fail(re.getMessage());
                logger.error("Retrieve Balance by ID with Encrption and balance not included has failed {}", re.getMessage());
            }
        }
        else
            logger.info("To run this use cases, Set runWithEncryptedPayload=true and other encryption / decryption keys in mastercard-api.properties.");
    }



}
