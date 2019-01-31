package com.timeinc.tcs.epayG.beans;

public class ClientBean implements Comparable {
    String clientId = null;
    String clientCd = null;
    String transType = null;
    
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId.toUpperCase();
	}

	public String getClientCd() {
		return clientCd;
	}

	public void setClientCd(String clientCd) {
		this.clientCd = clientCd.toUpperCase();
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType.toUpperCase();
	}
	
	public int compareTo(Object clientBean) {
        
        int result = 0; 
        result = clientId.trim().toUpperCase().compareTo(((ClientBean)clientBean).clientId.trim().toUpperCase());

        return result;
    }
}
