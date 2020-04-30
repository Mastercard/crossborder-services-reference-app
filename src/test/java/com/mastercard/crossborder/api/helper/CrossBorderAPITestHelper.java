package com.mastercard.crossborder.api.helper;

import com.mastercard.crossborder.api.rest.request.AdditionalDataField;
import com.mastercard.crossborder.api.rest.request.AdditionalField;
import com.mastercard.crossborder.api.rest.request.Address;
import com.mastercard.crossborder.api.rest.request.Amount;
import com.mastercard.crossborder.api.rest.request.CustomerData;
import com.mastercard.crossborder.api.rest.request.ForwardFees;
import com.mastercard.crossborder.api.rest.request.GovernmentIdData;
import com.mastercard.crossborder.api.rest.request.QuoteType;
import com.mastercard.crossborder.api.rest.request.QuotesRequest;
import com.mastercard.crossborder.api.rest.request.RemittanceRequest;
import com.mastercard.crossborder.api.rest.request.ReverseFees;

import java.util.ArrayList;
import java.util.List;

/*
    This is a helper class, can be used to modify data to be passed to APIs.
 */
public class CrossBorderAPITestHelper {

    public static QuotesRequest setDataForForwardQuoteWithFeesIncluded(){
        /* set the input */
        QuotesRequest request = new QuotesRequest();
        request.setProposalReference("08" + System.currentTimeMillis() + "ACFQ");
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
        request.setProposalReference("08" + System.currentTimeMillis() + "ACRQ");
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
        request.setProposalReference("08" + System.currentTimeMillis() + "ACFQ");
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

    /**
     * This method will set data for forward quote with fees not included and payment type is P2B.
     * For Payment type P2B following information should be set
     * senders information -> firstname, lastname should be set, middlename being optional.
     * recipients information -> organisational name
     * @return RemittanceRequest
     */
    public static RemittanceRequest setPaymentDataForwardQuoteFeesNotIncluded() {
        RemittanceRequest paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status
        paymentRequest.setRemittanceReference("08" + System.currentTimeMillis() + "ACFQ");
        paymentRequest.setSenderAccountUri("tel:+254108989");
        paymentRequest.setRecipientAccountUri("ewallet:paypal_user011");
        /* Amount information */
        Amount amount = new Amount();
        amount.setAmount("200");
        amount.setCurrency("INR");
        paymentRequest.setPaymentAmount(amount);
        paymentRequest.setOriginatingCountry("ARE");

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
        //Needs to be set when paymenttype is P2B
        senderData.setFirstName("Pat");
        //Needs to be set when paymenttype is P2B
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
        //Needs to be set when paymenttype is P2B
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

    /**
     * This method will set data for forward quote with fees included and payment type is P2P.
     * For Payemnt type P2P following information should be set
     * senders information -> firstname, lastname should be set, middlename being optional.
     * recipients information -> firstname, lastname should be set, middlename being optional
     * @return RemittanceRequest
     */
    public static RemittanceRequest setPaymentDataForwardQuoteFeesIncluded() {
        RemittanceRequest paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status

        paymentRequest.setRemittanceReference("08" + System.currentTimeMillis() + "RMFQ");
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
        paymentRequest.setPaymentType("P2P");

        QuoteType forwardQuote = new QuoteType();
        ForwardFees forwardFees = new ForwardFees();
        /*Note: If quote type is not set default value is forwardQuotes with FeesIncluded = true */
        forwardFees.setFeesIncluded("true");
        forwardFees.setReceiverCurrency("USD");
        forwardQuote.setForwardFees(forwardFees);
        paymentRequest.setFxType(forwardQuote);

        /*Sender Information */
        CustomerData senderData = new CustomerData();
        //Need to be set when payment type is P2P
        senderData.setFirstName("Pat");
        //Need to be set when payment type is P2P
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
        //Need to be set when payment type is P2P
        recipientData.setFirstName("JOHN");
        //Need to be set when payment type is P2P
        recipientData.setLastName("SMITH");
        recipientData.setEmail("test@gmail.com");
        recipientData.setNationality("USA");

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

    /**
     * This method will set data for forward quote with fees included and payment type is B2B.
     * For Payemnt type B2B following information should be set
     * senders information -> Organization name
     * recipients information -> Organization name
     * @return RemittanceRequest
     */
    public static RemittanceRequest setPaymentDataForBusinessToBusiness() {
        RemittanceRequest paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status

        paymentRequest.setRemittanceReference("08" + System.currentTimeMillis() + "RMFQ");
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
        paymentRequest.setPaymentType("B2B");

        QuoteType forwardQuote = new QuoteType();
        ForwardFees forwardFees = new ForwardFees();
        /*Note: If quote type is not set default value is forwardQuotes with FeesIncluded = true */
        forwardFees.setFeesIncluded("true");
        forwardFees.setReceiverCurrency("USD");
        forwardQuote.setForwardFees(forwardFees);
        paymentRequest.setFxType(forwardQuote);

        /*Sender Information */
        CustomerData senderData = new CustomerData();
        //Need to be set when payment type is B2B
        senderData.setOrganizationName("WU");
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
        //Need to be set when payment type is B2B
        recipientData.setOrganizationName("NU");
        recipientData.setEmail("test@gmail.com");
        recipientData.setNationality("USA");

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

    /**
     * This method will set data for forward quote with fees included and payment type is G2P.
     * For Payemnt type G2P following information should be set
     * senders information -> Organization name
     * recipients information -> firstname, lastname should be set, middlename being optional
     * @return RemittanceRequest
     */
    public static RemittanceRequest setPaymentDataForGovernmentToPerson() {
        RemittanceRequest paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status

        paymentRequest.setRemittanceReference("08" + System.currentTimeMillis() + "RMFQ");
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
        paymentRequest.setPaymentType("G2P");

        QuoteType forwardQuote = new QuoteType();
        ForwardFees forwardFees = new ForwardFees();
        /*Note: If quote type is not set default value is forwardQuotes with FeesIncluded = true */
        forwardFees.setFeesIncluded("true");
        forwardFees.setReceiverCurrency("USD");
        forwardQuote.setForwardFees(forwardFees);
        paymentRequest.setFxType(forwardQuote);

        /*Sender Information */
        CustomerData senderData = new CustomerData();
        //Need to be set when payment type is G2P
        senderData.setOrganizationName("WU");
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
        //Need to be set when payment type is G2P
        recipientData.setFirstName("JOHN");
        //Need to be set when payment type is G2P
        recipientData.setLastName("SMITH");
        recipientData.setEmail("test@gmail.com");
        recipientData.setNationality("USA");

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
        RemittanceRequest paymentRequest = new RemittanceRequest();
        paymentRequest.setRemittanceReference(String.valueOf(System.currentTimeMillis()));
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

    /**
     * This method will set data for forward quote with fees included and payment type is B2P.
     * For Payemnt type B2P following information should be set
     * senders information -> Organization name
     * recipients information -> firstname, lastname should be set, middlename being optional
     * @return RemittanceRequest
     */
    public static RemittanceRequest setPaymentDataForReverseQuote() {
        RemittanceRequest paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status
        paymentRequest.setRemittanceReference("08" + System.currentTimeMillis() + "RMRQ");
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
        paymentRequest.setPaymentType("B2P");

        /*Sender Information */
        CustomerData senderData = new CustomerData();

        senderData.setNationality("IND");
        senderData.setOrganizationName("WU");
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
        recipientData.setFirstName("JOHN");
        recipientData.setLastName("SMITH");
        recipientData.setEmail("test@gmail.com");
        recipientData.setNationality("USA");
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
        RemittanceRequest paymentRequest = new RemittanceRequest();
        paymentRequest.setRemittanceReference("08" + System.currentTimeMillis() + "RMFQ");
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
