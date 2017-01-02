<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>

<html>
<head>
<meta charset='UTF-8'>
<title>SignIn/SignUp</title>
<link rel='stylesheet' href='css/stylesignin.css'>
</head>
<body>
<div id='container1'>
<header>
<div id=imageLogo>
<img src='images/best_deals.png'/>
<h1><a href='HomeJsp.jsp'>Best<span>Deals</span></a></h1>
<h2>new deals everyday</h2></div></header></div>					
<div class='login-page'>
<div class='form'>
<form class='register-form' method='post' action='SignUpJsp.jsp'>
<input type='text' name='nname' placeholder='name'/>
<input type='password' name='npassword' placeholder='password'/>
<input type='text' name='email' placeholder='email address'/>
<button>Sign Up</button>
<p class='message'>Already registered? <a href='#'>Sign In</a></p>
</form>
<form class='login-form' method='post' action='LoginJsp.jsp'>
<input type='text' name='username' placeholder='username'/>
<input type='password' name='password' placeholder='pasword'/>
<button>Log In</button>
<p class='message'>Not registered? <a href='#'>Create an account</a></p>
</form>
</div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='js/signinup.js'></script>
</body>
</html>
					
		