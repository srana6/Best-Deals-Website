<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>


	
	<% List<String> delCartItems = new ArrayList<String>();

	 List<Products> tvList = new ArrayList<Products>();
	 List<Products> laptopList = new ArrayList<Products>();
	 List<Products> phoneList = new ArrayList<Products>();
	 List<Products> tabletList = new ArrayList<Products>();

	 HashMap<String, List<Products>> map;
	 HashMap<String, List<String>> hm;

	 Map<String, List<Products>> prod ;
	 String k;
	 Products p;
	 String username;

	 ServletContext sc;
	 String firstLetter;
     String productcategory;
     String productretailer;
     String productid;
     String productname; 
	 String productimage;
     int productprice;
     String prodpricestr;
	 String productformid;
	
	
      
	PrintWriter pw= response.getWriter();
	response.setContentType("text/html");
        
    HttpSession s=request.getSession(); 
    username=((String)s.getAttribute("userid"));
		
    prod=new HashMap<String, List<Products>>(); 
		
       
    productformid =request.getParameter("deleteFormId");
	System.out.println("The id of the product to be deleted is: "+productformid);
			
	MySqlDataStore.deleteProduct(productformid);%>
								
			
	
	
	<html>
	<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<title>Product Added : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
	</head>
	<body>
	<div id='container'>
	<header><div class='header_logo'><img src='images/best_deals.png'/>
	<h1><a href='#'>BEST <span>DEAL</span></a></h1>
	<h2>new deals everyday</h2></div></header>
	<h3><span style='width:310px;display:inline-block'></span>Your Cart Has Been Updated</h3>
	<span style='width:310px;display:inline-block'></span><a href='ProductCartJsp.jsp'>Due to Extra Security Reasons click here to see the changes</a> 
