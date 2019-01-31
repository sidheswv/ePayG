package com.timeinc.tcs.epayG.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.JsonObject;
import com.timeinc.tcs.epayG.constants.OrbitalConstants;
import com.timeinc.tcs.epayG.dto.ApplePayData;
import com.timeinc.tcs.epayG.messages.DebundleRequest;
import com.timeinc.tcs.epayG.messages.DebundleResponse;

public class OrbitalHelper {

	/**
	 * Method for reading the Orbital credentials, Bin field and Merchant URL
	 * from the Orbital Properties file.
	 * 
	 * @return
	 * @throws NamingException
	 * @throws IOException
	 */
	public static Properties getOrbitalProperties() throws NamingException,
			IOException {
		Properties orbitProperties = null;
		InputStream iStream = null;
		try {
			InitialContext iCtx = new InitialContext();
			String orbitalProperties = (String) iCtx
					.lookup(OrbitalConstants.ORBITAL_PROPERTIES_FILE);

			if (orbitalProperties == null || orbitalProperties == "") {
				System.out.println("Orbital Properties lookup failed");
				throw new FileNotFoundException();
			}

			iStream = new FileInputStream(orbitalProperties);
			orbitProperties = new Properties();
			orbitProperties.load(iStream);
		} catch (NamingException e) {
			System.out
					.println("Naming Error at Finding Orbital properties file"
							+ e.getMessage());
			throw e;
		} catch (IOException e) {
			System.out
					.println("IO Exception when retrieving Orbital properties file"
							+ e.getMessage());
			throw e;
		}
		return orbitProperties;
	}

	/**
	 * Method for building Debundle Request
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws NamingException
	 */
	public static String buildDebundleRequestXML(ApplePayData applePayData,
			Properties orbitalProperties) throws NamingException, IOException {
		DebundleRequest debundleRequest = new DebundleRequest();
		String jsonData = buildJsonData(applePayData);
		if (applePayData.getIsTestEnv()) {
			debundleRequest.setOrbitalConnectionUsername(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_TEST_USER));
			debundleRequest.setOrbitalConnectionPassword(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_TEST_PWD));
		} else {
			debundleRequest.setOrbitalConnectionUsername(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_USER));
			debundleRequest.setOrbitalConnectionPassword(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_PWD));
		}
		debundleRequest.setBin(orbitalProperties
				.getProperty(OrbitalConstants.ORBITAL_BIN));
		if (!applePayData.getIsOrbitalRetry()) {
			debundleRequest.setMerchantID(orbitalProperties
					.getProperty(OrbitalConstants.MERCHANT_ID_1));
		} else {
			debundleRequest.setMerchantID(orbitalProperties
					.getProperty(OrbitalConstants.MERCHANT_ID_2));
		}
		debundleRequest.setData(jsonData);
		debundleRequest
				.setLatitudeLongitude(OrbitalConstants.ORBITAL_DEFAULT_LATLONG);
		String XMLData = marshallOrbitalRequest(debundleRequest);
		XMLData = XMLData.replaceFirst(OrbitalConstants.DATA_TAG,
				OrbitalConstants.DATA_TAG_WITH_CDATA);
		XMLData = XMLData.replaceFirst(OrbitalConstants.DATA_TAG_CLOSE,
				OrbitalConstants.DATA_TAG_WITH_CDATA_CLOSE);
		System.out.println("Request XML: " + XMLData);
		return XMLData;
	}
	
	
	/**
	 * Method for building Debundle Request for NG
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws NamingException
	 */

