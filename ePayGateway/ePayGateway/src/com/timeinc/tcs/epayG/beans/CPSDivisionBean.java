package com.timeinc.tcs.epayG.beans;

public class CPSDivisionBean implements Comparable {
    String clientCd = null;
    String currencyCd = null;
    String clientCurrencyCombine = null;
    String presenterCurCd = null;
    String cpsDivision = null;
    
	public String getClientCd() {
		return clientCd;
	}

	public void setClientCd(String clientId) {
		this.clientCd = clientId.toUpperCase();
		if(this.currencyCd != null){
			this.clientCurrencyCombine = clientId.toUpperCase() + this.currencyCd;
		}
	}

	public String getCurrencyCd() {
		return currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd.toUpperCase();
		if(this.clientCd != null){
			this.clientCurrencyCombine = this.clientCd + currencyCd.toUpperCase();
		}
	}

	public String getPresenterCurCd() {
		return presenterCurCd;
	}

	public void setPresenterCurCd(String presenterCurCd) {
		this.presenterCurCd = presenterCurCd.toUpperCase();
	}

	public String getCPSDivision() {
		return cpsDivision;
	}

	public void setCPSDivision(String cpsDivision) {
		this.cpsDivision = cpsDivision;
	}

	public int compareTo(Object cpsDivisionBean) {
        
        int result = 0; 
        result = clientCurrencyCombine.trim().toUpperCase().compareTo(((CPSDivisionBean)cpsDivisionBean).clientCurrencyCombine.trim().toUpperCase());
 
        return result;
    }
}
