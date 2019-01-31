<%@page import="java.util.HashMap"  %>
<%@page import="java.util.Map"  %>
<%@page import="com.timeinc.tcs.epayG.entity.PaymentWidgetObject"%>
<%@page import="com.timeinc.tcs.epayG.entity.WidgetResponseObject"%>
<%@page import="java.util.Calendar"  %>
<%@ page import="com.timeinc.tcs.view.ViewUtil" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
  <title>
    	<% 
    	String sourceid= request.getParameter("SOURCE");
    	if(sourceid.equalsIgnoreCase("IHSTORE")) { %>
				KALMBACH HOBBYSTORE
			<%} else if(sourceid.equalsIgnoreCase("IBSTORE")) {%>
				JEWELRY AND BEADING STORE
			<%} else if(sourceid.equalsIgnoreCase("ISSTORE")) { %>
				MY SCIENCE SHOP
			<%} 
			%>	
	</title>
	<jsp:include page="/ePayG/common/header.jsp"/>
	<style>
	.johnson-box-head-xs {
  background: #ECECEC;
  padding: 5px;
  line-height: 1.42857143;
  vertical-align: top;
  border-top: 1px solid #ddd;
  text-align: center;
  font-weight: bold;
}
	</style>
<%
  String addressFlag = request.getParameter("isAddressClean");
  String payMentFlag = request.getParameter("isCardVerified");
  String  addrMsg = request.getParameter("addressMessage");
  String  addrCode = request.getParameter("addressCode");
  
  String paymentAmount = "";	
	if(request.getParameter("Total_Amount") != null && !request.getParameter("Total_Amount").equals("")) {
		paymentAmount = ViewUtil.formatDollar(request.getParameter("Total_Amount"));
	} else {
		paymentAmount = ViewUtil.formatDollar(request.getParameter("Total_Amount"));
	}
	
	Map<String, String> transMap = new HashMap<String, String>();
	transMap.put("0000","US");
	transMap.put("0001","CANADA");
	transMap.put("0002","ENGLAND");
	transMap.put("1001","ALBANIA");
	transMap.put("1002","ALGERIA");
	transMap.put("1003","ANDORRA");
	transMap.put("1004","ANGOLA");
	transMap.put("1005","ARGENTINA");
	transMap.put("1006","ASCENSION ISLANDS");
	transMap.put("1007","AUSTRALIA");
	transMap.put("1008","AUSTRIA");
	transMap.put("1009","AZORES");
	transMap.put("1010","BAHAMAS");
	transMap.put("1011","BAHRAIN");
	transMap.put("1012","BANGLADESH");
	transMap.put("1013","BARBADOS");
	transMap.put("1014","BELGIUM");
	transMap.put("1015","BELIZE");
	transMap.put("1016","BENIN");
	transMap.put("1017","BERMUDA");
	transMap.put("1018","BHUTAN");
	transMap.put("1019","BOLIVIA");
	transMap.put("1020","BORNEO");
	transMap.put("1021","BOTSWANA");
	transMap.put("1022","BRAZIL");
	transMap.put("1023","BRITISH VIRGIN ISLES");
	transMap.put("1024","BRUNEI");
	transMap.put("1025","BULGARIA");
	transMap.put("1026","BURMA");
	transMap.put("1027","BURUNDI");
	transMap.put("1028","CAMBODIA");
	transMap.put("1029","CAMEROON");
	transMap.put("1030","CANARY ISLANDS");
	transMap.put("1032","CAPE VERDE");
	transMap.put("1033","CAROLINE ISLANDS");
	transMap.put("1034","CAYMAN ISLANDS");
	transMap.put("1035","CENTRAL AFRICAN REP");
	transMap.put("1036","CHAD");
	transMap.put("1037","CHANNEL ISLANDS");
	transMap.put("1038","CHILE");
	transMap.put("1039","CHINA");
	transMap.put("1040","COLOMBIA");
	transMap.put("1041","COMOROS ISLANDS");
	transMap.put("1042","CONGO");
	transMap.put("1043","CORSICA");
	transMap.put("1044","COSTA RICA");
	transMap.put("1045","CUBA");
	transMap.put("1046","CYPRUS");
	transMap.put("1047","CZECHOSLOVAKIA");
	transMap.put("1048","DENMARK");
	transMap.put("1049","DJIBOUTI");
	transMap.put("1050","DOMINICA WI");
	transMap.put("1051","DOMINICAN REPUBLIC");
	transMap.put("1052","PORT TIMOR");
	transMap.put("1053","ECUADOR");
	transMap.put("1054","EGYPT");
	transMap.put("1055","EL SALVADOR");
	transMap.put("1056","EQUATORIAL GUINEA");
	transMap.put("1057","ESTONIA");
	transMap.put("1058","ETHIOPIA");
	transMap.put("1059","FAEROE ISLANDS");
	transMap.put("1060","FALKLAND ISLANDS");
	transMap.put("1061","FIJI");
	transMap.put("1062","FINLAND");
	transMap.put("1063","FRANCE");
	transMap.put("1064","FRENCH GUIANA");
	transMap.put("1065","FRENCH POLYNESIA");
	transMap.put("1066","GABON");
	transMap.put("1067","GAMBIA");
	transMap.put("1068","GERMANY");
	transMap.put("1069","GERMAN FED REP");
	transMap.put("1070","GHANA");
	transMap.put("1071","GIBRALTAR");
	transMap.put("1072","GREAT BRITAIN-ENGLAN");
	transMap.put("1073","GREECE");
	transMap.put("1074","GREENLAND");
	transMap.put("1075","GRENADA");
	transMap.put("1076","GUADELOUPE");
	transMap.put("1077","GUAM");
	transMap.put("1078","GUATEMALA");
	transMap.put("1079","GUINEA");
	transMap.put("1080","GUINEA-BISSAU");
	transMap.put("1081","GUYANA");
	transMap.put("1082","HAITI");
	transMap.put("1083","HONDURAS");
	transMap.put("1084","HONG KONG");
	transMap.put("1085","HUNGARY");
	transMap.put("1086","ICELAND");
	transMap.put("1087","INDIA");
	transMap.put("1088","INDONESIA");
	transMap.put("1091","IRELAND");
	transMap.put("1092","ISRAEL");
	transMap.put("1093","ITALY");
	transMap.put("1094","IVORY COAST");
	transMap.put("1095","JAMAICA");
	transMap.put("1096","JAPAN");
	transMap.put("1097","JORDAN");
	transMap.put("1098","KAMPUCHEA");
	transMap.put("1099","KENYA");
	transMap.put("1100","KIRIBATI");
	transMap.put("1101","NORTH KOREA");
	transMap.put("1103","LAOS");
	transMap.put("1104","LATVIA");
	transMap.put("1105","LEBANON");
	transMap.put("1106","LEEWARD ISLANDS");
	transMap.put("1107","LESOTHO");
	transMap.put("1108","LIBERIA");
	transMap.put("1109","LIBYA");
	transMap.put("1110","LIECHTENSTEIN");
	transMap.put("1111","LITHUANIA");
	transMap.put("1112","LUXEMBOURG");
	transMap.put("1113","MACAO");
	transMap.put("1114","MADAGASCAR");
	transMap.put("1115","MADEIRA ISLANDS");
	transMap.put("1116","MALAWI");
	transMap.put("1117","MALAYSIA");
	transMap.put("1118","MALDIVES");
	transMap.put("1119","MALI");
	transMap.put("1120","MALTA");
	transMap.put("1121","MARTINIQUE");
	transMap.put("1122","MAURITANIA");
	transMap.put("1123","MARUITIUS");
	transMap.put("1124","MEXICO");
	transMap.put("1125","MONACO");
	transMap.put("1126","MONGOLIA");
	transMap.put("1127","MOROCCO");
	transMap.put("1128","MOZAMBIQUE");
	transMap.put("1129","NAMIBIA");
	transMap.put("1130","NAURA");
	transMap.put("1131","NEPAL");
	transMap.put("1132","NETHERLANDS HOLLAND");
	transMap.put("1133","NETHERLANDS ANTILLES");
	transMap.put("1134","NEW CALEDONIA");
	transMap.put("1135","NEW ZEALAND");
	transMap.put("1136","NICARAGUA");
	transMap.put("1137","NIGER");
	transMap.put("1138","NIGERIA");
	transMap.put("1139","NORTHERN IRELAND");
	transMap.put("1140","NORWAY");
	transMap.put("1141","OMAN");
	transMap.put("1143","PANAMA");
	transMap.put("1144","PAPUA NEW GUINEA");
	transMap.put("1145","PARAGUAY");
	transMap.put("1146","PERU");
	transMap.put("1147","PHILIPPINES");
	transMap.put("1148","PITCAIHN ISLANDS");
	transMap.put("1149","POLAND");
	transMap.put("1150","PORTUGAL");
	transMap.put("1151","QATAR");
	transMap.put("1152","REP OF KOREA");
	transMap.put("1153","REUNION ISLAND");
	transMap.put("1154","RHODESIA");
	transMap.put("1155","ROMANIA");
	transMap.put("1156","RWANDA");
	transMap.put("1157","SAINT HELENA");
	transMap.put("1158","SAINT LUCIA");
	transMap.put("1159","ST PIERRE & MIQUELON");
	transMap.put("1160","SANTA CRUZ ISLANDS");
	transMap.put("1161","SAO TOME ET PRINCIPE");
	transMap.put("1162","SAUDI ARABIA");
	transMap.put("1163","SCOTLAND");
	transMap.put("1164","SENEGAL");
	transMap.put("1165","SEYCHELLES");
	transMap.put("1166","SIERRA LEONE");
	transMap.put("1167","SINGAPORE");
	transMap.put("1168","SOLOMON ISLANDS");
	transMap.put("1169","SOMALIA NORTHERN REG");
	transMap.put("1170","SOMALIA SOUTHERN REG");
	transMap.put("1171","SOUTH AFRICA");
	transMap.put("1172","SPAIN");
	transMap.put("1173","SRI LANKA");
	transMap.put("1174","ST VINCENT & GRENADI");
	transMap.put("1175","SUDAN");
	transMap.put("1176","SURINAME");
	transMap.put("1177","SWAZILAND");
	transMap.put("1178","SWEDEN");
	transMap.put("1179","SWITZERLAND");
	transMap.put("1180","SYRIA");
	transMap.put("1181","TAIWAN");
	transMap.put("1182","TANZANIA");
	transMap.put("1183","THAILAND");
	transMap.put("1184","TOGO");
	transMap.put("1185","TONGA");
	transMap.put("1186","TRINIDAD & TOBAGO");
	transMap.put("1187","TRISTAN DE CUNHA");
	transMap.put("1188","TUNISIA");
	transMap.put("1189","TURKEY");
	transMap.put("1190","TURKS & CAICOS ISLANDS");
	transMap.put("1191","TUVALU");
	transMap.put("1192","UGANDA");
	transMap.put("1193","UNION SOVIET SOC REP");
	transMap.put("1194","UNITED ARAB EMIRATES");
	transMap.put("1195","UPPER VOLTA");
	transMap.put("1196","URUGUAY");
	transMap.put("1197","VANUATU");
	transMap.put("1198","VATICAN CITY");
	transMap.put("1199","VENEZUELA");
	transMap.put("1200","VIETNAM");
	transMap.put("1201","U.S. VIRGIN ISLANDS");
	transMap.put("1202","WALES");
	transMap.put("1203","WESTERN SAMOA");
	transMap.put("1204","YEMEN");
	transMap.put("1205","YEMEN ARAB REP");
	transMap.put("1206","YUGOSLAVIA");
	transMap.put("1207","ZAIRE");
	transMap.put("1208","ZAMBIA");
	transMap.put("1209","ZIMBABWE");
	transMap.put("1210","AMERICAN SAMOA");
	transMap.put("1211","ANGUILLA");
	transMap.put("1212","ANTIGUA & BARBUDA");
	transMap.put("1213","BURKINA FASO");
	transMap.put("1214","COOK ISLANDS");
	transMap.put("1215","DOMINICA");
	transMap.put("1216","DOMINICAN REBUBLIC");
	transMap.put("1217","MICRONESIA");
	transMap.put("1218","MONTSERRAT");
	transMap.put("1219","NEVIS");
	transMap.put("1220","ARUBA");
	transMap.put("1221","NORTHERN MARIANA");
	transMap.put("1222","PALAU");
	transMap.put("1223","ST CRISTHOPHER");
	transMap.put("1224","TAHITI");
	transMap.put("1225","WAKE ISLAND");
	transMap.put("1226","WALLIS & FUTUNA");
	transMap.put("1227","GEORGELAND CANADA");
	transMap.put("1228","PUERTO RICO");
	transMap.put("1252","NORFOLK ISLAND");
	transMap.put("1300","EQUADOR");
	transMap.put("1301","SLOVENIA");
	transMap.put("1302","SERBIA");
	transMap.put("1303","WEST AFRICA");
	transMap.put("1304","ARUBA NETH ANT");
	transMap.put("1305","SLOVAKIA");
	transMap.put("1306","MACEDONIA");
	transMap.put("1307","WEST INDIES");
	transMap.put("1308","ADMIRALTY ISLAND");
	transMap.put("1310","ANTIGUA & BARBUDAA");
	transMap.put("1311","ARMENIA");
	transMap.put("1312","AZERBAIJAN");
	transMap.put("1313","BELARUS");
	transMap.put("1314","BRITISH VIRGIN ISLANDS");
	transMap.put("1315","CANAL ZONE");
	transMap.put("1316","CENTRAL AFRICAN REPUBLIC");
	transMap.put("1317","CHRISTMAS ISLAND");
	transMap.put("1318","COCOS ISLAND");
	transMap.put("1319","COMOROS");
	transMap.put("1320","ERITREA");
	transMap.put("1321","FANNING ISLAND");
	transMap.put("1322","FAROE ISLANDS");
	transMap.put("1323","GEORGIA");
	transMap.put("1324","GUINEA BISSAU");
	transMap.put("1325","KAZAKHSTAN");
	transMap.put("1326","KWAJALEIN ISLAND");
	transMap.put("1327","KYRGYZSTAN");
	transMap.put("1328","MACAU");
	transMap.put("1329","MADEIRA");
	transMap.put("1330","MALDIVE ISLANDS");
	transMap.put("1331","MANCHURIA");
	transMap.put("1332","MARIANA ISLANDS");
	transMap.put("1333","MARSHALL ISLANDS");
	transMap.put("1334","MAURITIUS");
	transMap.put("1335","MAYOTTE");
	transMap.put("1336","MOLDOVA");
	transMap.put("1337","MONTENEGRO");
	transMap.put("1338","MONTSERRAT WI");
	transMap.put("1339","MYANMAR");
	transMap.put("1340","NAURU");
	transMap.put("1341","NIUE ISLAND");
	transMap.put("1343","SOUTH KOREA");
	transMap.put("1344","OCEAN ISLAND");
	transMap.put("1345","PHOENIX ISLAND");
	transMap.put("1346","PITCAIRN ISLAND");
	transMap.put("1347","REUNION");
	transMap.put("1348","SPANISH SAHARA");
	transMap.put("1349","TAJIKISTAN");
	transMap.put("1350","TANGIER");
	transMap.put("1351","TIBET");
	transMap.put("1352","TOKELAN ISLAND");
	transMap.put("1353","TRISTAN DA CUNHA");
	transMap.put("1354","TURKMENISTAN");
	transMap.put("1355","UKRAINE");
	transMap.put("1356","UZBEKISTAN");
	transMap.put("1357","WEST CAMEROON");
	transMap.put("1358","ZANZIBAR");
	transMap.put("1400","CROATIA");
					
	
	
