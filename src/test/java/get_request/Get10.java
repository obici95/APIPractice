package get_request;

import baseUrls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
         {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Prof. Bala Pilla",
        "email": "pilla_prof_bala@zemlak.io",
        "gender": "female",
        "status": "active"
    }
}
     */

    @Test
    public void get10() {

        //1- Set the Url
        specGoRest.pathParams("pp1","users","pp2",2986);

        //Set the Expected Data
        GoRestTestData objGoRestTestData=new GoRestTestData();
        Map<String,String> dataKeyMapInner = objGoRestTestData.dataKeyMapMethod("Prof. Bala Pilla","pilla_prof_bala@zemlak.io","female","active");

        Map<String ,Object> expectedDataMap=objGoRestTestData.expectedDataMethod(null,dataKeyMapInner);
        System.out.println(expectedDataMap);

        // Send the request GET the response
        Response response =given().spec(specGoRest).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        // Do Assertion
        Map<String,Object> actDataMap=response.as(HashMap.class);

        assertEquals(expectedDataMap.get("meta"),actDataMap.get("meta"));
        assertEquals(dataKeyMapInner.get("name"),((Map)actDataMap.get("data")).get("name"));
        assertEquals(dataKeyMapInner.get("email"),((Map)actDataMap.get("data")).get("email"));
        assertEquals(dataKeyMapInner.get("gender"),((Map)actDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMapInner.get("status"),((Map)actDataMap.get("data")).get("status"));


    }
}
