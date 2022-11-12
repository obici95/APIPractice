package get_request;

import baseUrls.DummyResApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get17 extends DummyResApiBaseUrl {
      /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */


    @Test
    public void get17() {
        specDummyRest.pathParams("pp1","employee","pp2",1);

        DummRestApiDataPojo dummRestApiDataPojo=new DummRestApiDataPojo("Tiger Nixon",320800,61,"");
        DummyRestApiResponseBodyPojo expData=new DummyRestApiResponseBodyPojo("success",dummRestApiDataPojo,"Successfully! Record has been fetched.");

        Response response =given().spec(specDummyRest).when().get("{pp1}/{pp2}");

        DummyRestApiResponseBodyPojo actData= ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expData.getStatus(),actData.getStatus());

        assertEquals(expData.getData().getEmployee_name(),actData.getData().getEmployee_name());
        assertEquals(expData.getData().getEmployee_salary(),actData.getData().getEmployee_salary());
        assertEquals(expData.getData().getEmployee_age(),actData.getData().getEmployee_age());

        assertEquals(expData.getMessage(),actData.getMessage());

    }
}
