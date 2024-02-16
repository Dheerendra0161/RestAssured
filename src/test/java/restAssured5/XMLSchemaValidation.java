package restAssured5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.*;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class XMLSchemaValidation {

	@Test
	void xmlSchemaValidation() {
		given().when().get("http://restapi.adequateshop.com/api/Traveler").then().assertThat()
				.body(RestAssuredMatchers.matchesXsdInClasspath("traveller.xsd"));
		
		// Visit this site for the schema converter:
		// https://www.liquid-technologies.com/online-xml-to-xsd-converter
		// take the response body of the post request and upload this site it will
		// generate the schema

	}
}
