import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class WriteReviewServlet extends HttpServlet {
	
	public String username;
	public String firstLetter;
	public String productCategory;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			HttpSession s=request.getSession();
			username=(String)s.getAttribute("userid");
		
			String productID=request.getParameter("productFormId");
			String productName=request.getParameter("productFormName");
			String productImage=request.getParameter("productFormImage");
			System.out.println("Image: "+productImage);
			String productRetailer=request.getParameter("productFormRetailer");
			String productPrice=request.getParameter("productFormPrice");
			HttpSession session=request.getSession(true); 
			String user=((String)session.getAttribute("userid"));
		
			response.setContentType("text/html");
		
			PrintWriter pw = response.getWriter();
			
			firstLetter = String.valueOf(productID.charAt(0)); 
			
			if(firstLetter.equals("l"))
            {
                productCategory="Laptop";
				productImage="\'images/lp_03.png\'";
			}
			else if(firstLetter.equals("t")){
				productCategory="TV";
				productImage="\'images/tv_3.jpg\'";
			}
			else if(firstLetter.equals("b")){
				productCategory="Tablet";
				productImage="\'images/tab_03.jpg\'";
			}
			else if(firstLetter.equals("p")){
				productCategory="Phone";
				productImage="\'images/s_03.jpg\'";
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
					"<h5 style='color:blue;float:right' ><a href='/login4/SignOutServlet'>Sign Out</a></h5></br>"+
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
					"<h2>Please Write Review</h2>");
			
									
			pw.println(" <h3>" +productName+ "</h3> ");
			pw.println("<form method='get' action='/login4/SubmitReviewToMongoServlet'>");
			pw.println("<fieldset>");
			pw.println("<p><img src="+productImage+"/>");
			pw.println("<legend>Product information:</legend>");
			
			pw.println("<table>");
			
			pw.println("<tr>");
			pw.println("<td style='width:30%'> Product Name: </td>");
			pw.println("<td>"+productName+"</td>");
			pw.println("</tr>");
			
			
			pw.println("<tr>");
			pw.println("<td style='width:30%'> Product Price: </td>");
			pw.println("<td>"+productPrice+" </td>");
			pw.println("</table>");
			pw.println("</fieldset>");
			pw.println("<fieldset>");
			pw.println("<legend>Reviews:</legend>");
			
			
		
			
			
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<td> Product Model Name: </td>");
			pw.println("<td> <input type='text' name= 'productName' readonly value = \""+productName+"\">  </td>");
			pw.println("</tr>");
			
		
			
			pw.println("<td> <input type='hidden' name= 'productID' readonly value = \""+productID+"\">  </td>");
			
			

			pw.println("<tr>");
			pw.println("<td> Product Category: </td>");
			pw.println("<td> <input type='text' name= 'productCategory' readonly value = \""+productCategory+"\">  </td>");
			pw.println("</tr>");
			
			
			pw.println("<tr>");
			pw.println("<td> Price </td>");
			pw.println("<td> <input type='text' name= 'productPrice' readonly value = \""+productPrice+"\">  </td>");
			pw.println("</tr>");
			
			
			pw.println("<tr>");
			pw.println("<td> Retailer Name</td>");
			pw.println("<td> <input type='text' name='productRetailer' readonly value = \""+productRetailer+"\" >  </td>");
			pw.println("</tr>");
			
			
			pw.println("<tr>");
			pw.println("<td> Retailer Zip</td>");
			pw.println("<td> <input type='number' name= 'retailerzip' >  </td>");
			pw.println("</tr>");
			
			
			pw.println("<tr>");
			pw.println("<td> Retailer City</td>");
			pw.println("<td> <input type='text' name='retailercity' >  </td>");
			pw.println("</tr>");
			
		
			pw.println("<tr>");
			pw.println("<td> Retailer State</td>");
			pw.println("<td> <input type='text' name= 'retailerstate' >  </td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> Product on Sale </td>");
			pw.println("<td>");
			pw.println("<select name='sale'>");
			pw.println("<option value='Yes' selected>Yes</option>");
			pw.println("<option value='No'>No</option>");
			pw.println("</td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> Manufacturer Name </td>");
			pw.println("<td>");
			pw.println("<select name=\"mfname\">");
			pw.println("<option value=\"LG\" selected>LG</option>");
			pw.println("<option value=\"Samsung\" selected>Samsung</option>");
			pw.println("<option value=\"Apple\" selected>Apple</option>");
			pw.println("<option value=\"Lenovo\" selected>Lenovo</option>");
			pw.println("<option value=\"HCL\">HCL</option>");
			pw.println("<option value=\"Sony\">Sony</option>");
			pw.println("<option value=\"Dell\">Dell</option>");
			pw.println("<option value=\"Motorola\">Motorola</option>");
			pw.println("<option value=\"LaptopSellCompany\">LaptopSellCompany</option>");
			pw.println("<option value=\"TVSellCompany\">TVSellCompany</option>");
			pw.println("<option value=\"PhoneSellCompany\">PhoneSellCompany</option>");
			pw.println("<option value=\"OnePlus\">OnePlus</option>");
			pw.println("</td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> Manufacturer Rebate </td>");
			pw.println("<td>");
			pw.println("<select name=\"rebate\">");
			pw.println("<option value=\"Yes\" selected>Yes</option>");
			pw.println("<option value=\"No\">No</option>");
			pw.println("</td>");
			pw.println("</tr>");
			
			
			pw.println("<tr>");
			pw.println("<td> User ID </td>");
			pw.println("<td> <input type=\"text\" name= \"userid\" readonly value = \""+username+"\">  </td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> User Age: </td>");
			pw.println("<td> <input type=\"text\" name=\"userAge\"> </td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> User Gender: </td>");
			pw.println("<td> <input type=\"text\" name=\"userGender\"> </td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> User Occupation: </td>");
			pw.println("<td> <input type=\"text\" name=\"userOccupation\"> </td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> Review Rating: </td>");
			pw.println("<td>");
			pw.println("<select name=\"reviewRating\">");
			pw.println("<option value=\"1\" selected>1</option>");
			pw.println("<option value=\"2\">2</option>");
			pw.println("<option value=\"3\">3</option>");
			pw.println("<option value=\"4\">4</option>");
			pw.println("<option value=\"5\">5</option>");
			pw.println("</td>");
			pw.println("</tr>");
			
			
			
			
			pw.println("<tr>");
			pw.println("<td> Review Date: </td>");
			pw.println("<td> <input type=\"text\" name=\"reviewDate\"> </td>");
			pw.println("</tr>");
			pw.println("<tr>");
			
			pw.println("<td> Review Text: </td>");
			pw.println("<td><textarea name=\"reviewText\" rows=\"4\" cols=\"50\"> </textarea></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("<br><br>");
			pw.println("<input type=\"submit\" value=\"Submit Review\">");
			pw.println("</fieldset>");
			pw.println("</form>");
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
	
			
						
	    } 
	}