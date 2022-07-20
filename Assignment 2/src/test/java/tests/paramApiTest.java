package tests;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class paramApiTest {

	@DataProvider(name = "dataprovider1")
	public String[][] createData() 
	{			
	    return new String[][]
	    {
	        {"us","90210 ","Beverly Hills"},
	        {"us","12345 ","Schenectady"},
	        {"ca","B2R ","Waverley"}
	    };
	}
		
	/*POST Request Example*/
	@Test(enabled=true, dataProvider ="dataprovider1")
	public void postExample(String country , String postalCode, String placeName )
	{
		RestAssured.baseURI="http://api.zippopotam.us/";
	    Response res =  
	    RestAssured.given()
	        .header("Content-Type","application/json")
	        .queryParams("country", country,"postal-code",postalCode)
	    .when()
	        .get()
	    .then()
	        .statusCode(200)
	        .extract().response();
			
	    //res.body().prettyPrint();
	    System.out.println(res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//System.out.println(res.getContentType());
		//Assert.assertEquals(res.getContentType(), "application/json");
		
		System.out.println(res.getTime());
		System.out.println(res.getTimeIn(TimeUnit.SECONDS));
		
		ValidatableResponse valRes = res.then();
		try {
			valRes.time(Matchers.lessThan(1000L));
		}catch (AssertionError e) {
	         System.out.println(e.getMessage());
	      }
	}

}
