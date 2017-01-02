/*
 * LoginServlet.java
 *
 */
 

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
	
	
	
	public List<String> l=new ArrayList<String>();
	
	public String userid;
	public String password;
	public String id;
	public String pass;
	public String match;
	public boolean  flag=false;
	public ServletContext sc;
	public List<Products> productList = new ArrayList<Products>();
	public List<Products> smartList;

	public HashMap<String, List<Products>> ee ;
	public HashMap<String,String> clientcred;
	public HashMap<String,String> admincred;
	public HashMap<String,String> salescred;
 
	public String k;
	public String val;
	
	public File file;
	public long size;
   
    protected Map users = new HashMap();
	public static PrintWriter pw1;
    /** 
     * Initializes the servlet with some usernames/password
    */  
    /*public void init() {
                users.put("admin", "admin");
    }*/

	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		PrintWriter pw= response.getWriter(); 
		response.setContentType("text/html");
		
		
		clientcred=new HashMap<String,String>();
		admincred=new HashMap<String,String>();
		salescred=new HashMap<String,String>();
		
        userid = request.getParameter("username");
        password = request.getParameter("password");
		
		sc=request.getSession().getServletContext();
		
		clientcred=MySqlDataStore.selectUser();
		admincred=MySqlDataStore.selectAdmin();
		salescred=MySqlDataStore.selectSales();
		
        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(userid != null && password != null) {
			
				if(userid.equals("admin")){
					for(Map.Entry<String,String> m :admincred.entrySet()){
						id=m.getKey();
						val=m.getValue();
						System.out.println("Reached in admin");
						System.out.println("The key/username is: "+id+" "+"& the corresponding password is: "+val);
			
						if(userid.equals(id)&&password.equals(val)){
						
						response.sendRedirect("AdminHomePageServlet");
						HttpSession s=request.getSession();
						s.setAttribute("userid",userid);
						}
						
					}
					
				}
				else if(userid.equals("sales")){
					for(Map.Entry<String,String>hm : salescred.entrySet()){
						id=hm.getKey();
						val=hm.getValue();
						
						System.out.println("Reached in sales");
						System.out.println("The key/username is: "+id+" "+"& the corresponding password is: "+val);
			
						if(userid.equals(id)&&password.equals(val)){
						
						response.sendRedirect("SalesHomePageServlet");
						HttpSession s=request.getSession();
						s.setAttribute("userid",userid);
						}
					}
				}
				else{
        
        for(Map.Entry<String,String> m :clientcred.entrySet()){
			
			id=m.getKey();
			val=m.getValue();
			System.out.println("Reached in client");
			System.out.println("The key/username is: "+id+" "+"& the corresponding password is: "+val);
			
			if(userid.equals(id)&&password.equals(val)){
				//showPage(response, "Login Success!");
				HttpSession s=request.getSession();
				s.setAttribute("userid",userid);
				flag=true;
				response.sendRedirect("/login5/DealMatches");
			}
 
		}
		if(flag == false){
			LoginServlet.showError(response, "Login Failure! Username or password is incorrect");
		}
	}
		
		
}
}
    
    /**
     * ADMIN PAGE FUNCTIONALITY
     */
	 
	 protected void showAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
			sc=request.getSession().getServletContext();
			PrintWriter pw= response.getWriter(); 
			response.setContentType("text/html");
			//SaxParser.loadProducts();
			
			pw.println("<html>"+
						"<head>"+
						"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
						"<title>SignIn : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
						"</head>"+
						"<body>"+
						"<div id='container'>"+
						"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
						"<h1><a href='/login5/HomeServlet'>BEST <span>DEAL</span></a></h1>"+
						"<h2>new deals everyday</h2>" + "<span style='width:110px;display:inline-block'></span>"+ "</div>" + "</header>"+
						"<h5 style='color:blue;float:right' ><a href='/login5/SignOutServlet'>Sign Out</a></h5></br>"+
						"<table>"+"<tr>"+"<th>Product Id</th>"+"<th>Product Name</th>"+"<th>Product Retailer</th>"+"<th>Product Price</th>"+"</tr>");

                
                for (Map.Entry<String, List<Products>> hm : SaxParser.m.entrySet()) //TRAVERSE THE FILE USING HASH MAP
                    {
                        productList = hm.getValue();

                        for (Products p : productList) {
						pw.println("<tr>");
                        pw.println("<td bgcolor='#c98e8e'>"+p.productId+"</td>");
                        pw.println("<td>"+p.productName+"</td>");
                        pw.println("<td bgcolor='#c98e8e'>"+p.productRetailer+"</td>");
                        pw.println("<td>"+p.productPrice+"</td>");
						pw.println("</tr>");
						}
                    }
                  
		
         pw.println("<td>"+"<a href='/login5/AddProductByAdminDesignServlet'><b>Add Product </b></a>"+"</td>"); //LOAD ADD PRODUCTS BY ADMIN DESIGN SERVLET
		 pw.println("<td>");
		 /*
		 USER ENTER THE ID OF PRODUCT TO BE UPDATED AND UPDATE PRODUCT DESIGN BY ADMIN SERVLET WILL OPEN
		 */
         pw.println("<form method='post' action='/login5/UpdateProductDesignByAdminServlet'>"+"<p>Enter the Product IDyou like to update</p>");
         pw.println("<input type='text' name='productUpdateId'>"+"<input type='submit' name='submit' style='background-color:#c98e8e'>"+"</form>");
		 pw.println("</td>");
		 pw.println("<td>");
		 /*
		 USER ENTER THE ID OF PRODUCT TO BE DELETED AND DELETE PRODUCT DESIGN BY ADMIN SERVLET WILL OPEN
		 */
         pw.println("<form method='post' action='/login5/DeleteProductByAdminServlet' style='float:right','font-size:900%>"+"<p>Enter the Product ID you like to delete</p>");
         pw.println("<input type='text' name='productDeleteId'>"+"<input type='submit' name='submit' style='background-color:#c98e8e'>"+"</form>");  
		 pw.println("</td>");
		 pw.println("</tr>");
         pw.close();       
            
		 
	 }
	 
	 /*
	 WHEN CLIENT SUCCESSFULLY ENTERS THE CREDENTIALS
	 */
	 
    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter pw = response.getWriter();
		
		
		pw.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />"+
					"<title>Welcome to Best Deals</title>"+
					"<link rel='stylesheet' href='styles.css' type='text/css' />"+ 
					"<script type='text/javascript' src='javascript.js'></script>"+
					"</head>");
		
		pw.println("<body onload='init()''>"+
					"<div id='container'>"+
					"<header>"+
					"<div id=imageLogo>"+
					"<img src='images/best_deals.png'/>"+
					"<h1><a href='/'>Best<span>Deals</span></a></h1>"+
					"<h2>new deals everyday</h2>"+
					"<h5 style='color:blue;float:right' ><a href='/login5/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ userid +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='#'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
					/*
					PHONE DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login5/ContactServlet'>ContactUS</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					/*"<form method='get' id='search' action='#'>"+
					"<span>"+
					"<input type='text' value='Search...' id='searchbox' />"+
					//"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
					"</span>"+
					"</form>"+*/
					"</div>"+
					"</li>"+
					"</ul>"+
					"</nav>"+
					"<img class=header-image src='images/combine_images.png' alt='phones' />"+
					"</div>"+
					"<div id='search_body'>"+
					"<form name='autofillform' action='autocomplete'><table><tbody>"+
					"<input type='text' size='30'  name='searchId' id='searchId' onkeyup='doCompletion()' style='font-size: 16px;' placeholder='Search Here...!!' />"+
					//"<img src='images/Search.png' style='margin-top:10px;margin-left: 5px;'/>"+
					"</td></tr><tr><td id='auto-row' colspan='2'><table id='complete-table' class='popupBox' /></td></tr></tbody></table></form>"+ 
					"</div>"+
					"<div id='body'>"+
					"<section id='content'>"+
					"<article>"+
					"<h2>Introduction to BEST DEALS</h2>"+
					"<p>Welcome to Best Deals, a place to do your shopping. "+
					"We gurantee that you will get the lowest price which you have never seen before. "+
					"Apart from low price we maintain quality so our custmoers have 100% satisfaction. "+
					"You can shop best Smart Phones, Tablets, Smart Tv, Laptops, more products are comming soon. "+
					"Hope you wil have best experience here. Enjoy!</p>	"+
					"</article>"+
					"</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
					/*
					PHONE DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TvDataStoreServlet'>TV</a></li>"+
					/*
					TRENDING PRODUCT SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TrendingProductServlet'>Trending</a></li>"+
                    "</ul>"+
					"</li>"+
					"<li>"+
                    "<h4>About us</h4>"+
                    "<ul>"+
                    "<li class='text'>"+
                    "<p style='margin: 0;'>Welcome to Best Deals, Where you will get new deals everyday</p>"+
                    "</li>"+
                    "</ul>"+
					"</li>"+
					"</ul>"+
					"</aside>"+
					"<div class='clear'></div>"+
					"</div>"+
					"</div>"+
					"</body>"+
					"</html>");	
 
    }
	
	public static void showError(HttpServletResponse response, String message) throws ServletException, java.io.IOException{
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
					"<span style='width:310px;display:inline-block'></span>"+"<a href='HomeServlet'>Sorry</a>");  
					
					pw1.close();
		
	}
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}
