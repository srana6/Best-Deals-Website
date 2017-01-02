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
		
		public static HashMap<String, String> fetchdetails_history;
		public static HashMap<String, List<SalesModel>> fetchdetailsSalesProductDisplay; 
		public static SalesModel s=new SalesModel();
		public static List<SalesModel> salesProductDisplayList;
		
	    public static List<Products> tvList = new ArrayList<Products>();
		public static List<Products> laptopList = new ArrayList<Products>();
		public static List<Products> phoneList = new ArrayList<Products>();
		public static List<Products> tabletList = new ArrayList<Products>();
		
		public static int sumTotal;
		public static int totalRow;

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

		public static void insertUser(String username,String password,String email){

				try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				System.out.println("Connection Succesful2");
				String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,userpassword,useremail) "+ "VALUES (?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,username);
				pst.setString(2,password);
				pst.setString(3,email);
				pst.execute();
				}
				catch(Exception e){}
		}

		public static HashMap<String, String> selectUser(){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT username,userpassword FROM registration;";
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);

			while (rs.next()) {
			    // Get data at cursor
			    fetchSignupDetails.put(rs.getString(1),rs.getString(2));
			}

			}
			catch(Exception e){}
			return fetchSignupDetails;
		}
		
		public static HashMap<String,String> selectAdmin(){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				System.out.println("Connection Succesful");
				String adminQuery= "SELECT adminusername,adminpassword FROM admin;";
				Statement stmt=conn.createStatement();
				ResultSet rs;
				rs = stmt.executeQuery(adminQuery);
				
				while(rs.next()){
					fetchAdminDetails.put(rs.getString(1),rs.getString(2));
				}
			}
			catch(Exception e){}
			return fetchAdminDetails;
		}
		
		public static HashMap<String,String> selectSales(){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
				System.out.println("Connection Succesful");
				String salesPersonQuery= "SELECT salesusername,salespassword FROM salesperson;";
				Statement stmt=conn.createStatement();
				ResultSet rs;
				rs = stmt.executeQuery(salesPersonQuery);
				
				while(rs.next()){
					fetchSalesDetails.put(rs.getString(1),rs.getString(2));
				}
			}
			catch(Exception e){}
			return fetchSalesDetails;
		}
		
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
		}
		
		public static HashMap<String, List<Products>> selectProducts(String productId){

			try{
			System.out.println("id in database: "+productId);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productsincart;";
			//String selectIntoCustomerRegisterQuery = "SELECT * FROM productsincart WHERE productid='"+productId+"';";
			//System.out.println("incoming data:"+selectIntoCustomerRegisterQuery);
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			System.out.println("ResultSet::"+rs);
			
			laptopList = new ArrayList<Products>();
			while (rs.next()) {
			    // Get data at cursor
				//if(rs.getString(1).equals("Laptop")){
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
			return fetchProductsDetails;

			/*while (rs.next()) {
			    // Get data at cursor
				if(rs.getString(1).equals("Laptop")){
					p = new Laptop();
					p.setProductId(rs.getString(2));
					p.setProductName(rs.getString(3));
					p.setProductRetailer(rs.getString(4));
					p.setProductPrice(rs.getInt(5));
					p.setProductImage(rs.getString(6));
					
					if(laptopList.isEmpty())
					{
						laptopList.add(p);
					}

					else{
					for(Products p2 : laptopList)
					{
						if(!(p2.productId).equals(rs.getString(2)))
						{
							laptopList.add(p);
						}
						else{
							System.out.println("In ELSE Loop::"+rs.getString(2));
							break;
						}
					}
				}
					
					fetchProductsDetails.put(rs.getString(1),laptopList);
				}

				if(rs.getString(1).equals("Tablet")){
					p = new Tablet();
					p.setProductId(rs.getString(2));
					p.setProductName(rs.getString(3));
					p.setProductRetailer(rs.getString(4));
					p.setProductPrice(rs.getInt(5));
					p.setProductImage(rs.getString(6));
					if(tabletList.isEmpty())
					{
						tabletList.add(p);
					}

					else{
					for(Products p2 : tabletList)
					{		
						if(!(p2.productId).equals(rs.getString(2)))
						{
							tabletList.add(p);
						}
						else{
							System.out.println("In ELSE Loop::"+rs.getString(2));
							break;
						}
					}
				}

					fetchProductsDetails.put(rs.getString(1),tabletList);
				}
				if(rs.getString(1).equals("SmartPhone")){
					p = new Phone();
					p.setProductId(rs.getString(2));
					p.setProductName(rs.getString(3));
					p.setProductRetailer(rs.getString(4));
					p.setProductPrice(rs.getInt(5));
					p.setProductImage(rs.getString(6));
					if(phoneList.isEmpty())
					{
						phoneList.add(p);

					}

					
					else{
					for(Products p2 : phoneList)
					{
						System.out.println("In For Loop::");
						if(!(p2.productId).equals(rs.getString(2)))
						{
							System.out.println("In IF Loop::");
							phoneList.add(p);
							break;
						}
						else{
							System.out.println("In ELSE Loop::"+rs.getString(2));
							break;
						}
					}
				}

					fetchProductsDetails.put(rs.getString(1),phoneList);
				}
				if(rs.getString(1).equals("TV")){
					p = new TV();
					p.setProductId(rs.getString(2));
					p.setProductName(rs.getString(3));
					p.setProductRetailer(rs.getString(4));
					p.setProductPrice(rs.getInt(5));
					p.setProductImage(rs.getString(6));
					if(tvList.isEmpty())
					{
						tvList.add(p);
					}

					else{
					for(Products p2 : tvList)
					{
						if(!(p2.productId).equals(rs.getString(2)))
						{
							tvList.add(p);
						}
						else{
							System.out.println("In ELSE Loop::"+rs.getString(2));
							break;
						}
					}
				}
					fetchProductsDetails.put(rs.getString(1),tvList);
			    //fetchdetails_signup.put(rs.getString(1),rs.getString(2));
			}
		}*/

		}
			/*catch(Exception e){}
			return fetchProductsDetails;*/
			
			
		public static void deleteProduct(String productId){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			System.out.println("Here1");
			String selectIntoCustomerRegisterQuery = "DELETE FROM productsincart WHERE productid='"+productId+"';";
			Statement stmt = conn.createStatement();
			System.out.println("Here3"+stmt);
			int rs;
			rs = stmt.executeUpdate(selectIntoCustomerRegisterQuery);
			System.out.println("Here4"+rs);

		}
		catch(Exception e){}
		}
		
		public static int priceCalculate(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT SUM(productprice) FROM productsincart;";
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			rs.next();
			sumTotal=rs.getInt(1);
			System.out.println("The sum is: "+sumTotal);


		}
		catch(Exception e){}
		return sumTotal;
		}
		
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
			}
			
			public static HashMap<String, String> selectOrderHistory(){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoOrderHistory = "SELECT orderid,price FROM orderhistory;";
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(selectIntoOrderHistory);

			history = new HashMap<String,String>();
			while (rs.next()) {
			  history.put(rs.getString(1),rs.getString(2));
			}

			}
			catch(Exception e){}
			return history;
		}
		
		public static void cancelProduct(String productId){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoOrderHistory = "DELETE FROM orderhistory WHERE orderid='"+productId+"';";
			Statement stmt = conn.createStatement();
			int rs;
			rs = stmt.executeUpdate(selectIntoOrderHistory);
		}
		catch(Exception e){}
		}
		
		/*public static HashMap<String, List<OrderHistoryModel>> selectUpdatedOrderHistory(String productId){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT * FROM orderhistory WHERE orderId ='"+productId+"';";
			System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoCustomerRegisterQuery);
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);
			
			selectUpdatedOrderHistory_list = new ArrayList<OrderHistoryModel>();
			while (rs.next()) {
			    // Get data at cursor
				//if(rs.getString(1).equals("Laptop")){
				
					o = new OrderHistoryModel();
					o.setOrderId(rs.getString(1));
					o.setFirstName(rs.getString(2));
					o.setLastName(rs.getString(3));
					o.setPrice(rs.getString(4));
					o.setAddress(rs.getString(5));
					o.setPhone(rs.getString(6));
					o.setCreditCard(rs.getString(7));
					
					selectUpdatedOrderHistory_list.add(o);
					fetchdetails_selectUpdatedOrderHistory.put(rs.getString(1),selectUpdatedOrderHistory_list);
				
			}
		}catch(Exception e){}
			return fetchdetails_selectUpdatedOrderHistory;*/
			
		public static List<String> selectUpdatedOrderHistory(String productId){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoUpdateOrderQuery = "SELECT * FROM orderhistory WHERE orderid ='"+productId+"';";
			//System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoCustomerRegisterQuery);
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(selectIntoUpdateOrderQuery);
			
			updateOrderHistory = new ArrayList<String>();
			while (rs.next()) {
			    // Get data at cursor
				//if(rs.getString(1).equals("Laptop")){
				
					//o = new OrderHistoryModel();
					updateOrderHistory.add(rs.getString(1));
					updateOrderHistory.add(rs.getString(2));
					updateOrderHistory.add(rs.getString(3));
					updateOrderHistory.add(rs.getString(4));
					updateOrderHistory.add(rs.getString(5));
					updateOrderHistory.add(rs.getString(6));
					updateOrderHistory.add(rs.getString(7));
					
					//selectUpdatedOrderHistory_list.add(o);
					//fetchdetails_selectUpdatedOrderHistory.put(rs.getString(1),selectUpdatedOrderHistory_list);
				
			}
		}catch(Exception e){}
			return updateOrderHistory;

	}
	public static void updateOrderHistoryClient(String orderId1,String firstName1,String lastName1,String price1,String address1,String phone1,String creditCard1){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			System.out.println(" updateSelectedOrderHistory Here1");
			String updateOrderHistoryClientQuery = "UPDATE orderhistory SET orderid='"+orderId1+"', firstname='"+firstName1+"', lastname='"+lastName1+"', price='"+price1+"',address='"+address1+"',phonenumber='"+phone1+"',creditcard='"+creditCard1+"' WHERE orderid='"+orderId1+"';";
			System.out.println("updateSelectedOrderHistory Here2");
			Statement stmt = conn.createStatement();
			System.out.println("updateSelectedOrderHistory Here3"+stmt);
			int rs;
			rs = stmt.executeUpdate(updateOrderHistoryClientQuery);
			System.out.println("updateSelectedOrderHistory Here4"+rs);

		}
		catch(Exception e){}
	}
	
	public static int countProductsInCart(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCountProductsInCart = "SELECT COUNT(*) FROM productsincart;";
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(selectIntoCountProductsInCart);
			rs.next();
			totalRow=rs.getInt(1);
			System.out.println("The total row are: "+totalRow);


		}
		catch(Exception e){}
		return totalRow;
		}
		
	public static HashMap<String, List<SalesModel>> salesProductDisplay(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoSalesProductDisplayQuery = "SELECT * FROM orderhistory;";
			//System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoSalesProductDisplayQuery);
			Statement stmt = conn.createStatement();
			ResultSet rs;
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
			return fetchdetailsSalesProductDisplay;
		

	}
		public static void clearProductCart(){

			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String clearProductCartHistory = "TRUNCATE productsincart;";
			Statement stmt = conn.createStatement();
			
			
			
			int result;
			result = stmt.executeUpdate(clearProductCartHistory);
			conn.commit();
			

			}
			catch(Exception e){}
			
		}
}
		
