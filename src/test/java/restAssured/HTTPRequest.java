package restAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequest {
	int id;

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
		id = given().contentType("application/json")
				.body(data)
				.when().post("https://reqres.in/api/users")
				.jsonPath()
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
