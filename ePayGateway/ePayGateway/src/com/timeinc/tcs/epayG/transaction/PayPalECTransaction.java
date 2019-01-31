package com.timeinc.tcs.epayG.transaction;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.timeinc.tcs.cics.gateway.TransactionProcessor;
import com.timeinc.tcs.epayG.dto.ECDirectData;
import com.timeinc.tcs.messaging.ValueSet;
import com.timeinc.tcs.messaging.ValueSetImpl;
import com.timeinc.tcs.messaging.WESSession;
import com.timeinc.tcs.view.ViewUtil;

public class PayPalECTransaction{
	
	
	private static final String BASE_PATH = "com.timeinc.tcs.wes.transactions.";
	private static final String MWERRCOD = "MWERRCOD";
	private static final String MWERRMSG = "ORIG_ERRMSG";
/*	private static final String TCC_TRANSACTION = "TCC";*/
	private static final String PSA_TRANSACTION = "PSA";
	private static final String RFD_TRANSACTION = "RFD";
	private static final String TRB_TRANSACTION = "TRB";
	private static final String ART_TRANSACTION = "ART";
/*	private static final String VISA_TYPE = "VISA";
	private static final String AMEX_TYPE = "AMEX";
	private static final String MASTER_TYPE = "MASTERCARD";
	private static final String DISCOVER_TYPE = "DISCOVER";*/

	private HttpServletRequest req = null;
	private HttpSession session = null;
	private WESSession ws = null;
	private String sessionId = null;
	ValueSet valSet;

	public PayPalECTransaction() {

	}

