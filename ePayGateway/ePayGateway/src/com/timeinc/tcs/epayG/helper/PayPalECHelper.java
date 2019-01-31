
package com.timeinc.tcs.epayG.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.timeinc.tcs.epay2.TokenMessage;
import com.timeinc.tcs.epayG.beans.ECDirectBean;
import com.timeinc.tcs.epayG.beans.ECDirectOrphanBean;
import com.timeinc.tcs.epayG.constants.OrbitalConstants;
import com.timeinc.tcs.epayG.constants.PayPalECConstants;
import com.timeinc.tcs.epayG.dto.ECDirectData;
import com.timeinc.tcs.epayG.entity.EpayRequestObject;
import com.timeinc.tcs.epayG.exception.ECConnectionException;
import com.timeinc.tcs.epayG.exception.ServiceException;
import com.timeinc.tcs.epayG.handler.PayPalECHandler;
import com.timeinc.tcs.epayG.utils.InvokeCrdVService;
//import com.timeinc.tcs.services.Connection;
import com.timeinc.tcs.jms.JMSQCommand;


/**
 * 
 * Added properties file for merchant details - Lakshmi N poduri - 08/24/15
 * 
 * 
 * The PayPalECHelper will help the PayPalECHandler in creating request and
 * unwrapping response for all the calls to PayPal Express Checkout
 * 
 * @author bilgin
 *
 */
public class PayPalECHelper {

	/**
	 * Populates a new list of Merchant information * USER : User name of the
	 * merchant (the email) PWD : Password SIGNATURE : To authorize and
	 * authenticate the merchant
	 * 
	 * @return : a new list of Merchant information
	 * 
	 */
	private String userId;
	private String pwd;
	private String sign;
	private String payPalEmail;
	private String merchantProperties;
	private String returnUrl;
	private String host;
	private String remotehost;
	private String scheme;
	private String notify_url;
	boolean checkSnBox;
	
