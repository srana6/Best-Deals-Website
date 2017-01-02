import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class MainHomePageServlet extends HttpServlet {
	public ServletContext sc;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		sc=request.getSession().getServletContext();
		PrintWriter pw= response.getWriter(); 
		response.setContentType("text/html");
		
		HttpSession s=request.getSession();
    	String userid=(String)s.getAttribute("userid");
		
		pw.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />"+
					"<title>Welcome to Best Deals</title>"+
					"<script src='javascript.js'></script>"+
					"<link rel='stylesheet' href='styles.css' type='text/css' />"+ 
					"</head>");
		
		pw.println("<body onload='init()'>"+
					"<div id='container'>"+
					"<header>"+
					"<div id=imageLogo>"+
					"<img src='images/best_deals.png'/>"+
					"<h1><a href='/'>Best<span>Deals</span></a></h1>"+
					"<h2>new deals everyday</h2>"+
					"<h5 style='color:blue;float:right' ><a href='/login4/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ userid +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='MainHomePageServlet'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
					/*
					PHONE DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login4/ContactServlet'>ContactUS</a></li>"+
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
                    "<li><a href='/login4/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
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
 
    }
	
		
	}