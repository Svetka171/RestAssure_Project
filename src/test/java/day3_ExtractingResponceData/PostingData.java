package day3_ExtractingResponceData;
import io.restassured.http.ContentType;
import pojo.Spartan;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class PostingData extends SpartanNoAuthBaseTest {
    @Test
    public void addPostWithStringBody(){
        /*
        "name" : "Abigale",
        "gender" : "Female",
        "phone" : 1800233232
         */
        String postStrBody = "{\"name\" : \"Abigale\",\n" +
                "        \"gender\" : \"Female\",\n" +
                "        \"phone\" : 1800233232}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(postStrBody).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Abigale"))
        ;

    }
    @Test
    public void externalData(){
        // Single singleSpartan.json with the same contact
        File jsonFile = new File("singleSpartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonFile).
        when()
                .post("/spartans").
        then()
                .statusCode(201);


    }

    @Test
    public void postDataWithMapObject(){
        /*
        "name" : "Abigale",
        "gender" : "Female",
        "phone" : 1800233232
         */

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", "Abigale");
        bodyMap.put("gender", "Female");
        bodyMap.put("phone", 1800233232);



        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
        when()
                .post("/spartans").
        then()
                .statusCode(201)   ;

    }

    @Test
    public void pojoIntro(){
        Spartan sp = new Spartan("Abigale", "Female", 1800233238);



        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp).
        when()
                .post("/spartans").
        then()
                .statusCode(201)   ;

    }

}
