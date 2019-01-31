package com.timeinc.tcs.epayG.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timeinc.tcs.epayG.constants.OrbitalConstants;
import com.timeinc.tcs.epayG.dto.ApplePayData;
import com.timeinc.tcs.epayG.handler.OrbitalHandler;
import com.timeinc.tcs.epayG.helper.OrbitalHelper;
import com.timeinc.tcs.epayG.messages.DebundleResponse;

/**
 * Servlet implementation class ApplePayController
 */
public class ApplePayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplePayController() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In ApplePayController.doPost");
		DebundleResponse debundleResponse = new DebundleResponse();
		ApplePayData applePayData = new ApplePayData();
		try {
			applePayData = OrbitalHelper.populateDataForRequest(request);
			String host=request.getServerName();
			System.out.println("+++++++++++"+host);
			if(host!=null){
			applePayData.setHostName(host);
			}
			if (!applePayData.getIsOrbitalRetry()) {
				System.out.println("+++++++inside check+++++++++++++++");
				debundleResponse = OrbitalHandler
						.handleOrbitalCall(applePayData);

				if (debundleResponse != null) {
					if (debundleResponse.getProcStatus().equalsIgnoreCase(
							OrbitalConstants.PROCSTATUS_CODE_SUCCESS)) {
						response.setContentType(OrbitalConstants.TEXT_PLAIN);
						response.addHeader(OrbitalConstants.PROCSTATUS,
								debundleResponse.getProcStatus());
						response.addHeader(OrbitalConstants.CRYPTOGRAM,
								debundleResponse.getTokenData()
										.getPaymentData()
										.getOnlinePaymentCryptogram());
						response.addHeader(OrbitalConstants.DPAN,
								debundleResponse.getTokenData()
										.getApplicationPrimaryAccountNumber());
						response.addHeader(OrbitalConstants.EXPIRY_DATE,
								debundleResponse.getTokenData()
										.getApplicationExpirationDate());
					} else {
						OrbitalHandler.handleInsertDatabaseCall(applePayData);
						response.setContentType(OrbitalConstants.TEXT_PLAIN);
						response.addHeader(OrbitalConstants.PROCSTATUS,
								debundleResponse.getProcStatus());
					}
				} else {
					OrbitalHandler.handleInsertDatabaseCall(applePayData);
					response.setContentType(OrbitalConstants.TEXT_PLAIN);
					response.addHeader(OrbitalConstants.PROCSTATUS,
							OrbitalConstants.CUSTOM_ERROR_CODE);
				}
			} else {
				ApplePayData aPayData = applePayData;
				applePayData = OrbitalHandler
						.handleGetDatabaseCall(applePayData);
				if (applePayData != null) {
					if (applePayData.getIsFromListener()) {
						applePayData.setIsMatchToListener(OrbitalConstants.YES);
					}
					OrbitalHandler.handleOrbitalRetryCall(applePayData);
				} else {
					if (aPayData.getIsFromListener()) {
						aPayData.setIsMatchToListener(OrbitalConstants.NO);
						OrbitalHandler.handleInsertDatabaseCall(aPayData);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Exception caught at ApplePayController.doPost "
					+ e.getMessage());
			OrbitalHandler.handleInsertDatabaseCall(applePayData);
			response.setContentType(OrbitalConstants.TEXT_PLAIN);
			response.addHeader(OrbitalConstants.PROCSTATUS,
					OrbitalConstants.CUSTOM_ERROR_CODE);
		}
	}
}
