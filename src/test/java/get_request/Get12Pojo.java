package get_request;

import baseUrls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends RestfulBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/5
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like:
 		               {
                            "firstname": "James",
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
    public void get12Pojo() {
        //Set the Url
        specRestFul.pathParams("pp1","booking","pp2",5);

        //Set the Expected Data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2021-10-16","2022-07-08");

        BookingPojo expData=new BookingPojo("James","Brown",228,false,bookingDatesPojo,null);
        System.out.println(expData);

        //Send the Request and Get the Response
        Response response =given().spec(specRestFul).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
       BookingPojo actData= response.as(BookingPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(expData.getFirstname(),actData.getFirstname());
        assertEquals(expData.getLastname(),actData.getLastname());
        assertEquals(expData.getTotalprice(),actData.getTotalprice());
        assertEquals(expData.getDepositpaid(),actData.getDepositpaid());

        // bookingdates 1. yol
        assertEquals(expData.getBookingdates().getCheckin(),actData.getBookingdates().getCheckin());
        assertEquals(expData.getBookingdates().getCheckout(),actData.getBookingdates().getCheckout());
        assertEquals(expData.getAdditionalneeds(),actData.getAdditionalneeds());

        //2.yol
        assertEquals(expData.getBookingdates().getCheckin(),actData.getBookingdates().getCheckin());
        assertEquals(expData.getBookingdates().getCheckout(),actData.getBookingdates().getCheckout());

    }

}
