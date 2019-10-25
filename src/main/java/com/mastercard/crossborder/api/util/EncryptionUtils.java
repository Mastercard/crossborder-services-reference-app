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
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;


public class EncryptionUtils {

	private EncryptionUtils() {
		//to hide public constructor
	}

	private static final JWEAlgorithm ALG = JWEAlgorithm.RSA_OAEP_256;
		private static final EncryptionMethod ENC_MTHD = EncryptionMethod.A256GCM;

		public static String jweEncrypt(String plainData, Resource crtFileName, String keyFingerPrint, String requestContentType) throws ServiceException {
			try {
				RSAPublicKey rsaPublicKey = (RSAPublicKey) getPublicKeyFromCrt(crtFileName);
				return encryptWithPublicKey(plainData, rsaPublicKey, keyFingerPrint, requestContentType);
			}catch (JOSEException je){
				throw new ServiceException(je.getMessage());
			}
		}


		public static String jweDecrypt(String cipher, Resource privateKeyFile) throws ServiceException {

			try {
				// Decrypt JWE with CEK directly, with the DirectDecrypter in promiscuous mode
				JWEObject jwe = JWEObject.parse(cipher);
				jwe.decrypt(new RSADecrypter(getPrivateKeyFromPKCS8KeyFile(privateKeyFile)));
				return jwe.getPayload().toString();
			}
			catch(ParseException |JOSEException e){
				throw new ServiceException(e.getMessage());
			}
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

		private static PrivateKey getPrivateKeyFromPKCS8KeyFile(Resource keyPath) throws ServiceException  {
			try {
				InputStream is = keyPath.getInputStream();
				KeyFactory kf = KeyFactory.getInstance("RSA", "SunRsaSign");
				PKCS8EncodedKeySpec kspec = new PKCS8EncodedKeySpec(StreamUtils.copyToByteArray(is));
				return kf.generatePrivate(kspec);
			}
			catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchProviderException ke){
				throw new ServiceException(ke.getMessage());
			}
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
