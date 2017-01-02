import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DeleteProductByAdminServlet extends HttpServlet implements Serializable{
	
	public ServletContext sc;
	
	transient List<Products> productList = new ArrayList<Products>();
	transient List<Products> productList1 = new ArrayList<Products>();
	transient List<Products> productList2 = new ArrayList<Products>();
	transient List<Products> productlist3 = new ArrayList<Products>();

	transient List<Products> tvList = new ArrayList<Products>();
	transient List<Products> laptopList = new ArrayList<Products>();
	transient List<Products> phoneList = new ArrayList<Products>();
	transient List<Products> tabletList = new ArrayList<Products>();

	public String productcategory;
	public String productretailer;
	public String productid;
	public String productname;
	public String productprice;

	public String firstletter;
	public Map<String, List<Products>> prod;
	public HashMap<String, List<Products>> map;

	public String k;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
		sc=request.getSession().getServletContext();
		response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        
        HttpSession session=request.getSession(); 
        String user=((String)session.getAttribute("userid")); 

		/*
		WHEN USER CLICKS ON THE DELETE FROM THE CART THE CORRESPONDING ID WILL BE CAPTURED eg:- p001, b001, l001,t001 and so on  
		*/
		productid = request.getParameter("productDeleteId");
		System.out.println("The product id to be deleted is: "+productid);
		
		/*
		EXTRACT FIRST LETTER OUT OF THE ID CAPTURED IN PREVIOUS STEP eg:- p--p001, b--b001, l--l001,t--t001 and so on  
		*/
		firstletter=String.valueOf(productid.charAt(0));
		System.out.println("The first letter of the id to be deleted is: "+firstletter);
		
		try{
			
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("AllContentFile"))));
			map=(HashMap<String,List<Products>>)ois.readObject();
			ois.close();
			
			for(Map.Entry<String,List<Products>> hm:map.entrySet()){
				k=hm.getKey();
				//pw.println(k);
				if(firstletter.equals("t")){
					productcategory="TV";
				if(k.equals("TV")&&productcategory.equals("TV")){
					tvList=hm.getValue();
					
					for(Products p1:tvList){
						if(p1.getProductId().equals(productid)){
							tvList.remove(p1);
							break;
						}
					}
				}
					
				}
				
				if(firstletter.equals("b")){
					productcategory="Tablet";
				if(k.equals("Tablet")&&productcategory.equals("Tablet")){
					tabletList=hm.getValue();
					
					for(Products p1:tabletList){
						if(p1.getProductId().equals(productid)){
							tabletList.remove(p1);
							break;
						}
					}
				}
					
				}
				
				if(firstletter.equals("p")){
					productcategory="Phone";
				if(k.equals("Phone")&&productcategory.equals("Phone")){
					phoneList=hm.getValue();
					
					for(Products p1:phoneList){
						if(p1.getProductId().equals(productid)){
							phoneList.remove(p1);
							break;
						}
					}
				}
					
				}
				
				if(firstletter.equals("l")){
					productcategory="Laptop";
				if(k.equals("Laptop")&&productcategory.equals("Laptop")){
					laptopList=hm.getValue();
					
					for(Products p1:laptopList){
						if(p1.getProductId().equals(productid)){
							laptopList.remove(p1);
							break;
						}
					}
				}
					
				}
				
			
				
			}
		}
		catch(Exception e){  }

                                        
        try{
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\login1\\AllContentFile")));    
        oos.writeObject(map);
        
        oos.flush();
        oos.close();

    }catch(Exception e){
        System.out.println("Could NOT Write microsoft to writeTvDataStore ...");
    }  
	
	

	pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + "<h1><a href='/login1/LoginServlet'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>"+ 
					"</div>" + 
					"</header>"+
					"<h4>" + "<span style='width:310px;display:inline-block'></span>" + "Your Product Has Been Added" + "</h4>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login1/HomeServlet'>Due to extra security Please login again to see the changes</a>"+
					"</body></html>");
        pw.close(); 
		
		
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