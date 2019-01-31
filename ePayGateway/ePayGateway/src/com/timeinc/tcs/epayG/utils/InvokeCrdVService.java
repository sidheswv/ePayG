package com.timeinc.tcs.epayG.utils;

import com.timeinc.tcs.epayG.entity.EpayRequestObject;
import com.timeinc.tcs.epayG.constants.PaymentWidgetConstants;
import com.timeinc.tcs.epay2.EPay2DTO;
import com.timeinc.tcs.epay2.EPay2ItemServiceLocator;
import com.timeinc.tcs.epay2.EPay2ItemSoapBindingStub;

public class InvokeCrdVService {

	public static EPay2DTO verifyCard(EpayRequestObject epayRequestObject,
			String transId, String isCSPIID) {
		boolean verificationStatus = false;
		String clientId = "";
		EPay2DTO epayResponse = new EPay2DTO();
		EPay2ItemServiceLocator serviceLocation = null;
		EPay2ItemSoapBindingStub serviceStub = null;

		String tranRequestType = PaymentWidgetConstants.TRANSACTION_VERIFY;
		String presenterId = PaymentWidgetConstants.EPAY_PRESENTER_ID;
		if(null!= isCSPIID && isCSPIID.equalsIgnoreCase("Y")){ 
			clientId = PaymentWidgetConstants.EPAY_DC_CSPI_CODE;
		}
		else{
		clientId = PaymentWidgetConstants.EPAY_DC_KALMBACH_CODE;
		}
		String refId = transId;
		String firstName = epayRequestObject.getFirstName();
		String lastName = epayRequestObject.getLastName();
		String address1 = epayRequestObject.getAddress_line1();
		String address2 = "";
		String city = epayRequestObject.getCity();
		String state = epayRequestObject.getState();
		String postalCode = epayRequestObject.getZipCode();
		String plus4Zip = "";
		String countryCode = epayRequestObject.getCountryCode();
		String epvType = epayRequestObject.getCardType();
		String accountNumber = epayRequestObject.getCardNumber();
		String ccExp = epayRequestObject.getExpiryMonth() + ""
				+ epayRequestObject.getExpiryYear();
		String ccSecurityCode = "";
		String bankRoutingNumber = "";
		String bankAccountType = "";
		String currencyCode = PaymentWidgetConstants.EPAY_CURRENCY_CODE;
		String tranAmount = "";
		String salesTax = "";
		String descriptor = "";
		String descriptorCityPhone = "";
		String clientPassThrough = "";
		String clientTranId = transId;
		String rvrslTCSTranId = "";
		String rvrslTranDate = "";
		String rvrslAuthCode = "";
		String encryptionFlag = "";
		String companyName = "";
		String billingFreq = "";
		String billingStartDate = "";
		String accountNbrToken = "";
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++CLIENT ID+++++++++++++++++++++"+clientId);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		try {
			serviceLocation = new EPay2ItemServiceLocator();
			serviceStub = (EPay2ItemSoapBindingStub) serviceLocation.getEPay2Item();

			
			System.out.println("Out going request to EPAY" + tranRequestType + "--"
					+ "firstName :" + firstName
					+ "lastName :" + lastName
					+ "currencyCode :" + currencyCode
					+ "presenterId :" + presenterId
					+ "clientId :" + clientId
					+ "postalCode :" + postalCode
					+ "epvType :" + epvType
					+ "tranAmount :" + tranAmount
					+ "countryCode :" + countryCode
					);
			
			
			epayResponse = serviceStub.requestAuthorization(tranRequestType,
					presenterId, clientId, refId, firstName, lastName,
					address1, address2, city, state, postalCode, plus4Zip,
					countryCode, epvType, accountNumber, ccExp, ccSecurityCode,
					bankRoutingNumber, bankAccountType, currencyCode,
					tranAmount, salesTax, descriptor, descriptorCityPhone,
					clientPassThrough, clientTranId, rvrslTCSTranId,
					rvrslTranDate, rvrslAuthCode, encryptionFlag, companyName,
					billingFreq, billingStartDate, accountNbrToken);

			// If verification status is 104 then give a pass.
			// Log.setReport("Response received from epay for verification -->"+epayResponse.getResponseCode());
			if (epayResponse.getTranResponseType().equals("A")) {
				verificationStatus = true;
			} else {
				verificationStatus = false;
			}

		} catch (Exception e) {
			// Log.setReport("Some execption occured in invoking the service."+e.getMessage());
			e.printStackTrace();
			verificationStatus = false;
		}
		return epayResponse;
	}

	public static String tokenCard(EpayRequestObject epayRequestObject,
			String transId) {
		boolean verificationStatus = false;
		String ccToken = null;
		EPay2ItemServiceLocator serviceLocation = null;
		EPay2ItemSoapBindingStub serviceStub = null;

		String tranRequestType = PaymentWidgetConstants.TRANSACTION_TOKENIZE;
		String presenterId = PaymentWidgetConstants.EPAY_PRESENTER_ID;
		String clientId = PaymentWidgetConstants.EPAY_DC_KALMBACH_CODE;
		String refId = transId;
		String firstName = epayRequestObject.getFirstName();
		String lastName = epayRequestObject.getLastName();
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String postalCode = "";
		String plus4Zip = "";
		String countryCode = PaymentWidgetConstants.EPAY_COUNTRYCODE;
		String epvType = "PY";
		String accountNumber = epayRequestObject.getCardNumber();
		String ccExp = "";
		String ccSecurityCode = "";
		String bankRoutingNumber = "";
		String bankAccountType = "";
		String currencyCode = PaymentWidgetConstants.EPAY_CURRENCY_CODE;
		String tranAmount = "";
		String salesTax = "";
		String descriptor = "";
		String descriptorCityPhone = "";
		String clientPassThrough = "";
		String clientTranId = transId;
		String rvrslTCSTranId = "";
		String rvrslTranDate = "";
		String rvrslAuthCode = "";
		String encryptionFlag = "";
		String companyName = "";
		String billingFreq = "";
		String billingStartDate = "";
		String accountNbrToken = "";

		try {
			serviceLocation = new EPay2ItemServiceLocator();
			serviceStub = (EPay2ItemSoapBindingStub) serviceLocation
					.getEPay2Item();

			EPay2DTO epayResponse = serviceStub.requestAuthorization(
					tranRequestType, presenterId, clientId, refId, firstName,
					lastName, address1, address2, city, state, postalCode,
					plus4Zip, countryCode, epvType, accountNumber, ccExp,
					ccSecurityCode, bankRoutingNumber, bankAccountType,
					currencyCode, tranAmount, salesTax, descriptor,
					descriptorCityPhone, clientPassThrough, clientTranId,
					rvrslTCSTranId, rvrslTranDate, rvrslAuthCode,
					encryptionFlag, companyName, billingFreq, billingStartDate,
					accountNbrToken);

			// If verification status is 104 then give a pass.
			// Log.setReport("Response received from epay for verification -->"+epayResponse.getResponseCode());
			if (epayResponse.getAccountNbrToken() != null
					&& epayResponse.getAccountNbrToken() != "") {
				ccToken = epayResponse.getAccountNbrToken();
				System.out.println("After Tokenize call response :"
						+ epayResponse.getResponseCode() + "Token :" + ccToken);
			} else {
				// Get the return code and handle
				verificationStatus = false;
			}

		} catch (Exception e) {
			// Log.setReport("Some execption occured in invoking the service."+e.getMessage());
			e.printStackTrace();
			verificationStatus = false;
		}
		return ccToken;
	}

}
