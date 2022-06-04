package restApiXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	
	@Test
	public void test1() {

		RestAssured.given()
		.baseUri("https://chercher.tech/sample/api/books.xml")
		.when()
		.get()
		.then()
		.log()
		.body()
		.statusCode(200);
		
	}
		
		
	@Test
	public void test2() {

	Response response =	RestAssured.given()
		                 .baseUri("https://chercher.tech/sample/api/books.xml")
		                 .when()
		                 .get();
	NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
	
	System.out.println("All the books are" + books.toString());
	System.out.println("First book is " + books.get(0).toString());
	System.out.println("Second book is " + books.get(1).toString());		
	
	System.out.println("Languge of first book is " + books.get(0).getAttribute("lang"));
	System.out.println("Languge of second book is " + books.get(1).getAttribute("lang"));
	
	for(String s:books) {                //(for each loop, we can hardcoded for each book)
		System.out.println(s);
	}
	
	NodeChildrenImpl prices = response.then().extract().path("bookstore.book.price");
	System.out.println("All the price are" + prices.toString());
	System.out.println("First price : paperback is " + prices.get(0).children().get("paperback"));
	//System.out.println("Second price : hardcover is " + prices.get(1).children().get("hardcover"));(it should be null becoz dont have child) 
	
	System.out.println("The price of the second book is " +  prices.get(1).toString());
    
	
	
	}	
		
	
		
}	
	
	
		
		
	

