package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {

    protected RequestSpecification specGoRest;

    @Before
    public void setUp(){
        specGoRest=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();
    }

}
