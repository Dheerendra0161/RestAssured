package restAssured4;

import static io.restassured.RestAssured.given;

import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import org.testng.annotations.Test;

public class SingleandMultipleFileUpload {

	public class FileUploadTest {

		@Test
		public void uploadFile() {
			File myFile = new File("path/to/your/file.txt"); // use your local file path

			given().multiPart("file", myFile).contentType("multipart/form-data").when()
					.post("http://localhost:8080/upload")
					.then().statusCode(200)
					.body("TravelerinformationResponse.page", equalTo("Text1.txt"))
					.log().all();
		}
	}

	@Test
	public void uploadMultipleFiles() {
	    File file1 = new File("path/to/your/file1.txt");
	    File file2 = new File("path/to/your/file2.txt");
	    File file3 = new File("path/to/your/file3.txt");

	    given()
	        .multiPart("file", file1)
	        .multiPart("file", file2)
	        .multiPart("file", file3)
	        .contentType("multipart/form-data")
	    .when()
	        .post("http://localhost:8080/upload")
	    .then()
	        .statusCode(200)
	        .body("[0].fileName", equalTo("file1.txt"))
	        .body("[1].fileName", equalTo("file2.txt"))
	        .body("[2].fileName", equalTo("file3.txt"))
	        .log().all();
	}



	    @Test
	    public void uploadMultipleFilesWithArray() {
	    	//It may won't work for all kind of API
	        File[] files = {
	            new File("path/to/your/file1.txt"),
	            new File("path/to/your/file2.txt"),
	            new File("path/to/your/file3.txt")
	        };

	        given()
	            .multiPart("file", files)
	            .contentType("multipart/form-data")
	        .when()
	            .post("http://localhost:8080/upload")
	        .then()
	            .statusCode(200)
	            .body("[0].fileName", equalTo("file1.txt"))
	            .body("[1].fileName", equalTo("file2.txt"))
	            .body("[2].fileName", equalTo("file3.txt"))
	            .log().all();
	    }
	}



