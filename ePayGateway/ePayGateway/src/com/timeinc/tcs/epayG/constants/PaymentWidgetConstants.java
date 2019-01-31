package com.timeinc.tcs.epayG.constants;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import com.timeinc.tcs.util.Log;

public class PaymentWidgetConstants {
	
	static {
		try {
            Context ctx=new InitialContext();

            //Below properties are configured on server's name space bindings.
            // Get the properties and bin
            
            DB_SCHEMA = (String)ctx.lookup("wesOwner");

            //SERVICE_LOCATION = (String)ctx.lookup("ePayServiceLocation");
            SERVICE_LOCATION = "https://httppc9.tcs.timeinc.com/EPay2App/services/EPay2Item";
            FILE_UPLOAD_PATH = (String)ctx.lookup("AffiliateOrderFilePath");
            
        	// This is to differentiate between the test server or production server.
           /** if(PaymentWidgetConstants.DB_SCHEMA.equalsIgnoreCase("dtqwes")){
            	PaymentWidgetConstants.APP_EMAIL_ID="AffiliateOrdersTest@custserv.com";
            }else{
            	PaymentWidgetConstants.APP_EMAIL_ID="AffiliateOrders@custserv.com";
            } **/
            
          
      } catch (NamingException e) {
           e.printStackTrace();
      }

	}
	
	public static final String PROJECT_NAME ="Payment Widget";	
	public static String DB_SCHEMA;
	public static String SERVICE_LOCATION;
	
	// /tcs/wes/V7/qc1/lib/web/AffiliateOrderFiles/ and so on..
	public static String FILE_UPLOAD_PATH;
	
	public static final String OPERATOR_ID="WSA";
	public static final String FIELDS_ALL ="SLIOPID="+OPERATOR_ID+",MWTOPID="+OPERATOR_ID+",WESSUBMIT=Submit,WESCOMMIN=MAGMWSI,WESCOMMOUT=MAGMWSO,WESACTIVESESSION=TRUE,MSCACK=N";
	public static final String FIELDS_ORD ="WESACT=ORD, MSCFCOPY=1, MSCEFKOP=1, MSCCANRC=S, WESCOMMIN=MAGMWSI, WESCOMMOUT=MAGMWSO, MSRSEX=N";	
	
	// Below constants are exclusively for the epay Invocation service.
	public static final String TRANSACTION_VERIFY="VERIFY";
	public static final String TRANSACTION_AUTH="AUTH";
	public static final String TRANSACTION_AUTHDEP = "AUTHDEP";
	public static final String TRANSACTION_AUTHRVRSL="AUTHRVRSL";
	public static final String TRANSACTION_TOKENIZE="TOKENIZE";
	
	public static final String TRANSACTION_ID="TRANSACTION_ID";
	public static final String EPAY_PRESENTER_ID="PYMTWDGT";
	//public static final String EPAY_CLIENT_ID="DIRCOMM";
	public static final String EPAY_COUNTRYCODE="US";
	public static final String EPAY_CURRENCY_CODE="USD";
	public static final String EPAY_DC_KALMBACH_CODE="DCKPC";
	public static final String EPAY_DC_CSPI_CODE="DCCSPI";
	
	// Send email properties.
	public static String APP_EMAIL_ID;
	// public static String [] APP_EMAIL_RECIPIENTS ={"naresh_yadav@tas-analytic.com"};
	public static String [] APP_EMAIL_RECIPIENTS ={"TI-TCS-SpecialServices@custserv.com","naresh_yadav@tas-analytic.com","damodara_reddy@tas-analytic.com","vengate.sidheswaran@custserv.com"};
	public static final String APP_EMAIL_SUBJECT="Affiliate Orders Processing";
	public static final String APP_EMAIL_SUBJECT_PAYMENT_FAILURE="Payment Failure Notice";
	
	
	public static final String AUTH_STATUS = "authStatus";
	
	public static final String ERROR_MSG ="errorMsg";
	
	public static final String TEMPLATE_FILE_NAME = "orders_template.xls";
	
	// Roles of users of the application.
	public static final String ROLE_ADMIM = "Admin";
	public static final String ROLE_USER = "User";
	
	/* This is the list of Entities */
	public static final String ENTITY_USER_INFO = "UserInfo";
	public static final String ENTITY_EPAY_REQUEST_OBJECT = "epayRequestObject";
	
	public static final String SUCCESS_USER_ADDED = "User is added successfully!!!";
	public static final String FAILURE_USER_ADDED = "User could not be added to application!!!";
	
	public static final String SUCCESS_USER_UPDATE = "User details updated successfully!!!";
	public static final String FAILURE_USER_UPDATE = "User details could not be Updated. Please try again.";
	
	public static final String SUCCESS_USER_DELETE = "User successfully disabled for Application!!!";
	public static final String FAILURE_USER_DELETE = "User could not be removed from the Application. Please try again.";
	
	public static final String SUCCESS_PASSWORD_CHANGE = "Password successfully changed !!!";
	public static final String FAILURE_PASSWORD_CHANGE = "Password could not be changed. Please try again.";
	
	public static final String TRANSACTION_ADD ="ADD";
	public static final String TRANSACTION_UPDATE ="UPDATE";
	public static final String TRANSACTION_DELETE ="DELETE";
	public static final String TRANSACTION_CHANGE_PASSWORD ="UPDPWD";
	public static final String TRANSACTION_RESET_PASSWORD = "RSTPWD";
	
	public static final String TRANSACTION_ORDER = "Order";

	// Status for order processing.
	public static final String ORDER_PROCESS = "PRS";
	public static final String ORDER_SUCCESS = "SUC";
	public static final String ORDER_QUEUE = "QUE";
	public static final String ORDER_REJECTED = "REJ";
	
	// Response messages for Generate voucher
	public static final String SUCCESS_CREDITCARD_VERIFICATION ="Credit card verified successfully.";
	public static final String FAILURE_CREDITCARD_VERIFICATION ="Credit card verification failed. Please try again with valid credit card details.";
	public static final String FAILURE_PAYMENT_PROCESSING = "Error!!! Payment was denied by the card provider";
	public static final String FAILURE_VOUCHERGENERATION ="Generation of vouchers failed. Please try again.";
	public static final String SUCCESS_EXCEL_CREATION ="Excel file created successfully.";
	public static final String FAILURE_EXCEL_CREATION ="Failed to create the Excel file. Please try again.";
	public static final String SUCCESS_SENDING_EMAIL ="Email with Voucher Codes successfully sent to the registered Email Id.";
	public static final String FAILURE_SENDING_EMAIL ="Creation of batch Failed. Please try again for a fresh batch.";
	public static final String SUCCESS_USER_RESET_PASSWORD = "Password Successfully reset for user.";
	public static final String FAILURE_USER_RESET_PASSWORD = "Reseting password failed due to unknown reason. Please try again.";
	
	// Messages for batch deletion.
	public static final String SUCCESS_BATCHDELETION = "deleted successfully.";
	public static final String FAILURE_BATCHDELETION = "could not be deleted because batch is either active or you are not authorized to delete this batch.";
	
}
