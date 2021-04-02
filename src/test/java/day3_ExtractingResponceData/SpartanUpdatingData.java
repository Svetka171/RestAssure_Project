package day3_ExtractingResponceData;

import io.restassured.http.ContentType;
import pojo.Spartan;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;

public class SpartanUpdatingData extends SpartanNoAuthBaseTest {
    @Test
    public void updateWithMap(){
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", "Lamba");
        bodyMap.put("gender", "Female");
        bodyMap.put("phone", 1800233232);

        given()
                .log().all()
                .pathParam("id", 35)
                .contentType(ContentType.JSON)
                .body(bodyMap).
        when()
                .put("/spartans/{id}").
        then()
                .statusCode(204);


    }

    @Test
    public void updateWithPojo(){

        Spartan sp = new Spartan("Dean", "Male", 2359657825l);
        given()
                .log().all()
                .pathParam("id", 35)
                .contentType(ContentType.JSON)
                .body(sp).
        when()
                .put("/spartans/{id}").
        then()
                .statusCode(204);

    }


}
