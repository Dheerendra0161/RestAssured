package restAssured5;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

//  Visit this site to get schema:  https://jsonformatter.org/json-to-jsonschema 
// To get the JSON and xml response: http://restapi.adequateshop.com/swagger/ui/index
//Note: Create file in resources and the file must be with json extension.

public class JSONSchemaValidator {
	@Test
	public void jsonSchemaValidator() {
		 given()
		.when()
		.get("http://restapi.adequateshop.com/api/Tourist")
		.then()
		.assertThat()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		// Converted JSON which is written in schema.json file in resources package
	}

	@Test
	public void xmlSchemaValidator() {

		// Perform the request and validate the response against the XSD schema
		 given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler")
		.then()
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveller.xsd")); // Provide the XSD file name or path
	}

}
