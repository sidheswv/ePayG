package com.timeinc.tcs.epayG.messages;

import java.io.Serializable;

/**
 * @author Lakshmi N Poduri
 *
 */

public class PaypalExpressRequest implements Serializable{

	
		private static final long serialVersionUID = 1L;
	
		// Declare the Request Parms
		private String cancelURL;

		public String getCancelURL() {
			return cancelURL;
		}

		public void setCancelURL(String cancelURL) {
			this.cancelURL = cancelURL;
		}

		@Override
		public String toString() {
			return "PaypalExpressRequest [cancelURL=" + cancelURL + "]";
		}
		
		
		
		
}
