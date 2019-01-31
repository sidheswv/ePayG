package com.timeinc.tcs.epayG.rest;

/**
 * 
 * @author poduril
 * 
 * Application class for RESTful Service-PayPal ExpressCheckout  Functionality
 * 
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.ws.rs.core.Application;

import com.timeinc.tcs.epayG.model.DBAccess;

/**
  
import com.timeinc.tcs.epayG.beans.ClientBean;
import com.timeinc.tcs.epayG.beans.CpsResponseBean;
import com.timeinc.tcs.epayG.beans.DivisionBean; 
import javax.sql.DataSource;
import javax.ws.rs.ApplicationPath;
import java.util.Vector;
import java.util.Collections;
import java.util.Enumeration;

*
**/


public class PaypalExpressCheckoutApplication extends Application {

	long currentTime;

	//static Vector divisionTable = new Vector(); // Vector to hold rows of TBWG's
												// DTPPSV.TBV_CPS_DIVISION DB2
												// table
	//static Vector clientTable = new Vector(); // Vector to hold rows of TBWG's
												// DTPPSV.TBV_CLIENT DB2 table
	//static Vector cpsResponseTable = new Vector(); // Vector to hold CPS
													// specific rows of TBWG's
													// DTPPSV.TBV_RESPONSE DB2
													// table
	
	public static HashMap<String, String> authTable = new HashMap<String, String>();// HahsMap holds ClientCode and Paypal_Email_Addr from TBV_CPS_DIVISION table

	static boolean on390 = false;

	static String serverName = "";
	static String LPAR = "";

	static {
		String dbOwner = null;

		try {
			InitialContext ctx = new InitialContext();
			dbOwner = (String) ctx.lookup("epayGowner"); // Lookup value of namespace
													// binding variable.
			//lakshmi
			//DataSource datasource = (DataSource)ctx.lookup("epay3");
			//System.out.println(ctx.lookup("jdbc/epay3"));
			//System.out.println("datasource :-" +  ctx.lookup("jdbc/epay3") );
			
			if (dbOwner == null) {
				// Log.setError("Epay3 The NameSpaceBinding variable 'epayOwner' is missing.");
				System.out.println(" epayowner is missing");
			} else {
				System.out.println("EPayG epayOwner: " + dbOwner);

				//clientTable = DBAccess.getClientTable(dbOwner,ctx); // Load client
																// table entries
																// into a vector
																// so we can
																// search on
				
				authTable = DBAccess.getAuthTable(dbOwner,ctx);
				//Collections.sort(clientTable); // it later to validate the
												// presenter id sent by the
												// client and
				//Enumeration e = clientTable.elements(); // also get the 2
														// character client cd
														// to lookup the client
														// division.
				@SuppressWarnings("rawtypes")
				Set set = authTable.entrySet();
				
				@SuppressWarnings("rawtypes")
				Iterator i = set.iterator();
				
				System.out.println("Elements of the HashMap authTable are : ");
				while (i.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry me = (Map.Entry)i.next();
					System.out.print(me.getKey() + ": ");
			        System.out.println(me.getValue());
				}


			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}

	}

	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();

		// Resources
		classes.add(com.timeinc.tcs.epayG.services.ExpressCheckoutService.class);
		
		//classes.add(com.timeinc.tcs.epayG.services.PayPalECOrderService.class);

		// Providers

		classes.add(com.timeinc.tcs.epayG.messages.ExpressCheckoutJson.class);

		return classes;
	}

}
