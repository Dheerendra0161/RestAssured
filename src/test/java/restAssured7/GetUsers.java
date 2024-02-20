package restAssured7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUsers {

	@Test
	void getUsers() {
		String token = "9f378887517c46948b9c0e768423abb96c0dcf03b61c7b3bd40af81f74d8ff6d";

		given().header("Authorization", "Bearer " + token)
		.when().get("https://gorest.co.in/public/v2/users")
		.then()
		.statusCode(200) // Assuming 200 is the expected status code
		.body("data.size()", greaterThan(0)); // Assuming you expect at least one user
	}
}
