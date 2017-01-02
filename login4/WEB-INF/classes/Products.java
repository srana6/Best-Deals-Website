import java.util.*;
import java.io.*;

public class Products implements java.io.Serializable{
	public String productId;
	public String productName;
	public int productPrice;
	public String productImage;
	public String productRetailer;
	
	public Products(){
		
	}
	public Products (String productId, String productName) {
        this.productId = productId;
        this.productName = productName;       
	}
	
	//Encapsulation, if I make my variables as Private then I can't update them, only way to update them is my public setter and getter.
	public String getProductId(){
		return productId;
	}
	public void setProductId(String pId){
		productId=pId;
	}
	
	public String getProductName(){
		return productName;
	}
	public void setProductName(String pName){
		productName=pName;
	}
	public int getProductPrice(){
		return productPrice;
	}
	public void setProductPrice(int pPrice){
		productPrice=pPrice;
	}
	public String getProductImage(){
		return productImage;
	}
	public void setProductImage(String pImage){
		productImage=pImage;
	}
	public String getProductRetailer(){
		return productRetailer;
	}
	public void setProductRetailer(String pRetailer){
		productRetailer=pRetailer;
	}
}