<% String scThisPage = "find_eRenewal.jsp"; %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="wes" %>
<%@ include file="/csp-kmb/controller/session_controller.jsp"%>
<%
	omnitureType = "transaction";
	String returnCode = vs.getString("WESRETURNCODE");
	String trCode = vs.getString("TR");

	boolean allowCheck = false; 
	// allowACH set in session_controller.jsp
	if(allowACH && !vs.getString("M1IRCSVC").equals("Y") && !vs.getString("M1IRCSVC").equals("1")) {
		allowCheck = true;
	} 
	
	boolean allowCard = true; // to make payment section reuseable
	boolean allowBillMe = false; // to make payment section reuseable
	
	String tccIssues = "";
	if (!vs.getString("M1ITCCTM").equals("")) { 
		tccIssues = vs.getString("M1ITCCTM");		
	} else {
		tccIssues = vs.getString("M1CFTERM");
	}

	String tccNewstand = ViewUtil.formatDollar(vs.getString("M1ITCCNP"));
	String tccYourPrice = ""; 
	String tccPaid = "";
	String tccBalance = "";
	
	if (!isInternationalTitle) {
		tccYourPrice = ViewUtil.formatDollar(vs.getString("M1ITCCYP"));
		tccPaid = ViewUtil.formatDollar(vs.getString("M1ITCCPD"));
		if(tccPaid.equals("")) { tccPaid = "$0.00"; }
		tccBalance = ViewUtil.formatDollar(vs.getString("M1ITCCAM"));
	} else {
		tccBalance = ViewUtil.formatCurrency( vs.getString("M1ITCCAM"),currSymbol,currDecim,currCode );		
	}

	
	
	boolean isContSvc = false;
	
	if(vs.getString("M1IRCSVC").equals("Y") || vs.getString("M1IRCSVC").equals("1")) {
		// subscription marked as C/S
		isContSvc = true;	
	} else if(vs.getString("M1IWPIQY").equals("C") || vs.getString("M1IWPIQY").equals("B")) {
		// TODO: Payment Incentives
		// payment incentive C/S
		// isContSvc = true;
	}
	
	boolean hasCardOnFile = false;
	
	//if(!vs.getString("M1ICRDNR").equals("") && !vs.getString("M1ICRDTP").equals("T") && !vs.getString("M1ICRDTP").equals("L")) {
	// TODO: Why doesn't M1ICRDNR check work in CSP but does in old WES
	if(!vs.getString("CCSUBSTR").equals("") && !vs.getString("M1CCRDTP").equals("T") && !vs.getString("M1CCRDTP").equals("L")) {
		hasCardOnFile = true;
	}
	
	boolean isBankAcct = false;
	if(vs.getString("M1CCRDTP").equals("G") || vs.getString("M1CCRDTP").equals("I") || vs.getString("M1CCRDTP").equals("K")) {
		isBankAcct = true;
	}

	// column values - for easy changing
	String addrLabelCol = "col-lg-3 col-md-4 col-sm-5 col-xs-18 bold";
	String addrLabelStyle = "padding-bottom: 0px;";
	String addrFieldCol = "col-lg-5 col-md-6 col-sm-8 col-xs-18";
	String addrFieldStyle = "padding-bottom: 6px;";
									
	String refundLabelCol = addrLabelCol;
	String refundLabelStyle = addrLabelStyle;
	String refundFieldCol = addrFieldCol;
	String refundFieldStyle = addrFieldStyle;
	
	String svcThroughLabelCol = addrLabelCol;
	String svcThroughLabelStyle = addrLabelStyle;
	String svcThroughFieldCol = addrFieldCol;
	String svcThroughFieldStyle = addrFieldStyle;	
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title><%= magPageTitle %>Customer Service - Payment</title>
	<%@ include file="/csp-kmb/common/header.jsp"%>
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
  </head>
  <body onLoad="errorMsg()">

	<wes:banner magBannerTitle="<%= magBannerTitle %>" isLoggedIn="<%= isLoggedIn %>" allowLinkBack="<%= allowLinkBack %>" entryCV="<%= entryCV %>" />
	
    <div class="container-fluid">
    	<div class="row">
	        <div class="col-lg-18 col-md-18 col-sm-18 layout-col-1">
				<div class="panel panel-single tcs-center-panel">
					<div class="panel-heading">
						<div class="row panel-page-header">
											

							<div class="col-lg-18 col-md-18 col-sm-18 col-xs-18 panel-title2">
								Verify your Address
							</div>	
						</div>					
					</div>
								
					<div class="panel-body">
						
						<div class="row row-field">
							<div class="<%= addrLabelCol %>" style="<%= addrLabelStyle %>">Name:</div>
							<div class="<%= addrFieldCol %>" style="<%= addrFieldStyle %>"><input name="MSRSUBNM" id="MSRSUBNM" type="text" class="input" value="<%=vs.getString("MSRSUBNM")%>" maxlength="30"/></div>
						</div>
						<div class="row row-field">
							<div class="<%= addrLabelCol %>" style="<%= addrLabelStyle %>">Address 1:</div>
							<div class="<%= addrFieldCol %>" style="<%= addrFieldStyle %>"> <input name="MSRADDR1" id="MSRADDR1" type="text" class="input" value="<%=vs.getString("MSRADDR1")%>" maxlength="30"/></div>
						</div>
						<div class="row row-field">
							<div class="<%= addrLabelCol %>" style="<%= addrLabelStyle %>">Address 2:</div>
							<div class="<%= addrFieldCol %>" style="<%= addrFieldStyle %>"><input name="MSRADDR2" id="MSRADDR2" type="text" class="input" value="<%=vs.getString("MSRADDR2")%>" maxlength="30" /></div>
						</div>
						<div class="row row-field">
							<div class="<%= addrLabelCol %>" style="<%= addrLabelStyle %>">City:</div>
							<div class="<%= addrFieldCol %>" style="<%= addrFieldStyle %>"><input name="WESCITY" id="WESCITY" type="text" class="input" value="<%=vs.getString("WESCITY")%>" maxlength="20"/></div>
						</div>
						<div class="row row-field">
							<div class="<%= addrLabelCol %>" style="<%= addrLabelStyle %>">State:</div>
							<div class="<%= addrFieldCol %>" style="<%= addrFieldStyle %>">
								<select id="addrState" style="color: #808080;" >
											<option value="">State/Province</option>
										  <% for(int s=0; s<stateList.length; s++) { %>
											<option value="<%= stateList[s][0] %>"><%= stateList[s][1] %></option>
										  <% } %>
										</select>
							</div>
						</div>
						<div class="row row-field">
							<div class="<%= addrLabelCol %>" style="<%= addrLabelStyle %>">Zip Code:</div>
							<div class="<%= addrFieldCol %>" style="<%= addrFieldStyle %>"><input name="WESZIP" id="WESZIP" type="text" class="input" value="<%=vs.getString("WESZIP")%>" maxlength="5"/></div>
						</div>
						
						<div class="row row-skip-2">
							<input id="verifyBtn" type="button" value="Verify Address"  />	
						</div>	
						
						<div class="row row-skip-1">
							<h3>Pay your bill quickly and securely!</h3>
							<h6 class="gray-dark-2">This site is secure for all debit/credit card transactions</h6>
						</div>
						<div class="row row-skip-1">
							<div class="col-lg-12 col-md-16 col-sm-18 col-xs-18" style="padding-left: 0px;margin-left: 0px;">
								Thank you for your subscription request. To pay in full now, please enter your credit/debit card information below, or choose PayPal.
								
							</div>
						</div> 
						<div class="row row-skip-2">
							<h4>Select your payment method:</h4>
						</div>
						<div class="row">
							<div class="col-lg-5 col-md-5 col-sm-6 col-xs-18 col-lg-push-6 col-md-push-9 col-sm-push-10">
								<div style="padding-bottom:25px;">
									<%@ include file="/csp-kmb/common/secure_certificate.jsp" %>
								</div>
									
								<span class="visible-lg visible-md visible-sm">
									<div id="checkExample" style="display: none;">
										<img class="img-responsive" src="<%= appImagesDir %>/check-example.png">
									</div>
								</span>
							</div>
							<div class="col-lg-6 col-md-9 col-sm-10 col-xs-18 col-lg-pull-5 col-md-pull-5 col-sm-pull-6">
								<div class="row" style="margin-bottom: 7px;">
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-right"><input type="radio" name="paymentType" value="card" <% if(!hasCardOnFile) { %>checked<% } %> onClick="togglePaymentType('card')"></div>
									<div class="col-lg-16 col-md-16 col-sm-16 col-xs-15 no-left-space"><strong>Credit/Debit Card</strong></div>
								</div>
								<div id="payCard" class="row" style="display: none;">
									<div class="col-lg-9 col-md-9 col-sm-12 col-xs-15 col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2">
										<div class="row" style="padding-bottom: 7px">
											<% if (showVisa){ %><img src="<%= appImagesDir %>/icons/payment/Visa.png"><% } %> 
											<% if (showMastercard){ %><img src="<%= appImagesDir %>/icons/payment/Mastercard.png"><% } %> 
											<% if (showAmex){ %><img src="<%= appImagesDir %>/icons/payment/American-Express.png"><% } %> 
											<% if (showDiscover){ %><img src="<%= appImagesDir %>/icons/payment/Discover-Network.png"><% } %>
											<% if (showDinersClub){ %><img src="<%= appImagesDir %>/icons/payment/diners-club.jpg"><% } %>
										</div>
										<div class="row">
											<select id="cardType" style="color: #808080;" onFocus="clearDefault(this, '')" onBlur="restoreDefault(this, '')">
												<% if (showVisa) { %><option value="V">Visa</option><% } %>
												<% if (showMastercard) { %><option value="M">Mastercard</option><% } %>
												<% if (showAmex) { %><option value="A">American Express</option><% } %>
												<% if (showDiscover) { %><option value="S">Discover</option><% } %>
												<% if (showDinersClub) { %><option value="D">Diners Club</option><% } %>												
											</select>
										</div>
										<div class="row" style="padding: 7px 0px;">
											<input id="cardNumber" type="text" value="Card Number" maxlength="20" style="color: #808080;" onFocus="clearDefault(this, 'Card Number')" onBlur="restoreDefault(this, 'Card Number')">
										</div>
										<div class="row" style="padding-bottom: 30px">
											<select id="expireMM" style="color: #808080;" onFocus="clearDefault(this, '')" onBlur="restoreDefault(this, '')">
												<option value="">- Month -</option>
											  <% for(int mm=1; mm<=12; mm++) { %>
												<option value="<%= addLeadingZero(mm) %>"><%= addLeadingZero(mm) %></option>
											  <% } %>
											</select>
										
											<select id="expireYYYY" style="color: #808080;" onFocus="clearDefault(this, '')" onBlur="restoreDefault(this, '')">
												<option value="">- Year -</option>
											  <% for(int y=0; y<=10; y++) { %>
												<option value="<%= currentYear+y %>"><%= currentYear+y %></option>
											  <% } %>
											</select>
										</div>
									</div>
								</div>
							  	<div class="row" style="margin-bottom: 7px;">
									<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-right"><input type="radio" name="paymentType" value="PayPal" onClick="togglePaymentType('PayPal')"></div>
									<div class="col-lg-16 col-md-16 col-sm-16 col-xs-15 no-left-space"><img src="<%= appImagesDir %>/paypal.png"> <span class="badge font-11" onMouseOver="this.style.cursor='pointer'" onClick="window.open('https://www.paypal.com/webapps/mpp/paypal-popup')">?</span></div>
								</div>
								<%@ include file="/csp-kmb/common/payment/paypal/ECButton.jsp"%>
							 </div>
						</div> 
						
						
						   	<div id="contSvc" class="row row-skip-2">
						</div>
						
						<div class="row row-skip-2">
							Please <a href="javascript:window.print();">print</a> a copy of this form for your records.
						</div>	
						
						<div class="row row-skip-2">
							<input id="submitBtn" type="button" value="Submit Payment" onClick="submitPayment()" />	
						</div>	
					</div>
				</div>
				
				<div class="panel panel-single tcs-footer-legal">
					<div data-bind="text: legal.frequency"></div>
					<br />
					<div data-bind="text: legal.subject_to_approval"></div>
				</div>
								
			</div>
		</div>
    </div>
	<wes:footer appImagesDir="<%= appImagesDir %>" magTitle="<%= magTitle %>" />
