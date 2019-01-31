package com.timeinc.tcs.epayG.validation;
//import com.timeinc.tcs.util.Log;
public class Validator {
	
	public static boolean isValidString(String inputString){

		if(inputString != null){				
				       if (inputString.indexOf("<APPLET") != -1
										|| (inputString.indexOf("<OBJECT") != -1 && (inputString.indexOf("CODEBASE") != -1 
										|| inputString.indexOf("CLASSID") != -1))
										|| (inputString.indexOf("LANG") != -1 && (inputString.indexOf("JAVASCRIPT") != -1 
										|| inputString.indexOf("VBSCRIPT") != -1))
										|| (inputString.indexOf("<EMBED") != -1 && inputString.indexOf("SRC") != -1)
										|| inputString.indexOf("<SERVER") != -1
										|| inputString.indexOf("<A") != -1
										|| inputString.indexOf("%") != -1
										|| inputString.indexOf("HREF") != -1
										|| inputString.indexOf("<IMG") != -1
										|| inputString.indexOf("<SCRIPT") != -1
										|| inputString.indexOf("<IMAGE") != -1
										|| inputString.indexOf(">>>") != -1 
										|| inputString.indexOf("=") != -1
										|| inputString.indexOf("--") != -1
										|| inputString.indexOf("-") == 0
										|| inputString.indexOf("-") == 1
										|| inputString.indexOf("<<<") != -1) {
										
									
									//infoBean.setCustomer_cc_name_error("Invalid characters were entered, caught script error.");
									
									
									System.out.println("USE of Invalid charactor or string in request ="+ inputString);
									
									return false;
													
								}
				       		}
							return true;
					}
	//method to clean query params. Idea is to remove blank spaces so as to minimize SQL Injection possibilities
	public static void cleanQueryParam(String inputString){
			if(inputString==null)
				inputString="";
			inputString.replaceAll("\\s+","");
	}
		
}

