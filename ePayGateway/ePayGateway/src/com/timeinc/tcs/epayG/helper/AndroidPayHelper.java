package com.timeinc.tcs.epayG.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import com.timeinc.tcs.epayG.constants.AndroidPayConstants;
import com.timeinc.tcs.epayG.dto.AndroidPayData;
import com.timeinc.tcs.epayG.messages.DecryptedResponse;

public class AndroidPayHelper {

	/**
	 * Method to populate Android Pay data in HTTPServletRequest to Java Bean
	 * 
	 * @param request
	 * @return
	 */
	public static AndroidPayData populateDataForRequest(
			HttpServletRequest request) {
		AndroidPayData androidPayData = new AndroidPayData();
		if (request.getHeader(AndroidPayConstants.ANDPAYTYPE) != null) {
			androidPayData.setAndroidPayType((String) request
					.getHeader(AndroidPayConstants.ANDPAYTYPE));
		}
		System.out.println("APAYTYPE: " + androidPayData.getAndroidPayType());

		if (request.getHeader(AndroidPayConstants.ANDPAYVALUE) != null) {
			androidPayData.setAndroidPayValue((String) request
					.getHeader(AndroidPayConstants.ANDPAYVALUE));
		}
		System.out
				.println("ANDPAYVALUE: " + androidPayData.getAndroidPayType());

		if (request.getHeader(AndroidPayConstants.ANDPAYDATA) != null) {
			convertAndroidPayDataToPojo(
					(String) request.getHeader(AndroidPayConstants.ANDPAYDATA),
					androidPayData);
		}
		System.out.println("EPHEMERAL_PUBLIC_KEY: "
				+ androidPayData.getEphemeralPublicKey());
		System.out.println("ENCRYPTED_MESSAGE: "
				+ androidPayData.getEncryptedMessage());
		System.out.println("TAG: " + androidPayData.getTag());

		if (request.getHeader(AndroidPayConstants.EFFORT_KEY) != null) {
			androidPayData.setEffortKey((String) request
					.getHeader(AndroidPayConstants.EFFORT_KEY));
		}
		if (androidPayData.getEffortKey() != null
				&& androidPayData.getEffortKey().trim().length() > 6) {
			androidPayData.setEffortKey(androidPayData.getEffortKey().substring(0,
					6));
		}		
		System.out.println("Effort Key: " + androidPayData.getEffortKey());
		
		if (request.getHeader(AndroidPayConstants.EFFORT_KEY_OPTION) != null) {
			androidPayData.setEffortKeyOption((String) request
					.getHeader(AndroidPayConstants.EFFORT_KEY_OPTION));
		}
		System.out.println("Effort Key Option: "
				+ androidPayData.getEffortKeyOption());

		if (request.getHeader(AndroidPayConstants.DOLLAR_VALUE) != null) {
			androidPayData.setDollarValue((String) request
					.getHeader(AndroidPayConstants.DOLLAR_VALUE));
		}
		System.out.println("Dollar Value: " + androidPayData.getDollarValue());

		if (request.getHeader(AndroidPayConstants.CUSTOMER_NAME) != null) {
			androidPayData.setCustName((String) request
					.getHeader(AndroidPayConstants.CUSTOMER_NAME));
		}
		System.out.println("Customer Name: " + androidPayData.getCustName());

		if (request.getHeader(AndroidPayConstants.EFFORT_TERM) != null) {
			androidPayData.setEffortTerm((String) request
					.getHeader(AndroidPayConstants.EFFORT_TERM));
		}
		System.out.println("Effort term: " + androidPayData.getEffortTerm());

		if (request.getHeader(AndroidPayConstants.EFFORT_VALUE) != null) {
			androidPayData.setEffortValue((String) request
					.getHeader(AndroidPayConstants.EFFORT_VALUE));
		}
		System.out.println("Effort Value: " + androidPayData.getEffortValue());

		if (request.getHeader(AndroidPayConstants.MAG_CODE) != null) {
			androidPayData.setMagCode((String) request
					.getHeader(AndroidPayConstants.MAG_CODE));
		}
		System.out.println("Mag Code: " + androidPayData.getMagCode());
		
		if (request.getHeader(AndroidPayConstants.IS_FROM_BATCH) != null) {
			androidPayData.setIsFromBatch(Boolean.valueOf(request
					.getHeader(AndroidPayConstants.IS_FROM_BATCH)));
		}
		System.out.println("Is From Batch: " + androidPayData.getIsFromBatch());
		
		if (request.getHeader(AndroidPayConstants.IS_TEST_ENV) != null) {
			androidPayData.setIsTestEnv(Boolean.valueOf((String) request
					.getHeader(AndroidPayConstants.IS_TEST_ENV)));
		}
		System.out.println("Is Test Env: "
				+ androidPayData.getIsTestEnv().toString());
		
		if (request.getHeader(AndroidPayConstants.KEYLINE) != null) {
			androidPayData.setKeyline((String) request
					.getHeader(AndroidPayConstants.KEYLINE));
		}
		System.out.println("Keyline: " + androidPayData.getKeyline());
		
		if (request.getHeader(AndroidPayConstants.CUSTOMER_ADDRESS_1) != null) {
			androidPayData.setCustAddr1((String) request
					.getHeader(AndroidPayConstants.CUSTOMER_ADDRESS_1));
		}
		System.out.println("Cust Addr 1: " + androidPayData.getCustAddr1());
		
		if (request.getHeader(AndroidPayConstants.CUSTOMER_ADDRESS_2) != null) {
			androidPayData.setCustAddr2((String) request
					.getHeader(AndroidPayConstants.CUSTOMER_ADDRESS_2));
		}
		System.out.println("Cust Addr 2: " + androidPayData.getCustAddr2());
		
		if (request.getHeader(AndroidPayConstants.CUSTOMER_EMAIL) != null) {
			androidPayData.setCustEmail((String) request
					.getHeader(AndroidPayConstants.CUSTOMER_EMAIL));
		}
		System.out.println("Customer Email: " + androidPayData.getCustEmail());
		
		if (request.getHeader(AndroidPayConstants.POSTAL_CODE) != null) {
			androidPayData.setPostalCode((String) request
					.getHeader(AndroidPayConstants.POSTAL_CODE));
		}
		System.out.println("Postal Code: " + androidPayData.getPostalCode());
		
		if (request.getHeader(AndroidPayConstants.CITY_STREET) != null) {

			androidPayData.setCityStreet((String) request
					.getHeader(AndroidPayConstants.CITY_STREET));
		}
		System.out.println("City Street: " + androidPayData.getCityStreet());
		
		if (request.getHeader(AndroidPayConstants.SEG_ID) != null) {
			androidPayData.setSegId((String) request
					.getHeader(AndroidPayConstants.SEG_ID));
		}
		System.out.println("Seg Id: " + androidPayData.getSegId());
		
		if (request.getHeader(AndroidPayConstants.PDAT) != null) {
			androidPayData.setPdat((String) request
					.getHeader(AndroidPayConstants.PDAT));
		}
		System.out.println("PDAT: " + androidPayData.getPdat());
		
		if (request.getHeader(AndroidPayConstants.UNIQ) != null) {
			androidPayData.setUniq((String) request
					.getHeader(AndroidPayConstants.UNIQ));
		}
		System.out.println("Uniq: " + androidPayData.getUniq());
		
		if (request.getHeader(AndroidPayConstants.ACCOUNT_NUMBER) != null) {
			androidPayData.setAccountNumber((String) request
					.getHeader(AndroidPayConstants.ACCOUNT_NUMBER));
		}
		System.out
				.println("Account Number: " + androidPayData.getAccountNumber());
		
		if (request.getHeader(AndroidPayConstants.ACTIVITY) != null) {
			androidPayData.setActivity((String) request
					.getHeader(AndroidPayConstants.ACTIVITY));
		}
		System.out.println("Activity: " + androidPayData.getActivity());
		
		if (request.getHeader(AndroidPayConstants.TAX) != null) {
			androidPayData.setTax((String) request
					.getHeader(AndroidPayConstants.TAX));
		}
		System.out.println("Tax: " + androidPayData.getTax());
		
		if (request.getHeader(AndroidPayConstants.TOTAL_VALUE) != null) {
			androidPayData.setTotalValue((String) request
					.getHeader(AndroidPayConstants.TOTAL_VALUE));
		}
		System.out.println("Total Value: " + androidPayData.getTotalValue());
		
		if (request.getHeader(AndroidPayConstants.IS_ANDROID_PAY_RETRY) != null) {
			androidPayData.setIsAndroidPayRetry(Boolean.valueOf(request
					.getHeader(AndroidPayConstants.IS_ANDROID_PAY_RETRY)));
		}
		System.out.println("Is Android Pay Retry: " + androidPayData.getIsAndroidPayRetry());
		
		if (request.getHeader(AndroidPayConstants.IS_FROM_LISTENER) != null) {
			androidPayData.setIsFromListener(Boolean.valueOf((String) request
					.getHeader(AndroidPayConstants.IS_FROM_LISTENER)));
		}
		System.out.println("Is From Listener: "
				+ androidPayData.getIsFromListener().toString());
		
		return androidPayData;
	}

