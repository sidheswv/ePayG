package com.timeinc.tcs.epayG.messages;

public class paymentData {

	private String onlinePaymentCryptogram;
	private String eciIndicator;
	
	public String getOnlinePaymentCryptogram() {
		return onlinePaymentCryptogram;
	}
	public void setOnlinePaymentCryptogram(String onlinePaymentCryptogram) {
		this.onlinePaymentCryptogram = onlinePaymentCryptogram;
	}
	public String getEciIndicator() {
		return eciIndicator;
	}
	public void setEciIndicator(String eciIndicator) {
		this.eciIndicator = eciIndicator;
	}
	
	
}
