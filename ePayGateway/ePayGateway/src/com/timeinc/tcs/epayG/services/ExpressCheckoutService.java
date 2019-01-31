package com.timeinc.tcs.epayG.services;

/**
 * 
 * @author poduril
 * 
 * RESTful Service for PayPal ExpressCheckout  Functionality
 * 
 */

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.timeinc.tcs.epayG.dto.PayPalECDto;
import com.timeinc.tcs.epayG.exception.ServiceException;
import com.timeinc.tcs.epayG.exception.ValidateException;
import com.timeinc.tcs.epayG.messages.PayPalExpressResponse;
import com.timeinc.tcs.epayG.messages.PaypalExpressRequest;
import com.timeinc.tcs.epayG.servlet.PayPalECServlet;
import com.timeinc.tcs.epayG.validation.ParseECRequest;
import com.timeinc.tcs.epayG.validation.ValidatePayPalMail;

@Path("/paypalexpresscheckout")
public class ExpressCheckoutService {

	// private String cancelURL;
	private boolean validMail;
	private boolean validDetails;
	private int rtrnUrlflag;
	private final int unauth = 401;
	private final int internalErr = 500;

	@Path("/{method}")
	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	public Response setExpressCheckOut(@Context HttpServletRequest request,
			@Context HttpServletResponse response,
			@PathParam("method") String method,
			@QueryParam("token") String token,
			@HeaderParam("CancelURL") String cancelURL,
			@HeaderParam("ClientId") String clientId,
			@HeaderParam("paypalEmail") String payPalMail,
			@HeaderParam("MerchantId") String merchantId,
			@HeaderParam("MerchantPwd") String merchantPwd,
			@HeaderParam("isSandBox") String isSandBox,
	        @HeaderParam("RTRNURL") String rtrnurl) {

		PayPalExpressResponse paypalExpressResponse = new PayPalExpressResponse();
		PayPalECServlet payPalECServlet = new PayPalECServlet();
		ValidatePayPalMail ValidatePayPalMail = new ValidatePayPalMail();
		ParseECRequest parseECRequest = new ParseECRequest();
		PayPalECDto payPalECDto = null;
		Response serviceResponse = null;
		String redirectURL = null;

		// Logging - Incoming request
		System.out.println("ExpressCheckout WebService-Incoming request at : "
				+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
						.format(new Date()));
		System.out.println("Incoming Method:" + method);
		System.out.println("Request Information :" + request.getPathInfo());

		try {

			if (token == null) {

				validDetails = ValidatePayPalMail.isValidDetail(cancelURL,
						"CANCELURL");
			}

			// Validation, throws Validate Exception
			validMail = ValidatePayPalMail.isPayPalMail(clientId, payPalMail);

			if (validMail) {
				//Parsing incoming request
				if(rtrnurl == null){
					rtrnUrlflag = 1;
				}
				else{
				    rtrnUrlflag = parseECRequest.parseRtrnURL(rtrnurl);
				}

				// Call the Servlet method for API calls
				payPalECDto = payPalECServlet
						.myExpressCheckout(request, response, method, token,
								cancelURL, clientId, isSandBox,payPalMail, rtrnUrlflag);

				if (method != null && method.equals("SetExpressCheckout")) {
					redirectURL = payPalECDto.getRedirectUrl();
					System.out.println("redirectURL" + redirectURL);
					serviceResponse = Response.ok(redirectURL).build();
				} else if (method != null
						&& method.equals("CreateBillingAgreement")) {

					paypalExpressResponse.setBillingAgreementId(payPalECDto
							.getBillingAgreementID());
					paypalExpressResponse.setPayerId(payPalECDto.getPayerID());
					paypalExpressResponse
							.setMessage("Succesfull ExpressCheckout Operation");

					serviceResponse = Response.ok(paypalExpressResponse)
							.build();
				}
			}

		} catch (ValidateException ve) {

			// Log the error
			System.out.println("Validation Exception occured :"
					+ ve.getMessage() + " fieldname" + ve.geterror_field());
			ve.printStackTrace();
			// Responding with 401 status
			serviceResponse = Response.status(unauth)
					.entity("Validation Failure at" + ve.geterror_field())
					.build();

		} catch (ServiceException sre) {

			// Log the error
			System.out
					.println("Service Exception occured :" + sre.getMessage());
			sre.printStackTrace();
			// Responding with 401 status
			serviceResponse = Response.status(internalErr)
					.entity("Error at PayPal call").build();

		}catch (IOException e) {
			// Log the error
			System.out.println("IO Exception occured :" + e.getMessage());
			e.printStackTrace();
			serviceResponse = Response.status(internalErr)
					.entity("OOPS! Something went wrong").build();

		} catch (ServletException se) {
			// Log the error
			System.out.println("Servlet Exception occured :" + se.getMessage());
			se.printStackTrace();
			serviceResponse = Response.status(internalErr)
					.entity("OOPS! Something went wrong").build();

		} catch (Exception e) {
			System.out.println("UnKnown Exception occured :" + e.getMessage());
			e.printStackTrace();
			serviceResponse = Response.status(internalErr)
					.entity("OOPS! Something went wrong").build();
		}

		// Logging - Outgoing Response
		System.out.println("ExpressCheckout WebService-OutGoing request at : "
				+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
						.format(new Date()));
		System.out.println("Response Information :"
				+ serviceResponse.toString());
		return serviceResponse;
	}

}
