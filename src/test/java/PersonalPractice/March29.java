package PersonalPractice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
@DisplayName("Spartans")
public class March29 extends SpartanNoAuthBaseTest {
    @Test
    public void task1(){
    Response response =
        given()
                .log().uri()
                .contentType("application/json").
        when()
                .get("/spartans");


                assertThat(response.statusCode(), is (200) );
    }

    @Test
    public void task2(){
        given()
                .log().all()
                .pathParam("id", 45)
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", is("Heddie"))
                .body("gender", is("Female"))


        ;
    }

    @Test
    public void task3(){

        given()
                .log().uri()
                .queryParam("nameContains", "an")
                .queryParam("gender", "Female").
        when()
                .get("/spartans/search/").
        then()
                .statusCode(200)
                .body("totalElement", is(391));


    }




}
