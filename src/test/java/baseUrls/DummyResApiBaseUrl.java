package baseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyResApiBaseUrl {

    protected RequestSpecification specDummyRest;

    @Before
    public void setUp(){
        specDummyRest =new RequestSpecBuilder().
                setBaseUri("https://dummy.restapiexample.com/api/v1").build();

    }
}
