package get_request;

import baseUrls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingDatesPojo;
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

        BookingDatesPojo bookingdates=new BookingDatesPojo("2018-01-01","2019-01-01");


        BookingPojo bookingPojo =new BookingPojo("Jim","Brown",111,true,bookingdates,"Breakfast");
        System.out.println(bookingPojo);

        Response response=given().
                contentType(ContentType.JSON).
                spec(specRestFul).body(bookingPojo).
                when().
                post("/{pp1}");
        response.prettyPrint();

        Bookingid responseBody = response.as(Bookingid.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(),responseBody.getBookingPojo().getFirstname());
        assertEquals(bookingPojo.getLastname(),responseBody.getBookingPojo().getLastname());
        assertEquals(bookingPojo.getTotalprice(),responseBody.getBookingPojo().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(),responseBody.getBookingPojo().getDepositpaid());
        assertEquals(bookingPojo.getBookingdates().getCheckin(),responseBody.getBookingPojo().getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(),responseBody.getBookingPojo().getBookingdates().getCheckout());
    }


}
