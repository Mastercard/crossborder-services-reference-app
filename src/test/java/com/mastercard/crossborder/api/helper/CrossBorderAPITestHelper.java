package com.mastercard.crossborder.api.helper;

import com.mastercard.crossborder.api.rest.request.*;

import java.util.ArrayList;
import java.util.List;
/*
    This is a helper class, can be used to modify data to be passed to APIs.
 */
public class CrossBorderAPITestHelper {
    public CrossBorderAPITestHelper() {
        //default constructor
    }

    public static QuotesRequest setDataForForwardQuote(){
        /* set the input */
        QuotesRequest request = new QuotesRequest();
        request.setTransactionReference(String.valueOf(System.currentTimeMillis()));
        request.setSenderAccountUri("tel:+2130000");
        request.setRecipientAccountUri("tel:+213070001");
        request.setPaymentType("P2P");

        //Set Forward Quote Type
        QuoteType forwardQuote = new QuoteType();
        ForwardFees forwardFees = new ForwardFees();
        /*Note: If quote type is not set default value is forwardQuotes with FeesIncluded = true */
        forwardFees.setFeesIncluded("true");
        forwardFees.setReceiverCurrency("MAD");
        forwardQuote.setForwardFees(forwardFees);
        request.setQuoteType(forwardQuote);

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setAmount("100");
        request.setRemittanceAmount(amount);
        request.setOriginatingCountry("USD");
        return request;
    }
    public static QuotesRequest setDataForReverseQuote(){
        /* set the input */
        QuotesRequest request = new QuotesRequest();
        request.setTransactionReference(String.valueOf(System.currentTimeMillis()));
        request.setSenderAccountUri("tel:+2130000");
        request.setRecipientAccountUri("tel:+213070001");
        request.setPaymentType("P2P");

        //Set reverse Quote Type
        QuoteType reverseQuote = new QuoteType();
        ReverseFees reverseFees = new ReverseFees();
        reverseFees.setSenderCurrency("USD");
        reverseQuote.setReverseFees(reverseFees);
        request.setQuoteType(reverseQuote);

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setAmount("100");
        request.setRemittanceAmount(amount);
        request.setOriginatingCountry("USD");
        return request;
    }
    public static QuotesRequest setDataForForwardQuoteWithFeesNotIncluded(){
        /* set the input */
        QuotesRequest request = new QuotesRequest();
        request.setTransactionReference(String.valueOf(System.currentTimeMillis()));
        request.setSenderAccountUri("tel:+2130000");
        request.setRecipientAccountUri("tel:+213070001");
        request.setPaymentType("P2P");

        //Set Forward Quote Type
        QuoteType forwardQuote = new QuoteType();
        ForwardFees forwardFees = new ForwardFees();
        /*Note: If quote type is not set default value is forwardQuotes with FeesIncluded = true */
        forwardFees.setFeesIncluded("false");
        forwardFees.setReceiverCurrency("USD");
        forwardQuote.setForwardFees(forwardFees);
        request.setQuoteType(forwardQuote);

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setAmount("100");
        request.setRemittanceAmount(amount);
        request.setOriginatingCountry("USD");
        return request;
    }
    public static RemittanceRequest setPaymentDataForwardQuote() {
        RemittanceRequest  paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status
        paymentRequest.setTransactionReference("06" + System.currentTimeMillis());
        paymentRequest.setSenderAccountUri("tel:+254108989");
        paymentRequest.setRecipientAccountUri("ewallet:paypal_user011");
        /* Amount information */
        Amount amount = new Amount();
        amount.setAmount("200");
        amount.setCurrency("INR");
        paymentRequest.setPaymentAmount(amount);
        paymentRequest.setOriginatingCountry("ARE");

        QuoteType quoteType = new QuoteType();
        ReverseFees reverseFees = new ReverseFees();
        reverseFees.setSenderCurrency("USD");
        quoteType.setReverseFees(reverseFees);
        paymentRequest.setFxType(quoteType);
        paymentRequest.setBankCode("NP021");
        paymentRequest.setPaymentType("P2B");

        QuoteType forwardQuote = new QuoteType();
        ForwardFees forwardFees = new ForwardFees();
        /*Note: If quote type is not set default value is forwardQuotes with FeesIncluded = true */
        forwardFees.setFeesIncluded("false");
        forwardFees.setReceiverCurrency("USD");
        forwardQuote.setForwardFees(forwardFees);
        paymentRequest.setFxType(forwardQuote);
        paymentRequest.setBankCode("NP021");
        paymentRequest.setPaymentType("P2B");
        /*Sender Information */
        CustomerData senderData = new CustomerData();
        senderData.setFirstName("Pat");
        senderData.setLastName("Rose");
        senderData.setNationality("IND");
        Address senderAddress = new Address();
        senderAddress.setLine1("53 Main Street");
        senderAddress.setLine2("5A");
        senderAddress.setCity("Pune");
        senderAddress.setCountrySubdivision("MH");
        senderAddress.setCountry("IND");
        senderAddress.setPostalCode("411001");
        senderData.setAddress(senderAddress);
        GovernmentIdData idData = new GovernmentIdData();
        List<String> governmentIds = new ArrayList<>();
        governmentIds.add("ppn:123456789;expiration-date=2019-05-27;issue-date=2011-07-12;country=USA");
        idData.setGovernmentIdURIs(governmentIds);
        senderData.setGovernmentIdData(idData);
        senderData.setDateOfBirth("1985-06-24");
        paymentRequest.setSenderInformation(senderData);

        /*Recipient information */
        CustomerData recipientData = new CustomerData();
        recipientData.setEmail("test@gmail.com");
        recipientData.setNationality("USA");
        recipientData.setOrganizationName("WU");
        Address recipientAddress = new Address();
        recipientAddress.setLine1("123 MainStreet");
        recipientAddress.setLine2("5A");
        recipientAddress.setCity("Arlington");
        recipientAddress.setCountrySubdivision("VA");
        recipientAddress.setCountry("USA");
        recipientAddress.setPostalCode("22207");
        recipientData.setAddress(recipientAddress);
        paymentRequest.setReceiverInformation(recipientData);

        /* Additional Data */
        List <AdditionalDataField> fields = new ArrayList<>();
        AdditionalDataField dataField1 = new AdditionalDataField();
        dataField1.setFieldId("501");dataField1.setValue("1234222222");
        AdditionalDataField dataField2 = new AdditionalDataField();
        dataField2.setFieldId("503");dataField2.setValue("12362");
        AdditionalField additionalField = new AdditionalField();
        additionalField.setData(fields);
        paymentRequest.setAdditionalField(additionalField);

        paymentRequest.setSourceOfIncome("Bank");
        paymentRequest.setReceivingBankName("Royal Exchange");
        paymentRequest.setReceivingBankBranchName("Quad Cities");
        paymentRequest.setPaymentFileIdentifier("1233241223");

        return paymentRequest;
    }
    public static RemittanceRequest setPaymentDataRejectedStatus() {
        RemittanceRequest  paymentRequest = new RemittanceRequest();
        paymentRequest.setTransactionReference(String.valueOf(System.currentTimeMillis()));
        paymentRequest.setProposalId("BadProposalId");
        paymentRequest.setSourceOfIncome("Bank");
        paymentRequest.setReceivingBankName("Royal Exchange");
        paymentRequest.setReceivingBankBranchName("Quad Cities");
        /*Sender Information */
        CustomerData senderData = new CustomerData();
        senderData.setFirstName("Pat");
        senderData.setLastName("Rose");
        senderData.setNationality("IND");
        Address senderAddress = new Address();
        senderAddress.setLine1("123 Main Street");
        senderAddress.setLine2("5A");
        senderAddress.setCity("Pune");
        senderAddress.setCountrySubdivision("MH");
        senderAddress.setCountry("IND");
        senderAddress.setPostalCode("411001");
        senderData.setAddress(senderAddress);
        senderData.setDateOfBirth("1985-06-24");
        paymentRequest.setSenderInformation(senderData);

        /*Recipient information */
        CustomerData recipientData = new CustomerData();
        recipientData.setFirstName("Jerin");
        recipientData.setLastName("John");
        recipientData.setEmail("test@gmail.com");
        recipientData.setPhone("0016367224357");
        recipientData.setNationality("USA");
        Address recipientAddress = new Address();
        recipientAddress.setLine1("123MainStreet");
        recipientAddress.setLine2("5A");
        recipientAddress.setCity("Arlington");
        recipientAddress.setCountrySubdivision("VA");
        recipientAddress.setCountry("USA");
        recipientAddress.setPostalCode("22207");
        recipientData.setAddress(recipientAddress);
        paymentRequest.setReceiverInformation(recipientData);

        return paymentRequest;


    }
    public static RemittanceRequest setPaymentDataForReverseQuote() {
        RemittanceRequest  paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status
        paymentRequest.setTransactionReference("06" + System.currentTimeMillis());
        paymentRequest.setSenderAccountUri("tel:+254108989");
        paymentRequest.setRecipientAccountUri("ewallet:paypal_user011");
        /* Amount information */
        Amount amount = new Amount();
        amount.setAmount("200");
        amount.setCurrency("INR");
        paymentRequest.setPaymentAmount(amount);
        paymentRequest.setOriginatingCountry("ARE");

        QuoteType quoteType = new QuoteType();
        ReverseFees reverseFees = new ReverseFees();
        reverseFees.setSenderCurrency("USD");
        quoteType.setReverseFees(reverseFees);
        paymentRequest.setFxType(quoteType);
        paymentRequest.setBankCode("NP021");
        paymentRequest.setPaymentType("P2B");

        /*Sender Information */
        CustomerData senderData = new CustomerData();
        senderData.setFirstName("Pat");
        senderData.setLastName("Rose");
        senderData.setNationality("IND");
        Address senderAddress = new Address();
        senderAddress.setLine1("53 Main Street");
        senderAddress.setLine2("5A");
        senderAddress.setCity("Pune");
        senderAddress.setCountrySubdivision("MH");
        senderAddress.setCountry("IND");
        senderAddress.setPostalCode("411001");
        senderData.setAddress(senderAddress);
        GovernmentIdData idData = new GovernmentIdData();
        List<String> governmentIds = new ArrayList<>();
        governmentIds.add("ppn:123456789;expiration-date=2019-05-27;issue-date=2011-07-12;country=USA");
        idData.setGovernmentIdURIs(governmentIds);
        senderData.setGovernmentIdData(idData);
        senderData.setDateOfBirth("1985-06-24");
        paymentRequest.setSenderInformation(senderData);

        /*Recipient information */
        CustomerData recipientData = new CustomerData();
        recipientData.setEmail("test@gmail.com");
        recipientData.setNationality("USA");
        recipientData.setOrganizationName("WU");
        Address recipientAddress = new Address();
        recipientAddress.setLine1("123 MainStreet");
        recipientAddress.setLine2("5A");
        recipientAddress.setCity("Arlington");
        recipientAddress.setCountrySubdivision("VA");
        recipientAddress.setCountry("USA");
        recipientAddress.setPostalCode("22207");
        recipientData.setAddress(recipientAddress);
        paymentRequest.setReceiverInformation(recipientData);

        /* Additional Data */
        List <AdditionalDataField> fields = new ArrayList<>();
        AdditionalDataField dataField1 = new AdditionalDataField();
        dataField1.setFieldId("501");dataField1.setValue("1234222222");
        AdditionalDataField dataField2 = new AdditionalDataField();
        dataField2.setFieldId("503");dataField2.setValue("12362");
        AdditionalField additionalField = new AdditionalField();
        additionalField.setData(fields);
        paymentRequest.setAdditionalField(additionalField);

        paymentRequest.setSourceOfIncome("Bank");
        paymentRequest.setReceivingBankName("Royal Exchange");
        paymentRequest.setReceivingBankBranchName("Quad Cities");
        paymentRequest.setPaymentFileIdentifier("1233241223");

        return paymentRequest;
    }
    public static RemittanceRequest setPaymentDataWithQuote(String proposalId) {
        RemittanceRequest  paymentRequest = new RemittanceRequest();
        paymentRequest.setTransactionReference(String.valueOf(System.currentTimeMillis()));
        paymentRequest.setProposalId(proposalId);
        paymentRequest.setSourceOfIncome("Bank");
        paymentRequest.setReceivingBankName("Royal Exchange");
        paymentRequest.setReceivingBankBranchName("Quad Cities");
        /*Sender Information */
        CustomerData senderData = new CustomerData();
        senderData.setFirstName("Pat");
        senderData.setLastName("Rose");
        senderData.setNationality("IND");
        Address senderAddress = new Address();
        senderAddress.setLine1("123 Main Street");
        senderAddress.setLine2("5A");
        senderAddress.setCity("Pune");
        senderAddress.setCountrySubdivision("MH");
        senderAddress.setCountry("IND");
        senderAddress.setPostalCode("411001");
        senderData.setAddress(senderAddress);
        senderData.setDateOfBirth("1985-06-24");
        paymentRequest.setSenderInformation(senderData);

        /*Recipient information */
        CustomerData recipientData = new CustomerData();
        recipientData.setFirstName("Jerin");
        recipientData.setLastName("John");
        recipientData.setEmail("test@gmail.com");
        recipientData.setPhone("0016367224357");
        recipientData.setNationality("USA");
        Address recipientAddress = new Address();
        recipientAddress.setLine1("123MainStreet");
        recipientAddress.setLine2("5A");
        recipientAddress.setCity("Arlington");
        recipientAddress.setCountrySubdivision("VA");
        recipientAddress.setCountry("USA");
        recipientAddress.setPostalCode("22207");
        recipientData.setAddress(recipientAddress);
        paymentRequest.setReceiverInformation(recipientData);

        return paymentRequest;
    }

}
