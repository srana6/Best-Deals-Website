<%@ page import=" java.io.IOException,  
java.io.PrintWriter, 
javax.servlet.ServletException,  
javax.servlet.http.HttpServlet,
javax.servlet.http.HttpServletRequest,
javax.servlet.http.HttpServletResponse,
javax.servlet.http.HttpSession"%>
 
              
    <%response.setHeader("Cache-Control", "no-cache, no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	request.getSession().invalidate();
	response.sendRedirect("HomeJsp.jsp");%> 
    