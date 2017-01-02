package WebAssignment3;
import java.util.ArrayList;
import java.util.*;
import java.io.*;



public class SalesModel implements Serializable{
	public String orderId;
	public  String firstName;
	public String lastName;
	public String price;
	public String address;
	public String phone;
	public String creditCard;


public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

public String getOrderId() {
    	return orderId;
    }

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getFirstName() {
		return firstName;
	}


public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getLastName() {
		return lastName;
	}


public String getPrice() {
		return price;
	}
	
public void setPrice(String price) {
	this.price = price;
}

public String getAddress() {
		return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getPhone() {
		return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getCreditCard() {
		return creditCard;
}

public void setCreditCard(String creditCard) {
	this.creditCard = creditCard;
}

}
