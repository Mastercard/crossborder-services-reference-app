package com.mastercard.crossborder.api.rest;

import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.response.FxRateResponse;
import com.mastercard.crossborder.api.service.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PullCardedAPI {


    @Autowired
    RestClientService restClientService;

    private static final Logger logger = LoggerFactory.getLogger(PullCardedAPI.class);

    public static final String FXRATE="/send/v1/partners/{partner-id}/crossborder/rates";


    public FxRateResponse getFxRates(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {
        logger.info("Calling retrieve FX Rates API");
        return (FxRateResponse) restClientService.service(FXRATE, headers, HttpMethod.GET, requestParams,null, FxRateResponse.class);
    }


    public FxRateResponse getFxRatesEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {

        logger.info("Calling retrieve FX Rates API");
        return (FxRateResponse) restClientService.serviceEncryption(FXRATE, headers, HttpMethod.GET, requestParams, null, FxRateResponse.class);
    }
}
