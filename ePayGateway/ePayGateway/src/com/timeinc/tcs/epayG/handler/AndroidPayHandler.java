package com.timeinc.tcs.epayG.handler;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.InitialContext;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Base64;

import com.google.common.io.BaseEncoding;
import com.timeinc.tcs.epayG.constants.AndroidPayConstants;
import com.timeinc.tcs.epayG.dto.AndroidPayData;
import com.timeinc.tcs.epayG.helper.AndroidPayHelper;
import com.timeinc.tcs.epayG.messages.DecryptedResponse;
import com.timeinc.tcs.epayG.model.DBAccess;
import com.timeinc.tcs.epayG.transaction.AndroidPayTransaction;

public class AndroidPayHandler {

	private PrivateKey privateKey;

	private AndroidPayHandler(PrivateKey privateKey) {
		if (!AndroidPayConstants.ASYMMETRIC_KEY_TYPE.equals(privateKey.getAlgorithm())) {
			throw new IllegalArgumentException("Unexpected type of private key");
		}
		this.privateKey = privateKey;
	}

	public static AndroidPayHandler createFromPkcs8EncodedPrivateKey(
			byte[] pkcs8PrivateKey) {
		PrivateKey privateKey;
		setupSecurityProvider();
		try {
			KeyFactory asymmetricKeyFactory = KeyFactory.getInstance(
					AndroidPayConstants.ASYMMETRIC_KEY_TYPE, AndroidPayConstants.SECURITY_PROVIDER);
			privateKey = asymmetricKeyFactory
					.generatePrivate(new PKCS8EncodedKeySpec(pkcs8PrivateKey));
		} catch (NoSuchAlgorithmException e1) {
			throw new RuntimeException(
					"NoSuchAlgorithmException at UnbundleService.UnbundleService",
					e1);
		} catch (NoSuchProviderException e2) {
			throw new RuntimeException(
					"NoSuchProviderException at UnbundleService.UnbundleService",
					e2);
		} catch (InvalidKeySpecException e3) {
			throw new RuntimeException(
					"InvalidKeySpecException at UnbundleService.UnbundleService",
					e3);
		}
		return new AndroidPayHandler(privateKey);
	}

