/*
CALL FROM "ADD TO CART SERVLET" WILL COME HERE
*/
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProductCartServlet extends HttpServlet implements Serializable {

	transient List<String> cartList = new ArrayList<String>();
	transient List<String> cartList1 = new ArrayList<String>();

	public HashMap<String, List<String>> map;
	public HashMap<String, List<Products>> map1;
	
	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();
	
	transient List<Integer> productValues=new ArrayList<Integer>();

	public Map<String, List<Products>> prod ;
	public String k;
	public Products p;
	public String username;

	public ServletContext sc;
	public String firstLetter;
    public String productcategory;
    public String productretailer;
    public String productid;
    public String productname;        
    public int productprice = 0;
    public String prodpricestr;
	public String productformid;
	
	int count = 0;
	int temp=0;
	int tempPrice;
	public int totalPrice=0;
	public int val;
	public int[] priceArray = new int[50];

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		
		sc=request.getSession().getServletContext();
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		System.out.println("The username recieved inside productCartServlet is: "+ username);
		
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        
        try{
			/*
			THE DATA INSIDE THE FILE-"PRODUCTSINCARTFILE" IS RED AND LOADED INTO A HASHMAP
			*/
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("ProductsInCartFile"))));
            map=(HashMap<String,List<String>>)ois.readObject();
            ois.close();
                             
            for (Map.Entry<String, List<String>> hm : map.entrySet()) 
                {                   
					k = hm.getKey();
					System.out.println("The key in hashmap(productCartServlet) is: "+k);
                    if(k.equals(username))
                    {
                        cartList = hm.getValue();
                        break;
                    }                                                 
               }
			   /*
			   printing the cartList to see the items
			   */
			for(int i=0;i<cartList.size();i++){
				System.out.println("The items recieved in cartList(productCartServlet) are: "+cartList.get(i));
			}

            }catch(Exception e){  }

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
					"<h5 style='color:blue;float:right' ><a href='/login1/SignOutServlet'>Sign Out</a></h5></br>"+
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
                    "<li><a href='/login1/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login1/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login1/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login1/TvDataStoreServlet'>TV</a></li>"+
					"</ul>"+
					"</li>"+
					"<li class='current-item'><a href='/login1/ContactServlet'>ContactUS</a></li>"+
					//"<li><a href='#'>Welcome</a></li>"+
					"<li>"+
					"<div class='gadget'>"+
					"<form method='get' id='search' action='#'>"+
					"<span>"+
					"<input type='text' value='Search...' id='searchbox' />"+
					"<input type='button' value='Go' id='searchbutton' class='btn'  />"+
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
					"<h2>CART</h2>");
					/*
					PRINTING DATA INSIDE CARTLIST ON HTML PAGE
					*/
					int i=0;
					while(i<cartList.size()){
					String v=cartList.get(i);
					System.out.println("The values comming from cart list are: "+v);
					if(!v.equals("$")){
						cartList1.add(v);
					}
					else if(v.equals("$")){
						/*
						ITERATING THE LIST--CARTLIST1 WHICH DOESN'T CONTAIN "$"
						*/
						for ( int k=0; k<cartList1.size();k++) {
                        System.out.println(k);
					
						if(k ==0)
							{
								pw.println("<img src="+cartList1.get(k)+"/>");
								pw.println("<br/><br/>");
							}
				   
						if(k ==1)
							{
								pw.println("<p align='left'>Name:" + cartList1.get(k));
								pw.println("<br/><br/>");
							}
						if(k ==2)
							{
								pw.println("<p align='left'>Retailer:" + cartList1.get(k) + "</p>");
								pw.println("<br/><br/>");
							}
						if(k ==3)
							{
								pw.println("<p align='left'>Price:" + cartList1.get(k)+ "</p>");
								tempPrice = Integer.parseInt(cartList1.get(k));
								System.out.println("The temp price going inside the productValues list is: "+tempPrice);
								productValues.add(tempPrice);
								int pos=productValues.size()-1;
								val=productValues.get(pos);
								/*if(productValues.size()>1){
									productValues.clear();
									System.out.println("list is cleared"+productValues.size());
								}
								else if(productValues.size()<=1){
									System.out.println("list size: "+productValues.size());
									totalPrice=totalPrice+tempPrice;
								}*/
								
								
								
								//priceArray[]=tempPrice;
								pw.println("<br/><br/>");
							}
						if(k ==4)
							{
								pw.println("<p align='left'>ID:" + cartList1.get(k)+ "</p>");
								pw.println("<br/><br/>");
								/*
								DELETE FROM CART BUTTON IS DISPLAYED
								*/
								pw.println("<form action='/login1/DeleteProductFromCartServlet' method='get' style='margin-bottom:20px;'>"+
								"<input type='hidden' name='deleteFormId' value='"+cartList1.get(k)+"' />" +"<span style='width:110px;display:inline-block'></span>"+
								"<input type='submit' value='Delete From Cart' class='submit-button' style='width:200px;'>" +
								"</form>");
								pw.println("<hr width='90%' size='2' align='center'>");
                    
							}

						}	
                    cartList1.clear();
                }
			   i++;
		   }
		   
		   totalPrice=totalPrice+val;
		   System.out.println("The total price is: "+totalPrice);

            
            pw.println("</article>"+
					"</section>"+
					"<aside class='sidebar'>"+
					"<ul>"+
					"<li>"+
                    "<h4>Products</h4>"+
                    "<ul>"+
                    "<li><a href='#'>Home Page</a></li>"+
                    "<li><a href='/login1/PhoneDataStoreServlet'>Smart Phones</a></li>"+
                    "<li><a href='/login1/TabletDataStoreServlet'>Tablets</a></li>"+
                    "<li><a href='/login1/LaptopDataStoreServlet'>Laptops</a></li>"+
                    "<li><a href='/login1/TvDataStoreServlet'>TV</a></li>"+
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
					/*
					PLACE ORDER BUTTON, WHEN IT IS CLICKED TOTAL PRICE WILL BE SEND FORWARD TO "PLACE ORDER FROM CART SERVLET"
					*/
					"<form action='/login1/PlaceOrderFromCartServlet' method='get'>"+
                    "<input type='hidden' name='totalprice' value='"+totalPrice+"' />" +
                    "<input style='float:center' style='color:Red' type='submit' value='Place Order' class='submit-button' style='width:250px;'/>" +
                    "</form>"+
					"<div class='clear'></div>"+
					"</div>"+
					"</div>"+
					"</body>"+
					"</html>");	
 

                        
            }
			

    /*
	CALL IS FIRST RECIEVED HERE AND processRequest() METHOD IS CALLED
	*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 
    /*
	CALL IS FIRST RECIEVED HERE AND processRequest() METHOD IS CALLED
	*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}