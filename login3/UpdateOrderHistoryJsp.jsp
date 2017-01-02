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
		
	PrintWriter pw=response.getWriter();
	String orderId1 = request.getParameter("orderId1");
    String firstName1 = request.getParameter("firstName1");
    String lastName1 = request.getParameter("lastName1");
    String price1 = request.getParameter("price1");        
    String address1= request.getParameter("address1");
    String phone1 = request.getParameter("phone1");
    String creditCard1 = request.getParameter("creditCard1");

        
    MySqlDataStore.updateOrderHistoryClient(orderId1,firstName1,lastName1,price1,address1,phone1,creditCard1);%>

       <html>
       <head>
       <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
       <title>Cart Delete : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
       </head>
       <body>
       <div id='container'>
       <header><div class='header_logo'><img src='images/best_deals.png'/><h1><a href='#'>BEST <span>DEAL</span></a></h1>
       <h2>new deals everyday</h2></div></header>
		<%if(username.equals("sales")){%>
		<h4><span style='width:310px;display:inline-block'></span>Your Order Has Been Updated Successfully</h4>
       <span style='width:310px;display:inline-block'></span><a href='SalesHomePageJsp.jsp'>Click here to redirect</a>
		<%}
		else{%>
       <h4><span style='width:310px;display:inline-block'></span>Your Order Has Been Updated Successfully</h4>
       <span style='width:310px;display:inline-block'></span><a href='OrderHistoryJsp.jsp'>Click here to redirect</a>
		<%}%>
       