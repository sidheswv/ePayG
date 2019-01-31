package com.timeinc.tcs.epayG.dto;

public class ECDirectData {
	
	private String magCode;
	
	
//Effort Details
	private String effortKey;
	private String effortKeyOption;	
	private String effortTerm;
	private String effortValue;		
//Customer Details
	private String custName;
	private String keyline;
	private String custAddr1;
	private String custAddr2;
	private String custEmail;
	private String postalCode;
	private String cityStreet;
//Contract Details
	private String contractId;
	private String segId;
	private String pdat;
	private String uniq;
	
//Credit Card Details	
	private String ecDirectBID;
	private String ecDirectTransID;
	private String ecDirectParentTransID = "XXXX XXXX XXXX XXXX";
	private String ecDirectTransName;
	private String ecDirectPayerID;
	private String ecDirectReceiptID;
	private String ecDirectAmt;
	private String dpan;
	private String dollarValue;	
	
//Payment Details
	private String paymentStatus;
	private String refundStatus;
	private String pendingReason;
	
//Miscellaneous
	private String transDone = "1";
	
//Flags	
	private boolean isTestEnv;
	private boolean isIPNCall ;
	private boolean isOrphanEntry ;
	private boolean isTransEntry ;
	private boolean isListenerCall ;
	private boolean isBatchCall;
	private boolean isFirstCapture;
	
// PayPal API response
	private String ecDirectCorrID;
	private String ecDirect_API_RC = "00000";
	
	public String getMagCode() {
		return magCode;
	}
	public void setMagCode(String magCode) {
		this.magCode = magCode;
	}
	public String getEffortKey() {
		return effortKey;
	}
	public void setEffortKey(String effortKey) {
		this.effortKey = effortKey;
	}
	public String getEffortKeyOption() {
		return effortKeyOption;
	}
	public void setEffortKeyOption(String effortKeyOption) {
		this.effortKeyOption = effortKeyOption;
	}
	public String getEffortTerm() {
		return effortTerm;
	}
	public void setEffortTerm(String effortTerm) {
		this.effortTerm = effortTerm;
	}
	public String getEffortValue() {
		return effortValue;
	}
	public void setEffortValue(String effortValue) {
		this.effortValue = effortValue;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getKeyline() {
		return keyline;
	}
	public void setKeyline(String keyline) {
		this.keyline = keyline;
	}
	public String getCustAddr1() {
		return custAddr1;
	}
	public void setCustAddr1(String custAddr1) {
		this.custAddr1 = custAddr1;
	}
	public String getCustAddr2() {
		return custAddr2;
	}
	public void setCustAddr2(String custAddr2) {
		this.custAddr2 = custAddr2;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCityStreet() {
		return cityStreet;
	}
	public void setCityStreet(String cityStreet) {
		this.cityStreet = cityStreet;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getSegId() {
		return segId;
	}
	public void setSegId(String segId) {
		this.segId = segId;
	}
	public String getPdat() {
		return pdat;
	}
	public void setPdat(String pdat) {
		this.pdat = pdat;
	}
	public String getUniq() {
		return uniq;
	}
	public void setUniq(String uniq) {
		this.uniq = uniq;
	}
	public String getEcDirectBID() {
		return ecDirectBID;
	}
	public void setEcDirectBID(String ecDirectBID) {
		this.ecDirectBID = ecDirectBID;
	}
	public String getEcDirectTransID() {
		return ecDirectTransID;
	}
	public void setEcDirectTransID(String ecDirectTransID) {
		this.ecDirectTransID = ecDirectTransID;
	}
	public String getEcDirectPayerID() {
		return ecDirectPayerID;
	}
	public void setEcDirectPayerID(String ecDirectPayerID) {
		this.ecDirectPayerID = ecDirectPayerID;
	}
	public String getDpan() {
		return dpan;
	}
	public void setDpan(String dpan) {
		this.dpan = dpan;
	}
	public String getDollarValue() {
		return dollarValue;
	}
	public void setDollarValue(String dollarValue) {
		this.dollarValue = dollarValue;
	}

	public String getEcDirectReceiptID() {
		return ecDirectReceiptID;
	}
	public void setEcDirectReceiptID(String ecDirectReceiptID) {
		this.ecDirectReceiptID = ecDirectReceiptID;
	}
	public String getEcDirectAmt() {
		return ecDirectAmt;
	}
	public void setEcDirectAmt(String ecDirectAmt) {
		this.ecDirectAmt = ecDirectAmt;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPendingReason() {
		return pendingReason;
	}
	public void setPendingReason(String pendingReason) {
		this.pendingReason = pendingReason;
	}
	public boolean getIsIPNCall() {
		return isIPNCall;
	}
	public void setIsIPNCall(boolean isIPNCall) {
		this.isIPNCall = isIPNCall;
	}
	public String getEcDirectTransName() {
		return ecDirectTransName;
	}
	public void setEcDirectTransName(String ecDirectTransName) {
		this.ecDirectTransName = ecDirectTransName;
	}
	public String getEcDirectParentTransID() {
		return ecDirectParentTransID;
	}
	public void setEcDirectParentTransID(String ecDirectParentTransID) {
		this.ecDirectParentTransID = ecDirectParentTransID;
	}
	public boolean getIsOrphanEntry() {
		return isOrphanEntry;
	}
	public void setIsOrphanEntry(boolean isOrphanEntry) {
		this.isOrphanEntry = isOrphanEntry;
	}
	public boolean getIsTransEntry() {
		return isTransEntry;
	}
	public void setIsTransEntry(boolean isTransEntry) {
		this.isTransEntry = isTransEntry;
	}
	public boolean getIsTestEnv() {
		return isTestEnv;
	}
	public void setIsTestEnv(boolean isTestEnv) {
		this.isTestEnv = isTestEnv;
	}
	public boolean getIsListenerCall() {
		return isListenerCall;
	}
	public void setIsListenerCall(boolean isListenerCall) {
		this.isListenerCall = isListenerCall;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public boolean isBatchCall() {
		return isBatchCall;
	}
	public void setBatchCall(boolean isBatchCall) {
		this.isBatchCall = isBatchCall;
	}

	public String getTransDone() {
		return transDone;
	}
	public void setTransDone(String transDone) {
		this.transDone = transDone;
	}
	public boolean isFirstCapture() {
		return isFirstCapture;
	}
	public void setFirstCapture(boolean isFirstCapture) {
		this.isFirstCapture = isFirstCapture;
	}
	public String getEcDirectCorrID() {
		return ecDirectCorrID;
	}
	public void setEcDirectCorrID(String ecDirectCorrID) {
		this.ecDirectCorrID = ecDirectCorrID;
	}
	public String getEcDirect_API_RC() {
		return ecDirect_API_RC;
	}
	public void setEcDirect_API_RC(String ecDirect_API_RC) {
		this.ecDirect_API_RC = ecDirect_API_RC;
	}

	

}