	private static JMSQCommand queueCommand = null;	
	private static JMSQCommand queueCommand1 = null;
	
	
	private List<NameValuePair> populateMerchantInfo(HttpServletRequest request, String magcode,
			boolean isSandBox,String payPalMail, int rtrnUrlflag) throws IOException, NamingException {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		scheme = request.getScheme();
		host = request.getHeader("host");
		remotehost = request.getRemoteHost();
		System.out.println("Scheme:" + scheme);
		System.out.println("Host:" + host);
		String originalHost = host;
		if(host!=null && (host.equalsIgnoreCase("wesqa.customersvc.com")|| 
				          host.equalsIgnoreCase("wesqaprod.customersvc.com")|| 
				          host.equalsIgnoreCase("testsecure.customersvc.com")||
				          host.equalsIgnoreCase("httpqc1.tcs.timeinc.com")
				          )){
			//do nothing
			isSandBox = true;
			checkSnBox = true;
			
		}else{
			isSandBox = false;
			checkSnBox =false;
			//host = "secure.customersvc.com";
		}

		// Get details from Properties file
		Properties merchProperties = new Properties();
		InputStream iStream = null;

		try {
			InitialContext iCtx = new InitialContext();
			merchantProperties = (String) iCtx.lookup("merchantProperties");

			if (merchantProperties == null || merchantProperties == "") {
				System.out.println("Merchant Properties lookup failed");
				throw new FileNotFoundException();
			}

			iStream = new FileInputStream(merchantProperties);
			merchProperties.load(iStream);
			
			if (isSandBox) {
				userId = merchProperties.getProperty("USER_SNDBX");
				pwd = merchProperties.getProperty("PWD_SNDBX");
				sign = merchProperties.getProperty("SIGNATURE_SNDBX");
				payPalEmail = merchProperties.getProperty("SUBJECT_SNDBX");
				if(rtrnUrlflag <= 1){
					//This check is set up for diverting wesqa to httpqc1 at time of zcloud for WK magazine
					if("WK".equalsIgnoreCase(magcode)){
						
						returnUrl = scheme + "://wesqa.customersvc.com" + merchProperties.getProperty("RETURNURL_SNDBX_" + magcode);
						
					}
					else{
						
				    returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_SNDBX_" + magcode);
					}
				}
				else{
				    returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_SNDBX_" + magcode + "_" + rtrnUrlflag);
				}
			} else {

				userId = merchProperties.getProperty("USER_" + magcode);
				pwd = merchProperties.getProperty("PWD_" + magcode);
				sign = merchProperties.getProperty("SIGNATURE_" + magcode);
				payPalEmail = payPalMail;
				//payPalEmail = merchProperties.getProperty("SUBJECT_" + magcode);
				if(rtrnUrlflag <= 1){
					
					if(null != magcode && "" != magcode && "WK".equalsIgnoreCase(magcode)){
/*						if(null != remotehost && "" != remotehost){							
							System.out.println("Remote Host for WK magazine is :" + remotehost);
							returnUrl = scheme + "://" + remotehost + merchProperties.getProperty("RETURNURL_" + magcode);
						}
						else{
							returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_" + magcode);
						}*/
						
					}
					else{
					returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_" + magcode);
					System.out.println("returnUrl is "+returnUrl);
					}
				}
				else{
				    //returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_" + magcode + "_" + rtrnUrlflag);
					host = originalHost;
					System.out.println("originalHost is "+host);
					returnUrl = scheme + "://" + host + "/wes/servlet/Show?WESPAGE=pm/makeCB.jsp&MSRSMAG="+magcode+"&PAYPAL_EMAIL="+payPalMail;
					System.out.println("returnUrl is "+returnUrl);
				}			
			}
		} catch (IOException ie) {
			System.out.println("Error at Merchant properties file" + ie.getMessage());
			ie.printStackTrace();
			throw ie;
            	
		} catch (NamingException ne) {
			System.out.println("Naming Error at Finding Merchant properties file" + ne.getMessage());
			ne.printStackTrace();
			throw ne;
		} finally {
			if (iStream != null) {
				try {
					iStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		nameValuePairs.add(new BasicNameValuePair("USER", userId));
		nameValuePairs.add(new BasicNameValuePair("PWD", pwd));
		nameValuePairs.add(new BasicNameValuePair("SIGNATURE", sign));
		
		if(!isSandBox){
				nameValuePairs.add(new BasicNameValuePair("SUBJECT", payPalEmail));
		}

		return nameValuePairs;
	}
	
	
	
	/**
	 * 
	 * @return : The request for SetExpressCheckout API call
	 * @throws NamingException 
	 * @throws IOException 
	 */
	public List<NameValuePair> createSetECRequest(HttpServletRequest request, String cancelURL,
			String magcode, boolean isSandBox,String payPalMail, int rtrnUrlflag) throws IOException, NamingException {
		host = request.getHeader("host");
		scheme = request.getScheme();
		System.out.println("Host:" + host);
		String originalHost = host;
		if(host!=null && (host.equalsIgnoreCase("wesqa.customersvc.com")|| 
				          host.equalsIgnoreCase("wesqaprod.customersvc.com")|| 
				          host.equalsIgnoreCase("testsecure.customersvc.com")||
				          host.equalsIgnoreCase("httpqc1.tcs.timeinc.com"))){
			//do nothing
			isSandBox = true;
			checkSnBox = true;
		}else{
			isSandBox = false;
			checkSnBox =false;
			//host = "secure.customersvc.com";
		}

		List<NameValuePair> setECReq = populateMerchantInfo(request,magcode,isSandBox,payPalMail,rtrnUrlflag);

		if("WK".equalsIgnoreCase(magcode)){
			setECReq.add(new BasicNameValuePair("VERSION", "124"));
		}
		else{
			setECReq.add(new BasicNameValuePair("VERSION", "78"));
		}
		setECReq.add(new BasicNameValuePair("METHOD",
				PayPalECConstants.SETEXPRESSCHEKOUT));
		setECReq.add(new BasicNameValuePair("PAYMENTREQUEST_0_PAYMENTACTION",
				"AUTHORIZATION"));
		setECReq.add(new BasicNameValuePair("PAYMENTREQUEST_0_AMT", "0"));
		setECReq.add(new BasicNameValuePair("PAYMENTREQUEST_0_CURRENCYCODE",
				"USD"));
		setECReq.add(new BasicNameValuePair("L_BILLINGTYPE0",
				"MerchantInitiatedBilling"));
		setECReq.add(new BasicNameValuePair("L_BILLINGAGREEMENTDESCRIPTION0",
				"ClubUsage"));
		setECReq.add(new BasicNameValuePair("CANCELURL", cancelURL));
		
		// Add the return URL for WK magazine,
		if(null != magcode && "" != magcode && "WK".equalsIgnoreCase(magcode)){
			String cancelURLHost = cancelURL.substring(8, cancelURL.indexOf("/wes/"));
			System.out.println("EC Helper : Host captured from Cancel URL is :" + cancelURLHost);
			if(rtrnUrlflag <= 1){
				
				returnUrl = scheme + "://" + cancelURLHost + "/wes/servlet/Show?WESPAGE=csp-dp/makeCB.jsp";
				
				
			}
			System.out.println("EC Helper: Retun URL for WK magazine is :" + returnUrl);
		}
		else{
		System.out.println("Non WK magazine , return URL set in populate merchnat info");
		
		}
		setECReq.add(new BasicNameValuePair("RETURNURL",returnUrl));
		//setECReq.add(new BasicNameValuePair("RETURNURL","http://localhost:9080/e3PayGWAS/makeCB.jsp"));

		return setECReq;
	}
	

	public List<NameValuePair> createBillingAgreementRequest(HttpServletRequest request,String token,
			String magcode, boolean isSandBox,String payPalMail, int rtrnUrlflag) throws IOException, NamingException {
		host = request.getHeader("host");
		if(host!=null && (host.equalsIgnoreCase("wesqa.customersvc.com")|| 
				          host.equalsIgnoreCase("wesqaprod.customersvc.com")|| 
				          host.equalsIgnoreCase("testsecure.customersvc.com")||
				          host.equalsIgnoreCase("httpqc1.tcs.timeinc.com"))){
			//do nothing
			isSandBox = true;
			checkSnBox = true;
		}else{
			isSandBox = false;
			checkSnBox =false;
			//host = "secure.customersvc.com";
		}
		
		List<NameValuePair> createBillingAgreementReq = populateMerchantInfo(request,
				magcode, isSandBox,payPalMail,rtrnUrlflag);

		
		if("WK".equalsIgnoreCase(magcode)){
			createBillingAgreementReq.add(new BasicNameValuePair("VERSION", "124"));
		}
		else{
			createBillingAgreementReq.add(new BasicNameValuePair("VERSION", "78"));
		}
		createBillingAgreementReq.add(new BasicNameValuePair("METHOD",
				PayPalECConstants.CREATEBILLINGAGREEMENT));
		createBillingAgreementReq.add(new BasicNameValuePair("TOKEN", token));

		return createBillingAgreementReq;
	}

	public List<NameValuePair> createGetECDetailsRequest(HttpServletRequest request,String token,
			String magcode, boolean isSandBox,String payPalMail,int rtrnUrlflag) throws IOException, NamingException {
		host = request.getHeader("host");
		System.out.println("Host:" + host);
		if(host!=null && (host.equalsIgnoreCase("wesqa.customersvc.com")|| 
				          host.equalsIgnoreCase("wesqaprod.customersvc.com")|| 
				          host.equalsIgnoreCase("testsecure.customersvc.com")||
				          host.equalsIgnoreCase("httpqc1.tcs.timeinc.com"))){
			//do nothing
			isSandBox = true;
			checkSnBox = true;
		}else{
			isSandBox = false;
			checkSnBox =false;
			//host = "secure.customersvc.com";
		}
		List<NameValuePair> getECDetailsReq = populateMerchantInfo(request,magcode,
				isSandBox,payPalMail, rtrnUrlflag);

		if("WK".equalsIgnoreCase(magcode)){
			getECDetailsReq.add(new BasicNameValuePair("VERSION", "124"));
		}
		else{
			getECDetailsReq.add(new BasicNameValuePair("VERSION", "78"));
		}
		getECDetailsReq.add(new BasicNameValuePair("METHOD",
				PayPalECConstants.GETEXPRESSCHECKOUTDETAILS));
		getECDetailsReq.add(new BasicNameValuePair("TOKEN", token));

		return getECDetailsReq;
	}

	/**
	 * 
	 * @param callResponse
	 * @return
	 * @throws Exception 
	 */
/*	public Map<String, String> handlePayPalECResponse(
			CloseableHttpResponse httpResponse, String method) throws Exception {*/
		@SuppressWarnings("deprecation")
		public Map<String, String> handlePayPalECResponse(
				List<NameValuePair> requestParams, String method) throws Exception {

		Map<String, String> jsonMap = new HashMap<String, String>();
		

		try {

			List<NameValuePair> params = requestParams;
			/*String[] jsonArr = new String[params.size()];
			jsonArr = (String[]) params.toArray();*/

			
			
					for (NameValuePair pair : params) {
						
						//String[] keyValue = ((String) pair).split("=");

						jsonMap.put(URLDecoder.decode(pair.getName(), "UTF-8"),
								URLDecoder.decode(pair.getValue(), "UTF-8"));
					}
				//}
			} catch (Exception e) {
                System.out.println("Exception occured at handling PayPal APi calls" + e.getMessage());
				e.printStackTrace();
				throw e;
			}
			finally{
			//	httpResponse.close();
				
			}
	//	}

		return jsonMap;
	}
		

		/**
		 * Method to populate Apple Pay and ECDIRECT data in HTTPServletRequest to Java Bean
		 * 
		 * @param request
		 * @return
		 */
		public static ECDirectData populateDataForRequest(HttpServletRequest request, boolean isSandbox) {
			ECDirectData ecDirectData = new ECDirectData();
			String host1 = request.getHeader("host");
			System.out.println("Host:" + host1);
/*			if(host1!=null && (host1.equalsIgnoreCase("wesqa.customersvc.com")|| 
					          host1.equalsIgnoreCase("wesqaprod.customersvc.com")|| 
					          host1.equalsIgnoreCase("testsecure.customersvc.com"))){
				//do nothing
				isSandbox = true;
				
			}else{
				isSandbox = false;
				
				//host = "secure.customersvc.com";
			}*/
			
/*			if (null != request.getHeader(PayPalECConstants.IS_TEST_ENV)) {
				String isTestEnvValue = request.getHeader(PayPalECConstants.IS_TEST_ENV);
				if("TRUE".equalsIgnoreCase(isTestEnvValue)){
					ecDirectData.setIsTestEnv(true);
				}
				
			}*/
			if(isSandbox ){
				
				ecDirectData.setIsTestEnv(true);
			}
			else{
				ecDirectData.setIsTestEnv(false);
			}
			System.out.println("Is Test Env: " + ecDirectData.getIsTestEnv());
			if (request.getHeader(PayPalECConstants.EFFORT_KEY) != null) {
				ecDirectData.setEffortKey((String) request
						.getHeader(PayPalECConstants.EFFORT_KEY));
			}
			
			
			System.out.println("Effort Key: " + ecDirectData.getEffortKey());
			if (request.getHeader(PayPalECConstants.EFFORT_KEY_OPTION) != null) {
				ecDirectData.setEffortKeyOption((String) request
						.getHeader(PayPalECConstants.EFFORT_KEY_OPTION));
			}
			System.out.println("Effort Key Option: " + ecDirectData.getEffortKeyOption());
			
			if (request.getHeader(PayPalECConstants.DOLLAR_VALUE) != null) {
				String dValue = (String) request
						.getHeader(PayPalECConstants.DOLLAR_VALUE);
				System.out.println("EC Helper Parsing : Incoming Dollar Value :" + dValue);
				dValue = new StringBuffer(dValue).insert(dValue.length()-2, ".").toString();
				
				ecDirectData.setDollarValue(dValue);
			}
			System.out.println("Dollar Value: " + ecDirectData.getDollarValue());
			if (request.getHeader(PayPalECConstants.CUSTOMER_NAME) != null) {
				ecDirectData.setCustName((String) request
						.getHeader(PayPalECConstants.CUSTOMER_NAME));
			}
			System.out.println("Customer Name: " + ecDirectData.getCustName());
			
			if (request.getHeader(PayPalECConstants.KEYLINE) != null) {
				System.out.println("Inclong Keyline EC Controller :" + request.getHeader(PayPalECConstants.KEYLINE));
				ecDirectData.setKeyline((String) request
						.getHeader(PayPalECConstants.KEYLINE));
			}
			System.out.println("Keyline: " + ecDirectData.getKeyline());
			
			if (request.getHeader(PayPalECConstants.EFFORT_TERM) != null) {
				ecDirectData.setEffortTerm((String) request
						.getHeader(PayPalECConstants.EFFORT_TERM));
			}
			System.out.println("Effort term: " + ecDirectData.getEffortTerm());
			if (request.getHeader(PayPalECConstants.EFFORT_VALUE) != null) {
				ecDirectData.setEffortValue((String) request
						.getHeader(PayPalECConstants.EFFORT_VALUE));
			}
			System.out.println("Effort Value: " + ecDirectData.getEffortValue());
			if (request.getHeader(PayPalECConstants.MAG_CODE) != null) {
				ecDirectData.setMagCode((String) request
						.getHeader(PayPalECConstants.MAG_CODE));
			}
			System.out.println("Mag Code: " + ecDirectData.getMagCode());
			
			if (request.getHeader(PayPalECConstants.CONTRACT_ID) != null) {
				ecDirectData.setContractId((String) request
						.getHeader(PayPalECConstants.CONTRACT_ID));
				
			}
			System.out.println("Contract Id: " + ecDirectData.getContractId());
			
			
			if (request.getHeader(PayPalECConstants.SEG_ID) != null) {
				ecDirectData.setSegId((String) request
						.getHeader(PayPalECConstants.SEG_ID));
				
			}
			System.out.println("SEG ID: " + ecDirectData.getSegId());
			
			if (request.getHeader(PayPalECConstants.PDAT) != null) {
				ecDirectData.setPdat((String) request
						.getHeader(PayPalECConstants.PDAT));
				
			}
			System.out.println("PDAT: " + ecDirectData.getPdat());
			
			
			if (request.getHeader(PayPalECConstants.UNIQ) != null) {
				ecDirectData.setUniq((String) request
						.getHeader(PayPalECConstants.UNIQ));
				
			}
			System.out.println("UNIQ: " + ecDirectData.getUniq());			
			
			
			if (request.getHeader(PayPalECConstants.ECD_BID) != null) {
				ecDirectData.setEcDirectBID((String) request
						.getHeader(PayPalECConstants.ECD_BID));
				
			}else if (request.getHeader("MP_ID") != null) {
				ecDirectData.setEcDirectBID((String) request
						.getHeader("MP_ID"));
				
			}
			
			
			System.out.println("Billing Agreement Id: " + ecDirectData.getEcDirectBID());
			

			
			// Params Values for Listener			
			
			if (null != request.getParameter(PayPalECConstants.EFFORT_KEY) 
					&& "" != request.getParameter(PayPalECConstants.EFFORT_KEY)) {
				ecDirectData.setEffortKey((String) request.getParameter(PayPalECConstants.EFFORT_KEY));
			}
			System.out.println("Effort Key: " + ecDirectData.getEffortKey());
			
			
			if (null != request.getParameter(PayPalECConstants.EFFORT_KEY_OPTION)
					&& "" != request.getParameter(PayPalECConstants.EFFORT_KEY_OPTION)) {
				ecDirectData.setEffortKeyOption((String) request.getParameter(PayPalECConstants.EFFORT_KEY_OPTION));
			}
			System.out.println("Effort Key Option: " + ecDirectData.getEffortKeyOption());
			
			
			if (null != request.getParameter(PayPalECConstants.DOLLAR_VALUE)
					&& "" != request.getParameter(PayPalECConstants.DOLLAR_VALUE)) {
				String dValue = (String) request
						.getParameter(PayPalECConstants.DOLLAR_VALUE);
				dValue = new StringBuffer(dValue).insert(dValue.length()-2, ".").toString();
				
				ecDirectData.setDollarValue(dValue);
			}
			System.out.println("Dollar Value: " + ecDirectData.getDollarValue());
			
			
			if (null != request.getParameter(PayPalECConstants.CUSTOMER_NAME)
					&& "" != request.getParameter(PayPalECConstants.CUSTOMER_NAME)) {
				ecDirectData.setCustName((String) request
						.getParameter(PayPalECConstants.CUSTOMER_NAME));
			}
			System.out.println("Customer Name: " + ecDirectData.getCustName());
			
			
			
			if (null != request.getParameter(PayPalECConstants.KEYLINE)
					&& "" != request.getParameter(PayPalECConstants.KEYLINE)) {
				System.out.println("Incoming Keyline EC Controller :" + request.getParameter(PayPalECConstants.KEYLINE));
				ecDirectData.setKeyline((String) request
						.getParameter(PayPalECConstants.KEYLINE));
			}
			System.out.println("Keyline: " + ecDirectData.getKeyline());
			
			
			
			if (null != request.getParameter(PayPalECConstants.EFFORT_TERM) 
					&& "" != request.getParameter(PayPalECConstants.EFFORT_TERM)) {
				ecDirectData.setEffortTerm((String) request
						.getParameter(PayPalECConstants.EFFORT_TERM));
			}
			System.out.println("Effort term: " + ecDirectData.getEffortTerm());
			
			
			if (null != request.getParameter(PayPalECConstants.EFFORT_VALUE) 
					&& "" != request.getParameter(PayPalECConstants.EFFORT_VALUE)) {
				ecDirectData.setEffortValue((String) request
						.getParameter(PayPalECConstants.EFFORT_VALUE));
			}
			System.out.println("Effort Value: " + ecDirectData.getEffortValue());
			
			
			if (null != request.getParameter(PayPalECConstants.MAG_CODE) 
					&& "" != request.getParameter(PayPalECConstants.MAG_CODE)) {
				ecDirectData.setMagCode((String) request
						.getParameter(PayPalECConstants.MAG_CODE));
			}
			System.out.println("Mag Code: " + ecDirectData.getMagCode());
			
			if (null != request.getParameter(PayPalECConstants.CONTRACT_ID) 
					&& "" != request.getParameter(PayPalECConstants.CONTRACT_ID)) {
				ecDirectData.setContractId((String) request
						.getParameter(PayPalECConstants.CONTRACT_ID));
				
			}
			System.out.println("Contract Id: " + ecDirectData.getContractId());
			
			
			if (null != request.getParameter(PayPalECConstants.SEG_ID) 
					&& "" != request.getParameter(PayPalECConstants.SEG_ID)) {
				ecDirectData.setSegId((String) request
						.getParameter(PayPalECConstants.SEG_ID));
				
			}
			System.out.println("SEG ID: " + ecDirectData.getSegId());
			
			if (null != request.getParameter(PayPalECConstants.PDAT) 
					&& "" != request.getParameter(PayPalECConstants.PDAT)) {
				ecDirectData.setPdat((String) request
						.getParameter(PayPalECConstants.PDAT));
				
			}
			System.out.println("PDAT: " + ecDirectData.getPdat());
			
			
			if (null != request.getParameter(PayPalECConstants.UNIQ) 
					&& "" != request.getParameter(PayPalECConstants.UNIQ)) {
				ecDirectData.setUniq((String) request
						.getParameter(PayPalECConstants.UNIQ));
				
			}
			System.out.println("UNIQ: " + ecDirectData.getUniq());			
			
			
			if (null != request.getParameter("MSCCR5NR") 
					&& "" != request.getParameter("MSCCR5NR")) {
				ecDirectData.setEcDirectBID((String) request
						.getParameter("MSCCR5NR"));
				
			}
			System.out.println("Billing Agreement Id: " + ecDirectData.getEcDirectBID());
			
			if (null != request.getParameter("MSCACT") && "" != request.getParameter("MSCACT")) {
				ecDirectData.setEcDirectTransName((String) request
						.getParameter("MSCACT"));
				
			}
			System.out.println("SMN TRANSACTION NAME : " + ecDirectData.getEcDirectTransName());
			
			if (null != request.getParameter("MSCRTCAP") && "" != request.getParameter("MSCRTCAP")) {
				
				ecDirectData.setEcDirectTransID((String) request
						.getParameter("MSCRTCAP").trim());
				// Also setting parent transid
				ecDirectData.setEcDirectParentTransID(ecDirectData.getEcDirectTransID());
				
			}
			System.out.println("SMN TRANSACTION ID : " + ecDirectData.getEcDirectTransID());
			
			if (null != request.getParameter("MSCREFND") && "" != request.getParameter("MSCREFND")) {
				String listenerDollarAmount = (String) request.getParameter("MSCREFND");
				// Remove all Leading Zeroes and assign to POJO
				String dValue = listenerDollarAmount.replaceFirst("^0+(?!$)", "");
				//dValue = new StringBuffer(dValue).insert(dValue.length()-2, ".").toString();
				ecDirectData.setDollarValue(dValue);
				
			}
			System.out.println("SMN TRANSACTION REFUND AMOUNT : " + ecDirectData.getDollarValue());	
			
			if (null != request.getParameter("MSCDAMT") && "" != request.getParameter("MSCDAMT")) {
				String listenerAmount = (String) request.getParameter("MSCDAMT");
				// Remove all Leading Zeroes and assign to POJO
				String qAmnt = listenerAmount.replaceFirst("^0+(?!$)", "");
			//	ecDirectData.setEcDirectAmt(listenerAmount.replaceFirst("^0+(?!$)", ""));
				//System.out.println("SMN TRANSACTION AMOUNT : " + qAmnt);
				ecDirectData.setEcDirectAmt(qAmnt);
			}
			System.out.println("SMN TRANSACTION AMOUNT : " + ecDirectData.getEcDirectAmt());

			if (null != request.getParameter("ISFIRSTCAPTURE") && "" != request.getParameter("ISFIRSTCAPTURE")) {
				String captureFlag = (String) request.getParameter("ISFIRSTCAPTURE");
				// Remove all Leading Zeroes and assign to POJO
				//String qAmnt = listenerAmount.replaceFirst("^0+(?!$)", "");
			//	ecDirectData.setEcDirectAmt(listenerAmount.replaceFirst("^0+(?!$)", ""));
				//System.out.println("SMN TRANSACTION AMOUNT : " + qAmnt);
				//ecDirectData.setEcDirectAmt(qAmnt);
			}
			System.out.println("SMN TRANSACTION AMOUNT : " + ecDirectData.getEcDirectAmt());			
			
			
			
		return ecDirectData;
		}

		public List<NameValuePair> ecDirectCapture(ECDirectData ecDirectData,
				boolean isSandboxHost) throws IOException, NamingException {
			// TODO Auto-generated method stub
/*			if(checkSnBox){
				isSandboxHost = true;
			}else{
				isSandboxHost =false;
			}*/
			List<NameValuePair> ecDirectCaptureReq = populateMerchantInfo(ecDirectData, isSandboxHost);

			if("WK".equalsIgnoreCase(ecDirectData.getMagCode())){
				ecDirectCaptureReq.add(new BasicNameValuePair("VERSION", "124"));
			}
			else{
				ecDirectCaptureReq.add(new BasicNameValuePair("VERSION", "78"));
			}
			ecDirectCaptureReq.add(new BasicNameValuePair("METHOD",
					PayPalECConstants.DOREFERENCETRANSACTION));
			ecDirectCaptureReq.add(new BasicNameValuePair("PAYMENTACTION",
					PayPalECConstants.SALE));
			
			if(!ecDirectData.isBatchCall()){
				System.out.println("ECHelper Setting for NON Batch Call Request");
			    ecDirectCaptureReq.add(new BasicNameValuePair("REFERENCEID", ecDirectData.getEcDirectBID()));
			}
			else{
				System.out.println("ECHelper Setting for Batch Call Request");
				String bidToken = bidTokenizer(ecDirectData.getEcDirectBID(),"DECIPHER");
				ecDirectCaptureReq.add(new BasicNameValuePair("REFERENCEID", bidToken));
			}
			// Code to force error and subsequent TRB
			// ecDirectCaptureReq.add(new BasicNameValuePair("REFERENCEID", ""));
			
			if(ecDirectData.getIsListenerCall()){
	            System.out.println("EC Helper recognized the Transaction as Listener PAY");
	            ecDirectCaptureReq.add(new BasicNameValuePair("AMT", ecDirectData.getEcDirectAmt()));
			}
			else{
			ecDirectCaptureReq.add(new BasicNameValuePair("AMT", ecDirectData.getDollarValue()));
			}

			return ecDirectCaptureReq;
			
		}
		
		public static String bidTokenizer(String ecDirectBID, String method) {
			
			TokenMessage requestMessage = null;
			TokenMessage replyMessage = null;
			String paymentToken = null;
			String resMess =null;
			EpayRequestObject epayRequestObject = new EpayRequestObject();
			System.out.println("EC Direct : Helper Class - Inside Tokenize method");
			
			// TOKENIZE
			if("ENCIPHER".equalsIgnoreCase(method)){
				System.out.println("Inside Tokenizing Paypal Billing Agreement Id");
	            SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhhmmssSSS");
				String transId = "ORD" + sdf.format(new Date());
				
				epayRequestObject.setCardNumber(ecDirectBID);
				if (ecDirectBID != null && ecDirectBID.length() > 0) {
					paymentToken = InvokeCrdVService.tokenCard(epayRequestObject, transId);
				}
	            System.out.println("PayPal Tokenize" + paymentToken);
			} 
			//DETOKENIZE
			else if("DECIPHER".equalsIgnoreCase(method)){
				System.out.println("Inside De Tokenizing Paypal Billing Agreement Id");
			if (ecDirectBID != null && ecDirectBID.length() > 1) {
				String token = ecDirectBID.substring(1);
				// Code from DC
				requestMessage = TokenMessage
						.newInstance()
						.setCipherText(token)
						.setEncryptionFlag("T")
						.setFunctionName(
								TokenMessage.Function.DECIPHER.toString())
						.setSystemId("MAGIC   ");

				// The JMS connection
				Connection connection = null;

				try {
					// Look up the JMS objects needed to talk to CICS
					// Context jndi = new InitialContext();

					Context jndi = new InitialContext();
					queueCommand = new JMSQCommand("java:comp/env/jms/QCF",
							"jms/EPAY2_REQUESTQ");

					queueCommand1 = new JMSQCommand("java:comp/env/jms/QCF",
							"jms/EPAY2_REPLYQ");

					ConnectionFactory cf = (ConnectionFactory) jndi
							.lookup("jms/wesQCF");

					byte[] bytes = requestMessage.toString().getBytes("037");

					connection = cf.createConnection();
					Session session = connection.createSession(false,
							Session.AUTO_ACKNOWLEDGE);

					BytesMessage bm = session.createBytesMessage();
					bm.setIntProperty("Length", bytes.length);

					bm.setJMSReplyTo(queueCommand1.getQueue());
					// bm.setJMSReplyTo(replyQ);
					bm.setStringProperty("JMS_IBM_Character_Set", "037");
					bm.writeBytes(bytes);

					MessageProducer producer = session
							.createProducer(queueCommand.getQueue());
					producer.send(bm);

					// Used to find the reply message
					String selector = String.format("JMSCorrelationID='%s'",
							bm.getJMSMessageID());
					// System.out.println("JMS Selector: " + selector);
					connection.start();

					// MessageConsumer consumer =
					// session.createConsumer(replyQ, selector);
					MessageConsumer consumer = session.createConsumer(
							queueCommand1.getQueue(), selector);
					Message reply = null;

					/*
					 * while (true) { reply = consumer.receive(10000L); // wait
					 * up to 10 seconds for a reply break; }
					 */
					while (true) {
						reply = consumer.receive(10000L); // wait up to
															// 10
															// seconds
															// for a
															// reply
						if (reply != null) {
							resMess = reply.toString();
						}
						if (resMess != null && resMess.length() > 0) {
							System.out.println(resMess);
						} else {
							System.out.println("No message");
						}

						break;
					}

					if (reply != null && reply instanceof BytesMessage) {
						BytesMessage replybm = (BytesMessage) reply;
						byte[] replybytes = new byte[(int) replybm
								.getBodyLength()];
						replybm.readBytes(replybytes);
						replyMessage = TokenMessage.deserialize(new String(
								replybytes, "037"));

					} else {
						System.err
								.println("No message found or message not of type BytesMessage");
					}

				} catch (NamingException e) {
					e.printStackTrace();
				} catch (JMSException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
				paymentToken = replyMessage.getClearText();

				// ENd DC code

				System.out.println("PayPal BID DeTokenize"
						+ paymentToken.length());
			}
			}
			return paymentToken;
		}

		public List<NameValuePair> ecDirectRefund(ECDirectData ecDirectData,
				boolean isSandboxHost) throws IOException, NamingException {
			// TODO Auto-generated method stub
/*			if(checkSnBox){
				isSandboxHost = true;
			}else{
				isSandboxHost =false;
			}*/
			List<NameValuePair> ecDirectCaptureReq = populateMerchantInfo(ecDirectData, isSandboxHost);

			if("WK".equalsIgnoreCase(ecDirectData.getMagCode())){
				ecDirectCaptureReq.add(new BasicNameValuePair("VERSION", "124"));
			}
			else{
				ecDirectCaptureReq.add(new BasicNameValuePair("VERSION", "78"));
			}
			ecDirectCaptureReq.add(new BasicNameValuePair("METHOD",
					PayPalECConstants.REFUNDTRANSACTION));
		
			ecDirectCaptureReq.add(new BasicNameValuePair("TRANSACTIONID", ecDirectData.getEcDirectTransID()));
			//ecDirectCaptureReq.add(new BasicNameValuePair("TRANSACTIONID", ""));
			ecDirectCaptureReq.add(new BasicNameValuePair("REFUNDTYPE", "Partial"));
			ecDirectCaptureReq.add(new BasicNameValuePair("AMT", ecDirectData.getDollarValue()));

			return ecDirectCaptureReq;
			
		}		

		private List<NameValuePair> populateMerchantInfo(
				ECDirectData ecDirectData, boolean isSandboxHost) throws IOException, NamingException {
			// TODO Auto-generated method stub
/*			if(checkSnBox){
				isSandboxHost = true;
			}else{
				isSandboxHost =false;
			}*/
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			// Get details from Properties file
			Properties merchProperties = new Properties();
			InputStream iStream = null;

			try {
				InitialContext iCtx = new InitialContext();
				merchantProperties = (String) iCtx.lookup("merchantProperties");

				if (merchantProperties == null || merchantProperties == "") {
					System.out.println("Merchant Properties lookup failed");
					throw new FileNotFoundException();
				}

				iStream = new FileInputStream(merchantProperties);
				merchProperties.load(iStream);
				
				if (isSandboxHost) {
					userId = merchProperties.getProperty("USER_SNDBX");
					pwd = merchProperties.getProperty("PWD_SNDBX");
					sign = merchProperties.getProperty("SIGNATURE_SNDBX");
					payPalEmail = merchProperties.getProperty("SUBJECT_SNDBX");
					notify_url = "https://wesqa.customersvc.com/ePayG/PayPalECIPN";
					
/*					if(rtrnUrlflag <= 1){
					    returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_SNDBX_" + magcode);
					}
					else{
					    returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_SNDBX_" + magcode + "_" + rtrnUrlflag);
					}*/
				} else {
					String magcode = ecDirectData.getMagCode();

					userId = merchProperties.getProperty("USER_" + magcode);
					pwd = merchProperties.getProperty("PWD_" + magcode);
					sign = merchProperties.getProperty("SIGNATURE_" + magcode);
					notify_url = "https://secure.customersvc.com/ePayG/PayPalECIPN";
/*				//	payPalEmail = payPalMail;
					//payPalEmail = merchProperties.getProperty("SUBJECT_" + magcode);
					if(rtrnUrlflag <= 1){
						returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_" + magcode);
					}
					else{
					    //returnUrl = scheme + "://" + host + merchProperties.getProperty("RETURNURL_" + magcode + "_" + rtrnUrlflag);
						returnUrl = scheme + "://" + host + "/wes/servlet/Show?WESPAGE=pm/makeCB.jsp";
					}*/			
				}
			} catch (IOException ie) {
				System.out.println("Error at Merchant properties file" + ie.getMessage());
				ie.printStackTrace();
				throw ie;
	            	
			} catch (NamingException ne) {
				System.out.println("Naming Error at Finding Merchant properties file" + ne.getMessage());
				ne.printStackTrace();
				throw ne;
			} finally {
				if (iStream != null) {
					try {
						iStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			nameValuePairs.add(new BasicNameValuePair("USER", userId));
			nameValuePairs.add(new BasicNameValuePair("PWD", pwd));
			nameValuePairs.add(new BasicNameValuePair("SIGNATURE", sign));
			nameValuePairs.add(new BasicNameValuePair("NOTIFYURL", notify_url));

			return nameValuePairs;
		}

		public static String getCurrentDateTime() {
			// TODO Auto-generated method stub
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(
					OrbitalConstants.DATE_TIME_FORMAT);
			return sdf.format(cal.getTime());
	
		}				
		
		
		
		@SuppressWarnings("unchecked")
		public static List<NameValuePair> getBody(HttpServletRequest request, HttpServletResponse response, List<NameValuePair> params) throws Exception {

		    String body = null;
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    List<NameValuePair> requestparams;

		    try {
		        
/*		    	InputStream inputStream = request.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");*/
		    	
/*		    	Enumeration<String> parameterNames = request.getParameterNames();
		    	while (parameterNames.hasMoreElements()) {
		    		System.out.println("Looping through Request Params");
		    		String paramName = (String)parameterNames.nextElement();
		    		String[] paramValues = request.getParameterValues(paramName);
	                System.out.println("paramName :" + request.getParameterValues(paramName));
		    	}*/
		    	//List<NameValuePair> requestparams = new ArrayList<NameValuePair>();
		    	requestparams = params;
		    	for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
		    		System.out.println("Parsing IPN incmoing Query String");
		    		String name = e.nextElement();
		    		String value = request.getParameter(name);
		    		requestparams.add(new BasicNameValuePair(name, value));
		    		}
		    	
		    	
		    	
/*		    	if(null != request.getQueryString() && "" != request.getQueryString()){ 
		    		System.out.println("Parsing IPN incmoing Query String");
		    	body = request.getQueryString();
		    	}
		    	else{
		    		System.out.println("Parsing IPN incmoing Query String : String Empty or Null");
		    		
		    	}
		    	*/
		    	
		    	
		    	
		        
		    } /*catch (IOException ex) {
		    	System.out.println("I/O Exception Occured while reading Incoming request");
		        throw ex;      
		    } */
		    catch (Exception e1) {
		    	System.out.println("Exception Occured while reading Incoming request");
		        throw e1;
		        
		    }finally {
/*		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }*/
		    }

		   // body = stringBuilder.toString();
		    return requestparams;
		}

		
		/**
		 * Method to populate ECDIRECT data in IPN Request String to Java Bean
		 * 
		 * @param request
		 * @return
		 */
		public static ECDirectData populateDataForRequest(List<NameValuePair> requestparams, boolean isSandbox) {
			ECDirectData ecDirectData = new ECDirectData();
			Map<String, String> ecIPNDataMap = new HashMap<String, String>();
			PayPalECHelper payPalECHelper = new PayPalECHelper();
			String[] ecIPNData = null;
			
			try{
				
				if(isSandbox ){
					
					ecDirectData.setIsTestEnv(true);
				}
				else{
					ecDirectData.setIsTestEnv(false);
				}
				
				//ecIPNData = payloadRequest.split("&");
				ecIPNDataMap = payPalECHelper.handlePayPalECResponse(requestparams, "IPNDATA");

				if(ecIPNDataMap != null && ecIPNDataMap.size() > 0) {
					if(null != (ecIPNDataMap.get("txn_id")) && "" != (ecIPNDataMap.get("txn_id"))) {
						
						System.out.println("TRANSACTION ID FROM IPN MAP DAT :" + ecIPNDataMap.get("txn_id"));
						ecDirectData.setEcDirectTransID(ecIPNDataMap.get("txn_id"));
						
						if(null != (ecIPNDataMap.get("payment_status")) && "" != (ecIPNDataMap.get("payment_status"))) {
							
							System.out.println("PAYMENT STATUS FROM IPN MAP DAT :" + ecIPNDataMap.get("payment_status"));
							ecDirectData.setPaymentStatus(ecIPNDataMap.get("payment_status"));
							
						}

						if(null != (ecIPNDataMap.get("refund_status")) && "" != (ecIPNDataMap.get("refund_status"))) {
							
							System.out.println("REFUND STATUS FROM IPN MAP DAT :" + ecIPNDataMap.get("refund_status"));
							ecDirectData.setRefundStatus(ecIPNDataMap.get("refund_status"));
							
						}
						
						if(null != (ecIPNDataMap.get("pending_reason")) && "" != (ecIPNDataMap.get("pending_reason"))) {
							
							System.out.println("PENDING REASON FROM IPN MAP DAT :" + ecIPNDataMap.get("pending_reason"));
							ecDirectData.setPendingReason(ecIPNDataMap.get("pending_reason"));
							
						}
						
						if(null != (ecIPNDataMap.get("payer_id")) && "" != (ecIPNDataMap.get("payer_id"))) {
							
							System.out.println("Payer ID FROM IPN MAP DAT :" + ecIPNDataMap.get("payer_id"));
						//	ecDirectData.setPaymentStatus(ecIPNDataMap.get("payment_status"));
							
						}
						
					if(null != (ecIPNDataMap.get("payment_gross")) && "" != (ecIPNDataMap.get("payment_gross"))) {
							
							System.out.println("Payment Gross FROM IPN MAP DAT :" + ecIPNDataMap.get("payment_gross"));
							ecDirectData.setDollarValue(ecIPNDataMap.get("payment_gross"));
							
						}						
						
		              // ANy Other fields can be added later
						
					} 
					// REFUND TRANSACTIONS
/*					else if(null != (ecIPNDataMap.get("refund_txn_id")) && "" != (ecIPNDataMap.get("refund_txn_id"))) {
							
							System.out.println("REFUND TRANSACTION ID FROM IPN MAP DAT :" + ecIPNDataMap.get("refund_txn_id"));
							ecDirectData.setEcDirectTransID(ecIPNDataMap.get("refund_txn_id"));
							
							if(null != (ecIPNDataMap.get("refund_status")) && "" != (ecIPNDataMap.get("refund_status"))) {
								
								System.out.println("REFUND STATUS FROM IPN MAP DAT :" + ecIPNDataMap.get("refund_status"));
								ecDirectData.setPaymentStatus(ecIPNDataMap.get("refund_status"));
								
							}
							
							if(null != (ecIPNDataMap.get("pending_reason")) && "" != (ecIPNDataMap.get("pending_reason"))) {
								
								System.out.println("PENDING REASON FROM IPN MAP DAT :" + ecIPNDataMap.get("pending_reason"));
								ecDirectData.setPaymentStatus(ecIPNDataMap.get("pending_reason"));
								
							}
							
							if(null != (ecIPNDataMap.get("payer_id")) && "" != (ecIPNDataMap.get("payer_id"))) {
								
								System.out.println("Payer ID FROM IPN MAP DAT :" + ecIPNDataMap.get("payer_id"));
							//	ecDirectData.setPaymentStatus(ecIPNDataMap.get("payment_status"));
								
							}
			              // ANy Other fields can be added later
							
						} */
					
					else {
						  System.out.println("NO TRANSACTION ID IN IPN MAP DATA:" + ecIPNDataMap.get("txn_id"));

					}
				} else {
					// In case of HTTP status code not being 200
					System.out.println("Error at IPN Data Processing from MAP:");
				}				
				
			}
			catch(Exception e){
				
				System.out.println("Exception while building POJO from IPN Data" + e.getMessage());
				
			}
			return ecDirectData;
		}

		public Map<String, String> handlePayPalECResponse(String[] jsonArr,
				String method) throws ECConnectionException {
			Map<String, String> jsonMap = new HashMap<String, String>();

			try {
				//int count = 0;		
				for (String pair : jsonArr) {
							//System.out.println("Helper : Looping for time :" + count);
							String[] keyValue = pair.split("=");
/*							System.out.println("Helper : String is  :" + keyValue);
							System.out.println("Helper : String split 0  :" + keyValue[0]);
							System.out.println("Helper : String split 0  :" + keyValue[1]);*/
							jsonMap.put(keyValue[0],
									URLDecoder.decode(keyValue[1], "UTF-8"));
							
							//count++;
						}
					//}
				} catch (Exception e) {
	                System.out.println("Exception occured at handling PayPal APi calls" + e.getMessage());
					e.printStackTrace();
					throw new ECConnectionException("General Exception occured at Helper class");
				}
				finally{
				//	httpResponse.close();
					
				}
		//	}
			return jsonMap;
		}

		public static ECDirectData populateDataForRequest(
				ECDirectBean ecDirectBean, ECDirectData ecDirectData) {
			
			
			if (null != ecDirectBean.getEffortKey() && "" != ecDirectBean.getEffortKey()) {
				ecDirectData.setEffortKey(ecDirectBean.getEffortKey());
			}
			System.out.println("IPN Set Effort Key : " + ecDirectData.getEffortKey());
			
			if (null != ecDirectBean.getEffortKeyOption() && "" != ecDirectBean.getEffortKeyOption()) {
				ecDirectData.setEffortKeyOption(ecDirectBean.getEffortKeyOption());
			}
			System.out.println("IPN Set Effort Key Option: " + ecDirectData.getEffortKeyOption());
			
			if (null != ecDirectBean.getEffortTerm() && "" != ecDirectBean.getEffortTerm()) {
				ecDirectData.setEffortTerm(ecDirectBean.getEffortTerm());
			}
			System.out.println("IPN Set Effort Term:" + ecDirectData.getEffortTerm());
			
			if (null != ecDirectBean.getEffortValue() && "" != ecDirectBean.getEffortValue()) {
				ecDirectData.setEffortValue(ecDirectBean.getEffortValue());
			}
			System.out.println("IPN Set Effort Value:" + ecDirectData.getEffortValue());			
			
			
			if (null != ecDirectBean.getCustName() && "" != ecDirectBean.getCustName()) {
				ecDirectData.setCustName(ecDirectBean.getCustName());
			}
			System.out.println("IPN Set Customer Name:" + ecDirectData.getCustName());
			
			if (null != ecDirectBean.getKeyline() && "" != ecDirectBean.getKeyline()) {
				ecDirectData.setKeyline(ecDirectBean.getKeyline());
			}
			System.out.println("IPN Set KeyLine:" + ecDirectData.getKeyline());
			
			if (null != ecDirectBean.getCustAddr1() && "" != ecDirectBean.getCustAddr1()) {
				ecDirectData.setCustAddr1(ecDirectBean.getCustAddr1());
			}
			System.out.println("IPN Set Customer Address 1:" + ecDirectData.getCustAddr1());
			
/*			if (null != ecDirectBean.getCustAddr2() && "" != ecDirectBean.getCustAddr2()) {
				ecDirectData.setCustAddr2(ecDirectBean.getCustAddr2());
			}
			System.out.println("IPN Set Customer Address 2:" + ecDirectData.getCustAddr2());*/			

			if (null != ecDirectBean.getPostalCode() && "" != ecDirectBean.getPostalCode()) {
				ecDirectData.setPostalCode(ecDirectBean.getPostalCode());
			}
			System.out.println("IPN Set ZIP Code:" + ecDirectData.getPostalCode());	
			
			if (null != ecDirectBean.getSegId() && "" != ecDirectBean.getSegId()) {
				ecDirectData.setSegId(ecDirectBean.getSegId());
			}
			System.out.println("IPN Set SEGID:" + ecDirectData.getSegId());	
			
			if (null != ecDirectBean.getPdat() && "" != ecDirectBean.getPdat()) {
				ecDirectData.setPdat(ecDirectBean.getPdat());
			}
			System.out.println("IPN Set PDAT:" + ecDirectData.getPdat());				
			
			if (null != ecDirectBean.getUniq() && "" != ecDirectBean.getUniq()) {
				ecDirectData.setUniq(ecDirectBean.getUniq());
			}
			System.out.println("IPN Set UNIQ:" + ecDirectData.getUniq());				
			
			if (null != ecDirectBean.getMagCode() && "" != ecDirectBean.getMagCode()) {
				ecDirectData.setMagCode(ecDirectBean.getMagCode());
			}
			System.out.println("IPN Set MAGCODE:" + ecDirectData.getMagCode());
			
			if (null != ecDirectBean.getEcDirectParentTransID() && "" != ecDirectBean.getEcDirectParentTransID()) {
				ecDirectData.setEcDirectParentTransID(ecDirectBean.getEcDirectParentTransID());
			}
			System.out.println("IPN Set Parent Trans ID:" + ecDirectData.getEcDirectParentTransID());			
			
			if (null != ecDirectBean.getEcDirectTransName() && "" != ecDirectBean.getEcDirectTransName()) {
				ecDirectData.setEcDirectTransName(ecDirectBean.getEcDirectTransName());
			}
			System.out.println("IPN Set Trans Name:" + ecDirectData.getEcDirectTransName());	
			
			if(null == ecDirectData.getEcDirectAmt() || "" ==  ecDirectData.getEcDirectAmt()){
				if(null != ecDirectData.getDollarValue() || "" !=  ecDirectData.getDollarValue()){
					
					System.out.println("Setting Dollar Amount to EC Direct Amount");
					ecDirectData.setEcDirectAmt(ecDirectData.getDollarValue().replace(".", ""));
				}
				
			}		
			

	
			return ecDirectData;
		}

		public static ECDirectData populateDataForRequest(
				ECDirectOrphanBean ecDOrphan) {
			// TODO Auto-generated method stub
			
			ECDirectData ecDirectData = new ECDirectData();
			
			if (null != ecDOrphan.getEffortKey() && "" != ecDOrphan.getEffortKey()) {
				ecDirectData .setEffortKey(ecDOrphan.getEffortKey());
			}
			System.out.println("BATCH Set Effort Key : " + ecDirectData.getEffortKey());
			
			if (null != ecDOrphan.getEffortKeyOption() && "" != ecDOrphan.getEffortKeyOption()) {
				ecDirectData.setEffortKeyOption(ecDOrphan.getEffortKeyOption());
			}
			System.out.println("BATCH Set Effort Key Option: " + ecDirectData.getEffortKeyOption());
			
			if (null != ecDOrphan.getEffortTerm() && "" != ecDOrphan.getEffortTerm()) {
				ecDirectData.setEffortTerm(ecDOrphan.getEffortTerm());
			}
			System.out.println("BATCH Set Effort Term:" + ecDirectData.getEffortTerm());
			
			if (null != ecDOrphan.getEffortValue() && "" != ecDOrphan.getEffortValue()) {
				ecDirectData.setEffortValue(ecDOrphan.getEffortValue());
			}
			System.out.println("BATCH Set Effort Value:" + ecDirectData.getEffortValue());			
			
			
			if (null != ecDOrphan.getCustName() && "" != ecDOrphan.getCustName()) {
				ecDirectData.setCustName(ecDOrphan.getCustName());
			}
			System.out.println("BATCH Set Customer Name:" + ecDirectData.getCustName());
			
			if (null != ecDOrphan.getKeyline() && "" != ecDOrphan.getKeyline()) {
				ecDirectData.setKeyline(ecDOrphan.getKeyline());
			}
			System.out.println("BATCH Set KeyLine:" + ecDirectData.getKeyline());
			
			if (null != ecDOrphan.getCustAddr1() && "" != ecDOrphan.getCustAddr1()) {
				ecDirectData.setCustAddr1(ecDOrphan.getCustAddr1());
			}
			System.out.println("BATCH Set Customer Address 1:" + ecDirectData.getCustAddr1());
			
			

			if (null != ecDOrphan.getPostalCode() && "" != ecDOrphan.getPostalCode()) {
				ecDirectData.setPostalCode(ecDOrphan.getPostalCode());
			}
			System.out.println("BATCH Set ZIP Code:" + ecDirectData.getPostalCode());	
			
			if (null != ecDOrphan.getSegId() && "" != ecDOrphan.getSegId()) {
				ecDirectData.setSegId(ecDOrphan.getSegId());
			}
			System.out.println("BATCH Set SEGID:" + ecDirectData.getSegId());	
			
			if (null != ecDOrphan.getPdat() && "" != ecDOrphan.getPdat()) {
				ecDirectData.setPdat(ecDOrphan.getPdat());
			}
			System.out.println("BATCH Set PDAT:" + ecDirectData.getPdat());				
			
			if (null != ecDOrphan.getUniq() && "" != ecDOrphan.getUniq()) {
				ecDirectData.setUniq(ecDOrphan.getUniq());
			}
			System.out.println("BATCH Set UNIQ:" + ecDirectData.getUniq());				
			
			if (null != ecDOrphan.getMagCode() && "" != ecDOrphan.getMagCode()) {
				ecDirectData.setMagCode(ecDOrphan.getMagCode());
			}
			System.out.println("BATCH Set MAGCODE:" + ecDirectData.getMagCode());
			
			if (null != ecDOrphan.getEcDirectParentTransID() && "" != ecDOrphan.getEcDirectParentTransID()) {
				ecDirectData.setEcDirectParentTransID(ecDOrphan.getEcDirectParentTransID());
				//Setting also to TransID needed for RFD retry
				ecDirectData.setEcDirectTransID(ecDOrphan.getEcDirectParentTransID());
			}
			System.out.println("BATCH Set Parent Trans ID:" + ecDirectData.getEcDirectParentTransID());			
			
			if (null != ecDOrphan.getEcDirectTransName() && "" != ecDOrphan.getEcDirectTransName()) {
				ecDirectData.setEcDirectTransName(ecDOrphan.getEcDirectTransName().trim());
			}
			System.out.println("BATCH Set Trans Name:" + ecDirectData.getEcDirectTransName());	
			
			if (null != ecDOrphan.getEcDirectAmt() && "" != ecDOrphan.getEcDirectAmt()) {
				ecDirectData.setEcDirectAmt(ecDOrphan.getEcDirectAmt());
			}
			System.out.println("BATCH Set Amount:" + ecDirectData.getEcDirectAmt());	
			
			if (null != ecDOrphan.getDollarValue() && "" != ecDOrphan.getDollarValue()) {
				ecDirectData.setDollarValue(ecDOrphan.getDollarValue());
			}
			System.out.println("BATCH Set Dollar Value:" + ecDirectData.getDollarValue());	
						
			
			// BID Token 
			// Go for decipher
			if (null != ecDOrphan.getEcDirectBIDToken() && "" != ecDOrphan.getEcDirectBIDToken()) {
				ecDirectData.setEcDirectBID(ecDOrphan.getEcDirectBIDToken());
			}
			System.out.println("BATCH Set BID:" + ecDirectData.getEcDirectBID());
			
			if (null != ecDOrphan.getIsTransDone() && "" != ecDOrphan.getIsTransDone()) {
				ecDirectData.setTransDone(ecDOrphan.getIsTransDone());
			}
			System.out.println("BATCH Set TransDone:" + ecDirectData.getTransDone());
			
			
/*			if(null == ecDirectData.getEcDirectAmt() || "" ==  ecDirectData.getEcDirectAmt()){
				if(null != ecDirectData.getDollarValue() || "" !=  ecDirectData.getDollarValue()){
					
					System.out.println("Setting Dollar Amount to EC Direct Amount");
					ecDirectData.setEcDirectAmt(ecDirectData.getDollarValue().replace(".", ""));
				}
				
			}*/		
			

	
			return ecDirectData;			
		}
	
/**
 * Method to count the number of transactions
 * and set it as "Y", if it has exceeded 6 times.
 * 
 * @param ecDirectData
 */
		public static void setTransactionValue(ECDirectData ecDirectData) {
			if(!ecDirectData.getTransDone().trim().equalsIgnoreCase("Y")) {
				int count = Integer.parseInt(ecDirectData.getTransDone().trim());
				if(count < 6){
					count++;
					ecDirectData.setTransDone(Integer.toString(count));
				}else {
					ecDirectData.setTransDone("Y");
				}
			}
		}
	
/*		public static String handlePayPalECResponse(String[] jsonArr) throws Exception {
			String verifyResponse = "";
			

			try {

				
						for (String pair : jsonArr) {
							String[] keyValue = pair.split("=");

							jsonMap.put(keyValue[0],
									URLDecoder.decode(keyValue[1], "UTF-8"));
							
							verifyResponse = pair;
							System.out.println("Looping thorugh IPN verification Reply Parsing");
						}
					//}
				} catch (Exception e) {
	                System.out.println("Exception occured at handling PayPal APi calls" + e.getMessage());
					e.printStackTrace();
					throw e;
				}
				finally{
				//	httpResponse.close();
					
				}
		//	}

			return verifyResponse;
		}*/		
		
}

