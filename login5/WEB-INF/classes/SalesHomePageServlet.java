import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class SalesHomePageServlet extends HttpServlet implements Serializable {
	
	transient List<String> cartList = new ArrayList<String>();
	transient List<String> cartList1 = new ArrayList<String>();

	public HashMap<String, List<SalesModel>> map;
	public HashMap<String, List<SalesModel>> map1;
	
	transient List<SalesModel> tvList = new ArrayList<SalesModel>();
	transient List<SalesModel> productList = new ArrayList<SalesModel>();
	transient List<SalesModel> phoneList = new ArrayList<SalesModel>();
	transient List<SalesModel> tabletList = new ArrayList<SalesModel>();

	//public Map<String, List<SalesModel>> prod ;
	public String k;
	public Products p;
	public String username;

	public ServletContext sc;
	public String firstLetter;
    public String productcategory;
    public String productretailer;
    public String productid;
    public String productname;        
    public int productprice = 0;
    public String prodpricestr;
	public String productformid;
	
	public int totalPrice=0;
	public int val;
	
	//FETCH THE DATA STORE IN ORDER HISTORY TABLE, SO IN UPDATING THE ORDER WE ARE FETCHING, SO FETCH LIKE THAT, ADD DELETE/CANCEL AND UPDATE FUNCTIONALITY.

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		System.out.println("The username recieved inside productCartServlet is: "+ username);
		
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        
                        
        HashMap<String,List<SalesModel>> mapInFile = new HashMap<String,List<SalesModel>>();
      
		//List<SalesModel> salesDisplayList = new ArrayList<SalesModel>();
		
		mapInFile = MySqlDataStore.salesProductDisplay(); 
              
		
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
					"<li><a href='#'>Home</a></li>"+
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
					"<h2>Order History:</h2>");
					/*
					PRINTING DATA INSIDE CARTLIST ON HTML PAGE
					*/
					
					for (Map.Entry<String, List<SalesModel>> hm : mapInFile.entrySet()) 
                                {                   
                                    k = hm.getKey();
                                    
                                        productList = hm.getValue();
                                        System.out.println("The Key of SALES is: "+k+"&"+" the value in list is: "+productList);
										
                                        for(SalesModel prod : productList){
											pw.println(	"<TABLE BORDER=1 ALIGN=CENTER>\n" +
														"<TR BGCOLOR='#FFAD00'>\n" +
														"<TH>Details<TH>Values");
											pw.print("<TR><TD Style='Color:Red'> Product ID: \n</TD>");
											pw.print("<TD>" + prod.orderId + "\n</TD></TR>");
											
											pw.print("<TR><TD Style='Color:Red'> First Name: \n</TD>");
											pw.print("<TD>" + prod.firstName + "\n</TD></TR>");
											
											pw.print("<TR><TD Style='Color:Red'> Last Name: \n</TD>");
											pw.print("<TD>" + prod.lastName + "\n</TD></TR>");
											
											pw.print("<TR><TD Style='Color:Red'> Product Price: \n</TD>");
											pw.print("<TD>" + prod.price + "\n</TD></TR>");
											
											pw.print("<TR><TD Style='Color:Red'> Address: \n</TD>");
											pw.print("<TD>" + prod.address + "\n</TD></TR>");
											
											pw.print("<TR><TD Style='Color:Red'> Phone Number: \n</TD>");
											pw.print("<TD>" + prod.phone + "\n</TD></TR>");
											
											pw.print("<TR><TD Style='Color:Red'> Credit Card: \n</TD>");
											pw.print("<TD>" + prod.creditCard + "\n</TD></TR>");
											
											pw.println("</TABLE>\n");
											/*pw.println("<p Style='Color:Red'>Product ID:</p>");
											pw.println("<p>"+prod.orderId+"</p>");
											pw.println("<p Style='Color:Red'>First Name:</p>");
											pw.println("<p>"+prod.firstName+"</p>");
											pw.println("<p Style='Color:Red'>Last Name:</p>");
											pw.println("<p>"+prod.lastName+"</p>");
											pw.println("<p Style='Color:Red'>Product Price:</p>");
											pw.println("<p>"+prod.price+"</p>");
											pw.println("<p Style='Color:Red'>Address:</p>");
											pw.println("<p>"+prod.address+"</p>");
											pw.println("<p Style='Color:Red'>Phone Number:</p>");
											pw.println("<p>"+prod.phone+"</p>");
											pw.println("<p Style='Color:Red'>Credit Card:</p>");
											pw.println("<p>"+prod.creditCard+"</p>");*/
											/*pw.println("<form action='/login5/DeleteProductFromCartServlet' method='get' style='margin-bottom:20px;'>"+
											"<input type='hidden' name='deleteFormId' value='"+prod.productId+"' />" +"<span style='width:110px;display:inline-block'></span>"+
											"<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;color:white;background-color:red;border:2px solid #336600'>" +
											"</form>");*/
											//pw.println("<hr width='90%' size='2' align='center'>");
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

    /*
	CALL IS FIRST RECIEVED HERE AND processRequest() METHOD IS CALLED
	*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 
    /*
	CALL IS FIRST RECIEVED HERE AND processRequest() METHOD IS CALLED
	*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}