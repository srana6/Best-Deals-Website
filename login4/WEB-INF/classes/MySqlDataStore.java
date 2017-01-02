//package WebAssignment3;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;




public class MySqlDataStore
{
		public static Connection conn = null;
		public static HashMap<String, String> fetchSignupDetails = new HashMap<String,String>();
		public static HashMap<String, String> fetchAdminDetails = new HashMap<String,String>();
		public static HashMap<String, String> fetchSalesDetails = new HashMap<String,String>();
		public static HashMap<String, String> history = new HashMap<String,String>();

		public static Products p = new Products();
		public static HashMap<String, List<Products>> fetchProductsDetails = new HashMap<String,List<Products>>();
		public static List<String> updateOrderHistory;
		public static List<String> updateProductByAdmin;
		
		public static HashMap<String, String> fetchdetails_history;
		public static HashMap<String, List<SalesModel>> fetchdetailsSalesProductDisplay; 
		public static HashMap<String, List<Products>> fetchdetailsAdminProductDisplay; 
		public static HashMap<String, Products> fetchdetailsAjaxProductDisplay; 
		public static SalesModel s=new SalesModel();
		public static List<SalesModel> salesProductDisplayList;
		public static List<Products> adminProductDisplayList;
		
	    public static List<Products> tvList = new ArrayList<Products>();
		public static List<Products> laptopList = new ArrayList<Products>();
		public static List<Products> phoneList = new ArrayList<Products>();
		public static List<Products> tabletList = new ArrayList<Products>();
		public static List<Products> searchResultProductList;
		
		public static int sumTotal;
		public static int totalRow;
		
		public static PreparedStatement pst;
		public static Statement stmt;
		public static ResultSet rs;

