package day1;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.* ;

public class RestAssured_Intro {
    @DisplayName("Intro to Rest Assured")
    @Test
    public void tstHelloEndPoint(){
        //http://3.82.193.59:8000/api/hello
        Response responce = get("http://3.82.193.59:8000/api/hello");
        System.out.println("responce.statusCode() = " + responce.statusCode());
        System.out.println("responce.getHeader() = " + responce.getHeader("Content-type"));
        System.out.println("responce.asString() = " + responce.asString());
        System.out.println("responce.getStatusCode() = " + responce.getStatusCode());
        System.out.println("responce.getContentType() = " + responce.getContentType());
        System.out.println("responce.getContentType() = " + responce.contentType());

        assertThat(responce.statusCode(), is(200));
        assertThat(responce.contentType(), is ("text/plain;charset=UTF-8"));
        assertThat(responce.contentType(), is(startsWith("text/plain")));
        assertThat(responce.asString(), is("Hello from Sparta"));

        System.out.println("==========");
        responce.prettyPrint();
        responce.prettyPeek();
        System.out.println("----------------------------------------------");
    }


    @DisplayName("Testing GET/api/spartans/{id} endpoint")
    @Test
    public void testSingleSpartan(){
        //http://3.82.193.59:8000/api/spartans/4
        Response response1 = get("http://3.82.193.59:8000/api/spartans/4").prettyPeek();

        assertThat(response1.contentType(), is("application/json"));
        assertThat(response1.statusCode(), is(200));
        assertThat(response1.header("Connection"), equalTo("keep-alive"));

        //System.out.println(response1.asString());

        //getting the field value of Json body

        System.out.println("response1.path(\"id\") = " + response1.path("id"));

        int myId = response1.path("id");
        String name = response1.path("name");

    }


}
