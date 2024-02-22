package restAssured4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;

public class ParsingXMLResponse {
	@Test
	void xmlResponse() {
		// Approach1
		// without storing the response in the variable
		given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1").then()
				.headers("Content-Type", "application/xml; charset=utf-8").statusCode(200)
				.body("TravelerinformationResponse.page", equalTo("1"))
				.body("TravelerinformationResponse.travelers.Travelerinformation[0].email",
						equalTo("Developer12@gmail.com"));

		// while creating the xpath we use here dot(".")

	}

	@Test
	void xmlResponse1() {
		// Approach2
		Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		Assert.assertEquals(res.statusCode(), 200);
		String emailID = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].email")
				.toString();
		Assert.assertEquals(emailID, "Developer12@gmail.com");
	}

	@Test
	void xmlResponseBody() {

		Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
		XmlPath xmlObj = new XmlPath(res.asString()); // convert res object into string format
		
		// If we want to use entire response data as string then we have to use the
		// asString() method
		// If we want to use only response data into string format then use toString()
		// method.
		// toString() is used to get a string representation of the Response object
		// itself, while asString() is used to get the response body as a string.
		List<String> travellers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");

		Assert.assertEquals(travellers.size(), 10);

		// Verify travelers name is present in the response
		List<String> TravelersEmails = xmlObj
				.getList("TravelerinformationResponse.travelers.Travelerinformation.email");
		boolean emailStatus = false;
		for (String travelerMail : TravelersEmails) {

			if (travelerMail.equals("ashot.vardanyan.2000@gmail.com")) {
				emailStatus = true;
				break;
			}
		}

		if (emailStatus == true) {
			System.out.println("This email is present in the response");
		} else {
			System.out.println("Email not present in the response");
		}
		Assert.assertEquals(emailStatus, true);

	}

}
