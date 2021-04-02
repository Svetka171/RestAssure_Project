package day5_;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTestInJUnit {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,10})
    public void testPrintMultipleIteration(int num){

        System.out.println(num);
        assertTrue(num>3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sveta", "Dima", "Sasha"})
    public void test2(String num){

        System.out.println(num);
        assertTrue(num.equals("Sveta"));

    }

    @ParameterizedTest
    @ValueSource(shorts = { 22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void testZipcodes( short zip  ){

        System.out.println("zipcode = " + zip);

        given()
                .baseUri("https://api.zippopotam.us")
                .log().all()
                .pathParam("zipcode" ,   zip  ).
        when()
                .get("/us/{zipcode}").
        then()
                .statusCode( 200 )
                .log().all()
        ;



    }
}
