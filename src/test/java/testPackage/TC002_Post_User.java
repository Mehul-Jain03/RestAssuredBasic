/******************************************************
Test Name:    Create One User
URI:          https://gorest.co.in/public-api/users
Request Type: Post
Request Payload(Body): {"email":"XXXXX","firstName":"XXXX","lastName":"XXX", "gender":"XXXX"}

********* Validations **********
Response Payload(Body): {"email":"XXXXX","firstName":"XXXX","lastName":"XXX", "gender":"XXXX"}
Content-Type  application/json; charset=UTF-8
Status Code   200 
Status Line : HTTP/1.1 200 OK
Server : nginx
 *********************************************************/

package testPackage;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import resources.Base;
import utilities.ReusableMethods;

public class TC002_Post_User extends Base {

	String email = ReusableMethods.email();
	String firstName = ReusableMethods.firstName();
	String lastName = ReusableMethods.lastName();
	String gender = "male";

	@BeforeClass
	void getAllUsers() throws InterruptedException {
		logger.info("************Started TC002_Post_User *****************");
		RestAssured.baseURI = propertyObject().getProperty("URI");
		httpRequest = RestAssured.given();
		httpRequest.header("Authorization", propertyObject().getProperty("Authorization"));

		JSONObject param = new JSONObject();
		param.put("email", email);
		param.put("first_name", firstName);
		param.put("last_name", lastName);
		param.put("gender", gender);

		httpRequest.body(param.toJSONString());

		httpRequest.header("Content-Type", "application/json");

		response = httpRequest.post("/users");

		Thread.sleep(3000);

	}

	@Test
	void checkResponseBody() {
		logger.info("************Checking Response Body *****************");
		String responseBody = response.body().asString();
		logger.info("Response Body : " + responseBody);
		Assert.assertEquals(responseBody.contains(email), true);
		Assert.assertEquals(responseBody.contains(firstName), true);
		Assert.assertEquals(responseBody.contains(lastName), true);
		Assert.assertEquals(responseBody.contains(gender), true);
		Assert.assertTrue(responseBody.contains("A resource was successfully created in response to a POST request"));
	}

	@Test
	void checkStatusCode() {
		logger.info("************Checking Status Code *****************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code : " + statusCode);
		Assert.assertEquals(statusCode, 302);

	}

	@Test
	void checkStatusLine() {
		logger.info("************Checking Status Line *****************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line : " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 302 Moved Temporarily");
	}

	@Test
	void checkContentType() {
		logger.info("************Checking Content Type *****************");
		String contentType = response.contentType();
		logger.info("Content Type : " + contentType);
		Assert.assertEquals(contentType, "application/json; charset=UTF-8");
	}

	@Test
	void checkServer() {
		logger.info("************Checking Server *****************");
		String server = response.header("Server");
		logger.info("Server : " + server);
		Assert.assertEquals(server, "nginx");
	}

	@AfterClass
	void tearDown() {
		logger.info("************Finished TC002_Post_User *****************");
	}
}