public static String ngBuildDebundleRequestXML(ApplePayData applePayData,
			Properties orbitalProperties) throws NamingException, IOException {
		DebundleRequest debundleRequest = new DebundleRequest();
		String jsonData = buildJsonData(applePayData);
		if (applePayData.getIsTestEnv()) {
			debundleRequest.setOrbitalConnectionUsername(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_TEST_USER_NG));
			debundleRequest.setOrbitalConnectionPassword(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_TEST_PWD_NG));
		} else {
			debundleRequest.setOrbitalConnectionUsername(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_USER_NG));
			debundleRequest.setOrbitalConnectionPassword(orbitalProperties
					.getProperty(OrbitalConstants.ORBITAL_PWD_NG));
		}
		debundleRequest.setBin(orbitalProperties
				.getProperty(OrbitalConstants.ORBITAL_BIN));
		if (!applePayData.getIsOrbitalRetry()) {
			debundleRequest.setMerchantID(orbitalProperties
					.getProperty(OrbitalConstants.MERCHANT_ID_NG));
		} else {
			debundleRequest.setMerchantID(orbitalProperties
					.getProperty(OrbitalConstants.MERCHANT_ID_NG_2));
		}
		debundleRequest.setData(jsonData);
		debundleRequest
				.setLatitudeLongitude(OrbitalConstants.ORBITAL_DEFAULT_LATLONG);
		String XMLData = marshallOrbitalRequest(debundleRequest);
		XMLData = XMLData.replaceFirst(OrbitalConstants.DATA_TAG,
				OrbitalConstants.DATA_TAG_WITH_CDATA);
		XMLData = XMLData.replaceFirst(OrbitalConstants.DATA_TAG_CLOSE,
				OrbitalConstants.DATA_TAG_WITH_CDATA_CLOSE);
		System.out.println("Request XML: " + XMLData);
		return XMLData;
	}

	/**
	 * Method for Marshalling Debundle Request to XML Format
	 * 
	 * @param debundleRequest
	 * @return
	 */
	private static String marshallOrbitalRequest(DebundleRequest debundleRequest) {
		StringWriter writer = new StringWriter();
		;
		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(DebundleRequest.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(debundleRequest, writer);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * Method for Unmarshalling the XML Debundle response received from Orbital
	 * API.
	 * 
	 * @param xmlResponse
	 * @return
	 */
	public static DebundleResponse unMarshallOrbitalDebundleResponse(
			String xmlResponse) {
		DebundleResponse res = new DebundleResponse();
		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(DebundleResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlResponse);
			res = (DebundleResponse) unmarshaller.unmarshal(reader);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Method builds Json Data
	 * 
	 * @return
	 */
	private static String buildJsonData(ApplePayData applePayData) {
		JsonObject header = new JsonObject();
		if (applePayData.getaPayAppData() != null
				&& !applePayData.getaPayAppData().equalsIgnoreCase(
						OrbitalConstants.NULL)) {
			header.addProperty(OrbitalConstants.APPLICATION_DATA, applePayData
					.getaPayAppData().trim());
		}
		header.addProperty(OrbitalConstants.EPHEMERAL_PUBLIC_KEY, applePayData
				.getaPayEpKey().trim());
		header.addProperty(OrbitalConstants.PUBLIC_KEY_HASH, applePayData
				.getaPayPubHash().trim());
		header.addProperty(OrbitalConstants.TRANSACTION_ID, applePayData
				.getaPayTranId().trim());

		JsonObject jsonData = new JsonObject();
		jsonData.addProperty(OrbitalConstants.DATA, applePayData.getaPayData()
				.trim());
		jsonData.add(OrbitalConstants.HEADER, header);
		jsonData.addProperty(OrbitalConstants.SIGNATURE, applePayData
				.getaPaySign().trim());
		jsonData.addProperty(OrbitalConstants.VERSION,
				OrbitalConstants.VERSION_VALUE);
		return jsonData.toString();
	}

	/**
	 * Method to populate Apple Pay data in HTTPServletRequest to Java Bean
	 * 
	 * @param request
	 * @return
	 */
	public static ApplePayData populateDataForRequest(HttpServletRequest request) {
		ApplePayData applePayData = new ApplePayData();
		if (request.getHeader(OrbitalConstants.APPLE_PAY_DATA) != null) {
			applePayData.setaPayData((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_DATA));
		}
		System.out.println("Apple_Pay_Data: " + applePayData.getaPayData());
		if (request.getHeader(OrbitalConstants.MERCHANT_ID) != null) {
			applePayData.setMerchantId((String) request
					.getHeader(OrbitalConstants.MERCHANT_ID));
		}
		System.out.println("Merchant_id: " + applePayData.getMerchantId());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_SIGNATURE) != null) {
			applePayData.setaPaySign((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_SIGNATURE));
		}
		System.out.println("Signature: " + applePayData.getaPaySign());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_APP_DATA) != null) {
			applePayData.setaPayAppData((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_APP_DATA));
		}
		System.out.println("Apple Pay App data: "
				+ applePayData.getaPayAppData());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_EP_KEY) != null) {
			applePayData.setaPayEpKey((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_EP_KEY));
		}
		System.out.println("Apple Pay EP Key: " + applePayData.getaPayEpKey());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_PUBLIC_HASH) != null) {
			applePayData.setaPayPubHash((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_PUBLIC_HASH));
		}
		System.out.println("Apple Pay Pub Hash: "
				+ applePayData.getaPayPubHash());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_TRAN_ID) != null) {
			applePayData.setaPayTranId((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_TRAN_ID));
		}
		System.out
				.println("Apple Pay Tran Id: " + applePayData.getaPayTranId());
		if (request.getHeader(OrbitalConstants.IS_TEST_ENV) != null) {
			applePayData.setIsTestEnv(Boolean.valueOf((String) request
					.getHeader(OrbitalConstants.IS_TEST_ENV)));
		}
		System.out.println("Is Test Env: "
				+ applePayData.getIsTestEnv().toString());
		if (request.getHeader(OrbitalConstants.IS_ORBITAL_RETRY) != null) {
			applePayData.setIsOrbitalRetry(Boolean.valueOf((String) request
					.getHeader(OrbitalConstants.IS_ORBITAL_RETRY)));
		}
		System.out.println("Orbital Retry: "
				+ applePayData.getIsOrbitalRetry().toString());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_NETWORK) != null) {
			applePayData.setaPayPNW((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_NETWORK));
		}
		System.out.println("Apple Pay NW: " + applePayData.getaPayPNW());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_TYPE) != null) {
			applePayData.setaPayType((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_TYPE));
		}
		System.out.println("Apple Pay Type: " + applePayData.getaPayType());
		if (request.getHeader(OrbitalConstants.APPLE_PAY_VALUE) != null) {
			applePayData.setaPayValue((String) request
					.getHeader(OrbitalConstants.APPLE_PAY_VALUE));
		}
		System.out.println("Apple Pay Value: " + applePayData.getaPayValue());
		if (request.getHeader(OrbitalConstants.EFFORT_KEY) != null) {
			applePayData.setEffortKey((String) request
					.getHeader(OrbitalConstants.EFFORT_KEY));
		}
		if (applePayData.getEffortKey() != null
				&& applePayData.getEffortKey().trim().length() > 6) {
			applePayData.setEffortKey(applePayData.getEffortKey().substring(0,
					6));
		}
		System.out.println("Effort Key: " + applePayData.getEffortKey());
		if (request.getHeader(OrbitalConstants.EFFORT_KEY_OPTION) != null) {
			applePayData.setEffortKeyOption((String) request
					.getHeader(OrbitalConstants.EFFORT_KEY_OPTION));
		}
		System.out.println("Effort Key Option: "
				+ applePayData.getEffortKeyOption());
		if (request.getHeader(OrbitalConstants.DOLLAR_VALUE) != null) {
			applePayData.setDollarValue((String) request
					.getHeader(OrbitalConstants.DOLLAR_VALUE));
		}
		System.out.println("Dollar Value: " + applePayData.getDollarValue());
		if (request.getHeader(OrbitalConstants.CUSTOMER_NAME) != null) {
			applePayData.setCustName((String) request
					.getHeader(OrbitalConstants.CUSTOMER_NAME));
		}
		System.out.println("Customer Name: " + applePayData.getCustName());
		if (request.getHeader(OrbitalConstants.CUSTOMER_ADDRESS_1) != null) {
			applePayData.setCustAddr1((String) request
					.getHeader(OrbitalConstants.CUSTOMER_ADDRESS_1));
		}
		System.out.println("Cust Addr 1: " + applePayData.getCustAddr1());
		if (request.getHeader(OrbitalConstants.CUSTOMER_ADDRESS_2) != null) {
			applePayData.setCustAddr2((String) request
					.getHeader(OrbitalConstants.CUSTOMER_ADDRESS_2));
		}
		System.out.println("Cust Addr 2: " + applePayData.getCustAddr2());
		if (request.getHeader(OrbitalConstants.CUSTOMER_EMAIL) != null) {
			applePayData.setCustEmail((String) request
					.getHeader(OrbitalConstants.CUSTOMER_EMAIL));
		}
		System.out.println("Customer Email: " + applePayData.getCustEmail());
		if (request.getHeader(OrbitalConstants.POSTAL_CODE) != null) {
			applePayData.setPostalCode((String) request
					.getHeader(OrbitalConstants.POSTAL_CODE));
		}
		System.out.println("Postal Code: " + applePayData.getPostalCode());
		if (request.getHeader(OrbitalConstants.CITY_STREET) != null) {
			applePayData.setCityStreet((String) request
					.getHeader(OrbitalConstants.CITY_STREET));
		}
		System.out.println("City Street: " + applePayData.getCityStreet());
		if (request.getHeader(OrbitalConstants.KEYLINE) != null) {
			applePayData.setKeyline((String) request
					.getHeader(OrbitalConstants.KEYLINE));
		}
		System.out.println("Keyline: " + applePayData.getKeyline());
		if (request.getHeader(OrbitalConstants.EFFORT_TERM) != null) {
			applePayData.setEffortTerm((String) request
					.getHeader(OrbitalConstants.EFFORT_TERM));
		}
		System.out.println("Effort term: " + applePayData.getEffortTerm());
		if (request.getHeader(OrbitalConstants.EFFORT_VALUE) != null) {
			applePayData.setEffortValue((String) request
					.getHeader(OrbitalConstants.EFFORT_VALUE));
		}
		System.out.println("Effort Value: " + applePayData.getEffortValue());
		if (request.getHeader(OrbitalConstants.MAG_CODE) != null) {
			applePayData.setMagCode((String) request
					.getHeader(OrbitalConstants.MAG_CODE));
		}
		System.out.println("Mag Code: " + applePayData.getMagCode());
		if (request.getHeader(OrbitalConstants.SEG_ID) != null) {
			applePayData.setSegId((String) request
					.getHeader(OrbitalConstants.SEG_ID));
		}
		System.out.println("Seg Id: " + applePayData.getSegId());
		if (request.getHeader(OrbitalConstants.PDAT) != null) {
			applePayData.setPdat((String) request
					.getHeader(OrbitalConstants.PDAT));
		}
		System.out.println("PDAT: " + applePayData.getPdat());
		if (request.getHeader(OrbitalConstants.UNIQ) != null) {
			applePayData.setUniq((String) request
					.getHeader(OrbitalConstants.UNIQ));
		}
		System.out.println("Uniq: " + applePayData.getUniq());
		if (request.getHeader(OrbitalConstants.IS_FROM_LISTENER) != null) {
			applePayData.setIsFromListener(Boolean.valueOf((String) request
					.getHeader(OrbitalConstants.IS_FROM_LISTENER)));
		}
		System.out.println("Is From Listener: "
				+ applePayData.getIsFromListener().toString());

		if (request.getHeader(OrbitalConstants.ACCOUNT_NUMBER) != null) {
			applePayData.setAccountNumber((String) request
					.getHeader(OrbitalConstants.ACCOUNT_NUMBER));
		}
		System.out
				.println("Account Number: " + applePayData.getAccountNumber());
		if (request.getHeader(OrbitalConstants.ACTIVITY) != null) {
			applePayData.setActivity((String) request
					.getHeader(OrbitalConstants.ACTIVITY));
		}
		System.out.println("Activity: " + applePayData.getActivity());
		if (request.getHeader(OrbitalConstants.TAX) != null) {
			applePayData.setTax((String) request
					.getHeader(OrbitalConstants.TAX));
		}
		System.out.println("Tax: " + applePayData.getTax());
		if (request.getHeader(OrbitalConstants.TOTAL_VALUE) != null) {
			applePayData.setTotalValue((String) request
					.getHeader(OrbitalConstants.TOTAL_VALUE));
		}
		System.out.println("Total Value: " + applePayData.getTotalValue());

		return applePayData;
	}

	/**
	 * Method to read Response body from Orbital
	 * 
	 * @param httpConn
	 * @return
	 * @throws IOException
	 */
	public static String readResponseData(HttpURLConnection httpConn)
			throws IOException {
		InputStreamReader inputStream = null;
		BufferedReader bufferedReader = null;
		String xmlResponse = null;
		try {
			inputStream = new InputStreamReader(httpConn.getInputStream(),
					OrbitalConstants.UTF_8_ENCODING);
			bufferedReader = new BufferedReader(inputStream);
			xmlResponse = bufferedReader.readLine();
			System.out.println("API Response: " + xmlResponse);
		} catch (Exception e) {
			System.out
					.println("Exception while reading Response Data OrbitalHelper.readResponseData "
							+ e.getMessage());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return xmlResponse;
	}

	/**
	 * Method returns the current date and time in "yyyy-MM-dd HH:mm:ss" format
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(
				OrbitalConstants.DATE_TIME_FORMAT);
		return sdf.format(cal.getTime());
	}
}
