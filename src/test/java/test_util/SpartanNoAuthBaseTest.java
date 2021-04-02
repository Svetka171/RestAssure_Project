package test_util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanNoAuthBaseTest {

    @BeforeAll
    public static void init(){
        // as a homework , put these information
        // in configurations.properties file
        // this will set the part of URL at RestAssured
        RestAssured.baseURI     = Configuration_reader.getProperties("SpartanURL")  ;
//        RestAssured.port = 8000 ;
        RestAssured.basePath    = Configuration_reader.getProperties("/api") ;
    }

    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }


}
