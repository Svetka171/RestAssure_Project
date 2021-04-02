package day5_;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class Practice {
    /*
    Interview Questions :
Send request to  GET https://swapi.dev/api/people/
Find out average height of all people showed up in the response
     */
    @BeforeAll
    public static void init(){
        baseURI = "https://swapi.dev";
        basePath = "/api";
    }
    @AfterAll
    public static void cleanUp(){
        reset();
    }

    @Test
    public void test1() {
        //for 1 page only
        List<Integer> heights = get("/people/").
                jsonPath().getList("results.height", Integer.class);

        System.out.println("heights = " + heights);

        //How to find average from list
        int sum = 0;
        for (Integer each : heights) {
            sum += each;
        }
        System.out.println(sum / heights.size());
    }

       //for multiple pages
        @Test
        public void multiPage(){
            List<Integer> allHeights = new ArrayList<>() ;

            // send initial request , find total count and decide how many pages exists
            JsonPath jp =  get("/people").jsonPath() ;
            int peopleCount =  jp.getInt("count") ;  //82
            // if there is remainder we want to add 1 , if there is not keep it as is
            int pageCount = (peopleCount % 10==0)  ? peopleCount / 10  :  (peopleCount / 10)+1 ;


            List<Integer> firstPageHeights = jp.getList("results.height") ;
            allHeights.addAll(firstPageHeights ) ;


            // now it's time to loop and get the rest of the pages
            for (int pageNum = 2; pageNum <= pageCount ; pageNum++) {
                // GET /people?page = yourPageNumberGoesHere

                List<Integer> heightsOnThisPage =   get("/people?page="+pageNum )
                        .jsonPath().getList("results.height");
                allHeights.addAll( heightsOnThisPage ) ;
            }
            // third page has a value unknown and it's somehow got stored since getList get all all
            // jsonPath is backed by groovy language and it's allowing such value here so we will remove it
            allHeights.remove("unknown") ;
            System.out.println("allHeights = " + allHeights);
            System.out.println("allHeights.size() = " + allHeights.size());

        }












}
