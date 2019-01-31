package com.timeinc.tcs.epayG.dto;

import com.timeinc.tcs.epayG.constants.OrbitalConstants;

public class ApplePayData {

			private String magCode;
		// Bundle Details	
		    private String aPayData;
			private String merchantId;
			private String aPaySign;
			private String aPayAppData;
			private String aPayEpKey;
			private String aPayPubHash;
			private String aPayTranId;
			private String aPayPNW;
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
			private String segId;
			private String pdat;
			private String uniq;
		//Credit Card Details	
			private String aPayType;
			private String aPayValue;
			private String cryptogram;
			private String dpan;
			private String dollarValue;		
			private String accountNumber;
			private String activity;
			private String tax;
			private String totalValue;
			private String expiryDate;
		//Flags	
			private Boolean isTestEnv;
			private Boolean isOrbitalRetry;
			private Boolean isFromListener = false;
			private String isCryptogramPresent = OrbitalConstants.NO;
			private String orbitalSuccess = OrbitalConstants.NO;
			private String isMatchToListener = OrbitalConstants.NA;
	
			private String hostName;
	
			
	// only for Test
	public String getHostName() {
				return hostName;
			}
	public void setHostName(String hostName) {
				this.hostName = hostName;
			}
	public String getaPayData() {
		return aPayData;
	}
	public void setaPayData(String aPayData) {
		this.aPayData = aPayData;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getaPaySign() {
		return aPaySign;
	}
	public void setaPaySign(String aPaySign) {
		this.aPaySign = aPaySign;
	}
	public String getaPayAppData() {
		return aPayAppData;
	}
	public void setaPayAppData(String aPayAppData) {
		this.aPayAppData = aPayAppData;
	}
	public String getaPayEpKey() {
		return aPayEpKey;
	}
	public void setaPayEpKey(String aPayEpKey) {
		this.aPayEpKey = aPayEpKey;
	}
	public String getaPayPubHash() {
		return aPayPubHash;
	}
	public void setaPayPubHash(String aPayPubHash) {
		this.aPayPubHash = aPayPubHash;
	}
	public String getaPayTranId() {
		return aPayTranId;
	}
	public void setaPayTranId(String aPayTranId) {
		this.aPayTranId = aPayTranId;
	}
	public Boolean getIsTestEnv() {
		return isTestEnv;
	}
	public void setIsTestEnv(Boolean isTestEnv) {
		this.isTestEnv = isTestEnv;
	}
	public Boolean getIsOrbitalRetry() {
		return isOrbitalRetry;
	}
	public void setIsOrbitalRetry(Boolean isOrbitalRetry) {
		this.isOrbitalRetry = isOrbitalRetry;
	}
	public String getaPayPNW() {
		return aPayPNW;
	}
	public void setaPayPNW(String aPayPNW) {
		this.aPayPNW = aPayPNW;
	}
	public String getaPayType() {
		return aPayType;
	}
	public void setaPayType(String aPayType) {
		this.aPayType = aPayType;
	}
	public String getaPayValue() {
		return aPayValue;
	}
	public void setaPayValue(String aPayValue) {
		this.aPayValue = aPayValue;
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
	public String getDollarValue() {
		return dollarValue;
	}
	public void setDollarValue(String dollarValue) {
		this.dollarValue = dollarValue;
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
	public String getOrbitalSuccess() {
		return orbitalSuccess;
	}
	public void setOrbitalSuccess(String orbitalSuccess) {
		this.orbitalSuccess = orbitalSuccess;
	}
	public String getMagCode() {
		return magCode;
	}
	public void setMagCode(String magCode) {
		this.magCode = magCode;
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
	public String getCryptogram() {
		return cryptogram;
	}
	public void setCryptogram(String cryptogram) {
		this.cryptogram = cryptogram;
	}
	public String getDpan() {
		return dpan;
	}
	public void setDpan(String dpan) {
		this.dpan = dpan;
	}
	public Boolean getIsFromListener() {
		return isFromListener;
	}
	public void setIsFromListener(Boolean isFromListener) {
		this.isFromListener = isFromListener;
	}
	public String getIsCryptogramPresent() {
		return isCryptogramPresent;
	}
	public void setIsCryptogramPresent(String isCryptogramPresent) {
		this.isCryptogramPresent = isCryptogramPresent;
	}
	public String getIsMatchToListener() {
		return isMatchToListener;
	}
	public void setIsMatchToListener(String isMatchToListener) {
		this.isMatchToListener = isMatchToListener;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
