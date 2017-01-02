import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddProductByAdminDesignServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        PrintWriter pw= response.getWriter(); 
        response.setContentType("text/html");
		
		/*
		CALL WILL GO TO AddProductByAdminServlet
		*/
        
		pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Admin Add Products : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head><body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='/login4/HomeServlet'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "<span style='width:110px;display:inline-block'></span>" + "</div>" + "</header>"+
					"<h5 style='color:blue;float:right' ><a href='/login4/SignOutServlet'>Sign Out</a></h5></br>"+
					"<form method='post' action='/login4/AddProductByAdminServlet'><h3>Add Products</h3><table cellpadding='2' cellspacing='1'>"+
					"<tr><td>Category</td><td><select name='productCategory'><option value='TV'>TV</option><option value='Laptop'>Laptop</option>"+
					"<option value='Tablet'>Tablet</option><option value='SmartPhone'>SmartPhone</option></select></td></tr>"+
					"<tr><td>Retailer</td><td><input type='TEXT' size='15' name='productRetailer'></input></td></tr>"+
					"<tr><td>Product Id</td><td><input type='TEXT' size='15' name='productId'></input></td></tr>"+
					"<tr><td>Product Name</td><td><input type='text' size='15' name='productName'/></td></tr>"+
					"<tr><td>Price</td><td><input type='TEXT' size='15' name='productPrice'></input></td></tr>"+
					"<tr><td>Image</td><td><input type='TEXT' size='20' name='productImage' placeholder='images/picture.jpg'></input></td></tr>"+
					"<tr><td colspan='2'><center><input type='submit' value='Add Product' /></center></td></tr>"+
					"</table></form>");
					
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

