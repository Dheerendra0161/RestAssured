package restAssured1;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DifferentWaysToCreatePostRequestBody {
	@Test
	public void postRequestWithHashMap() {
		// Create a HashMap to represent the request body*******************************
		HashMap<String, Object> requestBody = new HashMap<>();
		requestBody.put("name", "John");
		requestBody.put("age", 30);
		requestBody.put("email", "john@gmail.com");
		given().contentType("application/json").body(requestBody).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	public void postRequestWithPOJO() {
		// Create a User object to represent the request body************************
		POJO_UserPostRequest POJOuser = new POJO_UserPostRequest();
		POJOuser.setName("Vikas");
		POJOuser.setAge(30);
		POJOuser.setEmail("vikas@gmail.com");
		POJOuser.setHobbies(Arrays.asList("Reading", "Gaming", "Traveling"));
		// Send a POST request with the User object as the request body
		given().contentType("application/json").body(POJOuser).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	public void postRequestWithArrayInJSON() {
		// (org.json) Create a JSON object to represent the request body***************
		JsonObject requestBodyJson = new JsonObject();
		requestBodyJson.addProperty("name", "dheerendra");
		requestBodyJson.addProperty("age", 30);
		requestBodyJson.addProperty("email", "dheerendra@gmail.com");

		// Create an array and add elements to it
		JsonArray hobbiesArray = new JsonArray();
		hobbiesArray.add("Reading");
		hobbiesArray.add("Gaming");
		hobbiesArray.add("Traveling");

		// Add the array to the JSON object
		requestBodyJson.add("hobbies", hobbiesArray);

		// Send a POST request with the JSON object as the request body
		given().contentType("application/json").body(requestBodyJson.toString()).when()
				.post("https://reqres.in/api/users").then().statusCode(201).log().all();
	}

	@Test
	public void postRequestUsingExternalJSONFile() {
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\restAssured1\\" + "Body.json";
			// Create a FileReader to read the JSON file
			FileReader fileReader = new FileReader(filePath);

			// Create a JsonTokener to parse the JSON file contents.Parsing JSON file
			// contents refers to the process of extracting data from a JSON (JavaScript
			// Object Notation) file. This process involves reading the JSON file,
			// interpreting its structure, and extracting specific data elements such as
			// values, arrays, or objects.
			JSONTokener jsonTokener = new JSONTokener(fileReader);
			
			// Create a JSONObject by passing the JsonTokener
			JSONObject jsonObject = new JSONObject(jsonTokener);

			// Optionally, you can retrieve individual values or arrays from the JSONObject
			String name = jsonObject.getString("name");
			int age = jsonObject.getInt("age");
			String email = jsonObject.getString("email");
			JSONArray hobbies = jsonObject.getJSONArray("hobbies");

			// Send a POST request with the JSONObject as the request body
			given().contentType("application/json").body(jsonObject.toString()).when()
					.post("https://reqres.in/api/users").then().statusCode(201).log().all();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
