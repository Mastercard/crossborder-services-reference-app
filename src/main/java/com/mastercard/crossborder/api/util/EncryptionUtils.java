package com.mastercard.crossborder.api.util;

import com.mastercard.crossborder.api.exception.ServiceException;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;


import org.springframework.core.io.Resource;


import java.io.IOException;
import java.io.InputStream;

import java.security.KeyStore;

import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


public class EncryptionUtils {

	private EncryptionUtils() {
		//to hide public constructor
	}

	private static final JWEAlgorithm ALG = JWEAlgorithm.RSA_OAEP_256;
		private static final EncryptionMethod ENC_MTHD = EncryptionMethod.A256GCM;

		public static String jweEncrypt(String plainData, Resource crtFileName, String keyFingerPrint, String requestContentType,String decryptionKeyAlias, String decryptionPassword) throws ServiceException {
			try {
				RSAPublicKey rsaPublicKey = (RSAPublicKey) getPublicKeyFromCrt(crtFileName);
				return encryptWithPublicKey(plainData, rsaPublicKey, keyFingerPrint, requestContentType);
			}catch (JOSEException je){
				throw new ServiceException(je.getMessage());
			}
		}

		public static String jweDecrypt(String cipher, Resource privateKeyFile,String decryptionKeyAlias, String decryptionPassword) throws ServiceException {

			try {
				// Decrypt JWE with CEK directly, with the DirectDecrypter in promiscuous mode
				JWEObject jwe = JWEObject.parse(cipher);
				jwe.decrypt(new RSADecrypter(getPrivateKey(privateKeyFile,decryptionKeyAlias,decryptionPassword)));
				return jwe.getPayload().toString();
			}
			catch(Exception e ){
				throw new ServiceException(e.getMessage());
			}
		}

		private static PrivateKey getPrivateKey(Resource keyPath,String decryptionKeyAlias, String decryptionPassword) throws Exception {
			InputStream is =  keyPath.getInputStream();
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(is,decryptionPassword.toCharArray());
			return  (RSAPrivateKey) keyStore.getKey(decryptionKeyAlias, decryptionPassword.toCharArray());
		}
		private static String encryptWithPublicKey(String plainData, RSAPublicKey rsaPublicKey, String keyFingerPrint, String requestContentType) throws JOSEException {
			// Encrypt the JWE with the RSA public key + specified AES CEK
			final JWEHeader.Builder builder = new JWEHeader.Builder(ALG, ENC_MTHD);
			if(keyFingerPrint != null){
				builder.keyID(keyFingerPrint);
			}
			if(requestContentType != null){
				builder.contentType(requestContentType);
			}
			final JWEHeader jweHeader = builder.build();
			JWEObject jwe = new JWEObject(
					jweHeader,
					new Payload(plainData));

			final RSAEncrypter encrypter = new RSAEncrypter(rsaPublicKey);

			jwe.encrypt(encrypter);
			return jwe.serialize();
		}


		private static PublicKey getPublicKeyFromCrt(Resource filePath) throws ServiceException {
			try{
			InputStream is = filePath.getInputStream();
			CertificateFactory factory = CertificateFactory.getInstance("X.509", "SUN"); //"X.509"
			return factory.generateCertificate(is).getPublicKey();
			}
			catch (IOException | CertificateException | NoSuchProviderException ke){
				throw new ServiceException(ke.getMessage());
			}

		}



}
