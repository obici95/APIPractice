package patch_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									    }
     */

    @Test
    public void patch01() {
        //Set the URL
        specJsonPlace.pathParam("pp1",198);

        //Set the expected Data
        JsonPlaceHolderTestData objJsonPlace=new JsonPlaceHolderTestData();
        Map<String,Object> expData=objJsonPlace.expDataMapMethod(null,"Wash the dishes",null);
        System.out.println("expData = " + expData);


        //Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).contentType(ContentType.JSON).body(expData).when().patch("{pp1}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actData=response.as(HashMap.class);
        System.out.println(actData);
        assertEquals(200,response.statusCode());

        assertEquals(expData.get("title"),actData.get("title"));





    }
}
