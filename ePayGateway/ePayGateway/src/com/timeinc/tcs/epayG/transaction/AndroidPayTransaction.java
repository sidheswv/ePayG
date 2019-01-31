package com.timeinc.tcs.epayG.transaction;

import java.util.Calendar;

import com.timeinc.tcs.cics.gateway.TransactionProcessor;
import com.timeinc.tcs.epayG.dto.AndroidPayData;
import com.timeinc.tcs.messaging.ValueSet;
import com.timeinc.tcs.messaging.ValueSetImpl;
import com.timeinc.tcs.view.ViewUtil;

public class AndroidPayTransaction {

	private static final String BASE_PATH = "com.timeinc.tcs.wes.transactions.";
	private static final String TCC_TRANSACTION = "TCC";
	private static final String VISA_TYPE = "VISA";
	private static final String AMEX_TYPE = "AMEX";
	private static final String MASTER_TYPE = "MASTERCARD";
	private static final String DISCOVER_TYPE = "DISCOVER";

	ValueSet valSet;

	public AndroidPayTransaction() {

	}

	public void processTCC(AndroidPayData androidPayData) {
		System.out.println("Inside TCC Process");
		try {
			valSet = createVS(TCC_TRANSACTION, androidPayData);

			System.out.println("Calling TCC Servlet process");
			TransactionProcessor.process(valSet, BASE_PATH + "TransferCC");
			System.out.println("After process");
		} catch (Exception e) {
			System.out.println("Exception Occured in processTCC"
					+ e.getMessage());

		}

	}

