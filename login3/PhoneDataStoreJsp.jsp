<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>


	
	
	
	<%
	List<Products> productList;
	String k="Phone";
	String username;
	
		
    HttpSession s=request.getSession();
    username=(String)s.getAttribute("userid");
	
	productList=new ArrayList<Products>();
	
	
	productList= MySqlDataStore.selectPhoneProducts(k);%>
		
		
		
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
					<h3 style='color:blue;float:right;font-style:italic' >Welcome: <%=username%></h3>
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
					<h2>CHOOSE YOUR SMART PHONES TODAY</h2>
					
					<%for(Products prod : productList){%>
					<p><img src=<%=prod.productImage%>/>
					<p Style='Color:Red'>Product ID:</p>
					<p><%=prod.productId%></p>
					<p Style='Color:Red'>Product Name:</p>
					<p><%=prod.productName%></p>
					<p Style='Color:Red'>Product Price:</p>
					<p><%=prod.productPrice%></p>
					<p Style='Color:Red'>Product Manufacturer:</p>
					<p><%=prod.productRetailer%></p>
					
					<form action='AddingIntoCartJsp.jsp' method='get' style='margin-bottom:20px;'>
					<input type='hidden' name='productFormId' value='<%=prod.productId%>' />
					<input type='submit' value='Add to Cart' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>
					
					
					<form action='WriteReviewJsp.jsp' method='get' style='margin-bottom:20px;'>
					<input type='hidden' name='productFormId' value='<%=prod.productId%>' />
					<input type='hidden' name='productFormName' value='<%=prod.productName%>' />
					<input type='hidden' name='productFormImage' value='<%=prod.productImage%>' />
					<input type='hidden' name='productFormRetailer' value='<%=prod.productRetailer%>' />
					<input type='hidden' name='productFormPrice' value='<%=prod.productPrice%>' />
					<input type='submit' value='Write Review' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>
					
					<form action='ReadReviewJsp.jsp' method='get' style='margin-bottom:20px;'>
					<input type='hidden' name='productFormId' value='<%=prod.productId%>' />
					<input type='submit' value='Read Review' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>
					
					<%} %>
					
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
		