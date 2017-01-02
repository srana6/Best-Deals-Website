/*
CALL FROM "ADD TO CART SERVLET" WILL COME HERE
*/
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProductCartServlet extends HttpServlet implements Serializable {

	transient List<String> cartList = new ArrayList<String>();
	transient List<String> cartList1 = new ArrayList<String>();

	public HashMap<String, List<String>> map;
	public HashMap<String, List<Products>> map1;
	
	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public Map<String, List<Products>> prod ;
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

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
		
		String productformid =request.getParameter("productFormId");

		map1=new HashMap<String,List<Products>>();
		map1 = MySqlDataStore.selectProducts(productformid);
		
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
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ username +"</h3>");
		int productsInCart=MySqlDataStore.countProductsInCart();
		pw.println( "<span style='width:110px;display:inline-block;color:red'>CART:"+productsInCart+"</span>"+
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
					"<h2>CART</h2>");
					/*
					PRINTING DATA INSIDE CARTLIST ON HTML PAGE
					*/
					
					for (Map.Entry<String, List<Products>> hm : map1.entrySet()) 
                                {                   
                                    k = hm.getKey();
                                    
                                        laptopList = hm.getValue();
                                        
                                        for(Products prod : laptopList){
											pw.println("<p><img src="+prod.productImage+"/>");
											pw.println("<p Style='Color:Red'>Product ID:</p>");
											pw.println("<p>"+prod.productId+"</p>");
											pw.println("<p Style='Color:Red'>Product Name:</p>");
											pw.println("<p>"+prod.productName+"</p>");
											pw.println("<p Style='Color:Red'>Product Price:</p>");
											pw.println("<p>"+prod.productPrice+"</p>");
											pw.println("<p Style='Color:Red'>Product Manufacturer:</p>");
											pw.println("<p>"+prod.productRetailer+"</p>");
											pw.println("<form action='/login5/DeleteProductFromCartServlet' method='get' style='margin-bottom:20px;'>"+
											"<input type='hidden' name='deleteFormId' value='"+prod.productId+"' />" +"<span style='width:110px;display:inline-block'></span>"+
											"<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;color:white;background-color:red;border:2px solid #336600'>" +
											"</form>");
											pw.println("<hr width='90%' size='2' align='center'>");
                                    } 
									
											int totalPrice=MySqlDataStore.priceCalculate();
											System.out.println("Sum is: "+totalPrice);
											
											pw.println("<form action='/login5/PlaceOrderFromCartServlet' method='get'>"+
											"<input type='hidden' name='totalprice' value='"+totalPrice+"' />" +
											"<input style='float:center;width:250px;color:white;background-color:red;border:2px solid #336600' type='submit' value='Place Order' class='submit-button'/>" +
											"</form>");
											pw.println("<hr width='90%' size='2' align='center'>");
									
									
									
					/*for (Map.Entry<String, List<Products>> hm : map1.entrySet()) 
                        {                   
                            key = hm.getKey();
                            if(key.equals("Laptop"))
								{
                                   laptop_list = ee.getValue();
                                       
                                    for (Product p : laptop_list) {
                                            
                                        
                                            out.println("<p align='left'>ID:" + p.id + "<p align='left'>Name:" + p.name + "</p>");
                                            out.println("<p align='left'>Retailer:" + p.retailer + "</p>");
                                            out.println("<p align='left'><img src =" + p.image + "width = '200' height = '200' alt =" + p.name +"></p>");
                                            //out.println("<button onclick='WriteToCartFile()'>Add to Shopping Cart</button>");
                                            out.println("<form action='/Assignment2_Test/DeleteCartServlet' method='get' style='margin-bottom:20px;'>"+
                        "<input type='hidden' name='deleteFormId' value='"+p.id+"' />" +"<span style='width:110px;display:inline-block'></span>"+
                        "<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;'>" +
                        "</form>");
                                         } 
                                    } 

                                    if(key.equals("TV"))
                                    {
                                        tv_list = ee.getValue();
                                        //out.println("Key:"+key);
                                        //out.println("Laptop_List::"+laptop_list);
                                        //break;
                                        for (Product p : tv_list) {
            
                                            out.println("<p align='left'>ID:" + p.id + "<p align='left'>Name:" + p.name + "</p>");
                                            out.println("<p align='left'>Retailer:" + p.retailer + "</p>");
                                            out.println("<p align='left'><img src =" + p.image + "width = '200' height = '200' alt =" + p.name +"></p>");
                                            //out.println("<button onclick='WriteToCartFile()'>Add to Shopping Cart</button>");
                                            out.println("<form action='/Assignment2_Test/DeleteCartServlet' method='get' style='margin-bottom:20px;'>"+
                        "<input type='hidden' name='deleteFormId' value='"+p.id+"' />" +"<span style='width:110px;display:inline-block'></span>"+
                        "<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;'>" +
                        "</form>");
                                         } 
                                    }       

                                    if(key.equals("SmartPhone"))
                                    {
                                        sm_list = ee.getValue();
                                        //out.println("Key:"+key);
                                        //out.println("Laptop_List::"+laptop_list);
                                        //break;
                                        for (Product p : sm_list) {
            
            out.println("<p align='left'>ID:" + p.id + "<p align='left'>Name:" + p.name + "</p>");
            out.println("<p align='left'>Retailer:" + p.retailer + "</p>");
            out.println("<p align='left'><img src =" + p.image + "width = '200' height = '200' alt =" + p.name +"></p>");
            //out.println("<button onclick='WriteToCartFile()'>Add to Shopping Cart</button>");
            out.println("<form action='/Assignment2_Test/DeleteCartServlet' method='get' style='margin-bottom:20px;'>"+
                        "<input type='hidden' name='deleteFormId' value='"+p.id+"' />" +"<span style='width:110px;display:inline-block'></span>"+
                        "<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;'>" +
                        "</form>");           
            out.println("<hr width='90%' size='2' align='center'>");
         } 
                                    }       


                                    if(key.equals("Tablet"))
                                    {
                                        tab_list = ee.getValue();
                                        //out.println("Key:"+key);
                                        //out.println("Laptop_List::"+laptop_list);
                                        //break;
                                        for (Product p : tab_list) {
            
            out.println("<p align='left'>ID:" + p.id + "<p align='left'>Name:" + p.name + "</p>");
            out.println("<p align='left'>Retailer:" + p.retailer + "</p>");
            out.println("<p align='left'><img src =" + p.image + "width = '200' height = '200' alt =" + p.name +"></p>");
            //out.println("<button onclick='WriteToCartFile()'>Add to Shopping Cart</button>");
            out.println("<form action='/Assignment2_Test/DeleteCartServlet' method='get' style='margin-bottom:20px;'>"+
                        "<input type='hidden' name='deleteFormId' value='"+p.id+"' />" +"<span style='width:110px;display:inline-block'></span>"+
                        "<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;'>" +
                        "</form>");
                    out.println("<hr width='90%' size='2' align='center'>");          
            out.println("<hr width='90%' size='2' align='center'>");
         } 
                                    }       

                                }

             int total_price=MySqlDataStoreUtilities.priceCalculate();
             System.out.println("Sum is: "+total_price);*/
			 

            
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
					/*
					PLACE ORDER BUTTON, WHEN IT IS CLICKED TOTAL PRICE WILL BE SEND FORWARD TO "PLACE ORDER FROM CART SERVLET"
					*/
					/*"<form action='/login5/PlaceOrderFromCartServlet' method='get'>"+
                    "<input type='hidden' name='totalprice' value='"+totalPrice+"' />" +
                    "<input style='float:center' style='color:Red' type='submit' value='Place Order' class='submit-button' style='width:250px;'/>" +
                    "</form>"+*/
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