%>	

	
<script>

	var CanArray = new Array("AB","BC","MB","NB","NF","NT","NS","ON","PE","QC","SK","YT");
	
	var countryIndex = "";
		
	function clearValue(id, defaultValue){

		if(document.getElementById(id).value == defaultValue){
			document.getElementById(id).value = "";
		}

	}
	
	function reloadDefault(id, defaultValue){
		if(document.getElementById(id).value == ""){
			document.getElementById(id).value = defaultValue;
			}
		}
	
	var formSubmitted = false;
	
	function verifyAddress(form){
	//	document.getElementById("verifyAddress").style.display = "";
			 if(formCheckAddrs(form)){
				 document.forms[0].ADDRCNFRMD.value = "N";
				 return checkSubmit(form);
			 } else {
			    	return false;
		  }
	}
	function checkSubmit(form){
		if (formSubmitted) {
			alert('Please be patient. Your order may take 10 - 15 seconds to process. Thank you!');
			return false;
		} else {
				formSubmitted = true;
				return true; 	
			}
	}		
	
	function formCheckAddrs(formobj){
		
			var errFields = new Array();
			var j = "1";
			var fieldRequired = Array("firstName", "lastName", "address1", 
									  "city","state","zipcode","country");
			// field description to appear in the dialog box
			var fieldDescription = Array("First Name","Last Name","Address",
										 "City","State","Zip Code","Country");
										 
										 var fieldValues = Array("First Name","Last Name","Address 1",
										 "City","State","Zip Code", "Country");	
										 
			// dialog message
			var alertMsg = "Please complete the following fields:\n";	
			var l_Msg = alertMsg.length;
			formobj = document.paymentWidget;
			for (var i = 0; i < fieldRequired.length; i++){
				var obj = formobj.elements[fieldRequired[i]];
				if(obj){
				 if(obj.value == "" || obj.value == null|| obj.value == fieldValues[i]){
					alertMsg += " - " + fieldDescription[i] + "\n";

					
					errFields[j] = fieldRequired[i];
					j++;
				 }
				}
			}
			var countryIndex = document.paymentWidget.country.selectedIndex;
			if (alertMsg.length == l_Msg){
				if (document.paymentWidget.country[countryIndex].value == "US" || document.paymentWidget.country[countryIndex].value == "CANADA"){
				checkState();
				if(checkZip()){ //path - verify zip entered.
					return true; 
				} else{	 //path - invalid zip .
				    return false;
				}}
				else{
					return true;
				}
			}else{  //path - display the mandatory fields and its description to be completed.
				alert(alertMsg);
		//	    document.getElementById(errFields[1]).focus();  
				return false;
			}
			
		}
	
	function checkZip(){
		var zip = document.forms[0].zipcode.value; 
		var statecheck = document.paymentWidget.state.value;
		//path - zip value is numeric for domestic (US) zip code 		
	if((statecheck!="AB") && (statecheck!="BC") && (statecheck!="MB") && (statecheck!="NB") && (statecheck!="NL") && (statecheck!="NT") && (statecheck!="NS") && (statecheck!="NU") && (statecheck!="ON") && (statecheck!="PE") && (statecheck!="QC") && (statecheck!="SK") && (statecheck!="YT")){
		if (zip.length == "5" && zip.match(/^[0-9]{5}$/)) {
				
				return true;
		}
		else{
		
		alert("Please enter a valid Zip Code for US");
					return false;
				}
	}
	}
	
	function checkState() {
		var stateIndex = document.paymentWidget.state.selectedIndex;
		var countryIndex = document.paymentWidget.country.selectedIndex;
		var canState = false;
	
		//Check US state
		if (document.paymentWidget.country[countryIndex].value == "US") {
			for (x=0; x<CanArray.length; x++) {
				if (document.paymentWidget.state.options[stateIndex].value == CanArray[x] || document.paymentWidget.state.options[stateIndex].value == "FN") {
					alert("Invalid US State.");
					break;
				}
			}
		} else {
			//Check Canadian state
			if (document.paymentWidget.country[countryIndex].value == "CANADA") {
				for (x=0; x<CanArray.length; x++) {
					if (document.paymentWidget.state.options[stateIndex].value == CanArray[x] && !(document.paymentWidget.state.options[stateIndex].value == "FN")) {
						canState = true;
						break;
					}
				}
				if (canState == false) {
					alert("Invalid Canadian Province.");
				}
			} else {
				if(document.paymentWidget.state.value=='')
					document.paymentWidget.state.value="FN";
			}
		}
	} 
	
	function isNumeric(evt) {
		//restrict the input key values entered to only numbers 
		     var charCode = (evt.which) ? evt.which : evt.keyCode
		     if (charCode > 31 && (charCode < 48 || charCode > 57))
		        return false;
		// returned values are only numbers
		     return true;
		}

	function submitPayment(form) {
		var formSubmitted = false;
		form = document.paymentWidget;
	//	if(formCheckAddrs(form) && paymentOption(form)){
		if(paymentOption(form)){
		    	return submitCheck(form);
		   } else {
			    return false;
		  }
	}


		function submitCheck(form){
			if (formSubmitted) {
				alert('Please be patient. Your order may take 10 - 15 seconds to process. Thank you!');
				return false;
			}
			else {
				formSubmitted = true;
				return true; 	
			}
		}


		function paymentOption(formobj){
			
		 if(cardPayment(formobj)){
			    return isCardPayment();
		   } 
		}


		function getRadioValue(field) {
			var radioValue = "";
			
			if(document.getElementsByName(field)) {
				var radio = document.getElementsByName(field);
			
				for (var i=0; i<radio.length; i++) {
					if(radio[i].checked) {
						radioValue = radio[i].value;
						break;
					}
				}
			}
			
			return radioValue;
		} // end getRadioValue

		function isPayPalPayment(){
			
			document.forms[0].ccNum.value = "";
			document.forms[0].expMonth.value = "";
			document.forms[0].expYear.value = "";
			document.getElementById("cardType").disabled = true;
			document.getElementById("ccNum").disabled = true;
			document.getElementById("expMonth").disabled = true;
			document.getElementById("expYear").disabled = true;
			var billType =document.forms[0].ccType.value;
			//var billType = getRadioValue("ccType ");
			document.paymentWidget.ccNum.value = "";
			document.paymentWidget.expMonth.value = "";
			document.paymentWidget.expYear.value = "";
			document.paymentWidget.ccType .value = billType;
	//		document.paymentWidget.action ="/wes/servlet/Show?WESPAGE=csp/NYM/paypal_request.jsp&TR=ORD";
			return true;
		}
		function blankCC(){
			document.forms[0].ccNum.value = "";
			document.forms[0].expMonth.value = "";
			document.forms[0].expYear.value = "";
			
		}

		function cardPayment(formobj){
			// dialog message
			var alertMsg = "Please complete the following fields:\n";
			var l_Msg = alertMsg.length;
			var obj = document.forms[0].ccType ;
				if (obj.type == "select-one" && obj.selectedIndex == 0){	
						alertMsg += " - " + "Select Payment Option" + "\n";				
				} else if (obj.type == undefined ){
					var rdochecked = false;
					for (var j = 0; j < obj.length; j++){
						if (obj[j].checked){
							if(obj[j].value=="L"){
								return true;
							}
							rdochecked = true;
						}
					}
					if (!rdochecked){
						alertMsg += " - " + "Select Payment Option" + "\n";
					}
				}
								
			// name of mandatory fields
			var fieldRequired = Array("cardType","ccNum","expMonth", "expYear");
			// field description to appear in the dialog box
			var fieldDescription = null; 
			fieldDescription = Array("Card Type","Card Number","Card Expire Month",
										 "Card Expire year");	
			formobj = document.paymentWidget;
			for (var i = 0; i < fieldRequired.length; i++){
				var obj = formobj.elements[fieldRequired[i]];
				if (obj){	
				 if(obj.value == "" || obj.value == null){
					alertMsg += " - " + fieldDescription[i] + "\n";
				 }
				}
			}
			if (alertMsg.length == l_Msg){
				return isCardExpired();
			}else{
				alert(alertMsg);
				return false;
			}
		}
		function isCardExpired(){	
			<% Calendar rightNow = Calendar.getInstance();  
					int cmonth = rightNow.get(Calendar.MONTH)+ 1;
					int cyear = rightNow.get(Calendar.YEAR);				
			%>
				 if(document.forms[0].expYear.value > <%=cyear%>){
				   return true;
				 }else if(document.forms[0].expYear.value == <%=cyear%>) {
				 		if(document.forms[0].expMonth.value >= <%=cmonth%>){

						return true;
					}else{
						alert( "The credit card you have entered has expired.\n\nPlease use a valid credit card." );
						return false;
					}
			}
		}
		function isCardPayment(){
			  
			  var billdata=document.getElementById("cardType") ;
			  var crdtype="";	  
			  var paypalECRadio = false;
			  var obj = document.forms[0].ccType ;
				if (obj.type == undefined ){
					for (var j = 0; j < obj.length; j++){
						if (obj[j].checked){
							if(obj[j].value=="L"){
								return true;
								paypalECRadio = true;
							}
							
						}
					}
				}
			  
			  if(paypalECRadio){
				  return isValidCard("L");
			  } else {
				  if (billdata.type == "select-one"){	
				  	for (var j = 0; j < billdata.length; j++){
						if (billdata[j].selected){
						crdtype= billdata.options[billdata.selectedIndex].value;
						return isValidCard(crdtype);	
					 	} 
					 }
					} else if (billdata.type == undefined ){	
				  	  for (var j = 0; j < billdata.length; j++){
						if (billdata[j].checked){
						crdtype =  billdata[j].value;
						return isValidCard(crdtype);	
					 	} 
					 }
					} else {
						return isValidCard(billdata.value);
						
					}			
			  } 
		}
			
		function isValidCard(crdtype){
		 	var cc=document.forms[0].ccNum.value;
			switch(crdtype){
				case "A": //check the credit card isAmericanExpress
					if(cc.match(/^3[47][0-9]{13}$/)){ return true; 
					} else {	
						alert("Please check your AMEX CARD Number.");
						document.forms[0].ccNum.focus;
						return false; 
					}
					 break;
				case "S":  //check the credit card isDiscover	
					if(cc.match(/^6(?:011|5[0-9]{2})[0-9]{12}$/)){return true;
					}else {	
						alert("Please check your DISCOVER CARD Number.");
						document.forms[0].ccNum.focus;
						return false;
					}
					 break;
				case "M":  //check the credit card isMasterCard 	
					if(cc.match(/^5[1-5][0-9]{14}$/)){return true; 
					} else {
						alert("Please check your MASTER CARD Number.");
						document.forms[0].ccNum.focus;
						return false; 
					}
					 break;
				case "V": //check the credit card isVisa
					if(cc.match(/^4[0-9]{12}(?:[0-9]{3})?$/)){return true;
					} else {
						  alert("Please check your VISA CARD Number.");
						  document.forms[0].ccNum.focus;
						  return false; 
		   		    }
		   		     break;
				case "D":  // check the credit card isDiners
						if(cc.match(/^3(?:0[0-5]|[68][0-9])[0-9]{11}$/)){ return true; 
						} else {	
							alert("Please check your DINERS CARD Number.");
							document.forms[0].ccNum.focus();
							return false; 
						}
		   		case "L":
		   			return isPayPalPayment();
		   			break;
				default	:
					alert("Please select your credit card type.");
					return false;
					break;	
			} 
		}
		
		function updateAddress(opt){
			if(opt == '2'){
				document.forms[0].firstName.value = document.forms[0].rfirstName.value;
				document.forms[0].lastName.value = document.forms[0].rlastName.value;
				document.forms[0].address1.value = document.forms[0].outputAddr1.value;
				document.forms[0].address2.value = document.forms[0].outputAddr2.value;
				document.forms[0].city.value = document.forms[0].outputCity.value;
				document.forms[0].state.value = document.forms[0].outputState.value;
				document.forms[0].zipcode.value = document.forms[0].outputZip.value;
			} else {
				document.forms[0].firstName.value = document.forms[0].ifirstName.value;
				document.forms[0].lastName.value = document.forms[0].ilastName.value;
				document.forms[0].address1.value = document.forms[0].inputAddr1.value;
				document.forms[0].address2.value = document.forms[0].inputAddr2.value;
				document.forms[0].city.value = document.forms[0].inputCity.value;
				document.forms[0].state.value = document.forms[0].inputState.value;
				document.forms[0].zipcode.value = document.forms[0].inputZip.value;
			}
		}
		
 </script>
	
	
  </head>
  <body >
  
	
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
       <div onmouseover="this.style.cursor='pointer'" onclick="logout()" class="navbar-brand logo" style="cursor: pointer;"></div>
				<div class="banner-text">
				</div>
      </div>
    </div>
    
   
    <form name="paymentWidget" id="paymentWidget" method="post"  action="/ePayG/WidgetServlet" AUTOCOMPLETE="OFF">
    
    <div class="container-fluid">
    	<div class="row">
	        <div class="col-lg-18 col-md-18 col-sm-18 layout-col-1">
				<div class="panel panel-single tcs-center-panel">
					<div class="panel-heading">
						
							<% String cust_Country =null;
								String t_country = request.getParameter("customer_country");
								if(request.getParameter("temp_country") != null && !request.getParameter("temp_country").equals("")){
									t_country = request.getParameter("temp_country");
								}
								
								String int_country = request.getParameter("customer_country");
								if((request.getParameter("customer_country")!=null && request.getParameter("customer_country").equalsIgnoreCase("0000")) || 
										(request.getParameter("temp_country")!=null && request.getParameter("temp_country").equalsIgnoreCase("0000"))){
									cust_Country="US";
								}else if((request.getParameter("customer_country")!=null && request.getParameter("customer_country").equalsIgnoreCase("0001")) || 
								(request.getParameter("temp_country")!=null && request.getParameter("temp_country").equalsIgnoreCase("0001"))){
									cust_Country="CA";
								}else if((request.getParameter("customer_country")!=null && request.getParameter("customer_country").equalsIgnoreCase("0002")) || 
								(request.getParameter("temp_country")!=null && request.getParameter("temp_country").equalsIgnoreCase("0002"))){
									cust_Country="GB";
								}else if((request.getParameter("customer_country")!=null && request.getParameter("customer_country").equalsIgnoreCase("1072")) || 
								(request.getParameter("temp_country")!=null && request.getParameter("temp_country").equalsIgnoreCase("1072"))){
									cust_Country="UK";
								}else if(request.getParameter("customer_country")!=null && transMap.containsKey(request.getParameter("customer_country"))){
									cust_Country= transMap.get(request.getParameter("customer_country"));	
								} else {
									if(cust_Country == null && request.getParameter("temp_country")!=null){
										cust_Country= transMap.get(request.getParameter("temp_country"));	
									}
								}



							String srt_fname = request.getParameter("customer_name") == null ? "" : (String)request.getParameter("customer_name");
							String srt_lname = request.getParameter("customer_last_name") == null ? "" : (String)request.getParameter("customer_last_name");
							String srt_addr1 = request.getParameter("customer_address") == null ? "" : (String)request.getParameter("customer_address");
							String srt_addr2 = request.getParameter("customer_address1") == null ? "" : (String)request.getParameter("customer_address1");
							String srt_city = request.getParameter("customer_city") == null ? "" : (String)request.getParameter("customer_city");
							String srt_state = request.getParameter("customer_state") == null ? "" : (String)request.getParameter("customer_state");
							String srt_zipcode = request.getParameter("customer_zip") == null ? "" : (String)request.getParameter("customer_zip");
							String srt_country = request.getParameter("customer_country") == null ? "" : int_country;
							System.out.println("Country is "+srt_country);
							
							%>				

							<!-- <div class="col-lg-18 col-md-18 col-sm-18 col-xs-18 panel-title2">
							 	PLEASE ENTER/UPDATE YOUR BILLING ADDRESS AND VALIDATE THE SAME
								Hi <%= srt_fname %> <%= srt_lname %>, Please refer the below address and do the payment for your order.
							</div>	-->
					</div>
					
					<% if ( addrMsg != null) { %>
					<br><br>
							<h4 style="color: red;"><%= request.getParameter("addressMessage") %></h4>
						<% } %>
								
					<div class="panel-body">
						<div class="row row-field" id="verifyAddress" >
						<div class="col-lg-9 col-md-9 col-sm-18 address">
							<%= srt_fname %> <%= srt_lname %>,<br>
							<% if(!srt_addr1.equals("")) {%><%= srt_addr1 %>,<br><%} %>
							<% if(!srt_addr2.equals("")) {%><%= srt_addr2 %>,<br><%} %>
							<%= srt_city %><% if(!srt_state.equals("")) {%>, <%= srt_state %><%} %>, <%= srt_zipcode %>,<br>
							<% if(!srt_country.equals("")) {%><%= cust_Country %><br><%} %>
						</div>
						</div>
						<input type="hidden" name="inputAddr1" value="">
						<input type="hidden" name="inputAddr2" value="">
						<input type="hidden" name="inputCity" value="">
						<input type="hidden" name="inputState" value="">
						<input type="hidden" name="inputZip" value="">
						
						<input type="hidden" name="outputAddr1" value="">
						<input type="hidden" name="outputAddr2" value="">
						<input type="hidden" name="outputCity" value="">
						<input type="hidden" name="outputState" value="">
						<input type="hidden" name="outputZip" value="">
						<input type="hidden" name="firstName" value="<%= srt_fname %>">
							<input type="hidden" name="lastName" value="<%= srt_lname %>">
							<input type="hidden" name="customer_address" value="<%= srt_addr1 %>">
							<input type="hidden" name="customer_address1" value="<%= srt_addr2 %>">
							<input type="hidden" name="city" value="<%= srt_city %>">
							<input type="hidden" name="state" value="<%= srt_state %>">
							<input type="hidden" name="zipcode" value="<%= srt_zipcode %>">
							<input type="hidden" name="country" value="<%= cust_Country %>">
							<input type="hidden" name="temp_country" value="<%= t_country %>">
							<input type="hidden" name="customer_country" value="<%= request.getParameter("customer_country") %>">
						
						<!-- 
						< %
						String srt_fname = request.getParameter("firstName") == null ? "" : (String)request.getParameter("firstName");
						String srt_lname = request.getParameter("lastName") == null ? "" : (String)request.getParameter("lastName");
						String srt_addr1 = request.getParameter("address1") == null ? "" : (String)request.getParameter("address1");
						String srt_addr2 = request.getParameter("address2") == null ? "" : (String)request.getParameter("address2");
						String srt_city = request.getParameter("city") == null ? "" : (String)request.getParameter("city");
						String srt_state = request.getParameter("state") == null ? "" : (String)request.getParameter("state");
						String srt_zipcode = request.getParameter("zipcode") == null ? "" : (String)request.getParameter("zipcode");
						
						String srt_opaddr1 = request.getParameter("outputAddr1") == null ? "" : (String)request.getParameter("outputAddr1");
						String srt_opcity = request.getParameter("outputCity") == null ? "" : (String)request.getParameter("outputCity");
						String srt_opstate = request.getParameter("outputState") == null ? "" : (String)request.getParameter("outputState");
					
					
						if( addressFlag != null && addressFlag.equalsIgnoreCase("N") && srt_opaddr1 != null && !srt_opaddr1.equals("") && srt_opcity != null && !srt_opcity.equals("") && srt_opstate != null && !srt_opstate.equals("")){ %>
						<div class="row" id="verifyAddress" >
						<br><br>
						<div class="col-lg-9 col-md-9 col-sm-18 ">
						<div class="row"> <input type ="radio" name ="addressVerify" id ="inputAddress"  checked="checked" onclick="updateAddress('1')"/><strong>Entered Shipping Address</strong> </div>
						<div class="col-lg-9 col-md-9 col-sm-18 ">
						<div class="row"><label for="ifirstName">First Name</label>
						<input name="ifirstName" type="text" id="ifirstName" readonly="readonly" value ="<%= request.getParameter("firstName") %>" size="35" maxlength="16" tabindex="1"/></div>
						<div class="row"><label for="ilastName">Last Name</label>
						<input size="35"  name="ilastName" type="text" id="ilastName" readonly="readonly" value ="<%= request.getParameter("lastName") %>" maxlength="16" tabindex="3" /></div>
						<div class="row"><label for="inputAddr1">Address 1</label>
						<input name="inputAddr1" size="35"  type="text" id="inputAddr1" readonly="readonly"  value ="<%= request.getParameter("inputAddr1") %>" maxlength="30" tabindex="5" /></div>
						<div class="row"><label for="inputAddr2">Address 2</label>
						<input name="inputAddr2" size="35"  type="text" id="inputAddr2" readonly="readonly" value = "<%= request.getParameter("inputAddr2") %>" maxlength="30" tabindex="6" /></div>
						<div class="row"><label for="inputCity">City</label>
						<input name="inputCity" type="text" size="35" id="inputCity"  readonly="readonly" value ="<%= request.getParameter("inputCity") %>" maxlength="30" tabindex="9" /></div>
						<div class="row"><label for="inputState">State</label>
						<input name="inputState" type="text" size="35" id="inputState" readonly="readonly" value ="<%= request.getParameter("inputState") %>" tabindex="12" /></div>
						<div class="row"><label for="inputZip">Zip Code</label>
						<input name="inputZip" type="text" id="inputZip" readonly="readonly" value ="<%= request.getParameter("inputZip") %>" size="35" maxlength="14" tabindex="15" /></div>
						</div>
						</div>
											
						<div class="col-lg-9 col-md-9 col-sm-18 ">
						<div class="row"> <input type ="radio" name ="addressVerify" id ="recommendedAddress" onclick="updateAddress('2')"/><strong>Recommended Shipping Address</strong> </div>
						<div class="col-lg-9 col-md-9 col-sm-18 ">
						<div class="row"><label for="rfirstName">First Name</label>
						<input name="rfirstName" type="text" id="rfirstName" readonly="readonly" value ="<%= request.getParameter("firstName") %>" size="35" maxlength="16" tabindex="2"/></div>
						<div class="row"><label for="rlastName">Last Name</label>
						<input size="35"  name="rlastName" type="text" id="rlastName" readonly="readonly" value ="<%= request.getParameter("lastName") %>" maxlength="16" tabindex="4" /></div>
						<div class="row"><label for="outputAddr1">Address 1</label>
						<input name="outputAddr1" size="35"  type="text" id="outputAddr1" readonly="readonly" value = "<%= request.getParameter("outputAddr1") %>" maxlength="30" tabindex="6" /></div>
						<div class="row"><label for="outputAddr2">Address 2</label>
						<input name="outputAddr2" size="35"  type="text" id="outputAddr2" readonly="readonly" value = "<%= request.getParameter("outputAddr2") %>" maxlength="30" tabindex="8" /></div>
						<div class="row"><label for="outputCity">City</label>
						<input name="outputCity" type="text" size="35"  id="outputCity" readonly="readonly"  value ="<%= request.getParameter("outputCity") %>" maxlength="30" tabindex="10" /></div>
						<div class="row"><label for="outputState">State</label>
						<input name="outputState" type="text" size="35"  id="outputState" readonly="readonly" value ="<%= request.getParameter("outputState") %>" tabindex="12" /></div>
						<div class="row"><label for="outputZip">Zip Code</label>
						<input name="outputZip" type="text" id="outputZip" readonly="readonly" value ="<%= request.getParameter("outputZip") %>" size="35" maxlength="14" tabindex="16" /></div>
						</div>
						</div>
						</div>
							<input type="hidden" name="firstName" value="<%= srt_fname %>">
							<input type="hidden" name="lastName" value="<%= srt_lname %>">
							<input type="hidden" name="address1" value="<%= srt_addr1 %>">
							<input type="hidden" name="address2" value="<%= srt_addr2 %>">
							<input type="hidden" name="city" value="<%= srt_city %>">
							<input type="hidden" name="state" value="<%= srt_state %>">
							<input type="hidden" name="zipcode" value="<%= srt_zipcode %>">
							
						< % } else {
						
							srt_fname = request.getParameter("firstName") == null ? "" : (String)request.getParameter("customer_name");
							srt_lname = request.getParameter("lastName") == null ? "" : (String)request.getParameter("customer_last_name");
							srt_addr1 = request.getParameter("address1") == null ? "" : (String)request.getParameter("customer_address");
							srt_addr2 = request.getParameter("address2") == null ? "" : (String)request.getParameter("customer_address1");
							srt_city = request.getParameter("city") == null ? "" : (String)request.getParameter("customer_city");
							srt_state = request.getParameter("state") == null ? "" : (String)request.getParameter("cudtomer_state");
							srt_zipcode = request.getParameter("zipcode") == null ? "" : (String)request.getParameter("customer_zip");
							srt_zipcode = request.getParameter("country") == null ? "" : (String)request.getParameter("customer_country");

						%>
						
						<div id="inputForm">
						<div class="row row-field">
							<div class=" " style="" >First Name:</div>
							<div class=" " style=" "><input name="firstName" id="firstName" type="text" class="input" maxlength="16" value="<%= srt_fname %>"/></div>
						</div>
						<div class="row row-field">
							<div class=" " style="" >Last Name:</div>
							<div class=" " style=" "><input name="lastName" id="lastName" type="text" class="input" maxlength="16"value="<%= srt_lname %>"/></div>
						</div>
						<div class="row row-field">
							<div class=" "style="" >Address 1:</div>
							<div class=" " style=" "> <input name="address1" id="address1" type="text" class="input" maxlength="30"value="<%= srt_addr1 %>"/></div>
						</div>
						<div class="row row-field">
							<div class=" "style="" >Address 2:</div>
							<div class=" " style=" "><input name="address2" id="address2" type="text" class="input" maxlength="30" value="<%= srt_addr2 %>"/></div>
						</div>
						<div class="row row-field">
							<div class=" "style="" >City:</div>
							<div class=" " style=" "><input name="city" id="city" type="text" class="input" maxlength="30"value="<%= srt_city %>"/></div>
						</div>
						<div class="row row-field">
							<div class=" "style="" >State:</div>
							<div class=" " style=" ">
								<select name="state" id="state" style="color: #808080;" >
											<option value="FN" selected="selected">Select</OPTION>      
											<% 
											if(!srt_state.equals("")){ %> 
												<OPTION value="<%= srt_state %>" selected="selected"><%= srt_state %></OPTION>
											<% } %>  
  											<OPTION value=""></OPTION>
  										  <option value="AL">Alabama</option>
								          <option value="AK">Alaska</option>
								          <option value="AB">Alberta</option>
								          <option value="AZ">Arizona</option>
								          <option value="AR">Arkansas</option>
								          <option value="AF">Armed Forces Africa</option>
								          <option value="AA">Armed Forces Americas</option>
								          <option value="AC">Armed Forces Canada</option>
								          <option value="AE">Armed Forces Europe</option>
								          <option value="AM">Armed Forces Middle East</option>
								          <option value="AP">Armed Forces Pacific</option>
								          <option value="BC">British Columbia</option>
								          <option value="CA">California</option>
								          <option value="CO">Colorado</option>
								          <option value="CT">Connecticut</option>
								          <option value="DE">Delaware</option>
								          <option value="DC">District of Columbia</option>
								          <option value="FL">Florida</option>
								          <option value="GA">Georgia</option>
								          <option value="HI">Hawaii</option>
								          <option value="ID">Idaho</option>
								          <option value="IL">Illinois</option>
								          <option value="IN">Indiana</option>
								          <option value="IA">Iowa</option>
								          <option value="KS">Kansas</option>
								          <option value="KY">Kentucky</option>
								          <option value="LA">Louisiana</option>
								          <option value="ME">Maine</option>
								          <option value="MB">Manitoba</option>
								          <option value="MD">Maryland</option>
								          <option value="MA">Massachusetts</option>
								          <option value="MI">Michigan</option>
								          <option value="MN">Minnesota</option>
								          <option value="MS">Mississippi</option>
								          <option value="MO">Missouri</option>
								          <option value="MT">Montana</option>
								          <option value="NE">Nebraska</option>
								          <option value="NV">Nevada</option>
								          <option value="NB">New Brunswick</option>
								          <option value="NH">New Hampshire</option>
								          <option value="NJ">New Jersey</option>
								          <option value="NM">New Mexico</option>
								          <option value="NY">New York</option>
								          <option value="NF">Newfoundland</option>
								          <option value="NC">North Carolina</option>
								          <option value="ND">North Dakota</option>
								          <option value="NT">Northwest Territories</option>
								          <option value="NS">Nova Scotia</option>
								          <option value="NU">Nunavut</option>
								          <option value="OH">Ohio</option>
								          <option value="OK">Oklahoma</option>
								          <option value="ON">Ontario</option>
								          <option value="OR">Oregon</option>
								          <option value="PA">Pennsylvania</option>
								          <option value="PE">Prince Edward Island</option>
								          <option value="QC">Quebec</option>
								          <option value="RI">Rhode Island</option>
								          <option value="SK">Saskatchewan</option>
								          <option value="SC">South Carolina</option>
								          <option value="SD">South Dakota</option>
								          <option value="TN">Tennessee</option>
								          <option value="TX">Texas</option>
								          <option value="UT">Utah</option>
								          <option value="VT">Vermont</option>
								          <option value="VA">Virginia</option>
								          <option value="WA">Washington</option>
								          <option value="WV">West Virginia</option>
								          <option value="WI">Wisconsin</option>
								          <option value="WY">Wyoming</option>
								          <option value="YT">Yukon Territory</option>
										  
										</select>
							</div>
						</div>
						<div class="row row-field">
							<div class=" "style="" >Zip Code:</div>
							<div class=" " style=" "><input name="zipcode" id="zipcode" type="text" class="input" maxlength="14" value="<%= srt_zipcode %>"/></div>
						</div>
						
						<div class="row row-field">
							<div class=" "style="" >Country:</div>
							<select name="country" id="country" style="color: #808080;" onchange="this.form.verifyBtn.style.display = (this.value!='US') ? 'none' : 'inline';">
							<OPTION selected="selected" value="">Select</OPTION>  
							<option id="US" value="US"> United States</option>
          					<option value="CANADA"> Canada</option>
				            <option value="">-------------------------------</option>
				            <option value="">Select a Country</option>
							<option value="ADMIRALTY ISLAND">ADMIRALTY ISLAND</option>
							<option value="ALBANIA">ALBANIA</option>
							<option value="ALGERIA">ALGERIA</option>
							<option value="AMERICAN SAMOA">AMERICAN SAMOA</option>
							<option value="ANDORRA">ANDORRA</option>
							<option value="ANGOLA">ANGOLA</option>
							<option value="ANTIGUA &amp; BARBUDA">ANTIGUA & BARBUDA</option>
							<option value="US">APO AA</option>
							<option value="US">APO AE</option>
							<option value="US">APO/FPO AP</option>
							<option value="ARGENTINA">ARGENTINA</option>
							<option value="ARMENIA">ARMENIA</option>
							<option value="ARUBA NETH ANT">ARUBA NETH ANT</option>
							<option value="ASCENSION ISLANDS">ASCENSION ISLANDS</option>
							<option value="AUSTRALIA">AUSTRALIA</option>
							<option value="AUSTRIA">AUSTRIA</option>
							<option value="AZERBAIJAN">AZERBAIJAN</option>
							<option value="AZORES">AZORES</option>
							<option value="BAHAMAS">BAHAMAS</option>
							<option value="BAHRAIN">BAHRAIN</option>
							<option value="BANGLADESH">BANGLADESH</option>
							<option value="WEST INDIES">BARBADOS WI</option>
							<option value="BELARUS">BELARUS</option>
							<option value="BELGIUM">BELGIUM</option>
							<option value="BELIZE">BELIZE</option>
							<option value="BENIN">BENIN</option>
							<option value="BERMUDA">BERMUDA</option>
							<option value="BHUTAN">BHUTAN</option>
							<option value="BOLIVIA">BOLIVIA</option>
							<option value="NETHERLANDS ANTILLES">BONAIRE NETH ANT</option>
							<option value="YUGOSLAVIA">BOSNIA HERZEGOVINA</option>
							<option value="BOTSWANA">BOTSWANA</option>
							<option value="BRAZIL">BRAZIL</option>
							<option value="BRITISH VIRGIN ISLANDS">BRITISH VIRGIN ISLANDS</option>
							<option value="BRUNEI">BRUNEI</option>
							<option value="BULGARIA">BULGARIA</option>
							<option value="BURKINA FASO">BURKINA FASO</option>
							<option value="BURUNDI">BURUNDI</option>
							<option value="CAMBODIA">CAMBODIA</option>
							<option value="CAMEROON">CAMEROON</option>
							<option value="CANAL ZONE">CANAL ZONE</option>
							<option value="CANARY ISLANDS">CANARY ISLANDS</option>
							<option value="CAPE VERDE">CAPE VERDE</option>
							<option value="CAROLINE ISLANDS">CAROLINE ISLANDS</option>
							<option value="CAYMAN ISLANDS">CAYMAN ISLANDS WI</option>
							<option value="CENTRAL AFRICAN REPUBLIC">CENTRAL AFRICAN REPUBLIC</option>
							<option value="CHAD">CHAD</option>
							<option value="CHILE">CHILE</option>
							<option value="CHINA">CHINA</option>
							<option value="CHRISTMAS ISLAND">CHRISTMAS ISLAND</option>
							<option value="COCOS ISLAND">COCOS ISLAND</option>
							<option value="COLOMBIA">COLOMBIA</option>
							<option value="COMOROS">COMOROS</option>
							<option value="CONGO">CONGO</option>
							<option value="COOK ISLANDS">COOK ISLANDS</option>
							<option value="COSTA RICA">COSTA RICA</option>
							<option value="CROATIA">CROATIA</option>
							<option value="CUBA">CUBA</option>
							<option value="NETHERLANDS ANTILLES">CURACAO NETH ANT</option>
							<option value="CYPRUS">CYPRUS</option>
							<option value="CZECHOSLOVAKIA">CZECH REPUBLIC</option>
							<option value="CONGO">DEMOCRATIC REP. OF CONGO</option>
							<option value="YEMEN ARAB REP">DEMOCRATIC REP. OF YEMEN</option>
							<option value="DENMARK">DENMARK</option>
							<option value="DJIBOUTI">DJIBOUTI</option>
							<option value="DOMINICA WI">DOMINICA WI</option>
							<option value="DOMINICAN REPUBLIC">DOMINICAN REPUBLIC</option>
							<option value="ECUADOR">ECUADOR</option>
							<option value="EGYPT">EGYPT</option>
							<option value="EL SALVADOR">EL SALVADOR</option>
							<option value="ENGLAND">ENGLAND</option>
							<option value="EQUATORIAL GUINEA">EQUATORIAL GUINEA</option>
							<option value="ERITREA">ERITREA</option>
							<option value="ESTONIA">ESTONIA</option>
							<option value="ETHIOPIA">ETHIOPIA</option>
							<option value="FALKLAND ISLANDS">FALKLAND ISLANDS</option>
							<option value="FANNING ISLAND">FANNING ISLAND</option>
							<option value="FAROE ISLANDS">FAROE ISLANDS</option>
							<option value="FIJI">FIJI ISLANDS</option>
							<option value="FINLAND">FINLAND</option>
							<option value="US">FPO AA</option>
							<option value="US">FPO AE</option>
							<option value="FRANCE">FRANCE</option>
							<option value="GUYANA">FRENCH GUYANA</option>
							<option value="FRENCH POLYNESIA">FRENCH POLYNESIA</option>
							<option value="GABON">GABON</option>
							<option value="GAMBIA">GAMBIA</option>
							<option value="GEORGIA">GEORGIA</option>
							<option value="GERMANY">GERMANY</option>
							<option value="GHANA">GHANA</option>
							<option value="GIBRALTAR">GIBRALTAR</option>
							<option value="GREECE">GREECE</option>
							<option value="GREENLAND">GREENLAND</option>
							<option value="GRENADA">GRENADA WI</option>
							<option value="GUADELOUPE">GUADELOUPE FWI</option>
							<option value="GUAM">GUAM</option>
							<option value="GUATEMALA">GUATEMALA</option>
							<option value="GUINEA">GUINEA</option>
							<option value="GUINEA BISSAU">GUINEA BISSAU</option>
							<option value="GUYANA">GUYANA</option>
							<option value="HAITI">HAITI</option>
							<option value="HONDURAS">HONDURAS</option>
							<option value="HONG KONG">HONG KONG</option>
							<option value="HUNGARY">HUNGARY</option>
							<option value="ICELAND">ICELAND</option>
							<option value="INDIA">INDIA</option>
							<option value="INDONESIA">INDONESIA</option>
							<option value="IRELAND">IRELAND</option>
							<option value="IRIAN JAYA INDONESIA">IRIAN JAYA INDONESIA</option>
							<option value="ISRAEL">ISRAEL</option>
							<option value="ITALY">ITALY</option>
							<option value="IVORY COAST">IVORY COAST</option>
							<option value="JAMAICA">JAMAICA WI</option>
							<option value="JAPAN">JAPAN</option>
							<option value="JORDAN">JORDAN</option>
							<option value="KAZAKHSTAN">KAZAKHSTAN</option>
							<option value="KENYA">KENYA</option>
							<option value="KIRIBATI">KIRIBATI</option>
							<option value="KWAJALEIN ISLAND">KWAJALEIN ISLAND</option>
							<option value="KYRGYZSTAN">KYRGYZSTAN</option>
							<option value="LAOS">LAOS</option>
							<option value="LATVIA">LATVIA</option>
							<option value="LEBANON">LEBANON</option>
							<option value="LESOTHO">LESOTHO</option>
							<option value="LIBERIA">LIBERIA</option>
							<option value="LIBYA">LIBYA</option>
							<option value="LIECHTENSTEIN">LIECHTENSTEIN</option>
							<option value="LITHUANIA">LITHUANIA</option>
							<option value="LUXEMBOURG">LUXEMBOURG</option>
							<option value="MACAU">MACAU</option>
							<option value="MACEDONIA">MACEDONIA</option>
							<option value="MADAGASCAR">MADAGASCAR</option>
							<option value="MADEIRA">MADEIRA</option>
							<option value="MALAWI">MALAWI</option>
							<option value="MALAYSIA">MALAYSIA</option>
							<option value="MALDIVE ISLANDS">MALDIVE ISLANDS</option>
							<option value="MALI">MALI</option>
							<option value="MALTA">MALTA</option>
							<option value="MANCHURIA">MANCHURIA</option>
							<option value="MARIANA ISLANDS">MARIANA ISLANDS</option>
							<option value="MARSHALL ISLANDS">MARSHALL ISLANDS</option>
							<option value="MARTINIQUE">MARTINIQUE FWI</option>
							<option value="MAURITANIA">MAURITANIA</option>
							<option value="MAURITIUS">MAURITIUS</option>
							<option value="MAYOTTE">MAYOTTE</option>
							<option value="MEXICO">MEXICO</option>
							<option value="MOLDOVA">MOLDOVA</option>
							<option value="MONACO">MONACO</option>
							<option value="MONGOLIA">MONGOLIA</option>
							<option value="MONTENEGRO">MONTENEGRO</option>
							<option value="MONTSERRAT WI">MONTSERRAT WI</option>
							<option value="MOROCCO">MOROCCO</option>
							<option value="MOZAMBIQUE">MOZAMBIQUE</option>
							<option value="MYANMAR">MYANMAR</option>
							<option value="NAMIBIA">NAMIBIA</option>
							<option value="NAURU">NAURU</option>
							<option value="NEPAL">NEPAL</option>
							<option value="NETHERLANDS HOLLAND">NETHERLANDS</option>
							<option value="NEW CALEDONIA">NEW CALEDONIA</option>
							<option value="NEW ZEALAND">NEW ZEALAND</option>
							<option value="NICARAGUA">NICARAGUA</option>
							<option value="NIGER">NIGER</option>
							<option value="NIGERIA">NIGERIA</option>
							<option value="NIUE ISLAND">NIUE ISLAND</option>
							<option value="NORFOLK ISLAND">NORFOLK ISLAND</option>
							<option value="NORTH KOREA">NORTH KOREA</option>
							<option value="NORTHERN IRELAND">NORTHERN IRELAND</option>
							<option value="NORWAY">NORWAY</option>
							<option value="OCEAN ISLAND">OCEAN ISLAND</option>
							<option value="OMAN">OMAN</option>
							<option value="PANAMA">PANAMA</option>
							<option value="PAPUA NEW GUINEA">PAPUA NEW GUINEA</option>
							<option value="PARAGUAY">PARAGUAY</option>
							<option value="PERU">PERU</option>
							<option value="PHILIPPINES">PHILIPPINES</option>
							<option value="PHOENIX ISLAND">PHOENIX ISLAND</option>
							<option value="PITCAIRN ISLAND">PITCAIRN ISLAND</option>
							<option value="POLAND">POLAND</option>
							<option value="PORT TIMOR">PORT TIMOR</option>
							<option value="PORTUGAL">PORTUGAL</option>
							<option value="PUERTO RICO">PUERTO RICO</option>
							<option value="QATAR">QATAR</option>
							<option value="REUNION">REUNION</option>
							<option value="ROMANIA">ROMANIA</option>
							<option value="UNION SOVIET SOC REP">RUSSIA</option>
							<option value="RWANDA">RWANDA</option>
							<option value="NETHERLANDS ANTILLES">SABA NETH ANT</option>
							<option value="NETHERLANDS ANTILLES">SAINT EUSTATIUS NETH ANT</option>
							<option value="SAINT HELENA">SAINT HELENA</option>
							<option value="NETHERLANDS ANTILLES">SAINT LUCIA WI</option>
							<option value="NETHERLANDS ANTILLES">SAINT MAARTEN NETH ANT</option>
							<option value="CANADA">SAINT PIERRE MIQUELON</option>
							<option value="WEST INDIES">SAINT VINCENT WI</option>
							<option value="WEST INDIES">SAMOA</option>
							<option value="SAO TOME ET PRINCIPE">SAO TOME ET PRINCIPE</option>
							<option value="SAUDI ARABIA">SAUDI ARABIA</option>
							<option value="SCOTLAND">SCOTLAND</option>
							<option value="SENEGAL">SENEGAL</option>
							<option value="SERBIA">SERBIA</option>
							<option value="SEYCHELLES">SEYCHELLES</option>
							<option value="SIERRA LEONE">SIERRA LEONE</option>
							<option value="SINGAPORE">REP OF SINGAPORE</option>
							<option value="SLOVAKIA">SLOVAKIA</option>
							<option value="SLOVENIA">SLOVENIA</option>
							<option value="SOLOMON ISLANDS">SOLOMON ISLAND</option>
							<option value="SOMALIA NORTHERN REG">SOMALIA</option>
							<option value="SOUTH AFRICA">REPUBLIC OF SOUTH AFRICA</option>
							<option value="SOUTH KOREA">SOUTH KOREA</option>
							<option value="SPAIN">SPAIN</option>
							<option value="SPANISH SAHARA">SPANISH SAHARA</option>
							<option value="SRI LANKA">SRI LANKA</option>
							<option value="SUDAN">SUDAN</option>
							<option value="SURINAME">SURINAME</option>
							<option value="SWAZILAND">SWAZILAND</option>
							<option value="SWEDEN">SWEDEN</option>
							<option value="SWITZERLAND">SWITZERLAND</option>
							<option value="SYRIA">SYRIA</option>
							<option value="TAIWAN">TAIWAN</option>
							<option value="TAJIKISTAN">TAJIKISTAN</option>
							<option value="TANGIER">TANGIER</option>
							<option value="TANZANIA">TANZANIA</option>
							<option value="THAILAND">THAILAND</option>
							<option value="TIBET">TIBET</option>
							<option value="TOGO">TOGO</option>
							<option value="TOKELAN ISLAND">TOKELAN ISLAND</option>
							<option value="TONGA">TONGA</option>
							<option value="WEST INDIES">TRINIDAD WI</option>
							<option value="TRISTAN DA CUNHA">TRISTAN DA CUNHA</option>
							<option value="TUNISIA">TUNISIA</option>
							<option value="TURKEY">TURKEY</option>
							<option value="TURKMENISTAN">TURKMENISTAN</option>
							<option value="TURKS &amp; CAICOS ISLANDS">TURKS & CAICOS ISLANDS</option>
							<option value="TUVALU">TUVALU</option>
							<option value="UNITED ARAB EMIRATES">UAE</option>
							<option value="UGANDA">UGANDA</option>
							<option value="UKRAINE">UKRAINE</option>
							<option value="URUGUAY">URUGUAY</option>
							<option value="UZBEKISTAN">UZBEKISTAN</option>
							<option value="VANUATU">VANUATU</option>
							<option value="VENEZUELA">VENEZUELA</option>
							<option value="VIETNAM">VIETNAM</option>
							<option value="U.S. VIRGIN ISLANDS">VIRGIN ISLANDS</option>
							<option value="WAKE ISLAND">WAKE ISLAND</option>
							<option value="WALES">WALES</option>
							<option value="WEST CAMEROON">WEST CAMEROON</option>
							<option value="WEST INDIES">WEST INDIES</option>
							<option value="YEMEN ARAB REP">REPUBLIC OF YEMEN</option>
							<option value="ZAMBIA">ZAMBIA</option>
							<option value="ZANZIBAR">ZANZIBAR</option>
							<option value="ZIMBABWE">ZIMBABWE</option>
							
							</select>
						</div>
						
						</div>
						<input type="hidden" name="inputAddr1" value="">
						<input type="hidden" name="inputAddr2" value="">
						<input type="hidden" name="inputCity" value="">
						<input type="hidden" name="inputState" value="">
						<input type="hidden" name="inputZip" value="">
						
						<input type="hidden" name="outputAddr1" value="">
						<input type="hidden" name="outputAddr2" value="">
						<input type="hidden" name="outputCity" value="">
						<input type="hidden" name="outputState" value="">
						<input type="hidden" name="outputZip" value="">
						
						
						<div id="verifyAddrButton" class="row row-skip-2">
							<input name="submit" id="verifyBtn" type="submit" value="Verify Address" onClick="return verifyAddress()"/>	
						</div>
										
						< %} %>
							 -->				
																		
						<div class="row row-skip-1">
							<h3>Pay your bill quickly and securely!</h3>
							<h6 class="gray-dark-2">This site is secure for all debit/credit card transactions</h6>
						</div>
						
						<div class="row row-skip-2">
									
						<% if( paymentAmount != null && !paymentAmount.equals("")) { %>
								<b>
								Payable Amount: <%= paymentAmount %></b>
								<br>
								<% } %>
								
						<% if( payMentFlag != null && payMentFlag.equalsIgnoreCase("N")){ %>
							<h4 style="color: red;">Please verify your credit/debit card details</h4>
						
						<% } %>
							<h4>Select your payment method:</h4>
						</div>
						<div class="row payDetails">
							
							<div class="col-lg-5 col-md-8 col-sm-9 col-xs-16 ">
								<div class="row" style="margin-bottom: 7px;">
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-right">
										<input type="hidden" name="ccType" id="ccType"  value="card" ></div>
									<div class="col-lg-16 col-md-16 col-sm-16 col-xs-15 no-left-space"><strong>Credit/Debit Card</strong></div>
								</div>
								<div id="payCard" class="row">
									<div class="col-lg-9 col-md-9 col-sm-12 col-xs-15 col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
										<div class="row" style="padding-bottom: 7px">
											<img src="/ePayG/images/icons/payment/Visa.png">
											<img src="/ePayG/images/icons/payment/Mastercard.png">
											<img src="/ePayG/images/icons/payment/American-Express.png">
											<img src="/ePayG/images/icons/payment/Discover-Network.png">
										</div>
										<div class="row">
											<select name="cardType" id="cardType" style="color: #808080; padding: 5px;" onFocus="" onBlur="">
												<option value="">Select Payment</option>
												<option value="V">Visa</option>
												<option value="M">Mastercard</option>
												<option value="A">American Express</option>
												<option value="S">Discover</option>
											</select>
										</div>
										<div class="row" style="padding: 7px 0px;">
											<input name="ccNum" id="ccNum" type="text" value="" placeholder="Card Number" maxlength="20" style="color: #808080; padding: 5px;" onclick="clearValue('ccNum','Card Number')"; onFocus="" onblur="reloadDefault('ccNum','Card Number')"; onkeypress="return isNumeric(event)" onkeypress="clearValue('ccNum','Card Number')" >
										</div>
										<div class="row" style="padding-bottom: 30px">
											<select name="expMonth" id="expMonth" style="color: #808080; padding: 5px;" onFocus="" onBlur="">
												<option value="">- Month -</option>
													<OPTION value="01">01</OPTION>
													<OPTION value="02">02</OPTION>
													<OPTION value="03">03</OPTION>
													<OPTION value="04">04</OPTION>
													<OPTION value="05">05</OPTION>
													<OPTION value="06">06</OPTION>
													<OPTION value="07">07</OPTION>
													<OPTION value="08">08</OPTION>
													<OPTION value="09">09</OPTION>
													<OPTION value="10">10</OPTION>
													<OPTION value="11">11</OPTION>
													<OPTION value="12">12</OPTION>
											 
											</select>
										
											<select name="expYear" id="expYear" style="color: #808080; padding: 5px;" onFocus="" onBlur="">
												<option value="">- Year -</option>
											  	<option value="2016">2016</option>
												<option value="2017">2017</option>
												<option value="2018">2018</option>
												<option value="2019">2019</option>
												<option value="2020">2020</option>
												<option value="2021">2021</option>
												<option value="2022">2022</option>
												<option value="2023">2023</option>
												<option value="2024">2024</option>
												<option value="2025">2025</option>
											</select>
										</div>
									</div>
								</div>