<form name="tccForm" method="post" action="<%=vs.getString("WESCONTEXTROOT")%>/servlet/TransferCC">
	<input type="hidden" name="MSRSMAG" value="<%= vs.getString("M1RMAGCD") %>">
	<input type="hidden" name="WESKEYLINE" value="<%= vs.getString("M1RTACCT")%>">	
	<input type="hidden" name="MSRSEGID" value="<%= vs.getString("M1ITCCSI")%>">
	<input type="hidden" name="MSRSPDAT" value="<%= vs.getString("M1ITCCSD")%>">
	<input type="hidden" name="MSRSUNIQ" value="<%= vs.getString("M1ITCCSU")%>">	

	<input type="hidden" name="MSCCANRC" value="S">
	<input type="hidden" name="MSDDAMT" value="<%= vs.getString("M1ITCCAM")%>">
	<input type="hidden" name="WESBILLTYPE" value="">
	<input type="hidden" name="WESINTBILLTYPE" value="">	
	<input type="hidden" name="MSCCRDNR" value="">
	<input type="hidden" name="MSCCBNKI" value="">
	<input type="hidden" name="WESEXPMM" value="">
	<input type="hidden" name="WESEXPYY" value="">
	<input type="hidden" name="WESEXPMM2" value="">
	<input type="hidden" name="WESEXPYY2" value="">

  	<input type="hidden" name="MSCOPTCS" value="">

  <% if(!vs.getString("MSCBLRSP").equals("")) { %>
   	<input type=HIDDEN name="MSCBLRSP" value="<%= vs.getString("MSCBLRSP")%>">	
  <% } else if(!vs.getString("M1IWPIQY").equals("")) { %>
	<input type=HIDDEN name="MSCBLRSP" value="W01">
  <% } else { %>
	<input type=HIDDEN name="MSCBLRSP" value="WES">
  <% } %>

	<input type="hidden" name="MSCCDSFL" value="<%= vs.getString("M1IWPITP")%>">
	<input type="hidden" name="MSCWPIPO" value="<%= vs.getString("M1IWPIPO")%>">
	
	<input type="hidden" name="RABKEY" value="<%= vs.getString("M1IWPIRE")%>">
	<input type="hidden" name="RABTERM" value="<%= vs.getString("M1IWPIRT")%>">
	<input type="hidden" name="RABVALUE" value="<%= vs.getString("M1IWPIRV")%>">
	<input type="hidden" name="M1IWPIAM" value="<%= vs.getString("M1IWPIAM")%>">
	<input type="hidden" name="M1ITCCAM" value="<%= vs.getString("M1ITCCAM")%>">

	<input type="hidden" name="PAYPAL_RETURN" value="<%=vs.getString("WESCONTEXTROOT")%>/servlet/TransferCC">
	
	<% if (isInternationalTitle) { %>
		<input type="hidden" name="WESINTERNATIONAL" value="T">
		<input type="hidden" name="WESREQUIREDFIELDS" value="MSCCRDNR WESEXPMM WESEXPYY MSCCRDTP">
		<input type="hidden" name="MSDCURR" value="<%= vs.getString("M1ICURCD")%>">		
	<% } else { %>
		<input type="hidden" name="WESREQUIREDFIELDS" value="MSRSUBKX MSRSEGID MSRSPDAT MSRSUNIQ">
	<% } %>
	
	<input type="hidden" name="TR" value="TCC">		
	<input type="hidden" name="WESSTATEPAGE" value="<%= appContextRoot %>/state/payment.txt">
	<input type="hidden" name="WESERRORPAGE" value="<%= appContextRoot %>/error.jsp">
	<input type="hidden" name="WESJSP" value="T">
	<!-- this field may be obsolete <input type="hidden" name="WESTCCJSP" value="T"> -->
	<input type="hidden" name="TCCPAGE" value="T">
