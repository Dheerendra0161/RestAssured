package restAssured5;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import restAssured1.POJO_UserPostRequest;

//POJO ------- Serilization ------>JSON Object ------->Deserilizatiion ------->POJO

//Serialization and deserialization in REST Assured help streamline the process of interacting with APIs by facilitating the conversion between Java objects and JSON/XML representations, enabling seamless communication between the test code and the API endpoints.
public class SerilizationDeseilization {
	@Test
	void convertingPOJOtoJSON() throws JsonProcessingException {
		// Create Java object to represent the request body************************
		POJO_UserPostRequest POJOuser = new POJO_UserPostRequest(); // POJO class object which is located in
																	// restAssured1
																	// package
		POJOuser.setName("Vikas");
		POJOuser.setAge(30);
		POJOuser.setEmail("vikas@gmail.com");
		POJOuser.setHobbies(Arrays.asList("Reading", "Gaming", "Traveling"));

		// Conveting Java object into json object(serilization) POJO----->JSON

		// ObjectMapper functionality for reading and writing JSON, either to and from
		// basic POJOs (POJO<----->JSON)
		ObjectMapper objMapper = new ObjectMapper(); // Import it from import
														// com.fasterxml.jackson.databind.ObjectMapper only
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(POJOuser);// Method will convert
																									// into String
		System.out.println(jsonData);

	}

	@Test
	void convertingJSONtoPOJO() throws IOException {
		// Just copy the Json response and paste here

		// Accessing fields of the deserialized object
		String jsonData = "{\n" + "  \"name\": \"Vikas\",\n" + "  \"age\": 30,\n"
				+ "  \"email\": \"vikas@gmail.com\",\n" + "  \"hobbies\": [\"Reading\", \"Gaming\", \"Traveling\"]\n"
				+ "}";

		// Deserialize JSON ----> POJO
		ObjectMapper objMapper = new ObjectMapper();
		// POJO_UserPostRequest is a class created in different restAssured package
		POJO_UserPostRequest employeeObj = objMapper.readValue(jsonData, POJO_UserPostRequest.class); // json-->POJO

		// Print POJO fields
		System.out.println("Name: " + employeeObj.getName());
		System.out.println("Age: " + employeeObj.getAge());
		System.out.println("Email: " + employeeObj.getEmail());
		System.out.println("Hobbies: ");
		for (String hobby : employeeObj.getHobbies()) {
			System.out.println("- " + hobby);
		}
	}

}
