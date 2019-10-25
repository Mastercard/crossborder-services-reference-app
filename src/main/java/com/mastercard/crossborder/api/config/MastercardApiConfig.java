package com.mastercard.crossborder.api.config;

import com.mastercard.crossborder.api.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;


@Configuration
@PropertySource("mastercard-api.properties")
@ComponentScan(basePackages = {"com.mastercard.crossborder"})
public class MastercardApiConfig {

    @Value("${mastercard.api.authentication.consumerKey}")
    private String consumerKey;

    @Value("${mastercard.api.authentication.keystore.keyalias}")
    private String keyAlias;

    @Value("${mastercard.api.authentication.keystore.password}")
    private String keyPassword;

    @Value("${mastercard.api.authentication.keystore.keyFile}")
    private Resource p12File;

    @Value("${mastercard.api.environment.sandbox.endPointURL}")
    private String sandboxEndPointURL;

    @Value("${mastercard.api.environment.sandbox.partnerId}")
    private String sandboxPartnerId;

    @Value("${mastercard.api.environment.runWithEncryptedPayload}")
    private Boolean runWithEncryptedPayload;

    @Value("${mastercard.api.encryption.certificateFile}")
    private Resource certificateFile;

    @Value("${mastercard.api.encryption.fingerPrint}")
    private String encryptionFP;

    @Value("${mastercard.api.decryption.keyFile}")
    private Resource decryptionKeyFile;

    @PostConstruct
    public void setupApiConfiguration() throws ServiceException {
        if(null == p12File || StringUtils.isEmpty( consumerKey))
            throw new ServiceException(".p12 file or consumerKey does not exist, please add details in mastercard-api.properties");
        if ( getRunWithEncryptedPayload().booleanValue() && ( StringUtils.isEmpty(encryptionFP) || null == certificateFile || null == decryptionKeyFile))
            throw new ServiceException("Key parameters required for encryption are not set, please add details in mastercard-api.properties");
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public Resource getP12File() {
        return p12File;
    }

    public String getEndPointURL() {
        return sandboxEndPointURL;
    }
    public String getPartnerId()  {
        return sandboxPartnerId;
    }

    public Boolean getRunWithEncryptedPayload() {
        return (null != runWithEncryptedPayload && runWithEncryptedPayload) || Boolean.FALSE ;
    }

    public Resource getCertificateFile() {
        return certificateFile;
    }

    public String getEncryptionFP() {
        return encryptionFP;
    }

    public Resource getDecryptionKeyFile() {
        return decryptionKeyFile;
    }

}
