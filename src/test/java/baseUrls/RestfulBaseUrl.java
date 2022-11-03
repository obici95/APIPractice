package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestfulBaseUrl {

    protected RequestSpecification specRestFul;

    @Before
    public void setup(){
        specRestFul=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}
