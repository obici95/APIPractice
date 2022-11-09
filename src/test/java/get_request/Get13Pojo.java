package get_request;

import baseUrls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {

     /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
         {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Rajinder Acharya",
                "email": "rajinder_acharya@mosciski.net",
                "gender": "female",
                "status": "active"
            }
        }
    */

    @Test
    public void get13Pojo() {
        //set the Url
        specGoRest.pathParams("pp1","users","pp2",2508);

        //Set the Expected Data
        GoRestDataPojo goRestDataPojo=new GoRestDataPojo(2508,"Rajinder Acharya","rajinder_acharya@mosciski.net","female","active");
        GoRestPojo expData=new GoRestPojo(null,goRestDataPojo);
        System.out.println(expData);

        //Send the Request and Get the Response
        Response response =given().spec(specGoRest).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
        GoRestPojo actData =response.as(GoRestPojo.class);
        System.out.println(actData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expData.getMeta(),actData.getMeta());

        assertEquals(goRestDataPojo.getId(),actData.getData().getId());
        assertEquals(goRestDataPojo.getName(),actData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actData.getData().getStatus());








    }
}
