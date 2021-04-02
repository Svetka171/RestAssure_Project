package test_util;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class LibraryAppBaseTest {
    public static String librarianToken;


    @BeforeAll
    public static void init(){

        baseURI  = "http://library1.cybertekschool.com" ;
        basePath = "/rest/v1" ;

        librarianToken =
                getToken("librarian69@library", "KNPXrm3S");

    }

    public static String getToken(String username, String password){

        return given()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password" , password).
                        when()
                .post("/login")
                .path("token");

    }


    @AfterAll
    public static void cleanup(){
        reset();
    }


}
