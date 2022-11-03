package test;

import baseUrls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Booking;
import pojos.Bookingdates;
import pojos.Bookingid;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_PostRequestWithPojo extends RestfulBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking
    request body
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

    Status code 200
    response body
         {
    "bookingid": 9769,
    "booking": {
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
}

Process finished with exit code 0

     */

    @Test
    public void test01() {

        specRestFul.pathParam("pp1","booking");

        Bookingdates bookingdates=new Bookingdates("2018-01-01","2019-01-01");


        Booking booking=new Booking("Jim","Brown",111,true,bookingdates,"Breakfast");
        System.out.println(booking);

        Response response=given().
                contentType(ContentType.JSON).
                spec(specRestFul).body(booking).
                when().
                post("/{pp1}");
        response.prettyPrint();

        Bookingid responseBody = response.as(Bookingid.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(booking.getFirstname(),responseBody.getBooking().getFirstname());
        assertEquals(booking.getLastname(),responseBody.getBooking().getLastname());
        assertEquals(booking.getTotalprice(),responseBody.getBooking().getTotalprice());
        assertEquals(booking.getDepositpaid(),responseBody.getBooking().getDepositpaid());
        assertEquals(booking.getBookingdates().getCheckin(),responseBody.getBooking().getBookingdates().getCheckin());
        assertEquals(booking.getBookingdates().getCheckout(),responseBody.getBooking().getBookingdates().getCheckout());
    }


}
