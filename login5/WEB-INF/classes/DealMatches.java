import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DealMatches extends HttpServlet {
	public ServletContext sc;
	
	
	
	
	public void runThisMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		sc=request.getSession().getServletContext();
		PrintWriter pw= response.getWriter(); 
		response.setContentType("text/html");
		
		HashMap<String,Products> selectAllProducts_hash=new HashMap<String,Products>();
		HashMap<String,Products> selectedproducts=new HashMap<String,Products>();	
		String line=null;
		
		HttpSession s=request.getSession();
    	String userid=(String)s.getAttribute("userid");
		
		selectAllProducts_hash= MySqlDataStore.getData();
		//System.out.println("The Result in selectAllProducts_hash is:::::"+selectAllProducts_hash);
		
		
		
		pw.println("<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />"+
					"<title>Welcome to Best Deals</title>"+
					"<script src='javascript.js'></script>"+
					"<link rel='stylesheet' href='styles.css' type='text/css' />"+ 
					"</head>");
		
		pw.println("<body onload='init()'>"+
					"<div id='container'>"+
					"<header>"+
					"<div id=imageLogo>"+
					"<img src='images/best_deals.png'/>"+
					"<h1><a href='/'>Best<span>Deals</span></a></h1>"+
					"<h2>new deals everyday</h2>"+
					"<h5 style='color:blue;float:right' ><a href='/login5/SignOutServlet'>Sign Out</a></h5></br>"+
					"<h3 style='color:blue;float:right;font-style:italic' >Welcome: "+ userid +"</h3>"+
					"</div>"+
					"</header>"+
					"<div class='menu-wrap'>"+
					"<nav class='menu'>"+
					"<ul class='clearfix'>"+
					"<li><a href='DealMatches'>Home</a></li>"+
					"<li>"+
					"<a href='#'>Products <span class='arrow'>&#9660;</span></a>"+
					"<ul class='sub-menu'>"+
					/*
					PHONE DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login5/ContactServlet'>ContactUS</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					/*"<form method='get' id='search' action='#'>"+
					"<span>"+
					"<input type='text' value='Search...' id='searchbox' />"+
					//"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
					"</span>"+
					"</form>"+*/
					"</div>"+
					"</li>"+
					"</ul>"+
					"</nav>"+
					"<img class=header-image src='images/combine_images.png' alt='phones' />"+
					"</div>"+
					"<div id='search_body'>"+
					"<form name='autofillform' action='autocomplete'><table><tbody>"+
					"<input type='text' size='30'  name='searchId' id='searchId' onkeyup='doCompletion()' style='font-size: 16px;' placeholder='Search Here...!!' />"+
					//"<img src='images/Search.png' style='margin-top:10px;margin-left: 5px;'/>"+
					"</td></tr><tr><td id='auto-row' colspan='2'><table id='complete-table' class='popupBox' /></td></tr></tbody></table></form>"+ 
					"</div>"+
					"<div id='body'>"+
					"<section id='content'>"+
					"<article>"+
					"<h2>Introduction to BEST DEALS</h2>"+
					"<p>Welcome to Best Deals, a place to do your shopping. "+
					"We gurantee that you will get the lowest price which you have never seen before. "+
					"Apart from low price we maintain quality so our custmoers have 100% satisfaction. "+
					"You can shop best Smart Phones, Tablets, Smart Tv, Laptops, more products are comming soon. "+
					"Hope you wil have best experience here. Enjoy!</p>	"+
					"</article>");
					//System.out.println("The Result in selectAllProducts_hash222 is:::::"+selectAllProducts_hash);
					for(Map.Entry<String,Products> hm : selectAllProducts_hash.entrySet()){
						if(selectedproducts.size()<2 && !selectedproducts.containsKey(hm.getKey())){
							BufferedReader reader= new BufferedReader(new FileReader(new File("C:\\apache-tomcat-7.0.34\\webapps\\login5\\DealMatches.txt")));
							line=reader.readLine();
							if(line==null){
								 pw.println("<h2 align='center'>No Offers Found</h2>");
								 break;
							}
							else{
								do{
									if(line.contains(hm.getKey())){
										pw.println("<h2>"+line+"</h2>");
										pw.println("<br>");
										selectedproducts.put(hm.getKey(),hm.getValue());
										break;
									}
								}while((line = reader.readLine()) != null);
							}
						}
						
					}
					for(Map.Entry<String, Products> map: selectedproducts.entrySet()){
						Products prod= map.getValue();
						
					pw.println("<p><img src="+prod.productImage+"/>");
					pw.println("<p Style='Color:Red'>Product ID:</p>");
					pw.println("<p>"+prod.productId+"</p>");
					pw.println("<p Style='Color:Red'>Product Name:</p>");
					pw.println("<p>"+prod.productName+"</p>");
					pw.println("<p Style='Color:Red'>Product Price:</p>");
					pw.println("<p>"+prod.productPrice+"</p>");
					pw.println("<p Style='Color:Red'>Product Manufacturer:</p>");
					pw.println("<p>"+prod.productRetailer+"</p>");
					/*
					WHEN USER CLICKS ON ADD TO CART -- ADDING INTO CART SERVLET WILL BE CALLED
					*/
					pw.println("<form action='/login5/AddingIntoCartServlet' method='get' style='margin-bottom:20px;'>");
					pw.println("<input type='hidden' name='productFormId' value='"+prod.productId+"' />");
					pw.println("<input type='submit' value='Add to Cart' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>");
					//pw.println("<hr width='85%' size='4' align='center'");
					
					pw.println("<form action='/login5/WriteReviewServlet' method='get' style='margin-bottom:20px;'>");
					pw.println("<input type='hidden' name='productFormId' value='"+prod.productId+"' />");
					pw.println("<input type='hidden' name='productFormName' value='"+prod.productName+"' />");
					pw.println("<input type='hidden' name='productFormImage' value='"+prod.productImage+"' />");
					pw.println("<input type='hidden' name='productFormRetailer' value='"+prod.productRetailer+"' />");
					pw.println("<input type='hidden' name='productFormPrice' value='"+prod.productPrice+"' />");
					pw.println("<input type='submit' value='Write Review' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>");
					
					pw.println("<form action='/login5/ReadReviewServlet' method='get' style='margin-bottom:20px;'>");
					pw.println("<input type='hidden' name='productFormId' value='"+prod.productId+"' />");
					pw.println("<input type='submit' value='Read Review' class='submit-button' style='width:250px;font-size:7pt;color:white;background-color:red;border:2px solid #336600;padding:3px'></form>");
					}
					pw.println("</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
					/*
					PHONE DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/PhoneDataStoreServlet'>Smart Phones</a></li>"+
					/*
					TABLET DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/TabletDataStoreServlet'>Tablets</a></li>"+
					/*
					LAPTOP DATA STORE SERVLET WILL BE CALLED
					*/
                    "<li><a href='/login5/LaptopDataStoreServlet'>Laptops</a></li>"+
					/*
					TV DATA STORE SERVLET WILL BE CALLED
					*/
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
/*
	CALL COMES HERE AND RUN THIS METHOD WILL BE CALLED
	*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
	runThisMethod(request,response);
}
	
		
	}