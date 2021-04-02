package day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class QueryParameter extends SpartanNoAuthBaseTest {
    @DisplayName("test get//spartans/search endpoint")
    @Test
    public void search(){
        Response responce =
                given()
                    .log().all()
                    .queryParam("nameContains", "Mustafa")
                    .queryParam("gender", "Male").
                when()
                    .get("/spartans/search")
                    .prettyPeek();


        assertThat(responce.path("totalElement"), is(110));

    }

}
