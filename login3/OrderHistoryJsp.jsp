<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>

  
<% String username;
  

	HttpSession s=request.getSession(); 
    username=((String)s.getAttribute("userid"));
		
	HashMap<String,String> hashmap = new HashMap<String,String>();
              
	PrintWriter pw = response.getWriter();     
    
	hashmap = MySqlDataStore.selectOrderHistory(); %>
		
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
					<h2>Order History</h2>
					
					<%for (Map.Entry<String, String> hm : hashmap.entrySet()) 
					{
           
						String orderId = hm.getKey();
						String price = hm.getValue();%>
						
						
				<TABLE BORDER=1 ALIGN=CENTER>
									<TR BGCOLOR='#FFAD00'>
									<TH>Details<TH>Values
						
				<TR><TD>ID</TD>
				<TD><%=orderId%></TD></TR>
				
				
				<TR><TD> Price</TD>
				<TD><%=price%></TD></TR>
				</TABLE>
					<%}%>
					
					<form method='post' action='CancelOrderJsp.jsp'><p>Enter the Order ID which you want to Cancel</p>
					<input type='text' name='productUpdateId'><input type='submit' style='width:250px;color:white;background-color:red;border:2px solid #336600' name='Cancel'></form>

					<form method='post' action='UpdateOrderJsp.jsp'><p>Enter the Order ID which you want to Update</p>
					<input type='text' name='productUpdateId'><input type='submit' style='width:250px;color:white;background-color:red;border:2px solid #336600' name='submit'></form>
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
