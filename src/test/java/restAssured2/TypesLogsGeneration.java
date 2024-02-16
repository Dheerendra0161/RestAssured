	package restAssured2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import org.testng.annotations.Test;

public class TypesLogsGeneration {
	@Test
	void allLogs() {
		given().when().get("https://www.google.com/").then().log().all();
//This logs all details including request, response, headers, status code, etc.
	}

	@Test
	void bodyDetails() {
		given().when().get("https://www.google.com/").then().log().body();

	}

	@Test
	void headersDetails() {
		given().when().get("https://www.google.com/").then().log().headers();
	}

	@Test
	void statusCheck() {
		given().when().get("https://www.google.com/").then().log().status();
	}

	@Test
	void ifValidationfFails() {
		given().when().get("https://www.google.com/").then().log().ifValidationFails();
	}

}
