package restAssured6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;

public class Authentication {
	// Note: BasicAuthentication,DigestAuthentication and PreemptiveAuthentication
	// are required userID and Password ,the only difference is internal algorithm
	// is different
	@Test
	void BasicAuthentication() {
		given().auth().basic("Dheerendra0161", "Marikpur@1").when()
				.get("https://api.github.com/repos/Dheerendra0161/Amazon-automation").then().statusCode(200)
				.body("full_name", equalTo("Dheerendra0161/Amazon-automation")).log().all();
	}

	@Test
	void DigestAuthentication() {
		given().auth().digest("Dheerendra0161", "Marikpur@1").when()
				.get("https://api.github.com/repos/Dheerendra0161/Amazon-automation").then().statusCode(200).log()
				.all();
	}

	@Test
	void PreemptiveAuthentication() {
		given().auth().preemptive().basic("Dheerendra0161", "Marikpur@1").when()
				.get("https://api.github.com/repos/Dheerendra0161/PageObjectModelNew").then().statusCode(200).log()
				.all();
	}

	@Test
	void BearerTockenAuthentication() {
		given().headers("Authorization", "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") // see the postman headers for
																						// token
				.when().get("https://api.github.com/repos/Dheerendra0161/PageObjectModelNew").then().statusCode(200)
				.log().all();
	}

	@Test
	void OAuthAuthentication1O() {

		// For OAuth developer will give these details to access the API
		final String CONSUMER_KEY = "your_consumer_key";
		final String CONSUMER_SECRET = "your_consumer_secret";
		final String TOKEN = "your_token";
		final String TOKEN_SECRET = "your_token_secret";

		given().auth().oauth(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET).when()
				.get("https://api.github.com/repos/Dheerendra0161/PageObjectModelNew").then().statusCode(200).log()
				.all();

	}

	@Test
	void APIkeyAuthentication() {

		// For OAuth developer will give these details to access the API
		final String TOKEN = "265caa6ec34ed60e7f355ac18f487f09";
		given().queryParam("appid", TOKEN).when()
				.get("api.openweathermap.org/data/3.0/onecall?lat=30.489772&lon=-99.771335").then().statusCode(200)
				.log().all();
	}

}
