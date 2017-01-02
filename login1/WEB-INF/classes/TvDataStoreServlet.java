/*
USER CLICKS ON TV IN HOME PAGE AND TV DATA STORE SERVLET IS CALLED
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TvDataStoreServlet extends HttpServlet implements Serializable{
	public ServletContext sc;
	
	public List<Products> productList;
	public String k;
	public String username;
	
	public void runThisMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
		sc=request.getSession().getServletContext();
        HttpSession s=request.getSession();
        username=(String)s.getAttribute("userid");
		
		printHtmlPage(response);//CALL TO PRINT HTML PAGE
		
	}
	
	
	public void printHtmlPage(HttpServletResponse response) throws ServletException, java.io.IOException{
			response.setContentType("text/html");
			java.io.PrintWriter pw = response.getWriter();
					
			productList=new ArrayList<Products>(); //ARRAY LIST TYPE PRODUCTS IS DEFINED
					
					
		try{
			/*
			ALL DATA FROM ALL CONTENT FILE IS RED AND COMES INTO HASH MAP NAME-MAP
			*/
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("AllContentFile"))));
			HashMap<String, List<Products>> hm=(HashMap<String,List<Products>>)ois.readObject();
			ois.close();
			
			/*
			TRAVERSE THROUGH HASH MAP
			*/
			
			for(Map.Entry<String,List<Products>> map:hm.entrySet()){
				k=map.getKey();
				if(k.equals("TV")){
					productList=map.getValue();
				}	
			}
			System.out.println(productList);
			for(Products p:productList){
				System.out.println(p.productId);
				System.out.println(p.productName);
				System.out.println(p.productPrice);
				System.out.println(p.productRetailer);
				
			}
		}
		catch(Exception e){
			
		}
					
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
					"<h5 style='color:blue;float:right' ><a href='/login1/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ username +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='#'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
                    "<li><a href='/login1/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login1/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login1/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login1/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login1/ContactServlet'>ContactUS</a></li>"+
					//"<li><a href='#'>Welcome</a></li>"+
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
					"<h2>CHOOSE YOUR SMART TV TODAY</h2>");
					/*
					TRAVERSING THORUGH PRODUCT LIST WHICH HAS DATA OF TV
					*/
					for(Products prod : productList){
					pw.println("<p><img src="+prod.productImage+"/>");
					pw.println("<p Style='Color:Red'>Product ID:</p>");
					pw.println("<p>"+prod.productId+"</p>");
					pw.println("<p Style='Color:Red'>Product Name:</p>");
					pw.println("<p>"+prod.productName+"</p>");
					pw.println("<p Style='Color:Red'>Product Price:</p>");
					pw.println("<p>"+prod.productPrice+"</p>");
					pw.println("<p Style='Color:Red'>Product Manufacturer:</p>");
					pw.println("<p>"+prod.productRetailer+"</p>");
					/*
					WHEN USER CLICKS ON ADD TO CART -- ADDING INTO CART SERVLET WILL BE CALLED
					*/
					pw.println("<form action='/login1/AddingIntoCartServlet' method='get' style='margin-bottom:20px;'>");
					pw.println("<input type='hidden' name='productFormId' value='"+prod.productId+"' />");
					pw.println("<input type='submit' value='Add to Cart' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>");
					pw.println("<hr width='85%' size='4' align='center'");
					}
					
		pw.println("</article>"+
					"</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
                    "<li><a href='/login1/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login1/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login1/LaptopDataStoreServlet'>Laptops</a></li>"+
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
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
}