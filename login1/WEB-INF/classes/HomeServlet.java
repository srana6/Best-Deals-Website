/*
 * HomeServlet.java
 *
 */
 
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HomeServlet extends HttpServlet {
   
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		loadLoginPage(response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		loadLoginPage(response);
    }
	
	public void loadLoginPage(HttpServletResponse response) throws ServletException, java.io.IOException{
		response.setContentType("text/html");
        java.io.PrintWriter pw = response.getWriter();
		pw.println("<html><head><meta charset='UTF-8'><title>SignIn/SignUp</title><link rel='stylesheet' href='css/stylesignin.css'></head>");
		pw.println("<body><div id='container1'><header><div id=imageLogo><img src='images/best_deals.png'/><h1><a href='/login1/HomeServlet'>Best<span>Deals</span></a></h1>"+
					"<h2>new deals everyday</h2></div></header></div>");
					
		pw.println("<div class='login-page'>"+
					"<div class='form'>"+
					"<form class='register-form' method='post' action='/login1/SignUpServlet'>"+
					"<input type='text' name='nname' placeholder='name'/>"+
					"<input type='password' name='npassword' placeholder='password'/>"+
					"<input type='text' name='email' placeholder='email address'/>"+
					"<button>Sign Up</button>"+
					"<p class='message'>Already registered? <a href='#'>Sign In</a></p>"+
					"</form>"+
					"<form class='login-form' method='post' action='/login1/LoginServlet'>"+
					"<input type='text' name='username' placeholder='username'/>"+
					"<input type='password' name='password' placeholder='pasword'/>"+
					"<button>Log In</button>"+
					"<p class='message'>Not registered? <a href='#'>Create an account</a></p>"+
					"</form>"+
					"</div>"+
					"</div>"+
					"<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>"+
					"<script src='js/signinup.js'></script>"+
					"</body>"+
					"</html>");
		
		
		
	}
}
