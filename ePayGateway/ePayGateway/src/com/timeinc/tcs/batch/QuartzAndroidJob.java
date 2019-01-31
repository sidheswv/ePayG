package com.timeinc.tcs.batch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.naming.InitialContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzAndroidJob implements Job {

	String androidBatchService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out
				.println("++++++++++++++++++++++++++ Quartz Android Job Execution Started ++++++++++++++++++++++++++");
		try {
			InitialContext iCtx = new InitialContext();
			androidBatchService = (String) iCtx.lookup("AndroidBatchService");

			if (androidBatchService == null || androidBatchService == "") {
				System.out.println("Android Batch Service lookup failed");
			} else {
				System.out.println("The Android Batch Service URL is :"
						+ androidBatchService);
				URL androidPayBatchUrl = new URL(androidBatchService);
				HttpURLConnection androidPayBatchConnection = (HttpURLConnection) androidPayBatchUrl
						.openConnection();
				androidPayBatchConnection.setRequestMethod("POST");
				androidPayBatchConnection.setConnectTimeout(1000 * 60 * 5);
				androidPayBatchConnection.setReadTimeout(1000 * 60 * 5);
				androidPayBatchConnection.setDoInput(true);
				androidPayBatchConnection.setDoOutput(true);
				androidPayBatchConnection.setUseCaches(false);

				androidPayBatchConnection.setRequestProperty("ISFROMBATCH", "TRUE");
				androidPayBatchConnection.setRequestProperty("ISANDROIDPAYRETRY", "FALSE");
				androidPayBatchConnection.setRequestProperty("ISTESTENV", "TRUE");
				androidPayBatchConnection.setRequestProperty("ISFROMLISTENER", "FALSE");
				System.out
						.println("QuartzJob.execute for AndroidPayBatch: Response Code: "
								+ androidPayBatchConnection.getResponseCode());
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
