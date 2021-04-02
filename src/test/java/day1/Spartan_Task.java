package day1;

import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static io.restassured.RestAssured.* ;


@DisplayName("Spartan app get request")
public class Spartan_Task  extends SpartanNoAuthBaseTest {
    //http://3.82.193.59:8000/api/spartans


    @Test
    public void sayHello(){
        get("/hello").prettyPeek();
    }

    @Test
    public void getAllSpartans(){
        get("/spartans").prettyPeek();

    }


}
