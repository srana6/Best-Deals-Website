<%@ page import=" java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
 java.io.*"%>
 
 <%@ page import="WebAssignment3.*"%>

    
	
	
	<html lang='en'>
					<head>
					<meta charset='utf-8'>
					<title>ContactUS Form</title>
					<link rel='stylesheet' href='css1/styleform.css'>
					</head>
					<body>
					<form action='#' class='contact'>
					<fieldset class='contact-inner'>
					<p class='contact-input'>
					<input type='text' name='name' placeholder='Your Name…' autofocus>
					</p>
					<p class='contact-input'>
					<label for='select' class='select'>
					<select name='subject' id='select'>
					<option value='' selected>Choose Subject…</option>
					<option value='1'>I have a suggestion</option>
					<option value='1'>I found a bug</option>
					<option value='1'>Other</option>
					</select>
					</label>
					</p>
					<p class='contact-input'>
					<textarea name='message' placeholder='Your Message…'></textarea>
					</p>
					<p class='contact-submit'>
					<input type='submit' value='Send Message'>
					</p>
					</fieldset>
					</form>
					</body>
					</html>