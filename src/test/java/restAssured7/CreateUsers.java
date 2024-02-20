package restAssured7;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUsers {

   

    @Test
    void createUsers(ITestContext context) {
    	int id=0;
        Faker faker = new Faker();
        JSONObject obj = new JSONObject();
        obj.put("name", faker.name().fullName());
        obj.put("gender", "Male");
        obj.put("email", faker.internet().emailAddress());
        obj.put("status", "Inactive");
        String token = "9f378887517c46948b9c0e768423abb96c0dcf03b61c7b3bd40af81f74d8ff6d";

        id = given()
                .headers("Authorization", "Bearer " + token) // Corrected the header key to "Authorization"
                .contentType("application/json")
                .body(obj.toString())
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath()
                .getInt("id");
        context.setAttribute("ID", id);
        System.out.println("id is: " + id);
    }
}
