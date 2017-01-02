/*
WHEN USER PRESS DELETE FROM THE CART-- DELETEPRODUCTFROMCARTSERVLET IS CALLED
*/
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DeleteProductFromCartServlet extends HttpServlet implements Serializable{
	
	transient List<String> delCartItems = new ArrayList<String>();

	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public HashMap<String, List<Products>> map;
	public HashMap<String, List<String>> hm;

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
	public String productimage;
    public int productprice;
    public String prodpricestr;
	public String productformid;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        sc=request.getSession().getServletContext();
		PrintWriter out= response.getWriter();
		response.setContentType("text/html");
        
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
        prod=new HashMap<String, List<Products>>(); //HASHMAP TYPE PRODUCTS IS DEFINED
        
        try{
			/*
			ALL CONTENT FILE IS RED AND VALUES ARE PUT INTO A HASHMAP
			*/
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("AllContentFile"))));
            map=(HashMap<String,List<Products>>)ois.readObject();
			ois.close();
            /*
			EXTRACT THE ID OF THE PRODUCT TO BE DELETED
			*/
            productformid =request.getParameter("deleteFormId");
			System.out.println("The id of the product to be deleted is: "+productformid);
            /*
			EXTRACT THE FIRST CHARACTER FROM THE ID for eg:- p001--p, l001--l, b001-b, t001-t and so on.
			*/
			firstLetter = String.valueOf(productformid.charAt(0));
			System.out.println("The first character of id to be deleted is: "+firstLetter);
								
			if(firstLetter.equals("l"))
                {
                    productcategory="Laptop";
					if(productcategory.equals("Laptop")){
					laptopList = map.get("Laptop"); //ADD ALL DATA OF LAPTOP INTO LAPTOP LIST
						/*
					TRAVERSING THE LIST GOT FROM LAPTOP
					*/
					
                        for(Products p1 : laptopList)
                            {
								/*
								CHECKING CONDITION THAT WHILE TRAVERSING THE LOOP WHEN IS THE IDs IN PRODUCT LIST IS EQUAL TO ID WE GOT IN productformid
								*/

                                if(p1.getProductId().equals(productformid))
                                    {
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
										
										/*
									  ADD ABOVE VARIABLES INTO THE CARTITEMS LIST DEFINED ABOVE
									  */
                                      
                                        delCartItems.add(productimage);
										delCartItems.add(productname);
                                        delCartItems.add(productretailer);
                                        delCartItems.add(Integer.toString(productprice));
                                        delCartItems.add(productid);
										delCartItems.add("$");
										
										for(int i=0;i<delCartItems.size();i++){
											System.out.println("the item in the cart: "+delCartItems.get(i));
										}
                                     
                                    } 
                                }
								}

                            }

                            else if(firstLetter.equals("t"))
                                {
                                 productcategory = "TV";
                                if(productcategory.equals("TV")){
								tvList = map.get("TV");

                                for(Products p1 : tvList)
                                {

                                    if(p1.getProductId().equals(productformid))
                                    {
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        delCartItems.add(productimage);
										delCartItems.add(productname);
                                        delCartItems.add(productretailer);
                                        delCartItems.add(Integer.toString(productprice));
                                        delCartItems.add(productid);
										delCartItems.add("$");
										for(int i=0;i<delCartItems.size();i++){
											System.out.println("the item in the cart: "+delCartItems.get(i));
										}
                                     
                                    } 
                                }
								}

                            }

                            else if(firstLetter.equals("b"))
                                {
                                 productcategory = "Tablet";
                                if(productcategory.equals("Tablet")){
								tabletList = map.get("Tablet");

                                for(Products p1 : tabletList)
                                {

                                    if(p1.getProductId().equals(productformid))
                                    {
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        delCartItems.add(productimage);
										delCartItems.add(productname);
                                        delCartItems.add(productretailer);
                                        delCartItems.add(Integer.toString(productprice));
                                        delCartItems.add(productid);
										delCartItems.add("$");
										for(int i=0;i<delCartItems.size();i++){
											System.out.println("the item in the cart: "+delCartItems.get(i));
										}
                                     
                                    } 
                                }
								}

                            }


                            else if(firstLetter.equals("p"))
                                {
                                 productcategory = "Phone";
                                if(productcategory.equals("Phone")){
								phoneList = map.get("Phone");

                                for(Products p1 : phoneList)
                                {

                                    if(p1.getProductId().equals(productformid))
                                    {
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        delCartItems.add(productimage);
										delCartItems.add(productname);
                                        delCartItems.add(productretailer);
                                        delCartItems.add(Integer.toString(productprice));
                                        delCartItems.add(productid);
										delCartItems.add("$");
										for(int i=0;i<delCartItems.size();i++){
											System.out.println("the item in the cart: "+delCartItems.get(i));
										}
                                     
                                    } 
                                }
								}

                            }

                    }catch(Exception e){  }
					
					/*
					HASH MAP WHICH WILL STORE ALL THE ITEMS IN THE CART ITEMS LIST WITH USERNAME AS KEY
					*/					
                    hm = new HashMap<String, List<String>>();
                    hm.put(username,delCartItems);
					
					for(Map.Entry<String,List<String>>map:hm.entrySet()){
						String k=map.getKey();
						List<String> exList=new ArrayList<String>();
						exList=map.getValue();
						System.out.println("The key is: "+k+" "+"The value inside the example delete list(cart) is: "+exList);
					}
 
    try{
		/*
		WRITE THE DATA FROM HASHMAP INTO THE SERIALIZED FILE NAMED "DeleteProductFromCartServlet"
		*/
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\login1\\DeleteProductFromCartServlet")));
        oos.writeObject(hm);
        oos.flush();
        oos.close();
        
    
    }catch(Exception e){
        
    }
	response.sendRedirect("/login1/ActualLogicDeleteFromCartServlet");

        /*out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='container'>");
        out.println("<header>" + "<div class='header_logo'>" + "<img src='images/images_logo_new.jpg'/>" + "<h1><a href='#'>BEST <span>DEAL</span></a></h1>");
        out.println("<h2>Your One Stop Shop</h2>" + "</div>" + "</header>");
        out.println("<h4>" + "<span style='width:310px;display:inline-block'></span>" + "Your Cart Has Been Updated" + "</h4>");
        out.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/Assignment1/DeleteProduct'>Click here to redirect</a>");
        out.close();*/        
            
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