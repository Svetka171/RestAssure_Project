package day4_PracticeMore;
import test_util.LibraryAppBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class LibraryAppAuthorizedRequestTest extends LibraryAppBaseTest {


    @DisplayName("GET /get_user_by_id/{user_id}")
    @Test
    public void testOneUser(){


        //we are sending request to this endpoint  : GET /get_user_by_id/1
        given()
                .log().all()
                .header("x-library-token", librarianToken)
                .pathParam("user_id", 1).
        when()
                .get("/get_user_by_id/{user_id}").
        then()
                .log().all()
                .statusCode(200)
        ;

    }
    @Test
    public void getAllUsers(){
        // /get_all_users
        List<String> allNames =
        given()

                .header("x-library-token", librarianToken).

        when()
                .get("/get_all_users").
        then()
                .statusCode(200).
        extract()
                .jsonPath()
                .getList("name", String.class);

        assertThat(allNames, hasSize(8665));

        Set<String> uniqueNames = new LinkedHashSet<>(allNames);
        System.out.println("uniqueNames.size() = " + uniqueNames.size());


    }



}