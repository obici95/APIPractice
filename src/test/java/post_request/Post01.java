package post_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
     /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
        When
         I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01() {
        //Set the url
        specJsonPlace.pathParam("pp1","");

        //Set the Expected Data
        JsonPlaceHolderTestData objJsonPlaceHolder=new JsonPlaceHolderTestData();
        Map<String,Object> expDataMap=objJsonPlaceHolder.expDataMapMethod(55,"Tidy your room",false);

        // Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).contentType(ContentType.JSON).body(expDataMap).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actDataMap=response.as(HashMap.class);
        assertEquals(expDataMap.get("userId"),actDataMap.get("userId"));
        assertEquals(expDataMap.get("title"),actDataMap.get("title"));
        assertEquals(expDataMap.get("completed"),actDataMap.get("completed"));


    }

}
