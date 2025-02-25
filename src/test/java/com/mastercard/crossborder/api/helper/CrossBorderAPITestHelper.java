package com.mastercard.crossborder.api.helper;

import com.mastercard.crossborder.api.rest.request.*;

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
        RemittanceRequest  paymentRequest = new RemittanceRequest();
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
        RemittanceRequest  paymentRequest = new RemittanceRequest();
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
        RemittanceRequest  paymentRequest = new RemittanceRequest();
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
        RemittanceRequest  paymentRequest = new RemittanceRequest();
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
        RemittanceRequest  paymentRequest = new RemittanceRequest();
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
        RemittanceRequest  paymentRequest = new RemittanceRequest();
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
        RemittanceRequest  paymentRequest = new RemittanceRequest();
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
    public static QuoteConfirmation setDataForQuoteConfirmation(String proposalId, String transactionReference ){
        QuoteConfirmation quoteConfirmationRequest=new QuoteConfirmation();
        quoteConfirmationRequest.setProposalId(proposalId);
        quoteConfirmationRequest.setTransactionReference(transactionReference);
        return quoteConfirmationRequest;
    }
    public static UploadDocumentRequest setDataForUploadDocument(){
        /* set the input */
        UploadDocumentRequest request = new UploadDocumentRequest();
        request.setFileName("test.doc");
        request.setFile("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");


        return request;
    }
    public static UpdateRequest setDataForUpdateRequest(){
        /* set the input */
        UpdateRequest request = new UpdateRequest();
        Sender sender=new Sender();
        sender.setFullName("Joseph Bloggs");

        request.setSender(sender);


        return request;
    }

    /**
     * Method to set data for timeout case
     * @return QuotesRequest
     */
    public static QuotesRequest setDataForForwardQuoteWithFeesIncludedForTimeout(){
        /* set the input */
        QuotesRequest request = new QuotesRequest();
        request.setProposalReference("07" + System.currentTimeMillis() + "TO");
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
    /**
     * This method will set data for one shot payment forward quote with fees not included and payment type is P2B used in
     * case of timeout scenario.
     * For Payment type P2B following information should be set
     * senders information -> firstname, lastname should be set, middlename being optional.
     * recipients information -> organisational name
     * @return RemittanceRequest
     */
    public static RemittanceRequest setPaymentDataForwardQuoteFeesNotIncludedForTimeout() {
        RemittanceRequest  paymentRequest = new RemittanceRequest();
        //This will make payment in Pending status
        paymentRequest.setRemittanceReference("08" + System.currentTimeMillis() + "TO");
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

}
