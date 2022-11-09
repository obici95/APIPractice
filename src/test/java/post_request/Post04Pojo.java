package post_request;

import baseUrls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends RestfulBaseUrl {
       /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        },
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test
    public void post04Pojo() {
        //Set the Url
        specRestFul.pathParam("pp1","booking");

        //Set the Expected Data
        BookingDatesPojo expBookingDatesData=new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo expData=new BookingPojo("Ali","Can",999,true,expBookingDatesData,"Breakfast");
        System.out.println("expData = " + expData);

        //Send the Request and Get the Response
       Response response = given().spec(specRestFul).contentType(ContentType.JSON).body(expData).when().post("{pp1}");
       response.prettyPrint();

       //Do ASsertion
       BookingResponseBodyPojo actData=response.as(BookingResponseBodyPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expData.getFirstname(),actData.getBooking().getFirstname());
        assertEquals(expData.getLastname(),actData.getBooking().getLastname());
        assertEquals(expData.getTotalprice(),actData.getBooking().getTotalprice());

        assertEquals(expBookingDatesData.getCheckin(),actData.getBooking().getBookingdates().getCheckin());
        assertEquals(expBookingDatesData.getCheckout(),actData.getBooking().getBookingdates().getCheckout());

        assertEquals(expData.getAdditionalneeds(),actData.getBooking().getAdditionalneeds());
    }
}
