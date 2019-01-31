package com.timeinc.tcs.epayG.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DebundleRequest")
public class DebundleRequest {

	private String OrbitalConnectionUsername;
	private String OrbitalConnectionPassword;
	private String Bin;
	private String MerchantID;
	private String Data;
	private String LatitudeLongitude;
	
	@XmlElement(name="OrbitalConnectionUsername")
	public String getOrbitalConnectionUsername() {
		return OrbitalConnectionUsername;
	}
	
	public void setOrbitalConnectionUsername(String orbitalConnectionUsername) {
		OrbitalConnectionUsername = orbitalConnectionUsername;
	}
	
	@XmlElement(name="OrbitalConnectionPassword")
	public String getOrbitalConnectionPassword() {
		return OrbitalConnectionPassword;
	}
	
	public void setOrbitalConnectionPassword(String orbitalConnectionPassword) {
		OrbitalConnectionPassword = orbitalConnectionPassword;
	}
	
	@XmlElement(name="Bin")
	public String getBin() {
		return Bin;
	}

	public void setBin(String bin) {
		Bin = bin;
	}

	@XmlElement(name="MerchantID")
	public String getMerchantID() {
		return MerchantID;
	}
	
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	
	@XmlElement(name="Data")
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	
	@XmlElement(name="LatitudeLongitude")
	public String getLatitudeLongitude() {
		return LatitudeLongitude;
	}
	public void setLatitudeLongitude(String latitudeLongitude) {
		LatitudeLongitude = latitudeLongitude;
	}
	
	
	
}
