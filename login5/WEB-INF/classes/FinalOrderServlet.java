import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;
import java.util.*;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FinalOrderServlet extends HttpServlet {
	
	//public String productName;
	public String productPrice;
	public String firstName;
	public String lastName;
	public String address;
	public String phoneNumber;
	public String creditCard;
	
	public String username;
	public String message;
	
	public Random random;
	public String orderNumber;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		
			HttpSession session = request.getSession(true);
			//productName = request.getParameter("productName");
			productPrice = request.getParameter("productPrice");
			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName");
			address = request.getParameter("address");
			phoneNumber = request.getParameter("phoneNumber");
			creditCard = request.getParameter("creditcard");			
			
			username=((String)session.getAttribute("userid"));
			message = "Your Order Placed Successfully";
			
			random = new Random( System.currentTimeMillis() );
			int rand= 10000 + random.nextInt(20000);
			orderNumber=Integer.toString(rand);			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); 
			c.add(Calendar.DATE, 14);
			String output = sdf.format(c.getTime());

				
			PrintWriter pw = response.getWriter();			
		
			MySqlDataStore.insertHistoryInDatabase(orderNumber,firstName,lastName,productPrice,address,phoneNumber,creditCard);
			MySqlDataStore.clearProductCart();
			
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
					"<h2>Your Order:</h2>");
                
			pw.println("<HEAD><TITLE>" + message + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=CENTER>" + message + "</H1>\n" +
				"<H3 ALIGN=CENTER>Your Order Number :" + orderNumber + "</H3>\n" +
                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>Parameter Name<TH>Parameter Value(s)");
				
			pw.print("<TR><TD> First Name  \n</TD>");
			pw.print("<TD>" + request.getParameter("firstName") + "\n</TD></TR>");
				
				
			pw.print("<TR><TD> Last Name  \n</TD>");
			pw.print("<TD>" + request.getParameter("lastName") + "\n</TD></TR>");
				
				
			pw.print("<TR><TD> Phone Number \n</TD>");
			pw.print("<TD>" + request.getParameter("phoneNumber") + "\n</TD></TR>");
				
			pw.print("<TR><TD> Address  \n</TD>");
			pw.print("<TD>" + request.getParameter("address") + "\n</TD></TR>");
				
				
					
			pw.print("<TR><TD> Delivery Date  \n</TD>");
			pw.print("<TD>" + output + "\n</TD></TR>");
			pw.println("</TABLE>\n");

			pw.println(	"</TABLE>\n" +
						"<FORM ACTION=/login5/OrderHistoryServlet  " +
						" METHOD='get'>\n"+
						"<BIG><CENTER>\n" +
						"<p>Thank you for shopping with us...</p>"+
						"<INPUT TYPE='SUBMIT'\n" +
						"VALUE='View Order History'>\n" +
		   
						"</CENTER></BIG></FORM>");
			pw.println(	"</article>"+
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
						
			pw.close();


}

	
}