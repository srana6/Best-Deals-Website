package WebAssignment3;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;



public class SaxParser extends DefaultHandler implements Serializable {
    public Products p;
    public transient List<Products> productList;
	public String productXmlFileName;
	public String elementValueRead;
	public static transient Map<String, List<Products>> m;
	
	public static List<Products> tvList = new ArrayList<Products>();
	public static List<Products> laptopList = new ArrayList<Products>();
	public static List<Products> phoneList = new ArrayList<Products>();
	public static List<Products> tabletList = new ArrayList<Products>();
	
	public static String productId=" ";
	public static String productName=" ";
	public static int productPrice=0;
	public static String productImage=" ";
	public static String productRetailer=" ";
	public static String productKey=" ";

    
   public SaxParser(String productXmlFileName) {
		this.productXmlFileName = productXmlFileName;
		productList = new ArrayList<Products>();
		parseDocument();
}

	public void parseDocument() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(productXmlFileName, this);
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		} catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startElement(String str1, String str2, String elementName,
			Attributes attributes) throws SAXException {
		if (elementName.equalsIgnoreCase("TV")) {
			p = new TV();
			p.setProductId(attributes.getValue(0));
			p.setProductRetailer(attributes.getValue(1));
		}
		if (elementName.equalsIgnoreCase("Laptop")) {
			p = new Laptop();
			p.setProductId(attributes.getValue(0));
			p.setProductRetailer(attributes.getValue(1));
		}
		if (elementName.equalsIgnoreCase("Phone")) {
			p = new Phone();
			p.setProductId(attributes.getValue(0));
			p.setProductRetailer(attributes.getValue(1));
		} 
		if (elementName.equalsIgnoreCase("Tablet")) {
			p = new Tablet();
			p.setProductId(attributes.getValue(0));
			p.setProductRetailer(attributes.getValue(1));
			
		}

	}

	@Override
	public void endElement(String str1, String str2, String element)
			throws SAXException {

		if (element.equalsIgnoreCase("image")) {
			p.setProductImage(elementValueRead);
		}
		if (element.equalsIgnoreCase("name")) {
			p.setProductName(elementValueRead);
		}		
		
		if (element.equalsIgnoreCase("price")) {
			p.setProductPrice(Integer.parseInt(elementValueRead));
		}
		
        if (element.equals("TV") || element.equals("Laptop")
				|| element.equals("Phone") || element.equals("Tablet")) {
			productList.add(p);
		}


	}
	
	public List<Products> getProducts(){
		return productList;
	}

	@Override
	public void characters(char[] content, int begin, int end)
			throws SAXException {
		elementValueRead = new String(content, begin, end);
	}

	public static void loadProducts() {
		m = new HashMap<String, List<Products>>();
		m.put("TV", new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\login2\\TvCatalog.xml").getProducts());
		m.put("Laptop", new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\login2\\LaptopCatalog.xml").getProducts());
		m.put("Phone", new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\login2\\PhoneCatalog.xml").getProducts());
		m.put("Tablet", new SaxParser("C:\\apache-tomcat-7.0.34\\webapps\\login2\\TabletCatalog.xml").getProducts());
		writeDataStore();
		
	
}

public static void writeDataStore(){

    try{
        for(Map.Entry<String, List<Products>> hm : m.entrySet()){
			String key=hm.getKey();
			if(key.equals("Phone")){
				phoneList=hm.getValue();
				productKey="Phone";
				for(Products p1 : phoneList){
					productImage=p1.productImage;
					productId=p1.productId;
                    productName=p1.productName;
                    productRetailer=p1.productRetailer;
                    productPrice=p1.productPrice;
					
					MySqlDataStore.insertDataIntoDatabase(productId,productName,productRetailer, productPrice,productImage,productKey);
				}
			}
			if(key.equals("Laptop")){
				laptopList=hm.getValue();
				productKey="Laptop";
				for(Products p1 : laptopList){
					productImage=p1.productImage;
					productId=p1.productId;
                    productName=p1.productName;
                    productRetailer=p1.productRetailer;
                    productPrice=p1.productPrice;
					
					MySqlDataStore.insertDataIntoDatabase(productId,productName,productRetailer, productPrice,productImage,productKey);
				}
			}
			if(key.equals("Tablet")){
				tabletList=hm.getValue();
				productKey="Tablet";
				for(Products p1 : tabletList){
					productImage=p1.productImage;
					productId=p1.productId;
                    productName=p1.productName;
                    productRetailer=p1.productRetailer;
                    productPrice=p1.productPrice;
					
					MySqlDataStore.insertDataIntoDatabase(productId,productName,productRetailer, productPrice,productImage,productKey);
				}
			}
			if(key.equals("TV")){
				tvList=hm.getValue();
				productKey="TV";
				for(Products p1 : tvList){
					productImage=p1.productImage;
					productId=p1.productId;
                    productName=p1.productName;
                    productRetailer=p1.productRetailer;
                    productPrice=p1.productPrice;
					
					MySqlDataStore.insertDataIntoDatabase(productId,productName,productRetailer, productPrice,productImage,productKey);
				}
			}
			
		}
    
    }catch(Exception e){
        System.out.println("Could NOT Write microsoft to writeTvDataStore ...");
        e.printStackTrace();
    }
}

	/*public static void main (String [] args){

		loadProducts();
	}*/

}