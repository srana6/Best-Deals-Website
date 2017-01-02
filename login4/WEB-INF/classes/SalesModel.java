import java.util.ArrayList;
import java.util.*;
import java.io.*;


public class SalesModel implements Serializable{
    String orderId;
    String firstName;
    String lastName;
    String price;
    String address;
	String phone;
    String creditCard;


public void setOrderId(String orderId) {
    	this.orderId = orderId;
    }

public String getOrderId() {
    	return orderId;
    }

void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getFirstName() {
		return firstName;
	}


void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getLastName() {
		return lastName;
	}


public String getPrice() {
		return price;
	}
	
void setPrice(String price) {
	this.price = price;
}

public String getAddress() {
		return address;
}

void setAddress(String address) {
	this.address = address;
}

public String getPhone() {
		return phone;
}

void setPhone(String phone) {
	this.phone = phone;
}

public String getCreditCard() {
		return creditCard;
}

void setCreditCard(String creditCard) {
	this.creditCard = creditCard;
}

}
