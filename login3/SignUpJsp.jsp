<%@ page import= "java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>
<%@ page import="WebAssignment3.*"%>

	
	<%
	long fileSize;
	Map<String,List<String>> mapInFile;
	List<String> newUserCredentials;
	List<String> l1;%>
	
	
	
    <%SaxParser.loadProducts();%>

	<%String userid=request.getParameter("nname");
	String password=request.getParameter("npassword");
	String email=request.getParameter("email");
		
	MySqlDataStore.insertUser(userid,password,email);
	%>
		
		
	<html>
	<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<title>Product Added : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
	</head>
	<body>
	<div id='container'>
	<header>
	<div class='header_logo'><img src='images/best_deals.png'/> 
	<h1><a href='#'>BEST <span>DEAL</span></a></h1>
	<h2>new deals everyday</h2></div></header>
	<h3><span style='width:310px;display:inline-block'></span>YOU ARE SUCCESSFULLY REGISTERED</h3>
	<span style='width:310px;display:inline-block'></span><a href='HomeJsp.jsp'>PLEASE LOGIN AGAIN</a>  
					