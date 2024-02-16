package restAssured2;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class ExtractDataTest {

	@Test
	void extractData() {
		Response res = when().get("http://restapi.adequateshop.com/api/TravelAgent");
		String responseBody = res.getBody().asString();
		String title = JsonPath.from(responseBody).getString("agent_location");

		System.out.println("Page title: " + title);
	}
}
