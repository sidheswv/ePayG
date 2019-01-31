<% String scThisPage = "makeEC.jsp"; %>
<%@ include file="/csp-kmb/controller/session_controller.jsp"%>

<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.BufferedInputStream"%>

<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.net.URL"%>


<head>
<title><%= magPageTitle %>Customer Service - Making Express Checkout</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>


<body bgcolor="#FFFFFF" text="#000000" onLoad="onForward();">

	<%!public String getURL(ValueSet vs) {

		String url = null;
		StringBuilder urlbldr = new StringBuilder();
		String setExpress = "https://wesqa.customersvc.com/ePayG/jaxrs/paypalexpresscheckout/SetExpressCheckout";

		// Create ePayGateway-->PayPal Express Checkout Request

		String cancelUrl = "https://wesqa.customersvc.com/wes/servlet/Show?WESPAGE=csp-kmb/cancelEC.jsp";
		//String ClientId = "TD";
		String ClientId = vs.getString("MSRSMAG");
		String paypalEmail = vs.getString("PAYPAL_EMAIL");
		String isSandBox = "true";

		HttpURLConnection conn,connrdrt = null;
		InputStream inputStream = null;
		BufferedReader br;
		boolean redirect = false;

		try {

			URL seturl = new URL(setExpress);
			conn = (HttpURLConnection) seturl
					.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("CancelURL", cancelUrl);
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
				connrdrt.setRequestMethod("GET");
				connrdrt.setRequestProperty("CancelURL", cancelUrl);
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
				urlbldr.append(output);
				System.out.println(output);

				url = urlbldr.toString();
				conn.disconnect();
				if (redirect) {
					connrdrt.disconnect();
				}

			}
		} catch (IOException e) {
			//Log.setError("IOException inside --> makeECCall(). The message " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				//Close Stream first and then connection as underlying redirects may use same connection
				//conn.disconnect();
			} catch (Exception ignore) {
			}
		}
		return url;
	}%>

	<%
		String url = getURL(vs);
		System.out.println("This is URL" + url);
	%>

	<form name="makeECForm" method=POST></form>
	<Script>

function onForward(){

var rdurl = "<%=url%>";
			window.location.href = rdurl;
		}
	</Script>

</body>