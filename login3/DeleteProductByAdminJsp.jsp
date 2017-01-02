<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>

	
	<% 
	 String productcategory =" ";
	 String productretailer=" ";
	 String productid=" ";
	 String productname=" ";
	 String productprice=" ";
	 
	 Map<String, List<Products>> prod;
	 HashMap<String, List<Products>> map;

	 String k=" ";
	
	
		
	response.setContentType("text/html");
    PrintWriter pw= response.getWriter();
        
    HttpSession s=request.getSession(); 
    String user=((String)s.getAttribute("userid")); 

	
	productid = request.getParameter("productDeleteId");
	System.out.println("The product id to be deleted is: "+productid);
	
	MySqlDataStore.deleteProductByAdmin(productid);
		
	%>

	<html>
	<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<title>Product Added : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
	</head>
	<body>
	<div id='container'>
	<header><div class='header_logo'><img src='images/best_deals.png'/><h1><a href='LoginJsp.jsp'>BEST <span>DEAL</span></a></h1>
	<h2>new deals everyday</h2> 
	</div>
	</header>
	<h4><span style='width:310px;display:inline-block'></span>Your Product Has Been Added</h4>
	<span style='width:310px;display:inline-block'></span><a href='AdminHomePageJsp.jsp'>Due to extra security Please login again to see the changes</a>
	</body></html>
   