
		<input type="image" id="PayPalExpress"
			src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif"
			onClick="startExpress();"
			/>
			
			
			<input type=HIDDEN id="message"  >
            <input type=HIDDEN id="payerid"  >
            <input type=HIDDEN id="billingagreementid"  >
            
            
<script>
var popup;
function startExpress(){
document.getElementById("PayPalExpress").disabled = true;
 popup = window.open('/wes/servlet/Show?WESPAGE=ePayG/common/payment/paypal/makeEC.jsp','popup','width=800,toolbar=1,resizable=1,scrollbars=yes,height=700,top=100,left=100');
 //popup.focus();
}

</script>            
 

