package restAssured7;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUsers {

    @Test
    void deleteUsers(ITestContext context) {
        String token = "9f378887517c46948b9c0e768423abb96c0dcf03b61c7b3bd40af81f74d8ff6d";
        int id = Integer.parseInt(context.getAttribute("ID").toString()); // Corrected the way of getting attribute value

        given()
            .header("Authorization", "Bearer " + token)
            .pathParam("ID", id) // Corrected the parameter name
        .when()
            .delete("https://gorest.co.in/public/v2/users/{ID}")
        .then()
            .statusCode(204)
            .log().all();
    }
}
