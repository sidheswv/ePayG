package com.timeinc.tcs.epayG.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timeinc.tcs.epayG.beans.ECDirectBean;
import com.timeinc.tcs.epayG.constants.PayPalECConstants;
import com.timeinc.tcs.epayG.controller.PayPalECController;
import com.timeinc.tcs.epayG.dto.ECDirectData;
import com.timeinc.tcs.epayG.handler.PayPalECHandler;
import com.timeinc.tcs.epayG.helper.PayPalECHelper;
import com.timeinc.tcs.epayG.transaction.PayPalECTransaction;

public class PayPalECIPN extends HttpServlet{
	
	
/**
	 * 
	 */


private static final long serialVersionUID = 1L;
	
	ECDirectData ecDirectData ;
	ECDirectBean ecDirectBean ;
	PayPalECTransaction paypalECTransaction;
	PayPalECController payPalECController;
	String transactonName;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayPalECIPN() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// START OLD CODE
/*			// The IPN Authorization process
			
	        // END OF of IPN Authorization process	
			System.out.println("Incoming Call from PayPal EC Direct to IPN" + request );
			System.out.println("PayPal EC Direct to IPN PAYERID" + request.getParameter("payer_id") );
			System.out.println("EC Direct to IPN payment_status" + request.getParameter("payment_status") );
			System.out.println("EC Direct to IPN txn_id" + request.getParameter("txn_id")  );
			ecDirectData = PayPalECHelper
					.populateDataForRequest(request);

			// Get the Record with Transaction ID
			ecDirectBean = PayPalECHandler.handleGetDatabaseCall(ecDirectData);
			//If Trans ID is present
			if(null != ecDirectBean.getEcDirectTransID() && "" != ecDirectBean.getEcDirectTransID()){
				//Check for existing status, 
				if(ecDirectBean.getPaymentStatus().equalsIgnoreCase(ecDirectData.getPaymentStatus())){
					//transactonName = ecDirectBean.getEcDIrectTransName();
					System.out.println("No CHange in status of Transaction : " + ecDirectBean.getEcDirectTransID());
				}
				else{
					//Change in Status of Transaction
					System.out.println("Change in status of Transaction : " + ecDirectBean.getEcDirectTransID());
					ecDirectData.setIsIPNCall(true);
					payPalECController.ecDirectTransaction(ecDirectData);
					
				}
			
				
			}
			//Transaction ID is not present in Database
			else{
				
				System.out.println("The Transaction ID is not present in TBV_ECDIRECT_TRANS Table");
				
			}
			
			
			
			
			//payPalECController.ecDirectTransaction(ecDirectData);
			 * 
			 END OF OLD CODE
			 */
			
			
			// Create HTTP Request and add the flag that it is from IPN
			System.out.println("Incoming Call from PayPal EC Direct to IPN" + request );
			System.out.println("PayPal EC Direct to IPN PAYERID" + request.getParameter("payer_id") );
			System.out.println("EC Direct to IPN payment_status" + request.getParameter("payment_status") );
			System.out.println("EC Direct to IPN txn_id" + request.getParameter("txn_id")  );			
			   
			//request.setAttribute("ISIPNCALL","True");
			// Dipatch it to Controller
			RequestDispatcher dispatcher = request.getRequestDispatcher("PayPalECController");
			dispatcher.forward(request, response);
			//System.out.println("Dispatched PayPal EC Direct to IPN to COntroller");			
			// return a 200 response code
			//response.setStatus(200);
			//System.out.println("Dispatched PayPal EC Direct to IPN response set to 200");	
			return;
			
		} catch (Exception e) {
			System.out.println("Exception caught at ECDirect IPN.doPost "
					+ e.getMessage());
		}
	}
	

	

	

}
