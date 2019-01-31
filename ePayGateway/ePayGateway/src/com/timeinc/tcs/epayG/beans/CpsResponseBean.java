package com.timeinc.tcs.epayG.beans;

public class CpsResponseBean implements Comparable {
    String respCd = null;
    String tranResponseType = null;
    
	public String getRespCd() {
		return respCd;
	}

	public void setRespCd(String respCd) {
		this.respCd = respCd.toUpperCase();
	}

	public String getTranResponseType() {
		return tranResponseType;
	}

	public void setTranResponseType(String tranResponseType) {
		this.tranResponseType = tranResponseType;
	}

	public int compareTo(Object cpsResponseBean) {
        
        int result = 0; 
        result = respCd.trim().toUpperCase().compareTo(((CpsResponseBean)cpsResponseBean).respCd.trim().toUpperCase());
        return result;
    }
}
