/*
USER CLICKS ON ADD TO CART AND ADDING INTO CART SERVLET IS CALLED
*/
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AddingIntoCartServlet extends HttpServlet implements Serializable{

	transient List<String> cartItems = new ArrayList<String>();

	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public HashMap<String, List<Products>> mapInFile;
	public HashMap<String, List<String>> hm;

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
	public String productimage;
	
    public int productprice;
	
    public String prodpricestr;
	
	public String productformid; //WHEN USER CLICKS ON THE PRODUCT AT PHONE,TABLET,LAPTOP,&TV PAGE THEIR CORRESPONDING ID WILL BE CAPTURED 


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        sc=request.getSession().getServletContext();
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
		 /*
		WHEN USER CLICKS ON THE PRODUCT AT PHONE,TABLET,LAPTOP,&TV PAGE THEIR CORRESPONDING ID WILL BE CAPTURED eg:- p001, b001, l001,t001 and so on  
		*/		
        productformid =request.getParameter("productFormId");
		
        prod=new HashMap<String, List<Products>>(); //PRODUCT TYPE HASH MAP IS DEFINED
		
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
 
		/*
		EXTRACT FIRST LETTER OUT OF THE ID CAPTURED IN PREVIOUS STEP eg:- p--p001, b--b001, l--l001,t--t001 and so on  
		*/
		
		firstLetter = String.valueOf(productformid.charAt(0)); 
		System.out.println("The first letter of the extracted product id is: "+firstLetter);
		
		if(firstLetter.equals("l"))
            {
                productcategory="Laptop";
				if(productcategory.equals("Laptop")){
					//laptopList = SaxParser.m.get("Laptop");
					laptopList = MySqlDataStore.selectLaptopProducts(productcategory);
					
					/*
					TRAVERSING THE LIST GOT FROM LAPTOP
					*/

                        for(Products p1 : laptopList)
                            {
								/*
								CHECKING CONDITION THAT WHILE TRAVERSING THE LOOP WHEN IS THE IDs IN PRODUCT LIST IS EQUAL TO ID WE GOT IN productformid
								*/

                                if(p1.getProductId().equals(productformid))
								{
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
										MySqlDataStore.insertProducts("Laptop",productid,productname,productretailer,productprice,productimage);
									  
                                     
                                } 
                                }
								}

                            }

                            else if(firstLetter.equals("t"))
                                {
                                productcategory = "TV";
                                if(productcategory.equals("TV")){
								tvList = MySqlDataStore.selectTvProducts(productcategory);

                                for(Products p1 : tvList)
                                {

                                    if(p1.getProductId().equals(productformid))
                                    {
										
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        MySqlDataStore.insertProducts("TV",productid,productname,productretailer,productprice,productimage);
                                     
                                    } 
                                }
								}

                            }

                            else if(firstLetter.equals("b"))
                                {
                                 productcategory = "Tablet";
                                if(productcategory.equals("Tablet")){
								tabletList = MySqlDataStore.selectTabletProducts(productcategory);

                                for(Products p1 : tabletList)
                                {
									
                                    if(p1.getProductId().equals(productformid))
                                    {
										
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        MySqlDataStore.insertProducts("Tablet",productid,productname,productretailer,productprice,productimage);
                                     
                                    } 
                                }
								}

                            }


                            else if(firstLetter.equals("p"))
                                {
                                 productcategory = "Phone";
                                if(productcategory.equals("Phone")){
								phoneList = MySqlDataStore.selectPhoneProducts(productcategory);

                                for(Products p1 : phoneList)
                                {

                                    if(p1.getProductId().equals(productformid))
                                    {
										
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        MySqlDataStore.insertProducts("Phone",productid,productname,productretailer,productprice,productimage);
                                     
                                    } 
                                }
								}

                            }
					
	/*
	INTENT WILL BE SEND TO PRODUCT CART SERVLET
	*/
	

		pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='#'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "</div>" + "</header>"+
					"<h3>" + "<span style='width:310px;display:inline-block'></span>" + "Your Product Has Been Added" + "</h3>"+
					"<span style='width:310px;display:inline-block'></span>"+
					"<form action='/login4/ProductCartServlet' method='get' style='margin-bottom:20px;'>"+
                    "<input type='hidden' name='productFormId' value='"+productformid+"' />" +
                    "<input type='submit' value='click here to see your cart' class='submit-button' style='width:200px;color:white;background-color:red;border:2px solid #336600;'>" +
                    "</form>");  
					
					pw.close();
            
    }


/*
CALL FROM PHONE,TABLET,LAPTOP,&TV SERVLET WILL COME HERE AND PROCESS REQUEST METHOD IS CALLED
*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}