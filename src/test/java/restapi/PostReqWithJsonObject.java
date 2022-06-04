package restapi;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostReqWithJsonObject {

	@Test
	public void test1() {
		RestAssured.baseURI ="http://localhost:3000/employees";
		
		RequestSpecification request = RestAssured.given();
		JSONObject jobj = new JSONObject();
		jobj.put("name", "Greg");
		jobj.put("salary", "3000");
		Response response = request.contentType(ContentType.JSON)
		                          .accept(ContentType.JSON)
		                          .body(jobj.toString())
		                          .post("/create");
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		 
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode,201 );
		                          			
		JsonPath jpath = response.jsonPath();
		System.out.println("id"+jpath.get("id"));
	}
}

