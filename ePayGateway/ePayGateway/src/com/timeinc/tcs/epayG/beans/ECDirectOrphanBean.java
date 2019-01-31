package com.timeinc.tcs.epayG.beans;

public class ECDirectOrphanBean {
	
	//Effort Details
			private String effortKey;
			private String effortKeyOption;	
			private String effortTerm;
			private String effortValue;		
		//Customer Details
			private String custName;
			private String keyline;
			private String custAddr1;
			private String postalCode;
			
		//Contract Details
			
			private String segId;
			private String pdat;
			private String uniq;
			private String magCode;
			
		//Credit Card Details	
			private String ecDirectBIDToken;
			private String ecDirectParentTransID;
			private String ecDirectTransName;
			private String ecDirectAmt;
			private String dollarValue;	
        
		//Miscellaneous Fields
			private String isTransDone;

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

			public String getPostalCode() {
				return postalCode;
			}

			public void setPostalCode(String postalCode) {
				this.postalCode = postalCode;
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

			public String getMagCode() {
				return magCode;
			}

			public void setMagCode(String magCode) {
				this.magCode = magCode;
			}

			public String getEcDirectParentTransID() {
				return ecDirectParentTransID;
			}

			public void setEcDirectParentTransID(String ecDirectParentTransID) {
				this.ecDirectParentTransID = ecDirectParentTransID;
			}

			public String getEcDirectTransName() {
				return ecDirectTransName;
			}

			public void setEcDirectTransName(String ecDirectTransName) {
				this.ecDirectTransName = ecDirectTransName;
			}

			public String getEcDirectAmt() {
				return ecDirectAmt;
			}

			public void setEcDirectAmt(String ecDirectAmt) {
				this.ecDirectAmt = ecDirectAmt;
			}

			public String getDollarValue() {
				return dollarValue;
			}

			public void setDollarValue(String dollarValue) {
				this.dollarValue = dollarValue;
			}

			public String getIsTransDone() {
				return isTransDone;
			}

			public void setIsTransDone(String isTransDone) {
				this.isTransDone = isTransDone;
			}

			public String getEcDirectBIDToken() {
				return ecDirectBIDToken;
			}

			public void setEcDirectBIDToken(String ecDirectBIDToken) {
				this.ecDirectBIDToken = ecDirectBIDToken;
			}


}
