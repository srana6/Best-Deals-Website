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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;


public class DataAnalyticsDesignServlet extends HttpServlet {

		public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		
		PrintWriter pw= response.getWriter();
		pw.println("<html>"+
						"<head>"+
						"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
						"<title>SignIn : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
						"</head>"+
						"<body>"+
						"<div id='container'>"+
						"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
						"<h1><a href='/login4/HomeServlet'>BEST <span>DEAL</span></a></h1>"+
						"<h2>new deals everyday</h2>" + "<span style='width:110px;display:inline-block'></span>"+ "</div>" + "</header>"+
						"<h5 style='color:blue;float:right' ><a href='/login4/SignOutServlet'>Sign Out</a></h5></br>");
		pw.println(		"<article>"+
                    "<h3> Find Data </h3>"+
                    "<form method='post' class='searchform' action='/login4/DataAnalysisServlet'>"+
                    "<table class = 'query-table'>"+
					"<tr>"+
					"<td colspan = '4'> <b> Simple Search </b> </td>"+
					"</tr>"+
					
           			"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='productName' > Select </td>"+
                    "<td> Product Name: </td>"+
					"<td>"+
                    "<select name='productName'>"+
					"<option value='ALL_PRODUCTS'>All Products</option>"+
					"<option value='Laptop'>Laptop</option>"+
					"<option value='Phone'>Phone</option>"+ 
					"<option value='Tablet'>Tablet</option>"+
					"<option value='TV'>TV</option>"+ 					
                    "</td>"+
					"<td> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='productPrice'> Select </td>"+
                    "<td> Product Price: </td>"+
                    "<td>"+
                    "<input type='number' name='productPrice' value = '0' size=10 class='s' /> </td>"+
					"<td>"+
					"<input type='radio' name='comparePrice' value='EQUALS_TO' > Equals <br>"+
					"<input type='radio' name='comparePrice' value='GREATER_THAN'> Greater Than <br>"+
					"<input type='radio' name='comparePrice' value='LESS_THAN'> Less Than</td>"+
                    "</tr>"+

					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='productCategory'> Select </td>"+
                    "<td> Product Category: </td>"+
                    "<td>"+
                    "<input type='text' name='productCategory' value = '' size=10 class='s' /> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='retailerName'> Select </td>"+
                    "<td> Retailer Name: </td>"+
                    "<td>"+
                    "<input type='text' name='retailerName' value = '' size=15 class='s' /> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='retailerState'> Select </td>"+
                    "<td> Retailer State: </td>"+
                    "<td>"+
                    "<input type='text' name='retailerState' value = '' size=15 class='s' /> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='productonSale' > Select </td>"+
                    "<td> Product on Sale: </td>"+
					"<td>"+
                    "<select name='productonSale'>"+
					"<option value='Yes'>Yes</option>"+
					"<option value='No'>No</option>"+                                  
                    "</td>"+
					"<td> </td>"+
                    "</tr>"+
					
                    "<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='retailerZipcode'> Select </td>"+
                    "<td> Retailer Zip code: </td>"+
                    "<td>"+
                    "<input type='number' name='retailerZipcode' value = '0' size=10/> </td>"+
					"<td> </td>"+
                    "</tr>"+
					"<tr>"+
					"<td><input type='checkbox' name='queryCheckBox' value='retailerCity'> Select </td>"+
					"<td> Retailer City: </td>"+
					"<td>"+
					"<input type='text' name='retailerCity' value = 'All' class='s' /> </td>"+
					"<td> </td>"+
					"</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='manufacturerName'> Select </td>"+
                    "<td> Manufacturer Name: </td>"+
                    "<td>"+
                    "<input type='text' name='manufacturerName' value = '' size=15 class='s' /> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='manufacturerRebate' > Select </td>"+
                    "<td> Manufacturer Rebate: </td>"+
					"<td>"+
                    "<select name='manufacturerRebate'>"+
					"<option value='Yes'>Yes</option>"+
					"<option value='No'>No</option>"+                                  
                    "</td>"+
					"<td> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='userID'> Select </td>"+
                    "<td> User ID: </td>"+
                    "<td>"+
                    "<input type='number' name='userID' value = '' size=15 class='s' /> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='userAge'> Select </td>"+
                    "<td> User Age: </td>"+
                    "<td>"+
                    "<input type='number' name='userAge' value = '' size=15 class='s' /> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='userGender'> Select </td>"+
                    "<td> User Gender: </td>"+
                    "<td>"+
                    "<input type='text' name='userGender' value = '' size=15 class='s' /> </td>"+
                    "</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='userOccupation'> Select </td>"+
                    "<td> User Occupation: </td>"+
                    "<td>"+
                    "<input type='text' name='userOccupation' value = '' size=15 class='s' /> </td>"+
                    "</tr>"+
					
					
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='reviewRating'> Select </td>"+
					"<td> Review Rating: </td>"+
					"<td>"+
					"<select name='reviewRating'>"+
					"<option value='1' selected>1</option>"+
					"<option value='2'>2</option>"+
					"<option value='3'>3</option>"+
					"<option value='4'>4</option>"+
					"<option value='5'>5</option>"+
					"</td>"+
					"<td>"+
					"<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>"+
					"<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than</td>"+
					"</tr>"+
					
					"<tr>"+
					"<td> <input type='checkbox' name='queryCheckBox' value='reviewText'> Select </td>"+
                    "<td> Review Text: </td>"+
                    "<td>"+
                    "<input type='textarea' name='reviewText' value = '' size=15 class='s' /> </td>"+
					"<td>(PATTERN MATCHING) </td>"+
                    "</tr>"+
					
					
					
					"<tr>"+
					"<td>Return:"+
					"</td>"+
					"<td colspan = '4'>"+
					"<select name='returnValue'>"+
					"<option value='ALL' selected>All</option>"+
					"<option value='TOP_5'>Top 5 </option>"+
					"<option value='TOP_10'>Top 10 </option>"+
					"<option value='LATEST_5'>Latest 5 </option>"+
					"<option value='LATEST_10'>Latest 10 </option>"+
					"</td>"+
					"</tr>"+
					
					"<tr>"+
					"<td colspan = '4'> <b> Grouping </b> </td>"+
					"</tr>"+
					"<tr>"+
					"<td>"+
					"<input type='checkbox' name='extraSettings' value='GROUP_BY'> Group By</td>"+
					"<td colspan = '3'>"+
					"<select name='groupByDropdown'>"+
					"<option value='GROUP_BY_CITY' selected>City</option>"+
					"<option value='GROUP_BY_PRODUCT'>Product Name</option>"+
					"<option value='GROUP_BY_RETAILER_NAME'>Retailer Name</option>"+
					"<option value='GROUP_BY_RETAILER_ZIP'>Retailer Zip</option>"+
					"</td>"+
					"</tr>"+
					
					"<tr>"+
					"<td>Return:"+
					"</td>"+
					"<td colspan = '4'>"+
					"<select name='returnData'>"+
					"<option value='ALL' selected>All</option>"+
					"<option value='HIGHEST_PRICE_PRODUCT'>Highest Price Product </option>"+
					"<option value='MEDIAN'>Median Price</option>"+
					"<option value='TOP_5_LIKED_PRODUCT'>Top 5 Liked Product </option>"+
					
					"</td>"+
					"</tr>"+
					
					
					"<tr>"+
					"<td colspan = '4'> <input type='submit' value='Find Data' class='formbutton' /> </td>"+
					"</form>"+
					/*"<form action='/csj/TrendingServlet' class='searchform' method='post'>"+
					"<tr>"+
					"<td colspan = '4'><input type='submit' value='TrendingServlet' class='formbuttom'/> </td>"+
					"</tr>"+
					"</table>"+
					"</form>"+*/
					
					"</article>"+
					"</section>"+
					"<div class='clear'></div>"+
					"</div>"+
					"<footer>"+
					"</div>"+
					"</body>"+
					"</html>");

					

		}
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




