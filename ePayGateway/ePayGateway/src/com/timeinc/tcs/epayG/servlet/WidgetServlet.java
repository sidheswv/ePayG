package com.timeinc.tcs.epayG.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timeinc.tcs.addr.EAddrDTO;
import com.timeinc.tcs.epay2.EPay2DTO;
import com.timeinc.tcs.epayG.entity.EAddrRequestObject;
import com.timeinc.tcs.epayG.entity.EpayRequestObject;
import com.timeinc.tcs.epayG.entity.PaymentWidgetObject;
import com.timeinc.tcs.epayG.entity.WidgetResponseObject;
import com.timeinc.tcs.epayG.constants.AddrHygnWidgetConstants;
import com.timeinc.tcs.epayG.constants.PaymentWidgetConstants;
import com.timeinc.tcs.epayG.utils.InvokeAdrHygnService;
import com.timeinc.tcs.epayG.utils.InvokeCrdVService;

/**
 * Servlet for Payment Widget
 * 
 * Vengat S -- 02/15/2016 -- 023962 Payment Widget project
 */
public class WidgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WidgetServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
	
		
		System.out.println("Inside POST");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
	//	response.getWriter().write("OUTPUT");
		
		boolean cleansingStatus = false;

		boolean cardStatus = false;
		boolean hasReturn = false;
		boolean isVerification = false;
		boolean isCountryblank = false;
		String paymentMessage = "";
		EPay2DTO epayResponse = new EPay2DTO();

		String isAddressClean = "N";
		String isCardVerified = "Y";
		RequestDispatcher reqDisp = null;
		EAddrDTO eaddrResponse = new EAddrDTO();
		PaymentWidgetObject paymentWidgetObject = new PaymentWidgetObject();
		WidgetResponseObject widgetResponseObject = new WidgetResponseObject();

		ServletContext foreignContext = this.getServletContext().getContext(
				"/wes");
		ServletContext dcContext = this.getServletContext().getContext(
				"/maitrd");

		System.out.println("Incoming request Object of Payment Widget :"
				+ request.toString());
			try {

			// Address Hygiene Request Data
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++INSIDE WIDGET SERVLET++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
			String source = "";
			if (request.getParameter("SOURCE") != null
					&& request.getParameter("SOURCE").trim().length() > 0) {

				source = request.getParameter("SOURCE");
			}
			
			String donationFlag = "";
			if (request.getParameter("donationFlag") != null
					&& request.getParameter("donationFlag").trim().length() > 0) {

				donationFlag = request.getParameter("donationFlag");
			}
			
			String firstName = "";
			if (request.getParameter("firstName") != null
					&& request.getParameter("firstName").trim().length() > 0) {

				firstName = request.getParameter("firstName");
			}

			String lastName = "";

			if (request.getParameter("lastName") != null
					&& request.getParameter("lastName").trim().length() > 0) {

				lastName = request.getParameter("lastName");
			}

			String address = "";
			if (request.getParameter("customer_address") != null
					&& request.getParameter("customer_address").trim().length() > 0) {

				address = request.getParameter("customer_address");
			}

			String address1 = "";
			if (request.getParameter("customer_address1") != null
					&& request.getParameter("customer_address1").trim()
							.length() > 0) {
				address1 = request.getParameter("customer_address1");
				System.out.println("Incoming address line 2 :" + address1);
			}
			String city = "";
			if (request.getParameter("city") != null
					&& request.getParameter("city").trim().length() > 0) {

				city = request.getParameter("city");
			}

			String state = "";
			if (request.getParameter("state") != null
					&& request.getParameter("state").trim().length() > 0) {

				state = request.getParameter("state");
			}

			String country = "  ";
			String country_name = "";
			if (request.getParameter("country") != null
					&& request.getParameter("country").trim().length() > 0) {
				country_name = request.getParameter("country");

				if (country_name.trim().length() == 2) {
					if (country_name.trim().equalsIgnoreCase("UK")) {

						country = "GB";
					} else {
						country = country_name;
					}
				}

			}
			String zipcode = "  ";
			String cust_zipcode = "";
			if (request.getParameter("zipcode") != null
					&& request.getParameter("zipcode").trim().length() > 0) {
				cust_zipcode = request.getParameter("zipcode");
			}
			if (country_name.equalsIgnoreCase("US")
					|| country_name.equalsIgnoreCase("CA")) {

				zipcode = cust_zipcode;
			}

			String addrCnfrmd = "";

			if (request.getParameter("ADDRCNFRMD") != null
					&& request.getParameter("ADDRCNFRMD").trim().length() > 0) {

				addrCnfrmd = request.getParameter("ADDRCNFRMD");
			}

			// Credit card data
			String cardNumber = "";
			if (request.getParameter("ccNum") != null && request.getParameter("ccNum").trim() != null
					&& request.getParameter("ccNum").trim().length() > 0) {
				cardNumber = request.getParameter("ccNum");

			}
			String expMonth = "";
			if (request.getParameter("expMonth") != null && request.getParameter("expMonth").trim() != null
					&& request.getParameter("expMonth").trim().length() > 0) {
				expMonth = request.getParameter("expMonth");
			}

			String cardexpYear = "";
			String expYear = "";
			if (request.getParameter("expYear") != null && request.getParameter("expYear").trim() != null
					&& request.getParameter("expYear").trim().length() > 0) {
				cardexpYear = request.getParameter("expYear");
				expYear = cardexpYear.substring(2);
			}

			String cardTypevalue = "";
			if (request.getParameter("cardType") != null && request.getParameter("cardType").trim() != null
					&& request.getParameter("cardType").trim().length() > 0) {
				cardTypevalue = request.getParameter("cardType");
			}

			String cardType = "";
			if (cardTypevalue != null) {
				if (cardTypevalue.equalsIgnoreCase("V")) {
					cardType = "VI";
				} else if (cardTypevalue.equalsIgnoreCase("A")) {
					cardType = "AX";
				} else if (cardTypevalue.equalsIgnoreCase("M")) {
					cardType = "MC";
				} else if (cardTypevalue.equalsIgnoreCase("S")) {
					cardType = "DI";
				} 
				if (cardTypevalue.equalsIgnoreCase("L")) {
					cardType = "PY";
				}
			}

			String address_line1 = address;

			String cardCity = city;

			String cardState = state;

			String cardZip = zipcode;

			// Payment Token
			String paymentToken = "";
			String paymentToken_val = "";

			String returnUrl = "";

			if (request.getParameter("RETURN_URL") != null
					&& request.getParameter("RETURN_URL").trim().length() > 0) {

				returnUrl = request.getParameter("RETURN_URL").trim();
				hasReturn = true;

			}
			System.out.println("return URL captured :" + returnUrl);

			// Customer_Email
			String custEmail = "";
			if (request.getParameter("customer_email") != null
					&& request.getParameter("customer_email").trim().length() > 0) {

				custEmail = request.getParameter("customer_email").trim();
				System.out.println("Customer EMail :" + custEmail);
			}

			// customer_phone
			String custPhone = "";
			if (request.getParameter("customer_phone") != null
					&& request.getParameter("customer_phone").trim().length() > 0) {

				custPhone = request.getParameter("customer_phone").trim();
				System.out.println("Customer Phone :" + custPhone);
			}
			
			// ADDRESS HYGIENE
			if (addrCnfrmd.equalsIgnoreCase("N")) {

				System.out
						.println("Inside address cleansing of Payment Widget :"
								+ addrCnfrmd);
				EAddrRequestObject eaddrRequestObject = new EAddrRequestObject();
				// For address Hygiene Presenter id is Client Id
				eaddrRequestObject.setClientId("MAITRD");
				eaddrRequestObject.setAddress1(address);
				eaddrRequestObject.setAddress2(address1);
				eaddrRequestObject.setCity(city);
				eaddrRequestObject.setState(state);
				eaddrRequestObject.setZipcode(zipcode);

				// Transaction Id should be generated uniquely for all the
				// orders.
				SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhhmmssSSS");
				String transId = "ORD" + sdf.format(new Date());

				eaddrResponse = InvokeAdrHygnService.addrHygn(
						eaddrRequestObject, transId);
				System.out.println("Resposne ADDRESS HYGIENE"
						+ eaddrResponse.getResponseCode() + "Message :"
						+ eaddrResponse.getResponseMessage());

				if (eaddrResponse != null) {

					if (eaddrResponse.getOutputAddr1() != null
							|| eaddrResponse.getOutputCity() != null
							|| eaddrResponse.getOutputZip() != null) {
						System.out
								.println("Address call response has output address"
										+ eaddrResponse.getResponseMessage());
						if (eaddrResponse.getInputAddr1().equalsIgnoreCase(
								eaddrResponse.getOutputAddr1())
								&& eaddrResponse.getInputCity()
										.equalsIgnoreCase(
												eaddrResponse.getOutputCity())
								&& eaddrResponse.getInputZip()
										.equalsIgnoreCase(
												eaddrResponse.getOutputZip())) {
							isAddressClean = "Y";
							System.out
									.println("Inside address hygiene after comparision");
						}

					}
					System.out
							.println("Address call response donot have output address. Code :"
									+ eaddrResponse.getResponseCode()
									+ "Message :"
									+ eaddrResponse.getResponseMessage());

					// Send back the two addresses to user to select one

					paymentWidgetObject.setInputAddr1(eaddrResponse
							.getInputAddr1());
					String inputAddr1 = eaddrResponse.getInputAddr1();
					paymentWidgetObject.setInputAddr2(eaddrResponse
							.getInputAddr2());
					String inputAddr2 = eaddrResponse.getInputAddr2();
					paymentWidgetObject.setInputCity(eaddrResponse
							.getInputCity());
					String inputCity = eaddrResponse.getInputCity();
					paymentWidgetObject.setInputState(eaddrResponse
							.getInputState());
					String inputState = eaddrResponse.getInputState();
					paymentWidgetObject
							.setInputZip(eaddrResponse.getInputZip());
					String inputZip = eaddrResponse.getInputZip();
					paymentWidgetObject.setOutputAddr1(eaddrResponse
							.getOutputAddr1());
					String outputAddr1 = eaddrResponse.getOutputAddr1();
					paymentWidgetObject.setOutputAddr2(eaddrResponse
							.getOutputAddr2());
					String outputAddr2 = eaddrResponse.getOutputAddr2();
					paymentWidgetObject.setOutputCity(eaddrResponse
							.getOutputCity());
					String outputCity = eaddrResponse.getOutputCity();
					paymentWidgetObject.setOutputState(eaddrResponse
							.getOutputState());
					String outputState = eaddrResponse.getOutputState();
					paymentWidgetObject.setOutputZip(eaddrResponse
							.getOutputZip());
					String outputZip = eaddrResponse.getOutputZip();
					String addressCode = eaddrResponse.getResponseCode();
					String addressMessage = eaddrResponse.getResponseMessage();

					paymentWidgetObject.setCardNumber(cardNumber);
					paymentWidgetObject.setCardType(cardType);
					paymentWidgetObject.setExpiryMonth(expMonth);
					paymentWidgetObject.setExpiryYear(expYear);
					paymentWidgetObject.setFirstName(firstName);
					paymentWidgetObject.setLastName(lastName);
					paymentWidgetObject.setAddress_line1(address_line1);
					paymentWidgetObject.setCardCity(cardCity);
					paymentWidgetObject.setCardZip(cardZip);

					reqDisp = foreignContext
							.getRequestDispatcher("/servlet/Show?WESPAGE=ePayG/payment_widget.jsp&MSRSMAG=DS&isAddressClean="
									+ URLEncoder.encode (isAddressClean, "UTF-8")
									+ "&firstName="
									+ URLEncoder.encode (firstName, "UTF-8")
									+ "&lastName="
									+ URLEncoder.encode (lastName, "UTF-8")
									+ "&inputAddr1="
									+ URLEncoder.encode (inputAddr1, "UTF-8")
									+ "&inputAddr2="
									+ URLEncoder.encode (inputAddr2, "UTF-8")
									+ "&inputCity="
									+ URLEncoder.encode (inputCity, "UTF-8")
									+ "&inputState="
									+ URLEncoder.encode (inputState, "UTF-8")
									+ "&inputZip="
									+ URLEncoder.encode (inputZip, "UTF-8")
									+ "&outputAddr1="
									+ URLEncoder.encode (outputAddr1, "UTF-8")
									+ "&outputAddr2="
									+ URLEncoder.encode (outputAddr2, "UTF-8")
									+ "&outputCity="
									+ URLEncoder.encode (outputCity, "UTF-8")
									+ "&outputState="
									+ URLEncoder.encode (outputState, "UTF-8")
									+ "&outputZip="
									+ URLEncoder.encode (outputZip, "UTF-8")
									+ "&addressCode="
									+ URLEncoder.encode (addressCode, "UTF-8")
									+ "&addressMessage="
									+ URLEncoder.encode (addressMessage, "UTF-8")
									+ "&cardNumber="
									+ URLEncoder.encode (cardNumber, "UTF-8")
									+ "&cardType="
									+ URLEncoder.encode (cardType, "UTF-8")
									+ "&expMonth="
									+ URLEncoder.encode (expMonth, "UTF-8")
									+ "&expYear="
									+ URLEncoder.encode (expYear, "UTF-8")
									+ "&returnUrl=" + URLEncoder.encode (returnUrl, "UTF-8"));
					System.out
							.println("Inside address flag process response of Payment Widget"
									+ isAddressClean);

				}
			}
			
			// Payments processing
			else {

				System.out.println("Inside Payments" + cleansingStatus);
				// Payment Processing
				EpayRequestObject epayRequestObject = new EpayRequestObject();

				epayRequestObject.setCardType(cardType);
				epayRequestObject.setCardNumber(cardNumber);
				epayRequestObject.setExpiryMonth(expMonth);
				epayRequestObject.setExpiryYear(expYear);
				epayRequestObject.setFirstName(firstName);
				epayRequestObject.setLastName(lastName);
				epayRequestObject.setAddress_line1(address_line1);
				epayRequestObject.setCity(cardCity);
				epayRequestObject.setState(cardState);
				epayRequestObject.setZipCode(cardZip);
				epayRequestObject.setCountryCode(country);
				//epayRequestObject.setClientId(clientId);
				System.out.println("cardType : " + cardType);
				System.out.println("cardNumber Length : " + cardNumber.length());
				System.out.println("expMonth : " + expMonth);
				System.out.println("expYear : " + expYear);
				System.out.println("firstName : " + firstName);
				System.out.println("lastName : " + lastName);
				System.out.println("address_line1 : " + address_line1);
				System.out.println("cardCity : " + cardCity);
				System.out.println("cardState : " + cardState);
				System.out.println("cardZip : " + cardZip);
				System.out.println("country : " + country);

				// Transaction Id should be generated uniquely for all the
				// orders.
				SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhhmmssSSS");
				String transId = "ORD" + sdf.format(new Date());
				
				// Credit Card verification
				if (!cardTypevalue.equalsIgnoreCase("L")) {

					System.out.println("Inside VERIFY CARD" + cleansingStatus);
					epayResponse = InvokeCrdVService.verifyCard(
							epayRequestObject, transId, donationFlag);
					System.out.println("Resposne VERIFY CARD"
							+ epayResponse.getResponseCode());
					System.out.println("Card verified status 1:"
							+ epayResponse.getTranResponseType());

					if (epayResponse.getTranResponseType().equals("A")) {
						System.out.println("Card verified status Inside:"
								+ epayResponse.getTranResponseType());
						cardStatus = true;
					}
					
					if("Y".equalsIgnoreCase(donationFlag)){
						response.setContentType("text/plain");
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						if(cardStatus){
							System.out.println("+++++++++Card Verified for Donation+++++++++");
							out.print("Y");
							System.out.println("Response SENT");
						}else{
							System.out.println("+++++++++Card is InValid for Donation+++++++++");
							out.print("N");
						}
					}
					
					if (!cardStatus && !"Y".equalsIgnoreCase(donationFlag)) {

						// Setting the Object

						isCardVerified = "N";
						reqDisp = foreignContext
								.getRequestDispatcher("/servlet/Show?WESPAGE=ePayG/payment_widget.jsp&SOURCE="
										+ URLEncoder.encode (source, "UTF-8")
										+ "&customer_name="
										+ URLEncoder.encode (firstName, "UTF-8")
										+ "&customer_last_name="
										+ URLEncoder.encode (lastName, "UTF-8")
										+ "&customer_address="
										+ URLEncoder.encode (address, "UTF-8")
										+ "&customer_address1="
										+ URLEncoder.encode (address1, "UTF-8")
										+ "&customer_city="
										+ URLEncoder.encode (city, "UTF-8")
										+ "&customer_state="
										+ URLEncoder.encode (state, "UTF-8")
										+ "&customer_zip="
										+ URLEncoder.encode (cust_zipcode, "UTF-8")
										+ "&customer_country="
										+ URLEncoder.encode (country_name, "UTF-8")
										+ "&customer_email="
										+ URLEncoder.encode (custEmail, "UTF-8")
										+ "&customer_phone="
										+ URLEncoder.encode (custPhone, "UTF-8")
										+ "&paymentMessage="
										+ URLEncoder.encode (paymentMessage, "UTF-8")
										+ "&isCardVerified=" 
										+ URLEncoder.encode (isCardVerified, "UTF-8"));

						System.out
								.println("Failed VERIFY CARD"
										+ epayResponse.getTranResponseType()
										+ "Token :"
										+ epayResponse.getAccountNbrToken());
						reqDisp.forward(request, response);

					} else if(!"Y".equalsIgnoreCase(donationFlag)){
						// Credit Card Token
						paymentToken = epayResponse.getAccountNbrToken();
						if (paymentToken != null) {
							paymentToken_val = paymentToken.trim().substring(1);
						}
						// Building Response Object

						System.out.println("Card Token :" + paymentToken);
						//System.out.println("return URL before sending :"
							//	+ returnUrl);
						System.out.println("return URL before sending :"
								+ returnUrl + "?SOURCE=" + URLEncoder.encode (source, "UTF-8")
								+ "&customer_name=" + URLEncoder.encode (firstName, "UTF-8")
								+ "&customer_last_name=" + URLEncoder.encode (lastName, "UTF-8")
								+ "&customer_address=" + URLEncoder.encode (address, "UTF-8")
								+ "&customer_address1=" + URLEncoder.encode (address1, "UTF-8")
								+ "&customer_city=" + URLEncoder.encode (city , "UTF-8")
								+ "&customer_state=" + URLEncoder.encode (state , "UTF-8")
								+ "&customer_zip=" + URLEncoder.encode (cust_zipcode, "UTF-8")
								+ "&customer_country=" + URLEncoder.encode (country_name, "UTF-8")
								+ "&customer_email=" + URLEncoder.encode (custEmail, "UTF-8")
								+ "&customer_phone=" + URLEncoder.encode (custPhone, "UTF-8")
								+ "&payment_type=" + URLEncoder.encode (cardType, "UTF-8")
								+ "&customer_cc_num=" + URLEncoder.encode (paymentToken_val, "UTF-8")
								+ "&expMonth=" + URLEncoder.encode (expMonth , "UTF-8")
								+ "&expYear=" + URLEncoder.encode (expYear , "UTF-8")
								+ "&isCardVerified=" + URLEncoder.encode (isCardVerified, "UTF-8"));
						
						System.out.println("Card Token sub :"
								+ paymentToken_val);
						

						response.sendRedirect(returnUrl + "?SOURCE=" + URLEncoder.encode (source, "UTF-8")
								+ "&customer_name=" + URLEncoder.encode (firstName, "UTF-8")
								+ "&customer_last_name=" + URLEncoder.encode (lastName, "UTF-8")
								+ "&customer_address=" + URLEncoder.encode (address, "UTF-8")
								+ "&customer_address1=" + URLEncoder.encode (address1, "UTF-8")
								+ "&customer_city=" + URLEncoder.encode (city , "UTF-8")
								+ "&customer_state=" + URLEncoder.encode (state , "UTF-8")
								+ "&customer_zip=" + URLEncoder.encode (cust_zipcode, "UTF-8")
								+ "&customer_country=" + URLEncoder.encode (country_name, "UTF-8")
								+ "&customer_email=" + URLEncoder.encode (custEmail, "UTF-8")
								+ "&customer_phone=" + URLEncoder.encode (custPhone, "UTF-8")
								+ "&payment_type=" + URLEncoder.encode (cardType, "UTF-8")
								+ "&customer_cc_num=" + URLEncoder.encode (paymentToken_val, "UTF-8")
								+ "&expMonth=" + URLEncoder.encode (expMonth , "UTF-8")
								+ "&expYear=" + URLEncoder.encode (expYear , "UTF-8")
								+ "&isCardVerified=" + URLEncoder.encode (isCardVerified, "UTF-8"));

					}

				}
				// Tokenizing PayPal Billing Agreement ID
				else {
					System.out
							.println("Inside Tokenizing Paypal Billing Agreement Id");
					paymentToken = InvokeCrdVService.tokenCard(
							epayRequestObject, transId);
					System.out.println("PayPal Tokenize" + paymentToken);

					// Building Response Object

					response.sendRedirect(returnUrl + "?SOURCE=" + URLEncoder.encode (source, "UTF-8")
							+ "&customer_name=" + URLEncoder.encode (firstName, "UTF-8")
							+ "&customer_last_name=" + URLEncoder.encode (lastName, "UTF-8")
							+ "&customer_address=" + URLEncoder.encode (address, "UTF-8")
							+ "&customer_address1=" + URLEncoder.encode (address1, "UTF-8")
							+ "&customer_city=" + URLEncoder.encode (city , "UTF-8")
							+ "&customer_state=" + URLEncoder.encode (state , "UTF-8")
							+ "&customer_zip=" + URLEncoder.encode (cust_zipcode, "UTF-8")
							+ "&customer_country=" + URLEncoder.encode (country_name, "UTF-8")
							+ "&customer_email=" + URLEncoder.encode (custEmail, "UTF-8")
							+ "&customer_phone=" + URLEncoder.encode (custPhone , "UTF-8")
							+ "&payment_type="  + URLEncoder.encode (cardType , "UTF-8")
							+ "&customer_cc_num=" + URLEncoder.encode (paymentToken_val, "UTF-8")
							+ "&expMonth=" + URLEncoder.encode (expMonth , "UTF-8")
							+ "&expYear=" + URLEncoder.encode (expYear, "UTF-8")

					);

				}

			}

		} catch (Exception e) {
			System.out.println("Error in Servlet" + e.getMessage());
			request.setAttribute("Error Message", "Error occured in Servlet");

			reqDisp = foreignContext
					.getRequestDispatcher("/servlet/Show?WESPAGE=ePayG/payment_widget.jsp&MSRSMAG=DS");
			reqDisp.forward(request, response);

		}

		System.out.println("OutGoing Request:" + request);

	}

}