	public void processTransaction(ECDirectData ecDirectData, String trans) {
		// Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		System.out.println("Inside PayPal EC Process");
		try {
			if(null != trans && "" != trans){
				if(PSA_TRANSACTION.equalsIgnoreCase(trans)){
					valSet = createVS(PSA_TRANSACTION, ecDirectData);
				}
				else if(RFD_TRANSACTION.equalsIgnoreCase(trans)){
					valSet = createVS(ART_TRANSACTION, ecDirectData);	
				}
				else if(TRB_TRANSACTION.equalsIgnoreCase(trans)){
					valSet = createVS(TRB_TRANSACTION, ecDirectData);
				}
				
			}
			//valSet = createVS(PSA_TRANSACTION, ecDirectData);

			System.out.println("Calling ECDIRECT Transaction Servlet process for transaction :" + trans);
			 TransactionProcessor.process(valSet, BASE_PATH + "Action");
			// TransactionProcessor.process(valSet, BASE_PATH + "TransferCC");
			System.out.println("After process");
		} catch (Exception e) {
			System.out.println("Exception Occured in processECDirect Transaction"
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

	private ValueSet createVS(String transactionType, ECDirectData ecDirectData) {
		ValueSet vs = new ValueSetImpl();
		System.out.println("ECDirect POJO received in value set build" + ecDirectData);
		try {
			/*String crypto1 = null;
			String crypto2 = null;*/

			// ValueSet vs = new ValueSetImpl();
			vs.put("WESACTIVESESSION", "true");
			vs.put("SLIOPID","WPN" );

			vs.put("XML", "XML");
			vs.put("DISPLAYXML", "FALSE");
			Calendar cal = Calendar.getInstance();
			vs.put("MSCTRXDT", ViewUtil.getJulianDate(cal));
			vs.put("WESACT1", transactionType);
			// General Transaction

				if (ecDirectData.getKeyline() != null
						&& ecDirectData.getKeyline() != "") {
					String kline = ecDirectData.getKeyline();
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
				if (ecDirectData.getMagCode() != null
						&& ecDirectData.getMagCode() != "") {
					vs.put("MSRSMAG", ecDirectData.getMagCode());
					System.out.println("TRANS LAYER MSRMAG : " +  ecDirectData.getMagCode());
				}
				
				// Contract Details
				if (ecDirectData.getSegId() != null
						&& ecDirectData.getSegId() != "") {
					vs.put("MSRSEGID", ecDirectData.getSegId());
					System.out.println("TRANS LAYER MSRSEGID : " +  ecDirectData.getSegId());
				}
				if (ecDirectData.getPdat() != null
						&& ecDirectData.getPdat() != "") {
					vs.put("MSRSPDAT", ecDirectData.getPdat());
					System.out.println("TRANS LAYER MSRPDAT : " +  ecDirectData.getPdat());
				}
				if (ecDirectData.getUniq() != null
						&& ecDirectData.getUniq() != "") {
					vs.put("MSRSUNIQ", ecDirectData.getUniq());
					System.out.println("TRANS LAYER MSRUNIQ : " +  ecDirectData.getUniq());
				}				
		
				
		// PSA and TRB common fields		
          if("PSA".equalsIgnoreCase(transactionType) || "TRB".equalsIgnoreCase(transactionType)){
				// Customer Details
				if (ecDirectData.getCustName() != null
						&& ecDirectData.getCustName() != "") {
					vs.put("MSRSUBNM", ecDirectData.getCustName());
					System.out.println("TRANS LAYER CUSTNAME : " +  ecDirectData.getCustName());
				}
				if (ecDirectData.getCustAddr1() != null
						&& ecDirectData.getCustAddr1() != "") {
					
						if("null".equalsIgnoreCase(ecDirectData.getCustAddr1())){
						System.out.println("TRANS LAYER ADDR1 CAME as: " + ecDirectData.getCustAddr1());
					}
					else{
					vs.put("MSRADDR1", ecDirectData.getCustAddr1());
					System.out.println("TRANS LAYER ADDR1 : " +  ecDirectData.getCustAddr1());
					}
				}
				if (ecDirectData.getCustAddr2() != null
						&& ecDirectData.getCustAddr2() != "") {
					vs.put("MSRADDR2", ecDirectData.getCustAddr2());
					System.out.println("TRANS LAYER ADDR2 : " +  ecDirectData.getCustAddr2());
				}
				
				if (ecDirectData.getPostalCode() != null
						&& ecDirectData.getPostalCode() != "") {
					
					if("null".equalsIgnoreCase(ecDirectData.getPostalCode())){
						System.out.println("TRANS LAYER MSRPOST CAME as: " + ecDirectData.getPostalCode());
					}
					else{
					vs.put("MSRPOST", ecDirectData.getPostalCode());
					System.out.println("TRANS LAYER MSRPOST : " +  ecDirectData.getPostalCode());
					}
				}
				
				if (ecDirectData.getCityStreet() != null
						&& ecDirectData.getCityStreet() != "") {
					vs.put("MSRCTYST", ecDirectData.getCityStreet());
					System.out.println("TRANS LAYER MSRCTYST : " +  ecDirectData.getCityStreet());
				}
				if (ecDirectData.getCustEmail() != null
						&& ecDirectData.getCustEmail() != "") {
					vs.put("MSREMAIL", ecDirectData.getCustEmail());
					System.out.println("TRANS LAYER MSREMAIL : " +  ecDirectData.getCustEmail());
				}
				// Effort Details
				if (ecDirectData.getEffortKey() != null
						&& ecDirectData.getEffortKey() != "") {
					vs.put("MSCEKX", ecDirectData.getEffortKey());
					System.out.println("TRANS LAYER MSCEKX : " +  ecDirectData.getEffortKey());
				}
				if (ecDirectData.getEffortKeyOption() != null
						&& ecDirectData.getEffortKeyOption() != "") {
					vs.put("MSCEFKOP", ecDirectData.getEffortKeyOption());
					System.out.println("TRANS LAYER MSCEFKOP : " +  ecDirectData.getEffortKeyOption());
				}
				if (null != ecDirectData.getEffortTerm() 
						&& "" != ecDirectData.getEffortTerm() ) {
					if("null".equalsIgnoreCase(ecDirectData.getEffortTerm())){
						System.out.println("TRANS LAYER MSCTERM CAME as: " + ecDirectData.getEffortTerm());
					}
					else{
						vs.put("MSCTERM", ecDirectData.getEffortTerm());
						System.out.println("TRANS LAYER MSCTERM : " +  ecDirectData.getEffortTerm());
					}
				}
				if (null != ecDirectData.getEffortValue() 
						&& "" != ecDirectData.getEffortValue()) {
					
					if("null".equalsIgnoreCase(ecDirectData.getEffortValue())){
						System.out.println("TRANS LAYER MSCVALUE CAME as: " + ecDirectData.getEffortValue());
					}
					else{
						vs.put("MSCVALUE", ecDirectData.getEffortValue());
						System.out.println("TRANS LAYER MSCVALUE : " +  ecDirectData.getEffortValue());
					}

				}

				// vs.put("M1CBLSTD", ecDirectData.getContractId());
				

				
								
			
				vs.put("WESBILLTYPE", "L");
			//	vs.put("MSCCR5TP", "L");
				
				if (ecDirectData.getEcDirectAmt() != null
						&& ecDirectData.getEcDirectAmt() != "") {
					String amount = ecDirectData.getEcDirectAmt().replace(".", "");
					vs.put("MSDDAMT", amount);
					System.out.println("TRANS LAYER MSDDAMT : " +  ecDirectData.getEcDirectAmt());
					
				}
				


				vs.put("MSCBLRSP", "WES");
				//vs.put("MSDRC2", "F");
				
				
				if (ecDirectData.getEcDirectReceiptID() != null
						&& ecDirectData.getEcDirectReceiptID() != "") {
					vs.put("MSCRTORF", ecDirectData.getEcDirectReceiptID());
					System.out.println("TRANS LAYER MSCRTORF : " +  ecDirectData.getEcDirectReceiptID());
				}
				

			if ("PSA".equals(transactionType)) {
				
				// Credit Card Details
				if (ecDirectData.getEcDirectBID() != null
						&& ecDirectData.getEcDirectBID() != "") {
					vs.put("MSCCR5NR", ecDirectData.getEcDirectBID());
					System.out.println("TRANS LAYER MSCCR5NR : " +  ecDirectData.getEcDirectBID());
					
				}				
				
				//Only PSA fields
				vs.put("MSCTDISP", "F");
				vs.put("MSDDAMT",ecDirectData.getEcDirectAmt());
				vs.put("MSCTRXRC", "100");
				vs.put("MSCCR5TP", "L");
				vs.put("MSCPSIND", "O");
				//vs.put("MSCCCRDTP", "Z");
				//vs.put("MSCTRXDT","01/22/15");
				vs.put("MSDAM","CI");
				vs.put("MSCCANRC", "S");
				
				// Transaction Details
				if (ecDirectData.getEcDirectTransID() != null
						&& ecDirectData.getEcDirectTransID() != "") {
					vs.put("MSCRTCAP", ecDirectData.getEcDirectTransID());
					System.out.println("TRANS LAYER MSCRTCAP : " +  ecDirectData.getEcDirectTransID());
				}
				
				
				System.out.println("PSA amount" + vs.getString("MSDDAMT"));

			}
			
/*			else if("TRB".equals(transactionType)){
				vs.put("MSCCANRC","S");
				vs.put("MSDDAMT",ecDirectData.getEcDirectAmt());
				System.out.println("Amount set for TRB:" + ecDirectData.getEcDirectAmt());
				System.out.println("TRB value set");
				
				
			}*/
			
          }
          else{
			// start else part
			if("ART".equals(transactionType)){
				System.out.println("Amount set for RFD ART:" + ecDirectData.getEcDirectAmt());
				vs.put("MSDAM", "RI");
								
				// Transaction Details
				if (ecDirectData.getEcDirectTransID() != null
						&& ecDirectData.getEcDirectTransID() != "") {
					vs.put("MSCRTRFD", ecDirectData.getEcDirectTransID());
					System.out.println("TRANS LAYER REFUND MSCRTRFD : " +  ecDirectData.getEcDirectTransID());
				}
				
			}
		}

		} catch (Exception e) {

			System.out.println("Exception occured while Building TCC Value set"
					+ e.getMessage());
		}
		

		return vs;
	}
	

}
