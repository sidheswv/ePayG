package com.timeinc.tcs.epayG.messages;

public class TokenData {

	private String applicationPrimaryAccountNumber;
	private String applicationExpirationDate;
	private String currencyCode;
	private String transactionAmount;
	private String deviceManufacturerIdentifier;
	private String paymentDataType;
	private paymentData paymentData;
	
	public String getApplicationPrimaryAccountNumber() {
		return applicationPrimaryAccountNumber;
	}
	public void setApplicationPrimaryAccountNumber(
			String applicationPrimaryAccountNumber) {
		this.applicationPrimaryAccountNumber = applicationPrimaryAccountNumber;
	}
	public String getApplicationExpirationDate() {
		return applicationExpirationDate;
	}
	public void setApplicationExpirationDate(String applicationExpirationDate) {
		this.applicationExpirationDate = applicationExpirationDate;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getDeviceManufacturerIdentifier() {
		return deviceManufacturerIdentifier;
	}
	public void setDeviceManufacturerIdentifier(String deviceManufacturerIdentifier) {
		this.deviceManufacturerIdentifier = deviceManufacturerIdentifier;
	}
	public String getPaymentDataType() {
		return paymentDataType;
	}
	public void setPaymentDataType(String paymentDataType) {
		this.paymentDataType = paymentDataType;
	}
	public paymentData getPaymentData() {
		return paymentData;
	}
	public void setPaymentData(paymentData paymentData) {
		this.paymentData = paymentData;
	}
	
	
}