	/**
	 * Sets up the {@link #SECURITY_PROVIDER} if not yet set up.
	 *
	 * You must call this method at least once before using this class.
	 */
	public static void setupSecurityProvider() {
		if (Security.getProvider(AndroidPayConstants.SECURITY_PROVIDER) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
	}

	/**
	 * Verifies then decrypts the given payload according to the Android Pay
	 * Network Token encryption spec.
	 */
	public String verifyAndDecrypt(AndroidPayData androidPayData) {
		try {
			byte[] ephemeralPublicKeyBytes = Base64.decode(androidPayData.getEphemeralPublicKey());
			byte[] encryptedMessageBytes = Base64.decode(androidPayData.getEncryptedMessage());
			byte[] tagBytes = Base64.decode(androidPayData.getTag());
			ECParameterSpec asymmetricKeyParams = generateECParameterSpec();
			KeyFactory asymmetricKeyFactory = KeyFactory.getInstance(
					AndroidPayConstants.ASYMMETRIC_KEY_TYPE, AndroidPayConstants.SECURITY_PROVIDER);
			PublicKey ephemeralPublicKy = asymmetricKeyFactory
					.generatePublic(new ECPublicKeySpec(ECPointUtil
							.decodePoint(asymmetricKeyParams.getCurve(),
									ephemeralPublicKeyBytes),
							asymmetricKeyParams));

			KeyAgreement keyAgreement = KeyAgreement.getInstance(
					AndroidPayConstants.KEY_AGREEMENT_ALGORITHM, AndroidPayConstants.SECURITY_PROVIDER);
			keyAgreement.init(privateKey);
			keyAgreement.doPhase(ephemeralPublicKy, true);
			byte[] sharedSecret = keyAgreement.generateSecret();
			HKDFBytesGenerator hkdfBytesGenerator = new HKDFBytesGenerator(
					new SHA256Digest());
			byte[] khdfInput = ByteUtils.concatenate(ephemeralPublicKeyBytes,
					sharedSecret);
			hkdfBytesGenerator.init(new HKDFParameters(khdfInput, AndroidPayConstants.HKDF_SALT,
					AndroidPayConstants.HKDF_INFO));
			byte[] encryptionKey = new byte[AndroidPayConstants.SYMMETRIC_KEY_BYTE_COUNT];
			hkdfBytesGenerator.generateBytes(encryptionKey, 0,
					AndroidPayConstants.SYMMETRIC_KEY_BYTE_COUNT);
			byte[] macKey = new byte[AndroidPayConstants.MAC_KEY_BYTE_COUNT];
			hkdfBytesGenerator.generateBytes(macKey, 0, AndroidPayConstants.MAC_KEY_BYTE_COUNT);

			Mac macGenerator = Mac
					.getInstance(AndroidPayConstants.MAC_ALGORITHM, AndroidPayConstants.SECURITY_PROVIDER);
			macGenerator.init(new SecretKeySpec(macKey, AndroidPayConstants.MAC_ALGORITHM));
			byte[] expectedTag = macGenerator.doFinal(encryptedMessageBytes);
			if (!isArrayEqual(tagBytes, expectedTag)) {
				throw new RuntimeException("Bad Message Authentication Code!");
			}

			// Decrypting the message.
			Cipher cipher = Cipher.getInstance(AndroidPayConstants.SYMMETRIC_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encryptionKey,
					AndroidPayConstants.SYMMETRIC_KEY_TYPE), new IvParameterSpec(AndroidPayConstants.SYMMETRIC_IV));
			return new String(cipher.doFinal(encryptedMessageBytes),
					AndroidPayConstants.DEFAULT_CHARSET);
		} catch (NoSuchAlgorithmException e2) {
			throw new RuntimeException(
					"NoSuchAlgorithmException at verifyAndDecrypt", e2);
		} catch (NoSuchProviderException e3) {
			throw new RuntimeException(
					"NoSuchProviderException at verifyAndDecrypt", e3);
		} catch (InvalidKeySpecException e4) {
			throw new RuntimeException(
					"InvalidKeySpecException at verifyAndDecrypt", e4);
		} catch (InvalidKeyException e5) {
			throw new RuntimeException("JSONException at verifyAndDecrypt", e5);
		} catch (NoSuchPaddingException e6) {
			throw new RuntimeException(
					"InvalidKeyException at verifyAndDecrypt", e6);
		} catch (InvalidAlgorithmParameterException e7) {
			throw new RuntimeException(
					"InvalidAlgorithmParameterException at verifyAndDecrypt",
					e7);
		} catch (IllegalBlockSizeException e8) {
			throw new RuntimeException(
					"IllegalBlockSizeException at verifyAndDecrypt", e8);
		} catch (BadPaddingException e9) {
			throw new RuntimeException(
					"BadPaddingException at verifyAndDecrypt", e9);
		}
	}

