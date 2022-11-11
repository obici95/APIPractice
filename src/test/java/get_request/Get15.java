package get_request;

import baseUrls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import testDataDeposu.RestFulTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {

     /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                  {
                    "firstname": "Howard",
                    "lastname": "Sevilla",
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
    public void get15() {
        //Set the Url
        specRestFul.pathParams("pp1","booking","pp2",22);

        //Set the Expeted Data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expData=new BookingPojo("Howard","Sevilla",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expData = " + expData);

        //Send the Request and Get the Response
       Response response = given().spec(specRestFul).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println("actData = " + actData);

        //Soft Assertion
        //1.Adim: SoftAssert objesi olustur
        SoftAssert softAssert=new SoftAssert();

        //2. adim assertion yap
        softAssert.assertEquals(actData.getFirstname(),expData.getFirstname(),"Fistname uyusmadi");
        softAssert.assertEquals(actData.getLastname(),expData.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actData.getTotalprice(),expData.getTotalprice(),"Totalprice uyusmadi");
        softAssert.assertEquals(actData.getDepositpaid(),expData.getDepositpaid(),"Depositpaid uyusmadi");

        assertEquals(actData.getBookingdates().getCheckin(),expData.getBookingdates().getCheckin());
        assertEquals(actData.getBookingdates().getCheckout(),expData.getBookingdates().getCheckout());

        assertEquals(actData.getAdditionalneeds(),expData.getAdditionalneeds());

        //3. adim:assertAll() methodunu kullan
        softAssert.assertAll("Sorun var kodlara bakiniz");


        //Hard Assertion
        assertEquals(200,response.statusCode());
        assertEquals(expData.getFirstname(),actData.getFirstname());
        assertEquals(expData.getLastname(),actData.getLastname());
        assertEquals(expData.getTotalprice(),actData.getTotalprice());
        assertEquals(expData.getDepositpaid(),actData.getDepositpaid());

        assertEquals(expData.getBookingdates().getCheckin(),actData.getBookingdates().getCheckin());
        assertEquals(expData.getBookingdates().getCheckout(),actData.getBookingdates().getCheckout());

        assertEquals(expData.getAdditionalneeds(),actData.getAdditionalneeds());



    }
}