	/**
	 * Method to create Value Set
	 * 
	 * @param transactionType
	 * @param androidPayData
	 * @return
	 */
	private ValueSet createVS(String transactionType, AndroidPayData androidPayData) {
		ValueSet vs = new ValueSetImpl();
		System.out.println("Androidpay POJO received in value set build" + androidPayData);
		try {
			String crypto1 = null;
			String crypto2 = null;
			
			vs.put("WESACTIVESESSION", "true");
			vs.put("SLIOPID","WES" );

			vs.put("XML", "XML");
			vs.put("DISPLAYXML", "FALSE");
			Calendar cal = Calendar.getInstance();
			vs.put("MSCTRXDT", ViewUtil.getJulianDate(cal));
			vs.put("WESACT1", transactionType);
			// TCC Transaction
			if ("TCC".equals(transactionType)) {

				if (androidPayData.getKeyline() != null
						&& androidPayData.getKeyline() != "") {
					String kline = androidPayData.getKeyline();
					if (kline.length() == 17){
					vs.put("MSRSUBKX", kline);
					vs.put("WESKEYLINE", kline);
					System.out.println("Keyline sent is : " +  kline);
					}
					else if(kline.length() == 16){
						kline = kline + "X";
						vs.put("MSRSUBKX", kline);
						vs.put("WESKEYLINE", kline);
					System.out.println("Keyline sent is : " +  kline);
					}
				}
				if (androidPayData.getMagCode() != null
						&& androidPayData.getMagCode() != "") {
					vs.put("MSRSMAG", androidPayData.getMagCode());
				}

				// Customer Details
				if (androidPayData.getCustName() != null
						&& androidPayData.getCustName() != "") {
					vs.put("MSRSUBNM", androidPayData.getCustName());
				}
				if (androidPayData.getCustAddr1() != null
						&& androidPayData.getCustAddr1() != "") {
					vs.put("MSRADDR1", androidPayData.getCustAddr1());
				}
				if (androidPayData.getCustAddr2() != null
						&& androidPayData.getCustAddr2() != "") {
					vs.put("MSRADDR2", androidPayData.getCustAddr2());
				}
				if (androidPayData.getPostalCode() != null
						&& androidPayData.getPostalCode() != "") {
					vs.put("MSRPOST", androidPayData.getPostalCode());
				}
				if (androidPayData.getCityStreet() != null
						&& androidPayData.getCityStreet() != "") {
					vs.put("MSRCTYST", androidPayData.getCityStreet());
				}
				if (androidPayData.getCustEmail() != null
						&& androidPayData.getCustEmail() != "") {
					vs.put("MSREMAIL", androidPayData.getCustEmail());
				}
				// Effort Details
				if (androidPayData.getEffortKey() != null
						&& androidPayData.getEffortKey() != "") {
					vs.put("MSCEKX", androidPayData.getEffortKey());
				}
				if (androidPayData.getEffortKeyOption() != null
						&& androidPayData.getEffortKeyOption() != "") {
					vs.put("MSCEFKOP", androidPayData.getEffortKeyOption());
				}
				if (androidPayData.getEffortTerm() != null
						&& androidPayData.getEffortTerm() != "") {
					vs.put("MSCTERM", androidPayData.getEffortTerm());
				}
				if (androidPayData.getEffortValue() != null
						&& androidPayData.getEffortValue() != "") {
					vs.put("MSCVALUE", androidPayData.getEffortValue());
				}
				// Contract Details
				if (androidPayData.getSegId() != null
						&& androidPayData.getSegId() != "") {
					vs.put("MSRSEGID", androidPayData.getSegId());
				}
				if (androidPayData.getPdat() != null
						&& androidPayData.getPdat() != "") {
					vs.put("MSRSPDAT", androidPayData.getPdat());
				}
				if (androidPayData.getUniq() != null
						&& androidPayData.getUniq() != "") {
					vs.put("MSRSUNIQ", androidPayData.getUniq());
				}
				
				// Credit Card Details
				if (androidPayData.getDpan() != null
						&& androidPayData.getDpan() != "") {
					vs.put("MSCCRDNR", androidPayData.getDpan());
				}
				// Credit Card Expiry Date
				if (androidPayData.getExpiryMonth() != null
						&& androidPayData.getExpiryMonth() != "" && androidPayData.getExpiryYear() != null
						&& androidPayData.getExpiryYear() != "") {
					String expiry = androidPayData.getExpiryMonth() 
							+ androidPayData.getExpiryYear().substring(2, 4);
					vs.put("MSCCRDEX", expiry);
				}
				if (androidPayData.getAndroidPayType() != null
						&& androidPayData.getAndroidPayType() != "") {
					if(VISA_TYPE.equalsIgnoreCase(androidPayData.getAndroidPayType())){
					     vs.put("MSCCRDTP", "V");
					     vs.put("WESBILLTYPE", "V"); 
					}
					if(AMEX_TYPE.equalsIgnoreCase(androidPayData.getAndroidPayType())){
					     vs.put("MSCCRDTP", "A");
					     vs.put("WESBILLTYPE", "A");
					}
					if(MASTER_TYPE.equalsIgnoreCase(androidPayData.getAndroidPayType())){
					     vs.put("MSCCRDTP", "M");
					     vs.put("WESBILLTYPE", "M");
					}
					if(DISCOVER_TYPE.equalsIgnoreCase(androidPayData.getAndroidPayType())){
					     vs.put("MSCCRDTP", "D");
					     vs.put("WESBILLTYPE", "D");
					}
					System.out.println("Card Type Value is :" + vs.getString("MSCCRDTP"));
				}
				if (androidPayData.getEffortValue() != null
						&& androidPayData.getEffortValue() != "") {
					vs.put("MSDDAMT", androidPayData.getEffortValue());
				}
				
				
				vs.put("MSCCRYPT", "G");
				
				if (androidPayData.getCryptogram().length() > 28) {
					crypto1 = androidPayData.getCryptogram().substring(0, 28);
					crypto2 = androidPayData.getCryptogram().substring(28);
				} else {

					crypto1 = androidPayData.getCryptogram();
				}

				vs.put("MSCCGRM1", crypto1);
				if ("A".equals(androidPayData.getAndroidPayType()) && crypto2 != null
						&& crypto2 != "") {
					vs.put("MSCCGRM2", crypto2);
				}

				vs.put("MSCBLRSP", "WES");
				vs.put("MSDRC2", "F");
				vs.put("MSCCANRC", "S");

			}

		} catch (Exception e) {

			System.out.println("Exception occured while Building TCC Value set for Android Pay: "
					+ e.getMessage());
		}
		

		return vs;
	}

}
