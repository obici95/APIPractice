package post_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonPlaceHolderBaseUrl {

     /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
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
    public void post05ObjectMapper() throws IOException {
        //Set the Url
        specJsonPlace.pathParam("pp1","");

        //Set the Expected Data

      /*  String jsonInString="{\n" +
                "                                    \"userId\": 55,\n" +
                "                                    \"title\": \"Tidy your room\",\n" +
                "                                    \"completed\": false,\n" +
                "                                    \"id\": 201\n" +
                "                                    }";

       */
        JsonPlaceHolderTestData objJPHTestData=new JsonPlaceHolderTestData();
        String jsonInString=objJPHTestData.expDataInString(55,"Tidy your room",false);

        HashMap expData =new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println(expData);

        //Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).contentType(ContentType.JSON).body(expData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        HashMap actData=new ObjectMapper().readValue(response.asString(),HashMap.class);
        System.out.println("actData = " + actData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expData.get("userId"),actData.get("userId"));
        assertEquals(expData.get("title"),actData.get("title"));
        assertEquals(expData.get("completed"),actData.get("completed"));


    }
}
