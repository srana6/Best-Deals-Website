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
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.AggregationOutput;
import com.mongodb.client.AggregateIterable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class DataAnalysisServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	String productName;
	public int productPrice;
	public String category;
	public String retailerName;
	public String retailerState;
	public int retailerZipcode;
		
	public String retailerCity;
	public String manName;
	public String userId;
	public String userAge;
	public String userGender;
	public String userOccupation;
	public int reviewRating;
	public String compareRating;
	public String reviewText;
	public String comparePrice;
	public String returnValueDropdown;
	public String returnData;
	public String groupByDropdown;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
		
		DB db = mongo.getDB("bestdealsmongodb");
		
		// If the collection does not exists, MongoDB will create it for you
		DBCollection myReviews = db.getCollection("myReviews");
		
		PrintWriter out= response.getWriter();
		BasicDBObject query = new BasicDBObject();
				
		try {
			
			// Get the form data
			productName = request.getParameter("productName");
			productPrice = Integer.parseInt(request.getParameter("productPrice"));
			category= request.getParameter("productCategory");
			retailerName= request.getParameter("retailerName");
			retailerState= request.getParameter("retailerState");
			retailerZipcode = Integer.parseInt(request.getParameter("retailerZipcode"));
		
			retailerCity = request.getParameter("retailerCity");
			manName = request.getParameter("manufacturerName");
			userId = request.getParameter("userID");
			userAge = request.getParameter("userAge");
			userGender = request.getParameter("userGender");
			userOccupation= request.getParameter("userOccupation");
			reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
			compareRating = request.getParameter("compareRating");
			reviewText=request.getParameter("reviewText").trim();
			comparePrice = request.getParameter("comparePrice");
			returnValueDropdown = request.getParameter("returnValue");
			returnData= request.getParameter("returnData");
			groupByDropdown = request.getParameter("groupByDropdown");
			
			//Boolean flags to check the filter settings
			boolean noFilter = false;
			boolean filterByProduct = false;
			boolean filterByPrice = false;
			boolean filterByCategory=false;
			boolean filterByRetailerName= false;
			boolean filterByRetailerState= false;
			boolean filterByUserGender= false;
			boolean filterByZip = false;
			boolean filterByUserId=false;
			boolean filterByUserAge= false;
			boolean filterByUserOccupation=false;
			boolean filterByCity = false;
			boolean filterByManName=false;
			boolean filterByRating = false;
			String groupByField="";
			boolean groupBy = false;
			boolean groupByCity = false;
			boolean groupByProduct = false;
			boolean groupByRetailerName = false;
			boolean groupByRetailerZip= false;
			
			boolean countOnly = false;
						
			//Get the filters selected
			//Filter - Simple Search
			String[] filters = request.getParameterValues("queryCheckBox");
			//Filters - Group By
			String[] extraSettings = request.getParameterValues("extraSettings");
			
			DBCursor dbCursor = null;
			AggregationOutput aggregateData = null;
			
			//Check for extra settings(Grouping Settings)
			if(extraSettings != null){				
				//User has selected extra settings
				groupBy = true;
				
				for(int x = 0; x <extraSettings.length; x++){
					switch (extraSettings[x]){						
						case "COUNT_ONLY":
							//Not implemented functionality to return count only
							countOnly = true;				
							break;
						case "GROUP_BY":	
							//Can add more grouping conditions here
							if(groupByDropdown.equals("GROUP_BY_CITY")){
								groupByCity = true;
								groupByField = "retailercity";
								
							}else if(groupByDropdown.equals("GROUP_BY_PRODUCT")){
								groupByProduct = true;
							} 
							else if(groupByDropdown.equals("GROUP_BY_RETAILER_NAME")){
								groupByRetailerName = true;
							}
							else if(groupByDropdown.equals("GROUP_BY_RETAILER_ZIP")){
								groupByRetailerZip = true;
								groupByField = "retailerzip";
							}
							break;
					}		
				}				
			}			
			
			//Check the main filters only if the 'groupBy' option is not selected
			if(filters != null){
				for (int i = 0; i < filters.length; i++) {
					//Check what all filters are ON
					//Build the query accordingly
					switch (filters[i]){										
						case "productName":							
							filterByProduct = true;
							if(!productName.equals("ALL_PRODUCTS")){
								query.put("productName", productName);
							}						
							break;
												
						case "productPrice":
							filterByPrice = true;
							
								if (comparePrice.equals("EQUALS_TO")) {
									query.put("productPrice", productPrice);
								}else if(comparePrice.equals("GREATER_THAN")){
									query.put("productPrice", new BasicDBObject("$gt", productPrice));
								}else if(comparePrice.equals("LESS_THAN")){
									query.put("productPrice", new BasicDBObject("$lt", productPrice));
								}
							
							break;
						case "productCategory":
							filterByCategory = true;
							query.put("productCategory", category);
							break;		
						
						case "retailerName":
							filterByZip = true;
							query.put("productRetailer", retailerName);
							break;
						
						case "retailerState":
							filterByRetailerState = true;
							query.put("retailerstate", retailerState);
							break;
						
						case "retailerZipcode":
							filterByZip = true;
							query.put("retailerzip", retailerZipcode);
							break;
								
						case "manufacturerName":
							filterByManName = true;
							query.put("mfname", manName);
							break;
							
						case "userID":
							filterByUserId = true;
							query.put("userid", userId);
							break;
						
						case "userAge":
							filterByUserAge = true;
							query.put("userage", userAge);
							break;

						case "userGender":
							filterByUserGender = true;
							query.put("usergender", userGender);
							break;
						
						case "userOccupation":
							filterByUserOccupation = true;
							query.put("userOccupation", userOccupation);
							break;
							
						case "retailerCity": 
							filterByCity = true;
							if(!retailerCity.equals("All") && !groupByCity){
								query.put("retailercity", retailerCity);
							}							
							break;
						
						case "reviewText": 
							filterByCity = true;
							
								query.put("reviewText", new BasicDBObject("$regex", String.format(".*((?i)%s).*", reviewText)) );
								
														
							break;	
							
						case "reviewRating":	
							filterByRating = true;
							if (compareRating.equals("EQUALS_TO")) {
								query.put("reviewRating", reviewRating);
							}else{
								query.put("reviewRating", new BasicDBObject("$gt", reviewRating));
							}
							break;
						
						
						default:
							//Show all the reviews if nothing is selected
							noFilter = true;
							break;						
					}				
				}
			}else{
				//Show all the reviews if nothing is selected
				noFilter = true;
			}
			
						
			//Construct the top of the page
			constructPageTop(output);
						
			//Run the query 
			if(groupBy == true){		
				//Run the query using aggregate function
				DBObject match = null;
				DBObject groupFields = null;
				DBObject group = null;
				DBObject projectFields = null;
				DBObject project = null;
				DBObject sort=null;
				AggregationOutput aggregate = null;
				
				if(returnData.equals("TOP_5_LIKED_PRODUCT") && groupByCity== true)
				{
										
				
					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$retailercity");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productName", new BasicDBObject("$push", "$productName"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					group = new BasicDBObject("$group", groupFields);
					
					DBObject sortFields = new BasicDBObject("reviewRating", -1);
					DBObject sort1 = new BasicDBObject("$sort", sortFields );
				
					
					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("Retailer City", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					project = new BasicDBObject("$project", projectFields);
					
					
					aggregate = myReviews.aggregate(sort1,group,project);
					
					constructGroupByCityTopFiveContent(aggregate, output, countOnly);
				
				}
				if(returnData.equals("MEDIAN") && groupByCity== true)
				{
					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$retailercity");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productName", new BasicDBObject("$push", "$productName"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					group = new BasicDBObject("$group", groupFields);
					
					DBObject sortFields = new BasicDBObject("price", 1);
					DBObject sort1 = new BasicDBObject("$sort", sortFields );
				
					
					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("Retailer City", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Price", "$price");
					projectFields.put("Product", "$productName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					project = new BasicDBObject("$project", projectFields);
				
					aggregate = myReviews.aggregate(sort1,group,project);
					//output.println(aggregate.results());
					constructGroupByCityMedianContent(aggregate, output, countOnly);
				
				}
				
				
				
				
				if (returnData.equals("HIGHEST_PRICE_PRODUCT") && (groupByCity== true || groupByRetailerZip==true)){
				
					
					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$"+groupByField);
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("price1", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("productName", new BasicDBObject("$push", "$productName"));
					group = new BasicDBObject("$group", groupFields);
					
					projectFields = new BasicDBObject("_id", 0);
					projectFields.put(groupByField, "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productName");
					projectFields.put("Price","$price1");

					project = new BasicDBObject("$project", projectFields);
					aggregate = myReviews.aggregate(group,project);
					
					
					
					constructGroupByCityHighestContent(aggregate, output, countOnly,groupByField);
				}
				
				if(groupByCity==true && returnData.equals("ALL")){
					
					if(filterByRating== true)
					{
						if(compareRating.equals("EQUALS_TO"))
						match = new BasicDBObject("$match", new BasicDBObject("reviewRating",new BasicDBObject("$eq", reviewRating)));
						else
						match = new BasicDBObject("$match", new BasicDBObject("reviewRating",new BasicDBObject("$gt", reviewRating)));
					}
					
					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$retailercity");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productName", new BasicDBObject("$push", "$productName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					
					
					group = new BasicDBObject("$group", groupFields);
					
	
					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("City", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					
					project = new BasicDBObject("$project", projectFields);
					
					
				
					if(filterByRating== true)
					aggregate = myReviews.aggregate(match,group,project);
					else
					aggregate = myReviews.aggregate(group,project);
											
					//Construct the page content
					constructGroupByCityContent(aggregate, output, countOnly);
					
				}else if(groupByProduct){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$productName");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("Product", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
										
					project = new BasicDBObject("$project", projectFields);
					
					aggregate = myReviews.aggregate(group, project);				
							
					//Construct the page content
					constructGroupByProductContent(aggregate, output, countOnly);
					
				}
				else if(groupByRetailerName){
					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$productRetailer");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productName", new BasicDBObject("$push", "$productName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("Retailer", "$_id");
					//projectFields.put("Product", "$productName");					
					projectFields.put("Product", "$productName");
					projectFields.put("Review Count", "$count");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
				
										
					project = new BasicDBObject("$project", projectFields);
					
					aggregate = myReviews.aggregate(group, project);				
							
					//Construct the page content
					constructGroupByRetailerNameContent(aggregate, output, countOnly);
					}
			}else{
				//Check the return value selected
				int returnLimit = 0;
				
				//Create sort variable
				DBObject sort = new BasicDBObject();
												
				if (returnValueDropdown.equals("TOP_5")){
					//Top 5 - Sorted by review rating
					returnLimit = 5;
					sort.put("reviewRating",-1);
					//+sort.put("_id","retailercity");
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("TOP_10")){
					//Top 10 - Sorted by review rating
					returnLimit = 10;
					sort.put("reviewRating",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("LATEST_5")){
					//Latest 5 - Sort by date
					returnLimit = 5;
					sort.put("reviewDate",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("LATEST_10")){
					//Latest 10 - Sort by date
					returnLimit = 10;
					sort.put("reviewDate",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else{
					//Run the simple search query(default result)
					dbCursor = myReviews.find(query);
					
				}		
				
				//Construct the page content
				constructDefaultContent(dbCursor, output, countOnly);
			}			
			
			//Construct the bottom of the page
			constructPageBottom(output);
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
	
	public void constructPageTop(PrintWriter output){
		output.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />"+
					"<title>Welcome to Best Deals</title>"+
					"<link rel='stylesheet' href='styles.css' type='text/css' />"+ 
					"</head>");
		
		output.println("<body>"+
					"<div id='container'>"+
					"<header>"+
					"<div id=imageLogo>"+
					"<img src='images/best_deals.png'/>"+
					"<h1><a href='/'>Best<span>Deals</span></a></h1>"+
					"<h2>new deals everyday</h2>"+
					"<h5 style='color:blue;float:right' ><a href='/login2/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='#'>Home</a></li>"+
					"<li>"+
					/*"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
                    "<li><a href='/login2/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login2/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login2/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login2/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+*/
					"<li class='current-item'><a href='/login2/ContactServlet'>ContactUS</a></li>"+
					//"<li><a href='#'>Welcome</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					"<form method='get' id='search' action='#'>"+
					"<span>"+
					//"<input type='text' value='Search...' id='searchbox' />"+
					//"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
					"</span>"+
					"</form>"+
					"</div>"+
					"</li>"+
					"</ul>"+
					"</nav>"+
					"<img class=header-image src='images/combine_images.png' alt='phones' />"+
					"</div>"+
					"<div id='body'>"+
					"<section id='content'>"+
					"<article>"+
					"<h2>Result:</h2>");
	}
	
	public void constructPageBottom(PrintWriter output){
		output.println(
           "<FORM ACTION=/login2/DataAnalyticsDesignServlet" +
		   " METHOD=\"get\">\n"+
           "<BIG><CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Back\">\n" +
		   
           "</CENTER></BIG></FORM>");		
		
		output.println(	"</article>"+
						"</section>"+
						"<aside class='sidebar'>"+
						"<ul>"+
						"<li>"+
						"<h4>Products</h4>"+
						"<ul>"+
						/*"<li><a href='#'>Home Page</a></li>"+
						"<li><a href='/login2/PhoneDataStoreServlet'>Smart Phones</a></li>"+
						"<li><a href='/login2/TabletDataStoreServlet'>Tablets</a></li>"+
						"<li><a href='/login2/LaptopDataStoreServlet'>Laptops</a></li>"+
						"<li><a href='/login2/TvDataStoreServlet'>TV</a></li>"+*/
						/*
					TRENDING PRODUCT SERVLET WILL BE CALLED
					*/
                   // "<li><a href='/login2/TrendingProductServlet'>Trending</a></li>"+
						"</ul>"+
						"</li>"+
						"<li>"+
						"<h4>About us</h4>"+
						"<ul>"+
						"<li class='text'>"+
						"<p style='margin: 0;'>Welcome to Best Deals, Where you will get new deals everyday</p>"+
						"</li>"+
						"</ul>"+
						"</li>"+
						"</ul>"+
						"</aside>"+
						"<div class='clear'></div>"+
						"</div>"+
						"</div>"+
						"</body>"+
						"</html>");	
						
			output.close();

	}
	
	public void constructDefaultContent(DBCursor dbCursor, PrintWriter output, boolean countOnly){
		int count = 0;
		String tableData = " ";
		String pageContent = " ";

		while (dbCursor.hasNext()) {		
			BasicDBObject bobj = (BasicDBObject) dbCursor.next();
			tableData =  "<tr><td>Name: <b>     " + bobj.getString("productName") + " </b></td></tr>"
						+ "<tr><td>Price:       " + bobj.getString("productPrice") + "</br>"
						+ "<tr><td>Product Category :       " + bobj.getString("productCategory") + "</br>"
						+ "Retailer:            " + bobj.getString("productRetailer") + "</br>"
						+ "Retailer Zipcode:    " + bobj.getInt("retailerzip") + "</br>"
						+ "Retailer City:       " + bobj.getString("retailercity") + "</br>"
						+ "Retailer State:      " + bobj.getString("retailerstate") + "</br>"
						+ "Sale:                " + bobj.getString("sale") + "</br>"
						+ "User ID:             " + bobj.getString("userid") + "</br>"
						+ "User Age:            " + bobj.getString("userage") + "</br>"
						+ "User Gender:         " + bobj.getString("usergender") + "</br>"
						+ "User Occupation:     " + bobj.getString("userOccupation") + "</br>"
						+ "Manufacturer:        " + bobj.getString("mfname") + "</br>"
						+ "Manufacturer Rebate: " + bobj.getString("rebate") + "</br>"
						+ "Rating:              " + bobj.getString("reviewRating") + "</br>"
						+ "Date:                " + bobj.getString("reviewDate") + "</br>"
						+ "Review Text:         " + bobj.getString("reviewText") + "</td></tr>";
				
			count++;
				
				output.println("<h3>"+count+"</h3>");
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
		}

		//No data found
		if(count == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	
	
	public void constructGroupByCityContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly){
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		
		output.println("<h1> Grouped By - City </h1>");		
		for (DBObject result : aggregate.results()) {
				
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				
				rowCount++;
				tableData = "<tr><td>City: "+bobj.getString("City")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (productCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(productCount)+"</br>"
							+   "Rating: "+rating.get(productCount)+"</br>"
							+	"Review: "+productReview.get(productCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					productCount++;					
				}	
				
				//Reset product count
				productCount =0;
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	
		public void constructGroupByCityTopFiveContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly){
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		
		output.println("<h1>Top 5 Products Grouped By - City</h1>");		
		for (DBObject result : aggregate.results()) {
				
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				
				int size=0;
				int total=productList.size();
				if(total>5)
				size=5;
				else
				size=total;
				
				rowCount++;
				tableData = "<tr><td>City: "+bobj.getString("Retailer City")+"</td>&nbsp"
						+	"<td>Top "+size+" Reviews : </td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				
				
				while (productCount <size ) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(productCount)+"</br>"
							+   "<b>Rating: "+rating.get(productCount)+"</b></br>"
							+	"Review: "+productReview.get(productCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					productCount++;					
				}	
				
				//Reset product count
				productCount =0;
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	
	public void constructGroupByCityMedianContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly){
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		
		output.println("<h1>Median Price Grouped By - City</h1>");		
		for (DBObject result : aggregate.results()) {
		BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList price = (BasicDBList) bobj.get("Price");
				
				int total=0;
				total=price.size();
				output.println(price.size());
				int median_price=0;
				if(total%2!=0)
				{
				total=total/2;
				median_price= (Integer)price.get(total);
				}
				else
				{
				total=(total-1)/2;
				median_price=((Integer)price.get(total)+(Integer)price.get(total+1))/2;
				}
				
				tableData = "<tr><td>City: "+bobj.getString("Retailer City")+"</td>&nbsp"
						+	"<td><b>Median Price : "+median_price+"</b></td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
					while (productCount <price.size() ) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(productCount)+"</br>"
							+   "Rating: "+rating.get(productCount)+"</br>"
							+	" Price: "+price.get(productCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					productCount++;					
				}	
				
				//Reset product count
				productCount =0;
				
				
				
				
	
		}
	
	}

	public void constructGroupByRetailerNameContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly){
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		
		output.println("<h1> Grouped By - Retailer Name </h1>");		
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				
				rowCount++;
				tableData = "<tr><td>Retailer Name: "+bobj.getString("Retailer")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (productCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(productCount)+"</br>"
							+   "Rating: "+rating.get(productCount)+"</br>"
							+	"Review: "+productReview.get(productCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					productCount++;					
				}	
				
				//Reset product count
				productCount =0;
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	
	
		
	public void constructGroupByCityHighestContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly, String groupByField){
		
				int rowCount = 0;
				int reviewCount = 0;
				String tableData = " ";
				String pageContent = " ";
				
				output.println("<h1> Grouped By -" +groupByField +" - Highest Priced Product </h1>");
				
				for (DBObject result : aggregate.results()) {
				//output.println(aggregate.results());
				BasicDBObject bobj = (BasicDBObject) result;
				
				
				//Integer price = (Integer)bobj.get("Price");
				//output.println(bobj.getString(groupByField));
				DBObject query = new BasicDBObject("price",
						new BasicDBObject("$eq",bobj.get("Price"))).append(groupByField,new BasicDBObject("$eq",bobj.get(groupByField)));
				//output.println(query);
				DB db = mongo.getDB("bestdealsmongodb");
				DBCollection myReviews = db.getCollection("myReviews");
				DBObject d1 = myReviews.findOne(query);
				

				
				tableData = "<tr><td>"+groupByField+": " + bobj.getString(groupByField) + "</td>&nbsp"
						+ "<td>Total Reviews Found: " + bobj.getString("Review Count") + "</td></tr>";
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";
				output.println(pageContent);

				//Now print the products with the given review rating
				tableData = "Product Name: "+d1.get("productName")+"</br>"
							+  "Price: "+bobj.get("Price")+"(MAX PRICE)</td></br>"
							+  "Rating: "+d1.get("reviewRating")+"</td></br>"
							+  "Review: "+d1.get("reviewText")+"</td></tr>";

					pageContent = "<table class = \"query-table\">"+tableData+"</table>";
					output.println(pageContent);
	
			}
	}

	
		
	
	
	
	
	public void constructGroupByProductContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
				
		output.println("<h1> Grouped By - Products </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				
				rowCount++;
				tableData = "<tr><td>Product: "+bobj.getString("Product")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productReview.size()) {
					 tableData = "<tr rowspan = \"3\"><td>Rating: "+rating.get(reviewCount)+"</br>" 
								+ "Review: "+productReview.get(reviewCount)+"</td></tr>";
							
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}
					
				//Reset review count
				reviewCount = 0;
					
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
}