package com.timeinc.tcs.epayG.transaction;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.timeinc.tcs.cics.gateway.TransactionProcessor;
import com.timeinc.tcs.epayG.dto.ApplePayData;
import com.timeinc.tcs.messaging.CICSSystem;
import com.timeinc.tcs.messaging.ValueSet;
import com.timeinc.tcs.messaging.ValueSetImpl;
import com.timeinc.tcs.messaging.WESSession;
import com.timeinc.tcs.view.ViewUtil;

public class ApplePayTransaction {

	private static final String BASE_PATH = "com.timeinc.tcs.wes.transactions.";
	private static final String MWERRCOD = "MWERRCOD";
	private static final String MWERRMSG = "ORIG_ERRMSG";
	private static final String TCC_TRANSACTION = "TCC";
	private static final String VISA_TYPE = "VISA";
	private static final String AMEX_TYPE = "AMEX";
	private static final String MASTER_TYPE = "MASTERCARD";
	private static final String DISCOVER_TYPE = "DISCOVER";

	private HttpServletRequest req = null;
	private HttpSession session = null;
	private WESSession ws = null;
	private String sessionId = null;
	ValueSet valSet;

	public ApplePayTransaction() {

	}

	public void processTCC(ApplePayData applePayData) {
		// Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		System.out.println("Inside TCC Process");
		try {
			valSet = createVS(TCC_TRANSACTION, applePayData);

			System.out.println("Calling TCC Servlet process");
			// TransactionProcessor.process(valSet, BASE_PATH + "Action");
			TransactionProcessor.process(valSet, BASE_PATH + "TransferCC");
			System.out.println("After process");
		} catch (Exception e) {
			System.out.println("Exception Occured in processTCC"
					+ e.getMessage());

		}

	}

	/*
	 * private ValueSet submit(ValueSet valSet,String transactionType, String
	 * id, String value, String refId, String amount) {
	 * 
	 * valSet = createVS(transactionType, id, value, refId, amount);
	 * 
	 * System.out.println("Outside process");
	 * TransactionProcessor.process(valSet, BASE_PATH + "Action");
	 * System.out.println("Inside process"); return valSet; }
	 */

	// private ValueSet populateMisc(ValueSet vs) {
	// req.setAttribute("WESACTIVESESSION", "true");
	// req.setAttribute("WESSESSIONID", req.getSession().getId());
	// req.setAttribute("XML", "XML");
	// req.setAttribute("DISPLAYXML", "FALSE");
	// vs.put("SLIOPID","WES");
	// // vs.put("MSCSEGID","C");
	// // vs.put("MSCSPDAT","0114050");
	// // vs.put("MSCSUNIQ","0");
	// // vs.put("WESACT","ART");
	// // vs.put("MSRSMAG","EW");
	//
	// // vs.put("MWTSYSID", "CICC");
	// // vs.put("WESKEYLINE","533002SGL99485F9");
	// if(!vs.getString("WESKEYLINE").equals("")) {
	// vs.put("MSRSUBKX", vs.getString("WESKEYLINE")+"X");
	// }
	// vs.put("REROUTE_TEST", "CICC");

	/*
	 * else if("ART".equals(transactionType) && "MSCRTORF".equals(id))
	 * vs.put("MSDAM","OI"); else if("ART".equals(transactionType) &&
	 * "MSCRTAUT".equals(id)) vs.put("MSDAM","AI"); else
	 * if("ART".equals(transactionType)&& "MSCRTRFD".equals(id)) vs.put("MSDAM",
	 * "RI"); else if("PSA".equals(transactionType) && "MSCRTCAP".equals(id)){
	 * vs.put("MSCTDISP", "F"); vs.put("MSDDAMT",amount); vs.put("MSCTRXRC",
	 * "100"); vs.put("MSCCR5TP", "Z"); //vs.put("MSCCCRDTP", "Z");
	 * //vs.put("MSCTRXDT","01/22/15"); vs.put("MSDAM","CI");
	 * 
	 * System.out.println("PSA amount" + vs.getString("MSDDAMT")); }else
	 * if("TRB".equals(transactionType)){ //vs.put("MSCTDISP", "F");
	 * vs.put("MSCCANRC","S"); vs.put("MSDDAMT",amount);
	 * System.out.println("Amount set for TRB:" + amount);
	 * System.out.println("TRB value set"); //vs.put("MSCCR5TP", "Z");
	 * //vs.put("MSCCCRDTP", "Z"); //vs.put("MSCTRXDT","01/22/15");
	 * //vs.put("MSDAM","CI");
	 * 
	 * } System.out.println("installflag" + vs.getString("INSTALLFLAG"));
	 * System.out.println("total condition"); if(vs.getString("INSTALLFLAG") !=
	 * null && vs.getString("INSTALLFLAG").equalsIgnoreCase("Y")){
	 * System.out.println("111"); vs.put("MSDRC2", "I"); }else
	 * if(!vs.getString("M1IITOTL__01").equals("") &&
	 * !vs.getString("M1IITOTL__01").equals("1")){ System.out.println("333");
	 * vs.put("MSDRC2", "I"); }
	 */

	//
	// return vs;
	// }

