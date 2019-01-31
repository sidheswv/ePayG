/**
 * 
 */
package com.timeinc.tcs.epayG.dto;

import java.util.List;

/**
 * @author bilgin
 *
 */
public class PayPalECDto {

	protected String token;
	
	protected String redirectUrl;
	
	protected String payerID;
	
	protected String billingAgreementID;

	protected List<String> setECErrors;
	
	protected List<String> getECDetailsErrors;
	
	protected List<String> createBAErrors;
	
	protected String paymentAmount;
	

	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the redirectUrl
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * @param redirectUrl the redirectUrl to set
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/**
	 * @return the payerID
	 */
	public String getPayerID() {
		return payerID;
	}

	/**
	 * @param payerID the payerID to set
	 */
	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}

	/**
	 * @return the billingAgreementID
	 */
	public String getBillingAgreementID() {
		return billingAgreementID;
	}

	/**
	 * @param billingAgreementID the billingAgreementID to set
	 */
	public void setBillingAgreementID(String billingAgreementID) {
		this.billingAgreementID = billingAgreementID;
	}

	/**
	 * @return the setECErrors
	 */
	public List<String> getSetECErrors() {
		return setECErrors;
	}

	/**
	 * @param setECErrors the setECErrors to set
	 */
	public void setSetECErrors(List<String> setECErrors) {
		this.setECErrors = setECErrors;
	}

	/**
	 * @return the getECDetailsErrors
	 */
	public List<String> getGetECDetailsErrors() {
		return getECDetailsErrors;
	}

	/**
	 * @param getECDetailsErrors the getECDetailsErrors to set
	 */
	public void setGetECDetailsErrors(List<String> getECDetailsErrors) {
		this.getECDetailsErrors = getECDetailsErrors;
	}

	/**
	 * @return the createBAErrors
	 */
	public List<String> getCreateBAErrors() {
		return createBAErrors;
	}

	/**
	 * @param createBAErrors the createBAErrors to set
	 */
	public void setCreateBAErrors(List<String> createBAErrors) {
		this.createBAErrors = createBAErrors;
	}
	/**
	 * @return the paymentAmount
	 */
	public String getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}
