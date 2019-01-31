    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
	<link rel="stylesheet" href="/ePayG/bootstrap-3.3.4/css/bootstrap.min.css">	
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<%
  String source = request.getParameter("SOURCE");
%>

	<% if(source.equalsIgnoreCase("DS")) { %>
	<link rel="stylesheet" href="/ePayG/css/theme_DS.css">
	<% } else if(source.equalsIgnoreCase("IHSTORE")) { %>
	<link rel="stylesheet" href="/ePayG/css/theme_IHSTORE.css">
	<% } else if(source.equalsIgnoreCase("IBSTORE")) { %>
	<link rel="stylesheet" href="/ePayG/css/theme_IBSTORE.css">
	<% } else if(source.equalsIgnoreCase("ISSTORE")) { %>
	<link rel="stylesheet" href="/ePayG/css/theme_ISSTORE.css">
	<% } %>	
  