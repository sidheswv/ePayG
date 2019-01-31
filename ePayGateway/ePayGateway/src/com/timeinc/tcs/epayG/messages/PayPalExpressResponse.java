package com.timeinc.tcs.epayG.messages;

import java.io.Serializable;

/**
 * @author Lakshmi N Poduri
 *
 */

public class PayPalExpressResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String payerId;
	private String billingAgreementId;
	private String message;


	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getBillingAgreementId() {
		return billingAgreementId;
	}

	public void setBillingAgreementId(String billingAgreementId) {
		this.billingAgreementId = billingAgreementId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "PayPalExpressResponse [payerId=" + payerId
				+ ", billingAgreementId=" + billingAgreementId + ", message="
				+ message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((billingAgreementId == null) ? 0 : billingAgreementId
						.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((payerId == null) ? 0 : payerId.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayPalExpressResponse other = (PayPalExpressResponse) obj;
		if (billingAgreementId == null) {
			if (other.billingAgreementId != null)
				return false;
		} else if (!billingAgreementId.equals(other.billingAgreementId))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (payerId == null) {
			if (other.payerId != null)
				return false;
		} else if (!payerId.equals(other.payerId))
			return false;

		return true;
	}

}