	/**
	 * Method for reading the AndroidPay private key
	 * from the AndroidPay Properties file.
	 * 
	 * @return
	 * @throws NamingException
	 * @throws IOException
	 */
	public static Properties getAndroidPayProperties() throws NamingException,
			IOException {
		Properties androidPayProperties = null;
		InputStream iStream = null;
		try {
			InitialContext iCtx = new InitialContext();
			String androidProperties = (String) iCtx
					.lookup(AndroidPayConstants.ANDROID_PROPERTIES_FILE);

			if (androidProperties == null || androidProperties == "") {
				System.out.println("AndroidPay Properties lookup failed");
				throw new FileNotFoundException();
			}

			iStream = new FileInputStream(androidProperties);
			androidPayProperties = new Properties();
			androidPayProperties.load(iStream);
		} catch (NamingException e) {
			System.out
					.println("Naming Error at Finding AndroidPay properties file"
							+ e.getMessage());
			throw e;
		} catch (IOException e) {
			System.out
					.println("IO Exception when retrieving AndroidPay properties file"
							+ e.getMessage());
			throw e;
		}
		return androidPayProperties;
	}

	/**
	 * Method to convert JSON String to POJO
	 * 
	 * @param jsonString
	 * @return
	 */
	private static AndroidPayData convertAndroidPayDataToPojo(String jsonString,AndroidPayData androidPayData) {
		StringTokenizer st = new StringTokenizer(jsonString, ",");
		while (st.hasMoreElements()) {
			String str = st.nextToken();
			if (str.contains("encryptedMessage:")) {
				androidPayData.setEncryptedMessage(str.substring(str.indexOf(":") + 1).replaceAll("\\\\", ""));
			}
			if (str.contains("ephemeralPublicKey:")) {
				androidPayData.setEphemeralPublicKey(str.substring(str
						.indexOf(":") + 1).replaceAll("\\\\", ""));
			}
			if (str.contains("tag:")) {
				androidPayData.setTag(str.substring(str
						.indexOf(":") + 1).replace("}", "").replaceAll("\\\\", ""));
			}
		}
		return androidPayData;
	}
	
	
	/**
	 * Method to convert JSON String to POJO
	 * 
	 * @param jsonString
	 * @return
	 */
	public static DecryptedResponse convertResponseToPojo(String jsonString) {
		String json = jsonString.replaceAll("\"", "");
		DecryptedResponse decryptedResponse = new DecryptedResponse();
		StringTokenizer st = new StringTokenizer(json, ",");
		while (st.hasMoreElements()) {
			String str = st.nextToken();
			if (str.contains("dpan:")) {
				decryptedResponse.setDpan(str.substring(str.indexOf(":") + 1).replace("}", ""));
			}
			if (str.contains("expirationMonth:")) {
				decryptedResponse.setExpirationMonth(str.substring(str
						.indexOf(":") + 1).replace("}", ""));
			}
			if (str.contains("expirationYear:")) {
				decryptedResponse.setExpirationYear(str.substring(str
						.indexOf(":") + 1).replace("}", ""));
			}
			if (str.contains("authMethod:")) {
				decryptedResponse
						.setAuthMethod(str.substring(str.indexOf(":") + 1).replace("}", ""));
			}
			if (str.contains("3dsCryptogram:")) {
				decryptedResponse
						.setCryptogram(str.substring(str.indexOf(":") + 1).replace("}", ""));
			}
			if (str.contains("3dsEciIndicator:")) {
				decryptedResponse.setEciIndicator(str.substring(
						str.indexOf(":") + 1).replace("}", ""));
			}
		}
		return decryptedResponse;
	}

	/**
	 * Method returns the current date and time in "yyyy-MM-dd HH:mm:ss" format
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(
				AndroidPayConstants.DATE_TIME_FORMAT);
		return sdf.format(cal.getTime());
	}
}
