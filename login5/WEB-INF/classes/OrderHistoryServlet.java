import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class OrderHistoryServlet extends HttpServlet {
  
  public String username;
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    
		HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
		HashMap<String,String> hashmap = new HashMap<String,String>();
              
		PrintWriter pw = response.getWriter();     
    
		hashmap = MySqlDataStore.selectOrderHistory(); 
		
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
					"<h5 style='color:blue;float:right' ><a href='/login5/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ username +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='DealMatches'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login5/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login5/ContactServlet'>ContactUS</a></li>"+
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
					"<h2>Order History</h2>");
					
					for (Map.Entry<String, String> hm : hashmap.entrySet()) 
					{
           
						String orderId = hm.getKey();
						String price = hm.getValue();
						/*pw.println("<p align='left'>Order ID:" + orderId ); 
						pw.println("<p align='left'>Price:" + price );*/ 
						
						pw.println("<TABLE BORDER=1 ALIGN=CENTER>\n" +
									"<TR BGCOLOR='#FFAD00'>\n" +
									"<TH>Details<TH>Values");
						
						pw.print("<TR><TD> ID \n</TD>");
						pw.print("<TD>" + orderId + "\n</TD></TR>");
				
				
						pw.print("<TR><TD> Price  \n</TD>");
						pw.print("<TD>" + price + "\n</TD></TR>");
						pw.println("</TABLE>\n");
					}
					
					pw.println("<form method='post' action='/login5/CancelOrderServlet'>"+"<p>Enter the Order ID which you want to Cancel</p>");
					pw.println("<input type='text' name='productUpdateId'>"+"<input type='submit' style='width:250px;color:white;background-color:red;border:2px solid #336600' name='Cancel'>"+"</form>");  

					pw.println("<form method='post' action='/login5/UpdateOrderServlet'>"+"<p>Enter the Order ID which you want to Update</p>");
					pw.println("<input type='text' name='productUpdateId'>"+"<input type='submit' style='width:250px;color:white;background-color:red;border:2px solid #336600' name='submit'>"+"</form>");
					pw.println("<hr width='90%' size='2' align='center'>");
					
					pw.println("</article>"+
					"</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
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
}  
