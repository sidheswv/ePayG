package com.timeinc.tcs.epayG.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timeinc.tcs.epayG.constants.PayPalECConstants;
import com.timeinc.tcs.epayG.dto.PayPalECDto;
import com.timeinc.tcs.epayG.exception.ServiceException;
import com.timeinc.tcs.epayG.handler.PayPalECHandler;
import com.timeinc.tcs.epayG.messages.PayPalExpressResponse;

/**
 * 
 * @author bilgin
 *
 * Modified By Lakshmi N Poduri for 022037 Project - Added logic for Sandbox flag, Exception building
 *
 */


public class PayPalECServlet {

	private static final long serialVersionUID = 1L;
	private PayPalECDto payPalECDto = new PayPalECDto();

	public PayPalECDto myExpressCheckout(HttpServletRequest request,
			HttpServletResponse response, String method, String token,
			String cancelURL, String clientId,String isSandBox,String payPalMail,int rtrnUrlflag) throws ServiceException,IOException, ServletException, Exception {


		PayPalECHandler payPalECHandler = new PayPalECHandler();

		if (method != null && method.equals("SetExpressCheckout")) {
			payPalECDto = payPalECHandler.handleSetECCall(request, cancelURL,clientId,isSandBox,payPalMail,rtrnUrlflag);

			if (payPalECDto != null
					&& payPalECDto.getRedirectUrl() != null
					&& !(PayPalECConstants.EMPTY_STRING)
							.equalsIgnoreCase(payPalECDto.getRedirectUrl())) {

			} else {
				System.out.println("Error with DTO payPalECDto :" + payPalECDto.toString());
				throw new ServiceException("Error at payPal API Calls");
			}

		} else if (method != null && method.equals("CreateBillingAgreement")) {
			payPalECDto.setToken(token);
			String billingAgreementID = payPalECHandler
					.handleCreateBillingAgreementCall(request,payPalECDto.getToken(),clientId,isSandBox,payPalMail,rtrnUrlflag);
			System.out.println("The Billing Agrrement ID : "
					+ billingAgreementID);
			payPalECDto.setBillingAgreementID(billingAgreementID);

			String payerID = payPalECHandler.handleGetECDetailsCall(request,payPalECDto
					.getToken(),clientId,isSandBox,payPalMail,rtrnUrlflag);
			System.out.println("The PayerID : " + payerID);
			payPalECDto.setPayerID(payerID);

		}

		return payPalECDto;
	}
	

}
