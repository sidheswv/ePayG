package com.timeinc.tcs.epayG.constants;

import java.nio.charset.Charset;

import org.bouncycastle.util.encoders.Hex;

public class AndroidPayConstants {

	public static final String ANDPAYTYPE = "ANDPAYTYPE";
	public static final String ANDPAYDATA = "ANDPAYDATA";
	public static final String ANDPAYVALUE = "ANDPAYVALUE";
	public static final String EFFORT_KEY = "MSCEKX";
	public static final String EFFORT_KEY_OPTION = "MSCEFKOP";
	public static final String DOLLAR_VALUE = "MSDDAMT";
	public static final String CUSTOMER_NAME = "MSRSUBNM";
	public static final String KEYLINE = "MSRSUBKX";
	public static final String EFFORT_TERM = "MSCTERM";
	public static final String EFFORT_VALUE = "MSCVALUE";
	public static final String MAG_CODE = "MSRSMAG";
	public static final String SEG_ID = "MSRSEGID";
	public static final String PDAT = "MSRSPDAT";
	public static final String UNIQ = "MSRSUNIQ";
	public static final String CUSTOMER_ADDRESS_1 = "MSRADDR1";
	public static final String CUSTOMER_ADDRESS_2 = "MSRADDR2";
	public static final String CUSTOMER_EMAIL = "MSREMAIL";
	public static final String POSTAL_CODE = "MSRPOST";
	public static final String CITY_STREET = "MSRCTYST";
	public static final String ACCOUNT_NUMBER = "MSRACCT";
	public static final String ACTIVITY = "MSRACT";
	public static final String TAX = "MSRTAX";
	public static final String TOTAL_VALUE = "MSRTOT";
	public static final String IS_FROM_BATCH = "ISFROMBATCH";
	public static final String IS_TEST_ENV = "ISTESTENV";
	public static final String ANDROID = "ANDROID";
	public static final String IS_ANDROID_PAY_RETRY = "ISANDROIDPAYRETRY";
	public static final String IS_FROM_LISTENER = "ISFROMLISTENER";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String EPAYGOWNER = "epayGowner";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SECURITY_PROVIDER = "BC";
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	public static final String ASYMMETRIC_KEY_TYPE = "ECDH";
	public static final String KEY_AGREEMENT_ALGORITHM = "ECDH";
	public static final String EC_CURVE = "prime256v1";
	public static final String SYMMETRIC_KEY_TYPE = "AES";
	public static final String SYMMETRIC_ALGORITHM = "AES/CTR/NoPadding";
	public static final byte[] SYMMETRIC_IV = Hex
			.decode("00000000000000000000000000000000");
	public static final int SYMMETRIC_KEY_BYTE_COUNT = 16;
	public static final String MAC_ALGORITHM = "HmacSHA256";
	public static final int MAC_KEY_BYTE_COUNT = 16;
	public static final byte[] HKDF_INFO = "Android".getBytes(DEFAULT_CHARSET);
	public static final byte[] HKDF_SALT = null;
	public static final String TEXT_PLAIN = "text/plain";
	public static final String PROCSTATUS = "PROCSTATUS";
	public static final String PROCSTATUS_CODE_SUCCESS = "0";
	public static final String PROCSTATUS_CODE_FAILURE = "9999";
	public static final String CRYPTOGRAM = "CRYPTOGRAM";
	public static final String DPAN = "DPAN";
	public static final String EXPIRY_MONTH = "EXPIRY_MONTH";
	public static final String EXPIRY_YEAR = "EXPIRY_YEAR";
	public static final String ANDROID_PROPERTIES_FILE = "androidPayProperties";
	public static final String MERCHANT_PRIVATE_KEY_PKCS8_BASE64 = "MERCHANT_PRIVATE_KEY_PKCS8_BASE64";
	
}
