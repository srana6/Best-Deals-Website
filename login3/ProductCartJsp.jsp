<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>



	<%List<String> cartList = new ArrayList<String>();
	List<String> cartList1 = new ArrayList<String>();

	 HashMap<String, List<String>> map;
	 HashMap<String, List<Products>> map1;
	
	List<Products> tvList = new ArrayList<Products>();
	List<Products> laptopList = new ArrayList<Products>();
	List<Products> phoneList = new ArrayList<Products>();
	List<Products> tabletList = new ArrayList<Products>();

	 Map<String, List<Products>> prod1 ;
	 String k;
	 Products p;
	 String username;

	 ServletContext sc;
	 String firstLetter;
     String productcategory;
     String productretailer;
     String productid;
     String productname;        
     int productprice = 0;
     String prodpricestr;
	 String productformid;
	
	 //int totalPrice=0;
	 int val;

   
		
    HttpSession s=request.getSession(); 
    username=((String)s.getAttribute("userid"));
		
    response.setContentType("text/html");
    PrintWriter pw= response.getWriter();
		
	productformid =request.getParameter("productFormId");

	map1=new HashMap<String,List<Products>>();
	map1 = MySqlDataStore.selectProducts(productformid);%>
		
		<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />
					<title>Welcome to Best Deals</title>
					<link rel='stylesheet' href='styles.css' type='text/css' /> 
					</head>
		
		<body>
					<div id='container'>
					<header>
					<div id=imageLogo>
					<img src='images/best_deals.png'/>
					<h1><a href='/'>Best<span>Deals</span></a></h1>
					<h2>new deals everyday</h2>
					<h5 style='color:blue;float:right' ><a href='SignOutJsp.jsp'>Sign Out</a></h5></br>
					<h3 style='color:blue;float:right;font-style:italic' >Welcome:<%=username%></h3>
		<%int productsInCart=MySqlDataStore.countProductsInCart();%>
		<span style='width:110px;display:inline-block;color:red'>CART:<%=productsInCart%></span>
					</div>
					</header>
					<div class='menu-wrap'>
					<nav class='menu'>
					<ul class='clearfix'>
					<li><a href='#'>Home</a></li>
					<li>
					<a href='#'>Products <span class='arrow'>&#9660;</span></a>
					<ul class='sub-menu'>
                    <li><a href='PhoneDataStoreJsp.jsp'>Smart Phones</a></li>
                    <li><a href='TabletDataStoreJsp.jsp'>Tablets</a></li>
                    <li><a href='LaptopDataStoreJsp.jsp'>Laptops</a></li>
                    <li><a href='TvDataStoreJsp.jsp'>TV</a></li>
					</ul>
					</li>
					<li class='current-item'><a href='ContactJsp.jsp'>ContactUS</a></li>
					<li>
					<div class='gadget'>
					<form method='get' id='search' action='#'>
					<span>
					<input type='text' value='Search...' id='searchbox' />
					<input type='button' value='Go' id='searchbutton' class='btn'  />
					</span>
					</form>
					</div>
					</li>
					</ul>
					</nav>
					<img class=header-image src='images/combine_images.png' alt='phones' />
					</div>
					<div id='body'>
					<section id='content'>
					<article>
					<h2>CART</h2>
					
					
					<%for (Map.Entry<String, List<Products>> hm : map1.entrySet()) 
                                {                   
                                    k = hm.getKey();
                                    
                                        laptopList = hm.getValue();
                                        
                                        for(Products prod : laptopList){%>
											<p><img src=<%=prod.productImage%>/>
											<p Style='Color:Red'>Product ID:</p>
											<p><%=prod.productId%></p>
											<p Style='Color:Red'>Product Name:</p>
											<p><%=prod.productName%></p>
											<p Style='Color:Red'>Product Price:</p>
											<p><%=prod.productPrice%></p>
											<p Style='Color:Red'>Product Manufacturer:</p>
											<p><%=prod.productRetailer%></p>
											<form action='DeleteProductFromCartJsp.jsp' method='get' style='margin-bottom:20px;'>
											<input type='hidden' name='deleteFormId' value='<%=prod.productId%>' /><span style='width:110px;display:inline-block'></span>
											<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;color:white;background-color:red;border:2px solid #336600'>
											</form>
											<hr width='90%' size='2' align='center'>
                                    <%} 
									
											int totalPrice=MySqlDataStore.priceCalculate();%>
											
											<form action='PlaceOrderFromCartJsp.jsp' method='get'>
											<input type='hidden' name='totalprice' value='<%=totalPrice%>' />
											<input style='float:center;width:250px;color:white;background-color:red;border:2px solid #336600' type='submit' value='Place Order' class='submit-button'/>
											</form>
											<hr width='90%' size='2' align='center'>

            </article>
					</section>
					<aside class='sidebar'>
					<ul>
					<li>
                    <h4>Products</h4>
                    <ul>
                    <li><a href='#'>Home Page</a></li>
                    <li><a href='PhoneDataStoreJsp.jsp'>Smart Phones</a></li>
                    <li><a href='TabletDataStoreJsp.jsp'>Tablets</a></li>
                    <li><a href='LaptopDataStoreJsp.jsp'>Laptops</a></li>
                    <li><a href='TvDataStoreJsp.jsp'>TV</a></li>
					
                    <li><a href='TrendingProductJsp.jsp'>Trending</a></li>
                    </ul>
					</li>
					<li>
                    <h4>About us</h4>
                    <ul>
                    <li class='text'>
                    <p style='margin: 0;'>Welcome to Best Deals, Where you will get new deals everyday</p>
                    </li>
                    </ul>
					</li>
					</ul>
					</aside>
					
					<div class='clear'></div>
					</div>
					</div>
					</body>
					</html>
		<%}%>
