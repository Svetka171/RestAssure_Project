package PersonalPractice;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;

public class March30 {

    //http://www.omdbapi.com/?apikey=c5c3f6ba&t

    @BeforeAll
    @Test
    public static void init(){
        baseURI = "http://www.omdbapi.com";

    }
    @Test
    public void test1(){

        JsonPath jp =
                given()
                        .log().all()
                        .queryParam("apikey", "c5c3f6ba")
                        .queryParam("t", "Superman").
                when()
                        .get("/")
                        .prettyPeek().jsonPath();

        System.out.println("jp.getString(\"title\") = " + jp.getString("Title"));
        System.out.println("jp.getInt(\"Year\") = " + jp.getInt("Year"));
        System.out.println("jp.getDouble(\"imdbRating\") = " + jp.getDouble("imdbRating"));
        System.out.println("jp.getString(\"Ratings[1].Source\") = " + jp.getString("Ratings[1].Source"));
        System.out.println("jp.getString(\"Ratings[0].Value\") = " + jp.getString("Ratings[0].Value"));
    }

    @Test
    public void test2(){
        JsonPath jp =
                given()
                        .log().all()
                        .queryParam("apikey", "c5c3f6ba")
                        .queryParam("s", "Flash")
                        .queryParam("type", "series").
                when()
                        .get("/")
                        .prettyPeek().jsonPath();

        System.out.println("jp.getString(\"Search[2].Title\") = " + jp.getString("Search[2].Title"));
        System.out.println("jp.getString(\"Search[2].Year\") = " + jp.getString("Search[2].Year"));
        System.out.println("jp.getString(\"Search[2].imdbID\") = " + jp.getString("Search[2].imdbID"));

       List<String> imdbID = jp.getList("Search.imdbID", String.class);
        System.out.println("imdbID = " + imdbID);

        System.out.println("jp.getInt(\"totalResults\") = " + jp.getInt("totalResults"));
        List<String> movieTitles = jp.getList("Search.Title", String.class);
        System.out.println("movieTitles = " + movieTitles);


    }





}
