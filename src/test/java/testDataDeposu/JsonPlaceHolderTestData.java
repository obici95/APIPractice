package testDataDeposu;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public int basariliStatusCode=200;

    public JSONObject expedtedDataOlustur(){
        JSONObject body=new JSONObject();

        body.put("userId", 2);
        body.put("id", 22);
        body.put("title","distinctio vitae autem nihil ut molestias quo");
        body.put("completed", true);

        return body;
    }

    public Map<String,Object> expDataMapMethod(Integer userId, String title, Boolean completed ){

        Map<String,Object> expDataMap=new HashMap<>();
        expDataMap.put("userId",userId);
        expDataMap.put("title",title);
        expDataMap.put("completed",completed);

        return expDataMap;
    }

    public JSONObject expDataMapMethodJson(Integer userId, String title, Boolean completed ){

        JSONObject expDatajson=new JSONObject();
        expDatajson.put("userId",userId);
        expDatajson.put("title",title);
        expDatajson.put("completed",completed);

        return expDatajson;
    }

    public Map<String,Object> expectedDataMapOlustur(Integer userId,String title, String body){

        Map<String,Object> requestBodyMap = new HashMap<>();

        requestBodyMap.put("userId",userId);
        requestBodyMap.put("title",title);
        requestBodyMap.put("body",body);

        return requestBodyMap;
    }

}
