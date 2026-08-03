package com.mastercard.crossborder.vas.bav.api.helper;

import com.mastercard.crossborder.api.rest.vas.bav.api.request.AccountUri;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.BAVAddress;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.Bank;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.BankInfoLookupRequest;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.Bic;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.IBanValidationRequest;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.IbanCreationDetails;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.validate.AccountDetails;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.validate.AccountHolder;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.validate.AccountHolderName;
import com.mastercard.crossborder.api.rest.vas.bav.api.request.validate.ValidateRequestType;

public class BavHelperApi {

    /**** IBan Generation request payloads ****/
    public static IbanCreationDetails createIban(){
        AccountUri accountUri = new AccountUri();
        accountUri.setType("ban");
        accountUri.setValue("0604511619000000000001");
        return new IbanCreationDetails(accountUri,"ITA","0604511619","000000000001");
    }

    public static IbanCreationDetails createIbanWithoutAccount(){
        return new IbanCreationDetails(null,"ITA","0604511619","000000000001");
    }

    public static  IbanCreationDetails createIBanWithNoCountry(){
        AccountUri accountUri = new AccountUri();
        accountUri.setType("ban");
        accountUri.setValue("0604511619000000000001");
        return new IbanCreationDetails(accountUri,null,"0604511619","000000000001");
    }

    public static IbanCreationDetails createIbanWithoutBranchCodeAndAccountNumber(){
        AccountUri accountUri = new AccountUri();
        accountUri.setType("ban");
        accountUri.setValue("0604511619000000000001");
        return new IbanCreationDetails(accountUri,"ITA",null,null);
    }

    public static IbanCreationDetails createIbanWithInvalidAccountValueLength(){
        AccountUri accountUri = new AccountUri();
        accountUri.setType("ban");
        accountUri.setValue("W06045116190000000000010000000000000000000000000000000000");
        return new IbanCreationDetails(accountUri,"ITA","0604511619","000000000001");
    }

    public static IbanCreationDetails createIbanWithInvalidAccountType(){
        AccountUri accountUri = new AccountUri();
        accountUri.setType("fBan");
        accountUri.setValue("0604511619000000000001");
        return new IbanCreationDetails(accountUri,"ITA","0604511619","000000000001");
    }

    public static IbanCreationDetails createIbanWithInvalidCountryFormat(){
        AccountUri accountUri = new AccountUri();
        accountUri.setType("ban");
        accountUri.setValue("0604511619000000000001");
        return new IbanCreationDetails(accountUri,"#TA","0604511619","000000000001");
    }

    /**** IBan Validation request payloads ****/
    public static IBanValidationRequest createAccount(){
        return new IBanValidationRequest(new AccountUri("IBAN","GB33BUKB20201555555555"));
    }

    public static IBanValidationRequest validateCardEligibility(){
        AccountUri accountUri = new AccountUri("PAN","4960144263583144");
        return new IBanValidationRequest(ValidateRequestType.CES, accountUri);
    }

    public static IBanValidationRequest createAccountWithEmptyType(){
        return new IBanValidationRequest(new AccountUri("IBAN",null));
    }

    public static IBanValidationRequest createAccountWithWrongType(){
        return new IBanValidationRequest(new AccountUri("TAN","IT83W0604511619000000000001"));
    }
    public static IBanValidationRequest createAccountWithInvalidValueLength(){
        return new IBanValidationRequest(new AccountUri("IBAN","IT83W060451161900000000000100000000000000000000000000000000"));
    }
    public static IBanValidationRequest createAccountWithInvalidValueFormat(){
        return new IBanValidationRequest (new AccountUri("IBAN","IT83W06045116#19000000000001"));
    }

    /**** Bank Info Request Payloads ****/
    public static BankInfoLookupRequest createBank() {
        Bic bic= new Bic("UKSORT","204846",null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AE");
        Bank bank = new Bank("Barclays Bank PLC",
                "Oxford Rd","GBR",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }
    public static BankInfoLookupRequest createBankWithNameAndCountry() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress("Belfast",null,null);
        Bank bank = new Bank("Barclays Bank PLC",
                null,"GBR",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static BankInfoLookupRequest createBankWithBicDetails() {
        Bic bic= new Bic("UKSORT","204846",null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AE");
        Bank bank = new Bank(null,
                "Oxford Rd","GBR",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static BankInfoLookupRequest createBankWithBankNameAndNoCountry() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AE");
        Bank bank = new Bank("Barclays Bank PLC",
                "Oxford Rd",null,bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static BankInfoLookupRequest createBankWithBankNameAndCountryNotSupported() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AE");
        Bank bank = new Bank("Barclays Bank PLC",
                "Oxford Rd","ARG",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static BankInfoLookupRequest createBankWithInvalidCountryFormat() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AE");
        Bank bank = new Bank("Barclays Bank PLC",
                "Oxford Rd","G@R",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static BankInfoLookupRequest createBankWithNoRecord() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AE");
        Bank bank = new Bank("Bank of Queensland Limited",
                "Oxford Rd","GBR",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static BankInfoLookupRequest createBankWithCountryNotSupported() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AE");
        Bank bank = new Bank("Bank of Queensland Limited",
                "Oxford Rd","SBM",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static BankInfoLookupRequest createBankWithInvalidCountry() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress(null,null,null);
        Bank bank = new Bank("Barclays Bank PLC",
                "Oxford Rd","XYZ",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }
    public static BankInfoLookupRequest createBankWithInvalidCountryLengthAndPostalCodeLength() {
        Bic bic= new Bic(null,null,null,null);
        BAVAddress bavAddress = new BAVAddress("Guiseley",null,"LS20 8AELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        Bank bank = new Bank("Barclays Bank PLC",
                "Oxford Rd","GBRRR",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

    public static IBanValidationRequest validateCardEligibility(){
        AccountUri accountUri = new AccountUri("PAN","4960144263583144");
        return new IBanValidationRequest(ValidateRequestType.CES, accountUri);
    }

    public static IBanValidationRequest validateAccountStatus(){
        AccountUri accountUri = new AccountUri("BAN","98000987651232");
        AccountHolder accountHolder = new AccountHolder(new AccountHolderName("Tim","John", "Smith"), "1234567");
        Bic bic = new Bic("ABA","123456789",null,null);
        AccountDetails accountDetails = new AccountDetails("USD", accountHolder, bic);
        return new IBanValidationRequest(ValidateRequestType.ASV, accountUri, accountDetails);
    }
    public static BankInfoLookupRequest createBankWithBicAchWireDetails() {
        Bic bic= new Bic("ABA","122240861",null,null);
        BAVAddress bavAddress = null;
        Bank bank = new Bank("FedSUC004",
                null,"USA",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }
    public static BankInfoLookupRequest createBankWithBicAchDetails() {
        Bic bic= new Bic("ABA","122240861",null,null);
        BAVAddress bavAddress = new BAVAddress(null,null,null);
        Bank bank = new Bank("FedSUC003",
                null,"USA",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }
    public static BankInfoLookupRequest createBankWithBicWireDetails() {
        Bic bic= new Bic("ABA","122240861",null,null);
        BAVAddress bavAddress = new BAVAddress(null,null,null);
        Bank bank = new Bank("FedSUC005",
                null,"USA",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }
    public static BankInfoLookupRequest createBankWithBicInvalidDetails() {
        Bic bic= new Bic("ABA","122240861",null,null);
        BAVAddress bavAddress = new BAVAddress(null,null,null);
        Bank bank = new Bank("FedSUC002",
                null,"USA",bic,bavAddress);
        return new BankInfoLookupRequest(bank);
    }

}
