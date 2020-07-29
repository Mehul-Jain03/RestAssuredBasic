package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {
	
	//Defining globalvVariables to use in the project
	public static RequestSpecification httpRequest;
	public static Response response;
	public Properties prop;
	public Logger logger;
	
	@BeforeClass
	//This method is used for logging purpose in all the test Cases
	public void setup(){
		
		logger=Logger.getLogger("Go Rest Database API");//added Logger
		PropertyConfigurator.configure("Log4j.properties"); //added logger
		logger.setLevel(Level.DEBUG);
		
	}

	//This Method is used to use property object in all the classes
	public Properties propertyObject() {

		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\resources\\commons.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
