package put_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									    "id": 198
									   }
     */

    @Test
    public void put01() {
        // Set the URL
        specJsonPlace.pathParam("pp1",198);

        // Set the expedted data
        JsonPlaceHolderTestData objJasonPlace=new JsonPlaceHolderTestData();
        Map <String,Object> expDataMap =objJasonPlace.expDataMapMethod(21,"Wash the dishes",false);

        // Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).contentType(ContentType.JSON).body(expDataMap).when().put("{pp1}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actDataMap=response.as(HashMap.class);
        assertEquals(expDataMap.get("userId"),actDataMap.get("userId"));
        assertEquals(expDataMap.get("title"),actDataMap.get("title"));
        assertEquals(expDataMap.get("completed"),actDataMap.get("completed"));


    }




}
