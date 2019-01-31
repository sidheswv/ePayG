package com.timeinc.tcs.epayG.services;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.BaseEncoding;
import com.timeinc.tcs.epayG.constants.AndroidPayConstants;
import com.timeinc.tcs.epayG.dto.AndroidPayData;
import com.timeinc.tcs.epayG.handler.AndroidPayHandler;
import com.timeinc.tcs.epayG.helper.AndroidPayHelper;
import com.timeinc.tcs.epayG.messages.DecryptedResponse;

/**
 * Servlet implementation class UnbundleService
 */
public class UnbundleService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UnbundleService() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In UnbundleService.doPost");
		AndroidPayData androidPayData = new AndroidPayData();
		Properties androidPayProperties;
		try {
			androidPayData = AndroidPayHelper.populateDataForRequest(request);
			androidPayProperties = AndroidPayHelper.getAndroidPayProperties();
			if (!androidPayData.getIsFromBatch()) {
				if (!androidPayData.getIsAndroidPayRetry()) {
					System.out.println("In First Call For Android Pay");
					AndroidPayHandler androidPayhandler = AndroidPayHandler
							.createFromPkcs8EncodedPrivateKey(BaseEncoding
									.base64()
									.decode(androidPayProperties
											.getProperty(AndroidPayConstants.MERCHANT_PRIVATE_KEY_PKCS8_BASE64)));
					String decryptedData = androidPayhandler
							.verifyAndDecrypt(androidPayData);
					System.out.println("DECRYPTED PAYLOAD: " + decryptedData);
					DecryptedResponse decryptResponse = AndroidPayHelper
							.convertResponseToPojo(decryptedData);
					System.out.println("DPAN: " + decryptResponse.getDpan());
					System.out.println("Cryptogram: "
							+ decryptResponse.getCryptogram());
					response.setContentType(AndroidPayConstants.TEXT_PLAIN);
					response.addHeader(AndroidPayConstants.PROCSTATUS,
							AndroidPayConstants.PROCSTATUS_CODE_SUCCESS);
					response.addHeader(AndroidPayConstants.DPAN,
							decryptResponse.getDpan());
					response.addHeader(AndroidPayConstants.CRYPTOGRAM,
							decryptResponse.getCryptogram());
					response.addHeader(AndroidPayConstants.EXPIRY_MONTH,
							decryptResponse.getExpirationMonth());
					response.addHeader(AndroidPayConstants.EXPIRY_YEAR,
							decryptResponse.getExpirationYear());
				} else {
					System.out.println("In Retry Call For Android Pay");
					if(androidPayData.getIsFromListener()){
						androidPayData.setIsMatchToListener(AndroidPayConstants.YES);
					}else {
						androidPayData.setIsMatchToListener(AndroidPayConstants.NO);
					}
					AndroidPayHandler.handleUpdateDatabaseCall(androidPayData);
				}
			} else {
				System.out.println("In Batch Call For Android Pay");
				List<AndroidPayData> androidPayDataList = AndroidPayHandler
						.handleGetDatabaseCall();
				androidPayProperties = AndroidPayHelper
						.getAndroidPayProperties();
				AndroidPayHandler.handleAndroidPayRetryCall(androidPayDataList,
						androidPayProperties);
			}
		} catch (Exception e) {
			System.out.println("Exception caught at UnbundleService.doPost "
					+ e.getMessage());
			AndroidPayHandler.handleInsertDatabaseCall(androidPayData);
			response.addHeader(AndroidPayConstants.PROCSTATUS,
					AndroidPayConstants.PROCSTATUS_CODE_FAILURE);
		}
	}

}
