package com.timeinc.tcs.epayG.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DebundleResponse")
public class DebundleResponse {
	
	private String ProcStatus;
	private TokenData tokenData;
	private String StatusMsg;
	
	@XmlElement(name="ProcStatus")
	public String getProcStatus() {
		return ProcStatus;
	}

	public void setProcStatus(String procStatus) {
		ProcStatus = procStatus;
	}

	@XmlElement(name="TokenData")
	public TokenData getTokenData() {
		return tokenData;
	}

	public void setTokenData(TokenData tokenData) {
		this.tokenData = tokenData;
	}

	@XmlElement(name="StatusMsg")
	public String getStatusMsg() {
		return StatusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		StatusMsg = statusMsg;
	} 
	
	

}
