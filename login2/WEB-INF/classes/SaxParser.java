
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
    Products p;
    transient List<Products> productList;
	String productXmlFileName;
	String elementValueRead;
	static transient Map<String, List<Products>> m;

    
   public SaxParser(String productXmlFileName) {
		this.productXmlFileName = productXmlFileName;
		productList = new ArrayList<Products>();
		parseDocument();
}

	private void parseDocument() {
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
		/*if (element.equalsIgnoreCase("condition")) {
			p.setCondition(elementValueRead);
		}*/
		if (element.equalsIgnoreCase("price")) {
			p.setProductPrice(Integer.parseInt(elementValueRead));
		}
		/*if(element.equalsIgnoreCase("accessory")){
           p.getAccessories().add(elementValueRead);
        }*/
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
	
}


	/*public static void main (String [] args){

		loadProducts();
	}*/

}