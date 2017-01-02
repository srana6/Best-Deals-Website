import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AdminHomePageServlet extends HttpServlet {

List<Products> productList = new ArrayList<Products>();

 String key;
 int flag;
 public ServletContext sc;

 


protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        sc=request.getSession().getServletContext();
			PrintWriter pw= response.getWriter(); 
			response.setContentType("text/html");
			
			HashMap<String,List<Products>> mapInFile = new HashMap<String,List<Products>>();
		
			mapInFile = MySqlDataStore.adminProductDisplay();
			
			pw.println("<html>"+
						"<head>"+
						"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
						"<title>SignIn : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
						"</head>"+
						"<body>"+
						"<div id='container'>"+
						"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
						"<h1><a href='/login5/HomeServlet'>BEST <span>DEAL</span></a></h1>"+
						"<h2>new deals everyday</h2>" + "<span style='width:110px;display:inline-block'></span>"+ "</div>" + "</header>"+
						"<h5 style='color:blue;float:right' ><a href='/login5/SignOutServlet'>Sign Out</a></h5></br>"+
						"<table>"+"<tr>"+"<th>Product Id</th>"+"<th>Product Name</th>"+"<th>Product Retailer</th>"+"<th>Product Price</th>"+"</tr>");

                
                for (Map.Entry<String, List<Products>> hm : mapInFile.entrySet()) //TRAVERSE THE FILE USING HASH MAP
                    {
                        productList = hm.getValue();

                        for (Products p : productList) {
						pw.println("<tr>");
                        pw.println("<td bgcolor='#c98e8e'>"+p.productId+"</td>");
                        pw.println("<td>"+p.productName+"</td>");
                        pw.println("<td bgcolor='#c98e8e'>"+p.productRetailer+"</td>");
                        pw.println("<td>"+p.productPrice+"</td>");
						pw.println("</tr>");
						}
                    }
                  
		
         pw.println("<td>"+"<a href='/login5/AddProductByAdminDesignServlet'><b>Add Product </b></a>"+"</td>"); //LOAD ADD PRODUCTS BY ADMIN DESIGN SERVLET
		 pw.println("<td>");
		 
		 /*
		 USER ENTER THE ID OF PRODUCT TO BE UPDATED AND UPDATE PRODUCT DESIGN BY ADMIN SERVLET WILL OPEN
		 */
         pw.println("<form method='post' action='/login5/UpdateProductDesignByAdminServlet'>"+"<p>Enter the Product IDyou like to update</p>");
         pw.println("<input type='text' name='productUpdateId'>"+"<input type='submit' name='submit' style='background-color:#c98e8e'>"+"</form>");
		 pw.println("</td>");
		 pw.println("<td>");
		 /*
		 USER ENTER THE ID OF PRODUCT TO BE DELETED AND DELETE PRODUCT DESIGN BY ADMIN SERVLET WILL OPEN
		 */
         pw.println("<form method='post' action='/login5/DeleteProductByAdminServlet' style='float:right','font-size:900%>"+"<p>Enter the Product ID you like to delete</p>");
         pw.println("<input type='text' name='productDeleteId'>"+"<input type='submit' name='submit' style='background-color:#c98e8e'>"+"</form>");
		//pw.println("<td>"+"<a href='/login5/DataAnalyticsDesignServlet'><b>Data Analytics</b></a>"+"</td>"); //LOAD ADD PRODUCTS BY ADMIN DESIGN SERVLET
		 pw.println("<td>");		 
		 pw.println("</td>");
		 pw.println("</tr>");
		 pw.println("</table>");
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