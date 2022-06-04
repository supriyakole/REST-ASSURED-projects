package apichaining;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {

	
	Response response;
	String BaseURI = "http://localhost:3000/employees";
	
	@Test
	public void test1() {
	
		response = GetMethodAll();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("swati","4000");
		AssertJUnit.assertEquals(response.getStatusCode(), 201);
		
		JsonPath jpath = response.jsonPath();
		int EmpId = jpath.get("id");
		System.out.println("id:- "+EmpId);
		
		response = PutMethod(EmpId, "sanika","5000");
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		jpath = response.jsonPath();
		Assert.assertEquals(jpath.get("name"),"sanika");
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		response = DeleteMethod(EmpId);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		String ResponseBody1 = response.getBody().asString();
        Assert.assertEquals(ResponseBody1, "{}"); 
	
        response = GetMethod(EmpId);
		AssertJUnit.assertEquals(response.getStatusCode(), 404);
		String ResponseBody2 = response.getBody().asString();
        Assert.assertEquals(ResponseBody2, "{}"); 
	
        
	}
	public Response GetMethodAll() {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		 Response response = request.get();
		 return response;
	}

	public Response PostMethod(String Name,String Salary) {	
        RestAssured.baseURI = BaseURI;
		JSONObject jobj = new JSONObject();
		jobj.put("name", "Name");
		jobj.put("salary", "Salary");
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON)
		                          .accept(ContentType.JSON)
		                          .body(jobj.toString())
		                          .post("/create");
		return response;
	}
	public Response PutMethod( int EmpID,String Name,String Salary) {	
        RestAssured.baseURI = BaseURI;
		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
		                          .accept(ContentType.JSON)
		                          .body(jobj.toString())
		                          .put("/" + EmpID);
		return response;
	}
	
	public Response DeleteMethod( int EmpID){
	RestAssured.baseURI ="http://localhost:3000/employees";
	RequestSpecification request = RestAssured.given();
	 Response response = request.delete("/" +EmpID);
	
	return response;
	}
	
	public Response GetMethod( int EmpID){
		RestAssured.baseURI ="http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		 Response response = request.get("/" +EmpID);
		
		return response;
	
	
	
	}	

}	 	 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
		
	

