<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>



<% HashMap<String, List<Products>> mapInFile;

 List<Products> tvList = new ArrayList<Products>();
 List<Products> laptopList = new ArrayList<Products>();
 List<Products> phoneList = new ArrayList<Products>();
 List<Products> tabletList = new ArrayList<Products>();

 List<Products> productList = new ArrayList<Products>();
 List<Products> productList1 = new ArrayList<Products>();
 List<Products> productList2 = new ArrayList<Products>();
 List<Products> productList3 = new ArrayList<Products>();

 
 String k=" ";

 String productcategory=" ";
 String productretailer=" ";
 String productid=" ";
 String productname=" ";        
 String productprice=" ";
 String productimage=" ";

 Products p;


		
     Map<String, List<Products>> prod=new HashMap<String, List<Products>>();
     response.setContentType("text/html");
     PrintWriter pw= response.getWriter();
     HttpSession s=request.getSession(); 
     String user=((String)s.getAttribute("userid")); 

			
     productcategory = request.getParameter("productCategory");
     productretailer = request.getParameter("productRetailer");
     productid = request.getParameter("productId");
     productname = request.getParameter("productName"); 
     productprice = request.getParameter("productPrice");
	 productimage=request.getParameter("productImage");

        if(productcategory.equals("TV"))
        {
            p = new TV();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
			productList.add(p);
			MySqlDataStore.insertDataIntoDatabase(productid,productname,productretailer,Integer.parseInt(productprice),productimage,"TV");
            
        }


        else if(productcategory.equals("Laptop"))
        {   
   
            p = new Laptop();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
            productList1.add(p);
			MySqlDataStore.insertDataIntoDatabase(productid,productname,productretailer,Integer.parseInt(productprice),productimage,"Laptop");
        }



        else if(productcategory.equals("Tablet"))
        {
            
            p = new Tablet();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
            productList2.add(p);
			MySqlDataStore.insertDataIntoDatabase(productid,productname,productretailer,Integer.parseInt(productprice),productimage,"Tablet");
        }



        else if(productcategory.equals("SmartPhone"))
        {
           
            p = new Phone();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
            productList3.add(p);
			MySqlDataStore.insertDataIntoDatabase(productid,productname,productretailer,Integer.parseInt(productprice),productimage,"Phone");
        } 

                                for (Map.Entry<String, List<Products>> hm : SaxParser.m.entrySet()) 
                                {                   
                                                    
                                                    k = hm.getKey();
                                                 
                                                    if(k.equals("TV")){
                                                    
                                                    tvList = hm.getValue();                                                    
                                                    tvList.addAll(productList);
                                                    
                                                    }
                                                    else if(k.equals("Laptop")){
                                                    
                                                    laptopList = hm.getValue();                                                    
                                                    laptopList.addAll(productList1);
                                                
                                                    }
													
													else if(k.equals("Tablet")){
                                                   
                                                    tabletList = hm.getValue();                                                    
                                                    tabletList.addAll(productList2);  
                                                   
                                                    }

                                                    else if(k.equals("Phone")){
                                                    
                                                    phoneList = hm.getValue();                                                    
                                                    phoneList.addAll(productList3);  
                                                 
                                                    }   

                                            } %>
                                            


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
					<span style='width:310px;display:inline-block'></span><a href='AdminHomePageJsp.jsp'>Due to Extra Security Reasons click here to see the changes</a>
					
				