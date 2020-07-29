package utilities;

import org.apache.commons.lang3.RandomStringUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	// Below Method is used to generate Random email id
	public static String email() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("John124" + generatedString + "@gmail.com");
	}

	// Below Method is used to generate Random first Name
	public static String firstName() {
		String firstName = RandomStringUtils.randomAlphabetic(6);
		return firstName;
	}

	// Below Method is used to generate Random last
	public static String lastName() {
		String lastName = RandomStringUtils.randomAlphabetic(3);
		return lastName;
	}

	// Below Method is used to covert rowResponse to xml
	public static XmlPath rawToXML(Response r) {
		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}

	// Below Method is used to covert rowResponse to Json
	public static JsonPath rawToJson(Response r) {
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		return x;
	}

}