</form>
	<%@ include file="/csp-kmb/common/scripts.jsp"%>	

  <script>
  togglePaymentType(getRadioValue("paymentType"));
  
  function submitPayment() {
	var payType = getRadioValue("paymentType");
	var isContSvc = <%= isContSvc %>;
	var isInternationalTitle = <%= isInternationalTitle %>;
	var csOption = getRadioValue("contSvcOpt");
	
	if(payType=="onFile") {
		var currentExp="<%= vs.getString("M1ICRDEX") %>";
		var updateExpMonth="";
		var updateExpYear="";
		
		if(currentExp == "") {
			updateExpMonth=document.getElementById("updateExpireMM").value;
			updateExpYear=document.getElementById("updateExpireYYYY").value;
		}
		
		if(currentExp == "" && (updateExpMonth=="" || updateExpYear=="" || pastExpDate("updateExpireMM","updateExpireYYYY"))) {
			alert("Please select a valid expiration date.");
		} else if(doesRadioExist("contSvcOpt") && csOption == "") {
			alert("Please selection an option for the automatic renewal program.");
		} else {
			document.tccForm.WESBILLTYPE.value = "Y";
			document.tccForm.WESEXPMM2.value = updateExpMonth;
			document.tccForm.WESEXPYY2.value = updateExpYear;
			
			if(isContSvc) {
				document.tccForm.MSCOPTCS.value = "Y";	
			} else if(doesRadioExist("contSvcOpt")) { 
				document.tccForm.MSCOPTCS.value = csOption;
			}
			
			document.getElementById("submitBtn").disabled = true;
			document.tccForm.submit();
		}
	} else if(payType=="card") {
		var ccType=document.getElementById("cardType").value;
		var ccNumber=document.getElementById("cardNumber").value;
		var ccExpMonth=document.getElementById("expireMM").value;
		var ccExpYear=document.getElementById("expireYYYY").value;
	
		// clear defaults
		if(ccNumber == "Card Number") { ccNumber = ""; }
		
		if(ccType=="") {
			alert("Please select a card type.");
		} else if(ccNumber=="") {
			alert("Please enter a card number.");
		} else if(ccExpMonth=="" || ccExpYear=="" || pastExpDate("expireMM","expireYYYY")) {
			alert("Please select a valid expiration date.");
		} else if(doesRadioExist("contSvcOpt") && csOption == "") {
			alert("Please selection an option for the automatic renewal program.");
		} else {
			document.tccForm.WESBILLTYPE.value = ccType;
			if (isInternationalTitle) {
				document.tccForm.WESINTBILLTYPE.value = ccType;
			}
			document.tccForm.MSCCRDNR.value = ccNumber;
			document.tccForm.WESEXPMM.value = ccExpMonth;
			document.tccForm.WESEXPYY.value = ccExpYear;

			if(isContSvc) {
				document.tccForm.MSCOPTCS.value = "Y";	
			} else if(doesRadioExist("contSvcOpt")) { 
				document.tccForm.MSCOPTCS.value = csOption;
			}
			
			document.getElementById("submitBtn").disabled = true;
			document.tccForm.submit();
		}
	} else if(payType=="bank") {
		var acctType=document.getElementById("acctType").value;
		var acctNumber=document.getElementById("acctNumber").value;
		var confirmAcctNumber=document.getElementById("confirmAcctNumber").value;
		var routingNumber=document.getElementById("routingNumber").value;
		var confirmRoutingNumber=document.getElementById("confirmRoutingNumber").value;

		acctNumber = acctNumber.trim();
		confirmAcctNumber = confirmAcctNumber.trim();
		routingNumber = routingNumber.trim();
		confirmRoutingNumber = confirmRoutingNumber.trim();
		
		if(acctType=="") {
			alert("Please select an account type.");
		} else if(routingNumber=="") {
			alert("Please enter the bank's routing number.");		
		} else if(routingNumber!=confirmRoutingNumber) {
			alert("Routing numbers do not match.");
		} else if(acctNumber=="") {
			alert("Please enter an account number.");
		} else if(acctNumber.length < 9) {
			alert("Account numbers must be at least 9 digits in length.");
		} else if(acctNumber!=confirmAcctNumber) {
			alert("Account numbers do not match.");				
		} else {
			document.tccForm.WESBILLTYPE.value = document.getElementById("acctType").value;
			if (isInternationalTitle) {
				document.tccForm.WESINTBILLTYPE.value = document.getElementById("acctType").value;
			}
			document.tccForm.MSCCRDNR.value = acctNumber;
			document.tccForm.MSCCBNKI.value = routingNumber;
			
			document.getElementById("submitBtn").disabled = true;
			document.tccForm.submit();			
		}	
	} else if(payType=="PayPal") {
		document.tccForm.WESBILLTYPE.value = "L";
// 		alert("paypal");
		document.getElementById("submitBtn").disabled = true;
		if(doesRadioExist("contSvcOpt")) { 
			document.tccForm.MSCOPTCS.value = csOption;
		}
		document.tccForm.action="<%=vs.getString("WESCONTEXTROOT")%>/servlet/Show?WESPAGE=<%= appContextRoot %>/paypal_request.jsp&TR=TCC";		
		document.tccForm.submit();
	} else {
		alert("Please select a payment method.");
	}
}

  </script>

