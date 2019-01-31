package com.timeinc.tcs.epayG.messages;

public class DecryptedResponse {
	private String dpan;
	private String expirationMonth;
	private String expirationYear;
	private String authMethod;
	private String cryptogram;
	private String eciIndicator;

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
	public String getEciIndicator() {
		return eciIndicator;
	}
	public void setEciIndicator(String eciIndicator) {
		this.eciIndicator = eciIndicator;
	}
	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public String getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	public String getAuthMethod() {
		return authMethod;
	}
	public void setAuthMethod(String authMethod) {
		this.authMethod = authMethod;
	}
}
