/*
USER CLICKS ON PHONE IN HOME PAGE AND PHONE DATA STORE SERVLET IS CALLED
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class PhoneDataStoreServlet extends HttpServlet implements Serializable{
	
	public ServletContext sc;
	
	public List<Products> productList;
	public String k="Phone";
	public String username;
	
	
	public void runThisMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
		
		sc=request.getSession().getServletContext();
		
        HttpSession s=request.getSession();
        username=(String)s.getAttribute("userid");
		
		printHtmlPage(request,response); //CALL TO PRINT HTML PAGE
		
		
	}
	
	
	public void printHtmlPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException{
					
					
		response.setContentType("text/html");
        java.io.PrintWriter pw = response.getWriter();
		
		productList=new ArrayList<Products>(); //ARRAY LIST TYPE PRODUCTS IS DEFINED
		productList= MySqlDataStore.selectPhoneProducts(k);
		
		
		
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
					"<h5 style='color:blue;float:right' ><a href='/login4/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ username +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='MainHomePageServlet'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
                    "<li><a href='/login4/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login4/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login4/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login4/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login4/ContactServlet'>ContactUS</a></li>"+
					//"<li><a href='#'>Welcome</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					/*"<form method='get' id='search' action='#'>"+
					"<span>"+
					"<input type='text' value='Search...' id='searchbox' />"+
					"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
					"</span>"+
					"</form>"+*/
					"</div>"+
					"</li>"+
					"</ul>"+
					"</nav>"+
					"<img class=header-image src='images/combine_images.png' alt='phones' />"+
					"</div>"+
					"<div id='body'>"+
					"<section id='content'>"+
					"<article>"+
					"<h2>CHOOSE YOUR SMART PHONES TODAY</h2>");
					/*
					TRAVERSING THORUGH PRODUCT LIST WHICH HAS DATA OF PHONE
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
					pw.println("<form action='/login4/AddingIntoCartServlet' method='get' style='margin-bottom:20px;'>");
					pw.println("<input type='hidden' name='productFormId' value='"+prod.productId+"' />");
					pw.println("<input type='submit' value='Add to Cart' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>");
					//pw.println("<hr width='85%' size='4' align='center'");
					
					pw.println("<form action='/login4/WriteReviewServlet' method='get' style='margin-bottom:20px;'>");
					pw.println("<input type='hidden' name='productFormId' value='"+prod.productId+"' />");
					pw.println("<input type='hidden' name='productFormName' value='"+prod.productName+"' />");
					pw.println("<input type='hidden' name='productFormImage' value='"+prod.productImage+"' />");
					pw.println("<input type='hidden' name='productFormRetailer' value='"+prod.productRetailer+"' />");
					pw.println("<input type='hidden' name='productFormPrice' value='"+prod.productPrice+"' />");
					pw.println("<input type='submit' value='Write Review' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>");
					
					pw.println("<form action='/login4/ReadReviewServlet' method='get' style='margin-bottom:20px;'>");
					pw.println("<input type='hidden' name='productFormId' value='"+prod.productId+"' />");
					pw.println("<input type='submit' value='Read Review' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>");
					//pw.println("<hr width='85%' size='4' align='center'");
					}
					
		pw.println("</article>"+
					"</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
                    "<li><a href='/login4/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login4/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login4/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login4/TvDataStoreServlet'>TV</a></li>"+
					/*
					TRENDING PRODUCT SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/TrendingProductServlet'>Trending</a></li>"+
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
		pw.close();
 
	}
	
	/*
	CALL COMES HERE AND RUN THIS METHOD WILL BE CALLED
	*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
/*
	CALL COMES HERE AND RUN THIS METHOD WILL BE CALLED
	*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
}