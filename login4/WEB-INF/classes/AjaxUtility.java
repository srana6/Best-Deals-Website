import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;



public class AjaxUtility implements Serializable{


	public  Connection conn = null;
	public  PreparedStatement pst;
	public  ResultSet rs;
	public  Statement stmt;
	HashMap<String,Products> data;
	HashMap<String,Products> selectAllProducts_hash;
	StringBuffer sb = new StringBuffer();




public HashMap<String,Products> getData(){

			try{
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeals","root","root");
			String selectIntoCustomerRegisterQuery = "SELECT * FROM productinfo;";
			System.out.println(" selectUpdatedOrderHistory incoming data:"+selectIntoCustomerRegisterQuery);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(selectIntoCustomerRegisterQuery);

			
			
			selectAllProducts_hash = new HashMap<String,Products>();
			
			
			while (rs.next()) {


					Products p = new Products(rs.getString(1), rs.getString(2));
					
					selectAllProducts_hash.put(rs.getString(1),p);
						
			}
		}catch(Exception e){}

		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
			}

			return selectAllProducts_hash;
		

	}


public StringBuffer readdata(String searchId)
{

	data = new HashMap<String,Products>();

	data=getData();


	Iterator it = data.entrySet().iterator();
	while (it.hasNext())
	{
	Map.Entry pi = (Map.Entry)it.next();
	Products p=(Products)pi.getValue();



	if (p.getProductName().toLowerCase().contains(searchId))
	{
		sb.append("<product>");
		sb.append("<id>" + p.getProductId() + "</id>");
		sb.append("<productName>" + p.getProductName() + "</productName>");
		sb.append("</product>");
	}
	}

return sb;
	}
	}
