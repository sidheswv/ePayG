/**
 * 
 */
package com.timeinc.tcs.epayG.client;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.timeinc.tcs.epayG.constants.PayPalECConstants;
import com.timeinc.tcs.epayG.exception.ECConnectionException;
//import com.timeinc.tcs.util.Log;
//import com.timeinc.tcs.util.Log;
import com.timeinc.tcs.epayG.helper.PayPalECHelper;

/**
 * @author bilgin
 *
 */
public class PayPalECClient {

	/**
	 * A generic client to make calls to Paypal server for Express Checkout It
	 * takes List of NameValuePairs (Apache HTTP package) and returns the
	 * HttpResponse object
	 * 
	 * @param paypalECRequest
	 *            : the entire request to make the API call
	 * @return : the HttpReponse object
	 * @throws Exception 
	 */

	public static String[] makeECCall(List<NameValuePair> paypalECRequest,
			boolean isSandboxHost) throws ECConnectionException {
		CloseableHttpResponse httpClientresponse = null;
		HttpPost getRequest;
		CloseableHttpClient httpClient = null;
		String[] jsonArr = null;
		String[] jsonArrEntities = null;
		boolean isRCCode = false;
		
		SSLConnectionSocketFactory f = null;
		try {

			// Code to be Implemented after Paypal Live goes for TLSv1.2

			 /** 
			 * httpClient = HttpClients.custom() .setSSLSocketFactory(f)
			 * .build();
			 * 
			 * httpClient.setConnectTimeout(TIMEOUT_VALUE);
			 * 
			 * //final RequestConfig params =
			 * RequestConfig.custom().setConnectTimeout
			 * (3000).setSocketTimeout(3000).build();
			 * httpPost.setConfig(params);
			 **/
			// create HTTP Client
			/** HttpClient httpClient = HttpClientBuilder.create().build(); **/
			long startTime2 = System.currentTimeMillis();

			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(PayPalECConstants.CONNECTION_TIMEOUT)
					.build();
			// RequestConfig requestConfig =
			// RequestConfig.custom().setConnectTimeout(7000).build();

			// RequestConfig requestConfig =
			// RequestConfig.custom().setConnectTimeout(PayPalECConstants.CONNECTION_TIMEOUT).setSocketTimeout(PayPalECConstants.SOCKET_TIMEOUT).build();

			//SANDBOX
			if(isSandboxHost){
				SSLContext sslContext = SSLContexts.custom()
					    .useTLS()
					    .build();
				 
				  f = new SSLConnectionSocketFactory(
				  sslContext, new String[]{"TLSv1.2"}, null,
				  SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				  
			      httpClient = HttpClientBuilder.create()
					.setDefaultRequestConfig(requestConfig).setSSLSocketFactory(f).build();
			}
			//PRODUCTION 
			else{
				 httpClient = HttpClientBuilder.create()
							.setDefaultRequestConfig(requestConfig).build();
				
			}

			System.out
					.println("Paypal EC setexpresscheckout RequestConfig requestConfig "
							+ (System.currentTimeMillis() - startTime2)
							+ " milliseconds ");

			/**
			 * HttpClient httpClient = new HttpClient(); DefaultHttpClient
			 * httpClient = new DefaultHttpClient();
			 **/

			// Create new getRequest with below mentioned URL
			// TODO : The endpoint needs to be dynamic - Sandbox or Live

			if (!isSandboxHost) {
				getRequest = new HttpPost("https://api-3t.paypal.com/nvp");
			} else {
				getRequest = new HttpPost(
						"https://api-3t.sandbox.paypal.com/nvp");
				/**
				 * getRequest = new
				 * HttpPost("https://old-api-3t.sandbox.paypal.com/nvp");
				 **/
			}
			// Add additional header to getRequest which accepts application/xml
			// data
			getRequest.addHeader("accept", "application/json");

			// Set the request parameters which are URL encoded
			getRequest.setEntity(new UrlEncodedFormEntity(paypalECRequest));

			// log the paypal request
			System.out.println("Calling paypal for setexpresscheckout POJO"
					+ paypalECRequest);
			System.out.println("Calling paypal for setexpresscheckout Method"
					+ getRequest.getMethod());

			long startTime = System.currentTimeMillis();

			// Execute your request and catch response
			httpClientresponse = httpClient.execute(getRequest);

			System.out.println("Paypal EC setexpresscheckout http call time "
					+ getRequest + " "
					+ (System.currentTimeMillis() - startTime)
					+ " milliseconds ");

			System.out.println("Response from PayPal Call: "
					+ httpClientresponse);

			// Handle Respopnse and Build Response String Array
			if (httpClientresponse != null
					&& httpClientresponse.getStatusLine().getStatusCode() == 200) {

				
				jsonArrEntities = EntityUtils.toString(httpClientresponse.getEntity())
						.split("&");
				String paypalrccode = httpClientresponse.getFirstHeader(PayPalECConstants.PAYPAL_API_RC).getValue();
				//if(null != paypalrccode && "" != paypalrccode){
				//if(PayPalECConstants.PAYPAL_API_RC.equalsIgnoreCase(paypalrccode.trim())){
				if(paypalrccode.trim().length() > 0){
					isRCCode = true;
				}
				System.out.println("EC Client : Response Header PayPal API RC" + paypalrccode);
				//List<String> responseEntityArray = new ArrayList<String>();
				//jsonArr = new String[jsonArrEntities.length + 1];
				/*for(String entityPair : jsonArrEntities){
					responseEntityArray.add(entityPair);
				}*/
				
				if(isRCCode){
					jsonArr = new String[jsonArrEntities.length + 1];
					
				      for(int i=0;i<jsonArrEntities.length;i++){
					   jsonArr[i] = jsonArrEntities[i];
        			  }
				//Adding the reposne code form response header
				
				//responseEntityArray.add(PayPalECConstants.PAYPAL_API_RC+"="+paypalrccode);
					jsonArr[jsonArrEntities.length] = PayPalECConstants.PAYPAL_API_RC+"="+paypalrccode;
					System.out.println("EC Client : PayPal API RC added" + jsonArr[jsonArrEntities.length]);
				}
				// Building back the array
				//jsonArr = responseEntityArray.toArray(new String[responseEntityArray.size()]);
				//System.out.println("EC Client : Response Array along with ARI RC header value" + jsonArr.length);

			} else {

				System.out.println("PayPalECClientCall , unsuccessfull response");
			}

			// } catch (ClientProtocolException e) {}
		} catch (HttpHostConnectException he) {
			System.out.println("Call to paypal - setexpresscheckout failed"
					+ he.getMessage());
			throw new ECConnectionException("Host Connection Exception Occured");

		} catch (Exception e) {
			/**
			 * Log.setError("ClientProtocolException inside " +
			 * PayPalECClient.class + " --> makeECCall() method. The message " +
			 * e.getMessage());
			 **/
			 
			e.printStackTrace();
			throw new ECConnectionException("General Exception Occured");
		} finally {
			try {
				httpClient.close();
			} catch (Exception eeee) {
				// System.out.println("Call to paypal - setexpresscheckout failed, hit finally, closing connection");

			}
		}

		// return httpClientresponse;
		if(isRCCode){
			System.out.println("EC Client : Response Array along with ARI RC header value" + jsonArr.length);
			return jsonArr;
		}
		else{
			System.out.println("EC Client : Response Array " + jsonArrEntities.length);
			return jsonArrEntities;
			
		}
	}
	
	public static String makeECIPNCall(List<NameValuePair> paypalECRequest,
			boolean isSandboxHost) throws IOException {
		CloseableHttpResponse httpClientresponse = null;
		HttpPost getRequest;
		CloseableHttpClient httpClient = null;
		String[] jsonArr = null;
		String verifyResponse = null;
		SSLConnectionSocketFactory f = null;
		try {


			long startTime2 = System.currentTimeMillis();

			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(PayPalECConstants.CONNECTION_TIMEOUT)
					.build();

			//SANDBOX
			if(isSandboxHost){
				SSLContext sslContext = SSLContexts.custom()
					    .useTLS()
					    .build();
				 
				  f = new SSLConnectionSocketFactory(
				  sslContext, new String[]{"TLSv1.2"}, null,
				  SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				  
			      httpClient = HttpClientBuilder.create()
					.setDefaultRequestConfig(requestConfig).setSSLSocketFactory(f).build();
			}
			//PRODUCTION 
			else{
				 httpClient = HttpClientBuilder.create()
							.setDefaultRequestConfig(requestConfig).build();
				
			}

			System.out
					.println("Paypal EC setexpresscheckout RequestConfig requestConfig "
							+ (System.currentTimeMillis() - startTime2)
							+ " milliseconds ");

			if (isSandboxHost) {
				getRequest = new HttpPost("https://ipnpb.sandbox.paypal.com/cgi-bin/webscr");
			} else {
				getRequest = new HttpPost(
						"https://ipnpb.paypal.com/cgi-bin/webscr");
	
			}

			getRequest.setEntity(new UrlEncodedFormEntity(paypalECRequest));
			
			// log the paypal request
			//System.out.println("Calling paypal for IPN verification");
			System.out.println("Calling paypal for IPN verification POJO"
					+ paypalECRequest);
			System.out.println("Calling paypal IPN verification"
					+ getRequest.getMethod());

			long startTime = System.currentTimeMillis();

			
			httpClientresponse = httpClient.execute(getRequest);

			System.out.println("Paypal EC setexpresscheckout http call time "
					+ getRequest + " "
					+ (System.currentTimeMillis() - startTime)
					+ " milliseconds ");

			System.out.println("Response from PayPal Call: "
					+ httpClientresponse);

			if (httpClientresponse != null
					&& httpClientresponse.getStatusLine().getStatusCode() == 200) {

				System.out.println("PayPalECIPNVerification Call , Successfull response");

			} else {

				System.out.println("PayPalECIPNVerification Call , unsuccessfull response");
			}
			verifyResponse = (EntityUtils.toString(httpClientresponse.getEntity())).trim();


		} catch (HttpHostConnectException he) {
			System.out.println("Call to PayPalECIPNVerification failed"
					+ he.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (Exception eeee) {
				
			}
		}

		return verifyResponse;
	}
	
	
	
}

