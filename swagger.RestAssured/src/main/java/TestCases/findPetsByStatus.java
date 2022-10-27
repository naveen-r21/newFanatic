package TestCases;

import  org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Properties.propertiesLoad;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class findPetsByStatus extends propertiesLoad {
	
	static ResponseBody responseBody;
	static JsonPath jsonPathEvaluator;

	@Test(priority=1)
	public static void availablePets() {

		Reporter.log("Available Pets",true);

		baseURI = Repository.getProperty("baseURI");
		basePath =Repository.getProperty("availablePath");
		
		Reporter.log("Base URI: "+baseURI,true);

		RequestSpecification reqSpec =	RestAssured.given();
		Response response = reqSpec.request(Method.GET,basePath);	
		Reporter.log("Base Path: "+basePath,true);

		jsonPathEvaluator = response.jsonPath();
		responseBody = response.body();

		String bodyObject = responseBody.asString();
		String name = jsonPathEvaluator.get("category[1].name");
		Reporter.log("Pet Name: "+name,true);

		String assertName = Repository.getProperty("PetName");
		Assert.assertEquals(bodyObject.contains(assertName), true);	
		Reporter.log("Response body contains "+assertName,true);

		int statusCode =	response.getStatusCode();
		Reporter.log("Status code: "+statusCode,true);
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Actual status Code matches with expected ",true);
		
		String statusLine =	response.getStatusLine();
		Reporter.log("StatusLine: "+statusLine,true);
		String expectedStatusLine = Repository.getProperty("StatusLine");
		Assert.assertEquals(statusLine, expectedStatusLine);
		Reporter.log("Actual status Line matches with expected ",true);

		Assert.assertEquals(bodyObject.contains("pending")||bodyObject.contains("sold"), false);
		Assert.assertEquals(bodyObject.contains("available"), true);
		Reporter.log("Response body contains Pets with available status only ",true);
		

	}

	@Test(priority=2)
	public static void pendingPets() {

		Reporter.log("Pending Pets",true);

		baseURI = Repository.getProperty("baseURI");
		basePath =Repository.getProperty("pendingPath");
		
		Reporter.log("Base URI: "+baseURI,true);

		RequestSpecification reqSpec =	RestAssured.given();
		Response response = reqSpec.request(Method.GET,basePath);	
		Reporter.log("Base Path: "+basePath,true);

		jsonPathEvaluator = response.jsonPath();
		responseBody = response.body();

		String bodyObject = responseBody.asString();
		String name = jsonPathEvaluator.get("category[1].name");
		Reporter.log("Pet Name: "+name,true);

		String assertName = Repository.getProperty("PetName");
		Assert.assertEquals(bodyObject.contains(assertName), true);	
		Reporter.log("Response body contains "+assertName,true);

		int statusCode =	response.getStatusCode();
		Reporter.log("Status code: "+statusCode,true);
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Actual status Code matches with expected ",true);
		
		String statusLine =	response.getStatusLine();
		Reporter.log("StatusLine: "+statusLine,true);
		String expectedStatusLine = Repository.getProperty("StatusLine");
		Assert.assertEquals(statusLine, expectedStatusLine);
		Reporter.log("Actual status Line matches with expected ",true);

		Assert.assertEquals(bodyObject.contains("sold")||bodyObject.contains("available"), false);
		Assert.assertEquals(bodyObject.contains("pending"), true);
		Reporter.log("Response body contains Pets with pending status only",true);

	}

	@Test(priority=3)
	public static void soldPets() {

		Reporter.log("Sold Pets",true);

		baseURI = Repository.getProperty("baseURI");
		basePath =Repository.getProperty("soldPath");
		
		Reporter.log("Base URI: "+baseURI,true);

		RequestSpecification reqSpec =	RestAssured.given();
		Response response = reqSpec.request(Method.GET,basePath);	
		Reporter.log("Base Path: "+basePath,true);

		jsonPathEvaluator = response.jsonPath();
		responseBody = response.body();

		String bodyObject = responseBody.asString();
		String name = jsonPathEvaluator.get("category[1].name");
		Reporter.log("Pet Name: "+name,true);

		String assertName = Repository.getProperty("PetName");
		Assert.assertEquals(bodyObject.contains(assertName), true);	
		Reporter.log("Response body contains "+assertName,true);

		int statusCode =	response.getStatusCode();
		Reporter.log("Status code: "+statusCode,true);
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Actual status Code matches with expected ",true);
		
		String statusLine =	response.getStatusLine();
		Reporter.log("StatusLine: "+statusLine,true);
		String expectedStatusLine = Repository.getProperty("StatusLine");
		Assert.assertEquals(statusLine, expectedStatusLine);
		Reporter.log("Actual status Line matches with expected ",true);

		Assert.assertEquals(bodyObject.contains("pending")||bodyObject.contains("available"), false);
		Assert.assertEquals(bodyObject.contains("sold"), true);
		Reporter.log("Response body contains Pets with sold status only ",true);
	}

}
