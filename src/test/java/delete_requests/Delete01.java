package delete_requests;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {

        //Set the Url
        specJsonPlace.pathParam("pp1",198);

        //Set the expected Data

        //Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).when().delete("{pp1}");
        response.prettyPrint();

        //Do Assertion

        assertEquals(200,response.getStatusCode());
    }
}
