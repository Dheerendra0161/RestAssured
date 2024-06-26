package restAssured7;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;

public class UpdateUsers {
	//ITestContext is an interface that represents the context of a test run. 
//	It provides information about the current test run, including details about 
//	the test suite, test methods, test parameters, and test results. The ITestContext 
//	interface allows you to access this information and perform various actions or 
//	assertions based on the test context.
	
    @Test
    void updateUsers(ITestContext context) {
        Faker faker = new Faker();
        JSONObject obj = new JSONObject();
        obj.put("name", faker.name().fullName());
        obj.put("gender", "Female");
        obj.put("email", faker.internet().emailAddress());
        obj.put("status", "Active");
        String token = "9f378887517c46948b9c0e768423abb96c0dcf03b61c7b3bd40af81f74d8ff6d";
        int id = Integer.parseInt(context.getAttribute("ID").toString()); // Corrected the way of getting attribute value

        given()
            .headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .pathParam("ID", id) // Corrected the parameter name
            .body(obj.toString()) // Added the request body
        .when()
            .put("https://gorest.co.in/public/v2/users/{ID}")
        .then()
            .statusCode(200)
            .log().all();
    }
}
//Note run it from the testng.xml file
