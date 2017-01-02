<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>


 <% List<Products> productList = new ArrayList<Products>();
 List<Products> productList1 = new ArrayList<Products>();
 List<Products> productList2 = new ArrayList<Products>();
 List<Products> productlist3 = new ArrayList<Products>();

 List<Products> tvList = new ArrayList<Products>();
 List<Products> laptopList = new ArrayList<Products>();
 List<Products> phoneList = new ArrayList<Products>();
 List<Products> tabletList = new ArrayList<Products>();

 String productCategory=" ";
 String productretailer=" ";
 String productid=" ";
 String productname=" ";
 String productprice=" ";

 String firstletter=" ";

 Products p;
 Map<String, List<Products>> prod;
 HashMap<String, List<Products>> mapInFile;

String k;
int flag;



        
        prod=new HashMap<String, List<Products>>();
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        
        HttpSession s=request.getSession(); 
        String user=((String)s.getAttribute("userid")); 


		productid = request.getParameter("productId");
        productname = request.getParameter("productName"); 
        
        productretailer = request.getParameter("productRetailer");      
		productprice = request.getParameter("productPrice");
        
		MySqlDataStore.updatingProductInfoByAdminInDb(productid,productname,productretailer,Integer.parseInt(productprice));
                                  
         %>
							


													


        <html>
		<head>
		<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
		<title>Product Added : Best Deal</title><link rel='stylesheet' href='styles.css' type='text/css' />
		</head>
		<body>
		<div id='container'>
		<header><div class='header_logo'><img src='images/best_deals.png'/><h1><a href='LoginJsp.jsp'>BEST <span>DEAL</span></a></h1>
		<h2>new deals everyday</h2>
		</div>
		</header>
		<h4><span style='width:310px;display:inline-block'></span>Your Product Has Been Added</h4>
		<span style='width:310px;display:inline-block'></span><a href='AdminHomePageJsp.jsp'>Due to extra security Please login again to see the changes</a>
		</body></html>



