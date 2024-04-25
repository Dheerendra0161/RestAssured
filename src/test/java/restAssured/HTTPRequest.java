package restAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequest {
	int id;
	/*
	 * given():given part sets up the initial state or context for the test. It
	 * typically includes configuring the request, such as setting headers(contentType,Content-Type), query
	 * parameters, request body, authentication, etc. It represents the
	 * preconditions required before executing the actual HTTP request.
	 * ex:given().contentType("application/json").body(POJOuser)	
	 * 
	 * 
	 * when() performs the HTTP POST request. HTTP Method, Endpoint URL, Request
	 * Body, Request Parameters, Headers, Authentication
	 * ex:when().post("https://reqres.in/api/users")
	 * 
	 * then(): The then part is where you define assertions to verify the response
	 * received from the server. It checks whether the response meets your
	 * expectations. It represents the expected outcomes or post-conditions of the
	 * test.
	 * ex:then().statusCode(201).log().all();
	 */
	/*
	 *
	 given()
    .auth().basic("username", "password") // Authorization
    .param("key1", "value1") // Query parameters
    .param("key2", "value2")
    .header("Content-Type", "application/json") // Headers    Content-Length
    .header("Content-Length", "application/json")
    .header("Authorization", "Basic ZGhlZXJlbmRyYS52aXNod2FrYXJtYUBjaGlhY29uLmNM3FkcjQ4NQ==")
    .body("{\"key\": \"value\"}") // Request body
    
    .when()
    .post("/api/users") // HTTP request action
    
    .then()
    .statusCode(201) // Response status assertion
    .contentType("application/json") // Response content type assertion
    .body("id", equalTo(123)) // Response body assertion
    .time(lessThan(1000)) // Response time assertion
    .header("Server", "Apache") // Response header assertion
    .cookie("sessionId", notNullValue()); // Response cookie assertion
	 
	 * 
	 */
	
	
	
	

	@Test(priority = 1)
	public void getRequest() {
		try {
			given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2))
					.log().all();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void postRequest() {

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "dheerendra");
		data.put("job", "tester");
		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

	}

	@Test(priority = 3, dependsOnMethods = "postRequest")
	public void putRequest() {

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Vikas");
		data.put("job", "Teacher");

		given().contentType("application/json").body(data).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
	}

	@Test(priority = 4, dependsOnMethods = "putRequest")
	public void deleteRequest() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}
}
