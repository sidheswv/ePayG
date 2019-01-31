package com.timeinc.tcs.epayG.dto;


public class AndroidPayData {

	//Android Data
	private String encryptedMessage;
	private String ephemeralPublicKey;
	private String tag;
	private String androidPayType;
	private String androidPayValue;
	
	//Effort key Details
	private String effortKey;
	private String effortKeyOption;	
	private String effortTerm;
	private String effortValue;
	private String dollarValue;	
	
	private String merchantId = "1234";
	private String dpan;
	private String cryptogram;
	private String expiryMonth;
	private String expiryYear;
	
	//Customer Details
	private String custName;
	private String keyline;
	private String custAddr1;
	private String custAddr2;
	private String custEmail;
	private String postalCode;
	private String cityStreet;
	
	//Contract Details
	private String segId;
	private String pdat;
	private String uniq;
	private String accountNumber;
	private String activity;
	private String tax;
	private String totalValue;
	
	private String magCode;
	private Boolean isFromBatch = false;
	private Boolean isAndroidPayRetry = false;
	private Boolean isFromListener = false;
	private Boolean isTestEnv;
	private String isCryptogramPresent = "N";
	private String androidPayStatus = "1";
	private String isMatchToListener = "NA";
	
	public String getEncryptedMessage() {
		return encryptedMessage;
	}
	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}
	public String getEphemeralPublicKey() {
		return ephemeralPublicKey;
	}
	public void setEphemeralPublicKey(String ephemeralPublicKey) {
		this.ephemeralPublicKey = ephemeralPublicKey;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getAndroidPayType() {
		return androidPayType;
	}
	public void setAndroidPayType(String androidPayType) {
		this.androidPayType = androidPayType;
	}
	public String getAndroidPayValue() {
		return androidPayValue;
	}
	public void setAndroidPayValue(String androidPayValue) {
		this.androidPayValue = androidPayValue;
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
	public String getDollarValue() {
		return dollarValue;
	}
	public void setDollarValue(String dollarValue) {
		this.dollarValue = dollarValue;
	}
	public String getMagCode() {
		return magCode;
	}
	public void setMagCode(String magCode) {
		this.magCode = magCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Boolean getIsFromBatch() {
		return isFromBatch;
	}
	public void setIsFromBatch(Boolean isFromBatch) {
		this.isFromBatch = isFromBatch;
	}
	public Boolean getIsTestEnv() {
		return isTestEnv;
	}
	public void setIsTestEnv(Boolean isTestEnv) {
		this.isTestEnv = isTestEnv;
	}
	public String getIsCryptogramPresent() {
		return isCryptogramPresent;
	}
	public void setIsCryptogramPresent(String isCryptogramPresent) {
		this.isCryptogramPresent = isCryptogramPresent;
	}
	public String getAndroidPayStatus() {
		return androidPayStatus;
	}
	public void setAndroidPayStatus(String androidPayStatus) {
		this.androidPayStatus = androidPayStatus;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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
	public Boolean getIsAndroidPayRetry() {
		return isAndroidPayRetry;
	}
	public void setIsAndroidPayRetry(Boolean isAndroidPayRetry) {
		this.isAndroidPayRetry = isAndroidPayRetry;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	public String getDpan() {
		return dpan;
	}
	public void setDpan(String dpan) {
		this.dpan = dpan;
	}
	public String getCryptogram() {
		return cryptogram;
	}
	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		if (expiryMonth != null && expiryMonth != ""
				&& expiryMonth.length() < 2) {
			expiryMonth = "0" + expiryMonth;
		}
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getIsMatchToListener() {
		return isMatchToListener;
	}
	public void setIsMatchToListener(String isMatchToListener) {
		this.isMatchToListener = isMatchToListener;
	}
	public Boolean getIsFromListener() {
		return isFromListener;
	}
	public void setIsFromListener(Boolean isFromListener) {
		this.isFromListener = isFromListener;
	}
	
}
