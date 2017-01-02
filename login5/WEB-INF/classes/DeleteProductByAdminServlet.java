import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DeleteProductByAdminServlet extends HttpServlet implements Serializable{
	
	public ServletContext sc;
	
	transient List<Products> productList = new ArrayList<Products>();
	transient List<Products> productList1 = new ArrayList<Products>();
	transient List<Products> productList2 = new ArrayList<Products>();
	transient List<Products> productlist3 = new ArrayList<Products>();

	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public String productcategory;
	public String productretailer;
	public String productid;
	public String productname;
	public String productprice;

	public String firstletter;
	public Map<String, List<Products>> prod;
	public HashMap<String, List<Products>> map;

	public String k;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
		sc=request.getSession().getServletContext();
		response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        
        HttpSession session=request.getSession(); 
        String user=((String)session.getAttribute("userid")); 

		/*
		WHEN USER CLICKS ON THE DELETE FROM THE CART THE CORRESPONDING ID WILL BE CAPTURED eg:- p001, b001, l001,t001 and so on  
		*/
		productid = request.getParameter("productDeleteId");
		System.out.println("The product id to be deleted is: "+productid);
		
		MySqlDataStore.deleteProductByAdmin(productid);
		
		

	pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + "<h1><a href='/login5/LoginServlet'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>"+ 
					"</div>" + 
					"</header>"+
					"<h4>" + "<span style='width:310px;display:inline-block'></span>" + "Your Product Has Been Added" + "</h4>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login5/AdminHomePageServlet'>Due to extra security Please login again to see the changes</a>"+
					"</body></html>");
        pw.close(); 
		
		
	}
	
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}