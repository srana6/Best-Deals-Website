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
		/*File f=new File(sc.getRealPath("signincredentialstore"));
		if(f.exists()){
			fileSize=f.length();
			System.out.println("The size of file is: "+fileSize);
		}
		else{
			System.out.println("File Doesn't exist");
		}
		
		if(fileSize<=0){
		
		Map<String,List<String>> sup=new HashMap<String,List<String>>();*/
		
		pw=response.getWriter();
		String userid=request.getParameter("nname");
		String password=request.getParameter("npassword");
		String email=request.getParameter("email");
		
		MySqlDataStoreUtilities.insertUser(userid,password,email);
		
		/*List<String> val=new ArrayList<String>();
		val.add(userid);
		val.add(password);
		val.add(email);
		
		sup.put(userid,val);*/
		
		/*
		Testing what's going on inside hash map
		*/
		
		/*for(Map.Entry<String,List<String>> m : sup.entrySet()){
			String key=m.getKey().toString();
			List l=m.getValue();
			System.out.println("The Key is:"+ key+"The corresponding value is:"+ l);
		}
		
		
		
		try{
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File(sc.getRealPath("signincredentialstore"))));    
        oos.writeObject(sup);
        oos.flush();
        
    
    }catch(Exception e){
        System.out.println("Could NOT Write microsoft to GameSpeedDataStore ...");
    }*/
		
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
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login2/HomeServlet'>PLEASE LOGIN AGAIN</a>");  
					
					pw.close();
		
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException{
		signup(request,response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException{
		signup(request,response);
	}
} 