	/**
	 * Method to handle insert database call to insert android pay, effort key and
	 * other information to Orbital Table
	 * 
	 * @param androidPayData
	 */
	public static void handleInsertDatabaseCall(AndroidPayData androidPayData) {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup(AndroidPayConstants.EPAYGOWNER);
			if (dbOwner == null) {
				System.out
						.println("EPayG epayowner for Insert Query is missing");
			} else {
				System.out.println("EPayG epayOwner for Insert Query: "
						+ dbOwner);
				DBAccess.insertAndroidDataToOrbitalTable(dbOwner, ctx, androidPayData);
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at AndroidPayHandler.handleInsertDatabaseCall "
							+ e.getMessage());
		}
	}

	/**
	 * Method to handle database call to update Success flag to Orbital Table
	 * 
	 * @param androidPayData
	 */
	public static void handleUpdateDatabaseCall(AndroidPayData androidPayData) {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup(AndroidPayConstants.EPAYGOWNER);
			if (dbOwner == null) {
				System.out
						.println("EPayG epayowner for Update Query is missing");
			} else {
				System.out.println("EPayG epayOwner for Update Query: "
						+ dbOwner);
				DBAccess.updateAndroidPayToOrbitalTable(dbOwner, ctx, androidPayData);
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at AndroidPayHandler.handleUpdateDatabaseCall "
							+ e.getMessage());
		}
	}
	
	
	/**
	 * Method to handle database call to get android pay details from TBV_ORBITAL_RETRY
	 * table.
	 * 
	 * @return
	 */
	public static List<AndroidPayData> handleGetDatabaseCall() {
		String dbOwner = null;
		List<AndroidPayData> androidPayDataList = new ArrayList<AndroidPayData>();
		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup(AndroidPayConstants.EPAYGOWNER);
			if (dbOwner == null) {
				System.out
						.println("EPayG epayowner for Select Query is missing");
			} else {
				System.out.println("EPayG epayOwner for Select Query: "
						+ dbOwner);
				androidPayDataList = DBAccess.getAndroidPayData(dbOwner, ctx);
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at AndroidPayHandler.handleGetDatabaseCall "
							+ e.getMessage());
		}
		return androidPayDataList;
	}
	
	/**
	 * Method to handle AndroidPay Retry Call logic
	 * 
	 * @param androidPayData
	 */
	public static void handleAndroidPayRetryCall(
			List<AndroidPayData> androidPayDataList,
			Properties androidPayProperties) {
		System.out.println("AndroidPay Retry at: "
				+ AndroidPayHelper.getCurrentDateTime());
		if (androidPayDataList != null && androidPayDataList.size() > 0) {
			for (AndroidPayData androidPayData : androidPayDataList) {
				try {
					AndroidPayHandler androidPayhandler = AndroidPayHandler
							.createFromPkcs8EncodedPrivateKey(BaseEncoding
									.base64()
									.decode(androidPayProperties
											.getProperty(AndroidPayConstants.MERCHANT_PRIVATE_KEY_PKCS8_BASE64)));
					String decryptedData = androidPayhandler
							.verifyAndDecrypt(androidPayData);
					System.out.println("DECRYPTED PAYLOAD at Retry: "
							+ decryptedData);
					DecryptedResponse decryptResponse = AndroidPayHelper
							.convertResponseToPojo(decryptedData);
					System.out.println("DPAN at Retry: "
							+ decryptResponse.getDpan());
					System.out.println("Cryptogram at Retry : "
							+ decryptResponse.getCryptogram());
					if (decryptResponse != null
							&& decryptResponse.getCryptogram() != null) {
						androidPayData.setAndroidPayStatus("Y");
						androidPayData.setIsCryptogramPresent("Y");
						androidPayData.setDpan(decryptResponse.getDpan());
						androidPayData.setCryptogram(decryptResponse.getCryptogram());
						androidPayData.setExpiryMonth(decryptResponse.getExpirationMonth());
						androidPayData.setExpiryYear(decryptResponse.getExpirationYear());
						
						new AndroidPayTransaction().processTCC(androidPayData);
					}
				} catch (Exception e) {
					System.out
							.println("Exception at AndroidPayHandler.handleAndroidPayRetryCall: Unbundling Failed ");
					int count = Integer.parseInt(androidPayData
							.getAndroidPayStatus().trim());
					if (count < 6) {
						count++;
						androidPayData.setAndroidPayStatus(Integer
								.toString(count));
					} else {
						androidPayData.setAndroidPayStatus("Y");
					}
				} finally {
					AndroidPayHandler.handleUpdateDatabaseCall(androidPayData);
				}
			}
		} else {
			System.out.println("AndroidPayHandler.handleAndroidPayRetryCall: No Retry Records to Process");
		}
	}
	
	private ECNamedCurveSpec generateECParameterSpec() {
		ECNamedCurveParameterSpec bcParams = ECNamedCurveTable
				.getParameterSpec(AndroidPayConstants.EC_CURVE);
		ECNamedCurveSpec params = new ECNamedCurveSpec(bcParams.getName(),
				bcParams.getCurve(), bcParams.getG(), bcParams.getN(),
				bcParams.getH(), bcParams.getSeed());
		return params;
	}

	/**
	 * Fixed-timing array comparison.
	 */
	public static boolean isArrayEqual(byte[] a, byte[] b) {
		if (a.length != b.length) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result |= a[i] ^ b[i];
		}
		return result == 0;
	}
}
