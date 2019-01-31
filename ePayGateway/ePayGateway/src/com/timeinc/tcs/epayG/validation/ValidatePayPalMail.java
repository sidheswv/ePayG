package com.timeinc.tcs.epayG.validation;


import com.timeinc.tcs.epayG.exception.ValidateException;
import com.timeinc.tcs.epayG.rest.PaypalExpressCheckoutApplication;

/**
 * 
 * @author poduril
 *
 */

public class ValidatePayPalMail {
	
	
	public boolean isPayPalMail(String clientId, String payPalMail) throws ValidateException{
		boolean isvalid = false;
		//Logging
		System.out.println("clientId at ValidatePayPalMail is "+clientId);
		System.out.println("payPalMail at ValidatePayPalMail is "+payPalMail);
		// Validate
		if(clientId != null && clientId != " "){
			if(payPalMail != null && payPalMail != " " ){
				//if valid
				//Check for match in Static HashSet
				if(PaypalExpressCheckoutApplication.authTable.containsKey(clientId.toUpperCase())){
					String paypalEmailAddr = (PaypalExpressCheckoutApplication.authTable.get(clientId.toUpperCase())).trim();			
					if(paypalEmailAddr!=null && paypalEmailAddr!=" "){
						if(paypalEmailAddr.equalsIgnoreCase(payPalMail)){
							isvalid = true;
						}
					}
					else{
						System.out.println("Error at PayPal_Email_Addr");
						throw new ValidateException("Wrong Client Code", clientId);
					}
					
				}
				else if(clientId != null && clientId.length()>0 && payPalMail != null && payPalMail.length()>0){
					
					
						isvalid = true;
					
					
				}else{
					
					System.out.println("Wrong Client Code sent from magazine");
					throw new ValidateException("Wrong Client Code", clientId);
					
				}

			}
			else
			{
				payPalMail = "xxxxx";
				throw new ValidateException("payPalMail cannot be null or spaces", payPalMail);
			}
			
		}
		else
		{
			clientId = "xxxxx";
			throw new ValidateException("ClientId cannot be null or spaces " , clientId);
		}
		
		return isvalid;
	}
	
	public boolean isValidDetail(String input_field, String field_name) throws ValidateException{
		
		boolean isvalid = false;
		if(input_field != null && input_field != ""){
			
			isvalid = true;
		}
		else
		{
			input_field = "xxxxx";
			throw new ValidateException( field_name + "cannot be NULL/SPACESss", input_field);
		}
		return isvalid;
	}

}
 