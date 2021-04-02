package day2;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
public class VerifyingResponse extends SpartanNoAuthBaseTest {

    @DisplayName("Verifying responce ")
    @Test
    public void testOneSpartanInOneShot(){


        given()
                .log().all()
                .pathParam("id",60).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)
                .header("Content-Type", is("application/json") )
                .contentType("application/json")
                .body("id", equalTo(60) )
                .body("name" , is("Nour") )
                .body("gender" , is("Female") )
                .body("phone" , equalTo(9234543890L))
        ;

    }
}
