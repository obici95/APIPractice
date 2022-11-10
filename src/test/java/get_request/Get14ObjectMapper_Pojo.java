package get_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
                                        "userId": 10,
                                        "id": 198,
                                        "title": "quis eius est sint explicabo",
                                        "completed": true
                                      }
     */

    //ObjectMapper+Pojo en iyi yontemdir.
    @Test
    public void get14Pojo(){
        //Set the Url
        specJsonPlace.pathParam("pp1",198);

        //Set the Expected Data
        JsonPlaceHolderPojo expData=new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
        System.out.println(expData);

        //Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).when().get("/{pp1}");
        response.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expData.getUserId(),actData.getUserId());
        assertEquals(expData.getTitle(),actData.getTitle());
        assertEquals(expData.getCompleted(),actData.getCompleted());
    }
}
