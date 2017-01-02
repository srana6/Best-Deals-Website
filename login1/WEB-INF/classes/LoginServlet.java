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
 
	public String k;
	
	public File file;
	public long size;
   
    protected Map users = new HashMap();
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
                users.put("admin", "admin");
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		PrintWriter pw= response.getWriter(); 
		response.setContentType("text/html");
		
		file =new File("C:\\apache-tomcat-7.0.34\\webapps\\login1\\AllContentFile");
        if(file.exists()){
            
            size = file.length();
            
        }
        else{
             pw.println("File does not exists!");
        }
        if(size <= 0){
        SaxParser.loadProducts();
		}
		
		
        userid = request.getParameter("username");
        password = request.getParameter("password");
		
		sc=request.getSession().getServletContext();
		
        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(userid != null && password != null) {
			
			try{
				if(userid.equals("admin")&&password.equals("admin")){
					showAdminPage(request,response);
					
				}
				else{
					
				
      
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("signincredentialstore"))));

        HashMap<String,List<String>> mapInFile=(HashMap<String,List<String>>)ois.readObject();

        ois.close();
        
        for(Map.Entry<String,List<String>> m :mapInFile.entrySet()){
			
			id=m.getKey();
			l=m.getValue();
 
		}
				}
			}
		catch(Exception e){
			
		}
		for(int i=0;i<l.size();i++){
			match=l.get(i);
			if(match.equals(password)){
				
				/*
				WHEN THE CLIENT WILL ADD HIS/HER CREDENTIALS SHOW PAGE METHOD WILL BE CALLED
				*/
				
				showPage(response, "Login Success!");
				HttpSession s=request.getSession();
				s.setAttribute("userid",userid);
				flag=true;
			}
		}
		if(flag == false){
			showError(response, "Login Failure! Username or password is incorrect");
		}
    }
}
    
    /**
     * ADMIN PAGE FUNCTIONALITY
     */
	 
	 protected void showAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
		 try{
			sc=request.getSession().getServletContext();
			PrintWriter pw= response.getWriter(); 
			response.setContentType("text/html");
			
			pw.println("<html>"+
						"<head>"+
						"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
						"<title>SignIn : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
						"</head>"+
						"<body>"+
						"<div id='container'>"+
						"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
						"<h1><a href='/login1/HomeServlet'>BEST <span>DEAL</span></a></h1>"+
						"<h2>new deals everyday</h2>" + "<span style='width:110px;display:inline-block'></span>"+ "</div>" + "</header>"+
						"<h5 style='color:blue;float:right' ><a href='/login1/SignOutServlet'>Sign Out</a></h5></br>"+
						"<table>"+"<tr>"+"<th>Product Id</th>"+"<th>Product Name</th>"+"<th>Product Retailer</th>"+"<th>Product Price</th>"+"</tr>");

            try{
				
				/*
				READING THE FILE WHICH GOT FILLED BY SAX PARSER
				*/
				
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("AllContentFile"))));
                 
                HashMap<String, List<Products>> mapInFile=(HashMap<String,List<Products>>)ois.readObject();
                    
                ois.close();
                
                for (Map.Entry<String, List<Products>> hm : mapInFile.entrySet()) //TRAVERSE THE FILE USING HASH MAP
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
                }catch(Exception e){  }  
		
         pw.println("<td>"+"<a href='/login1/AddProductByAdminDesignServlet'><b>Add Product </b></a>"+"</td>"); //LOAD ADD PRODUCTS BY ADMIN DESIGN SERVLET
		 pw.println("<td>");
		 /*
		 USER ENTER THE ID OF PRODUCT TO BE UPDATED AND UPDATE PRODUCT DESIGN BY ADMIN SERVLET WILL OPEN
		 */
         pw.println("<form method='post' action='/login1/UpdateProductDesignByAdminServlet'>"+"<p>Enter the Product IDyou like to update</p>");
         pw.println("<input type='text' name='productUpdateId'>"+"<input type='submit' name='submit' style='background-color:#c98e8e'>"+"</form>");
		 pw.println("</td>");
		 pw.println("<td>");
		 /*
		 USER ENTER THE ID OF PRODUCT TO BE DELETED AND DELETE PRODUCT DESIGN BY ADMIN SERVLET WILL OPEN
		 */
         pw.println("<form method='post' action='/login1/DeleteProductByAdminServlet' style='float:right','font-size:900%>"+"<p>Enter the Product ID you like to delete</p>");
         pw.println("<input type='text' name='productDeleteId'>"+"<input type='submit' name='submit' style='background-color:#c98e8e'>"+"</form>");  
		 pw.println("</td>");
		 pw.println("</tr>");
         pw.close();       
            
			 
		 }
		
		 catch(Exception e){
			 
		 }
		 
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
					"</head>");
		
		pw.println("<body>"+
					"<div id='container'>"+
					"<header>"+
					"<div id=imageLogo>"+
					"<img src='images/best_deals.png'/>"+
					"<h1><a href='/'>Best<span>Deals</span></a></h1>"+
					"<h2>new deals everyday</h2>"+
					"<img src='images/cart.png' style='float:left'/>"+
					"<h5 style='color:blue;float:right' ><a href='/login1/SignOutServlet'>Sign Out</a></h5></br>"+
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
                    "<li><a href='/login1/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login1/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login1/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login1/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login1/ContactServlet'>ContactUS</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					"<form method='get' id='search' action='#'>"+
					"<span>"+
					"<input type='text' value='Search...' id='searchbox' />"+
					"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
					"</span>"+
					"</form>"+
					"</div>"+
					"</li>"+
					"</ul>"+
					"</nav>"+
					"<img class=header-image src='images/combine_images.png' alt='phones' />"+
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
                    "<li><a href='/login1/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login1/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login1/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login1/TvDataStoreServlet'>TV</a></li>"+
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
	
	public void showError(HttpServletResponse response, String message) throws ServletException, java.io.IOException{
		response.setContentType("text/html");
		PrintWriter pw1=response.getWriter();
		pw1.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />"+
					"<title>Error</title>"+ 
					"</head>");
		
		pw1.println("<body>"+
					"<p><h1>"+message+"</h1></p>"+
					"</body></html>");
		
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
