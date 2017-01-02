import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class SignUpServlet extends HttpServlet{
	
	public static PrintWriter pw;
	public ServletContext sc;
	public long fileSize;
	public Map<String,List<String>> mapInFile;
	public List<String> newUserCredentials;
	public List<String> l1;
	
	
	public void init() throws ServletException{
      	SaxParser.loadProducts();
	}
	
	public void signup(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException{
		sc=request.getSession().getServletContext();
		
		pw=response.getWriter();
		String userid=request.getParameter("nname");
		String password=request.getParameter("npassword");
		String email=request.getParameter("email");
		
		MySqlDataStoreUtilities.insertUser(userid,password,email);
		
		
		
		SignUpServlet.loginSuccessful(response);
		
	}
	
	
	public static void loginSuccessful(HttpServletResponse response) throws ServletException,java.io.IOException{
		
		pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='#'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "</div>" + "</header>"+
					"<h3>" + "<span style='width:310px;display:inline-block'></span>" + "YOU ARE SUCCESSFULLY REGISTERED" + "</h3>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login4/HomeServlet'>PLEASE LOGIN AGAIN</a>");  
					
					pw.close();
		
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException{
		signup(request,response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException{
		signup(request,response);
	}
} 