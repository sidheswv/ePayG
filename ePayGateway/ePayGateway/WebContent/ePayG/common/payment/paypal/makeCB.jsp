<% String scThisPage = "makeCB.jsp"; %>
<%@ include file="/csp-kmb/controller/session_controller.jsp"%>


<%@page import="java.net.URLDecoder"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.net.URL"%>
<%@page import="com.timeinc.tcs.util.ExpressValues"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.fasterxml.jackson.databind.DeserializationFeature"%>


<head>
<title><%= magPageTitle %>Customer Service - Creating Express Checkout</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>


<body bgcolor="#FFFFFF" text="#000000">

	<%!public String getJSN(ValueSet vs, String token) {

		String tkn = token;
		StringBuilder jsonbldr = new StringBuilder();
		String json = null;
		System.out.println("This is inside second service call");

		HttpURLConnection conn,connrdrt = null;
		InputStream inputStream = null;
		//String ClientId = "TD";
		String ClientId = vs.getString("MSRSMAG");
		String paypalEmail = vs.getString("PAYPAL_EMAIL");
		String isSandBox = "true";
		boolean redirect = false;
		BufferedReader br;
		String createBilling = "https://wesqa.customersvc.com/ePayG/jaxrs/paypalexpresscheckout/CreateBillingAgreement?token="
				+ tkn;

		try {

			URL url = new URL(createBilling);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("ClientId", ClientId);
			conn.setRequestProperty("paypalEmail", paypalEmail);
			conn.setRequestProperty("isSandBox", isSandBox);
			//Setting the URl connection for redirection 
			conn.setInstanceFollowRedirects(true);

			int status = conn.getResponseCode();

			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
						|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER) {
					redirect = true;
					System.out.println("Response Code ... " + status);
				} else {
					throw new RuntimeException("Failed : HTTP error code : "
							+ status);
				}
			}
			System.out.println("Response Code ... " + status);

			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");

				// get the Headers if need, for login
				// open the new connnection again
				connrdrt = (HttpURLConnection) new URL(newUrl)
						.openConnection();
				conn.setRequestMethod("GET");

				connrdrt.setRequestProperty("Accept", "application/json");
				connrdrt.setRequestProperty("ClientId", ClientId);
				connrdrt.setRequestProperty("paypalEmail", paypalEmail);
				connrdrt.setRequestProperty("isSandBox", isSandBox);

				System.out.println("Redirect to URL : " + newUrl);

				br = new BufferedReader(new InputStreamReader(
						(connrdrt.getInputStream())));

			} else {
				br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
			}
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				jsonbldr.append(output);
				System.out.println(output);
			}
			System.out.println("This is Json builder :" + jsonbldr);
			json = jsonbldr.toString();
			conn.disconnect();
			if (redirect) {
				connrdrt.disconnect();
			}

		} catch (MalformedURLException e) {
			//	Log.setError("ClientProtocolException inside --> makeECCall(). The message " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			//Log.setError("IOException inside --> makeECCall(). The message " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				//Close Stream first and then connection as underlying redirects may use same connection

			} catch (Exception ignore) {
			}
		}
		System.out.println("JSON got from response is :" + json);
		return json;
	}%>

	<%
		String token = request.getParameter("token");
		String json = getJSN(vs, token);
		System.out.println("This is Json" + json);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		ExpressValues ev = mapper.readValue(json, ExpressValues.class);
		String payerid = ev.getpayerId();
		String billingid = ev.getBillingAgreementId();
		System.out.println("PayerId is" + payerid);
		System.out.println("BillingId is" + billingid);
		// Add logic to put the values into value Set
		vs.put("MSCPYRID", payerid);
		vs.put("MSCCRDNR", billingid);
	%>

	<form id="makeCB-form" method=POST>

		<script type="text/javascript">
			window.close();
		</script>

	</form>

</body>