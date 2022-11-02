package testDataDeposu;

import org.json.JSONObject;

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
}
