/*
 * IndexServlet.java
 *
 */
 
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class IndexServlet extends HttpServlet {
	
	public void init() throws ServletException{
      	//MySqlDataStore.truncateProducts();
        //SaxParser.loadProducts(); 
        
	}
   
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		loadLoginPage(request,response);
		//SaxParser.loadProducts();
		
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		//loadLoginPage(request,response);
		//SaxParser.loadProducts();
    }
	
	public void loadLoginPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException{
		MySqlDataStore.truncateProducts();
		SaxParser.loadProducts();
		response.sendRedirect("/login5/HomeServlet");
        
		
	}
	
}
