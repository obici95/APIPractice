package test;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_Deserialization extends JsonPlaceHolderBaseUrl {


    /*
      https://jsonplaceholder.typicode.com/todos/70 url'ine asagidaki
      body'e sahip bir PUT request yolladigimizda donen response'un
      response body'sinin asagida verilen ile ayni olduguunu test ediniz

      Request Body

     {
    "userId": 7,
    "id": 70,
    "title": "voluptatem laborum magni",
    "body": "sunt repellendus quae\nest asperiores aut deleniti esse accusamus repellendus quia aut\nquia dolorem unde\neum tempora esse dolore"
  }

    Expected Data :

    {
    "userId": 7,
    "id": 70,
    "title": "voluptatem laborum magni",
    "body": "sunt repellendus quae\nest asperiores aut deleniti esse accusamus repellendus quia aut\nquia dolorem unde\neum tempora esse dolore"
  }
     */

    @Test
    public void test01() {
        // 1- URL ve request body olustur
        specJsonPlace.pathParam("pp1",70);
        JsonPlaceHolderTestData objJPHolder=new JsonPlaceHolderTestData();
        Map<String,Object> reqBodyMap=objJPHolder.expectedDataMapOlustur(7,"voluptatem laborum magni","sunt repellendus quae\nest asperiores aut deleniti esse accusamus repellendus quia aut\nquia dolorem unde\neum tempora esse dolore");
        System.out.println(reqBodyMap);

        // 2- Expected Data Olustur

        Map <String,Object> expDataMap=objJPHolder.expectedDataMapOlustur(7,"voluptatem laborum magni","sunt repellendus quae\nest asperiores aut deleniti esse accusamus repellendus quia aut\nquia dolorem unde\neum tempora esse dolore");

        //3- Response'u kaydet
        Response response=given().
                contentType(ContentType.JSON).
                spec(specJsonPlace).
                when().
                body(reqBodyMap).
                put("{pp1}");
        response.prettyPrint();

        // 4- Assertion

        assertEquals(objJPHolder.basariliStatusCode,response.getStatusCode());

        Map<String,Object> actDataMap=response.as(HashMap.class);
        System.out.println(actDataMap);

        assertEquals(expDataMap.get("userId"),actDataMap.get("userId"));
        assertEquals(expDataMap.get("title"),actDataMap.get("title"));
        assertEquals(expDataMap.get("body"),actDataMap.get("body"));

    }
}