<!--							  	<div class="row" style="margin-bottom: 7px;">-->
<!--									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-right"><input type="radio" name="ccType" id="ccType" value="L" onclick=""></div>-->
<!--									-->
<!--										<img id="PayPalExpress"-->
<!--											src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif"-->
<!--											onClick=""-->
<!--											/>-->
<!--											-->
<!--											-->
<!--											<input type=HIDDEN id="message"  >-->
<!--								            <input type=HIDDEN id="payerid"  >-->
<!--								            <input type=HIDDEN id="billingagreementid"  >-->
<!--								            -->
<!--								            -->
<!--								<script>-->
<!--								var popup;-->
<!--								function startExpress(){-->
<!--								document.getElementById("PayPalExpress").disabled = true;-->
<!--								 popup = window.open('/wes/servlet/Show?WESPAGE=ePayG/common/payment/paypal/makeEC.jsp','popup','width=800,toolbar=1,resizable=1,scrollbars=yes,height=700,top=100,left=100');-->
<!--								 //popup.focus();-->
<!--								}-->
<!--								-->
<!--								</script>   -->
<!--								</div>-->
								
							 </div>
							 
							 <div class="col-lg-5 col-md-5 col-sm-6 col-xs-18 bottom-align-image">
								<div style="padding-bottom:25px;">
									<%@ include file="/ePayG/common/secure_certificate.jsp" %>
								</div>
									
								<span class="visible-lg visible-md visible-sm">
									<div id="checkExample" style="display: none;">
										<img class="img-responsive" src="/ePayG/images/check-example.png">
									</div>
								</span>
							</div>
							 
						</div> 
						
						<div class="row row-skip-2">
							<input class="submitButton" name="submitpayment" id="submitBtn" type="submit" value="Submit Payment" onClick="return submitPayment()" />	
						</div>	
					
						<div class="row row-skip-2">
						For inquiries please contact Customer Service at 1-800-533-6644 or 813-910-3616. <br>
						<a href="<%= request.getParameter("CANCELURL") %>">Return back to store</a>
						</div>
					
					</div>
				</div>
												
			</div>
		</div>
    </div>

	
	
	
	<input type="hidden" name="MSRSMAG" value="<%= request.getParameter("MSRSMAG") %>">
	<input type="hidden" name="clientId" value="<%= request.getParameter("clientId") %>">
	<input type="hidden" name="addressCode" value="">
	<input type="hidden" name="addressMessage" value="">
	
	<input type="hidden" name="RETURN_URL" value="<%= request.getParameter("RETURN_URL") %>">
	<input type="hidden" name="CANCELURL" value="<%= request.getParameter("CANCELURL") %>">
		
	<input type="hidden" name="ADDRCNFRMD" value="Y">
	
	<input type="hidden" name="SOURCE" value="<%= request.getParameter("SOURCE") %>">
	
	<input type="hidden" name="customer_email" value="<%= request.getParameter("customer_email") %>">
	<input type="hidden" name="customer_phone" value="<%= request.getParameter("customer_phone") %>">
	
	<input type="hidden" name="cardZip" value="<%= request.getParameter("cardZip") %>">
	
	<input type="hidden" name="Total_Amount" value="<%= request.getParameter("Total_Amount") %>">
	<input type="hidden" name="transAmount" value="<%= request.getParameter("Total_Amount") %>">
	
		
	
	</form>	

	<%@ include file="/ePayG/common/footer.jsp"%>
	
	
	
  </body>
</html>