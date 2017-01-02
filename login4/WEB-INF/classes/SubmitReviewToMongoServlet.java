import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class SubmitReviewToMongoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		DBCollection myReviews = MongoDBDataStoreUtilities.createMongoDB();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw= response.getWriter();		
		
		try{
			//Get the values from the form
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
			
			
										
			// If database doesn't exists, MongoDB will create it for you
			//DB db = mongo.getDB("bestdealsmongodb");
				
			// If the collection does not exists, MongoDB will create it for you
			//DBCollection myReviews = db.getCollection("myReviews");
			
				
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
									
			//myReviews.insert(info);
				
			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
			PrintWriter out = response.getWriter();
			
				
			pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='#'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "</div>" + "</header>"+
					"<h3>" + "<span style='width:310px;display:inline-block'></span>" + "Your Review Has Been Added" + "</h3>");
					if(category.equals("TV")){
			pw.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/login4/TvDataStoreServlet'>Due to Extra Security Reasons click here to see the changes</a>");  
					}
					else if(category.equals("Laptop")){
			pw.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/login4/LaptopDataStoreServlet'>Due to Extra Security Reasons click here to see the changes</a>");  
					}
					else if(category.equals("Phone")){
			pw.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/login4/PhoneDataStoreServlet'>Due to Extra Security Reasons click here to see the changes</a>");  
					}
					else if(category.equals("Tablet")){
			pw.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/login4/TabletDataStoreServlet'>Due to Extra Security Reasons click here to see the changes</a>");  
					}
					pw.close();
		
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}