<%@ page import=" java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>

	
	
	
	<%List<String> l=new ArrayList<String>();
	
	String userid=" ";
	String password=" ";
	String id=" ";
	String pass=" ";
	String match=" ";
	String message="Login Failure! Username or password is incorrect";
	boolean flag=false;
	List<Products> productList = new ArrayList<Products>();
	List<Products> smartList;

	 HashMap<String,String> clientcred;
	 HashMap<String,String> admincred;
	 HashMap<String,String> salescred;
 
	 String k=" ";
	 String val=" ";
   
    //Map users = new HashMap();
	 
	//SaxParser.loadProducts();
			
	clientcred=new HashMap<String,String>();
	admincred=new HashMap<String,String>();
	salescred=new HashMap<String,String>();
		
    userid = request.getParameter("username");
	System.out.println("Userid:::"+userid);
    password = request.getParameter("password");
	System.out.println("Password:::"+password);
		
	clientcred=MySqlDataStore.selectUser();
	System.out.println("The client credentials are::"+ clientcred);
	admincred=MySqlDataStore.selectAdmin();
	salescred=MySqlDataStore.selectSales();
		
        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(userid != null && password != null) {
			
				if(userid.equals("admin")){
					for(Map.Entry<String,String> m :admincred.entrySet()){
						id=m.getKey();
						val=m.getValue();
			
						if(userid.equals(id)&&password.equals(val)){
						response.sendRedirect("AdminHomePageJsp.jsp");
						HttpSession s=request.getSession();
						s.setAttribute("userid",userid);
						}
						
					}
					
				}
				else if(userid.equals("sales")){
					for(Map.Entry<String,String>hm : salescred.entrySet()){
						id=hm.getKey();
						val=hm.getValue();
					
			
						if(userid.equals(id)&&password.equals(val)){
						
						response.sendRedirect("SalesHomePageJsp.jsp");
						HttpSession s=request.getSession();
						s.setAttribute("userid",userid);
						}
					}
				}
				else{
        System.out.println("flow comes here--> client credentials");
        for(Map.Entry<String,String> hm :clientcred.entrySet()){
			
			id=hm.getKey();
			System.out.println("id recieved:::"+id);
			val=hm.getValue();
			System.out.println("val recieved:::"+val);
			
			if(userid.equals(id)&&password.equals(val)){
				HttpSession s=request.getSession();
				s.setAttribute("userid",userid);
				flag=true;
				%>

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
			<h3 style='color:blue;float:right;font-style:italic' >Welcome:<%=userid%> </h3>
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
			<h2>Introduction to BEST DEALS</h2>
			<p>Welcome to Best Deals, a place to do your shopping.
			We gurantee that you will get the lowest price which you have never seen before.
			Apart from low price we maintain quality so our custmoers have 100% satisfaction.
			You can shop best Smart Phones, Tablets, Smart Tv, Laptops, more products are comming soon.
			Hope you wil have best experience here. Enjoy!</p>
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
			
			
			<%
			}
 
		}
		if(flag == false){%>
		<html>
		<head>
		<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
		<title>Product Added : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
		</head>
		<body>
		<div id='container'>
		<header><div class='header_logo'><img src='images/best_deals.png'/>
		<h1><a href='#'>BEST <span>DEAL</span></a></h1>
		<h2>new deals everyday</h2></div></header>
		<h3><span style='width:310px;display:inline-block'></span><%=message%></h3>
		<span style='width:310px;display:inline-block'></span><a href=#>Sorry</a>
					
		<%}
	}
}%>
