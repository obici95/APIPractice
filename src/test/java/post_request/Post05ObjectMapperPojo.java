package post_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapperPojo extends JsonPlaceHolderBaseUrl {

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
    public void post05ObjectMapper() {
        //Set the Url
        specJsonPlace.pathParam("pp1","");

        //Set the Expected Data
        JsonPlaceHolderPojo expData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expData = " + expData);

        //Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).contentType(ContentType.JSON).body(expData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expData.getUserId(),actData.getUserId());
        assertEquals(expData.getTitle(),actData.getTitle());
        assertEquals(expData.getCompleted(),actData.getCompleted());

    }
}
