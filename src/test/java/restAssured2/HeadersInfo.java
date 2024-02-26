package restAssured2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.when;
import java.util.Map;

import org.testng.annotations.Test;

public class HeadersInfo {

	@Test
	public void getHeader() {
		// given().when().get("https://www.google.com/").then().log().all();
		// all() including headers, status code, response body, cookies,headers etc.
		given().when().get("https://www.google.com/").then().log().headers();
	}

	@Test
	public void getHeadersInfo() {
		Response res = when().get("https://www.google.com/");

		Headers headers = res.getHeaders();

		System.out.println(headers);
		System.out.println("*************************************");
		for (Header hd : headers) {
			System.out.println(hd.getName() + ":" + hd.getValue());

		}

	}
}
