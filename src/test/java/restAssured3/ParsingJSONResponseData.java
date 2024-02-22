package restAssured3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingJSONResponseData {
	@Test
	void jsonResponse() {
		// Approach1
		given().contentType(io.restassured.http.ContentType.JSON) // Use ContentType.JSON
				.when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.body("data[1].last_name", equalTo("Ferguson")) // json response path https://jsonpathfinder.com/
				.body("data[2].avatar", equalTo("https://reqres.in/img/faces/9-image.jpg"));

		// verify more body response if necessary
	}

	@Test
	void jsonResponse2() {
		// Approach2
		Response response = given().contentType(io.restassured.http.ContentType.JSON) // Use ContentType.JSON
				.when().get("https://reqres.in/api/users?page=2");
		Assert.assertEquals(response.getStatusCode(), 200); // used testNG assertions
		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");

		String lname = response.jsonPath().get("data[1].last_name").toString();
		Assert.assertEquals(lname, "Ferguson");

	}

	@Test
	void jsonResponse3() {
		Response res = given().contentType(io.restassured.http.ContentType.JSON) // Use ContentType.JSON
				.when().get("https://reqres.in/api/users?page=2");

		JSONObject jsonObj = new JSONObject(res.asString()); // Parsing of the JSON response: By using the JSON Object
																// we can parse the data we can also parse xml response

		boolean status = false;
		for (int i = 0; i < jsonObj.getJSONArray("data").length(); i++) {
			String firstName = jsonObj.getJSONArray("data").getJSONObject(i).getString("first_name");

			if (firstName.equals("Rachel")) {
				status = true;
				break;
			}
		}

		if (status == true) {
			System.out.println("firstName found");
		} else {
			System.out.println("firstName Not found");
		}
		Assert.assertEquals(status, true);

	}
}
