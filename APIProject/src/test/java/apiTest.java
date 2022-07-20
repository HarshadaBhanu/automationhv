import java.util.concurrent.TimeUnit;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class apiTest {

	public static void main(String[] args) {
		//baseURI
				RestAssured.baseURI="http://api.zippopotam.us/";
				RequestSpecification request = RestAssured.given(); //returns obj of request specification // making a request to server asking for data
				
				// making actual request to server
				Response response=request.get("de/bw/stuttgart"); //query or path parameter, returning in the form of response
				System.out.println(response.getStatusCode());
				Assert.assertEquals(response.getStatusCode(), 200);
				
				System.out.println(response.getContentType());
				Assert.assertEquals(response.getContentType(), "application/json");
				
				System.out.println(response.getTime());
				System.out.println(response.getTimeIn(TimeUnit.SECONDS));
				
				ValidatableResponse valRes = response.then();
				try {
					valRes.time(Matchers.lessThan(1000L));
				}catch (AssertionError e) {
			         System.out.println(e.getMessage());
			      }
				String str=response.getBody().asString();
				System.out.println(response.getBody().prettyPrint());

				JsonPath js = new JsonPath(str);
				System.out.println(js.get("places"));
				System.out.println(js.get("country"));
				Assert.assertEquals(js.get("country"), "Germany");
				Assert.assertEquals(js.get("state"), "Baden-Württemberg");
				
				
				JSONObject obj = new JSONObject(str);
			    JSONArray places = obj.getJSONArray("places");
			    int n = places.length();
			    for (int i = 0; i < n; ++i) {
			    	
			      JSONObject place = places.getJSONObject(i);
			      if(place.getString("post code").equals("70597")) {
			    	  System.out.println("here is "+i+" : array ");
				      System.out.println(place.getString("place name"));
				      System.out.println(place.getString("longitude"));
				      System.out.println(place.getString("post code"));
				      System.out.println(place.getDouble("latitude"));
				      if( place.getString("place name").equals("Stuttgart Degerloch") )
				    	  System.out.println("postal code "+place.getString("post code")+" matches with place name: "+place.getString("place name"));
				      else
				    	  System.out.println("postal code "+place.getString("post code")+" does NOT matches with place name: Stuttgart Degerloch");
			      }
			    }
	}

}
