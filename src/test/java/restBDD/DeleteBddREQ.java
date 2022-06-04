package restBDD;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteBddREQ {
	@Test
	public void test1() {
		
		RestAssured.given()
		.baseUri("http://localhost:3000/employees")
		.when()
		.delete("/5")
		.then()
		.log()
		.body()
		.statusCode(200);
		
}
}