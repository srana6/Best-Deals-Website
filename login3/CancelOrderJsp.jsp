<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>


	
	<%String username;
	
     
	HttpSession s=request.getSession(); 
    username=((String)s.getAttribute("userid"));
		
	PrintWriter pw = response.getWriter(); 
	String productformid =request.getParameter("productUpdateId");

	MySqlDataStore.cancelProduct(productformid); %>
		
	<html>
	<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />
	</head>
	<body>
	<div id='container'>
	<header><div class='header_logo'><img src='images/best_deals.png'/> 
	<h1><a href='#'>BEST <span>DEAL</span></a></h1>
	<h2>new deals everyday</h2></div></header>
	<%if(username.equals("sales")){%>
	<h3><span style='width:310px;display:inline-block'></span>Your Order has Been Canceled</h3>
	<span style='width:310px;display:inline-block'></span>"<a href='SalesHomePageJsp.jsp'>click here to see the changes</a>  
	<%}
	else{%>
	<h3><span style='width:310px;display:inline-block'></span>Your Order has Been Canceled</h3>
	<span style='width:310px;display:inline-block'></span>"<a href='OrderHistoryJsp.jsp'>click here to see the changes</a>  
	<%}%>
					