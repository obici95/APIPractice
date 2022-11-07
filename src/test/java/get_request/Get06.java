package get_request;

import baseUrls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get06 extends ReqresBaseUrl {
      /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console // idsi 3ten buyuk olan tum idleri yazdir
              Assert that there are 3 ids greater than 3  // 3 idsinden buyuk 3 tane id var
            4)Print all names whose ids are less than 3 on the console  // id'si 3ten kucuk tum isimleri ekrana yazdiracagim
              Assert that the number of names whose ids are less than 3 is 2 // sonra da assert edecegim
    */

    @Test
    public void get06(){
        // Set the Url
        spec.pathParams("first","api","second","unknown");


        // Set the Expected Data

        //Send the Request and Get the Response
      Response response=  given().spec(spec).when().get("/{first}/{second}");
      response.prettyPrint();



        //Do Assertion

        //1-Status code is 200
        assertEquals(200,response.statusCode());

        //2-Print all pantone_values
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("data.pantone_value"));

        //3-Print all ids greater than 3 on the console
        List<Integer> ids=jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " + ids);

        //4-Assert that there are 3 ids greater than 3 / idsi 3ten buyuk 3 tane varmis bunu assert edecegim
        assertEquals(3,ids.size());

        //Print all names whose ids are less than 3 on the console

        List<String> names=jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);

        //Assert that the number of names whose ids are less than 3 is 2 // id'si 3ten kucuk olan isimlerin sayisi 2 tane olmali
        assertEquals(2,names.size());

    }
}
