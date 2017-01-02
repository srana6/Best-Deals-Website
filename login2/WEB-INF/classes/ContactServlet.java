import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ContactServlet extends HttpServlet {
   
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		loadLoginPage(response);
        //processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		loadLoginPage(response);
        //processRequest(request, response);
    }
	
	public void loadLoginPage(HttpServletResponse response) throws ServletException, java.io.IOException{
		response.setContentType("text/html");
		//PrintWriter pw=response.getWriter();
		//response.setContentType("text/html");
        java.io.PrintWriter pw = response.getWriter();
		pw.println(	"<html lang='en'>"+
					"<head>"+
					"<meta charset='utf-8'>"+
					"<title>ContactUS Form</title>"+
					"<link rel='stylesheet' href='css1/styleform.css'>"+
					"</head>"+
					"<body>"+
					"<form action='/login1/LoginServlet' class='contact'>"+
					"<fieldset class='contact-inner'>"+
					"<p class='contact-input'>"+
					"<input type='text' name='name' placeholder='Your Name…' autofocus>"+
					"</p>"+
					"<p class='contact-input'>"+
					"<label for='select' class='select'>"+
					"<select name='subject' id='select'>"+
					"<option value='' selected>Choose Subject…</option>"+
					"<option value='1'>I have a suggestion</option>"+
					"<option value='1'>I found a bug</option>"+
					"<option value='1'>Other</option>"+
					"</select>"+
					"</label>"+
					"</p>"+
					"<p class='contact-input'>"+
					"<textarea name='message' placeholder='Your Message…'></textarea>"+
					"</p>"+
					"<p class='contact-submit'>"+
					"<input type='submit' value='Send Message'>"+
					"</p>"+
					"</fieldset>"+
					"</form>"+
					"</body>"+
					"</html>");
		
		
		
		
	}
}
