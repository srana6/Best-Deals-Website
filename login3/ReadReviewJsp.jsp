<%@ page import=" java.io.*,
 javax.servlet.ServletException,
 javax.servlet.annotation.WebServlet,
 javax.servlet.http.HttpServlet,
 javax.servlet.http.HttpServletRequest,
 javax.servlet.http.HttpServletResponse,
 javax.servlet.*,
 javax.servlet.http.*,
 com.mongodb.MongoClient,
 com.mongodb.MongoException,
 com.mongodb.WriteConcern,
 com.mongodb.DB,
 com.mongodb.DBCollection,
 com.mongodb.BasicDBObject,
 com.mongodb.DBObject,
 com.mongodb.DBCursor,
 com.mongodb.ServerAddress,
 java.util.Arrays,
 java.util.List,
 java.util.Set,
 java.util.Date"%>
 
 <%@ page import="WebAssignment3.*"%>


	
	<% String productName1;
	 String userName;
	 String category;
	 String price;
	 String retailer;
	 String retailerzip;
	 String retailercity;
	 String retailerstate;
	 String sale;
	 String mfname;
	 String userid;
	 String userage;
	 String usergender;
	 String reviewRating;
	 String reviewDate;
	 String reviewText;
	 String username;
	
	MongoClient mongo;
	
	DBCollection myReviews = MongoDBDataStoreUtilities.createMongoDB();
	
	
	try{
			
	HttpSession s=request.getSession();
	username=(String)s.getAttribute("userid");

			
	String productID= request.getParameter("productFormId");
	System.out.println("ID recieved: "+productID);
			
		
			
	DBCursor cursor1 = MongoDBDataStoreUtilities.fetchReviewMongoDB(productID);

			
	System.out.println("Cursor value is: "+cursor1);
	String productName=request.getParameter("productFormName");
	PrintWriter pw = response.getWriter();
			
	System.out.println("Cursor value is: "+cursor1.count()); %>
						
			
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
			
			<%if(cursor1.count() == 0){
			pw.print("There are no reviews for this product.");
		}else{
				int counter=0;
				
				while (cursor1.count()>counter) {
					counter++;
					
					BasicDBObject obj = (BasicDBObject) cursor1.next();	%>
					
				<TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR='#FFAD00'>
                <TH>Product Review<TH>Value(s)
				
				<TR><TD> Product Name </TD>
				<TD><%=obj.getString("productName")%></TD></TR>

				<TR><TD> Product ID </TD>
				<TD><%=obj.getString("productID")%></TD></TR>
				
				<TR><TD> Category </TD>
				<TD><%=obj.getString("productCategory")%></TD></TR>

				<TR><TD> Price </TD>
				<TD><%=obj.getString("productPrice")%></TD></TR>

				<TR><TD> Retailer </TD>
				<TD><%=obj.getString("productRetailer")%></TD></TR>

				<TR><TD> Retailer Zip </TD>
				<TD><%=obj.getString("retailerzip")%></TD></TR>
				
				<TR><TD> Retailer City </TD>
				<TD><%=obj.getString("retailercity")%></TD></TR>

				<TR><TD> Retailer State </TD>
				<TD><%=obj.getString("retailerstate")%></TD></TR>

				<TR><TD> Sale </TD>
				<TD><%=obj.getString("sale")%></TD></TR>
				
				<TR><TD> Manufacturer Name </TD>
				<TD><%=obj.getString("mfname")%></TD></TR>

				<TR><TD> User Age </TD>
				<TD><%=obj.getString("userage")%></TD></TR>

				<TR><TD> User Gender </TD>
				<TD><%=obj.getString("usergender")%></TD></TR>

				<TR><TD> User Name </TD>
				<TD><%=obj.getString("userid")%></TD></TR>

				<TR><TD> Review Rating </TD>
				<TD><%=obj.getString("reviewRating").toString()%></TD></TR>

				<TR><TD> Review Date </TD>
				<TD><%=obj.getString("reviewDate")%></TD></TR>

				<TR><TD> Review Text </TD>
				<TD><%=obj.getString("reviewText")%></TD></TR>

				</TABLE>

			</TABLE>
           <FORM ACTION='PhoneDataStoreJsp.jsp' METHOD='get'>
           <BIG><CENTER>
		   <p>Thank you for shopping with us...</p>
           <INPUT TYPE='SUBMIT' VALUE='Back'>
		   
           </CENTER></BIG></FORM>		
					
					
					
				<%}
			}%>
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
			
		<%}	
		catch (MongoException e) {
				e.printStackTrace();
		}  %>

	