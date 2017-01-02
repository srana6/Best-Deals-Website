/*
 * HomeServlet.java
 *
 */
 
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ErrorLoadingProductPageServlet extends HttpServlet {
	
	public PrintWriter pw1;
	public String message="Login Failure! Username or password is incorrect";
	
	public void init() throws ServletException{
      	
	}
   
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		
		showError(request,response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		
		showError(request,response);
    }
	
	public void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
		response.setContentType("text/html");
		pw1=response.getWriter();
		pw1.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='#'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "</div>" + "</header>"+
					"<h3>" + "<span style='width:310px;display:inline-block'></span>" + message + "</h3>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='MainHomePageServlet'>Sorry</a>");  
					
					pw1.close();
		
	}
}
