package com.timeinc.tcs.epayG.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import com.timeinc.tcs.epayG.constants.OrbitalConstants;

public class OrbitalClient {

	/**
	 * Method to make the HTTP Post call to Orbital API.
	 * 
	 * @param data
	 * @param merchantId
	 * @param merchProperties
	 * @return
	 */
	public static HttpURLConnection makeOrbitalCall(String XMLData,
			String merchantUrl) {
		HttpURLConnection httpConn = null;
		URL url;
		try {
			url = new URL(merchantUrl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(OrbitalConstants.CONNECTION_TIMEOUT);
			httpConn.setReadTimeout(OrbitalConstants.READ_TIMEOUT);
			httpConn.setRequestMethod(OrbitalConstants.POST);
			httpConn.setRequestProperty(OrbitalConstants.CONTENT_TYPE,
					OrbitalConstants.APPLICATION_XML);
			httpConn.setRequestProperty(OrbitalConstants.CONTENT_LENGTH,
					Integer.toString(XMLData.length()));
			httpConn.setRequestProperty(
					OrbitalConstants.CONTENT_TRANSFER_ENCODING,
					OrbitalConstants.TEXT);
			httpConn.setRequestProperty(OrbitalConstants.REQUEST_NUMBER,
					OrbitalConstants.ONE);
			httpConn.setRequestProperty(OrbitalConstants.DOCUMENT_TYPE,
					OrbitalConstants.REQUEST);
			httpConn.setRequestProperty(OrbitalConstants.ACCEPT,
					OrbitalConstants.APPLICATION_XML);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					(httpConn.getOutputStream())));
			writer.write(XMLData, 0, XMLData.length());
			writer.flush();
			writer.close();
		} catch (SocketTimeoutException e) {
			System.out
					.println("Time Out Exception at OrbitalClient.makeOrbitalCall "
							+ e.getMessage());
		} catch (MalformedURLException e) {
			System.out
					.println("MalformedURLException Exception at OrbitalClient.makeOrbitalCall "
							+ e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception at OrbitalClient.makeOrbitalCall "
					+ e.getMessage());
		}
		return httpConn;

	}

}
