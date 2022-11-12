package put_request;

import baseUrls.DummyResApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyResApiBaseUrl {
     /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */


    /*
Given
    https://dummy.restapiexample.com/api/v1/update/21
     {
        "employee_name": "Ali Can",
        "employee_salary": 111111,
        "employee_age": 23,
        "profile_image": "Perfect image"
     }
 When
   User sends PUT Request
  Then
   i) Status code is 200
   And
   ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }
 */

    @Test
    public void put02() {
        specDummyRest.pathParams("pp1","update","pp2",21);

        DummRestApiDataPojo dummRestApiDataPojo=new DummRestApiDataPojo("Ali Can",111111,23,"Perfect image");
        DummyRestApiResponseBodyPojo expData=new DummyRestApiResponseBodyPojo("success",dummRestApiDataPojo,"Successfully! Record has been updated.");

        Response response =given().spec(specDummyRest.contentType(ContentType.JSON)).body(dummRestApiDataPojo).when().put("{pp1}/{pp2}");
        response.prettyPrint();

        DummyRestApiResponseBodyPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(200,response.statusCode());

        assertEquals(expData.getStatus(),actData.getStatus());

        assertEquals(expData.getData().getEmployee_name(),actData.getData().getEmployee_name());
        assertEquals(expData.getData().getEmployee_salary(),actData.getData().getEmployee_salary());
        assertEquals(expData.getData().getEmployee_age(),actData.getData().getEmployee_age());
        assertEquals(expData.getData().getProfile_image(),actData.getData().getProfile_image());

        assertEquals(expData.getMessage(),actData.getMessage());


    }
}
