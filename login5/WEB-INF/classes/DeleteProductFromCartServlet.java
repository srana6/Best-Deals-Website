/*
WHEN USER PRESS DELETE FROM THE CART-- DELETEPRODUCTFROMCARTSERVLET IS CALLED
*/
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DeleteProductFromCartServlet extends HttpServlet implements Serializable{
	
	transient List<String> delCartItems = new ArrayList<String>();

	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public HashMap<String, List<Products>> map;
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
	public String productformid;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        sc=request.getSession().getServletContext();
		PrintWriter pw= response.getWriter();
		response.setContentType("text/html");
        
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
        prod=new HashMap<String, List<Products>>(); //HASHMAP TYPE PRODUCTS IS DEFINED
		
        /*
		EXTRACT THE ID OF THE PRODUCT TO BE DELETED
		*/
        productformid =request.getParameter("deleteFormId");
		System.out.println("The id of the product to be deleted is: "+productformid);
			
		MySqlDataStore.deleteProduct(productformid);
								
			
	//response.sendRedirect("/login5/ActualLogicDeleteFromCartServlet");
	
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
					"<h3>" + "<span style='width:310px;display:inline-block'></span>" + "Your Cart Has Been Updated" + "</h3>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login5/ProductCartServlet'>Due to Extra Security Reasons click here to see the changes</a>");  
					
					pw.close();

           
            
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