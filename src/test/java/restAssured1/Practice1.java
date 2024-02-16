package restAssured1;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class Practice1 {
	@Test
	void withHashMap() {
		HashMap<String, Object> requestBody = new HashMap<>();
		requestBody.put("name", "Dheerendra");
		requestBody.put("age", 30);
		requestBody.put("email", "dheerendra021@gmail.com");
		given().contentType("application/json").body(requestBody).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	void withArrayJSON() {
		JsonObject jObj = new JsonObject();
		jObj.addProperty("name", "dheerendra");
		jObj.addProperty("email", "dheerendra@gmail.com");
		jObj.addProperty("age", 30);

		JsonArray hobbyArray = new JsonArray();
		hobbyArray.add("Reading");
		hobbyArray.add("Travelling");
		hobbyArray.add("Music");

		jObj.add("hobby", hobbyArray);
		given().contentType("application/json").body(jObj.toString()).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	void withPOJO() {

		POJO_UserPostRequest POJOuser = new POJO_UserPostRequest();
		POJOuser.setAge(30);
		POJOuser.setName("dheerendraVishwakarma");
		POJOuser.setEmail("dheerendra@gmail.com");
		POJOuser.setHobbies(Arrays.asList("Reading", "Dancing", "Travelling"));

		given().contentType("application/json").body(POJOuser).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all();
	}

	@Test
	void withExternalJSONFile() {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\restAssured1\\Body.json";
		FileReader file = null;
		try {
			file = new FileReader(filePath);

			JSONTokener jsonTokener = new JSONTokener(file);
			JSONObject jsonObj = new JSONObject(jsonTokener);
			jsonObj.getString("name");
			jsonObj.getInt("age");
			jsonObj.getString("email");
			jsonObj.getJSONArray("hobbies");

			given().contentType("application/json").body(jsonObj.toString()).when().post("https://reqres.in/api/users")
					.then().statusCode(201).log().all();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	public void cookies() {
		given().contentType("content-Type")
		.when()
		.then();
	}



	
	
	
	
	
	
	
	
	
	
	
}
