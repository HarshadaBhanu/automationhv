package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class GetAPI {

	@Test(enabled=true)

	public void APITest1() {	
		
		//baseURI
				RestAssured.baseURI="http://api.zippopotam.us/";
				RequestSpecification request = RestAssured.given(); //returns obj of request specification // making a request to server asking for data
				
				// making actual request to server
				Response response=request.get("de/bw/stuttgart"); //query or path parameter, returning in the form of response
				System.out.println("The response status code for http://api.zippopotam.us/de/bw/stuttgart is  "+ response.getStatusCode());
				AssertJUnit.assertEquals(response.getStatusCode(), 200);
				System.out.println("Verified StatusCode is 200");
				
				System.out.println("The content type for http://api.zippopotam.us/de/bw/stuttgart is "+response.getContentType());
				AssertJUnit.assertEquals(response.getContentType(), "application/json");
				System.out.println("Verified ContentType is json");
				
				System.out.println("The response time in milisecon is" +response.getTime());
				System.out.println("The response time in sec is" +response.getTimeIn(TimeUnit.SECONDS));
				
				ValidatableResponse valRes = response.then();
				try {
					valRes.time(Matchers.lessThan(1000L));
				}catch (AssertionError e) {
			         System.out.println(e.getMessage());
			      }
				String str=response.getBody().asString();
				//System.out.println(response.getBody().prettyPrint());

				JsonPath js = new JsonPath(str);
				System.out.println("Places" +js.get("places"));
				System.out.println("Country" +js.get("country"));
				AssertJUnit.assertEquals(js.get("country"), "Germany");
				System.out.println("Verified country is Germany");
				AssertJUnit.assertEquals(js.get("state"), "Baden-Württemberg");
				System.out.println("Verified state is Baden-Württemberg");
				
				
				JSONObject obj = new JSONObject(str);
			    JSONArray places = obj.getJSONArray("places");
			    int n = places.length();
			    for (int i = 0; i < n; ++i) {
			    	
			      JSONObject place = places.getJSONObject(i);
			      if(place.getString("post code").equals("70597")) {
			    	  
				      if( place.getString("place name").equals("Stuttgart Degerloch") )
				    	  System.out.println("postal code "+place.getString("post code")+" when matches with place name: "+place.getString("place name"));
				      //else
				    	  //System.out.println("postal code "+place.getString("post code")+" does NOT matches with place name: Stuttgart Degerloch");
			      }
			    }
		}
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
			public void APITest2(String country , String postalCode, String placeName )
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
			    System.out.println("The status code is" +res.getStatusCode());
				AssertJUnit.assertEquals(res.getStatusCode(), 200);
				
				System.out.println(res.getContentType());
				try {
					AssertJUnit.assertEquals(res.getContentType(), "application/json", "The content type is not json");
				}catch (AssertionError e) {
			         System.out.println(e.getMessage());
			      }
				
				
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
