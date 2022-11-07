package get_request;

import baseUrls.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
           Response body should be like that;

                {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"

    }
     */


    @Test
    public void get09() {
        //1- Set the Url
        specRestFul.pathParams("pp1", "booking", "pp2", 91);

        //2- Set the Expected Data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("firstname", "Jim");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println(expectedData);

        //3- Send the Request and Get the Response

        Response response = given().spec(specRestFul).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //4- Do Assertion
        Map<String, Object> actDataMap = response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"), actDataMap.get("firstname"));
        assertEquals(expectedData.get("lastname"), actDataMap.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actDataMap.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actDataMap.get("depositpaid"));

        assertEquals(bookingdatesMap.get("checkin"), ((Map) (actDataMap.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map) (actDataMap.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actDataMap.get("additionalneeds"));

    }

    //JSONObject ve JsonPath
    @Test
    public void get09b() {
        //1- Set the Url
        specRestFul.pathParams("pp1", "booking", "pp2", 91);

        //2- Set the Expected Data
        JSONObject bookingdatesMap = new JSONObject();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");

        JSONObject expectedData = new JSONObject();

        expectedData.put("firstname", "Jim");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println(expectedData);

        //3- Send the Request and Get the Response

        Response response = given().spec(specRestFul).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //4- Do Assertion
        JsonPath actDataMap = response.jsonPath();
        assertEquals(expectedData.get("firstname"), actDataMap.get("firstname"));
        assertEquals(expectedData.get("lastname"), actDataMap.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actDataMap.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actDataMap.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"), (actDataMap.get("bookingdates.checkin")));
        assertEquals(bookingdatesMap.get("checkout"), (actDataMap.get("bookingdates.checkout")));
        assertEquals(expectedData.get("additionalneeds"), actDataMap.get("additionalneeds"));
    }
}
