package com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api;

import com.mastercard.crossborder.api.exception.ServiceException;
import com.mastercard.crossborder.api.rest.endpoint.guide.adapter.api.response.EndpointGuideResponse;
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
public class EndpointGuideAdapterApi {
    @Autowired
    RestClientService restClientService; // NOSONAR
    private static final Logger logger = LoggerFactory.getLogger(EndpointGuideAdapterApi.class);
    public static final String ENDPOINT_GUIDE_ADAPTER_URL="crossborder/endpoint-guide/specifications?payment_type={paymentType}&destination_payment_instrument={destinationPaymentInstrument}&destination_country={destinationCountry}&destination_currency={destinationCurrency}";

    public List<EndpointGuideResponse> getEndpointWithEncryption(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {
        logger.info("Calling Endpoint guide adapter api with encryption");
        return (List<EndpointGuideResponse>) restClientService.serviceEncryption(ENDPOINT_GUIDE_ADAPTER_URL,headers, HttpMethod.GET, requestParams,null, List.class, true, EndpointGuideResponse.class);
    }

    public List<EndpointGuideResponse> getEndpoint(HttpHeaders headers, Map<String, Object> requestParams) throws ServiceException {
        logger.info("Calling Endpoint guide adapter api ");
        return (List<EndpointGuideResponse>) restClientService.service(ENDPOINT_GUIDE_ADAPTER_URL,headers, HttpMethod.GET, requestParams,null, List.class, true,EndpointGuideResponse.class);
    }

}
