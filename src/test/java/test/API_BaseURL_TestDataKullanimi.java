package test;

import baseUrls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class API_BaseURL_TestDataKullanimi extends JsonPlaceHolderBaseUrl {

    /*
      https:jsonplaceholder.typicode.com/posts/22 url'ine bir GET
      request yolladigimizda donen respose'un status kodunun 200 ve
      response bodysinin asagida verilen ile ayni oldugunu test ediniz


      Rsponse Body ;

     {
    "userId": 2,
    "id": 22,
    "title": "distinctio vitae autem nihil ut molestias quo",
    "completed": true

    }
     */

    @Test
    public void name() {
        //1- URL ve Request Body olustur
        specJsonPlace.pathParam("pp1",22);

        //2- Expected Data olustur
        JsonPlaceHolderTestData jsonPlaceHolder=new JsonPlaceHolderTestData();
        JSONObject expBody= jsonPlaceHolder.expedtedDataOlustur();

        // 3- Responsu kaydet
        Response responce=given().spec(specJsonPlace).when().get("{pp1}");


        //4-Do Assertion
        JsonPath respjsonPath=responce.jsonPath();

        assertEquals(jsonPlaceHolder.basariliStatusCode,responce.getStatusCode());
        assertEquals(expBody.getInt("userId"),respjsonPath.getInt("userId"));
        assertEquals(expBody.getString("title"),respjsonPath.getString("title"));
        assertEquals(expBody.getBoolean("completed"),respjsonPath.getBoolean("completed"));







    }
}
