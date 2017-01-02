import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;


public class AutoCompleteServlet extends HttpServlet {

    private ServletContext sc;
    public HashMap<String, List<Products>> composers = new HashMap<String, List<Products>>();
    public List<Products> composers_list = new ArrayList<Products>();
	public PrintWriter pw;
    


   /* @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }*/


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    { 			
				sc=request.getSession().getServletContext();
				pw=response.getWriter();  
                
                StringBuffer sb = new StringBuffer();
                String searchId = request.getParameter("searchId");
				System.out.println("The SearchID is:::"+searchId);
                String action = request.getParameter("action");
				System.out.println("The action is:::"+action);
                

                AjaxUtility a=new AjaxUtility();

                boolean namesAdded = false;
				
                if (searchId != null && !searchId.equals("")) {
                    searchId = searchId.trim().toLowerCase();
                } 
                
                if (action.equals("complete"))
                {
                        if (!searchId.equals(""))
                            { 
                                
                                sb=a.readdata(searchId);
								
                                    if(sb !=null || !sb.equals(""))
                                        {
                                        namesAdded=true;
                                        }
                                if (namesAdded)
                                {
                                System.out.println("Send UTILITYs Servlet:::-->"+sb);
                                response.setContentType("text/xml");
                                response.setHeader("Cache-Control", "no-cache");
                                response.getWriter().write("<products>" + sb.toString() + "</products>");

                                }
                                else {
                                    //nothing to show
                                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
									
                                }
                            }
                }
				

                if (action.equals("lookup")) {

                    

            // put the target composer in the request scope to display 
            if ((searchId != null)) {
                response.sendRedirect("/login4/SearchResultPageServlet?prodid="+searchId);
            }
    }
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
