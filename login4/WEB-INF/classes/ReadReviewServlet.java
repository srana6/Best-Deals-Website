import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.*;
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

public class ReadReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String productName1;
	public String userName;
	public String category;
	public String price;
	public String retailer;
	public String retailerzip;
	public String retailercity;
	public String retailerstate;
	public String sale;
	public String mfname;
	public String userid;
	public String userage;
	public String usergender;
	public String reviewRating;
	public String reviewDate;
	public String reviewText;
	public String username;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		DBCollection myReviews = MongoDBDataStoreUtilities.createMongoDB();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			HttpSession s=request.getSession();
			username=(String)s.getAttribute("userid");
			
			// if database doesn't exists, MongoDB will create it for you
			//DB db = mongo.getDB("bestdealsmongodb");
			
			//DBCollection myReviews = db.getCollection("myReviews");
			
			String productID= request.getParameter("productFormId");
			System.out.println("ID recieved: "+productID);
			
			// Find and display 
			/*BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("productID", productID);*/
			
			DBCursor cursor1 = MongoDBDataStoreUtilities.fetchReviewMongoDB(productID);

			//DBCursor cursor1 = myReviews.find(searchQuery);
			System.out.println("Cursor value is: "+cursor1);
			String productName=request.getParameter("productFormName");
			PrintWriter pw = response.getWriter();
			//pw.println(cursor);
			System.out.println("Cursor value is: "+cursor1.count());
						
			
			pw.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />"+
					"<title>Welcome to Best Deals</title>"+
					"<link rel='stylesheet' href='styles.css' type='text/css' />"+ 
					"</head>");
		
			pw.println("<body>"+
					"<div id='container'>"+
					"<header>"+
					"<div id=imageLogo>"+
					"<img src='images/best_deals.png'/>"+
					"<h1><a href='/'>Best<span>Deals</span></a></h1>"+
					"<h2>new deals everyday</h2>"+
					"<h5 style='color:blue;float:right' ><a href='/login4/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ username +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='#'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
                    "<li><a href='/login4/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login4/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login4/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login4/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login4/ContactServlet'>ContactUS</a></li>"+
					//"<li><a href='#'>Welcome</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					/*"<form method='get' id='search' action='#'>"+
					"<span>"+
					"<input type='text' value='Search...' id='searchbox' />"+
					"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
					"</span>"+*/
					"</form>"+
					"</div>"+
					"</li>"+
					"</ul>"+
					"</nav>"+
					"<img class=header-image src='images/combine_images.png' alt='phones' />"+
					"</div>"+
					"<div id='body'>"+
					"<section id='content'>"+
					"<article>");
			
			if(cursor1.count() == 0){
			pw.println("There are no reviews for this product.");
		}else{
				int counter=0;
				
				while (cursor1.count()>counter) {
					counter++;
					
					BasicDBObject obj = (BasicDBObject) cursor1.next();	
					
					pw.println("<TABLE BORDER=1 ALIGN=CENTER>\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>Product Review<TH>Value(s)");
				
				pw.print("<TR><TD> Product Name  \n</TD>");
				pw.print("<TD>" + obj.getString("productName") + "\n</TD></TR>");

				pw.print("<TR><TD> Product ID  \n</TD>");
				pw.print("<TD>" + obj.getString("productID") + "\n</TD></TR>");
				
				pw.print("<TR><TD> Category  \n</TD>");
				pw.print("<TD>" + obj.getString("productCategory") + "\n</TD></TR>");

				pw.print("<TR><TD> Price  \n</TD>");
				pw.print("<TD>" + obj.getString("productPrice") + "\n</TD></TR>");

				pw.print("<TR><TD> Retailer  \n</TD>");
				pw.print("<TD>" + obj.getString("productRetailer") + "\n</TD></TR>");

				pw.print("<TR><TD> Retailer Zip  \n</TD>");
				pw.print("<TD>" + obj.getString("retailerzip") + "\n</TD></TR>");
				
				pw.print("<TR><TD> Retailer City  \n</TD>");
				pw.print("<TD>" + obj.getString("retailercity") + "\n</TD></TR>");

				pw.print("<TR><TD> Retailer State  \n</TD>");
				pw.print("<TD>" + obj.getString("retailerstate") + "\n</TD></TR>");

				pw.print("<TR><TD> Sale  \n</TD>");
				pw.print("<TD>" + obj.getString("sale") + "\n</TD></TR>");
				
				pw.print("<TR><TD> Manufacturer Name  \n</TD>");
				pw.print("<TD>" + obj.getString("mfname") + "\n</TD></TR>");

				pw.print("<TR><TD> User Age  \n</TD>");
				pw.print("<TD>" + obj.getString("userage") + "\n</TD></TR>");

				pw.print("<TR><TD> User Gender  \n</TD>");
				pw.print("<TD>" + obj.getString("usergender") + "\n</TD></TR>");

				pw.print("<TR><TD> User Name  \n</TD>");
				pw.print("<TD>" + obj.getString("userid") + "\n</TD></TR>");

				pw.print("<TR><TD> Review Rating  \n</TD>");
				pw.print("<TD>" + obj.getString("reviewRating").toString() + "\n</TD></TR>");

				pw.print("<TR><TD> Review Date  \n</TD>");
				pw.print("<TD>" + obj.getString("reviewDate") + "\n</TD></TR>");

				pw.print("<TR><TD> Review Text  \n</TD>");
				pw.print("<TD>" + obj.getString("reviewText") + "\n</TD></TR>");

				pw.println("</TABLE>\n");

				pw.println
          ("</TABLE>\n" +
           "<FORM ACTION=/login4/PhoneDataStoreServlet" +
		   " METHOD=\"get\">\n"+
           "<BIG><CENTER>\n" +
		   "<p>Thank you for shopping with us...</p>"+
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Back\">\n" +
		   
           "</CENTER></BIG></FORM>");			
					
					
					
				}
			}	
			pw.println("</article>"+
					"</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
                    "<li><a href='/login4/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login4/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login4/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login4/TvDataStoreServlet'>TV</a></li>"+
					/*
					TRENDING PRODUCT SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login4/TrendingProductServlet'>Trending</a></li>"+
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
			
		} 
		catch (MongoException e) {
				e.printStackTrace();
		}  
	}

	
	public void destroy(){
      // do nothing.
	}
}