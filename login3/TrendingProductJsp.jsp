<%@ page import="com.mongodb.MongoClient,
 com.mongodb.MongoException,
 com.mongodb.WriteConcern,
 com.mongodb.DB,
 com.mongodb.DBCollection,
 com.mongodb.BasicDBList,
 com.mongodb.BasicDBObject,
 com.mongodb.DBObject,
 com.mongodb.DBCursor,
 com.mongodb.ServerAddress,
 com.mongodb.AggregationOutput,
 com.mongodb.client.AggregateIterable,
 java.io.*,
 java.util.HashMap,
 java.util.Map,
 javax.servlet.*,
 javax.servlet.http.*,
 java.util.*"%>
 
 <%@ page import="WebAssignment3.*"%>



	<% DBObject match = null;
	DBObject groupFields = null;
	DBObject group = null;
	DBObject projectFields = null;
	DBObject project = null;
	DBObject sort=null;
	AggregationOutput aggregate = null;
				
	HashMap<String, Integer> map1;
	HashMap<String, Integer> map2;
	String username=" ";
	PrintWriter pw;
				
	DBCollection myReviews = MongoDBDataStoreUtilities.createMongoDB();
	
 
	pw= response.getWriter();
	HttpSession s=request.getSession();
    username=(String)s.getAttribute("userid"); %>

		<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />
					<title>Welcome to Best Deals</title>+
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
					<h2>TRENDING PRODUCTS</h2>

       <% aggregate=MongoDBDataStoreUtilities.mostLiked();
	   
        int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " "; %>
		
		<h2> Top Most Liked Products </h2>
		
	<%	for (DBObject result : aggregate.results()) {
				

			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productIdList = (BasicDBList) bobj.get("ProductID");
			BasicDBList productNameList = (BasicDBList) bobj.get("ProductName");
			BasicDBList ratingList = (BasicDBList) bobj.get("ReviewRating");
			
			ArrayList<Integer> filterList=new ArrayList<Integer>();
			
			for(Object o : ratingList){
				
				filterList.add((Integer) o);
				
			}
			
			for(int k=0;k<filterList.size();k++){
				System.out.println("The data inside the filterList is:::"+filterList.get(k));
			}
			
			rowCount++;

				//Now print the products with the given review rating
				while (productCount < productIdList.size()) { %>
				<% if (filterList.get(productCount)>=4){
					int num=filterList.get(productCount);%>	
				<TABLE BORDER=1 ALIGN=CENTER>
				<TR BGCOLOR='#FFAD00'>
				<TH>Details<TH>Values
				
				<TR><TD> Product ID: </TD>
					<TD><%=productIdList.get(productCount)%></TD></TR>
					
					<TR><TD> Rating: </TD>
					<TD><%=num%></TD></TR>
					
					<TR><TD> Product Name: </TD>
					<TD><%=productNameList.get(productCount)%></TD></TR>
					
				
					
					</TABLE>
					<%}%>
					
				<%	productCount++;					
				}	

				productCount =0;
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			pw.println(pageContent);
		}

        map1=MongoDBDataStoreUtilities.topFiveZipCodes();
        String tableData1 = " ";
		String pageContent1 = " ";%>
		<h2> Top Five Zip Codes With Max Product Sale </h2>
		<%for (Map.Entry<String,Integer> hm : map1.entrySet()) 
        {   
        	String zipCode = hm.getKey();                
            int count = hm.getValue(); %>
			
			<TABLE BORDER=1 ALIGN=CENTER>
			<TR BGCOLOR='#FFAD00'>
			<TH>Details<TH>Values
			<TR><TD> Zip Code:</TD>
			<TD><%=zipCode%></TD></TR>
			<TR><TD> Count:</TD>
			<TD><%=count%></TD></TR>
			
			</TABLE>
            
                                               
       <% }

        map2=MongoDBDataStoreUtilities.topMostReviewedProducts();
        
		String tableData2 = " ";
		String pageContent2 = " ";%>
		<h2> Top Five Most Reviewed Products </h2>
	<%for(Map.Entry<String,Integer> hm : map2.entrySet()) 
        {   
        	String product = hm.getKey();                
            int count2 = hm.getValue(); %>
			
			<TABLE BORDER=1 ALIGN=CENTER>
			<TR BGCOLOR='#FFAD00'>
			<TH>Details<TH>Values
			<TR><TD> Product: </TD>
			<TD><%=product%></TD></TR>
			<TR><TD> Count: </TD>
			<TD><%=count2%></TD></TR>
			
			</TABLE>
                                               
       <% } %>
		
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
