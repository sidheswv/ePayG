/**
 * 
 */
package com.timeinc.tcs.epayG.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.timeinc.tcs.epayG.beans.ECDirectBean;

import com.timeinc.tcs.epayG.beans.ECDirectOrphanBean;
import com.timeinc.tcs.epayG.client.PayPalECClient;
import com.timeinc.tcs.epayG.constants.PayPalECConstants;
import com.timeinc.tcs.epayG.dto.ECDirectData;
import com.timeinc.tcs.epayG.dto.PayPalECDto;
import com.timeinc.tcs.epayG.exception.ECConnectionException;
import com.timeinc.tcs.epayG.exception.ServiceException;
import com.timeinc.tcs.epayG.helper.PayPalECHelper;
import com.timeinc.tcs.epayG.model.DBAccess;

/**
 * 
 * @author bilgin
 *
 * Modified By Lakshmi N Poduri for 022037 Project - Added logic for Sandbox flag, Exception building
 * Modified By Lakshmi N poduri for 022037 project - Added flag for returnurl selection for pagemanager magazines
 *
 */
public class PayPalECHandler {
	
	public PayPalECDto handleSetECCall(HttpServletRequest request, String cancelURL,String clientId, String isSandBox,String payPalMail, int rtrnUrlflag ) throws ServiceException,IOException,Exception{
		
		PayPalECDto payPalECDto = new PayPalECDto();
		String redirectURL = "";
		String correlationID = "";
		boolean isSandboxHost = true;
		if(isSandBox.equalsIgnoreCase("false")){
		
			isSandboxHost = false;
		}
		
		
		PayPalECHelper payPalECHelper = new PayPalECHelper();
		Map<String, String> setECResMap = new HashMap<String, String>();
		
		// Create the request for SetExpressCheckout API call
		List<NameValuePair> paypalECRequest;
		try {
			paypalECRequest = payPalECHelper.createSetECRequest(request,cancelURL,clientId,isSandboxHost,payPalMail,rtrnUrlflag);
			// Make the API call
			String[] httpRespArr = PayPalECClient.makeECCall(paypalECRequest, isSandboxHost);
			// Create the response from the API call
			setECResMap = payPalECHelper.handlePayPalECResponse(httpRespArr, PayPalECConstants.SETEXPRESSCHEKOUT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// Logic to decide what to do with the response
		if(setECResMap != null && setECResMap.size() > 0) {
			if((PayPalECConstants.SUCCCESS).equalsIgnoreCase(setECResMap.get(PayPalECConstants.ACKNOWLEDGEMENT))) {
				if(setECResMap.get(PayPalECConstants.TOKEN) != null) {
					correlationID = setECResMap.get(PayPalECConstants.CORRELATIONID);
					if(isSandboxHost) {
						redirectURL = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+setECResMap.get(PayPalECConstants.TOKEN);
					} else {
						redirectURL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+setECResMap.get(PayPalECConstants.TOKEN);
					}
				}  // Since the SetExpressCheckout responds with ACK=SUCCESS then the response must contain TOKEN value
			} else {
				// In case of SetExpressCheckout failure ie., ACK=FAILURE, do not redirect the user to Paypal. Retain him in the current page. 
				   System.out.println("Error at paypal api calls, Didnot get Token :" + PayPalECConstants.SETEXPRESSCHEKOUT);
                   throw new ServiceException("Error at API calls to PAYPAL");
			}
		} else {
			// In case of HTTP status code not being 200, do not redirect the user to Paypal. Retain him in the current page.
			System.out.println("Error at paypal api calls :" + PayPalECConstants.SETEXPRESSCHEKOUT + "response object error :" + setECResMap.toString());
			throw new ServiceException("Error at API calls to PAYPAL");
		}
		
		System.out.println("Captured Correlation ID at  SETEXPRESSCHECKOUTDETAILS :" + correlationID);
		payPalECDto.setRedirectUrl(redirectURL);
		payPalECDto.setToken(setECResMap.get(PayPalECConstants.TOKEN));
		
		return payPalECDto;
	}
	
	
	
	
	public String handleGetECDetailsCall(HttpServletRequest request,String token, String clientId,String isSandBox,String payPalMail,int rtrnUrlflag) throws ServiceException, IOException, Exception{
		
		boolean isSandboxHost = true;
		if(isSandBox.equalsIgnoreCase("false")){
			
			isSandboxHost = false;
		}
		
		String payerID = "";
		String correlationID = "";
		PayPalECHelper payPalECHelper = new PayPalECHelper();
		Map<String, String> getECDetailsMap = new HashMap<String, String>();
		
		// Create the request for GetExpressCheckout API call
		List<NameValuePair> paypalECRequest;
		try {
			paypalECRequest = payPalECHelper.createGetECDetailsRequest(request,token,clientId,isSandboxHost,payPalMail,rtrnUrlflag);
			// Make the API call
			String[] httpRespArr = PayPalECClient.makeECCall(paypalECRequest, isSandboxHost);
			// Create the response from the API call
			getECDetailsMap = payPalECHelper.handlePayPalECResponse(httpRespArr, PayPalECConstants.GETEXPRESSCHECKOUTDETAILS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		
		// Logic to decide what to do with the response
		if(getECDetailsMap != null && getECDetailsMap.size() > 0) {
			if((PayPalECConstants.SUCCCESS).equalsIgnoreCase(getECDetailsMap.get(PayPalECConstants.ACKNOWLEDGEMENT))) {
				if(getECDetailsMap.get(PayPalECConstants.PAYERID) != null) {
					payerID = getECDetailsMap.get(PayPalECConstants.PAYERID);
					correlationID = getECDetailsMap.get(PayPalECConstants.CORRELATIONID);
				}  // Since the GetExpressCheckout responds with ACK=SUCCESS then the response must contain PAYERID value
			} else {
				// In case of GetExpressCheckout failure ie., ACK=FAILURE
 				payerID = null;
				System.out.println("Error at paypal api calls :" + PayPalECConstants.GETEXPRESSCHECKOUTDETAILS + ": payerID :" + payerID);
				throw new ServiceException("Error at API calls to PAYPAL");
			}
		} else {
			// In case of HTTP status code not being 200
			payerID = null;
			System.out.println("Error at paypal api calls :" + PayPalECConstants.GETEXPRESSCHECKOUTDETAILS +":payerID:"+ "response object error :" + getECDetailsMap.toString());
			throw new ServiceException("Error at API calls to PAYPAL");
		}
		System.out.println("Captured Correlation ID at  GETEXPRESSCHECKOUTDETAILS :" + correlationID);
		return payerID;
	}
	
	public String handleCreateBillingAgreementCall(HttpServletRequest request,String token,String clientId, String isSandBox,String payPalMail,int rtrnUrlflag) throws ServiceException, IOException, Exception {
		boolean isSandboxHost = true;
		if(isSandBox.equalsIgnoreCase("false")){
			
			isSandboxHost = false;
		}
	
		String billingAgreementID = "";
		String correlationID = "";
		PayPalECHelper payPalECHelper = new PayPalECHelper();
		Map<String, String> billingAgrementMap = new HashMap<String, String>();
		
		// Create the request for CreateBillingAgreement API call
		List<NameValuePair> paypalECRequest;
		try {
			paypalECRequest = payPalECHelper.createBillingAgreementRequest(request,token,clientId,isSandboxHost,payPalMail,rtrnUrlflag);
			// Make the API call
			String[] httpRespArr = PayPalECClient.makeECCall(paypalECRequest, isSandboxHost);
			// Create the response from the API call
			billingAgrementMap = payPalECHelper.handlePayPalECResponse(httpRespArr, PayPalECConstants.CREATEBILLINGAGREEMENT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		
		// Logic to decide what to do with the response
		if(billingAgrementMap != null && billingAgrementMap.size() > 0) {
			if((PayPalECConstants.SUCCCESS).equalsIgnoreCase(billingAgrementMap.get(PayPalECConstants.ACKNOWLEDGEMENT))) {
				if(billingAgrementMap.get(PayPalECConstants.BILLINGAGREEMENTID) != null) {
					billingAgreementID = billingAgrementMap.get(PayPalECConstants.BILLINGAGREEMENTID);
					correlationID = billingAgrementMap.get(PayPalECConstants.CORRELATIONID);
				}  // Since the CreateBillingAgreement responds with ACK=SUCCESS then the response must contain BILLINGAGREEMENTID value
			} else {
				// In case of CreateBillingAgreement failure ie., ACK=FAILURE
				billingAgreementID = null;
				System.out.println("Error at paypal api calls :" + PayPalECConstants.GETEXPRESSCHECKOUTDETAILS + ": billingAgreementID :" + billingAgreementID);
				throw new ServiceException("Error at API calls to PAYPAL");
			}
		} else {
			// In case of HTTP status code not being 200
			billingAgreementID = null;
			System.out.println("Error at paypal api calls :" + PayPalECConstants.GETEXPRESSCHECKOUTDETAILS +":billingAgreementID:"+ "response object error :" + billingAgrementMap.toString());
			throw new ServiceException("Error at API calls to PAYPAL");
		}
		System.out.println("Captured Correlation ID at CREATEBILLINGAGREEMENT :" + correlationID);
		return billingAgreementID;
	}
	
	public static ECDirectData handleECDirectCall(ECDirectData ecDirectData) throws Exception {
	
		boolean isSandboxHost = false;
		boolean isRefundTrans = false;
		String correlationID = "";
		
		if(ecDirectData.getIsTestEnv()){
			System.out.println("SANDBOX is true at Hanlder class");
			isSandboxHost = true;
		}
		
		if(null != ecDirectData.getEcDirectTransName() 
				&& "" != ecDirectData.getEcDirectTransName()
				       && "RFD".equalsIgnoreCase(ecDirectData.getEcDirectTransName())){
            System.out.println("EC Handler recognized the Transaction as RFD");
			isRefundTrans = true;
		}
	
		PayPalECHelper payPalECHelper = new PayPalECHelper();
		Map<String, String> ecDirectCapturetMap = new HashMap<String, String>();
		
		// Create the request for CreateBillingAgreement API call
		List<NameValuePair> paypalECRequest;
		try {
			if(!isRefundTrans){
			paypalECRequest = payPalECHelper.ecDirectCapture(ecDirectData,isSandboxHost);
			}
			else{
				
				paypalECRequest = payPalECHelper.ecDirectRefund(ecDirectData,isSandboxHost);
			}
			// Make the API call
			String[] httpRespArr = PayPalECClient.makeECCall(paypalECRequest, isSandboxHost);
			// Create the response from the API call
			if(!isRefundTrans){
			ecDirectCapturetMap = payPalECHelper.handlePayPalECResponse(httpRespArr, PayPalECConstants.DOREFERENCETRANSACTION);
			}
			else{
				ecDirectCapturetMap = payPalECHelper.handlePayPalECResponse(httpRespArr, PayPalECConstants.REFUNDTRANSACTION);
			}
		} catch (ECConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}  
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		// Logic to decide what to do with the response
		if(ecDirectCapturetMap != null && ecDirectCapturetMap.size() > 0) {
			if((PayPalECConstants.SUCCCESS).equalsIgnoreCase(ecDirectCapturetMap.get(PayPalECConstants.ACKNOWLEDGEMENT))) {
				
     			
				//ecDirectData.setEcDirect_API_RC(ecDirectCapturetMap.get(PayPalECConstants.PAYPAL_API_RC).trim());
     			//System.out.println("Handler : Caught PayPal Api RC is :" + ecDirectData.getEcDirect_API_RC());
				correlationID = ecDirectCapturetMap.get(PayPalECConstants.CORRELATIONID);
				ecDirectData.setEcDirectCorrID(correlationID);
				if(!isRefundTrans){
				// DO REFERENCE TRANSACTION CALL
				System.out.println("DOREFTRANS STATUS from call :" + ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS));
				
				//Succesfull Transactions
				if((PayPalECConstants.PAYMENT_COMPLETED).equalsIgnoreCase(ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS))){
					ecDirectData.setPaymentStatus(ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS));
				
				if(ecDirectCapturetMap.get(PayPalECConstants.TRANSACTIONID) != null) {
					ecDirectData.setEcDirectTransID(ecDirectCapturetMap.get(PayPalECConstants.TRANSACTIONID));
					System.out.println("Transaction ID is :" + ecDirectData.getEcDirectTransID());
					ecDirectData.setIsTransEntry(true);
					ecDirectData.setTransDone("Y");
					// Set the IS Trans done flag as Y
					
				} 
				else{
					System.out.println("Transaction ID is Not present in Completed Payment:" + ecDirectData.getEcDirectTransID());
					ecDirectData.setIsOrphanEntry(true);
					PayPalECHelper.setTransactionValue(ecDirectData);
					// Verify the flag 
					 // if 6 , directly set it as Y
					// If les than 6, bump the value
					
				}
					
			
				if(ecDirectCapturetMap.get(PayPalECConstants.RECEIPTID) != null) {
					ecDirectData.setEcDirectReceiptID(ecDirectCapturetMap.get(PayPalECConstants.RECEIPTID));
					System.out.println("Receipt ID is :" + ecDirectData.getEcDirectReceiptID());
				}
				if(ecDirectCapturetMap.get(PayPalECConstants.AMOUNT) != null) {
					String dirAmt = ecDirectCapturetMap.get(PayPalECConstants.AMOUNT);
					//dirAmt = dirAmt.replace(".", "");
					ecDirectData.setEcDirectAmt( dirAmt.replace(".", ""));
					System.out.println("EC Direct Amount is :" + ecDirectData.getEcDirectAmt());
				}
			
				}
				//Pending Transactions
				else if((PayPalECConstants.PAYMENT_INPROGESS).equalsIgnoreCase(ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS)) ||
						(PayPalECConstants.PAYMENT_PENDING).equalsIgnoreCase(ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS))){
					
					          if(ecDirectCapturetMap.get(PayPalECConstants.TRANSACTIONID) != null) {
						          ecDirectData.setEcDirectTransID(ecDirectCapturetMap.get(PayPalECConstants.TRANSACTIONID));
						          System.out.println("Transaction ID of PENDING is :" + ecDirectData.getEcDirectTransID());
						          ecDirectData.setIsTransEntry(true);
						          ecDirectData.setTransDone("Y");
						       // Set the IS Trans done flag as Y
					           }
					          else{
					        	  System.out.println("Transaction ID of PENDING is NOT Received :" + ecDirectData.getEcDirectTransID());  
					        	  ecDirectData.setIsOrphanEntry(true);
					        	  PayPalECHelper.setTransactionValue(ecDirectData);
					        	// Verify the flag 
									 // if 6 , directly set it as Y
					        	     // If less than 6 bump the value
					          }
					
					      ecDirectData.setPaymentStatus(ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS));
					      //Get the Pending Reasson
					      if((PayPalECConstants.PAYMENT_PENDING).equalsIgnoreCase(ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS))){
					    	  
					    	  ecDirectData.setPendingReason(ecDirectCapturetMap.get(PayPalECConstants.PENDINGREASON));
					    	  System.out.println("PENDING REASON is:" + ecDirectData.getPendingReason()); 
					    	  
					      }
					      
					      System.out.println("DOREFTRANS STATUS :" + ecDirectData.getPaymentStatus());
				}else {
					
					  ecDirectData.setPaymentStatus(ecDirectCapturetMap.get(PayPalECConstants.PAYMENTSTATUS));
				      System.out.println("DOREFTRANS STATUS NO PROPER RESPONSE:" + ecDirectData.getPaymentStatus());
				      // Do orphan Entry
				      ecDirectData.setIsOrphanEntry(true);
				      PayPalECHelper.setTransactionValue(ecDirectData);
			        	// Verify the flag 
							 // if 6 , directly set it as Y
			        	     // If less than 6 bump the value
				}

			}
				// REFUND TRANSACTION
				else{
					System.out.println("Capturing REFUND TRANS Response");
					System.out.println("REFUNDTRANS STATUS from call :" + ecDirectCapturetMap.get(PayPalECConstants.REFUNDSTATUS));
					
					if(null != ecDirectCapturetMap.get(PayPalECConstants.REFUNDTRANSACTIONID) && "" != ecDirectCapturetMap.get(PayPalECConstants.REFUNDTRANSACTIONID)){
						//Setting the TransID as Parent ID
						ecDirectData.setEcDirectParentTransID(ecDirectData.getEcDirectTransID());
						ecDirectData.setEcDirectTransID(ecDirectCapturetMap.get(PayPalECConstants.REFUNDTRANSACTIONID));
						ecDirectData.setIsTransEntry(true);
						ecDirectData.setTransDone("Y");
						// Set the IS Trans done flag as Y
						
					  if((PayPalECConstants.REFUNDINSTANT).equalsIgnoreCase(ecDirectCapturetMap.get(PayPalECConstants.REFUNDSTATUS))){
						
						  ecDirectData.setEcDirectAmt(ecDirectCapturetMap.get(PayPalECConstants.GROSSREFUNDAMT));
						  ecDirectData.setRefundStatus(PayPalECConstants.REFUNDINSTANT);
						  System.out.println("TransaID for Instant REFUND is : " + ecDirectData.getEcDirectTransID());
						  
						  
					  }
					  // Code has to be added for batch processing and verify if teh transaction is pending for finite time.
					  //Then set the record for NWC transaction
					  else if((PayPalECConstants.REFUNDDELAYED).equalsIgnoreCase(ecDirectCapturetMap.get(PayPalECConstants.REFUNDSTATUS))){
						
						ecDirectData.setPendingReason(ecDirectCapturetMap.get(PayPalECConstants.PENDINGREASON));
						ecDirectData.setRefundStatus(PayPalECConstants.REFUNDDELAYED);
						System.out.println("TransaID for Delayed REFUND is : " + ecDirectData.getEcDirectTransID());
						
					  }
					else{
						// Other cases for Refund transaction responses
						// has to be checked for  reject and do a NWC transaction
						
					}
				}else{
						
						System.out.println("REFUND TRANS STATUS NOT RECEIVED");
						ecDirectData.setIsOrphanEntry(true);
						PayPalECHelper.setTransactionValue(ecDirectData);
			        	// Verify the flag 
							 // if 6 , directly set it as Y
			        	     // If less than 6 bump the value
						//Do NWC
						
					}
					
				}
			
			} else {
     			System.out.println("Error at DO REF API call :" + PayPalECConstants.DOREFERENCETRANSACTION + ": ecDirectData :" + ecDirectData.toString());
     			ecDirectData.setEcDirect_API_RC(ecDirectCapturetMap.get(PayPalECConstants.PAYPAL_API_RC).trim());
    			correlationID = ecDirectCapturetMap.get(PayPalECConstants.CORRELATIONID);
    			ecDirectData.setEcDirectCorrID(correlationID);
     			System.out.println("Handler : Caught PayPal Api RC is :" + ecDirectData.getEcDirect_API_RC());
     			if(isRefundTrans){
     				System.out.println("REFUND TRANSACTION IS  FAILED");
     				ecDirectData.setIsOrphanEntry(true);
     				PayPalECHelper.setTransactionValue(ecDirectData);
		        	// Verify the flag 
						 // if 6 , directly set it as Y
		        	     // If less than 6 bump the value
     			}
     			// Do the Orphan Table Insert
     			else{
     				ecDirectData.setIsOrphanEntry(true);
     				PayPalECHelper.setTransactionValue(ecDirectData);
		        	// Verify the flag 
						 // if 6 , directly set it as Y
		        	     // If less than 6 bump the value
     			}
			}
		} else {
			
			
 			ecDirectData.setEcDirect_API_RC(ecDirectCapturetMap.get(PayPalECConstants.PAYPAL_API_RC).trim());
 			System.out.println("Handler : Caught PayPal Api RC is :" + ecDirectData.getEcDirect_API_RC());
			correlationID = ecDirectCapturetMap.get(PayPalECConstants.CORRELATIONID);
			ecDirectData.setEcDirectCorrID(correlationID);
			System.out.println("Error at DO REF API call :" + PayPalECConstants.DOREFERENCETRANSACTION + ": ecDirectData :" + ecDirectData.toString());
			ecDirectData.setIsOrphanEntry(true);
			PayPalECHelper.setTransactionValue(ecDirectData);
			

        	// Verify the flag 
				 // if 6 , directly set it as Y
        	     // If less than 6 bump the value
		}
		System.out.println("Captured Correlation ID at  DOREFERENCETRANSACTION :" + correlationID);
		return ecDirectData;
	}

	/**
	 * Method to handle insert database call to insert ECDirectData , effort key and
	 * other information to DB2 Table
	 * 
	 * @param ecDirectData
	 */
	public static void handleInsertDatabaseCall(ECDirectData ecDirectData) {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup("epayGowner");
			if (dbOwner == null) {
				System.out.println("EPayG epayowner is missing");
			} else {
				System.out.println("EPayG epayOwner: " + dbOwner);
				if(ecDirectData.getIsOrphanEntry() && !ecDirectData.isBatchCall()){
				// DO the Encipher of BID token
				String bidToken = PayPalECHelper.bidTokenizer(ecDirectData.getEcDirectBID(), "ENCIPHER");
				ecDirectData.setEcDirectBID(bidToken);
				DBAccess.insertToECDirectOrphanTable(dbOwner, ctx, ecDirectData);
				}
				else if (ecDirectData.getIsTransEntry()){
					DBAccess.insertToECDirectTable(dbOwner, ctx, ecDirectData);
				
				}
				else{
					
					// Entry into New Invalid Table
					System.out.println("Entry into Invalid IPN Table Or No Need of Entry");
				}
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at PayPalEChandler.handleInsertDatabaseCall "
							+ e.getMessage());
		}
	}
	
	/**
	 * Method to handle database call to update Keyline, contractId and
	 * Orbital Success flag to Orbital Table
	 * 
	 * @param ecDirectData
	 */
	public static void handleUpdateDatabaseCall(ECDirectData ecDirectData) {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup("epayGowner");
			if (dbOwner == null) {
				System.out.println("EPayG epayowner is missing");
			} else {
				System.out.println("EPayG epayOwner: " + dbOwner);
				if(ecDirectData.getIsTransEntry() && !ecDirectData.isBatchCall()){
				DBAccess.updateECDirectTable(dbOwner, ctx, ecDirectData);
				}
				else if(ecDirectData.isBatchCall()){
					DBAccess.updateECDirectOrphanTable(dbOwner, ctx, ecDirectData);
				}
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at Orbitalhandler.handleInsertDatabaseCall "
							+ e.getMessage());
		}
	}
	
	/**
	 * Method to handle database call to get EC Direct details from
	 * Orbital table.
	 * 
	 * @param ecDirectData
	 * @return
	 */
	public static ECDirectBean handleGetDatabaseCall(ECDirectData ecDirectData) {
		String dbOwner = null;
		ECDirectBean ecDirectBean = new ECDirectBean();

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup("epayGowner");
			if (dbOwner == null) {
				System.out.println("EPayG epayowner is missing");
			} else {
				System.out.println("EPayG epayOwner: " + dbOwner);
			
				ecDirectBean = DBAccess.getECDirectData(dbOwner, ctx, ecDirectData,ecDirectBean);
								
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at PayPalEChandler.handleGetDatabaseCall "
							+ e.getMessage());
		}
		return ecDirectBean;
	}

	public static ECDirectData handleIPNDataRequest(ECDirectData ecDirectData, String IPNStatus) {
		
		// IF Verify Status is VERIFIED, check for TXN ID
		if(null != IPNStatus && PayPalECConstants.IPN_VERIFIED.equalsIgnoreCase(IPNStatus)){
			if((ecDirectData.getPaymentStatus()).equalsIgnoreCase(PayPalECConstants.PAYMENT_PENDING) 
					|| (ecDirectData.getPaymentStatus()).equalsIgnoreCase(PayPalECConstants.PAYMENT_NONE)){
		         System.out.println("Payment Status from IPN is Pending/None");
			
			}
			// Payment Status not pending or None
			else{	
			ECDirectBean ecDirectBean = PayPalECHandler.handleGetDatabaseCall(ecDirectData);
			if(null != ecDirectBean.getEcDirectTransID() && "" != ecDirectBean.getEcDirectTransID()){
				
			    if( "RFD" != ecDirectBean.getEcDirectTransName()){
				if(null != ecDirectData.getPaymentStatus() && "" != ecDirectData.getPaymentStatus()){
					if(ecDirectBean.getPaymentStatus().equalsIgnoreCase(ecDirectData.getPaymentStatus())){
						System.out.println("Payment Status is not changed bean Status :" + ecDirectBean.getPaymentStatus()
								+ "DTO Status :" + ecDirectData.getPaymentStatus());
						
					}
					else{
						// Payment Status Changed
						System.out.println("Payment Status Changed bean Status :" + ecDirectBean.getPaymentStatus()
								+ "DTO Status :" + ecDirectData.getPaymentStatus());
						
						
						ecDirectData = PayPalECHelper
								.populateDataForRequest(ecDirectBean,ecDirectData);
						ecDirectData.setIsTransEntry(true);
					}
				}
				else{
					System.out.println("Payment Status is not found in Verified Message");
				}
				}
				// Refund Transactions
			    else {
								if(null != ecDirectData.getRefundStatus() && "" != ecDirectData.getRefundStatus()){
									if(ecDirectBean.getPaymentStatus().equalsIgnoreCase(ecDirectData.getRefundStatus())){
										System.out.println("Refund Status is not changed");
										
									}
									else{
										// Payment Status Changed
										System.out.println("Refund Status Changed");
										ecDirectData.setIsTransEntry(true);
									}
									
								}
								else{
									
									System.out.println("Refund Status is not found in Verified Message");
								}
						    	
						    }
			

			}
			else{
				System.out.println("Transaction ID is not found in DB for IPN Message");
				if(null != ecDirectData.getEcDirectTransID() && "" != ecDirectData.getEcDirectTransID()){
					//ODD TransId, pushed into Table
					ecDirectData.setIsTransEntry(true);
				}
				
			}
			
			}
		
		}
		
		// IF INVALID, ENTER INTO IPN INVALID TABLE
		else if(null != IPNStatus && PayPalECConstants.IPN_INVALID.equalsIgnoreCase(IPNStatus)) {
			System.out.println("INVALID Verification returned by PayPal for IPN Message");
			// Entry into Invalid Table Maybe
			//PayPalECHandler.handleInsertDatabaseCall(ecDirectData);
		}
		// IF JUNK
		else{
			
			System.out.println("Junk Reply to IPN POST Verification");
		}
		
          return ecDirectData;
	}
	
	/**
	 * Method to handle database call to get EC Direct Orphan details from
	 * Orbital table.
	 * 
	 * @param ecDirectData
	 * @return
	 */
	public static ArrayList<ECDirectOrphanBean> handleGetECOrphanDatabaseCall() {
		String dbOwner = null;
		//ECDirectOrphanBean ecDirectOrphan;
		ArrayList<ECDirectOrphanBean> orphanList = null; 

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup("epayGowner");
			if (dbOwner == null) {
				System.out.println("EPayG epayowner is missing");
			} else {
				System.out.println("EPayG epayOwner: " + dbOwner);
			
				orphanList = DBAccess.getECDirectOrphanData(dbOwner, ctx);
				System.out.println("ECDIrect Handler : AFter getting orphan list " + dbOwner);
								
			}
		} catch (Exception e) {
			System.out
					.println("Exception caught at PayPalEChandler.handleGetOrphanDatabaseCall "
							+ e.getMessage());
		}
		return  orphanList;
	}
	
	
	

}

