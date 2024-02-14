package restAssured2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class CookiesDemo {
	@Test
	void testCookies() {
		given().when().get("https://www.google.com/").then()
				.cookie("AEC", "Ae3NU9Ofi92wSoVoIRKX5neXJ27XKF6iBhXaTV4qPOFZdixYUUkVHovaB-A").log().all();
	}

	@Test
	public void getCookiesInfo() {
		Response res = when().get("https://www.google.com/");

		Map<String, String> cookiesValue = res.getCookies();

		for (String key : cookiesValue.keySet()) {
			System.out.println(key + ":" + cookiesValue.get(key)); // Corrected line to access the value associated with
																	// the key
		}
	}
}
