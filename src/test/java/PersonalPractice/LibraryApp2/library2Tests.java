package PersonalPractice.LibraryApp2;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class library2Tests extends BaseClass{

    String username = "librarian570@library";
    String password = "2gCucjjn";
    String token;



    @Test
    // get token to make sure its not empty
    public void testLogIn(){

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password", password).
        when()
                .post("/login").
        then()
                .statusCode(200)
                .log().all()
                .body("token", is(not(empty()))).
        extract()
                .path("token");

    }

    @Test
    // first send request to POST /login extract token
    // then send request to POST /decode to verify emails and other info
    public void testToken(){

        String token =
                given()
                        .contentType(ContentType.URLENC)
                        .formParam("email", username)
                        .formParam("password", password).
                when()
                        .post("/login").
                then()
                        .statusCode(200).
                extract()
                        .path("token");
        System.out.println(token);


        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("token", token).
        when()
                .post("/decode").
        then()
                .statusCode(200)
                .body("email", is(username))
                .body("id", is("846"));



    }

    @DisplayName("Test GET /dashboard_stats endpoint")
    @Test
    public void testDashboardNumbers() {
        //1st get token
        String token =
                given()
                        .contentType(ContentType.URLENC)
                        .formParam("email", username)
                        .formParam("password", password).
                        when()
                        .post("/login").
                        then()
                        .statusCode(200).
                        extract()
                        .path("token");
        System.out.println(token);

        //2nd get how many users
        String users =
                given()
                        .header("x-library-token", token).
                when()
                        .get("/dashboard_stats").
                then()
                        .statusCode(200).
                extract()
                        .path("users");
        System.out.println("users = " + users);

        String book_count =
                given()
                        .header("x-library-token", token).
                when()
                        .get("/dashboard_stats").
                then()
                        .statusCode(200).
                extract()
                        .path("book_count");
        System.out.println("book_count = " + book_count);

        String borrowed_books =
                given()
                        .header("x-library-token", token).
                when()
                        .get("/dashboard_stats").
                        then()
                        .statusCode(200).
                        extract()
                        .path("borrowed_books");
        System.out.println("borrowed_books = " + borrowed_books);

        given()
                .header("x-library-token", token).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .body("users", is(users))
                .body("book_count", is(book_count))
                .body("borrowed_books", is(borrowed_books));

        JsonPath jp =
                given()
                        .header("x-library-token", token).
                when()
                        .get("/dashboard_stats").
                then()
                        .extract().jsonPath();


        assertThat(jp.getString("book_count"), is( book_count));

    }

    @DisplayName("GET /get_user_by_id/{user_id}")
    @Test
    public void testOneUser(){
        System.out.println(libraryToken2);

        String name =
        given()

                .header("x-library-token", libraryToken2)
                .pathParam("id", 1).
        when()
                .get("/get_user_by_id/{id}") .
        then()
                .statusCode(200).
        extract()
                .path("full_name")
                ;
        given()
                .header("x-library-token", libraryToken2)
                .pathParam("id", 1).
        when()
                .get("/get_user_by_id/{id}") .
        then()
                .log().all()
                .statusCode(200)
                .body("full_name", is(name))

        ;
    }

    @Test
    public void getBookCategories(){

        List<String> categories =
                given()
                        .header("x-library-token", libraryToken2).
                when()
                        .get("/get_book_categories").
                then()
                        .statusCode(200).
                extract()
                        .jsonPath().getList("name");
        System.out.println("categories = " + categories);
        System.out.println(categories.size());
        System.out.println(categories.contains("Comic and Graphic Novel"));

        given()
                .header("x-library-token", libraryToken2).
        when()
                .get("/get_book_categories").
        then()
                .statusCode(200)
                .body("name[19]", is("Poetry"))
                .body("name", hasSize(categories.size()))
                .body("name[0]", is(categories.get(0)));

    }

    @Test
    public void getBookByID(){
        //  /get_book_by_id/{id}
        String name =
                given()
                        .header("x-library-token", libraryToken2)
                        .pathParam("id", 7).
                when()
                        .get("/get_book_by_id/{id}").
                then()
                        .statusCode(200).
                extract()
                        .path("name");
        System.out.println("name = " + name);


        given()
                .header("x-library-token", libraryToken2)
                .pathParam("id", 7).
        when()
                .get("/get_book_by_id/{id}").
        then()
                .statusCode(200)
                .body("name",is (name));

    }

    @Test
    public void postBook(){
        Faker faker = new Faker();

        given()
                .header("x-library-token", libraryToken2)
                .contentType(ContentType.URLENC)
                .formParam("name", faker.name().firstName() )
                .formParam("isbn", faker.number().digits(12))
                .formParam("year", 2000)
                .formParam("author", faker.name().firstName())
                .formParam("book_category_id", faker.number().numberBetween(5,108))
                .formParam("description", "whyycvawblivka").
        when()
                .post("/add_book").
        then()
                .statusCode(200)
                .body("message", is("The book has been created."));

    }

    @Test
    public void deleteBook(){
        given()
                .header("x-library-token", libraryToken2)
                .pathParam("id", 7).
        when()
                .delete("/delete_book/{id}").
        then()
                .statusCode(403);

    }

    @Test
    public void postBook1(){
        Map<String, Object> newMap = newBook();

        String bookId =
        given()
                .header("x-library-token", libraryToken2)
                .contentType(ContentType.URLENC)
                .formParams(newMap).
        when()
                .post("/add_book").
        then()
                .statusCode(200).
        extract()
                .path("book_id");
        System.out.println(bookId);

        given()
                .header("x-library-token", libraryToken2)
                .contentType(ContentType.URLENC)
                .pathParam("id", bookId).
        when()
                .get("/get_book_by_id/{id}").
        then()
                .statusCode(200)
                .body("id", is(bookId))
                .body("name", is(newMap.get("name")))
                .body("isbn", is(newMap.get("isbn")));





    }





}
