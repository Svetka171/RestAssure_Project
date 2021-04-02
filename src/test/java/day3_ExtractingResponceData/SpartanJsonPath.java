package day3_ExtractingResponceData;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class SpartanJsonPath extends SpartanNoAuthBaseTest {
    //http://18.235.32.166:8000/api/spartans/160

    @Test
    public void test1(){
        Response responce =
        given()
                .pathParam("id", 160).
        when()
                .get("/spartans/{id}")
                .prettyPeek();

        int myID = responce.path("id");
        System.out.println(myID);

        JsonPath jp = responce.jsonPath();
        myID = jp.getInt("id");

        System.out.println(myID);

        long phoneNumber = jp.getLong("phone");
        System.out.println(phoneNumber);

        System.out.println(jp.getMap(""));

        Map<String, Object>  resultJsonInMap = jp.getMap("");
        System.out.println(resultJsonInMap);

    }

    @Test
    public void testGetAllSpartans(){
      //  Response response = get("/spartans");
      //  JsonPath jp = response.jsonPath();

        JsonPath jp = get("/spartans").jsonPath();

        System.out.println(jp.getInt("id[0]"));
        System.out.println(jp.getString("name[1]"));
        System.out.println("Length of name : " + jp.getString("name[1]").length());
        System.out.println(jp.getMap("[0]"));

        System.out.println("jp.getInt(\"[0].id\") = " + jp.getInt("[0].id"));

    }

    @Test
    public void testGetSearchSpartans(){
       //http://18.235.32.166:8000/api/spartans/search?nameContains=Abigale&gender=Male

        JsonPath jp =
                given()
                        .queryParam("nameContains","Abigale")
                        .queryParam("gender","Male")
                        .log().all().
                when()
                        .get("/spartans/search")
                        .jsonPath();
        // find out first guy id  , second guy name
        //  content[0].id           content[1].name
        System.out.println("jp.getInt(\" content[0].id\") = "
                + jp.getInt(" content[0].id"));
        System.out.println("jp.getString(\"content[1].name\") = "

                + jp.getString("content[1].name"));

        //open door by door!
        Map<String, Object> firstJson = jp.getMap("content[0]");
        System.out.println(firstJson);
    }

    @Test
    public void jsonArrayToList(){
        JsonPath jp =
                given()
                        .queryParam("nameContains","Abigale")
                        .queryParam("gender","Male")
                        .log().all().
                when()
                        .get("/spartans/search")
                        .prettyPeek()
                        .jsonPath();
        //save all id to list

        System.out.println("jp.getList(\"content.id\") = "
                + jp.getList("content.id"));

        //all name
        System.out.println("jp.getList(\"content.name\") = "
                + jp.getList("content.name"));

        List<Integer> allID = jp.getList("content.id");
        List<Integer> allID2 = jp.getList("content.id", Integer.class);
        List<String> allNames = jp.getList("content.name", String.class);


    }
    @DisplayName("Get list Practice")
    @Test
    public void testGetListOutOfSpartans(){
        JsonPath jp = get("/spartans").jsonPath();
        //save the list into list obj and assert size
        List<Integer> allID = jp.getList("id", Integer.class);
        System.out.println(allID);

        assertThat(allID, hasSize(12432));



    }

}
