package post_request;

import baseUrls.DummyResApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyResApiBaseUrl {

     /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */


    /*
    Given
        https://dummy.restapiexample.com/api/v1/create
    And    {
            "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891
          }
     When
        User sends POST request
     Then
        Status code is 200
     And
        Response body should be like the following
        {
            "status": "success",
            "data": {
                "employee_name": "Tom Hanks",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image",
                "id": 4891
            },
            "message": "Successfully! Record has been added."
        }
     */

    @Test
    public void post06() {

        //Set the url
        specDummyRest.pathParam("pp1","create");

        //Set the Expected Data
        DummRestApiDataPojo expData=new DummRestApiDataPojo("Tom Hanks",111111,23,"Perfect image");


        //Send the Request and Get the Response
        Response response=given().spec(specDummyRest).contentType(ContentType.JSON).body(expData).when().post("{pp1}");
        response.prettyPrint();

        //Do Assertion
        DummyRestApiResponseBodyPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(200,response.statusCode());
        assertEquals(expData.getEmployee_name(),actData.getData().getEmployee_name());
        assertEquals(expData.getEmployee_salary(),actData.getData().getEmployee_salary());
        assertEquals(expData.getEmployee_age(),actData.getData().getEmployee_age());
        assertEquals(expData.getProfile_image(),actData.getData().getProfile_image());

    }













}
