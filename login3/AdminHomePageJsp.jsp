<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>


<% 
	 String k=" ";
	 Products p;
	 String username=" ";

	 String firstLetter=" ";
     String productcategory=" ";
     String productretailer=" ";
     String productid=" ";
     String productname=" ";        
     int productprice = 0;
     String prodpricestr=" ";
	 String productformid=" ";
	
		
      HttpSession s=request.getSession(); 
      username=((String)s.getAttribute("userid"));
		
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        
        List<Products>productList=new ArrayList<Products>();                
        HashMap<String,List<Products>> mapInFile = new HashMap<String,List<Products>>();
      
		
		
		mapInFile = MySqlDataStore.adminProductDisplay(); %>
	
			
	<html>
	<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<title>SignIn : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
	</head>
	<body>
	<div id='container'>
	<header><div class='header_logo'><img src='images/best_deals.png'/>
	<h1><a href='HomeJsp.jsp'>BEST <span>DEAL</span></a></h1>
	<h2>new deals everyday</h2><span style='width:110px;display:inline-block'></span></div></header>
	<h5 style='color:blue;float:right' ><a href='SignOutJsp.jsp'>Sign Out</a></h5></br>
	<table><tr><th>Product Id</th><th>Product Name</th><th>Product Retailer</th><th>Product Price</th></tr>

                
             <%   for (Map.Entry<String, List<Products>> hm : mapInFile.entrySet()) 
                    {
                        productList = hm.getValue();

                        for (Products p1 : productList) { %>
						<tr>
                        <td bgcolor='#c98e8e'><%=p1.productId%></td>
                        <td><%=p1.productName%></td>
                        <td bgcolor='#c98e8e'><%=p1.productRetailer%></td>
                        <td><%=p1.productPrice%></td>
						</tr>
						<%}
                    } %>
                  
		
         <td><a href='AddProductByAdminDesignJsp.jsp'><b>Add Product </b></a></td>
		 <td>
		 
		 
         <form method='post' action='UpdateProductDesignByAdminJsp.jsp'><p>Enter the Product ID you like to update</p>
         <input type='text' name='productUpdateId'><input type='submit' name='submit' style='background-color:#c98e8e'></form>
		 </td>
		 <td>
		
        <form method='post' action='DeleteProductByAdminJsp.jsp' style='float:right','font-size:900%><p>Enter the Product ID you like to delete</p>
        <input type='text' name='productDeleteId'><input type='submit' name='submit' style='background-color:#c98e8e'></form>
		<td>		 
		</td>
		</tr>
		</table>
                
