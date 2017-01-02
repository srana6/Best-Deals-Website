import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class UpdateOrderServlet extends HttpServlet {
	String username;
	String productUpdateId;
	public List<String> prodList;
	
	public String id;
	public String firstName;
	public String lastName;
	public String price;
	public String address;
	public String phone;
	public String creditCard;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
			
        productUpdateId =request.getParameter("productUpdateId");
		
        prodList=new ArrayList<String>(); 
		
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
		
		prodList= MySqlDataStore.selectUpdatedOrderHistory(productUpdateId); 
		
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
					"<h2>Update The Order</h2>");
					for(int i=0;i<prodList.size();i++){
						if(i==0){
						id=prodList.get(i);
						}
						else if(i==1){
						firstName=prodList.get(i);
						}
						else if(i==2){
						lastName=prodList.get(i);
						}
						else if(i==3){
						price=prodList.get(i);
						}
						else if(i==4){
						address=prodList.get(i);
						}
						else if(i==5){
						phone=prodList.get(i);
						}
						else if(i==6){
						creditCard=prodList.get(i);
						}
					}
	
                                        
		pw.println("<form method='post' action='/login5/UpdateOrderHistoryServlet'><h3>Edit Order</h3><table cellpadding='2' cellspacing='1'>");
        pw.println("<tr><td>OrderID</td><td><input type='TEXT' readonly='true' size='15' value='"+id+"' name='orderId1'></input></td></tr>");        
        pw.println("<tr><td>First Name</td><td><input type='TEXT' size='15' value='"+firstName+"' name='firstName1'></input></td></tr>");
        pw.println("<tr><td>Last Name</td><td><input type='TEXT' size='15' value='"+lastName+"' name='lastName1'></input></td></tr>");
        pw.println("<tr><td>price</td><td><input type='text' size='15' value='"+price+"' name='price1'/></td></tr>");
        pw.println("<tr><td>Address</td><td><input type='TEXT' size='15' value='"+address+"' name='address1'></input></td></tr>");
        pw.println("<tr><td>Phone</td><td><input type='TEXT' size='15' value='"+phone+"' name='phone1'></input></td></tr>");
        pw.println("<tr><td>Credit Card</td><td><input type='TEXT' size='15' value='"+creditCard+"' name='creditCard1'></input></td></tr>");
        pw.println("<tr><td colspan='2'><center><input type='submit' value='Edit Order' /></center></td></tr>");
        pw.println("</table></form>"); 
									
            
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