import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddProductByAdminServlet extends HttpServlet implements Serializable{

public HashMap<String, List<Products>> mapInFile;

transient List<Products> tvList = new ArrayList<Products>();
transient List<Products> laptopList = new ArrayList<Products>();
transient List<Products> phoneList = new ArrayList<Products>();
transient List<Products> tabletList = new ArrayList<Products>();

transient List<Products> productList = new ArrayList<Products>();
transient List<Products> productList1 = new ArrayList<Products>();
transient List<Products> productList2 = new ArrayList<Products>();
transient List<Products> productList3 = new ArrayList<Products>();

transient HashMap<String, List<Products>> hm ;
public String k;
public ServletContext sc;

public String productcategory;
public String productretailer;
public String productid;
public String productname;        
public String productprice;
public String productimage;

public Products p;


protected void completeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException 
	{
		sc=request.getSession().getServletContext();
        Map<String, List<Products>> prod=new HashMap<String, List<Products>>();
        response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        HttpSession session=request.getSession(); 
        String user=((String)session.getAttribute("userid")); 


        productcategory = request.getParameter("productCategory");
        productretailer = request.getParameter("productRetailer");
        productid = request.getParameter("productId");
        productname = request.getParameter("productName");        
        productprice = request.getParameter("productPrice");
		productimage=request.getParameter("productImage");

        if(productcategory.equals("TV"))
        {
            p = new TV();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
            productList.add(p);
        }


        else if(productcategory.equals("Laptop"))
        {   
   
            p = new Laptop();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
            productList1.add(p);
        }



        else if(productcategory.equals("Tablet"))
        {
            
            p = new Tablet();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
            productList2.add(p);
        }



        else if(productcategory.equals("SmartPhone"))
        {
           
            p = new Phone();

            p.setProductRetailer(productretailer);
            p.setProductId(productid);
            p.setProductName(productname);
            p.setProductPrice(Integer.parseInt(productprice));
			p.setProductImage(productimage);
            productList3.add(p);
        } 

                            try{
                                sc=request.getSession().getServletContext();    
                              
								ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File(sc.getRealPath("AllContentFile"))));
								
                                mapInFile=(HashMap<String,List<Products>>)ois.readObject();
								
                                ois.close();

                                for (Map.Entry<String, List<Products>> hm : mapInFile.entrySet()) 
                                {                   
                                                    
                                                    k = hm.getKey();
                                                 
                                                    if(k.equals("TV")){
                                                    
                                                    tvList = hm.getValue();                                                    
                                                    tvList.addAll(productList);
                                                    
                                                    }
                                                    else if(k.equals("Laptop")){
                                                    
                                                    laptopList = hm.getValue();                                                    
                                                    laptopList.addAll(productList1);
                                                
                                                    }
													
													else if(k.equals("Tablet")){
                                                   
                                                    tabletList = hm.getValue();                                                    
                                                    tabletList.addAll(productList2);  
                                                   
                                                    }

                                                    else if(k.equals("Phone")){
                                                    
                                                    phoneList = hm.getValue();                                                    
                                                    phoneList.addAll(productList3);  
                                                 
                                                    }   

                                            }
                                        }catch(Exception e){  }


                                        try{
        
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("C:\\apache-tomcat-7.0.34\\webapps\\login1\\AllContentFile"))); 
        oos.writeObject(mapInFile);
        
        oos.flush();
        oos.close();
        

    }catch(Exception e){
       
    }                                           


        pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Product Added : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head>"+
					"<body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='#'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "</div>" + "</header>"+
					"<h3>" + "<span style='width:310px;display:inline-block'></span>" + "Your Product Has Been Added" + "</h3>"+
					"<span style='width:310px;display:inline-block'></span>"+"<a href='/login1/HomeServlet'>Due to Extra Security Reasons click here to see the changes</a>");  
					
					pw.close();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        completeRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        completeRequest(request, response);
    }
}