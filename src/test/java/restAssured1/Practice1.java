package restAssured1;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

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

	@Test
	public void cookies() {
		Response res = given().when().get("https://www.google.com/");
		Map<String, String> cookiesValue = res.getCookies();
		for (String key : cookiesValue.keySet()) {
			System.out.println(key + ":  " + cookiesValue.get(key));
		}
	}

	@Test
	public void headers() {
		// Get response headers
		Response res = given().when().get("https://www.google.com/");
		Headers headers = res.getHeaders();
		System.out.println(headers);
		for (Header hd : headers) {
			System.out.println(hd.getName() + ":" + hd.getValue());
		}
	}

	@Test
	void extractData() {
		Response resp = given().when().get("http://restapi.adequateshop.com/api/TravelAgent");
		String resBody = resp.getBody().asString();
		System.out.println(resBody);
		// String id = JsonPath.from(resBody).getString("id");
		// System.out.println(id);

		// JsonPath Instantiate a JsonPath object to parse the JSON response.
		JsonPath jsonPath = new JsonPath(resBody);

		// Get the size of the JSON array in the response.
		// size() function is used to retrieve the number of elements in the JSON
		// array. This function counts the number of elements at the root level of the
		// JSON structure
		int size = jsonPath.getInt("size()");

		for (int i = 0; i < size; i++) {
			String id = jsonPath.getString("[" + i + "].id"); // "[0].id" -->Specifies the path to the desired value.
																// Here, [0] denotes the first object in the array
			String agentEmail = jsonPath.getString("[" + i + "].agent_email");
			String agentLocation = jsonPath.getString("[" + i + "].agent_location");

			System.out.println("ID: " + id);
			System.out.println("Agent Email: " + agentEmail);
			System.out.println("Agent Location: " + agentLocation);
		}
	}

	@Test
	void QueryAndPathParamenters() {
		given().pathParam("myPath", "users").queryParam("page", 2).queryParam("id", 5).when()
				.get("https://reqres.in/api/{myPath}").then();
		// query parameters are then automatically appended to the URL
	}

	@Test
	void TypesOfLog() {
		System.out.println("All******************************************************************");
		given().pathParam("myPath", "users").queryParam("page", 2).queryParam("id", 5).when()
				.get("https://reqres.in/api/{myPath}").then().log().all();

		System.out.println("body******************************************************************");
		given().pathParam("myPath", "users").queryParam("page", 2).queryParam("id", 5).when()
				.get("https://reqres.in/api/{myPath}").then().log().body();

		System.out.println("cookies******************************************************************");
		given().pathParam("myPath", "users").queryParam("page", 2).queryParam("id", 5).when()
				.get("https://reqres.in/api/{myPath}").then().log().cookies();

		System.out.println("Headers******************************************************************");
		given().pathParam("myPath", "users").queryParam("page", 2).queryParam("id", 5).when()
				.get("https://reqres.in/api/{myPath}").then().log().headers();

		System.out.println("ifValidationFails******************************************************************");
		given().pathParam("myPath", "users").queryParam("page", 2).queryParam("id", 5).when()
				.get("https://reqres.in/api/{myPath}").then().log().ifValidationFails();

	}

	@Test
	void SchemaValidators() {
		given().when().get("http://restapi.adequateshop.com/api/Tourist").then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

		given().when().get("http://restapi.adequateshop.com/api/Traveler").then().assertThat()
				.body(RestAssuredMatchers.matchesXsdInClasspath("traveller.xsd")); // Provide the XSD file name or path

		// When you specify the XSD file name like this, the testing framework
		// (presumably RestAssured) will look for the XSD file within the classpath of
		// your project. The classpath includes locations like the "src/main/resources"
		// directory in Maven projects or any other directories configured to be part of
		// the classpath.

		// By giving the path of json and xsd file location
		given().when().get("http://restapi.adequateshop.com/api/Tourist").then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/schema.json")));

		given().when().get("http://restapi.adequateshop.com/api/Traveler").then().assertThat()
				.body(RestAssuredMatchers.matchesXsd(new File("src/test/resources/traveller.xsd")));

	}

	@Test
	void authentication() {

		// Basic authorisation
		given().auth().basic("Dheerendra0161", "Marikpur@1").when()
				.get("https://api.github.com/repos/Dheerendra0161/PageObjectModelNew").then().log().all();
		// Digest authorisation
		given().auth().digest("Dheerendra0161", "Marikpur@1").when()
				.get("https://api.github.com/repos/Dheerendra0161/PageObjectModelNew").then().log().all();

		// Preemptive authorisation
		given().auth().preemptive().basic("Dheerendra0161", "Marikpur@1").when()
				.get("https://api.github.com/repos/Dheerendra0161/PageObjectModelNew").then().log().all();

		// By API key/token authorisation
		String token = "265caa6ec34ed60e7f355ac18f487f09";
		given().queryParam("APIkey", token).when().get("https://api.github.com/repos/Dheerendra0161/PageObjectModelNew")
				.then().log().all();
	}

	@Test
	void convetingJSONtoJSONObj() {

		String jsonData = "{\n" + "  \"company\": \"ABC Corporation\",\n" + "  \"employees\": [\n" + "    {\n"
				+ "      \"id\": 1,\n" + "      \"name\": \"John\",\n" + "      \"department\": \"Engineering\",\n"
				+ "      \"skills\": [\"Java\", \"Python\", \"SQL\"]\n" + "    },\n" + "    {\n" + "      \"id\": 2,\n"
				+ "      \"name\": \"Alice\",\n" + "      \"department\": \"Marketing\",\n"
				+ "      \"skills\": [\"Marketing Strategy\", \"Social Media Management\"]\n" + "    }\n" + "  ],\n"
				+ "  \"projects\": [\n" + "    {\n" + "      \"id\": 101,\n" + "      \"name\": \"Project X\",\n"
				+ "      \"status\": \"In Progress\",\n" + "      \"team\": [\"John\", \"Alice\", \"Emily\"]\n"
				+ "    },\n" + "    {\n" + "      \"id\": 102,\n" + "      \"name\": \"Project Y\",\n"
				+ "      \"status\": \"Completed\",\n" + "      \"team\": [\"John\", \"Bob\", \"Carol\"]\n" + "    }\n"
				+ "  ]\n" + "}";

		JSONObject obj = new JSONObject(jsonData);
		for (int i = 0; i < obj.getJSONArray("employees").length(); i++) {
			System.out.println(obj.getJSONArray("employees").getJSONObject(i).getJSONArray("skills"));
		}
		System.out.println(obj.getJSONArray("projects").getJSONObject(1).getJSONArray("team"));

	}

	@Test
	void retrieveValueUsingExternalJsonFile() throws FileNotFoundException {
		String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\restAssured1\\Body1.json";
		FileReader fileReader = new FileReader(jsonFilePath);
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		JSONObject jsonObject = new JSONObject(jsonTokener);

		for (int i = 0; i < jsonObject.getJSONArray("employees").length(); i++) {
			System.out.println(jsonObject.getJSONArray("employees").getJSONObject(i).getJSONArray("skills"));
		}
		System.out.println(jsonObject.getJSONArray("projects").getJSONObject(1).getJSONArray("team"));

	}
	
	
	
	

}
