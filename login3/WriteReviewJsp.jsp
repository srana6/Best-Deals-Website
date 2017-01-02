<%@ page import="java.io.*,
java.util.HashMap,
java.util.Map,
javax.servlet.*,
javax.servlet.http.*,
java.util.*"%>

<%@ page import="WebAssignment3.*"%>


	
	<% String username=" ";
	 String firstLetter=" ";
	 String productCategory=" ";
	

		
	HttpSession s=request.getSession();
	username=(String)s.getAttribute("userid");
		
	String productID=request.getParameter("productFormId");
	String productName=request.getParameter("productFormName");
	String productImage=request.getParameter("productFormImage");
	System.out.println("Image: "+productImage);
	String productRetailer=request.getParameter("productFormRetailer");
	String productPrice=request.getParameter("productFormPrice");
	
		
	response.setContentType("text/html");
		
	PrintWriter pw = response.getWriter();
			
	firstLetter = String.valueOf(productID.charAt(0)); 
			
			if(firstLetter.equals("l"))
            {
                productCategory="Laptop";
				productImage="\'images/lp_03.png\'";
			}
			else if(firstLetter.equals("t")){
				productCategory="TV";
				productImage="\'images/tv_3.jpg\'";
			}
			else if(firstLetter.equals("b")){
				productCategory="Tablet";
				productImage="\'images/tab_03.jpg\'";
			}
			else if(firstLetter.equals("p")){
				productCategory="Phone";
				productImage="\'images/s_03.jpg\'";
			} %>
			
			<html>
					<head>
					<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />
					<title>Welcome to Best Deals</title>
					<link rel='stylesheet' href='styles.css' type='text/css' /> 
					</head>
		
		<body>
					<div id='container'>
					<header>
					<div id=imageLogo>
					<img src='images/best_deals.png'/>
					<h1><a href='/'>Best<span>Deals</span></a></h1>
					<h2>new deals everyday</h2>
					<h5 style='color:blue;float:right' ><a href='SignOutJsp.jsp'>Sign Out</a></h5></br>
					<h3 style='color:blue;float:right;font-style:italic' >Welcome:<%=username%></h3>
					</div>
					</header>
					<div class='menu-wrap'>
					<nav class='menu'>
					<ul class='clearfix'>
					<li><a href='#'>Home</a></li>
					<li>
					<a href='#'>Products <span class='arrow'>&#9660;</span></a>
					<ul class='sub-menu'>
                    <li><a href='PhoneDataStoreJsp.jsp'>Smart Phones</a></li>
                    <li><a href='TabletDataStoreJsp.jsp'>Tablets</a></li>
                    <li><a href='LaptopDataStoreJsp.jsp'>Laptops</a></li>
                    <li><a href='TvDataStoreJsp.jsp'>TV</a></li>
					</ul>
					</li>
					<li class='current-item'><a href='ContactJsp.jsp'>ContactUS</a></li>
					<li>
					<div class='gadget'>
					<form method='get' id='search' action='#'>
					<span>
					<input type='text' value='Search...' id='searchbox' />
					<input type='button' value='Go' id='searchbutton' class='btn'  />
					</span>
					</form>
					</div>
					</li>
					</ul>
					</nav>
					<img class=header-image src='images/combine_images.png' alt='phones' />
					</div>
					<div id='body'>
					<section id='content'>
					<article>
					<h2>Please Write Review</h2>
			
									
			 <h3><%=productName%></h3>
			<form method='get' action='SubmitReviewToMongoJsp.jsp'>
			<fieldset>
			<p><img src=<%=productImage%>/>
			<legend>Product information:</legend>
			
			<table>
			
			<tr>
			<td style='width:30%'> Product Name: </td>
			<td><%=productName%></td>
			</tr>
			
			
			<tr>
			<td style='width:30%'> Product Price: </td>
			<td><%=productPrice%></td>
			</table>
			</fieldset>
			<fieldset>
			<legend>Reviews:</legend>
			
			
		
			
			
			<table>
			<tr>
			<td> Product Model Name: </td>
			<td> <input type='text' name= 'productName' readonly value = <%=productName%> >  </td>
			</tr>
			
		
			
			<td> <input type='hidden' name= 'productID' readonly value = <%=productID%> >  </td>
			
			

			<tr>
			<td> Product Category: </td>
			<td> <input type='text' name= 'productCategory' readonly value = <%=productCategory%> >  </td>
			</tr>
			
			
			<tr>
			<td> Price </td>
			<td> <input type='text' name= 'productPrice' readonly value = <%=productPrice%> >  </td>
			</tr>
			
			
			<tr>
			<td> Retailer Name</td>
			<td> <input type='text' name='productRetailer' readonly value = <%=productRetailer%> >  </td>
			</tr>
			
			
			<tr>
			<td> Retailer Zip</td>
			<td> <input type='number' name= 'retailerzip' >  </td>
			</tr>
			
			
			<tr>
			<td> Retailer City</td>
			<td> <input type='text' name='retailercity' >  </td>
			</tr>
			
		
			<tr>
			<td> Retailer State</td>
			<td> <input type='text' name= 'retailerstate' >  </td>
			</tr>
			
			<tr>
			<td> Product on Sale </td>
			<td>
			<select name='sale'>
			<option value='Yes' selected>Yes</option>
			<option value='No'>No</option>
			</td>
			</tr>
			
			<tr>
			<td> Manufacturer Name </td>
			<td>
			<select name='mfname'>
			<option value='LG' selected>LG</option>
			<option value='Samsung' selected>Samsung</option>
			<option value='Apple' selected>Apple</option>
			<option value='Lenovo' selected>Lenovo</option>
			<option value='HCL'>HCL</option>
			<option value='Sony'>Sony</option>
			<option value='Dell'>Dell</option>
			<option value='Motorola'>Motorola</option>
			<option value='LaptopSellCompany'>LaptopSellCompany</option>
			<option value='TVSellCompany'>TVSellCompany</option>
			<option value='PhoneSellCompany'>PhoneSellCompany</option>
			<option value='OnePlus'>OnePlus</option>
			</td>
			</tr>
			
			<tr>
			<td> Manufacturer Rebate </td>
			<td>
			<select name='rebate'>
			<option value='Yes' selected>Yes</option>
			<option value='No'>No</option>
			</td>
			</tr>
			
			
			<tr>
			<td> User ID </td>
			<td> <input type='text' name= 'userid' readonly value = <%=username%> >  </td>
			</tr>
			
			<tr>
			<td> User Age: </td>
			<td> <input type='text' name='userAge'> </td>
			</tr>
			
			<tr>
			<td> User Gender: </td>
			<td> <input type='text' name='userGender'> </td>
			</tr>
			
			<tr>
			<td> User Occupation: </td>
			<td> <input type='text' name='userOccupation'> </td>
			</tr>
			
			<tr>
			<td> Review Rating: </td>
			<td>
			<select name='reviewRating'>
			<option value='1' selected>1</option>
			<option value='2'>2</option>
			<option value='3'>3</option>
			<option value='4'>4</option>
			<option value='5'>5</option>
			</td>
			</tr>
			
			
			
			
			<tr>
			<td> Review Date: </td>
			<td> <input type='text' name='reviewDate'> </td>
			</tr>
			<tr>
			
			<td> Review Text: </td>
			<td><textarea name='reviewText' rows='4' cols='50'> </textarea></td>
			</tr>
			</table>
			<br><br>
			<input type='submit' value='Submit Review'></input>
			</fieldset>
			</form>
			</article>
					</section>
					<aside class='sidebar'>
					<ul>
					<li>
                    <h4>Products</h4>
                    <ul>
                    <li><a href='#'>Home Page</a></li>
                    <li><a href='PhoneDataStoreJsp.jsp'>Smart Phones</a></li>
                    <li><a href='TabletDataStoreJsp.jsp'>Tablets</a></li>
                    <li><a href='LaptopDataStoreJsp.jsp'>Laptops</a></li>
                    <li><a href='TvDataStoreJsp.jsp'>TV</a></li>
                    <li><a href='TrendingProductJsp.jsp'>Trending</a></li>
                    </ul>
					</li>
					<li>
                    <h4>About us</h4>
                    <ul>
                    <li class='text'>
                    <p style='margin: 0;'>Welcome to Best Deals, Where you will get new deals everyday</p>
                    </li>
                    </ul>
					</li>
					</ul>
					</aside>
					<div class='clear'></div>
					</div>
					</div>
					</body>
					</html>	
	