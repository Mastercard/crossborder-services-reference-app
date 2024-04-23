package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.accountbalances.Account;
import com.mastercard.crossborder.api.service.RestClientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public class BalanceAPI {

    private static final Logger logger = LoggerFactory.getLogger(BalanceAPI.class);

    public static final String GET_ALL_ACCOUNTS_BALANCES = "/send/partners/{partner-id}/crossborder/accounts?include_balance={include_balance}";
    public static final String GET_ACCOUNT_BALANCE_BY_ID = "/send/partners/{partner-id}/crossborder/accounts/{account_id}?include_balance={include_balance}";

    @Autowired
    RestClientService restClientService;

    public List<Account> getAllAccountsBalances(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling get all Accounts Balances  API");

        return (List<Account>) restClientService.service(GET_ALL_ACCOUNTS_BALANCES, headers, HttpMethod.GET, requestParams,null, List.class,true,Account.class);

    }

    public Account getAcctBalanceById(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling retrieve account balance by ID API");
        return (Account) restClientService.service(GET_ACCOUNT_BALANCE_BY_ID, headers, HttpMethod.GET, requestParams,null, Account.class,false,null);
    }
    public List<Account>  getAllAccountsBalancesWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling get all Accounts Balances API WITH ENCRYPTION");
        return (List<Account>) restClientService.serviceEncryption(GET_ALL_ACCOUNTS_BALANCES, headers, HttpMethod.GET, requestParams, null, List.class,true,Account.class);
    }
    public Account getAcctBalanceByIdWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling retrieve account balance by ID API WITH ENCRYPTION");
        return (Account) restClientService.serviceEncryption(GET_ACCOUNT_BALANCE_BY_ID, headers, HttpMethod.GET, requestParams,null, Account.class,false,null);
    }


}

