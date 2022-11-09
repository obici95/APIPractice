package post_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {

     /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
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
    public void post03Pojo() {
        // Set the Url
        specJsonPlace.pathParam("pp1","");

        //Set the Expected Data
        JsonPlaceHolderPojo expData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expData = " + expData);

        //Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).contentType(ContentType.JSON).body(expData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actData=response.as(JsonPlaceHolderPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(201,response.getStatusCode());

        assertEquals(expData.getUserId(),actData.getUserId());
        assertEquals(expData.getTitle(),actData.getTitle());
        assertEquals(expData.getCompleted(),actData.getCompleted());


    }


}
