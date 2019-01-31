package com.timeinc.tcs.epayG.handler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.timeinc.tcs.epayG.client.OrbitalClient;
import com.timeinc.tcs.epayG.constants.OrbitalConstants;
import com.timeinc.tcs.epayG.dto.ApplePayData;
import com.timeinc.tcs.epayG.helper.OrbitalHelper;
import com.timeinc.tcs.epayG.messages.DebundleResponse;
import com.timeinc.tcs.epayG.model.DBAccess;
import com.timeinc.tcs.epayG.transaction.ApplePayTransaction;

public class OrbitalHandler {

	/**
	 * Method to handle the API call to Orbital
	 * 
	 * @param data
	 * @param merchantId
	 * @return
	 * @throws NamingException
	 * @throws IOException
	 */
	public static DebundleResponse handleOrbitalCall(ApplePayData applePayData) {
		DebundleResponse response = null;
		String merchantUrl = null;
		String XMLData=null;
		
		try {
			Properties orbitalProperties = OrbitalHelper.getOrbitalProperties();
			if (applePayData.getIsTestEnv()) {
				merchantUrl = orbitalProperties
						.getProperty(OrbitalConstants.ORBITAL_TEST_URL);
			} else {
				merchantUrl = orbitalProperties
						.getProperty(OrbitalConstants.ORBITAL_URL);
			}
			
			if(applePayData.getMagCode()!=null && !applePayData.getMagCode().equalsIgnoreCase("") && applePayData.getMagCode().equalsIgnoreCase("NG")){
				
				System.out.println("++++++++++++++++++++Entereing into NGS and wesqa++++++++++++");
			
				XMLData = OrbitalHelper.ngBuildDebundleRequestXML(
						applePayData, orbitalProperties);
			}else {
				System.out.println("++++++++++++++++++Entering into else++++++++");
				XMLData = OrbitalHelper.buildDebundleRequestXML(
						applePayData, orbitalProperties);
				
			}
			
		System.out.println("+++++++++++++++xml+++++++++++++"+XMLData);
			HttpURLConnection httpConn = OrbitalClient.makeOrbitalCall(XMLData,
					merchantUrl);

			System.out
					.println("Connection Code: " + httpConn.getResponseCode());

			if (httpConn.getResponseCode() == OrbitalConstants.HTTP_STATUS_OK) {
				String xmlResponse = OrbitalHelper.readResponseData(httpConn);
				response = OrbitalHelper
						.unMarshallOrbitalDebundleResponse(xmlResponse);
			} else if (httpConn.getResponseCode() == OrbitalConstants.HTTP_STATUS_PRECONDITION_FAILED) {
				response = new DebundleResponse();
				response.setProcStatus(OrbitalConstants.PRECONDITION_FAILED);
			}
		} catch (Exception e) {
			System.out
					.println("Exception Caught at OrbitalHandler.handleOrbitalCall "
							+ e.getMessage());
			return response;
		}
		return response;
	}

	/**
	 * Method to handle insert database call to insert apple pay, effort key and
	 * other information to Orbital Table
	 * 
	 * @param applePayData
	 */
	public static void handleInsertDatabaseCall(ApplePayData applePayData) {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup(OrbitalConstants.EPAYGOWNER);
			if (dbOwner == null) {
				System.out
						.println("EPayG epayowner for Insert Query is missing");
			} else {
				System.out.println("EPayG epayOwner for Insert Query: "
						+ dbOwner);
				DBAccess.insertToOrbitalTable(dbOwner, ctx, applePayData);
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at Orbitalhandler.handleInsertDatabaseCall "
							+ e.getMessage());
		}
	}

	/**
	 * Method to handle database call to update Keyline, contractId and Orbital
	 * Success flag to Orbital Table
	 * 
	 * @param applePayData
	 */
	public static void handleUpdateDatabaseCall(ApplePayData applePayData) {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup(OrbitalConstants.EPAYGOWNER);
			if (dbOwner == null) {
				System.out
						.println("EPayG epayowner for Update Query is missing");
			} else {
				System.out.println("EPayG epayOwner for Update Query: "
						+ dbOwner);
				DBAccess.updateOrbitalTable(dbOwner, ctx, applePayData);
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at Orbitalhandler.handleUpdateDatabaseCall "
							+ e.getMessage());
		}
	}

	/**
	 * Method to handle database call to get apple pay details from Orbital
	 * table.
	 * 
	 * @param applePayData
	 * @return
	 */
	public static ApplePayData handleGetDatabaseCall(ApplePayData applePayData) {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup(OrbitalConstants.EPAYGOWNER);
			if (dbOwner == null) {
				System.out
						.println("EPayG epayowner for Select Query is missing");
			} else {
				System.out.println("EPayG epayOwner for Select Query: "
						+ dbOwner);
				applePayData = DBAccess.getApplePayData(dbOwner, ctx,
						applePayData);
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at Orbitalhandler.handleGetDatabaseCall "
							+ e.getMessage());
		}
		return applePayData;
	}

	/**
	 * Method to handle Orbital Retry Call logic
	 * 
	 * @param applePayData
	 */
	public static void handleOrbitalRetryCall(final ApplePayData applePayData) {
		System.out.println("Orbital Retry at: "
				+ OrbitalHelper.getCurrentDateTime());
		DebundleResponse retryDebundleResponse = OrbitalHandler
				.handleOrbitalCall(applePayData);
		if (retryDebundleResponse != null
				&& retryDebundleResponse.getProcStatus().equalsIgnoreCase(
						OrbitalConstants.PROCSTATUS_CODE_SUCCESS)) {
			applePayData.setOrbitalSuccess(OrbitalConstants.YES);
			applePayData.setCryptogram(retryDebundleResponse.getTokenData()
					.getPaymentData().getOnlinePaymentCryptogram());
			applePayData.setDpan(retryDebundleResponse.getTokenData()
					.getApplicationPrimaryAccountNumber());
			applePayData.setExpiryDate(retryDebundleResponse.getTokenData()
					.getApplicationExpirationDate());
			if (applePayData.getCryptogram() != null) {
				applePayData.setIsCryptogramPresent(OrbitalConstants.YES);
				System.out.println("Calling Transaction Code");
				new ApplePayTransaction().processTCC(applePayData);
			}
		}
		OrbitalHandler.handleUpdateDatabaseCall(applePayData);
	}
}