<% if(returnCode.equals("-1")) { %>
<script type="text/javascript">
	// re-populate form on error  	
  	var payType = "<%= vs.getString("WESBILLTYPE") %>";
  	
  	if(payType == "Y") {
  		setRadioValue("paymentType","onFile");
  		togglePaymentType("onFile");
  		if(document.getElementById("updateExpireMM")) { document.getElementById("updateExpireMM").value = "<%= vs.getString("WESEXPMM2") %>"; }
  		if(document.getElementById("updateExpireYYYY")) { document.getElementById("updateExpireYYYY").value = "<%= vs.getString("WESEXPYY2") %>"; }
	} else if(payType == "A" || payType == "M" || payType == "S" || payType == "V") {
  		setRadioValue("paymentType","card");
  		togglePaymentType("card");
  		var cardType = document.getElementById("cardType");
  		var cardNumber = document.getElementById("cardNumber");
  		var expMM = document.getElementById("expireMM");
  		var expYY = document.getElementById("expireYYYY");

		if(cardType) { 
			clearDefault(cardType, '');
			cardType.value = "<%= vs.getString("WESBILLTYPE") %>"; 
		}
		
		if(cardNumber) {
			clearDefault(cardNumber, 'Card Number'); 
			cardNumber.value = "<%= vs.getString("MSCCRDNR") %>"; 
		}
		
  		if(expMM) { 
			clearDefault(expMM, ''); 
  			expMM.value = "<%= vs.getString("WESEXPMM") %>"; 
  		}
  		
  		if(expYY) {  
			clearDefault(expYY, '');
  			expYY.value = "<%= vs.getString("WESEXPYY") %>"; 
  		}
  	} else if(payType == "G" || payType == "I" || payType == "K") {
  		setRadioValue("paymentType","bank");
  		togglePaymentType("bank");
   		var acctType = document.getElementById("acctType");
  		var routingNumber = document.getElementById("routingNumber");
  		var confirmRouting = document.getElementById("confirmRoutingNumber");
  		var acctNumber = document.getElementById("acctNumber");
  		var confirmAcct = document.getElementById("confirmAcctNumber");

		if(acctType) { 
			clearDefault(acctType, '');
			acctType.value = "<%= vs.getString("WESBILLTYPE") %>"; 
		}
		
		if(routingNumber) {
			clearDefault(routingNumber, 'Routing Number'); 
			clearDefault(confirmRouting, 'Re-Enter Routing');
			routingNumber.value = "<%= vs.getString("MSCCBNKI") %>";
			confirmRouting.value = "<%= vs.getString("MSCCBNKI") %>"; 
		}
		
		if(acctNumber) {
			clearDefault(acctNumber, 'Account Number'); 
			clearDefault(confirmAcct, 'Re-Enter Account');
			acctNumber.value = "<%= vs.getString("MSCCRDNR") %>";
			confirmAcct.value = "<%= vs.getString("MSCCRDNR") %>"; 
		}
  	} else if(payType == "L") {
  		setRadioValue("paymentType","PayPal");
   		togglePaymentType("PayPal"); 	  	
  	}
	</script>
<% } %>
	<%@ include file="/csp-kmb/common/error_msg.jsp" %>
  </body>
</html>