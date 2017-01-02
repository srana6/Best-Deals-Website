<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>



	<%List<String> cartItems = new ArrayList<String>();

	List<Products> tvList = new ArrayList<Products>();
	List<Products> laptopList = new ArrayList<Products>();
	List<Products> phoneList = new ArrayList<Products>();
	List<Products> tabletList = new ArrayList<Products>();

	HashMap<String, List<Products>> mapInFile;
	HashMap<String, List<String>> hm;

	 Map<String, List<Products>> prod ;
	 String k;
	 Products p;
	 String username;

	 ServletContext sc;
	 String firstLetter;
     String productcategory;
     String productretailer;
     String productid;
     String productname; 
	 String productimage;
	
     int productprice;
	
     String prodpricestr;
	
	 String productformid; 

     HttpSession s=request.getSession(); 
     username=((String)s.getAttribute("userid"));
			
     productformid =request.getParameter("productFormId");
	 
	 System.out.println("The product form id is:::"+productformid);
		
     prod=new HashMap<String, List<Products>>(); 
		
     response.setContentType("text/html");
     PrintWriter pw= response.getWriter();
 
	 firstLetter = String.valueOf(productformid.charAt(0)); 
	 System.out.println("The first letter of the extracted product id is: "+firstLetter);
		
		if(firstLetter.equals("l"))
            {
                productcategory="Laptop";
				if(productcategory.equals("Laptop")){
					//laptopList = SaxParser.m.get("Laptop");
					laptopList = MySqlDataStore.selectLaptopProducts(productcategory);
					
					System.out.println("Yes we get laptop list here: "+laptopList);
				

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

                            }%>
					


		<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
					<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />
					</head>
					<body>
					<div id='container'>
					<header><div class='header_logo'><img src='images/best_deals.png'/> 
					<h1><a href='#'>BEST <span>DEAL</span></a></h1>
					<h2>new deals everyday</h2></div></header>
					<h3><span style='width:310px;display:inline-block'></span>Your Product Has Been Added</h3>
					<span style='width:310px;display:inline-block'></span>
					<form action='ProductCartJsp.jsp' method='get' style='margin-bottom:20px;'>
                    <input type='hidden' name='productFormId' value=<%=productformid%>' />
                    <input type='submit' value='click here to see your cart' class='submit-button' style='width:200px;color:white;background-color:red;border:2px solid #336600;'>
                    </form>
					
            