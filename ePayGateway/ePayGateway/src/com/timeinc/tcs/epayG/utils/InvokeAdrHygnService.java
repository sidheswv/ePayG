package com.timeinc.tcs.epayG.utils;

import com.timeinc.tcs.addr.EAddrHygServiceLocator;
import com.timeinc.tcs.addr.EAddrDTO;
import com.timeinc.tcs.addr.EAddrHygSoapBindingStub;
import com.timeinc.tcs.epayG.entity.EAddrRequestObject;
import com.timeinc.tcs.epayG.constants.AddrHygnWidgetConstants;


public class InvokeAdrHygnService {
	
	

	public static EAddrDTO addrHygn(EAddrRequestObject eaddrRequestObject, String transId){
		boolean cleansingStatus = false;
		EAddrDTO eaddrResponse = new EAddrDTO();
		EAddrHygServiceLocator serviceLocation = null;
		EAddrHygSoapBindingStub serviceStub = null;
		
		/**String tranRequestType = AddrHygnWidgetConstants.TRANSACTION_VERIFY;
		String presenterId = AddrHygnWidgetConstants.EPAY_PRESENTER_ID;
		String clientId = AddrHygnWidgetConstants.EPAY_CLIENT_ID;
		String refId = transId;
		String firstName =eaddrRequestObject.getFirstName();
		String lastName =eaddrRequestObject.getLastName();
		String address1="";
		String address2="";
		String city="";
		String state="";
		String postalCode=eaddrRequestObject.getZipCode();
		String plus4Zip="";
		String countryCode=AddrHygnWidgetConstants.EPAY_COUNTRYCODE;
		String epvType=eaddrRequestObject.getCardType();
		String accountNumber=eaddrRequestObject.getCardNumber();
		String ccExp=eaddrRequestObject.getExpiryMonth()+""+EAddrRequestObject.getExpiryYear();
		String ccSecurityCode="";
		String bankRoutingNumber="";
		String bankAccountType="";
		String currencyCode=AddrHygnWidgetConstants.EPAY_CURRENCY_CODE;
		String tranAmount="";
		String salesTax="";
		String descriptor="";
		String descriptorCityPhone="";
		String clientPassThrough="";
		String clientTranId=transId;
		String rvrslTCSTranId="";
		String rvrslTranDate="";
		String rvrslAuthCode="";
		String encryptionFlag="";
		String companyName = "";
		String billingFreq = "";
		String billingStartDate ="";
		String accountNbrToken =""; **/
		
		String clientId = eaddrRequestObject.getClientId();
		String address1 = eaddrRequestObject.getAddress1();
		String address2 = eaddrRequestObject.getAddress2();
		String city = eaddrRequestObject.getCity();
		String state = eaddrRequestObject.getState();
		String zipcode = eaddrRequestObject.getZipcode();
		
		try {
			System.out.println("Inside address hygiene service call");
			serviceLocation = new EAddrHygServiceLocator();
			serviceStub = (EAddrHygSoapBindingStub) serviceLocation.getEAddrHyg();
			
			eaddrResponse = serviceStub.requestCleansing(clientId, address1, address2, city, state, zipcode);
			System.out.println("Inside address hygiene service call" + eaddrResponse.getOutputAddr1());
			if(eaddrResponse != null){
				if(eaddrResponse.getInputAddr1().equalsIgnoreCase(eaddrResponse.getOutputAddr1()) && eaddrResponse.getInputCity().equalsIgnoreCase(eaddrResponse.getOutputCity()) && eaddrResponse.getInputZip().equalsIgnoreCase(eaddrResponse.getOutputZip())){
					cleansingStatus = true;
				}
			}
			
			// If verification status is 104 then give a pass.
			//Log.setReport("Response received from epay for verification -->"+eaddrResponse.getResponseCode());
			//if(eaddrResponse.getTranResponseType().equals("A")){
				//verificationStatus = true;
			//}else{
			//	verificationStatus = false;
			//}
			
		} catch (Exception e) {
			//Log.setReport("Some execption occured in invoking the service."+e.getMessage());
			e.printStackTrace();
			cleansingStatus = false;
		}
		return eaddrResponse;
	}
	


}
