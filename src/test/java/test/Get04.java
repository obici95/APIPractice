package test;

import base_urls.ReqresBaseUrl;
import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get04 extends RestfulBaseUrl {

     /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Jacob&lastname=Davis
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Jacob" and lastname is "Davis"

 */

    @Test
    public void get04() {
        //Set the Url
        spec.pathParam("first","booking").queryParams("firstname","Jacob","lastname","Davis");

        //Set The Expected Data


        //Send The Request and Get The Response
       Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion

        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));
    }


}
