package get_request;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import net.minidev.json.writer.CollectionMapper;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Map extends JsonPlaceHolderBaseUrl {

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

    @Test
    public void get14Map() {
        //Set the Url
        specJsonPlace.pathParam("pp1",198);

        //Set the Expected Data
        String expDataInString=new JsonPlaceHolderTestData().expDataInString(10,"quis eius est sint explicabo",true);
        Map expData=ObjectMapperUtils.convertJsonToJava(expDataInString, Map.class);
        System.out.println("expData = " + expData);

        //Send the Request and Get the Response
        Response response =given().spec(specJsonPlace).when().get("{pp1}");
        response.prettyPrint();

        //Do Assertion
        Map actData=ObjectMapperUtils.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actData = " + actData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expData.get("userId"),actData.get("userId"));
        assertEquals(expData.get("title"),actData.get("title"));
        assertEquals(expData.get("completed"),actData.get("completed"));
    }
}
