import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class UpdateOrderHistoryServlet extends HttpServlet {
	
	public String username;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {
		HttpSession session=request.getSession(); 
        username=((String)session.getAttribute("userid"));
		
		PrintWriter pw=response.getWriter();
		String orderId1 = request.getParameter("orderId1");
        String firstName1 = request.getParameter("firstName1");
        String lastName1 = request.getParameter("lastName1");
        String price1 = request.getParameter("price1");        
        String address1= request.getParameter("address1");
        String phone1 = request.getParameter("phone1");
        String creditCard1 = request.getParameter("creditCard1");

        
        MySqlDataStore.updateOrderHistoryClient(orderId1,firstName1,lastName1,price1,address1,phone1,creditCard1);

        pw.println("<html>");
        pw.println("<head>");
        pw.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        pw.println("<title>Cart Delete : Best Deal</title>" + "<link rel='stylesheet' href='styles.css' type='text/css' />");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<div id='container'>");
        pw.println("<header>" + "<div class='header_logo'>" + "<img src='images/best_deals.png'/>" + "<h1><a href='#'>BEST <span>DEAL</span></a></h1>");
        pw.println("<h2>new deals everyday</h2>" + "</div>" + "</header>");
		if(username.equals("sales")){
		pw.println("<h4>" + "<span style='width:310px;display:inline-block'></span>" + "Your Order Has Been Updated Successfully" + "</h4>");
        pw.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/login5/SalesHomePageServlet'>Click here to redirect</a>");
		}
		else{
        pw.println("<h4>" + "<span style='width:310px;display:inline-block'></span>" + "Your Order Has Been Updated Successfully" + "</h4>");
        pw.println("<span style='width:310px;display:inline-block'></span>"+"<a href='/login5/OrderHistoryServlet'>Click here to redirect</a>");
		}
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