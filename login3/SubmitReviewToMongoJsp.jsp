<%@ page import=" java.io.*,
 javax.servlet.ServletException,
 javax.servlet.annotation.WebServlet,
 javax.servlet.http.HttpServlet,
 javax.servlet.http.HttpServletRequest,
 javax.servlet.http.HttpServletResponse,
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


	<%DBCollection myReviews = MongoDBDataStoreUtilities.createMongoDB();
	
	PrintWriter pw= response.getWriter();		
		
	try{
			
			String productName = request.getParameter("productName");
			String productID = request.getParameter("productID");
			String category = request.getParameter("productCategory");
			String price = request.getParameter("productPrice").trim();
			int price1= Integer.parseInt(price);
			String retailer = request.getParameter("productRetailer");
			Integer retailerzip = Integer.parseInt(request.getParameter("retailerzip"));
			String retailercity = request.getParameter("retailercity");
			String retailerstate = request.getParameter("retailerstate");
			String sale = request.getParameter("sale");
			String mfname = request.getParameter("mfname");
			String rebate = request.getParameter("rebate");
			String user = request.getParameter("userid");
			String userage = request.getParameter("userAge");
			String usergender = request.getParameter("userGender");
			String userOccupation = request.getParameter("userOccupation");
			int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));	
			String reviewDate = request.getParameter("reviewDate");
			String reviewText = request.getParameter("reviewText");
			
			
	
			
				
			BasicDBObject info = new BasicDBObject("title", "myReviews").
				append("productName", productName).
				append("productID", productID).
				append("productCategory", category).
				append("productPrice", price1).
				append("productRetailer", retailer).
				append("retailerzip", retailerzip).
				append("retailercity", retailercity).
				append("retailerstate", retailerstate).
				append("sale", sale).
				append("mfname", mfname).
				append("rebate",rebate).
				append("userid", user).
				append("userage", userage).
				append("usergender", usergender).
				append("userOccupation",userOccupation).
				append("reviewRating", reviewRating).
				append("reviewDate", reviewDate).
				append("reviewText", reviewText);
				
			Object o = MongoDBDataStoreUtilities.insertReviewInMongoDB(info);
									
			
				
			System.out.println("Document inserted successfully");
			
			
			 %>
			
				
					<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
					<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />
					</head>
					<body>
					<div id='container'>
					<header>" + "<div class='header_logo'><img src='images/best_deals.png'/>
					<h1><a href='#'>BEST <span>DEAL</span></a></h1>
					<h2>new deals everyday</h2></div></header>
					<h3><span style='width:310px;display:inline-block'></span>Your Review Has Been Added</h3>
					<%if(category.equals("TV")){%>
			<span style='width:310px;display:inline-block'></span>"+"<a href='TvDataStoreJsp.jsp'>Due to Extra Security Reasons click here to see the changes</a> 
					<%}
					else if(category.equals("Laptop")){%>
			<span style='width:310px;display:inline-block'></span>"+"<a href='LaptopDataStoreJsp.jsp'>Due to Extra Security Reasons click here to see the changes</a> 
					<%}
					else if(category.equals("Phone")){%>
			<span style='width:310px;display:inline-block'></span>"+"<a href='PhoneDataStoreJsp.jsp'>Due to Extra Security Reasons click here to see the changes</a> 
					<%}
					else if(category.equals("Tablet")){%>
			<span style='width:310px;display:inline-block'></span>"+"<a href='TabletDataStoreJsp.jsp'>Due to Extra Security Reasons click here to see the changes</a>
					<%}
					
		
		} catch (MongoException e) {
			e.printStackTrace();
		}%>
	
	