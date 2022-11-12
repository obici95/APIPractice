package get_request;

import baseUrls.DummyResApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyResApiBaseUrl {
      /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get request
    Then
         i) Status code is 200
    And
       ii) There are 24 employees
    And
      iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
       iv) The greatest age is 66
    And
        v) The name of the lowest age is "Tatyana Fitzpatrick"
    And
       vi) Total salary of all employees is 6,644,770
     */

    @Test
    public void get16() {
        specDummyRest.pathParam("pp1","employees");

        Response response =given().spec(specDummyRest).when().get("{pp1}");
        response.prettyPrint();

        //There are 24 employees , "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().statusCode(200).body("data.id",hasSize(24),
       "data.employee_name",hasItems("Tiger Nixon" , "Garrett Winters"));

        //The greatest age is 66
        List<Integer> ages= response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("Sorted ages = " + ages);
        System.out.println(ages.get(ages.size()-1));
        assertEquals(66, (int)(ages.get(ages.size()-1)));



        //The name of the lowest age is "Tatyana Fitzpatrick"

       String lowestAgeEmployee = response.jsonPath().getString("data.findAll{it.employee_age == "+ages.get(0)+"}");
        System.out.println("lowestAgeEmployee = " + lowestAgeEmployee);

        //       vi) Total salary of all employees is 6,644,770
        List <Integer> salaries=response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);

        //1. yol
        int sum=0;
        for (int w :salaries){
            sum += w;
        }
        System.out.println(sum);

        assertEquals(6644770,sum);

        //2. yol lambda
       int sum2= salaries.stream().reduce(0, Integer::sum);
        assertEquals(6644770,sum2);

       //3.yol
       int sum3=salaries.stream().reduce(0, Math::addExact);
        assertEquals(6644770,sum3);

    }


}
