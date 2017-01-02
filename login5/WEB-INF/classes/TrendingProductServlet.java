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
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TrendingProductServlet extends HttpServlet {

				DBObject match = null;
				DBObject groupFields = null;
				DBObject group = null;
				DBObject projectFields = null;
				DBObject project = null;
				DBObject sort=null;
				AggregationOutput aggregate = null;
				
				HashMap<String, Integer> map1;
				HashMap<String, Integer> map2;
				String username;
				DBCollection myReviews;
				public PrintWriter pw;
				
	public void init() throws ServletException{
      	// Connect to Mongo DB
	 myReviews = MongoDBDataStoreUtilities.createMongoDB();
	}
 
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
 
	pw= response.getWriter();
	HttpSession s=request.getSession();
    username=(String)s.getAttribute("userid");

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
					"<h5 style='color:blue;float:right' ><a href='/login5/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ username +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='DealMatches'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login5/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login5/ContactServlet'>ContactUS</a></li>"+
					//"<li><a href='#'>Welcome</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					/*"<form method='get' id='search' action='#'>"+
					"<span>"+
					"<input type='text' value='Search...' id='searchbox' />"+
					"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
					"</span>"+
					"</form>"+*/
					"</div>"+
					"</li>"+
					"</ul>"+
					"</nav>"+
					"<img class=header-image src='images/combine_images.png' alt='phones' />"+
					"</div>"+
					"<div id='body'>"+
					"<section id='content'>"+
					"<article>"+
					"<h2>TRENDING PRODUCTS</h2>");

        //aggregate=MongoDBDataStoreUtilities.mostLiked();
        //fiveMostLiked(aggregate);
		DBCursor cursor=MongoDBDataStoreUtilities.lessThan_GreaterThan_Rating(myReviews);
		fiveMostLiked(cursor);

        map1=MongoDBDataStoreUtilities.topFiveZipCodes();
        fiveZipCodes(map1);

        map2=MongoDBDataStoreUtilities.topMostReviewedProducts();
        topFiveReviewedProducts(map2);
		
        pw.println("</article>"+
					"</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login5/TvDataStoreServlet'>TV</a></li>"+
					/*
					TRENDING PRODUCT SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TrendingProductServlet'>Trending</a></li>"+
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
		pw.close();


        			  			


}

					

	public void fiveMostLiked(DBCursor cursor) {
	List<String> listUnique=new ArrayList<String>();
	System.out.println("The count of cursor is::::"+cursor.count());
	int counter=0;
	pw.println("<h2> Top Most Liked Product </h2>");
    while(cursor.count()>counter) {
		System.out.println("The counter count is::::"+counter);
        counter++;
					
					BasicDBObject obj = (BasicDBObject) cursor.next();
					String prodId=obj.getString("productID");
					if(!listUnique.contains(prodId)){
					listUnique.add(prodId);
					
				pw.println("<TABLE BORDER=1 ALIGN=CENTER>\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "<TH>Product Review<TH>Value(s)");
				
				pw.print("<TR><TD> Product Name  \n</TD>");
				pw.print("<TD>" + obj.getString("productName") + "\n</TD></TR>");

				pw.print("<TR><TD> Product ID  \n</TD>");
				pw.print("<TD>" + obj.getString("productID") + "\n</TD></TR>");
				
				pw.print("<TR><TD> Review Rating  \n</TD>");
				pw.print("<TD>" + obj.getString("reviewRating").toString() + "\n</TD></TR>");
				
				pw.println("</TABLE>\n");
				
					}
					else{
						System.out.println("Item already exist in the list");
					}
    }
}

	/*public void fiveMostLiked(AggregationOutput aggregate){
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		
		pw.println("<h2> Top Most Liked Products </h2>");		
		for (DBObject result : aggregate.results()) {
				

			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productIdList = (BasicDBList) bobj.get("ProductID");
			BasicDBList productNameList = (BasicDBList) bobj.get("ProductName");
			BasicDBList ratingList = (BasicDBList) bobj.get("ReviewRating");
			
			rowCount++;

				//Now print the products with the given review rating
				while (productCount < productIdList.size()) {
					
					pw.println(	"<TABLE BORDER=1 ALIGN=CENTER>\n" +
								"<TR BGCOLOR='#FFAD00'>\n" +
								"<TH>Details<TH>Values");
					pw.print("<TR><TD> Product ID: \n</TD>");
					pw.print("<TD>" + productIdList.get(productCount) + "\n</TD></TR>");
					
					pw.print("<TR><TD> Rating: \n</TD>");
					pw.print("<TD>" + ratingList.get(productCount) + "\n</TD></TR>");
					
					pw.print("<TR><TD> Product Name: \n</TD>");
					pw.print("<TD>" + productNameList.get(productCount) + "\n</TD></TR>");
					
					pw.println("</TABLE>\n");
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(productCount)+"</br>"
							+   "Rating: "+rating.get(productCount)+"</br>"
							+	"Review: "+productReview.get(productCount)+"</td></tr>";
												
					pageContent = "<table class = 'query-table'>"+tableData+"</table>";		
					pw.println(pageContent);
					
					productCount++;					
				}	
				
				//Reset product count
				//productList.removeAll(productList);
				//productReview.removeAll(productReview);
				//rating.removeAll(rating);

				productCount =0;
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			pw.println(pageContent);
		}
		
	}*/

	public void fiveZipCodes(HashMap<String, Integer>  mapInFile1){
		String tableData1 = " ";
		String pageContent1 = " ";
		pw.println("<h2> Top Five Zip Codes With Max Product Sale </h2>");
		for (Map.Entry<String,Integer> hm : mapInFile1.entrySet()) 
        {   
        	String zipCode = hm.getKey();                
            int count = hm.getValue();
			
			pw.println(	"<TABLE BORDER=1 ALIGN=CENTER>\n" +
								"<TR BGCOLOR='#FFAD00'>\n" +
								"<TH>Details<TH>Values");
			pw.print("<TR><TD> Zip Code: \n</TD>");
			pw.print("<TD>" + zipCode + "\n</TD></TR>");
			pw.print("<TR><TD> Count: \n</TD>");
			pw.print("<TD>" + count + "\n</TD></TR>");
			
			pw.println("</TABLE>\n");
            /*tableData1 = "<tr rowspan = \"3\"><td> Zip Code: "+zipCode+"</br>"
							+   "Count: "+count+"</br>"
							+	"</td></tr>";
												
			pageContent1 = "<table class = \"query-table\">"+tableData1+"</table>";		
			pw.println(pageContent1);*/
                                               
        }

}
	public void topFiveReviewedProducts(HashMap<String, Integer>  mapInFile2){
		String tableData2 = " ";
		String pageContent2 = " ";
		pw.println("<h2> Top Five Most Reviewed Products </h2>");
	for (Map.Entry<String,Integer> hm : mapInFile2.entrySet()) 
        {   
        	String product = hm.getKey();                
            int count2 = hm.getValue();
			
			pw.println(	"<TABLE BORDER=1 ALIGN=CENTER>\n" +
								"<TR BGCOLOR='#FFAD00'>\n" +
								"<TH>Details<TH>Values");
			pw.print("<TR><TD> Product: \n</TD>");
			pw.print("<TD>" + product + "\n</TD></TR>");
			pw.print("<TR><TD> Count: \n</TD>");
			pw.print("<TD>" + count2 + "\n</TD></TR>");
			
			pw.println("</TABLE>\n");
                                               
        }

}




		/** Handles the HTTP <code>GET</code> method.
		* @param request servlet request
		* @param response servlet response
		*/
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, java.io.IOException {
		    processRequest(request, response);
		} 

		/** Handles the HTTP <code>POST</code> method.
		* @param request servlet request
		* @param response servlet response
		*/
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, java.io.IOException {
		    processRequest(request, response);
		}
}