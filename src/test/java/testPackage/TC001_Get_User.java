/******************************************************
Test Name:    Get all User data
URI:          https://gorest.co.in/public-api/users
Request Type: GET
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

public class TC001_Get_User extends Base {

	@BeforeClass
	void getAllUsers() throws InterruptedException {
		logger.info("************Started TC001_Get_User *****************");
		RestAssured.baseURI = propertyObject().getProperty("URI");
		httpRequest = RestAssured.given();
		httpRequest.header("Authorization", propertyObject().getProperty("Authorization"));
		response = httpRequest.request(Method.GET, propertyObject().getProperty("Get"));
		Thread.sleep(3000);
	}

	@Test
	void checkResposeBody() {
		logger.info("*******************Checking Response Body***********************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body : " + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkStatusCode() {
		logger.info("*******************Checking Response Code***********************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code : " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkStatusLine() {
		logger.info("*******************Checking Response Status Line***********************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line : " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		logger.info("*******************Checking Response Content Type***********************");
		String contentType = response.contentType();
		logger.info("Content Type : " + contentType);
		Assert.assertEquals(contentType, "application/json; charset=UTF-8");
	}

	@Test
	void checkContentEncoding() {
		logger.info("*******************Checking Content Encoding***********************");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding : " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test
	void checkServer() {
		logger.info("*******************Checking Server Type***********************");
		String server = response.header("Server");
		logger.info("Server : " + server);
		Assert.assertEquals(server, "nginx");
	}

	@AfterClass
	void tearDown() {
		logger.info("*******************Finished TC001_Get_User ***********************");
	}

}
