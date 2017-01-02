<%@ page import="java.io.*,
 java.util.HashMap,
 java.util.Map,
 javax.servlet.*,
 javax.servlet.http.*,
 java.util.*,
 java.util.Arrays,
 java.util.List,
 java.util.Set,
 java.util.Date,
 java.text.DateFormat,
 java.util.*,
 java.util.Random,
 java.text.DateFormat,
java.text.SimpleDateFormat"%>

<%@ page import="WebAssignment3.*"%>


	 <%String productPrice;
	 String firstName;
	 String lastName;
	 String address;
	 String phoneNumber;
	 String creditCard;
	
	 String username;
	 String message;
	
	 Random random;
	 String orderNumber;
	
	

		
	HttpSession s = request.getSession(true);
	username=((String)s.getAttribute("userid"));
	
	productPrice = request.getParameter("productPrice");
	firstName = request.getParameter("firstName");
	lastName = request.getParameter("lastName");
	address = request.getParameter("address");
	phoneNumber = request.getParameter("phoneNumber");
	creditCard = request.getParameter("creditcard");			
			
			
	message = "Your Order Placed Successfully";
			
	random = new Random( System.currentTimeMillis() );
	int rand= 10000 + random.nextInt(20000);
	orderNumber=Integer.toString(rand);			

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	Calendar c = Calendar.getInstance();
	c.setTime(new Date()); 
	c.add(Calendar.DATE, 14);
	String output = sdf.format(c.getTime());

				
	PrintWriter pw = response.getWriter();			
		
	MySqlDataStore.insertHistoryInDatabase(orderNumber,firstName,lastName,productPrice,address,phoneNumber,creditCard);
	MySqlDataStore.clearProductCart();%>
			
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
					<h2>Your Order:</h2>
                
	<HEAD><TITLE><%=message%></TITLE></HEAD>
                <BODY BGCOLOR=\"#FDF5E6\">
                <H1 ALIGN=CENTER><%=message%></H1>
				<H3 ALIGN=CENTER>Your Order Number :<%=orderNumber%></H3>
                <TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR=\"#FFAD00\">
                <TH>Parameter Name<TH>Parameter Value(s)
				
	<TR><TD> First Name </TD>
	<TD><%=request.getParameter("firstName")%></TD></TR>
				
				
	<TR><TD> Last Name </TD>
	<TD><%=request.getParameter("lastName")%></TD></TR>
				
				
	<TR><TD> Phone Number</TD>
	<TD><%=request.getParameter("phoneNumber")%></TD></TR>
				
	<TR><TD> Address</TD>
	<TD><%=request.getParameter("address")%></TD></TR>
				
				
					
	<TR><TD> Delivery Date </TD>
	<TD><%=output%></TD></TR>
	</TABLE>

	</TABLE>
						<FORM ACTION=OrderHistoryJsp.jsp METHOD='get'>
						<BIG><CENTER>
						<p>Thank you for shopping with us...</p>
						<INPUT TYPE='SUBMIT'
						VALUE='View Order History'>
		   
						</CENTER></BIG></FORM>
			</article>
						</section>
						<aside class='sidebar'>
						<ul>
						<li>
						<h4>Products</h4>
						<ul>
						<li><a href='#'>Home Page</a></li>
						<li><a href='PhoneDataStoreJsp.jsp'>Smart Phones</a></li>
						<li><a href='TabletDataStoreServlet'>Tablets</a></li>
						<li><a href='LaptopDataStoreServlet'>Laptops</a></li>
						<li><a href='TvDataStoreServlet'>TV</a></li>
                    <li><a href='TrendingProductServlet'>Trending</a></li>
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
						