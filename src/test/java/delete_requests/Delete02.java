package delete_requests;

import baseUrls.DummyResApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeletePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyResApiBaseUrl {
      /*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"
       */

    /*
     Given
         https://dummy.restapiexample.com/api/v1/delete/2
     When
         User Sends Delete Request and gets Response
     And
         Status code should be 200
     And
         "status" is "success"
     And
          "data" is "2"
     And
          "message" is "Successfully! Record has been deleted"

     */

    @Test
    public void delete02() {

        specDummyRest.pathParams("pp1","delete","pp2",2);

        DummyRestApiDeletePojo expData=new DummyRestApiDeletePojo("success","2","Successfully! Record has been deleted");
        System.out.println("expData = " + expData);

        Response response =given().spec(specDummyRest).when().delete("{pp1}/{pp2}");
        response.prettyPrint();
        DummyRestApiDeletePojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiDeletePojo.class);
        System.out.println("actData = " + actData);
        assertEquals(200,response.statusCode());
        assertEquals(expData.getStatus(),actData.getStatus());
        assertEquals(expData.getData(),actData.getData());
        assertEquals(expData.getMessage(),actData.getMessage());


    }
}
