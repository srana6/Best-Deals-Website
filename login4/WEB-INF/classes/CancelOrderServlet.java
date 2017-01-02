import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CancelOrderServlet extends HttpServlet {
	
	public String username;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {      
		HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
		PrintWriter pw = response.getWriter(); 
		String productformid =request.getParameter("productUpdateId");

		MySqlDataStore.cancelProduct(productformid); 
		
		pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='#'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "</div>" + "</header>");
					if(username.equals("sales")){
		pw.println("<h3>" + "<span style='width:310px;display:inline-block'></span>" + "Your Order has Been Canceled" + "</h3>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login4/SalesHomePageServlet'>click here to see the changes</a>");  
					}
					else{
		pw.println("<h3>" + "<span style='width:310px;display:inline-block'></span>" + "Your Order has Been Canceled" + "</h3>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login4/OrderHistoryServlet'>click here to see the changes</a>");  
					}
					
		pw.close();

		
	}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}