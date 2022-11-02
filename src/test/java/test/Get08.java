package test;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    //De-Serialization: Json datayı Java objesine çevirme
    //Serialization: Java objesini Json formatına çevirme.
    //De-Serialization: Iki türlü yapacağız.
    //Gson: Google tarafından üretilmiştir.
    //Object Mapper: Daha popüler...

      /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void get08() {

        // Set the Url
        specJsonPlace.pathParams("pp1","todos","pp2",2);

        //Set The Expected Data ==>Paylodad
        Map<String,Object> expData=new HashMap<>();
        expData.put("userId",1);
        expData.put("id",2);
        expData.put("title","quis ut nam facilis et officia qui");
        expData.put("completed",false);
        System.out.println(expData);

        // Send The Request and Get The Response
      Response response=  given().spec(specJsonPlace).when().get("/{first}/{second}");
      response.prettyPrint();


        //Do Assertion
        Map<String,Object> actData= response.as(HashMap.class); //De-Serialization
        System.out.println("actData = " + actData);

        assertEquals(expData.get("userId"),actData.get("userId"));
        assertEquals(expData.get("id"),actData.get("id"));
        assertEquals(expData.get("title"),actData.get("title"));
        assertEquals(expData.get("completed"),actData.get("completed"));

        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));
        assertEquals("cloudflare",response.header("Server"));
        assertEquals(200,response.statusCode());

    }
}
