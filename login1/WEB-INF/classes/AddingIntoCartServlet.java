/*
USER CLICKS ON ADD TO CART AND ADDING INTO CART SERVLET IS CALLED
*/
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AddingIntoCartServlet extends HttpServlet implements Serializable{

	transient List<String> cartItems = new ArrayList<String>();

	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public HashMap<String, List<Products>> mapInFile;
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
	
	public String productformid; //WHEN USER CLICKS ON THE PRODUCT AT PHONE,TABLET,LAPTOP,&TV PAGE THEIR CORRESPONDING ID WILL BE CAPTURED 
	
	public static int counter=0;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        sc=request.getSession().getServletContext();
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
        prod=new HashMap<String, List<Products>>(); //PRODUCT TYPE HASH MAP IS DEFINED
		
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
 
        try{
		/*
		ALL DATA FROM ALL CONTENT FILE IS RED AND COMES INTO HASH MAP NAME-MAP
		*/
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("AllContentFile"))));
        mapInFile=(HashMap<String,List<Products>>)ois.readObject();
		ois.close();
        /*
		WHEN USER CLICKS ON THE PRODUCT AT PHONE,TABLET,LAPTOP,&TV PAGE THEIR CORRESPONDING ID WILL BE CAPTURED eg:- p001, b001, l001,t001 and so on  
		*/		
        productformid =request.getParameter("productFormId");
		System.out.println("The product is clicked and the productId is: "+ productformid);
        
		/*
		EXTRACT FIRST LETTER OUT OF THE ID CAPTURED IN PREVIOUS STEP eg:- p--p001, b--b001, l--l001,t--t001 and so on  
		*/
		
		firstLetter = String.valueOf(productformid.charAt(0)); 
		System.out.println("The first letter of the extracted product id is: "+firstLetter);
		
		if(firstLetter.equals("l"))
            {
                productcategory="Laptop";
				if(productcategory.equals("Laptop")){
					laptopList = mapInFile.get("Laptop");
					
					System.out.println("Yes we get laptop list here: "+laptopList);
					
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
                                        cartItems.add(productimage);
										cartItems.add(productname);
                                        cartItems.add(productretailer);
                                        cartItems.add(Integer.toString(productprice));
                                        cartItems.add(productid);
                                        cartItems.add("$");
										
										for(int i=0;i<cartItems.size();i++){
											System.out.println("the item in the cart: "+cartItems.get(i));
										}
                                     
                                } 
                                }
								}

                            }

                            else if(firstLetter.equals("t"))
                                {
                                productcategory = "TV";
                                if(productcategory.equals("TV")){
								tvList = mapInFile.get("TV");

                                for(Products p1 : tvList)
                                {

                                    if(p1.getProductId().equals(productformid))
                                    {
										
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        cartItems.add(productimage);
										cartItems.add(productname);
                                        cartItems.add(productretailer);
                                        cartItems.add(Integer.toString(productprice));
                                        cartItems.add(productid);
                                        cartItems.add("$");
										for(int i=0;i<cartItems.size();i++){
											System.out.println("the item in the cart: "+cartItems.get(i));
										}
                                     
                                    } 
                                }
								}

                            }

                            else if(firstLetter.equals("b"))
                                {
                                 productcategory = "Tablet";
                                if(productcategory.equals("Tablet")){
								tabletList = mapInFile.get("Tablet");

                                for(Products p1 : tabletList)
                                {
									
                                    if(p1.getProductId().equals(productformid))
                                    {
										
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        cartItems.add(productimage);
										cartItems.add(productname);
                                        cartItems.add(productretailer);
                                        cartItems.add(Integer.toString(productprice));
                                        cartItems.add(productid);
                                        cartItems.add("$");
										for(int i=0;i<cartItems.size();i++){
											System.out.println("the item in the cart: "+cartItems.get(i));
										}
                                     
                                    } 
                                }
								}

                            }


                            else if(firstLetter.equals("p"))
                                {
                                 productcategory = "Phone";
                                if(productcategory.equals("Phone")){
								phoneList = mapInFile.get("Phone");

                                for(Products p1 : phoneList)
                                {

                                    if(p1.getProductId().equals(productformid))
                                    {
										
                                        productid = p1.getProductId();
                                        productname = p1.getProductName();
                                        productretailer = p1.getProductRetailer();
										productimage=p1.getProductImage();
                                        productprice= p1.getProductPrice();
                                      
                                        cartItems.add(productimage);
										cartItems.add(productname);
                                        cartItems.add(productretailer);
                                        cartItems.add(Integer.toString(productprice));
                                        cartItems.add(productid);
                                        cartItems.add("$");
										for(int i=0;i<cartItems.size();i++){
											System.out.println("the item in the cart: "+cartItems.get(i));
										}
                                     
                                    } 
                                }
								}

                            }

                    }catch(Exception e){  }
					
					/*
					HASH MAP WHICH WILL STORE ALL THE ITEMS IN THE CART ITEMS LIST
					*/
                    hm = new HashMap<String, List<String>>();
                    hm.put(username,cartItems);
					
					for(Map.Entry<String,List<String>>map:hm.entrySet()){
						String k=map.getKey();
						List<String> exList=new ArrayList<String>();
						exList=map.getValue();
						System.out.println("The key is: "+k+" "+"The value inside the example list(cart) is: "+exList);
					}
					
    

    try{
		/*
		WRITE THE DATA FROM HASHMAP INTO THE SERIALIZED FILE NAMED "PRODUCTS IN CART FILE"
		*/
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\login1\\ProductsInCartFile")));    
        oos.writeObject(hm);
        oos.flush();
        oos.close();
    
    }catch(Exception e){
    }
	/*
	INTENT WILL BE SEND TO PRODUCT CART SERVLET
	*/
	response.sendRedirect("/login1/ProductCartServlet");
	

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
        out.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/Assignment1/CartProductDisplay'>Click here to redirect</a>");
        out.close(); */       
            
    }


/*
CALL FROM PHONE,TABLET,LAPTOP,&TV SERVLET WILL COME HERE AND PROCESS REQUEST METHOD IS CALLED
*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}