package PersonalPractice.LibraryApp2;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class BaseClass {

    public static String libraryToken2;

    @BeforeAll
    public static void init(){
        //http://library2.cybertekschool.com/rest/v1
        baseURI = "http://library2.cybertekschool.com";
        basePath = "/rest/v1";
        libraryToken2 = getToken2("librarian570@library",
                "2gCucjjn");
    }

    public static String getToken2(String username, String password){

        return
        given()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password", password).
        when()
                .post("/login")
                .path("token");

    }

    public static Map<String, Object> newBook(){
        Faker faker = new Faker();
        Map<String, Object> myBookMap = new LinkedHashMap<>();

        myBookMap.put("name", faker.book().title()      );
        myBookMap.put("isbn", faker.number().digits(8) );
        myBookMap.put("year", faker.number().numberBetween(1600, 2021));
        myBookMap.put("author",faker.book().author() );
        myBookMap.put("book_category_id", faker.number().numberBetween(1,20)  );
        myBookMap.put("description",faker.chuckNorris().fact()  );

        return myBookMap;
    }



    @AfterAll
    public static void cleanUp(){
        reset();
    }




}
