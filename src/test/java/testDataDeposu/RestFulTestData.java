package testDataDeposu;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RestFulTestData {

    public Map<String,String> bookingDatesMethod(String checkin,String checkout){

        Map<String,String> bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin",checkin);
        bookingDatesMap.put("checkout",checkout);

        return bookingDatesMap;
    }

    public Map<String,Object> expDataMapMethod (String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingdates ){

        Map<String,Object> expDataMap=new HashMap<>();
        expDataMap.put("firstname",firstname);
        expDataMap.put("lastname",lastname);
        expDataMap.put("totalprice",totalprice);
        expDataMap.put("depositpaid",depositpaid);
        expDataMap.put("bookingdates",bookingdates);

        return expDataMap;
    }

    public JSONObject bookingDatesMethodJson(String checkin, String checkout){


        JSONObject bookingDatesJson=new JSONObject();
        bookingDatesJson.put("checkin",checkin);
        bookingDatesJson.put("checkout",checkout);

        return bookingDatesJson;
    }

    public JSONObject expDataMethodJson(String firstname, String lastname, Integer totalprice, Boolean depositpaid, JSONObject bookingdates ){

        JSONObject expDataJson=new JSONObject();
        expDataJson.put("firstname",firstname);
        expDataJson.put("lastname",lastname);
        expDataJson.put("totalprice",totalprice);
        expDataJson.put("depositpaid",depositpaid);
        expDataJson.put("bookingdates",bookingdates);

        return expDataJson;
    }
}