	private ValueSet createVS(String transactionType, ApplePayData applePayData) {
		ValueSet vs = new ValueSetImpl();
		System.out.println("Applepay POJO received in value set build" + applePayData);
		try {
			String crypto1 = null;
			String crypto2 = null;

			// ValueSet vs = new ValueSetImpl();
			vs.put("WESACTIVESESSION", "true");
			vs.put("SLIOPID","WES" );

			vs.put("XML", "XML");
			vs.put("DISPLAYXML", "FALSE");
			Calendar cal = Calendar.getInstance();
			vs.put("MSCTRXDT", ViewUtil.getJulianDate(cal));
			vs.put("WESACT1", transactionType);
			// TCC Transaction
			if ("TCC".equals(transactionType)) {

				if (applePayData.getKeyline() != null
						&& applePayData.getKeyline() != "") {
					String kline = applePayData.getKeyline();
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
				if (applePayData.getMagCode() != null
						&& applePayData.getMagCode() != "") {
					vs.put("MSRSMAG", applePayData.getMagCode());
				}

				// Customer Details
				if (applePayData.getCustName() != null
						&& applePayData.getCustName() != "") {
					vs.put("MSRSUBNM", applePayData.getCustName());
				}
				if (applePayData.getCustAddr1() != null
						&& applePayData.getCustAddr1() != "") {
					vs.put("MSRADDR1", applePayData.getCustAddr1());
				}
				if (applePayData.getCustAddr2() != null
						&& applePayData.getCustAddr2() != "") {
					vs.put("MSRADDR2", applePayData.getCustAddr2());
				}
				if (applePayData.getPostalCode() != null
						&& applePayData.getPostalCode() != "") {
					vs.put("MSRPOST", applePayData.getPostalCode());
				}
				if (applePayData.getCityStreet() != null
						&& applePayData.getCityStreet() != "") {
					vs.put("MSRCTYST", applePayData.getCityStreet());
				}
				if (applePayData.getCustEmail() != null
						&& applePayData.getCustEmail() != "") {
					vs.put("MSREMAIL", applePayData.getCustEmail());
				}
				// Effort Details
				if (applePayData.getEffortKey() != null
						&& applePayData.getEffortKey() != "") {
					vs.put("MSCEKX", applePayData.getEffortKey());
				}
				if (applePayData.getEffortKeyOption() != null
						&& applePayData.getEffortKeyOption() != "") {
					vs.put("MSCEFKOP", applePayData.getEffortKeyOption());
				}
				if (applePayData.getEffortTerm() != null
						&& applePayData.getEffortTerm() != "") {
					vs.put("MSCTERM", applePayData.getEffortTerm());
				}
				if (applePayData.getEffortValue() != null
						&& applePayData.getEffortValue() != "") {
					vs.put("MSCVALUE", applePayData.getEffortValue());
				}
				// Contract Details
				if (applePayData.getSegId() != null
						&& applePayData.getSegId() != "") {
					vs.put("MSRSEGID", applePayData.getSegId());
				}
				if (applePayData.getPdat() != null
						&& applePayData.getPdat() != "") {
					vs.put("MSRSPDAT", applePayData.getPdat());
				}
				if (applePayData.getUniq() != null
						&& applePayData.getUniq() != "") {
					vs.put("MSRSUNIQ", applePayData.getUniq());
				}
				// vs.put("M1CBLSTD", applePayData.getContractId());
				
				// Credit Card Details
				if (applePayData.getDpan() != null
						&& applePayData.getDpan() != "") {
					vs.put("MSCCRDNR", applePayData.getDpan());
				}
				// Credit Card Expiry Date
				if (applePayData.getExpiryDate() != null
						&& applePayData.getExpiryDate() != "") {
					String expiry = applePayData.getExpiryDate().substring(2, 4) 
							+ applePayData.getExpiryDate().substring(0, 2);
					vs.put("MSCCRDEX", expiry);
				}
				if (applePayData.getaPayPNW() != null
						&& applePayData.getaPayPNW() != "") {
					if(VISA_TYPE.equalsIgnoreCase(applePayData.getaPayPNW())){
					     vs.put("MSCCRDTP", "V");
					     vs.put("WESBILLTYPE", "V"); 
					}
					if(AMEX_TYPE.equalsIgnoreCase(applePayData.getaPayPNW())){
					     vs.put("MSCCRDTP", "A");
					     vs.put("WESBILLTYPE", "A");
					}
					if(MASTER_TYPE.equalsIgnoreCase(applePayData.getaPayPNW())){
					     vs.put("MSCCRDTP", "M");
					     vs.put("WESBILLTYPE", "M");
					}
					if(DISCOVER_TYPE.equalsIgnoreCase(applePayData.getaPayPNW())){
					     vs.put("MSCCRDTP", "D");
					     vs.put("WESBILLTYPE", "D");
					}
					System.out.println("Card Type Value is :" + vs.getString("MSCCRDTP"));
				}
				if (applePayData.getEffortValue() != null
						&& applePayData.getEffortValue() != "") {
					vs.put("MSDDAMT", applePayData.getEffortValue());
				}
				
				
				vs.put("MSCCRYPT", "C");
				
				if (applePayData.getCryptogram().length() > 28) {
					crypto1 = applePayData.getCryptogram().substring(0, 28);
					crypto2 = applePayData.getCryptogram().substring(28);
				} else {

					crypto1 = applePayData.getCryptogram();
				}

				vs.put("MSCCGRM1", crypto1);
				if ("A".equals(applePayData.getaPayType()) && crypto2 != null
						&& crypto2 != "") {
					vs.put("MSCCGRM2", crypto2);
				}

				vs.put("MSCBLRSP", "WES");
				vs.put("MSDRC2", "F");
				vs.put("MSCCANRC", "S");

			}

		} catch (Exception e) {

			System.out.println("Exception occured while Building TCC Value set"
					+ e.getMessage());
		}
		

		return vs;
	}

}
