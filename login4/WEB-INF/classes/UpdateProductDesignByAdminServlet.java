import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UpdateProductDesignByAdminServlet extends HttpServlet{

transient List<Products> productList = new ArrayList<Products>();
transient List<Products> productList1 = new ArrayList<Products>();
transient List<Products> productList2 = new ArrayList<Products>();
transient List<Products> productlist3 = new ArrayList<Products>();

transient List<Products> tvList = new ArrayList<Products>();
transient List<Products> laptopList = new ArrayList<Products>();
transient List<Products> phoneList = new ArrayList<Products>();
transient List<Products> tabletList = new ArrayList<Products>();

List<String> prodList=new ArrayList<String>();

public String productcategory;
public String productretailer;
public String productid;
public String productname;
public String productcondition;
public int productprice;
public String firstLetter  = "";

public Products p;
public Map<String, List<Products>> prod;
public HashMap<String, List<Products>> mapInFile;

//transient HashMap<String, List<Products>> ee ;
String key;
int flag;
public ServletContext sc;


protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
        prod=new HashMap<String, List<Products>>();
        sc=request.getSession().getServletContext();
		response.setContentType("text/html");
        PrintWriter pw= response.getWriter();
        HttpSession session=request.getSession(); 
        String user=((String)session.getAttribute("userid"));

								
		String produpdateId =request.getParameter("productUpdateId");//productUpdateID is name of input field where we are taking id input.
                                
        prodList=MySqlDataStore.productUpdateByAdmin(produpdateId);
		
					for(int i=0;i<prodList.size();i++){
						if(i==0){
						productid=prodList.get(i);
						}
						else if(i==1){
						productname=prodList.get(i);
						}
						else if(i==2){
						productretailer=prodList.get(i);
						}
						else if(i==3){
						productprice=Integer.parseInt(prodList.get(i));
						}
		}

                                


        pw.println(	"<html>"+
					"<head>"+
					"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
					"<title>Add Products : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />"+
					"</head><body>"+
					"<div id='container'>"+
					"<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + 
					"<h1><a href='/login4/HomeServlet'>BEST <span>DEAL</span></a></h1>"+
					"<h2>new deals everyday</h2>" + "<span style='width:110px;display:inline-block'></span>" + 
					"<h2 style='float:center','font-size:900%'>Welcome "+ " !</h2>" + "</div>" + "</header>"+  
					"<h5 style='color:blue;float:right' ><a href='/login4/SignOutServlet'>Sign Out</a></h5></br>"+
					"<form method='post' action='/login4/UpdateProductByAdminServlet'><h3>Edit Products</h3><table cellpadding='2' cellspacing='1'>"+
					//"<tr><td>Category</td><td><input type='TEXT' size='15' value='"+productcategory+"' name='productCategory'></input></td></tr>"+       
					"<tr><td>Retailer</td><td><input type='TEXT' size='15' value='"+productretailer+"' name='productRetailer'></input></td></tr>"+
					"<tr><td>Product Id</td><td><input type='TEXT' readonly='true' size='15' value='"+productid+"' name='productId'></input></td></tr>"+
					"<tr><td>Product Name</td><td><input type='text' size='15' value='"+productname+"' name='productName'/></td></tr>"+
					"<tr><td>Price</td><td><input type='TEXT' size='15' value='"+productprice+"' name='productPrice'></input></td></tr>"+
					"<tr><td colspan='2'><center><input type='submit' value='Edit Product' /></center></td></tr>"+
					"</table></form>");
		
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