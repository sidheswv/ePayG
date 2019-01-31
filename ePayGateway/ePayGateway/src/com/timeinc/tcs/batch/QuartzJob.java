package com.timeinc.tcs.batch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.naming.InitialContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {
	
	
	String dsBatchController ;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("++++++++++++++++++++++++++ Quartz Job Executed TESTING ++++++++++++++++++++++++++");
		try{
			
			InitialContext iCtx = new InitialContext();
			dsBatchController = (String) iCtx.lookup("DSBatchController");

			if (dsBatchController == null || dsBatchController == "") {
				System.out.println("Batch Controller lookup failed");
			}
			else{
				System.out.println("The COntroller URL is :" + dsBatchController);
				
				URL ecDirectCallURLFinal = new URL(dsBatchController);
				HttpURLConnection ecdirectConnection = (HttpURLConnection) ecDirectCallURLFinal.openConnection();
				ecdirectConnection.setRequestMethod("POST");
				ecdirectConnection.setConnectTimeout(1000*60*5);
				ecdirectConnection.setReadTimeout(1000*60*5);	        
				ecdirectConnection.setDoInput(true);
				ecdirectConnection.setDoOutput(true);
				ecdirectConnection.setUseCaches(false);   

				ecdirectConnection.setRequestProperty("ISFROMBATCH","TRUE");
				System.out.println("PayPalDS.callPayPalBatch: Response Code: " + ecdirectConnection.getResponseCode());				
				
			}
    
        
        }  catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

   


}
