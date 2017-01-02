<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>

 <% 
        MySqlDataStore.truncateProducts();
        SaxParser.loadProducts(); 
        response.sendRedirect("HomeJsp.jsp");
        %>