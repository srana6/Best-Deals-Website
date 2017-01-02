import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ActualLogicDeleteFromCartServlet extends HttpServlet implements Serializable{
	transient List<String> delCartList = new ArrayList<String>();
	transient List<String> addToCartList = new ArrayList<String>();
	transient List<String> list = new ArrayList<String>();

	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public HashMap<String, List<Products>> map;
	public HashMap<String, List<String>> hm;
	public HashMap<String, List<String>> mapInFile1;

	public Map<String, List<Products>> prod ;
	public HashMap<String, List<String>> m1 ;
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
		PrintWriter pw= response.getWriter();
		response.setContentType("text/html");
        
        HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
        
		try{
			/*
			READ THE productsInCartFile WHICH HAS PRODUCTS CURRENTLY IN THE CART. AND PUT IT INTO THE HASH MAP
			*/
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("ProductsInCartFile"))));
            hm=(HashMap<String,List<String>>)ois.readObject();
            ois.close();
                                
            for (Map.Entry<String, List<String>> mp : hm.entrySet()) 
                {                   
                    k = mp.getKey();
					System.out.println("The user name corresponding to data to be deleted is: "+username);
                    if(k.equals(username))
                        {
							addToCartList = mp.getValue();
                            break;
                        }                                                 
                }
				for(int i=0;i<addToCartList.size();i++){
					System.out.println("The value corresponding to: "+k+" "+"which are in cart are: "+addToCartList.get(i));
				} 

            }catch(Exception e){  }



            try{
				/*
				READ FILE DeleteProductFromCartServlet WHICH HAS ITEMS TO BE DELETED.
				*/
                ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("DeleteProductFromCartServlet"))));    
                mapInFile1=(HashMap<String,List<String>>)ois.readObject();
                ois.close();
                               
				for (Map.Entry<String, List<String>> mp1 : mapInFile1.entrySet()) 
                    {                   
                        k = mp1.getKey();
                        if(k.equals(username))
                            {
                                delCartList = mp1.getValue();
                                break;
                            }                                                 
                    }
					for(int m=0;m<delCartList.size();m++){
						System.out.println("The products to be deleted corresponding to user: "+k+" are:"+delCartList.get(m));
					}

                }catch(Exception e){  }

				/*
				REMOVE THE ITEMS IN delCartList(ITEMS TO BE DELETED) FROM addToCartList(LIST CONTAINING ITEMS IN THE CART)
				*/
                addToCartList.removeAll(delCartList);
				for(int l=0;l<addToCartList.size();l++){
					System.out.println("The remaining items list is: "+addToCartList.get(l));
				}
				/*
				REMAINING ITEMS
				*/
				int x=5;
				String add="$";
				for(int n=0;n<addToCartList.size();n++){
					String s=addToCartList.get(n);
					
					if(n == x){
						addToCartList.add(x,add);
						//x=x+4;
					}
					
				}
				addToCartList.add("$");
				
				for(int j=0;j<addToCartList.size();j++){
					System.out.println("The remaining items list after adding $ is: "+addToCartList.get(j));
				}
				m1 = new HashMap<String, List<String>>();
                m1.put(username,addToCartList);


        try{
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\login1\\ProductsInCartFile")));     
        oos.writeObject(m1);
        oos.flush();
        oos.close();
        
    
    }catch(Exception e){
        
    }

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
        out.close();   */               


                        
            }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}