		public void getConnection()
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			}
			catch(Exception e)
			{}
		}
		
		/*
		INSERT USER INTO THE DATABASE
		*/

		public static void insertUser(String username,String password,String email){

				try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,userpassword,useremail) "+ "VALUES (?,?,?);";
				pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,username);
				pst.setString(2,password);
				pst.setString(3,email);
				pst.execute();
				}
				catch(Exception e){}
				finally {
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
		}
		
		/*
		INSERT ALL DATA FROM SAXPARSER INTO THE DATABASE
		*/
		public static void insertDataIntoDatabase(String productid,String productname,String productretailer, int productprice, String productimage,String productkey){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				String insertIntoCustomerRegisterQuery = "INSERT INTO productinfo(productid,productname,productretailer,productprice,productimage,productkey) "+ "VALUES (?,?,?,?,?,?);";
				pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,productid);
				pst.setString(2,productname);
				pst.setString(3,productretailer);
				pst.setInt(4,productprice);
				pst.setString(5,productimage);
				pst.setString(6,productkey);
				pst.execute();
				}
				catch(Exception e){}
				finally {
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
				
		}
		
		/*
		SELECTING USER CREDENTIALS DURING LOGIN
		*/
		public static HashMap<String, String> selectUser(){

			try{
				System.out.println("Flow comes in select user");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT username,userpassword FROM registration;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);

			while (rs.next()) {
			    // Get data at cursor
			    fetchSignupDetails.put(rs.getString(1),rs.getString(2));
			}

			}
			catch(Exception e){}
			finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return fetchSignupDetails;
		}
		
		/*
		SELECT ADMIN CREDENTIALS FROM THE DATABASE
		*/
		public static HashMap<String,String> selectAdmin(){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				String adminQuery= "SELECT adminusername,adminpassword FROM admin;";
				stmt=conn.createStatement();
				rs = stmt.executeQuery(adminQuery);
				
				while(rs.next()){
					fetchAdminDetails.put(rs.getString(1),rs.getString(2));
				}
			}
			catch(Exception e){}
			finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return fetchAdminDetails;
		}
		
		/*
		SELECT SALESMAN CREDENTIALS FROM THE DATABASE
		*/
		public static HashMap<String,String> selectSales(){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				System.out.println("Connection Succesful");
				String salesPersonQuery= "SELECT salesusername,salespassword FROM salesperson;";
				 stmt=conn.createStatement();
				
				rs = stmt.executeQuery(salesPersonQuery);
				
				while(rs.next()){
					fetchSalesDetails.put(rs.getString(1),rs.getString(2));
				}
			}
			catch(Exception e){}
			finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return fetchSalesDetails;
		}
		
		/*
		INSERTING PRODUCTS INTO THE CART TABLE
		*/
		
		public static void insertProducts(String key,String id,String name,String retailer,int price,String image){

				try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				String insertIntoCustomerRegisterQuery = "INSERT INTO productsincart(productkey,productid,productname,productretailer,productprice,productimage) "+ "VALUES (?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,key);
				pst.setString(2,id);
				pst.setString(3,name);
				pst.setString(4,retailer);
				pst.setInt(5,price);
				pst.setString(6,image);
				pst.execute();
				}
				catch(Exception e){}
				finally {
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
		}
		
		/*
		FETCHING PRODUCTS FROM CART TABLE TO DISPLAY ON WEBPAGE
		*/
		
		public static HashMap<String, List<Products>> selectProducts(String productId){

			try{
			System.out.println("id in database: "+productId);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productsincart;";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			System.out.println("ResultSet::"+rs);
			
			laptopList = new ArrayList<Products>();
			while (rs.next()) {
				String key="temp";
					p = new Products();
					p.setProductId(rs.getString(2));
					p.setProductName(rs.getString(3));
					p.setProductRetailer(rs.getString(4));
					p.setProductPrice(rs.getInt(5));
					p.setProductImage(rs.getString(6));
					
					laptopList.add(p);
					fetchProductsDetails.put(key,laptopList);
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return fetchProductsDetails;

		

		}
			
		/*
		DELETE PRODUCTS FROM THE CART
		*/		
		public static void deleteProduct(String productId){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			System.out.println("Here1");
			String selectIntoCustomerRegisterQuery = "DELETE FROM productsincart WHERE productid='"+productId+"';";
			 stmt = conn.createStatement();
			System.out.println("Here3"+stmt);
			int rs;
			rs = stmt.executeUpdate(selectIntoCustomerRegisterQuery);
			System.out.println("Here4"+rs);

		}
		catch(Exception e){}
		finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
		}
		
		/*
		DELETE PRODUCT BY ADMIN
		*/
		
		public static void deleteProductByAdmin(String productId){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			System.out.println("Here1");
			String selectIntoCustomerRegisterQuery = "DELETE FROM productinfo WHERE productid='"+productId+"';";
			 stmt = conn.createStatement();
			System.out.println("Here3"+stmt);
			int rs;
			rs = stmt.executeUpdate(selectIntoCustomerRegisterQuery);
			System.out.println("Here4"+rs);

		}
		catch(Exception e){}
		finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
		}
		
		/*
		CALCULATION OF PRICE BEFORE CHECKOUT
		*/
		public static int priceCalculate(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT SUM(productprice) FROM productsincart;";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			rs.next();
			sumTotal=rs.getInt(1);
			System.out.println("The sum is: "+sumTotal);


		}
		catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return sumTotal;
		}
		
		/*
		INSERTING ORDER HISTORY INTO THE DATABASE
		*/
		public static void insertHistoryInDatabase(String orderNumber,String firstName,String lastName,String productPrice,String address,String phoneNumber,String creditCard){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				String insertIntoOrderHistoryQuery= "INSERT INTO orderhistory(orderid,firstname,lastname,price,address,phonenumber,creditcard)"+ "VALUES (?,?,?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoOrderHistoryQuery);
				pst.setString(1,orderNumber);
				pst.setString(2,firstName);
				pst.setString(3,lastName);
				pst.setString(4,productPrice);
				pst.setString(5,address);
				pst.setString(6,phoneNumber);
				pst.setString(7,creditCard);
				pst.execute();
				}
				catch(Exception e){}
				finally {
		    try { pst.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			}
			
			/*
			SHOWING ORDER HISTORY TO THE USER
			*/
			
			public static HashMap<String, String> selectOrderHistory(){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoOrderHistory = "SELECT orderid,price FROM orderhistory;";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoOrderHistory);

			history = new HashMap<String,String>();
			while (rs.next()) {
			  history.put(rs.getString(1),rs.getString(2));
			}

			}
			catch(Exception e){}
			finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return history;
			
		}
		
		/*
		CANCELATION OF PRODUCT BY USER OR SALESMAN
		*/
		public static void cancelProduct(String productId){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoOrderHistory = "DELETE FROM orderhistory WHERE orderid='"+productId+"';";
			 stmt = conn.createStatement();
			int rs;
			rs = stmt.executeUpdate(selectIntoOrderHistory);
		}
		catch(Exception e){}
		finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
		}
		
		/*
		SHOW UPDATED ORDER HISTORY BY USER OR SALESMAN
		*/
			
		public static List<String> selectUpdatedOrderHistory(String productId){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoUpdateOrderQuery = "SELECT * FROM orderhistory WHERE orderid ='"+productId+"';";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoUpdateOrderQuery);
			
			updateOrderHistory = new ArrayList<String>();
			while (rs.next()) {
			    // Get data at cursor
					updateOrderHistory.add(rs.getString(1));
					updateOrderHistory.add(rs.getString(2));
					updateOrderHistory.add(rs.getString(3));
					updateOrderHistory.add(rs.getString(4));
					updateOrderHistory.add(rs.getString(5));
					updateOrderHistory.add(rs.getString(6));
					updateOrderHistory.add(rs.getString(7));
					
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return updateOrderHistory;

	}
	
	/*
	UPDATE PRODUCT INFORMATION BY ADMIN
	*/
	
	public static List<String> productUpdateByAdmin(String productId){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoUpdateOrderQuery = "SELECT * FROM productinfo WHERE productid ='"+productId+"';";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoUpdateOrderQuery);
			
			updateProductByAdmin = new ArrayList<String>();
			while (rs.next()) {
			    // Get data at cursor
					updateProductByAdmin.add(rs.getString(1));
					updateProductByAdmin.add(rs.getString(2));
					updateProductByAdmin.add(rs.getString(3));
					updateProductByAdmin.add(rs.getString(4));
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return updateProductByAdmin;
	}
	
	/*
	UPDATING PRODUCT INFORMATION BY ADMIN INTO THE DATABASE
	*/
	
	public static void updatingProductInfoByAdminInDb(String productId1,String productName1,String productRetailer1,int productPrice1){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			System.out.println(" updateSelectedOrderHistory Here1");
			String updateProductByAdminQuery = "UPDATE productinfo SET productid='"+productId1+"', productname='"+productName1+"', productretailer='"+productRetailer1+"', productprice='"+productPrice1+"' WHERE productid='"+productId1+"';";
			System.out.println("updateSelectedOrderHistory Here2");
			 stmt = conn.createStatement();
			System.out.println("updateSelectedOrderHistory Here3"+stmt);
			int rs;
			rs = stmt.executeUpdate(updateProductByAdminQuery);
			System.out.println("updateSelectedOrderHistory Here4"+rs);

		}
		catch(Exception e){}
		finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	
	/*
	UPDATING PRODUCT INFORMATION BY CLEINT INTO THE DATABASE
	*/
	
	public static void updateOrderHistoryClient(String orderId1,String firstName1,String lastName1,String price1,String address1,String phone1,String creditCard1){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			System.out.println(" updateSelectedOrderHistory Here1");
			String updateOrderHistoryClientQuery = "UPDATE orderhistory SET orderid='"+orderId1+"', firstname='"+firstName1+"', lastname='"+lastName1+"', price='"+price1+"',address='"+address1+"',phonenumber='"+phone1+"',creditcard='"+creditCard1+"' WHERE orderid='"+orderId1+"';";
			System.out.println("updateSelectedOrderHistory Here2");
			 stmt = conn.createStatement();
			System.out.println("updateSelectedOrderHistory Here3"+stmt);
			int rs;
			rs = stmt.executeUpdate(updateOrderHistoryClientQuery);
			System.out.println("updateSelectedOrderHistory Here4"+rs);

		}
		catch(Exception e){}
		finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
	}
	
	/*
	COUNTING THE PRODUCTS IN THE CART
	*/
	public static int countProductsInCart(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCountProductsInCart = "SELECT COUNT(*) FROM productsincart;";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCountProductsInCart);
			rs.next();
			totalRow=rs.getInt(1);
			System.out.println("The total row are: "+totalRow);


		}
		catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
		return totalRow;
		}
		
		/*
		DISPLAYING PRODUCTS AT SALESMAN HOME PAGE
		*/
	public static HashMap<String, List<SalesModel>> salesProductDisplay(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoSalesProductDisplayQuery = "SELECT * FROM orderhistory;";
			//System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoSalesProductDisplayQuery);
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoSalesProductDisplayQuery);
			System.out.println(" salesProductDisplay executed");
			
			fetchdetailsSalesProductDisplay=new HashMap<String, List<SalesModel>>();
			salesProductDisplayList = new ArrayList<SalesModel>();
			while (rs.next()) {
					s = new SalesModel();
					String key="sales";
					s.setOrderId(rs.getString(1));
					s.setFirstName(rs.getString(2));
					s.setLastName(rs.getString(3));
					s.setPrice(rs.getString(4));
					s.setAddress(rs.getString(5));
					s.setPhone(rs.getString(6));
					s.setCreditCard(rs.getString(7));
					
					salesProductDisplayList.add(s);
					fetchdetailsSalesProductDisplay.put(key,salesProductDisplayList);
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return fetchdetailsSalesProductDisplay;
		

	}
	
	/*
	DISPLAYING ADMIN HOME PAGE WITH PRODUCTS
	*/
	
	public static HashMap<String, List<Products>> adminProductDisplay(){

			try{	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoAdminProductDisplayQuery = "SELECT * FROM productinfo;";
			System.out.println(" selectAdminDisplayProducts incoming data:"+selectIntoAdminProductDisplayQuery);
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoAdminProductDisplayQuery);
			System.out.println(" adminProductDisplay executed");
			
			fetchdetailsAdminProductDisplay=new HashMap<String, List<Products>>();
			adminProductDisplayList = new ArrayList<Products>();
			while (rs.next()) {
					p = new Products();
					String key="admin";
					p.setProductId(rs.getString(1));
					p.setProductName(rs.getString(2));
					p.setProductRetailer(rs.getString(3));
					p.setProductPrice(rs.getInt(4));
					//p.setProductImage(rs.getString(5));
					
					adminProductDisplayList.add(p);
					fetchdetailsAdminProductDisplay.put(key,adminProductDisplayList);
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return fetchdetailsAdminProductDisplay;
		

	}
	
	
	/*
	CLEAR CART AFTER USER CHECKOUT WITH THE FINAL ORDER
	*/
		public static void clearProductCart(){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String clearProductCartHistory = "TRUNCATE productsincart;";
			 stmt = conn.createStatement();
			
			
			
			int result;
			result = stmt.executeUpdate(clearProductCartHistory);
			conn.commit();
			

			}
			catch(Exception e){}
			finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			
		}
		
		/*
		DISPLAYING LAPTOP PRODUCTS ON CLICK OF LAPTOP
		*/
		
		public static List<Products> selectLaptopProducts(String k){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			//String selectIntoCustomerRegisterQuery = "SELECT * FROM productsincart;";
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productinfo WHERE productkey='"+k+"';";
			//System.out.println("incoming data:"+selectIntoCustomerRegisterQuery);
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			System.out.println("ResultSet::"+rs);
			
			laptopList = new ArrayList<Products>();
			while (rs.next()) {
				String key="temp";
					p = new Products();
					p.setProductId(rs.getString(1));
					p.setProductName(rs.getString(2));
					p.setProductRetailer(rs.getString(3));
					p.setProductPrice(rs.getInt(4));
					p.setProductImage(rs.getString(5));
					
					laptopList.add(p);
					//fetchProductsDetails.put(laptopList);
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return laptopList;

		}
		
		/*
		DISPLAYING TV PRODUCTS ON CLICK OF TV
		*/
		
		public static List<Products> selectTvProducts(String k){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productinfo WHERE productkey='"+k+"';";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			System.out.println("ResultSet::"+rs);
			
			tvList = new ArrayList<Products>();
			while (rs.next()) {
				String key="temp";
					p = new Products();
					p.setProductId(rs.getString(1));
					p.setProductName(rs.getString(2));
					p.setProductRetailer(rs.getString(3));
					p.setProductPrice(rs.getInt(4));
					p.setProductImage(rs.getString(5));
					
					tvList.add(p);
					//fetchProductsDetails.put(laptopList);
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return tvList;

		}
		
		/*
		DISPLAYING LAPTOP PHONE ON CLICK OF PHONE
		*/
		
		public static List<Products> selectPhoneProducts(String k){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			//String selectIntoCustomerRegisterQuery = "SELECT * FROM productsincart;";
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productinfo WHERE productkey='"+k+"';";
			//System.out.println("incoming data:"+selectIntoCustomerRegisterQuery);
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			System.out.println("ResultSet::"+rs);
			
			phoneList = new ArrayList<Products>();
			while (rs.next()) {
				
					p = new Products();
					p.setProductId(rs.getString(1));
					p.setProductName(rs.getString(2));
					p.setProductRetailer(rs.getString(3));
					p.setProductPrice(rs.getInt(4));
					p.setProductImage(rs.getString(5));
					
					phoneList.add(p);
					//fetchProductsDetails.put(laptopList);
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return phoneList;

		}
		
		/*
		DISPLAYING TABLET PRODUCTS ON CLICK OF TABLET
		*/
		
		public static List<Products> selectTabletProducts(String k){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productinfo WHERE productkey='"+k+"';";
			 stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			System.out.println("ResultSet::"+rs);
			
			tabletList = new ArrayList<Products>();
			while (rs.next()) {
					p = new Products();
					p.setProductId(rs.getString(1));
					p.setProductName(rs.getString(2));
					p.setProductRetailer(rs.getString(3));
					p.setProductPrice(rs.getInt(4));
					p.setProductImage(rs.getString(5));
					
					tabletList.add(p);
				
			}
		}catch(Exception e){}
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			return tabletList;

		}
		
		public static void truncateProducts(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "TRUNCATE productinfo;";
			stmt = conn.createStatement();
			int rs;
			rs = stmt.executeUpdate(selectIntoCustomerRegisterQuery);

		}
		catch(Exception e){}
			finally {		   
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}

		}
		
		public static List<Products> selectResultProduct(String prodId){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productinfo WHERE productid ='"+prodId+"';";
			System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoCustomerRegisterQuery);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			
			searchResultProductList = new ArrayList<Products>();
			while (rs.next()) {
					
					p = new Products();
					p.setProductId(rs.getString(1));
					p.setProductName(rs.getString(2));
					p.setProductRetailer(rs.getString(3));
					p.setProductPrice(rs.getInt(4));
					p.setProductImage(rs.getString(5));
					
					searchResultProductList.add(p);
						
			}
		}catch(Exception e){}

		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}

			return searchResultProductList;


		
}

}
		
