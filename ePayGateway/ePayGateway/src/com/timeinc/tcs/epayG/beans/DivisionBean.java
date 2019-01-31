package com.timeinc.tcs.epayG.beans;

public class DivisionBean implements Comparable {
    String clientCd = null;
    String currencyCd = null;
    String clientCurrencyCombine = null;
    String presenterCurCd = null;
    String division = null;
    
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public int compareTo(Object divisionBean) {
        
        int result = 0; 
        result = clientCurrencyCombine.trim().toUpperCase().compareTo(((DivisionBean)divisionBean).clientCurrencyCombine.trim().toUpperCase());
 
        return result;
    }
}
