package com.timeinc.tcs.epayG.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.timeinc.tcs.epayG.beans.ECDirectOrphanBean;
import com.timeinc.tcs.epayG.client.PayPalECClient;
import com.timeinc.tcs.epayG.constants.PayPalECConstants;
import com.timeinc.tcs.epayG.dto.ECDirectData;
import com.timeinc.tcs.epayG.exception.ECConnectionException;
import com.timeinc.tcs.epayG.handler.PayPalECHandler;
import com.timeinc.tcs.epayG.helper.PayPalECHelper;
import com.timeinc.tcs.epayG.transaction.PayPalECTransaction;

public class PayPalECController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String verificationTOKEN = "cmd=_notify-validate&";
	private boolean isSandbox;
	private boolean isListenerFlag = false;
	private boolean IPNFlag = false;
	private boolean isBatchFlag = false;
	private String payloadRequest = null;
	private String[] verifyResponseArr = null;
	private String verifyResponse = null;
	private String verifyRequest = null;
	List<NameValuePair> requestparams;

	ECDirectData ecDirectData;
	PayPalECTransaction paypalECTransaction;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayPalECController() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			System.out
					.println("++++ Request Received for PayPal EC Direct ++++++ PAYPALECCONTROLLER ");

			String host = request.getHeader("host");
			System.out.println("Incoming from HOST :" + host);

			// Check for Sandbox/ Prod
			if (host.equalsIgnoreCase("wesqa.customersvc.com")
					|| host.equalsIgnoreCase("testsecure.customersvc.com")
					|| host.equalsIgnoreCase("wesqaprod.customersvc.com")
					|| host.equalsIgnoreCase("httpqc1.tcs.timeinc.com")
					|| host.equalsIgnoreCase("httpqa1.customersvc.com")
					|| host.equalsIgnoreCase("httptc3.customersvc.com")) {
				System.out.println("The request is a SANDBOX request");
				isSandbox = true;

			} else {
				System.out.println("The request is a PROD request");
				isSandbox = false;
			}
			/*
			 * if(host.equalsIgnoreCase("secure.customersvc.com") ||
			 * host.equalsIgnoreCase("httppc1.customersvc.com")
			 * ||host.equalsIgnoreCase("httppc2.customersvc.com")){
			 * System.out.println("The request is a PROD request"); isSandbox =
			 * false; }else if (host.equalsIgnoreCase("wesqa.customersvc.com")
			 * || host.equalsIgnoreCase("httpqc1.customersvc.com")){
			 * System.out.println("The request is a SANDBOX request"); isSandbox
			 * = true;
			 * 
			 * }
			 */

			// Check if the request is from IPN/Listener/WES
			/*
			 * System.out.println("List of Headers in the request :" +
			 * request.getHeaderNames());
			 * System.out.println("List of Params in the request :" +
			 * request.getParameterNames());
			 */
			/*
			 * if(null != request.getHeader("ISFROMLISTENER") && "" !=
			 * request.getHeader("ISFROMLISTENER") ){
			 * 
			 * 
			 * } // Call from WES/IPN else {
			 */
			if (null != request.getHeader("ISIPNCALL")
					&& "FALSE".equalsIgnoreCase(request.getHeader("ISIPNCALL"))) {
				System.out.println("Request from WES Received");
				IPNFlag = false;
				isListenerFlag = false;
				isBatchFlag = false;
			} else if (null != request.getParameter("ISFROMLISTENER")
					&& "TRUE".equalsIgnoreCase(request
							.getParameter("ISFROMLISTENER"))) {
				System.out.println("Request from Listener Received");
				IPNFlag = false;
				isListenerFlag = true;
				isBatchFlag = false;

			}else if(null != request.getHeader("ISFROMBATCH")
					&& "TRUE".equalsIgnoreCase(request
							.getHeader("ISFROMBATCH"))) { 
				System.out.println("Request from Daily Batch Received");
				IPNFlag = false;
				isListenerFlag = false;			
			    isBatchFlag = true;			
			}else {
				System.out.println("Request from IPN Received");
				IPNFlag = true;
				isListenerFlag = false;
				isBatchFlag = false;
				// Check if the request is SANDBOX or PROD
				if (isSandbox) {
					System.out.println("The request is a IPN SANDBOX request");
				} else {
					System.out.println("The request is a IPN PROD request");
				}
			}

			/* } */

         if(!isBatchFlag){
			if (!isListenerFlag) {

				if (!IPNFlag) {
					ecDirectData = PayPalECHelper.populateDataForRequest(
							request, isSandbox);

					// ecDirectData.setEcDirectTransName("PSA");
					ecDirectData = makeECDIrectCall(ecDirectData);

					ecDirectTransaction(ecDirectData);
				} else {

					// Stream in the incoming data
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("cmd", "_notify-validate"));

					requestparams = PayPalECHelper.getBody(request, response,
							params);
					ecDirectData = PayPalECHelper.populateDataForRequest(
							requestparams, isSandbox);
					// Make a POST Back adding the NVP for verification

					verifyRequest = verificationTOKEN + payloadRequest;
					System.out.println("The Verification String for IPN"
							+ verifyRequest);
					System.out.println("The Verification NVP for IPN"
							+ requestparams);

					verifyResponse = PayPalECClient.makeECIPNCall(
							requestparams, isSandbox);
					// verifyResponse =
					// PayPalECHelper.handlePayPalECResponse(verifyResponseArr);

					// Check if the Message is Verified
					if (null != verifyResponse && "" != verifyResponse) {
						if (PayPalECConstants.IPN_VERIFIED
								.equalsIgnoreCase(verifyResponse)) {
							System.out
									.println("Succesful Verification of IPN post by PayPal");
							// Take initial payload and then get TXN_ID and
							// Payment Status
							ecDirectData = PayPalECHandler
									.handleIPNDataRequest(ecDirectData,
											PayPalECConstants.IPN_VERIFIED);
							if (ecDirectData.getIsTransEntry()) {
								System.out
										.println("EC Controller : Verified IPN message going for Transaction");
								ecDirectTransaction(ecDirectData);

							}
							response.setStatus(200);
							System.out
									.println("Dispatched PayPal EC Direct to IPN response set to 200");
						}

						else if (PayPalECConstants.IPN_INVALID
								.equalsIgnoreCase(verifyResponse)) {
							System.out
									.println("Invalid Verification of IPN post by PayPal");
							// PayPalECHandler.handleIPNDataRequest(ecDirectData,
							// PayPalECConstants.IPN_INVALID);
							response.setStatus(503);
							System.out
									.println("Dispatched PayPal EC Direct to IPN INAVLID response set to 503 - Bad request");

						}
						// ELSE IF ORPHAN TRANSACTIONS,UPDATE New table
						// ECDIRECT_ORPHAN
						else {
							System.out
									.println("Received Not Valid Junk String as verification"
											+ verifyResponse);
							// PayPalECHandler.handleIPNDataRequest(ecDirectData,
							// PayPalECConstants.IPN_JUNK);
							response.setStatus(503);
							System.out
									.println("Dispatched PayPal EC Direct to IPN JUNK response set to 503 - Bad request");
						}
					}
					// ELSE IF ORPHAN TRANSACTIONS,UPDATE New table
					// ECDIRECT_ORPHAN
					else {
						System.out
								.println("Received Null or Spaces as verification");
						response.setStatus(503);
						System.out
								.println("Dispatched PayPal EC Direct to IPN NULL/SPACES response set to 503 - Bad request");
					}
				}
			}
			// Listener Call Processing
			else {
				System.out
						.println("PayPal EC Controller calling the Listener Process Method");
				ecDirectData = PayPalECHelper.populateDataForRequest(request,
						isSandbox);
				ecDirectData.setIsListenerCall(true);
				ecDirectData = makeECDIrectCall(ecDirectData);
				ecDirectTransaction(ecDirectData);
			}
         }
         // Batch Processing	
         else {
        	 System.out
				.println("PayPal EC Controller calling the Batch Process Method");
        	 handleBatchProcess(isSandbox);
        	 
         }
        
		} catch (Exception e) {
			System.out.println("Exception caught at ECDirectController.doPost "
					+ e.getMessage());
		}
	}

	public void ecDirectTransaction(ECDirectData ecDirectData) {
		boolean isRefundTrans = false;
		paypalECTransaction = new PayPalECTransaction();

		try {

			if (null != ecDirectData.getEcDirectTransName()
					&& "" != ecDirectData.getEcDirectTransName()
					&& "RFD".equalsIgnoreCase(ecDirectData
							.getEcDirectTransName())) {
				System.out
						.println("EC Handler recognized the Transaction as RFD");
				isRefundTrans = true;
			}

			if (!ecDirectData.getIsOrphanEntry()) {
				if(isBatchFlag){
					
					ecDirectData.setBatchCall(true);
				}
				// NON RFD Code
				if (!isRefundTrans) {
					if ((PayPalECConstants.PAYMENT_COMPLETED)
							.equalsIgnoreCase(ecDirectData.getPaymentStatus())) {

						ecDirectData.setEcDirectTransName("PSA");
						if (IPNFlag || isBatchFlag) {
							PayPalECHandler
									.handleUpdateDatabaseCall(ecDirectData);
							if(isBatchFlag){
								PayPalECHandler
								.handleInsertDatabaseCall(ecDirectData);								
							}
						} else {
							PayPalECHandler
									.handleInsertDatabaseCall(ecDirectData);
						}
						paypalECTransaction.processTransaction(ecDirectData,
								"PSA");

					} else if ((PayPalECConstants.PAYMENT_INPROGESS)
							.equalsIgnoreCase(ecDirectData.getPaymentStatus())
							|| (PayPalECConstants.PAYMENT_PENDING)
									.equalsIgnoreCase(ecDirectData
											.getPaymentStatus())) {

						ecDirectData.setEcDirectTransName("PSA");
						System.out
								.println("Pending Reason at Do Ref trans is :"
										+ ecDirectData.getPendingReason());

						if (IPNFlag || isBatchFlag) {
							PayPalECHandler
									.handleUpdateDatabaseCall(ecDirectData);
							if(isBatchFlag){
								PayPalECHandler
								.handleInsertDatabaseCall(ecDirectData);								
							}							
						} else {
							PayPalECHandler
									.handleInsertDatabaseCall(ecDirectData);
						}

						// Testing code for Transacion Layer
						/*
						 * ecDirectData.setEcDirectReceiptID("TEST RECEIPT IDR ")
						 * ; if(null != ecDirectData.getEcDirectAmt() || "" !=
						 * ecDirectData.getEcDirectAmt() ){
						 * System.out.println("Inside Test Trans code"); } else{
						 * ecDirectData.setEcDirectAmt("1299"); }
						 * System.out.println
						 * ("EC Controller : CALLING TRANSACTION EVEN FOR PENDING"
						 * );
						 * paypalECTransaction.processTransaction(ecDirectData,
						 * "PSA");
						 */
						// ENd of Test Code for Transaction

					} else if ((PayPalECConstants.PAYMENT_FAILED)
							.equalsIgnoreCase(ecDirectData.getPaymentStatus())
							|| (PayPalECConstants.PAYMENT_DENIED)
									.equalsIgnoreCase(ecDirectData
											.getPaymentStatus())
							|| (PayPalECConstants.PAYMENT_VOIDED)
									.equalsIgnoreCase(ecDirectData
											.getPaymentStatus())) {

						ecDirectData.setEcDirectTransName("TRB");

						if (IPNFlag || isBatchFlag) {
							PayPalECHandler
									.handleUpdateDatabaseCall(ecDirectData);
							if(isBatchFlag){
								PayPalECHandler
								.handleInsertDatabaseCall(ecDirectData);								
							}								
							
						} else {
							PayPalECHandler
									.handleInsertDatabaseCall(ecDirectData);
						}
						paypalECTransaction.processTransaction(ecDirectData,
								"TRB");

						System.out
								.println("Do Ref trans is denied/failed/voided :"
										+ ecDirectData.getPaymentStatus());
					}
					// Code for other possible Payment Statuses
					else {
						System.out.println("Status at Do Ref trans is :"
								+ ecDirectData.getPaymentStatus());
						//if isBatchFlag true  and isTransDone 
						// is Y and if orphan Entry is true
						//update orphan table
						//do TRB
						if (isBatchFlag
								&& ecDirectData.getTransDone()
										.equalsIgnoreCase("Y")
								&& ecDirectData.getIsOrphanEntry()) {
							PayPalECHandler
									.handleUpdateDatabaseCall(ecDirectData);
							ecDirectData.setEcDirectTransName("TRB");
							paypalECTransaction.processTransaction(
									ecDirectData, "TRB");
						}
					}

				}
				// Listener Code
				else {
					System.out
							.println("Starting of REFUND Transaction in Controller");
					ecDirectData.setEcDirectTransName("RFD");

					if (PayPalECConstants.REFUNDINSTANT
							.equalsIgnoreCase(ecDirectData.getRefundStatus())) {
						// ecDirectData.setEcDirectTransName("RFD");
						if (IPNFlag || isBatchFlag) {
							PayPalECHandler
									.handleUpdateDatabaseCall(ecDirectData);
							if(isBatchFlag){
								PayPalECHandler
								.handleInsertDatabaseCall(ecDirectData);								
							}								
						} else {
							PayPalECHandler
									.handleInsertDatabaseCall(ecDirectData);
						}

						System.out
								.println("REFUNDTRANS is completed, going for RFD Trans with SMN ");
						paypalECTransaction.processTransaction(ecDirectData,
								"RFD");
					} else if (PayPalECConstants.REFUNDDELAYED
							.equalsIgnoreCase(ecDirectData.getRefundStatus())) {
						if(isBatchFlag){
							PayPalECHandler
							.handleUpdateDatabaseCall(ecDirectData);							
						}
						PayPalECHandler.handleInsertDatabaseCall(ecDirectData);
						System.out.println("REFUNDTRANS is delayed");

					}

				}
			} else {
                 // These Orphan transactions should be updated with a flag for ISTRANSDONE coloumn
			   if(!isBatchFlag){
				if (!isRefundTrans) {

					//Handling 10201 return code
					System.out.println("Controller : API RC Code :" + ecDirectData.getEcDirect_API_RC() + "Length :" + ecDirectData.getEcDirect_API_RC().length());
					if("10201".equalsIgnoreCase(ecDirectData.getEcDirect_API_RC().trim())){
						ecDirectData.setEcDirectTransName("TRB");
						ecDirectData.setTransDone("Y");
						System.out
						.println("Do Ref trans is errored with 10201 :"
								+ ecDirectData.getPaymentStatus());		
						PayPalECHandler.handleInsertDatabaseCall(ecDirectData);
						paypalECTransaction.processTransaction(ecDirectData,
								"TRB");
						
					}
					else{
					ecDirectData.setEcDirectTransName("PSA");
					
					PayPalECHandler.handleInsertDatabaseCall(ecDirectData);
					//paypalECTransaction.processTransaction(ecDirectData, "TRB");
					System.out
							.println("Do Ref trans is errored/no Trans ID Found :"
									+ ecDirectData.getPaymentStatus());
					}
				} else {

					PayPalECHandler.handleInsertDatabaseCall(ecDirectData);
					// paypalECTransaction.processTransaction(ecDirectData,
					// "TRB");
					System.out
							.println("Refund trans is errored/no Trans ID Found :"
									+ ecDirectData.getPaymentStatus());

				}
			   }
			   // retry batch failure cases
			   else{
				   if(ecDirectData.getTransDone().equalsIgnoreCase("Y")) {
					   PayPalECHandler.handleUpdateDatabaseCall(ecDirectData);
					   paypalECTransaction.processTransaction(ecDirectData,
								"TRB");
				   } else {
					   PayPalECHandler.handleUpdateDatabaseCall(ecDirectData);
				   }
			   }
			   
			   
			}

		} catch (Exception e) {
			System.out.println("Exception caught at ECDirectController.doPost "
					+ e.getMessage());
		}

	}

	/**
	 * Method to make the Call.
	 * 
	 * @param ecDirectData
	 */
	public ECDirectData makeECDIrectCall(ECDirectData ecDirectData) {

		try {

			ecDirectData = PayPalECHandler.handleECDirectCall(ecDirectData);
			System.out.println("Response from EC Direct :"
					+ ecDirectData.getPaymentStatus() + " Reason :"
					+ ecDirectData.getPendingReason());

			if (null == ecDirectData.getEcDirectAmt()
					|| "" == ecDirectData.getEcDirectAmt()) {
				if (null != ecDirectData.getDollarValue()
						|| "" != ecDirectData.getDollarValue()) {

					System.out
							.println("Setting Dollar Amount to EC Direct Amount");
					ecDirectData.setEcDirectAmt(ecDirectData.getDollarValue()
							.replace(".", ""));
				}

			}
			ecDirectData.getEcDirectAmt().replace(".", "");

		} catch (ECConnectionException e) {
			// Need to update the ISTRANSDONE flag and no transaction
			//Entry into the Orphan table.
			ecDirectData.setIsOrphanEntry(true);
			PayPalECHandler.handleInsertDatabaseCall(ecDirectData);
			
			e.printStackTrace();
			
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ecDirectData;
	}
	
	// Method which handles all batch calls
	public void handleBatchProcess(boolean isSandBox){
		
		try{
			
		ArrayList<ECDirectOrphanBean> orphanList;
		// Start retrieving all the records from DB2
		orphanList = PayPalECHandler.handleGetECOrphanDatabaseCall();
				
		// Iterate through list
		for(ECDirectOrphanBean ecDOrphan : orphanList){
		  // Build ECDirect
			ECDirectData ecDirectData = PayPalECHelper.populateDataForRequest(ecDOrphan);
			ecDirectData.setBatchCall(true);
			if(isSandBox){
				ecDirectData.setIsTestEnv(true);
			}
			else{
				ecDirectData.setIsTestEnv(false);
			}
		  // Call the  makeECDIrectCall method
			ecDirectData = makeECDIrectCall(ecDirectData);
		  // On errors, leave the record
		  // On Transaction ID presence
			
			ecDirectTransaction(ecDirectData);
			
		}
		System.out.println("EC Direct Settlement - End of loop - Batch Orphan Records");
		}catch(Exception e){
			
			System.out.println("Exception Occured ate Batch Process : Controller Handle Batch method");
		}
		
	}
	
}
