<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>



 <% List<Products> productList = new ArrayList<Products>();
 List<Products> productList1 = new ArrayList<Products>();
 List<Products> productList2 = new ArrayList<Products>();
 List<Products> productlist3 = new ArrayList<Products>();

 List<Products> tvList = new ArrayList<Products>();
 List<Products> laptopList = new ArrayList<Products>();
 List<Products> phoneList = new ArrayList<Products>();
 List<Products> tabletList = new ArrayList<Products>();
 
 List<String> prodList=new ArrayList<String>();

 String productcategory=" ";
 String productretailer=" ";
 String productid=" ";
 String productname=" ";
 String productcondition=" ";
 int productprice=0;
 String firstLetter = " ";

 Products p;
 Map<String, List<Products>> prod;
 HashMap<String, List<Products>> mapInFile;


String key;
int flag;
 



        prod=new HashMap<String, List<Products>>();
		response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        HttpSession s=request.getSession(); 
        String user=((String)s.getAttribute("userid"));

                      
								
        String produpdateId =request.getParameter("productUpdateId");
        System.out.println("Get The Required ID :"+ produpdateId);
		
		prodList=MySqlDataStore.productUpdateByAdmin(produpdateId);
		
		for(int i=0;i<prodList.size();i++){
						if(i==0){
						productid=prodList.get(i);
						}
						else if(i==1){
						productname=prodList.get(i);
						}
						else if(i==2){
						productretailer=prodList.get(i);
						}
						else if(i==3){
						productprice=Integer.parseInt(prodList.get(i));
						}
		}
		%>

                                


					<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
					<title>Add Products : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
					</head><body>
					<div id='container'>
					<header><div class='header_logo'><img src='images/best_deals.png'/>
					<h1><a href='HomeJsp.jsp'>BEST <span>DEAL</span></a></h1>
					<h2>new deals everyday</h2><span style='width:110px;display:inline-block'></span>
					<h2 style='float:center','font-size:900%'>Welcome!</h2></div></header>  
					<h5 style='color:blue;float:right' ><a href='SignOutJsp.jsp'>Sign Out</a></h5></br>
					<form method='post' action='UpdateProductByAdminJsp.jsp'><h3>Edit Products</h3><table cellpadding='2' cellspacing='1'>     
					<tr><td>Retailer</td><td><input type='TEXT' size='15' value='<%=productretailer%>' name='productRetailer'></input></td></tr>
					<tr><td>Product Id</td><td><input type='TEXT' readonly='true' size='15' value='<%=productid%>' name='productId'></input></td></tr>
					<tr><td>Product Name</td><td><input type='text' size='15' value='<%=productname%>' name='productName'/></td></tr>
					<tr><td>Price</td><td><input type='TEXT' size='15' value='<%=productprice%>' name='productPrice'></input></td></tr>
					<tr><td colspan='2'><center><input type='submit' value='Edit Product' /></center></td></tr>
					</table></form>
		