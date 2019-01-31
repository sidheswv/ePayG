package com.timeinc.tcs.epayG.validation;

/**
 * 
 * @author poduril
 *
 */

public class ParseECRequest {
	
	private int rtrnUrlflag = 1;
	
	public int parseRtrnURL(String rtrnurl){
		
		if (rtrnurl != null || rtrnurl != ""){
			if (rtrnurl.equalsIgnoreCase("pm")){
				rtrnUrlflag = 2;
			}
		}
		// Write else block after writing custom parsing exception	
		return rtrnUrlflag;
	}

}
