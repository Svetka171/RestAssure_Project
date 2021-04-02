package day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
@DisplayName("Spartan Test with path variable and query param")
public class SpartansTestQuery extends SpartanNoAuthBaseTest {

    @Test
    public void getOneSpartan(){
        get("/spartans/16").prettyPeek();

        //I want to provide 16 as path var
        //I  want to provide Accept header
        Response r1 = given()
                         .header("Accept", "application/json")
                         .pathParam("spartan_id",16).
                      when()
                            .get("/spartans/{spartan_id}")
                            .prettyPeek();

        //alternative option
        Response r2=
        given()
                .accept("application/json"). //same as header
        when()
                .get("/spartans/{spartan_id}",16)
                .prettyPeek();

    }

    @DisplayName("logging the request")
    @Test
    public void getOneSpartanWithLog() {
        Response response =
                given()
                    .log().all()
                    .accept("application/json")
                    .pathParam("id",16).
                when()
                    .get("spartans/{id}")
                    .prettyPeek()
                ;
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(response.path("name"), is("Wonder Woman"));

    }



}
