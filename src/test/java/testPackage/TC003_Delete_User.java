/******************************************************
Test Name:    Delete One User
URI:          https://gorest.co.in/public-api/users/{userId}
Request Type: Delete
Request Payload(Body): NA

********* Validations **********
Response Body
Content-Type  application/json; charset=UTF-8
Status Code   200 
Content-Encoding   gzip
Status Line : HTTP/1.1 200 OK
Server : nginx
 *********************************************************/

package testPackage;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import resources.Base;

public class TC003_Delete_User extends Base {

	@BeforeClass
	void deleteUser() throws InterruptedException {
		logger.info("*********************Started TC003_Delete_User***********************");
		RestAssured.baseURI = propertyObject().getProperty("URI");
		httpRequest = RestAssured.given();
		httpRequest.header("Authorization", propertyObject().getProperty("Authorization"));
		response = httpRequest.request(Method.DELETE,
				propertyObject().getProperty("Delete") + prop.getProperty("UserId"));
		Thread.sleep(3000);

	}

	@Test
	void checkResponseBody() {
		logger.info("*********************Checking Response Body***********************");
		String responseBody = response.body().asString();
		logger.info("Response Body : " + responseBody);
		Assert.assertTrue(responseBody
				.contains("The request was handled successfully and the response contains no body content."));
	}

	@Test
	void checkStatusCode() {
		logger.info("*********************Checking Response Status Code***********************");
		int statusCode = response.getStatusCode();
		logger.info("Response Code : " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkStatusLine() {
		logger.info("*********************Checking Response Status Line***********************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line : " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		logger.info("*********************Checking Content Type***********************");
		String contentType = response.contentType();
		logger.info("Content Type : " + contentType);
		Assert.assertEquals(contentType, "application/json; charset=UTF-8");
	}

	@Test
	void checkServer() {
		logger.info("*********************Checking Server***********************");
		String server = response.header("Server");
		logger.info("Server : " + server);
		Assert.assertEquals(server, "nginx");
	}

	@Test
	void checkContentEncoding() {
		logger.info("*********************Checking Content Encoding***********************");
		String encodingType = response.header("Content-Encoding");
		logger.info("Encoding Type : " + encodingType);
		Assert.assertEquals(encodingType, "gzip");
	}

	@AfterClass
	void tearDown() {
		logger.info("************Finished TC003_Delete_User *****************");
	}

}
