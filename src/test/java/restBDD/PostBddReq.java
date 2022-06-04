package restBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostBddReq {

	@Test
	public void test1() {
		
		Map<String,Object> MapObj = new HashMap<String,Object>();
		MapObj.put("name", "swati");
		MapObj.put("salary", "7000");
		
		RestAssured.given()
		                      .baseUri("http://localhost:3000/employees")
		                      .contentType(ContentType.JSON)
		                      .accept(ContentType.JSON)
		                      .body(MapObj)
		                      .when()
		                      .post("/create")
		                      .then()
		                      .log()
		                      .body()
		                      .statusCode(201)
		                      .body("name", Matchers.equalTo("swati"));
	}
	
	
	
	
	
	
	
	
	
}	
		
		
		
		
	
	
	

