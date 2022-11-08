package post_request;

import baseUrls.RestfulBaseUrl;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testDataDeposu.RestFulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
     /*
        Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
                                             .
      */

    @Test
    public void post02() {
        //Set the URL
        specRestFul.pathParam("pp1","booking");

        //Set the expected Data
        RestFulTestData objRestFul=new RestFulTestData();
        Map<String,String> expDataBookingDatesMap=objRestFul.bookingDatesMethod("2021-09-09","2021-09-21");
        Map<String,Object> expDataMap=objRestFul.expDataMapMethod("John","Doe",11111,true,expDataBookingDatesMap);
        System.out.println(expDataMap);


        //Send the Request and Get the Response
        Response response =given().spec(specRestFul).contentType(ContentType.JSON).body(expDataMap).when().post("{pp1}");
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actDataMap=response.as(HashMap.class);//De-Serialization
        System.out.println(actDataMap);
       assertEquals(expDataMap.get("firstname"),((Map)actDataMap.get("booking")).get("firstname"));
       assertEquals(expDataMap.get("lastname"),((Map)actDataMap.get("booking")).get("lastname"));
       assertEquals(expDataMap.get("totalprice"),((Map)actDataMap.get("booking")).get("totalprice"));
       assertEquals(expDataMap.get("depositpaid"),((Map)actDataMap.get("booking")).get("depositpaid"));

       assertEquals(expDataBookingDatesMap.get("checkin"),((Map)((Map)actDataMap.get("booking")).get("bookingdates")).get("checkin"));
       assertEquals(expDataBookingDatesMap.get("checkout"),((Map)((Map) actDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }


    // JSONObject ile
    @Test
    public void post02b() {
        //Set the URL
        specRestFul.pathParam("pp1","booking");

        //Set the expected Data
        RestFulTestData objRestFul=new RestFulTestData();
        JSONObject expBookingDatesJson =objRestFul.bookingDatesMethodJson("2021-09-09","2021-09-21");

        JSONObject expDataJson=objRestFul.expDataMethodJson("John","Doe",11111,true,expBookingDatesJson);
        System.out.println(expDataJson);


        //Send the Request and Get the Response
        Response response =given().
                spec(specRestFul).
                contentType(ContentType.JSON).
                body(expDataJson.toString()).
                when().
                post("{pp1}");

        response.prettyPrint();


        //Do Assertion
        JsonPath actDataJpath =response.jsonPath();//De-Serialization
        System.out.println(actDataJpath.prettify());


        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200);

        assertEquals(expDataJson.getString("firstname"),actDataJpath.get("booking.firstname"));
        assertEquals(expDataJson.getString("lastname"),actDataJpath.get("booking.lastname"));
        assertEquals(expDataJson.getNumber("totalprice"),actDataJpath.get("booking.totalprice"));
        assertEquals(expDataJson.getBoolean("depositpaid"),actDataJpath.get("booking.depositpaid"));
        assertEquals(expBookingDatesJson.getString("checkin"),actDataJpath.get("booking.bookingdates.checkin"));
        assertEquals(expBookingDatesJson.getString("checkout"),actDataJpath.get("booking.bookingdates.checkout"));


    }

}
